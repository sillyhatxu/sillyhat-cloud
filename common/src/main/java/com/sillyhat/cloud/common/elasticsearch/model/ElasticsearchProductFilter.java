package com.sillyhat.cloud.common.elasticsearch.model;

import com.sillyhat.cloud.common.constants.Constants;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.elasticsearch.index.query.QueryBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ElasticsearchProductFilter<T> extends BaseElasticsearchFilter<T> {

//    private String index;
//
//    private String type;


//    public ElasticSearchDejaProductsFilter(String index, String type) {
//        this.index = index;
//        this.type = type;
//    }
//
//    @Builder
//    public ElasticSearchDejaProductsFilter(String index, String type, Class<T> clazz, List<T> dataList, String primaryKey, QueryBuilder query, int page, int maxResultCount, int pageSize, int timeoutSecond, List<ElasticSearchSortDTO> sortList, String index1, String type1) {
//        super(index, type, clazz, dataList, primaryKey, query, page, maxResultCount, pageSize, timeoutSecond, sortList);
//        this.index = index1;
//        this.type = type1;
//    }

    @Builder
    public ElasticsearchProductFilter(String index, String type, Class<T> clazz, List<T> dataList, String primaryKey, QueryBuilder query, int page, int maxResultCount, int pageSize, int timeoutSecond, List<ElasticSearchSortDTO> sortList) {
        super(Constants.ELASTIC_SEARCH_INDEX_DEJA_PRODUCTS, Constants.ELASTIC_SEARCH_TYPE_TAGS, clazz, dataList, primaryKey, query, page, maxResultCount, pageSize, timeoutSecond, sortList);
    }
}
