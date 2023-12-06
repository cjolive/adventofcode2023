import java.util.List;
import java.util.stream.Collectors;

public class Day2 extends Day {
    @Override
    public long task1(boolean isTest) {

        List<String> lines = puzzleInputStream().collect(Collectors.toList());
        int sum = 0;

        for (String line : lines) {
            String[] gameParts = line.split(":");
            String gameNum = gameParts[0].split(" ")[1];
            String[] games = gameParts[1].split("[;,]");

            boolean valid = true;
            checkGame:
            for (String game : games) {
                String[] draw = game.trim().split(" ");
                int num = Integer.parseInt(draw[0].trim());
                switch (draw[1]) {
                    case "red":
                        if (num > 12) {
                            valid = false;
                            break checkGame;
                        }
                    case "green":
                        if (num > 13) {
                            valid = false;
                            break checkGame;
                        }
                    case "blue":
                        if (num > 14) {
                            valid = false;
                            break checkGame;
                        }
                }
            }

            if (valid) {
                sum += Integer.parseInt(gameNum);
            }
        }
        return sum;
    }

    @Override
    public long task2(boolean isTest) {
        List<String> lines = puzzleInputStream().collect(Collectors.toList());
        long sum = 0;

        for (String line : lines) {
            String[] gameParts = line.split(":");
            String gameNum = gameParts[0].split(" ")[1];
            String[] games = gameParts[1].split("[;,]");
            int maxRed = 0;
            int maxGreen = 0;
            int maxBlue = 0;

            for (String game : games) {
                String[] draw = game.trim().split(" ");
                int num = Integer.parseInt(draw[0].trim());
                switch (draw[1]) {
                    case "red":
                        if (num > maxRed) {
                            maxRed = num;
                        }
                        break;
                    case "green":
                        if (num > maxGreen) {
                            maxGreen = num;
                        }
                        break;
                    case "blue":
                        if (num > maxBlue) {
                            maxBlue = num;
                        }
                }
            }

            sum += (maxRed * maxGreen * maxBlue);
        }
        return sum;
    }
}
