package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import app.enums.BodyType;
import app.enums.DriveType;
import app.enums.TransmissionType;
import lombok.Data;

@Data
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Min(1980)
    @Max(2020)
    @Column(name = "prod_year")
    private Short year;

    @Min(1)
    @Max(12)
    @Column(name = "prod_month")
    private Short month;

    @Column(name = "engine")
    private Short engine;

    @Column(name = "turbo")
    private Boolean hasTurbo;

    @Column(name = "power")
    private Short power;

    @Column(name = "transmission_type")
    @Enumerated(value = EnumType.STRING)
    private TransmissionType transmissionType;

    @Column(name = "drive_type")
    @Enumerated(value = EnumType.STRING)
    private DriveType driveType;

    @Column(name = "body_type")
    @Enumerated(value = EnumType.STRING)
    private BodyType bodyType;

    @Column(name = "color")
    private String color;

    @Column(name = "ext_id")
    private String extId;
}
