package racingcar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static racingcar.OutputHandler.getCurrentRaceState;

public class OutputHandlerTest {
    @Test
    @DisplayName("createCars, 이름 리스트를 Car 객체 리스트로 변환 Test")
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
    @DisplayName("getRandomNumber, 항상 0~9 범위의 정수를 반환 Test")
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

        public boolean canMove() {
            return moveDecision;
        }

        public void moveCar(Car car) {
            if (canMove()) {
                car.increasePosition();
            }
        }
    }

    @Test
    @DisplayName("moveCar, canMove()가 true일 때 위치 1 증가")
    void moveCarTestTrue() {
        Car car = new Car("pobi");
        TestMove race = new TestMove(true);

        race.moveCar(car);

        assertThat(car.getPosition()).isEqualTo(1);
    }

    @Test
    @DisplayName("moveCar, canMove()가 false일 때 위치 변화 없음")
    void moveCarTestFalse() {
        Car car = new Car("pobi");
        TestMove race = new TestMove(false);

        race.moveCar(car);

        assertThat(car.getPosition()).isZero();
    }

    //GetCurrentRaceState
    private Car car1;
    private Car car2;
    private List<Car> cars;

    @BeforeEach
    void setUp() {
        car1 = new Car("pobi");
        car2 = new Car("jun");
        cars = Arrays.asList(car1, car2);
    }

    @Test
    @DisplayName("getCurrentRaceState, 참가자 이동이 정상인지 Test")
    void testGetCurrentRaceState() {
        //test를 위해 GetCurrentRaceState를 public으로 열러 놓음 -> 이후 방법 찾아보기
        car1.increasePosition();
        car1.increasePosition();
        car2.increasePosition();

        String expected =
                "pobi : --\n" +
                        "jun : -";

        String result = getCurrentRaceState(cars);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("RunRaceResultSize, 시도 횟수만큼의 문자열이 저장되는지 Test")
    void testRunRaceResultSize() {
        // Random 요소가 있어 정확한 결과는 예측이 불가능, 그렇기에 시도 횟수만큼의 문자열이 저장되는지 확인
        int tryCount = 5;

        List<String> results = OutputHandler.runRace(cars, tryCount);

        assertThat(results).hasSize(tryCount);
    }

    @Test
    @DisplayName("getWinners, 우승자 1명일때 Test")
    void testGetWinnersSingle() {
        car1.increasePosition();
        car1.increasePosition();
        car2.increasePosition();

        List<Car> winners = OutputHandler.getWinners(cars);

        assertThat(winners).hasSize(1);
        assertThat(winners.get(0).getName()).isEqualTo("pobi");
    }

    @Test
    @DisplayName("getWinners, 우승자 여러 명일때 Test")
    void testGetWinnersMultiple() {
        car1.increasePosition();
        car2.increasePosition();

        List<Car> winners = OutputHandler.getWinners(cars);

        assertThat(winners).hasSize(2);
        assertThat(winners).extracting(Car::getName)
                .containsExactlyInAnyOrder("pobi", "jun");
    }
}
