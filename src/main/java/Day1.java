import java.util.*;
import java.util.stream.Collectors;

public class Day1 extends Day {

    @Override
    public long task1() {
        List<String> lines = puzzleInputStream().collect(Collectors.toList());

        int sum = 0;

        for (String line : lines) {

            char[] chars = line.toCharArray();

            boolean firstFound = false;
            Character lastFound = null;
            StringBuilder b = new StringBuilder();

            for (int i = 0; i < chars.length; i++) {
                if (!firstFound && Character.isDigit(chars[i])) {
                    b.append(chars[i]);
                    firstFound = true;
                }
                if (Character.isDigit(chars[i])) {
                    lastFound = chars[i];
                }
            }
            if (lastFound != null) {
                b.append(lastFound);
            }

            if (!b.isEmpty()) {
                sum += Integer.valueOf(b.toString());
            }
        }
        return sum;
    }

    @Override
    public long task2() {
        List<String> lines = puzzleInputStream().collect(Collectors.toList());

        List<String> searchStrings = List.of(
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "one", "two", "three", "four", "five", "six", "seven",
                "eight", "nine");

        int sum = 0;

        for (String line : lines) {
            Integer firstNumber = null;
            Integer lastNumber = null;

            int firstIndex = Integer.MAX_VALUE, lastIndex = Integer.MIN_VALUE;
            for (String search : searchStrings) {

                if (line.indexOf(search) != -1 && line.indexOf(search) < firstIndex) {
                    firstNumber = parseString(search);
                    firstIndex = line.indexOf(search);
                }
                if (line.lastIndexOf(search) != -1 && line.lastIndexOf(search) > lastIndex) {
                    lastNumber = parseString(search);
                    lastIndex = line.lastIndexOf(search);
                }
            }

            String foundFirstNumber = firstNumber != null ? Integer.toString(firstNumber) : "";
            String foundLastNumber = lastNumber != null ? Integer.toString(lastNumber) : "";

            int number = Integer.parseInt(foundFirstNumber + foundLastNumber);
            sum += number;
        }
        return sum;
    }

    private int parseString(String s) {
        switch (s) {
            case "one":
                return 1;
            case "two":
                return 2;
            case "three":
                return 3;
            case "four":
                return 4;
            case "five":
                return 5;
            case "six":
                return 6;
            case "seven":
                return 7;
            case "eight":
                return 8;
            case "nine":
                return 9;
            default:
                return Integer.parseInt(s);
        }
    }
}
