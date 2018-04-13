package com.sillyhat.cloud.common.elasticsearch.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ElasticsearchField {

    String DEFAULT_VALUE = "";

    boolean DEFAULT_PRIMARY_KEY = false;

    String value() default DEFAULT_VALUE;

    boolean primaryKey() default DEFAULT_PRIMARY_KEY;

}
