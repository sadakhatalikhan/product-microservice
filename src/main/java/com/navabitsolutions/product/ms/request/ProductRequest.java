package com.navabitsolutions.product.ms.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;

@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Builder(builderClassName = "Builder", setterPrefix = "with")
public class ProductRequest {
    private String title;
    private BigDecimal price;
    private Integer quality;
}
