package racingcar;

import java.util.List;
import java.util.stream.Collectors;

public class OutView {
    private static final String EXECUTION_RESULT_MESSAGE = "\n실행 결과";
    private static final String WINNER_MESSAGE = "최종 우승자 : ";

    public static void printStartMessage() {
        System.out.println(EXECUTION_RESULT_MESSAGE);
    }

    //OutputHandler의 getCurrentRaceState를 안 쓰는 이유는 책임 분리(단지 출력용), SRP 원칙을 지키기 위함
    private static String formatCarProgress(Car car) {
        return car.getName() + " : " + "-".repeat(car.getPosition());
    }

    public static void printRaceResult(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(formatCarProgress(car));
        }
        System.out.println();
    }

    public static void printWinners(List<Car> winners) {
        String winnerNames = winners.stream()
                .map(Car::getName)
                .collect(Collectors.joining(", "));
        System.out.println(WINNER_MESSAGE + winnerNames);
    }
}
