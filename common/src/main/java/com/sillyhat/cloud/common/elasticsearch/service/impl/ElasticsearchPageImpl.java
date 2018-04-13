package com.sillyhat.cloud.common.elasticsearch.service.impl;

import com.sillyhat.cloud.common.elasticsearch.service.ElasticsearchPage;

import java.util.List;

public class ElasticsearchPageImpl<T> extends ElasticsearchChunk<T> implements ElasticsearchPage<T> {

    private static final long serialVersionUID = 8685523263173018521L;

    public ElasticsearchPageImpl(List<T> content, int page, int pageSize, long totalElements){
        super(content,page,pageSize,totalElements);
    }

}