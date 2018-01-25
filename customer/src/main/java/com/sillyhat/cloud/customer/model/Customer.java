package com.sillyhat.cloud.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sillyhat.cloud.customer.config.NumberUtils;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude={"addressList","cardList","relationCustomerAndCustomerGroupList"})
@ToString(exclude={"addressList","cardList","relationCustomerAndCustomerGroupList"})
public class Customer{
    @Id
    @JsonProperty("customer_id")
    private Long id;

    @JsonProperty("stripe_customer_id")
    private String stripeCustomerId;

    private String email;

    @OrderBy("last_modified_date DESC")
    @OneToMany(fetch=FetchType.LAZY,mappedBy = "customer", cascade = CascadeType.ALL,orphanRemoval=true)
    @JsonManagedReference
    @JsonProperty("address_list")
    private List<Address> addressList;

    @OrderBy("created_date DESC")
    @OneToMany(fetch=FetchType.LAZY,mappedBy = "customer", cascade = CascadeType.ALL,orphanRemoval=true)
    @JsonManagedReference
    @JsonProperty("card_list")
    private List<Card> cardList;

    @OneToMany(fetch=FetchType.LAZY,mappedBy = "customer", cascade = CascadeType.ALL,orphanRemoval=true)
    @JsonManagedReference
    @JsonProperty("relation_customer_and_customer_group_list")
    private List<RelationCustomerAndCustomerGroup> relationCustomerAndCustomerGroupList;

    @JsonIgnore
    public void addCustomerGroup(CustomerGroup customerGroup){
        if(relationCustomerAndCustomerGroupList == null){
            relationCustomerAndCustomerGroupList = new ArrayList<>();
        }
        relationCustomerAndCustomerGroupList.add(RelationCustomerAndCustomerGroup.builder().customer(this).customerGroup(customerGroup).build());
    }
    /*************** Address begin ***************/

    @JsonIgnore
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
        if(entity.isDefault()){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.SECOND,10);//TODO add default card,it(new card) will be show at the first.I'm sorry,English too bad.
        }
        entity.setCustomer(this);
        addressList.add(entity);
    };

    @JsonIgnore
    public void updateAddress(Address entity){
        if(addressList != null){
            if(entity.isDefault()){
                //update card is default
                for (Address address : addressList) {
                    address.setDefault(false);
                }
            }
            for (Address address : addressList) {
                if(NumberUtils.isEqual(entity.getId(),address.getId())){
                    address.setFirstName(entity.getFirstName());
                    address.setLastName(entity.getLastName());
                    address.setPostalCode(entity.getPostalCode());
                    address.setFloorUnitNumber(entity.getFloorUnitNumber());
                    address.setPhone(entity.getPhone());
                    address.setBlkNo(entity.getBlkNo());
                    address.setRoadName(entity.getRoadName());
                    address.setCountry(entity.getCountry());
                    address.setDefault(entity.isDefault());
                    address.setSearchval(entity.getSearchval());
                    address.setBuilding(entity.getBuilding());
                    address.setAddress(entity.getAddress());
                    address.setFormatAddress(entity.getFormatAddress());
                }
            }

        }
    };

    @JsonIgnore
    public void deleteAddressById(Long addressId){
        if(addressList != null){
            Optional<Address> addressOptional = addressList.stream().filter(address -> NumberUtils.isEqual(addressId,address.getId())).findFirst();
            Address deleteAddress = addressOptional.get();
            addressList.remove(deleteAddress);
            if(deleteAddress.isDefault() && addressList.size() > 0){
                Address address = addressList.get(0);
                address.setDefault(true);
            }
        }
    };

    @JsonIgnore
    public Address getAddressById(Long addressId){
        if(addressList != null){
            for (Address address : addressList) {
                if(NumberUtils.isEqual(addressId,address.getId())){
                    return address;
                }
            }
        }
        return null;
    }

    @JsonIgnore
    public Address getDefaultAddress(){
        if(addressList != null){
            for (Address address : addressList) {
                if(address.isDefault()){
                    return address;
                }
            }
        }
        return null;
    };

    @JsonIgnore
    public Address getLastAddress(){
        if(addressList != null && addressList.size() > 0){
            return addressList.get(addressList.size()-1);
        }
        return null;
    };

    /*************** Address end ***************/


    /*************** Card begin ***************/
    @JsonIgnore
    public void addCard(Card entity){
        if(cardList == null){
            cardList = new ArrayList<>();
        }
        if(cardList.size() == 0){
            entity.setDefault(true);
        }
        if(entity.isDefault()){
            //add card is default，then update all address is not default
            for (Card card : cardList) {
                card.setDefault(false);
            }
        }
        if(entity.isDefault()){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.SECOND,10);//TODO add default card,it(new card) will be show at the first.I'm sorry,English too bad.
        }
        entity.setCustomer(this);
        cardList.add(entity);
    };

    @JsonIgnore
    public void updateCard(Card entity){
        if(cardList != null){
            if(entity.isDefault()){
                //update card is default
                for (Card card : cardList) {
                    card.setDefault(false);
                }
                for (Card card : cardList) {
                    if(NumberUtils.isEqual(entity.getId(),card.getId())){
                        card.setDefault(true);
                    }
                }
            }
        }
    };

    @JsonIgnore
    public void deleteCardById(Long cardId){
        if(cardList != null){
            Optional<Card> cardOptional = cardList.stream().filter(card -> NumberUtils.isEqual(cardId,card.getId())).findFirst();
            Card deleteCard = cardOptional.get();
            cardList.remove(deleteCard);
            if(deleteCard.isDefault() && cardList.size() > 0){
                Card card = cardList.get(0);
                card.setDefault(true);
            }
        }
    };


    @JsonIgnore
    public Card getCardById(Long cardId){
        if(cardList != null){
            for (Card card : cardList) {
                if(NumberUtils.isEqual(cardId,card.getId())){
                    return card;
                }
            }
        }
        return null;
    }

    @JsonIgnore
    public int cardSize(){
        if(cardList != null){
            return cardList.size();
        }
        return 0;
    }


    /*************** Card end ***************/


}
