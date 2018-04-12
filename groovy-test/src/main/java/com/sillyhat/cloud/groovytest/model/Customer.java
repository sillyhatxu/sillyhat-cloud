package com.sillyhat.cloud.groovytest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Long id;

    private String name;

    private List<Address> addressList;

    public void addAddress(Address entity){
        if(addressList == null){
            addressList = new ArrayList<>();
        }
        if(addressList.size() == 0){
            entity.setDefault(true);
        }
        if(entity.isDefault()){
            //设置新增项为默认，则修改曾用默认地址为非默认地址
            for (Address address : addressList) {
                address.setDefault(false);
            }
        }
        addressList.add(entity);
    };
}
