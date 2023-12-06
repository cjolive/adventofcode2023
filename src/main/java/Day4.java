import java.util.*;
import java.util.stream.Collectors;

public class Day4 extends Day {
    @Override
    public long task1(boolean isTest) {
        // Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        List<String> lines = puzzleInputStream().collect(Collectors.toList());

        int total = 0;

        for (String line : lines) {
            String[] gameSplit = line.split("\\|");
            Set<Integer> gameNumbers = gameNumbers(gameSplit[0]);
            int matched = 0;

            for (Integer i : pickedNumbers(gameSplit[1])) {
                if (gameNumbers.contains(i)) {
                    matched++;
                }
            }

            if (matched > 0) {
                total += getTotal(matched);
            }
        }

        return total;
    }

    @Override
    public long task2(boolean isTest) {
        Map<Integer, String> map = puzzleInputAsMap();

        Map<Integer, Integer> copyMap = new HashMap<>();

        for (Map.Entry<Integer, String> entry : map.entrySet()) {

            String[] gameSplit = entry.getValue().split("\\|");
            Set<Integer> gameNumbers = gameNumbers(gameSplit[0]);
            int matched = 0;

            for (Integer i : pickedNumbers(gameSplit[1])) {
                if (gameNumbers.contains(i)) {
                    matched++;
                }
            }

            if (matched > 0) {
                for (int i = 1; i <= matched; i++) {

                    if (map.get(entry.getKey() + i) != null) {
                        Integer copies = copyMap.getOrDefault(entry.getKey() + i, 0);
                        copies += 1;
                        copyMap.put(entry.getKey() + i, copies);
                    }
                }
            }

            if (copyMap.get(entry.getKey()) != null) {
                if (matched > 0) {

                    int copyCards = copyMap.get(entry.getKey());

                    for (int j = 0; j < copyCards; j++) {
                        for (int i = 1; i <= matched; i++) {
                            Integer copies = copyMap.getOrDefault(entry.getKey() + i, 0);
                            copies += 1;
                            copyMap.put(entry.getKey() + i, copies);
                        }
                    }
                }
            }
        }

        int gameCards = map.size();
        int copyTotal = copyMap.values().stream().reduce(0, Integer::sum);

        return gameCards + copyTotal;
    }

    Set<Integer> gameNumbers(String gameData) {
        String[] test = gameData.trim().split(":")[1].split(" ");
        return Arrays.stream(gameData.split(":")[1].split(" "))
                .filter(s -> !s.isBlank())
                .map(s -> Integer.parseInt(s.trim()))
                .collect(Collectors.toSet());
    }

    Set<Integer> pickedNumbers(String pickedNumbers) {
        return Arrays.stream(pickedNumbers.split(" "))
                .filter(s -> !s.isBlank())
                .map(s -> Integer.parseInt(s.trim()))
                .collect(Collectors.toSet());
    }

    int getTotal(int numBalls) {
        int runningtotal = 1;
        for (int i = 1; i < numBalls; i++) {
            runningtotal += runningtotal;
        }

        return runningtotal;
    }
}
