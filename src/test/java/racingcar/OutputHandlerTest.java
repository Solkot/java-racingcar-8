package racingcar;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OutputHandlerTest {
    @Test
    void createCarsTest() {
        // given
        List<String> names = List.of("pobi", "woni", "jun");

        // when
        List<Car> cars = OutputHandler.createCars(names);

        // then
        assertThat(cars).hasSize(names.size());
        for (int i = 0; i < names.size(); i++) {
            assertThat(cars.get(i).getName()).isEqualTo(names.get(i));
            assertThat(cars.get(i).getPosition()).isZero();
        }
    }

    @Test
    void getRandomNumber() {
        for (int i = 0; i < 100; i++) {
            int random = OutputHandler.getRandomNumber();
            assertThat(random)
                    .isGreaterThanOrEqualTo(0)
                    .isLessThan(10);
        }
    }
}
