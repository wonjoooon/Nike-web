package com.wonjun.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Component
@Getter
@Setter
public class Product_copy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pc_code;

    @ManyToOne
    @JoinColumn(name ="p_code")
    private Product p_code;

    @Column (nullable = false)
    private String pc_size;

    @Column (nullable = false)
    private int pc_quantity;
}
