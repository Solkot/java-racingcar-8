package racingcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class InputHandlerTest {
    @Test
    @DisplayName("자동차 이름이 null Test")
    void validateNullName() {
        assertThatThrownBy(() -> InputHandler.validateCarName(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Car name blank error.");
    }

    @Test
    @DisplayName("자동차 이름이 공백 space Test")
    void validateBlankName() {
        assertThatThrownBy(() -> InputHandler.validateCarName("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Car name blank error.");
    }

    @Test
    @DisplayName("자동차 이름이 공백 Test")
    void validateEmptyName() {
        assertThatThrownBy(() -> InputHandler.validateCarName(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Car name blank error.");
    }

    @Test
    @DisplayName("자동차 이름 길이 준수 Test")
    void validateNameLength() {
        assertThatThrownBy(() -> InputHandler.validateCarName("abcdef"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Car name length error.");
    }

    @Test
    @DisplayName("자동차 이름이 영문자 획인 Test")
    void validateAlphabetName() {
        assertThatThrownBy(() -> InputHandler.validateCarName("abc1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Car name is only permitted alphabet.");
    }

    @Test
    @DisplayName("자동차 이름이 특수문자 예외 확인 Test")
    void validateCarNameContainsSymbol() {
        assertThatThrownBy(() -> InputHandler.validateCarName("car!"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Car name is only permitted alphabet.");
    }

    @Test
    @DisplayName("자동차 이름이 정해둔 모든 기준 요소를 통과하는지 Test")
    void validateCarNameNoException() {
        assertThatCode(() -> InputHandler.validateCarName("pobi"))
                .doesNotThrowAnyException();

        assertThatCode(() -> InputHandler.validateCarName("A"))
                .doesNotThrowAnyException();

        assertThatCode(() -> InputHandler.validateCarName("WONI"))
                .doesNotThrowAnyException();
    }

    // parseCarNames
    @Test
    @DisplayName("정상적인 입력 문자열, 쉼표 기준으로 분리 후 리스트로 반환 Test")
    void parseCarNamesValidInput() {
        // given
        String input = "pobi,woni,jun";

        // when
        List<String> result = InputHandler.parseCarNames(input);

        // then
        assertThat(result)
                .containsExactly("pobi", "woni", "jun")
                .hasSize(3);
    }

    @Test
    @DisplayName("입력에 공백이 포함되어 있으면 trim 처리 후 유효한 이름으로 반환 Test")
    void parseCarNamesTestTrim() {
        // given
        String input = " pobi , woni , jun ";

        // when
        List<String> result = InputHandler.parseCarNames(input);

        // then
        assertThat(result)
                .containsExactly("pobi", "woni", "jun");
    }

    @Test
    @DisplayName("parseCarNames 입력이 null Test")
    void parseCarNamesNullInput() {
        assertThatThrownBy(() -> InputHandler.parseCarNames(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Input is empty or blank input");
    }
    @Test
    @DisplayName("parseCarNames 입력이 공백 문자열 Test")
    void parseCarNamesBlankInput() {
        assertThatThrownBy(() -> InputHandler.parseCarNames("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Input is empty or blank input");
    }

    // getTryCount
    @Test
    @DisplayName("시도 횟수 정상 입력 및 정수 변환 Test")
    void getTryCountValidInput() {
        // given
        String input = "5";

        // when
        int result = InputHandler.getTryCount(input);

        // then
        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("시도 횟수 입력 0에 대한 정상 입력")
    void getTryCountZero() { // 이후 0일때는 따로 결과를 바로 표시하도록 진행
        // given
        String input = "0";

        // when
        int result = InputHandler.getTryCount(input);

        // then
        assertThat(result).isZero();
    }

    @Test
    @DisplayName("시도 횟수 입력이 null Test")
    void getTryCountNullInput() {
        assertThatThrownBy(() -> InputHandler.getTryCount(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Count blank error");
    }

    @Test
    @DisplayName("시도 횟수 입력이 공백 Test")
    void getTryCountBlankInput() {
        assertThatThrownBy(() -> InputHandler.getTryCount("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Count blank error");
    }

    @Test
    @DisplayName("시도 횟수 입력이 정수가 아닌 경우 Test")
    void getTryCountNonNumericInput() {
        assertThatThrownBy(() -> InputHandler.getTryCount("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Count input must be number");
    }

    @Test
    @DisplayName("시도 횟수 입력이 음수일 경우 Test")
    void getTryCountNegativeNumber() {
        assertThatThrownBy(() -> InputHandler.getTryCount("-3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Count input must be over 0");
    }
}
