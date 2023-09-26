package com.wonjun.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductAddForm {
    @NotEmpty(message = "상품명은 필수 입력사항입니다.")
    private String p_name;

    private String p_category;

    private String p_description;

    private String p_gender;

    @NotEmpty(message = "상품 제조 국가/지역은 필수 입력사항입니다.")
    private String p_location;

    @NotEmpty(message = "상품가격은 필수 입력사항입니다.")
    private String p_price;

    private String p_date;

    private String p_color;
}
