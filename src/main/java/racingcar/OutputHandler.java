package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static String getCurrentRaceState(List<Car> cars) {
        return cars.stream()
                .map(car -> car.getName() + " : " + "-".repeat(car.getPosition()))
                .collect(Collectors.joining("\n"));
    }

    public static List<String> runRace(List<Car> cars, int tryCount) {
        List<String> raceResults = new ArrayList<>();

        for (int i = 0; i < tryCount; i++) {
            for (Car car : cars) {
                moveCar(car);
            }
            raceResults.add(getCurrentRaceState(cars));
        }
        return raceResults;
    }

    public static List<Car> getWinners(List<Car> cars) {
        int max = cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);
        return cars.stream()
                .filter(car -> car.getPosition() == max)
                .collect(Collectors.toList());
        //한 명, 여러 명 상관 없이 훑고 ", " 추가
    }
}
