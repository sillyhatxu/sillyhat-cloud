package com.sillyhat.cloud.customer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude={"customer"})
@ToString(exclude={"customer"})
public class Card{

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @JsonProperty("last_four")
    private String lastFour;

    /**
     * 判断card是否已经绑定
     */
    private String fingerprint;

    /**
     * 用于支付的source
     */
    @JsonProperty("source_id")
    private String sourceId;

    @JsonProperty("is_default")
    private boolean isDefault;

    /**
     * card type
     */
    @JsonProperty("card_type")
    private String cardType;

}
