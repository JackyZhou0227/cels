package com.kclm.cels.entity;

import java.util.*;

public class PageBean {

    private List<Vocabulary> dataList;
    private int pageSize;
    private int first;
    private int last;
    private int previous;
    private int next;
    private int current;//当前页
    private int pages;//总页数
    private int total;//总记录数

    private String style;

    public PageBean(Set<Vocabulary> originalData, int pageSize) {

        this.dataList = new ArrayList<>(originalData);
        this.pageSize = pageSize;
        if (pageSize > 0) {
            this.total = this.dataList.size();
            if (this.total % this.pageSize == 0) {
                this.pages = this.total / this.pageSize;
            } else {
                this.pages = this.total / this.pageSize + 1;
            }
        }

    }

    public List<Vocabulary> getPageData(int pageNum) {
        current = pageNum;
        int startIndex = (pageNum - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, dataList.size());
        return new ArrayList<>(dataList.subList(startIndex, endIndex));
    }

    public List<Vocabulary> getDataList() {
        return dataList;
    }

    public void setDataList(List<Vocabulary> dataList) {
        this.dataList = dataList;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getPrevious() {
        return previous;
    }

    public void setPrevious(int previous) {
        this.previous = previous;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
