package racingcar;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class InputHandlerTest {
    @Test
    void validateNullName() {
        assertThatThrownBy(() -> InputHandler.validateCarName(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Car name blank error.");
    }

    @Test
    void validateBlankName() {
        assertThatThrownBy(() -> InputHandler.validateCarName("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Car name blank error.");
    }

    @Test
    void validateEmptyName() {
        assertThatThrownBy(() -> InputHandler.validateCarName(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Car name blank error.");
    }

    @Test
    void validateNameLength() {
        assertThatThrownBy(() -> InputHandler.validateCarName("abcdef"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Car name length error.");
    }

    @Test
    void validateAlphabetName() {
        assertThatThrownBy(() -> InputHandler.validateCarName("abc1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Car name is only permitted alphabet.");
    }

    @Test
    void validateCarNameContainsSymbol() {
        assertThatThrownBy(() -> InputHandler.validateCarName("car!"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Car name is only permitted alphabet.");
    }

    @Test
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
    void parseCarNamesNullInput() {
        assertThatThrownBy(() -> InputHandler.parseCarNames(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Input is empty or blank input");
    }
    @Test
    void parseCarNamesBlankInput() {
        assertThatThrownBy(() -> InputHandler.parseCarNames("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Input is empty or blank input");
    }

    // getTryCount
    @Test
    void getTryCountValidInput() {
        // given
        String input = "5";

        // when
        int result = InputHandler.getTryCount(input);

        // then
        assertThat(result).isEqualTo(5);
    }

    @Test
    void getTryCountZero() { // 이후 0일때는 따로 결과를 바로 표시하도록 진행
        // given
        String input = "0";

        // when
        int result = InputHandler.getTryCount(input);

        // then
        assertThat(result).isZero();
    }

    @Test
    void getTryCountNullInput() {
        assertThatThrownBy(() -> InputHandler.getTryCount(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Count blank error");
    }

    @Test
    void getTryCountBlankInput() {
        assertThatThrownBy(() -> InputHandler.getTryCount("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Count blank error");
    }

    @Test
    void getTryCountNonNumericInput() {
        assertThatThrownBy(() -> InputHandler.getTryCount("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Count input must be number");
    }

    @Test
    void getTryCountNegativeNumber() {
        assertThatThrownBy(() -> InputHandler.getTryCount("-3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Count input must be over 0");
    }
}
