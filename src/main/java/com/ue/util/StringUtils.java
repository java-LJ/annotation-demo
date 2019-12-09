package com.ue.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author LJ
 * @site www.lijun.com
 * @Date 2019年02月22日
 * @Time 17:02
 */
public class StringUtils {
    // 私有的构造方法，保护此类不能在外部实例化
    private StringUtils() {
    }

    /**
     * 如果字符串等于null或去空格后等于""，则返回true，否则返回false
     *
     * @param s
     * @return
     */
    public static boolean isBlank(String s) {
        boolean b = false;
        if (null == s || s.trim().equals("")) {
            b = true;
        }
        return b;
    }

    /**
     * 如果字符串不等于null或去空格后不等于""，则返回true，否则返回false
     *
     * @param s
     * @return
     */
    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }

    /**
     * set集合转string
     * @param hasPerms
     * @return
     */
    public static String SetToString(Set hasPerms){
        return  Arrays.toString(hasPerms.toArray()).replaceAll(" ", "").replace("[", "").replace("]", "");
    }

    /**
     * 转换成模糊查询所需参数
     * @param before
     * @return
     */
    public static String toLikeStr(String before){
        return isBlank(before) ? null : "%"+before+"%";
    }

    /**
     *	将图片的服务器访问地址转换为真实存放地址
     * @param imgpath	图片访问地址（http://localhost:8080/uploadImage/2019/01/26/20190126000000.jpg）
     * @param serverDir	uploadImage
     * @param realDir	E:/temp/
     * @return
     */
    public static String serverPath2realPath(String imgpath, String serverDir, String realDir) {
        imgpath = imgpath.substring(imgpath.indexOf(serverDir));
        return imgpath.replace(serverDir,realDir);
    }

    /**
     * 过滤掉集合里的空格
     * @param list
     * @return
     */
    public static List<String> filterWhite(List<String> list){
        List<String> resultList=new ArrayList<String>();
        for(String l:list){
            if(isNotBlank(l)){
                resultList.add(l);
            }
        }
        return resultList;
    }

    /**
     * 从html中提取纯文本
     * @param strHtml
     * @return
     */
    public static String html2Text(String strHtml) {
        String txtcontent = strHtml.replaceAll("</?[^>]+>", ""); //剔出<html>的标签
        txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");//去除字符串中的空格,回车,换行符,制表符
        return txtcontent;
    }

    public static void main(String[] args) {
    }
}
