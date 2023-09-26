package com.wonjun.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Getter
@Setter
@Component
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long m_uid;

    @Column(unique = true, nullable = false, name="m_id")
    private String username;

    @Column(nullable = false, name = "m_password")
    private String password;

    @Column(nullable = false)
    private String m_name;

    @Column(unique = true, nullable = false)
    private String m_email;

    @Column(nullable = false)
    private String m_address;
}
