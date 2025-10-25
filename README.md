# java-racingcar-precourse

# 자동차 경주

## 1. 기능 요구 사항 요약

* 주어진 횟수 동안 n대의 자동차가 전진 또는 멈출 수 있다.
* 각 자동차에 이름을 부여할 수 있다.
* 이름은 쉼표(,)로 구분하며, 각 이름은 5자 이하만 가능하다.
* 사용자는 시도할 횟수를 입력한다.
* 전진 조건: 0~9 사이의 난수 중 4 이상일 경우 전진한다.
* 모든 시도가 끝난 뒤 최종 우승자를 출력한다.
* 우승자가 여러 명일 경우 쉼표(,)로 구분하여 출력한다.
* 잘못된 입력 시 `IllegalArgumentException`을 발생시키고 프로그램은 종료된다.

---

## 2. 구현 기능 목록
### 2.1 입력 처리

#### `validateCarName(String name)`

* 이름이 비어 있지 않은가 (`""` 허용 X)
* 이름 길이가 1~5자 이내인가
* 위 조건 중 하나라도 위배되면 `IllegalArgumentException` 발생.

#### `parseCarNames(String input)`

* 입력받은 문자열을 쉼표(,) 기준으로 분리한다.
* 분리된 각 이름을 `validateCarName()`으로 검증한다.
* 잘못된 이름이 존재할 경우 `IllegalArgumentException` 발생.
* 검증된 이름 리스트(`List<String>`)를 반환한다.

#### `getTryCount(String input)`

* 문자열이 숫자인지 확인한다.
* 0 이상의 정수인지 검증한다.
* 잘못된 입력일 경우 `IllegalArgumentException` 발생.
* 시도 횟수를 정수형으로 반환한다.

---

### 2.2 도메인 로직

#### `createCars(List<String> names)`

* 검증된 자동차 이름 리스트를 받아 `Car` 객체 리스트로 변환한다.
* 각 이름으로 `Car` 객체를 생성하고 `List<Car>`에 저장한다.

#### `getRandomNumber()`

* 0~9 범위의 난수를 생성한다.

#### `canMove()`

* 생성된 난수가 4 이상일 경우 `true` 반환, 아니면 `false` 반환.

#### `moveCar(Car car)`

* `canMove()`가 `true`이면 해당 자동차의 위치를 1 증가시킨다.

#### `runRace(List<Car> cars, int tryCount)`

* 시도 횟수만큼 반복하며 각 턴마다 모든 자동차의 이동 여부를 결정한다.
* 매 턴 결과를 출력용으로 저장한다.

#### `getWinners(List<Car> cars)`

* 최종 이동 거리가 가장 먼 자동차의 이름을 추출한다.
* 여러 명일 경우 쉼표(,)로 구분한 문자열로 반환한다.

---

### 2.3 출력 처리

#### `printRaceResult()`

* 매 시도별 자동차 이동 결과를 출력한다.
* Car의 숫자를 받아 `-`를 반복해서 출력한다.

#### `printWinners()`

* 우승자 한 명일 경우
* 공동 우승자일 경우
* 모두 고려해서 출력한다.

### 2.4 예외 처리

* 이름이 5자를 초과하거나 비어 있을 경우 `IllegalArgumentException` 발생.
* 이름에 공백이 포함된 경우 `IllegalArgumentException` 발생.
* 시도 횟수가 0 미만이거나 숫자가 아닐 경우 `IllegalArgumentException` 발생.
* 입력값이 비어 있을 경우 `IllegalArgumentException` 발생.
