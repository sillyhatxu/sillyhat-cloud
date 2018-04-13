package com.sillyhat.cloud.common.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseElasticsearchFilter<T> {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int DEFAULT_TIMEOUT_SECOND = 60;
    private static final int DEFAULT_MAX_RESULT_COUNT = 200;

    private String index;

    private String type;

    private Class<T> clazz;

    private List<T> dataList;

    private String primaryKey;

    private QueryBuilder query;

    @Builder.Default
    private int page = DEFAULT_PAGE;

    @Builder.Default
    private int maxResultCount = DEFAULT_MAX_RESULT_COUNT;

    @Builder.Default
    private int pageSize = DEFAULT_PAGE_SIZE;

    @Builder.Default
    private int timeoutSecond = DEFAULT_TIMEOUT_SECOND;

    private List<ElasticSearchSortDTO> sortList;

    public int getFrom(){
        return page * pageSize;
    }

    public void sort(String name, SortOrder order){
        if(sortList == null){
            sortList = new ArrayList<>();
        }
        sortList.add(ElasticSearchSortDTO.builder().name(name).order(order).build());
    }

}
