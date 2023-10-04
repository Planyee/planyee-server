package com.gdsc2023.planyee.domain.place.domain;

import com.gdsc2023.planyee.domain.common.BaseEntity;
import com.gdsc2023.planyee.domain.user.domain.Gender;
import com.gdsc2023.planyee.domain.user.domain.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.text.DecimalFormat;


@Getter
@Entity
@NoArgsConstructor
public class Place extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal latitude;

    @Column(nullable = false)
    private BigDecimal longitude;

    @Builder
    public Place(Long id, String name, BigDecimal latitude, BigDecimal longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
