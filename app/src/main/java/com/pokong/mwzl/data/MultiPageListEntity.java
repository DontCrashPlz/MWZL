package com.pokong.mwzl.data;

import java.util.ArrayList;

/**
 * Created on 2018/11/23 16:59
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class MultiPageListEntity <T> {
    private int totalRow;
    private int pageNumber;
    private boolean last;
    private int startRow;
    private int currentPageCount;
    private boolean hasNextPage;
    private int totalPage;
    private int pageSize;
    private int endRow;
    private ArrayList<T> list;
    private String uri;
    private String orderMode;
    private String orderColunm;
    private boolean hasPreviousPage;
    private boolean first;

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getCurrentPageCount() {
        return currentPageCount;
    }

    public void setCurrentPageCount(int currentPageCount) {
        this.currentPageCount = currentPageCount;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getOrderMode() {
        return orderMode;
    }

    public void setOrderMode(String orderMode) {
        this.orderMode = orderMode;
    }

    public String getOrderColunm() {
        return orderColunm;
    }

    public void setOrderColunm(String orderColunm) {
        this.orderColunm = orderColunm;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    @Override
    public String toString() {
        return "MultiPageListEntity{" +
                "totalRow=" + totalRow +
                ", pageNumber=" + pageNumber +
                ", last=" + last +
                ", startRow=" + startRow +
                ", currentPageCount=" + currentPageCount +
                ", hasNextPage=" + hasNextPage +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                ", endRow=" + endRow +
                ", list=" + list +
                ", uri='" + uri + '\'' +
                ", orderMode='" + orderMode + '\'' +
                ", orderColunm='" + orderColunm + '\'' +
                ", hasPreviousPage=" + hasPreviousPage +
                ", first=" + first +
                '}';
    }
}
//        "totalRow":76,
//        "pageNumber":1,
//        "last":false,
//        "startRow":1,
//        "currentPageCount":1,
//        "hasNextPage":true,
//        "totalPage":76,
//        "pageSize":1,
//        "endRow":1,
//        "list":Array[1],
//        "uri":"/api/store/order",
//        "orderMode":"desc",
//        "extData":null,//补充信息，暂不解析
//        "orderColunm":"o.addTime",
//        "hasPreviousPage":false,
//        "queryParam":{//查询参数，暂不解析
//        "storeId":2
//        },
//        "first":true
