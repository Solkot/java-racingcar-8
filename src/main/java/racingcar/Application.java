package racingcar;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        //시작 출력 및 사용자 입력 보관
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String names = Console.readLine();
        System.out.println("시도할 횟수는 몇 회인가요");
        String count = Console.readLine();
        OutView.printStartMessage();

        //입력 검증(참가자, 시도 횟수)
        List<String> carNames = InputHandler.parseCarNames(names);
        int tryCount = InputHandler.getTryCount(count);

        //입력 결과 가공, 각 턴의 결과 출력
        List<Car> cars = OutputHandler.createCars(carNames);

        for (int i = 0; i < tryCount; i++) {
            for (Car car : cars) {
                OutputHandler.moveCar(car);
            }
            OutView.printRaceResult(cars);
        }

        // 우승자 출력
        List<Car> winners = OutputHandler.getWinners(cars);
        OutView.printWinners(winners);
    }
}
