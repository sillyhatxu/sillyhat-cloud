package com.sillyhat.cloud.customer.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="customer-properties")
public class CustomerProperties {

    private String onemapUrl;


}
