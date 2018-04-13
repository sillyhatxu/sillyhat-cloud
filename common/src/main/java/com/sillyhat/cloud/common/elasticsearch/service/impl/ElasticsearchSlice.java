package com.sillyhat.cloud.common.elasticsearch.service.impl;

import java.util.List;

public interface ElasticsearchSlice<T> extends Iterable<T> {

    int getPage();

    int getPageSize();

    int getTotalPages();

    long getTotalElements();

    boolean hasContent();

    boolean isFirst();

    boolean isLast();

    List<T> getContent();

}
