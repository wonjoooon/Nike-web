package com.wonjun.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCopyForm {
    @NotEmpty(message ="상품 코드는 필수 입력사항입니다.")
    private String p_code;

    @NotEmpty(message = "사이즈는 필수 입력사항입니다.")
    private String pc_size;

    @NotEmpty(message = "수량은 필수 입력사항입니다.")
    private String pc_quantity;
}
