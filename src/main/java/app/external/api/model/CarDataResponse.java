package app.external.api.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDataResponse implements Serializable {
    @JsonProperty(value = "objectId")
    private String objectId;

    @JsonProperty(value = "Make")
    private String make;

    @JsonProperty(value = "Model")
    private String model;

    @JsonProperty(value = "Year")
    private Integer year;

    @JsonProperty(value = "Category")
    private CarCategory category;

}
