package app.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import app.enums.BodyType;
import app.enums.DriveType;
import app.enums.TransmissionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CarDto implements Serializable {

    @JsonProperty(value = "id")
    private Long id;

    @NotNull
    @JsonProperty(value = "brand")
    private String brand;

    @NotNull
    @JsonProperty(value = "model")
    private String model;

    @NotNull
    @Min(1980)
    @Max(2019)
    @JsonProperty(value = "year")
    private Short year;

    @NotNull
    @Min(1)
    @Max(12)
    @JsonProperty(value = "month")
    private Short month;

    @NotNull
    @JsonProperty(value = "engine")
    private Short engine;

    @NotNull
    @JsonProperty(value = "turbo")
    private Boolean hasTurbo;

    @NotNull
    @JsonProperty(value = "power")
    private Short power;

    @NotNull
    @JsonProperty(value = "transmission_type")
    private TransmissionType transmissionType;

    @NotNull
    @JsonProperty(value = "drive_type")
    private DriveType driveType;

    @NotNull
    @JsonProperty(value = "body_type")
    private BodyType bodyType;

    @NotNull
    @JsonProperty(value = "color")
    private String color;

    @JsonProperty(value = "ext_id")
    private String extId;

}
