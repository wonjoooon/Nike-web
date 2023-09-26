package com.wonjun.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

@Entity
@Component
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private Long p_code;

    @Column(nullable = false, name = "m_id")
    private String memberId;

    @Column(nullable = false)
    @ColumnDefault("1")
    private int pc_quantity;

    @Column(nullable = false)
    private String pc_size;

    @Column(nullable = false)
    private String p_name;
}
