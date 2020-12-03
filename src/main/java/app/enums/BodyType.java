package app.enums;

import static app.external.api.model.CarCategory.CONVERTIBLE;
import static app.external.api.model.CarCategory.HATCHBACK;
import static app.external.api.model.CarCategory.MINIVAN;
import static app.external.api.model.CarCategory.PICKUP;
import static app.external.api.model.CarCategory.SUV;
import static app.external.api.model.CarCategory.VAN;

import java.util.Arrays;
import java.util.List;

import app.external.api.model.CarCategory;

public enum BodyType {
    OFFROADER(SUV, PICKUP),
    WAGON(CarCategory.WAGON, HATCHBACK, VAN, MINIVAN),
    SEDAN(CarCategory.SEDAN),
    COUPE(CarCategory.COUPE, CONVERTIBLE);

    BodyType(CarCategory... carCategories) {
        this.extCarCategories = Arrays.asList(carCategories);
    }

    private final List<CarCategory> extCarCategories;

    public static BodyType getByTestType(CarCategory carCategory) {
        return Arrays.stream(BodyType.values())
                .filter(bodyType -> bodyType.extCarCategories.contains(carCategory))
                .findFirst().orElse(null);
    }
}
