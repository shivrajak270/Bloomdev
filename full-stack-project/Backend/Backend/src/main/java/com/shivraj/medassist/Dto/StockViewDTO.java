package com.shivraj.medassist.Dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class StockViewDTO {

    private String medicine_name;
    private long quantity;
    private double price;
}
