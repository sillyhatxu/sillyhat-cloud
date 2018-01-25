package com.sillyhat.cloud.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerGroup {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    @OneToMany(fetch= FetchType.LAZY,mappedBy = "customerGroup", cascade = CascadeType.ALL,orphanRemoval=true)
    @JsonManagedReference
    @JsonProperty("relation_customer_and_customer_group_list")
    private List<RelationCustomerAndCustomerGroup> relationCustomerAndCustomerGroupList;

    @JsonIgnore
    public void addRelationCustomerAndCustomerGroup(RelationCustomerAndCustomerGroup relationCustomerAndCustomerGroup){
        relationCustomerAndCustomerGroupList.add(relationCustomerAndCustomerGroup);
    };

    @JsonIgnore
    public void clearRelationCustomerAndCustomerGroup(){
        relationCustomerAndCustomerGroupList.clear();
    };

}
