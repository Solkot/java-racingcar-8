package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class OutputHandler {
    private static final int Standard = 4;

    public static List<Car> createCars(List<String> names) {
        List<Car> cars = new ArrayList<>();
        for (String name : names) {
            cars.add(new Car(name));
        }
        return cars;
    }

    public static int getRandomNumber() {
        return Randoms.pickNumberInRange(0, 9);
    }

    public static boolean canMove() {
        return getRandomNumber() >= Standard;
    }

    public static void moveCar(Car car) {
        if (canMove()) {
            car.increasePosition();
        }
    }
}
