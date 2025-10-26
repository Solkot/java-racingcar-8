package racingcar;

import java.util.ArrayList;
import java.util.List;

public class InputHandler {
    private static boolean isEmpty(String name) { // 이름 공백 검사
        return name == null || name.isBlank();
    }

    private static boolean isLengthInvalid(String name) { // 이름 길이 검사
        return name.isEmpty() || name.length() > 5;
    }

    private static boolean containsNonAlphabet(String name) { // 이름 알파벳 여부 검사
        for (char c : name.toCharArray()) {
            if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
                return true;
            }
        }
        return false;
    }

    public static void validateCarName(String name) {
        if (isEmpty(name)) {
            throw new IllegalArgumentException("Car name blank error.");
        }
        if (isLengthInvalid(name)) {
            throw new IllegalArgumentException("Car name length error.");
        }
        if (containsNonAlphabet(name)) {
            throw new IllegalArgumentException("Car name is only permitted alphabet.");
        }
    }

    public static List<String> parseCarNames(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input is empty or blank input");
        }

        String[] names = input.split(","); // ","로 분리
        List<String> carNames = new ArrayList<>();

        for (String name : names) {
            String trimmedName = name.trim(); // 추가: 앞뒤 공백 제거
            InputHandler.validateCarName(trimmedName);
            carNames.add(trimmedName);
        }

        return carNames;
    }
}
