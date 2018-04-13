package com.sillyhat.cloud.common.elasticsearch.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

abstract class ElasticsearchChunk<T> implements ElasticsearchSlice<T>, Serializable {

    private static final long serialVersionUID = -7932100105124328335L;

    private final List<T> content = new ArrayList<T>();

    private final long totalElements;

    private final int page;

    private final int pageSize;

    ElasticsearchChunk(List<T> content, int page, int pageSize, long totalElements){
        this.content.addAll(content);
        this.page = page;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
    }

    @Override
    public List<T> getContent() {
        return content;
    }

    @Override
    public long getTotalElements() {
        return totalElements;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getTotalPages() {
        return (int)(getTotalElements() + getPageSize() - 1)/getPageSize();
    }

    @Override
    public boolean hasContent() {
        return getContent() != null && !getContent().isEmpty();
    }

    @Override
    public boolean isFirst() {
        return getPage() == 0;
    }

    @Override
    public boolean isLast() {
        return getPage() == getTotalPages();
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public Iterator<T> iterator() {
        return content.iterator();
    }
}