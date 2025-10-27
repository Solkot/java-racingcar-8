package racingcar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OutViewTest {
    private ByteArrayOutputStream output;

    @BeforeEach
    void setUp() {
        output = new ByteArrayOutputStream(); //System.out.println()은 콘솔로 바로 출력돼서 테스트 코드에서 내용을 읽을 수 없는 내용을 메모리 상에서 읽고 저장을 위해
        System.setOut(new PrintStream(output)); //assertThat을 통해 출력값을 검증하기 위한 단계
    }
    @Test
    void printStartMessageTest() {
        OutView.printStartMessage();

        assertThat(output.toString())
                .contains("실행 결과");
    }

    @Test
    void printRaceResultTest() {
        Car pobi = new Car("pobi");
        Car jun = new Car("jun");

        pobi.increasePosition();
        pobi.increasePosition();
        jun.increasePosition();

        OutView.printRaceResult(List.of(pobi, jun));

        String printed = output.toString().trim();

        assertThat(printed).contains("pobi : --");
        assertThat(printed).contains("jun : -");
    }
}
