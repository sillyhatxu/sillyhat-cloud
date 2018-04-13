package com.sillyhat.cloud.common.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.search.sort.SortOrder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElasticSearchSortDTO {

    private String name;

    private SortOrder order;


}