package com.fullCar.FullCar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ads")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Account user_id;
    private Double user_price;
    private String brand;
    private String code_fipe;
    private String fuel;
    private String model;
    private Integer model_year;
    private Double fipe_price;
    private String reference_month;
    private LocalDateTime created_at;
    private BigDecimal kilometers_driven;
    private String type_of_vehicle;
    private String traffic_signs;
    private String car_color;
    private String type_of_direction;
    private String gear_box;
    private BigDecimal engine_power;
}
