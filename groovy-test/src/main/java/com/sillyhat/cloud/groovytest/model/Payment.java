package com.sillyhat.cloud.groovytest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    private Long id;

    private Long totalPrice;

    private Long discountPrice;

}
