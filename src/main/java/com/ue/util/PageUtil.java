package com.ue.util;

import java.util.Map;
import java.util.Set;

/**
 * @author LJ
 * @site www.lijun.com
 * @Date 2019年02月22日
 * @Time 18:01
 * 基于bootstrap4生成分页代码
 */
public class PageUtil {
    public static String createPageCode(PageBean pageBean) {
        StringBuffer sb = new StringBuffer();
        /*
         * 拼接向后台提交数据的form表单
         * 	注意：拼接的form表单中的page参数是变化的，所以不需要保留上一次请求的值
         */
        sb.append("<form id='pageBeanForm' action='"+pageBean.getUrl()+"' method='post'>");
        sb.append("<input type='hidden' name='page'>");
        Map<String, String[]> parameterMap = pageBean.getParamMap();
        if(parameterMap != null && parameterMap.size() > 0) {
            Set<Map.Entry<String, String[]>> entrySet = parameterMap.entrySet();
            for (Map.Entry<String, String[]> entry : entrySet) {
                if(!"page".equals(entry.getKey())) {
                    String[] values = entry.getValue();
                    for (String val : values) {
                        sb.append("<input type='hidden' name='"+entry.getKey()+"' value='"+val+"'>");
                    }
                }
            }
        }
        sb.append("</form>");

        if(pageBean.getTotal()==0){
            return "未查询到数据";
        }else{
            sb.append("<li class='page-item'><a class='page-link' href='javascript:gotoPage(1)'>首页</a></li>");
            if(pageBean.getPage()>1){
                sb.append("<li class='page-item'><a class='page-link' href='javascript:gotoPage("+pageBean.getPreviousPage()+")'>上一页</a></li>");
            }else{
                sb.append("<li class='page-item disabled'><a class='page-link' href='javascript:gotoPage(1)'>上一页</a></li>");
            }
            for(int i=pageBean.getPage()-1;i<=pageBean.getPage()+1;i++){
                if(i<1||i>pageBean.getMaxPage()){
                    continue;
                }
                if(i==pageBean.getPage()){
                    sb.append("<li class='page-item active'><a class='page-link' href='#'>"+i+"</a></li>");
                }else{
                    sb.append("<li class='page-item'><a class='page-link' href='javascript:gotoPage("+i+")'>"+i+"</a></li>");
                }
            }
            if(pageBean.getPage()<pageBean.getMaxPage()){
                sb.append("<li class='page-item'><a class='page-link' href='javascript:gotoPage("+pageBean.getNextPage()+")'>下一页</a></li>");
            }else{
                sb.append("<li class='page-item disabled'><a class='page-link' href='javascript:gotoPage("+pageBean.getMaxPage()+")'>下一页</a></li>");
            }
            sb.append("<li class='page-item'><a class='page-link' href='javascript:gotoPage("+pageBean.getMaxPage()+")'>尾页</a></li>");
        }

        /*
         * 给分页条添加与后台交互的js代码
         */
        sb.append("<script type='text/javascript'>");
        sb.append("		function gotoPage(page) {");
        sb.append("			document.getElementById('pageBeanForm').page.value = page;");
        sb.append("			document.getElementById('pageBeanForm').submit();");
        sb.append("		}");
        sb.append("		function skipPage() {");
        sb.append("			var page = document.getElementById('skipPage').value;");
        sb.append("			if(!page || isNaN(page) || parseInt(page)<1 || parseInt(page)>"+pageBean.getMaxPage()+"){");
        sb.append("				alert('请输入1~N的数字');");
        sb.append("				return;");
        sb.append("			}");
        sb.append("			gotoPage(page);");
        sb.append("		}");
        sb.append("</script>");
        return sb.toString();
    }
}
