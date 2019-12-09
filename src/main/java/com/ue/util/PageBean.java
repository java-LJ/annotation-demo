package com.ue.util;

/**
 * @author LJ
 * @Date 2019年02月22日
 * @Time 17:03
 */

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;

/**
 * 分页工具类
 */
public class PageBean implements Serializable {

    private int page = 1;// 页码

    private int rows = 3;// 页大小

    private int total = 0;// 总记录数

    private boolean pagination = true;// 是否分页

    //保存上次查询的参数
    private Map<String, String[]> paramMap;
    //保存上次查询的url
    private String url;

    public void setRequest(HttpServletRequest request) {
        String page = request.getParameter("page");
        String rows = request.getParameter("offset");
        String pagination = request.getParameter("pagination");
        this.setPage(page);
        this.setRows(rows);
        this.setPagination(pagination);
        this.setUrl(request.getRequestURL().toString());
        this.setParamMap(request.getParameterMap());
    }

    public PageBean() {
        super();
    }

    public Map<String, String[]> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String[]> paramMap) {
        this.paramMap = paramMap;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPage(String page) {
        if(StringUtils.isNotBlank(page)) {
            this.page = Integer.parseInt(page);
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(String rows) {
        if(StringUtils.isNotBlank(rows)) {
            this.rows = Integer.parseInt(rows);
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTotal(String total) {
        if(StringUtils.isNotBlank(total)) {
            this.total = Integer.parseInt(total);
        }
    }

    public boolean isPagination() {
        return pagination;
    }

    public void setPagination(boolean pagination) {
        this.pagination = pagination;
    }

    public void setPagination(String pagination) {
        if(StringUtils.isNotBlank(pagination) && "false".equals(pagination)) {
            this.pagination = Boolean.parseBoolean(pagination);
        }
    }

    /**
     * 最大页
     * @return
     */
    public int getMaxPage() {
        int max = this.total/this.rows;
        if(this.total % this.rows !=0) {
            max ++ ;
        }
        return max;
    }

    /**
     * 下一页
     * @return
     */
    public int getNextPage() {
        int nextPage = this.page + 1;
        if(nextPage > this.getMaxPage()) {
            nextPage = this.getMaxPage();
        }
        return nextPage;
    }

    /**
     * 上一页
     * @return
     */
    public int getPreviousPage() {
        int previousPage = this.page -1;
        if(previousPage < 1) {
            previousPage = 1;
        }
        return previousPage;
    }


    /**
     * 获得起始记录的下标
     * @return
     */
    public int getStartIndex() {
        return (this.page - 1) * this.rows;
    }

    @Override
    public String toString() {
        return "PageBean [page=" + page + ", rows=" + rows + ", total=" + total + ", pagination=" + pagination + "]";
    }
}
