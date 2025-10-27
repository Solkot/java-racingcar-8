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

    // 테스트를 위해 canMove()를 항상 true/false로 조정할 수 있는 서브클래스 생성
    // static 메서드가 존재하기에 상속은 할 수 없기에 기능을 true/false로 조정 이외에 나머지 메서드는 동일하게 구성
    static class TestMove {
        private final boolean moveDecision;

        TestMove(boolean moveDecision) {
            this.moveDecision = moveDecision;
        }

        public  boolean canMove() {
            return moveDecision;
        }

        public void moveCar(Car car) {
            if (canMove()) {
                car.increasePosition();
            }
        }
    }

    @Test
    void moveCarTestTrue() {
        Car car = new Car("pobi");
        TestMove race = new TestMove(true);

        race.moveCar(car);

        assertThat(car.getPosition()).isEqualTo(1);
    }

    @Test
    void moveCarTestFalse() {
        Car car = new Car("pobi");
        TestMove race = new TestMove(false);

        race.moveCar(car);

        assertThat(car.getPosition()).isZero();
    }
}
