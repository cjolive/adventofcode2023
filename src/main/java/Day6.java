import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day6 extends Day {
    @Override
    public long task1(boolean isTest) {

        List<String> lines = puzzleInputStream(isTest).collect(Collectors.toList());

        List<TimeAndDistance> entries = new ArrayList<>();

        String timeLine = lines.get(0);
        List<Integer> timeLineParts = Arrays.stream(
                        timeLine.substring(timeLine.indexOf(':') + 1).trim().split(" "))
                .filter(s -> !s.isBlank())
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        String distanceLine = lines.get(1);
        List<Integer> distanceLineParts = Arrays.stream(distanceLine
                        .substring(distanceLine.indexOf(':') + 1)
                        .trim()
                        .split(" "))
                .filter(s -> !s.isBlank())
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        for (int i = 0; i < timeLineParts.size(); i++) {
            entries.add(new TimeAndDistance(timeLineParts.get(i), distanceLineParts.get(i)));
        }

        long total = 0;

        for (TimeAndDistance td : entries) {

            if (total == 0) {
                total = td.calculate();
            } else {
                total *= td.calculate();
            }
        }
        return total;
    }

    @Override
    public long task2(boolean isTest) {
        List<String> lines = puzzleInputStream(isTest).collect(Collectors.toList());

        String timeLine = lines.get(0);
        List<String> timeLineParts = Arrays.stream(
                        timeLine.substring(timeLine.indexOf(':') + 1).trim().split(" "))
                .filter(s -> !s.isBlank())
                .collect(Collectors.toList());

        String distanceLine = lines.get(1);
        List<String> distanceLineParts = Arrays.stream(distanceLine
                        .substring(distanceLine.indexOf(':') + 1)
                        .trim()
                        .split(" "))
                .filter(s -> !s.isBlank())
                .collect(Collectors.toList());

        StringBuilder timeString = new StringBuilder();
        for (String s : timeLineParts) timeString.append(s);

        StringBuilder distanceString = new StringBuilder();
        for (String s : distanceLineParts) distanceString.append(s);

        TimeAndDistance td =
                new TimeAndDistance(Long.parseLong(timeString.toString()), Long.parseLong(distanceString.toString()));

        return td.calculateV2();
    }

    class TimeAndDistance {

        Integer time;
        Integer distance;

        long longTime;
        long longDistance;

        TimeAndDistance(Integer time, Integer distance) {
            this.time = time;
            this.distance = distance;
        }

        TimeAndDistance(Long time, Long distance) {
            this.longTime = time;
            this.longDistance = distance;
        }

        int calculate() {

            int numberOfWays = 0;

            for (int i = 1; i < time; i++) {
                int remainingTime = time - i;
                int travelled = remainingTime * i;
                if (travelled > distance) {
                    numberOfWays++;
                }
            }

            return numberOfWays;
        }

        long calculateV2() {

            long numberOfWays = 0;

            for (long i = 1; i < longTime; i++) {
                long remainingTime = longTime - i;
                long travelled = remainingTime * i;
                if (travelled > longDistance) {
                    numberOfWays++;
                }
            }

            return numberOfWays;
        }
    }
}
