package com.sillyhat.cloud.common.elasticsearch.client;

import com.sillyhat.cloud.common.elasticsearch.annotation.ElasticsearchField;
import com.sillyhat.cloud.common.elasticsearch.exception.SillyHatElasticsearchException;
import com.sillyhat.cloud.common.elasticsearch.model.BaseElasticsearchFilter;
import com.sillyhat.cloud.common.elasticsearch.service.ElasticsearchPage;
import com.sillyhat.cloud.common.elasticsearch.service.impl.ElasticsearchPageImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ElasticsearchClient {

    private static RestHighLevelClient client;

    private ElasticsearchClient(){

    }

    public static ElasticsearchClient build(String ip, int port, String scheme){
        client = new RestHighLevelClient(RestClient.builder(new HttpHost(ip,port,scheme)));
        return new ElasticsearchClient();
    }

    public <T> ElasticsearchPage<T> queryPage(BaseElasticsearchFilter<T> elasticSearchFilter) throws SillyHatElasticsearchException {
        List<T> resultList;
        long totalElements;
        try {
            SearchHits searchHits = getResult(elasticSearchFilter);
            resultList = getResultList(searchHits,elasticSearchFilter.getClazz());
            totalElements = searchHits.getTotalHits();
            log.debug("search total record: {}",totalElements);
        }catch (Exception e){
            log.error("Search data exception by ElasticSearch",e);
            throw new SillyHatElasticsearchException("Search data exception by ElasticSearch",e);
        }
        return new ElasticsearchPageImpl<>(resultList,elasticSearchFilter.getPage(),elasticSearchFilter.getPageSize(),totalElements);
    }

    public <T> List<T> queryList(BaseElasticsearchFilter<T> elasticSearchFilter) throws SillyHatElasticsearchException {
        List<T> resultList;
        try {
            SearchHits searchHits = getResult(elasticSearchFilter);
            resultList = getResultList(searchHits,elasticSearchFilter.getClazz());
        }catch (Exception e){
            log.error("Search data exception by ElasticSearch",e);
            throw new SillyHatElasticsearchException("Search data exception by ElasticSearch",e);
        }
        return resultList;
    }

    public <T> T getByPrimaryKey(BaseElasticsearchFilter<T> elasticSearchFilter) throws SillyHatElasticsearchException {
        GetRequest request = new GetRequest(elasticSearchFilter.getIndex(),elasticSearchFilter.getType(),elasticSearchFilter.getPrimaryKey());
        try {
            GetResponse getResponse = client.get(request);
            Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
            T t = elasticSearchFilter.getClazz().newInstance();
            return setObjectValue(t,elasticSearchFilter.getClazz(),sourceAsMap,getResponse.getId());
        } catch (Exception e) {
            log.error("Search elasticsearch by primaryKey error",e);
            throw new SillyHatElasticsearchException("Search elasticsearch by primaryKey error",e);
        }
    }

    public <T> void refreshData(BaseElasticsearchFilter<T> elasticSearchFilter) throws SillyHatElasticsearchException {
        try {
            elasticSearchFilter.getDataList().forEach(t -> {
                try {
                    saveOrUpdate(elasticSearchFilter,t);
                } catch (SillyHatElasticsearchException e) {
                    log.error("ElasticSearch save or update error",e);
                }
            });
        }catch (Exception e){
            log.error("Refresh elastic search data exception",e);
            throw new SillyHatElasticsearchException("Refresh elastic search data exception",e);
        }
    }
    public <T> void deleteIndex(String index) throws SillyHatElasticsearchException {
        try {
            DeleteIndexRequest request = new DeleteIndexRequest(index);
            client.indices().deleteIndex(request);
        }catch (Exception e){
            log.error("Delete elasticsearch index error.",e);
            throw new SillyHatElasticsearchException("Delete elasticsearch index error.",e);
        }
    }

    public <T> void saveAllData(BaseElasticsearchFilter<T> elasticSearchFilter) throws SillyHatElasticsearchException {
        try {
            BulkRequest request = new BulkRequest();
            elasticSearchFilter.getDataList().forEach(t -> {
                try {
                    Field[] fields = elasticSearchFilter.getClazz().getDeclaredFields();
                    for(Field field : fields){
                        if(field.isAnnotationPresent(ElasticsearchField.class)){
                            if(field.getAnnotation(ElasticsearchField.class).primaryKey()){
                                field.setAccessible(true);
                                String primaryKey = (String) field.get(t);
                                request.add(getIndexRequest(elasticSearchFilter,primaryKey,t));
                            }
                        }
                    }
                } catch (Exception e) {
                    log.error("ElasticSearch saveAllData error",e);
                }
            });
            client.bulk(request);
        } catch (Exception e) {
            log.error("ElasticSearch save or update error",e);
            throw new SillyHatElasticsearchException("ElasticSearch save or update error",e);
        }
    }

    private <T> void saveOrUpdate(BaseElasticsearchFilter<T> elasticSearchFilter, T t) throws SillyHatElasticsearchException {
        try {
            BulkRequest request = new BulkRequest();
            Field[] fields = elasticSearchFilter.getClazz().getDeclaredFields();
            for(Field field : fields){
                if(field.isAnnotationPresent(ElasticsearchField.class)){
                    if(field.getAnnotation(ElasticsearchField.class).primaryKey()){
                        field.setAccessible(true);
                        String primaryKey = (String) field.get(t);
                        if(dataExist(elasticSearchFilter,primaryKey)){
                            request.add(getUpdateRequest(elasticSearchFilter,primaryKey,t));
                        }else{
                            request.add(getIndexRequest(elasticSearchFilter,primaryKey,t));
                        }
                    }
                }
            }
            client.bulk(request);
        } catch (Exception e) {
            log.error("ElasticSearch save or update error",e);
            throw new SillyHatElasticsearchException("ElasticSearch save or update error",e);
        }
    }

    private <T> boolean dataExist(BaseElasticsearchFilter<T> elasticSearchFilter, String primaryKey) throws SillyHatElasticsearchException {
        GetRequest request = new GetRequest(elasticSearchFilter.getIndex(),elasticSearchFilter.getType(),primaryKey);
        try {
            GetResponse getResponse = client.get(request);
            return getResponse.isExists();
        } catch (IOException e) {
            log.error("Get data by ElasticSearch error",e);
            throw new SillyHatElasticsearchException("Get data by ElasticSearch error",e);
        }
    }

    private <T> IndexRequest getIndexRequest(BaseElasticsearchFilter<T> elasticSearchFilter, String primaryKey, T t) throws SillyHatElasticsearchException {
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject();
            {
                Field[] fields = elasticSearchFilter.getClazz().getDeclaredFields();
                for(Field field : fields){
                    if(hasElasticSearchAnnotationValue(field)) {
                        field.setAccessible(true);
                        builder.field(field.getAnnotation(ElasticsearchField.class).value(), fieldValue(field,t));
                    }
                }
            }
            builder.endObject();
            return new IndexRequest(elasticSearchFilter.getIndex(),elasticSearchFilter.getType(),primaryKey).source(builder);
        }catch (Exception e){
            log.error("Save data exception by ElasticSearch",e);
            throw new SillyHatElasticsearchException("Save data exception by ElasticSearch",e);
        }
    }

    private <T> UpdateRequest getUpdateRequest(BaseElasticsearchFilter<T> elasticSearchFilter, String primaryKey, T t) throws SillyHatElasticsearchException {
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject();
            {
                Field[] fields = elasticSearchFilter.getClazz().getDeclaredFields();
                for(Field field : fields){
                    if(hasElasticSearchAnnotationValue(field)) {
                        field.setAccessible(true);
                        builder.field(field.getAnnotation(ElasticsearchField.class).value(), fieldValue(field,t));
                    }
                }
            }
            builder.endObject();
            return new UpdateRequest(elasticSearchFilter.getIndex(),elasticSearchFilter.getType(),primaryKey).doc(builder);
        }catch (Exception e){
            log.error("Update data exception by ElasticSearch",e);
            throw new SillyHatElasticsearchException("Update data exception by ElasticSearch",e);
        }
    }

    private <T> Object fieldValue(Field field,T t) throws IllegalAccessException {
        if(Date.class.isAssignableFrom(field.getType())){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime((Date) field.get(t));
            return calendar.getTimeInMillis();
        }else{
            return field.get(t);
        }
    }

    private <T> void setValue(T t,Field field,Map<String, Object> resultMap) throws IllegalAccessException {
        if(hasElasticSearchAnnotationValue(field)){
            field.setAccessible(true);
            if(Integer.class.isAssignableFrom(field.getType()) || int.class.isAssignableFrom(field.getType())) {
                field.set(t,isEmpty(resultMap,field.getAnnotation(ElasticsearchField.class).value()) ? 0 : Integer.parseInt(resultMap.get(field.getAnnotation(ElasticsearchField.class).value()).toString()));
            } else if(Boolean.class.isAssignableFrom(field.getType()) || boolean.class.isAssignableFrom(field.getType())){
                field.set(t, !isEmpty(resultMap, field.getAnnotation(ElasticsearchField.class).value()) && Boolean.parseBoolean(resultMap.get(field.getAnnotation(ElasticsearchField.class).value()).toString()));
            }else if(Long.class.isAssignableFrom(field.getType()) || long.class.isAssignableFrom(field.getType())){
                field.set(t,isEmpty(resultMap,field.getAnnotation(ElasticsearchField.class).value()) ? 0L : Long.parseLong(resultMap.get(field.getAnnotation(ElasticsearchField.class).value()).toString()));
            }else if(Double.class.isAssignableFrom(field.getType()) || double.class.isAssignableFrom(field.getType())){
                field.set(t,isEmpty(resultMap,field.getAnnotation(ElasticsearchField.class).value()) ? 0 : Double.parseDouble(resultMap.get(field.getAnnotation(ElasticsearchField.class).value()).toString()));
            }else if(Date.class.isAssignableFrom(field.getType())){
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(Long.parseLong(resultMap.get(field.getAnnotation(ElasticsearchField.class).value()).toString()));
                field.set(t,isEmpty(resultMap,field.getAnnotation(ElasticsearchField.class).value()) ? null : calendar.getTime());
            }else{
                field.set(t,isEmpty(resultMap,field.getAnnotation(ElasticsearchField.class).value()) ? null : resultMap.get(field.getAnnotation(ElasticsearchField.class).value()).toString());
            }
        }
    }

    private boolean isEmpty(Map<String, Object> resultMap,String key){
        return resultMap.get(key) == null;
    }

    private boolean hasElasticSearchAnnotationValue(Field field){
        return field.isAnnotationPresent(ElasticsearchField.class) && !field.getAnnotation(ElasticsearchField.class).primaryKey();
    }

    private <T> SearchHits getResult(BaseElasticsearchFilter<T> elasticSearchFilter) throws IOException {
        SearchRequest searchRequest = new SearchRequest(elasticSearchFilter.getIndex());
        searchRequest.types(elasticSearchFilter.getType());
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        if(elasticSearchFilter.getQuery() != null){
            sourceBuilder.query(elasticSearchFilter.getQuery());
        }
        sourceBuilder.from(elasticSearchFilter.getFrom());
        sourceBuilder.size(elasticSearchFilter.getPageSize());
        sourceBuilder.timeout(new TimeValue(elasticSearchFilter.getTimeoutSecond(), TimeUnit.SECONDS));
        if(elasticSearchFilter.getSortList() != null && !elasticSearchFilter.getSortList().isEmpty()){
            elasticSearchFilter.getSortList().forEach(sortDTO -> sourceBuilder.sort(sortDTO.getName(), sortDTO.getOrder()));
        }
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest);
        return searchResponse.getHits();
    }

    private <T> List<T> getResultList(SearchHits searchHits,Class<T> clazz) throws IllegalAccessException, InstantiationException {
        List<T> resultList = new ArrayList<>();
        for (SearchHit hit : searchHits.getHits()) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            log.debug("result map ：{}",hit.getSourceAsMap());
            T t = clazz.newInstance();
            resultList.add(setObjectValue(t,clazz,sourceAsMap,hit.getId()));
        }
        return resultList;
    }

    private <T> T setObjectValue(T t,Class<T> clazz,Map<String, Object> sourceAsMap,String primaryKey) throws IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            setValue(t,field,sourceAsMap);
            if(field.getAnnotation(ElasticsearchField.class).primaryKey()){
                field.setAccessible(true);
                field.set(t,primaryKey);
            }
        }
        return t;
    }

}
