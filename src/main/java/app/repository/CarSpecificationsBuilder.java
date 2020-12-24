package app.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import app.entity.Car;
import app.util.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

public class CarSpecificationsBuilder {

    private final List<SearchCriteria> params = new ArrayList<>();

    public CarSpecificationsBuilder with(String key, String operation, Object value) {
        // в список добавляется критерий для фильтрации типа color=red
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Car> build() {
        if (params.size() == 0) {
            return null;
        }

        // перемапливаем список критериев в список спецификаций (raw!?)
        List<Specification> specs = params.stream().map(CarSpecification::new).collect(Collectors.toList());

        Specification result = specs.get(0);
        for (int i = 1; i < params.size(); i++) {
            // в цикле накапливаем все спецификации, чтобы выполнялись все
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}
