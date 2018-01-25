package com.sillyhat.cloud.customer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude={"customer"})
@ToString(exclude={"customer"})
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("postal_code")
    private String postalCode;

    /**
     * 门牌号
     */
    @JsonProperty("floor_unit_number")
    private String floorUnitNumber;

    private String phone;

    @JsonProperty("block_number")
    private String blkNo;

    @JsonProperty("road_name")
    private String roadName;

    /**
     * default Singapore
     */
    private String country;

    /****************************************************/

    @JsonProperty("is_default")
    private boolean isDefault;

    @JsonProperty("card_list")
    private String searchval;

    private String building;

    private String address;

    /**
     * blkNo + roadName
     */
    @JsonProperty("format_address")
    private String formatAddress;

    @JsonProperty("is_default")
    public boolean isDefault() {
        return isDefault;
    }

//    private String postal;
//
//    private String x;
//
//    private String y;
//
//    private String latitude;
//
//    private String longitude;
//
//    private String longtitude;


}
