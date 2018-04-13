package com.sillyhat.elasticsearch;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TestElasticsearch {

    private static RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("172.16.99.130",9200,"http")));

    public static void main(String[] args) throws Exception {
        log.info("---------------------");
        insertElasticSearch();
//        delete();
//        ClearScrollRequest request = new ClearScrollRequest();
//        request.addScrollId(scrollId);
    }

    private static void insertElasticSearch() {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("product_id", 5404175L);
        jsonMap.put("shop_item_code", "SL7PFSBDTB");
        jsonMap.put("category", 18);
        jsonMap.put("color", "Black");
        jsonMap.put("currency", "S$");
        jsonMap.put("current_price", 4850);
        jsonMap.put("is_purchasable", true);
        jsonMap.put("last_modified_date", new Date());
        IndexRequest indexRequest = new IndexRequest("deja_products", "tags", "5404175");
        indexRequest.source(jsonMap);
        indexRequest.opType(DocWriteRequest.OpType.CREATE);
//        indexRequest.opType(DocWriteRequest.OpType.UPDATE);
        try {
            IndexResponse indexResponse = client.index(indexRequest);
            String index = indexResponse.getIndex();
            String type = indexResponse.getType();
            String id = indexResponse.getId();
            long version = indexResponse.getVersion();
            log.info("IndexResponse -> index : {}; type : {}; id : {}; version : {}", index, type, id, version);
//            log.info("IndexResponse -> requestId : {}; index : {}; type : {}; id : {}; version : {}",requestId,index,type,id,version);
            if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
                log.info("Created");
            } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                log.info("Update");
            }
        } catch (IOException e) {
            log.error("insert error", e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
