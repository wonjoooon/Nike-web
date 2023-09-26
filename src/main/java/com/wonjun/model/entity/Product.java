package com.wonjun.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.sql.Date;

@Entity
@Component
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long p_code;

    @Column(nullable = false)
    private String p_name;

    private String p_category;

    private String p_img;

    private String p_description;

    private String p_gender;

    private Date p_date;

    @Column(nullable = false)
    private String p_location;

    @Column(nullable = false)
    private String p_price;

    private String p_color;

}
