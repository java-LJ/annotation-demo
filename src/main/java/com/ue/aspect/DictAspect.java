package com.ue.aspect;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ue.annotation.Dict;
import com.ue.service.DataItemService;
import com.ue.util.ObjConvertUtils;
import com.ue.util.PageUtils;
import com.ue.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用于翻译字典的切面类
 * @author LiJun
 * @Date 2019/11/28
 * @Time 9:55
 */
@Aspect
@Component
@Slf4j
public class DictAspect {
    private static String DICT_TEXT_SUFFIX = "_dictText";

    @Autowired
    private DataItemService dataItemService;

    //定义切点Pointcut拦截所有对服务器的请求
    @Pointcut("execution( * com.ue.controller.*.*(..))")
    public void excudeService() {

    }

    /**
     * 这是触发excudeService的时候会执行的，在环绕通知中目标对象方法被调用后的结果进行再处理
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        //这是定义开始事件
        long time1 = System.currentTimeMillis();
        //这是方法并获取返回结果
        Object result = pjp.proceed();
        //这是获取到结束时间
        long time2 = System.currentTimeMillis();
        log.info("获取JSON数据耗时：" + (time2 - time1) + "ms");
        //解析开始时间
        long start = System.currentTimeMillis();
        //开始解析（翻译字段内部的值凡是打了@Dict这玩意的都会被翻译）
        this.parseDictText(result);
        //解析结束时间
        long end = System.currentTimeMillis();
        log.info("解析注入JSON数据耗时：" + (end - start) + "ms");
        return result;
    }

    /**
     * 本方法针对返回对象为Result的PageUtils的分页列表数据进行动态字典注入
     * 字典注入实现 通过对实体类添加注解@Dict来标识需要的字典内容,字典分为单字典dataSource即可
     * 示例为Student   
     * 字段为stu_sex添加了注解@Dict(dicDataSource = "stu_sex")会在字典服务立马查出来对应的text然后在请求list的时候将这个字典text，以字段名称加_dictText形式返回到前端
     * 例如输入当前返回值的就会多出一个stu_sex_dictText字段
     * {
     *      stu_sex:1,
     *      stu_sex_dictText:"男"
     * }
     * 前端直接取stu_sex_dictText的值在table里面进行展示，无需再进行前端的字典转换了
     * customRender:function (text) {
     *      if(text==1){
     *          return "男";
     *      }else if(text==2){
     *          return "女";
     *      }else{
     *          return text;
     *      }
     * }
     * 目前vue是这么进行字典渲染到table上的多了就很麻烦了 这个直接在服务端渲染完成前端可以直接用
     * @param result
     */
    private void parseDictText(Object result) {
        if (result instanceof PageUtils) {
            List<JSONObject> items = new ArrayList<>();
            PageUtils pageUtils = (PageUtils) result;
            //循环查找出来的数据
            for (Object record : pageUtils.getData()) {
                ObjectMapper mapper = new ObjectMapper();
                String json = "{}";
                try {
                    //解决@JsonFormat注解解析不了的问题详见SysAnnouncement类的@JsonFormat
                    json = mapper.writeValueAsString(record);
                } catch (JsonProcessingException e) {
                    log.error("Json解析失败：" + e);
                }
                JSONObject item = JSONObject.parseObject(json);

                //解决继承实体字段无法翻译问题
                for (Field field : ObjConvertUtils.getAllFields(record)) {
                    //解决继承实体字段无法翻译问题
                    if (field.getAnnotation(Dict.class) != null) {//如果该属性上面有@Dict注解，则进行翻译
                        String datasource = field.getAnnotation(Dict.class).dictDataSource();//拿到注解的dictDataSource属性的值
                        String text = field.getAnnotation(Dict.class).dictText();//拿到注解的dictText属性的值
                        //获取当前带翻译的值
                        String key = String.valueOf(item.get(field.getName()));
                        //翻译字典值对应的text值
                        String textValue = translateDictValue(datasource, key);
                        //DICT_TEXT_SUFFIX的值为，是默认值：
                        //public static final String DICT_TEXT_SUFFIX = "_dictText";
                        log.debug("字典Val: " + textValue);
                        log.debug("翻译字典字段：" + field.getName() + DICT_TEXT_SUFFIX + "： " + textValue);
                        //如果给了文本名
                        if (!StringUtils.isBlank(text)) {
                            item.put(text, textValue);
                        } else {
                            //走默认策略
                            item.put(field.getName() + DICT_TEXT_SUFFIX, textValue);
                        }
                    }
                    //date类型默认转换string格式化日期
                    if (field.getType().getName().equals("java.util.Date") && field.getAnnotation(JsonFormat.class) == null && item.get(field.getName()) != null) {
                        SimpleDateFormat aDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        item.put(field.getName(), aDate.format(new Date((Long) item.get(field.getName()))));
                    }
                }
                items.add(item);
            }
            pageUtils.setData(items);
        }
    }


    /**
     * 翻译字典文本
     * @param datasource
     * @param key
     * @return
     */
    private String translateDictValue(String datasource, String key) {
        //如果key为空直接返回就好了
        if (ObjConvertUtils.isEmpty(key)) {
            return null;
        }
        StringBuffer textValue = new StringBuffer();
        //分割key值
        String[] keys = key.split(",");
        //循环keys中的所有值
        for (String k : keys) {
            String tmpValue = null;
            log.debug("字典key：" + k);
            if (k.trim().length() == 0) {
                continue;//跳过循环
            }
            tmpValue = dataItemService.selectByDatasourceKey(datasource, k.trim());

            if (tmpValue != null) {
                if (!"".equals(textValue.toString())) {
                    textValue.append(",");
                }
                textValue.append(tmpValue);
            }
        }
        //返回翻译的值
        return textValue.toString();
    }
}