package com.sillyhat.cloud.common.elasticsearch.domain;

import com.sillyhat.cloud.common.elasticsearch.annotation.ElasticsearchField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElasticsearchProductDTO {

    @ElasticsearchField(primaryKey = true)
    private String id;

    @ElasticsearchField(value = "product_id")
    private Long productId;

    @ElasticsearchField(value = "product_code")
    private String productCode;

    @ElasticsearchField(value = "product_group_id")
    private String productGroupId;

    @ElasticsearchField(value = "category")
    private Integer category;

    @ElasticsearchField(value = "is_purchasable")
    private boolean isPurchasable;

    @ElasticsearchField(value = "is_recommend")
    private boolean isRecommend;

    @ElasticsearchField(value = "update_time")
    private Date updateTime;
}
