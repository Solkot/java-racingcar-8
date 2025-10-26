package racingcar;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
}
