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
public class Project {

    private Long id;

    private String name;

    private Integer quantity;

    private Long everyPrice;
}
