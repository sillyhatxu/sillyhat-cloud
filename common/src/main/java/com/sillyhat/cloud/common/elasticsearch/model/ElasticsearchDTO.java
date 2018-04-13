package com.sillyhat.cloud.common.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElasticsearchDTO {

    private String hostname;

    private int port;

    private String scheme;

    private String index;

    private String type;

//    @Override
//    public int hashCode() {
//        int hashResult = 1;
//        hashResult += Integer.valueOf(port).hashCode();
//        hashResult += (hostname == null) ? 0 : hostname.hashCode();
//        hashResult += (scheme == null) ? 0 : scheme.hashCode();
//        hashResult += (index == null) ? 0 : index.hashCode();
//        hashResult += (type == null) ? 0 : type.hashCode();
//        return hashResult;
//    }
}
