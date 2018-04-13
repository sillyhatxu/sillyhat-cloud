package com.sillyhat.cloud.common.elasticsearch.factory;

import com.sillyhat.cloud.common.constants.Constants;
import com.sillyhat.cloud.common.elasticsearch.model.ElasticsearchProductFilter;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.List;

@Slf4j
public class ElasticsearchFactory {

    public static <T> ElasticsearchProductFilter<T> buildDejaProductsGetByPrimaryKeyFilter(String primaryKey, Class<T> clazz){
        return ElasticsearchProductFilter.<T>builder().primaryKey(primaryKey).clazz(clazz).build();
    }

    public static <T> ElasticsearchProductFilter<T> buildDejaProductsQueryByProductIdList(List<Long> productIdList, Class<T> clazz){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_RECOMMEND, true));
        boolQueryBuilder.must(QueryBuilders.termsQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_PRODUCT_ID, productIdList));
        return ElasticsearchProductFilter.<T>builder().query(boolQueryBuilder).clazz(clazz).pageSize(productIdList.size()).build();
    }

    public static <T> ElasticsearchProductFilter<T> buildDejaProductsQueryByOcbTrueAndIsPurchasableTrueAndProductIdList(List<Long> productIdList, Class<T> clazz){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_RECOMMEND, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_OCB, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_PURCHASABLE, true));
        boolQueryBuilder.must(QueryBuilders.termsQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_PRODUCT_ID, productIdList));
        return ElasticsearchProductFilter.<T>builder().query(boolQueryBuilder).clazz(clazz).pageSize(productIdList.size()).build();
    }

    public static <T> ElasticsearchProductFilter<T> buildDejaProductsQueryByOcbFalseAndProductIdList(List<Long> productIdList, Class<T> clazz){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_RECOMMEND, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_OCB, false));
        boolQueryBuilder.must(QueryBuilders.termsQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_PRODUCT_ID, productIdList));
        return ElasticsearchProductFilter.<T>builder().query(boolQueryBuilder).clazz(clazz).pageSize(productIdList.size()).build();
    }

    public static <T> ElasticsearchProductFilter<T> buildDejaProductsQueryByOcbTrueAndIsPurchasableTrueAndSubcategoryAndColorPattern(Integer subcategory,Integer colorAndPattern, Class<T> clazz){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_RECOMMEND, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_OCB, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_PURCHASABLE, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_COLOR_AND_PATTERN, colorAndPattern));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_SUBCATEGORY, subcategory));
        return ElasticsearchProductFilter.<T>builder().query(boolQueryBuilder).clazz(clazz).pageSize(Constants.DEFAULT_ELASTICSEARCH_LIST_SIZE).build();
    }

    public static <T> ElasticsearchProductFilter<T> buildDejaProductsQueryByOcbTrueAndIsPurchasableTrueAndIsDiscountTrueAndSubcategoryAndColorPattern(Integer subcategory,Integer colorAndPattern, Class<T> clazz){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_RECOMMEND, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_OCB, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_PURCHASABLE, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_DISCOUNT, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_SUBCATEGORY, subcategory));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_COLOR_AND_PATTERN, colorAndPattern));
        return ElasticsearchProductFilter.<T>builder().query(boolQueryBuilder).clazz(clazz).pageSize(Constants.DEFAULT_ELASTICSEARCH_LIST_SIZE).build();
    }

    public static <T> ElasticsearchProductFilter<T> buildDejaProductsQueryByOcbTrueAndIsPurchasableTrueAndIsDiscountTrueAndColorPatternAndBrand(Integer colorAndPattern,String brandId,Class<T> clazz){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_RECOMMEND, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_OCB, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_PURCHASABLE, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_DISCOUNT, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_COLOR_AND_PATTERN, colorAndPattern));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_BRAND, brandId));
        return ElasticsearchProductFilter.<T>builder().query(boolQueryBuilder).clazz(clazz).pageSize(Constants.DEFAULT_ELASTICSEARCH_LIST_SIZE).build();
    }

    public static <T> ElasticsearchProductFilter<T> buildDejaProductsQueryByOcbTrueAndIsPurchasableTrueAndIsNewArrivalTrueAndColorPatternAndBrand(Integer colorAndPattern,String brandId,Class<T> clazz){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_RECOMMEND, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_OCB, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_PURCHASABLE, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_NEW_ARRIVAL, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_BRAND, brandId));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_COLOR_AND_PATTERN, colorAndPattern));
        return ElasticsearchProductFilter.<T>builder().query(boolQueryBuilder).clazz(clazz).pageSize(Constants.DEFAULT_ELASTICSEARCH_LIST_SIZE).build();
    }

    public static <T> ElasticsearchProductFilter<T> buildDejaProductsQueryByOcbTrueAndIsPurchasableTrueAndIsNewArrivalTrueAndSubcategoryAndColorPattern(Integer subcategory,Integer colorAndPattern,Class<T> clazz){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_RECOMMEND, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_OCB, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_PURCHASABLE, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_NEW_ARRIVAL, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_SUBCATEGORY, subcategory));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_COLOR_AND_PATTERN, colorAndPattern));
        return ElasticsearchProductFilter.<T>builder().query(boolQueryBuilder).clazz(clazz).pageSize(Constants.DEFAULT_ELASTICSEARCH_LIST_SIZE).build();
    }

    public static <T> ElasticsearchProductFilter<T> buildDejaProductsQueryByOcbFalseAndIsNewArrivalTrueOrIsDiscountTrueAndColorPatternAndBrand(Integer colorAndPattern,String brandId,Class<T> clazz){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_RECOMMEND, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_OCB, false));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_BRAND, brandId));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_COLOR_AND_PATTERN, colorAndPattern));

        BoolQueryBuilder newArrivalOrDiscountBoolQueryBuilder = QueryBuilders.boolQuery();
        newArrivalOrDiscountBoolQueryBuilder.should(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_NEW_ARRIVAL, true));
        newArrivalOrDiscountBoolQueryBuilder.should(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_DISCOUNT, true));
        boolQueryBuilder.must(newArrivalOrDiscountBoolQueryBuilder);
        return ElasticsearchProductFilter.<T>builder().query(boolQueryBuilder).clazz(clazz).pageSize(Constants.DEFAULT_ELASTICSEARCH_LIST_SIZE).build();
    }

    public static <T> ElasticsearchProductFilter<T> buildDejaProductsQueryByOcbFalseAndIsNewArrivalTrueOrIsDiscountTrueAndSubcategoryAndColorPattern(Integer subcategory,Integer colorAndPattern,Class<T> clazz){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_RECOMMEND, true));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_OCB, false));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_SUBCATEGORY, subcategory));
        boolQueryBuilder.must(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_COLOR_AND_PATTERN, colorAndPattern));

        BoolQueryBuilder newArrivalOrDiscountBoolQueryBuilder = QueryBuilders.boolQuery();
        newArrivalOrDiscountBoolQueryBuilder.should(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_NEW_ARRIVAL, true));
        newArrivalOrDiscountBoolQueryBuilder.should(QueryBuilders.termQuery(Constants.ELASTIC_SEARCH_DEJA_PRODUCTS_FIELD_IS_DISCOUNT, true));
        boolQueryBuilder.must(newArrivalOrDiscountBoolQueryBuilder);
        return ElasticsearchProductFilter.<T>builder().query(boolQueryBuilder).clazz(clazz).pageSize(Constants.DEFAULT_ELASTICSEARCH_LIST_SIZE).build();
    }

}