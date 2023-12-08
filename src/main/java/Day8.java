import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day8 extends Day {

    char[] instructions;

    Map<String, Elements> elementMap = new HashMap<>();

    @Override
    public long task1(boolean isTest) {

        puzzleInputStream(isTest).forEach(e -> {
            if (e.indexOf("=") != -1) {
                String[] parts = e.split(" = ");
                elementMap.put(parts[0].trim(), new Elements(parts[1]));
            } else if (!e.isBlank()) {
                instructions = e.toCharArray();
            }
        });

        boolean found = false;
        int steps = 0;
        int instructionSize = instructions.length;
        Elements element = elementMap.get("AAA");

        while (!found) {
            int position = steps % instructionSize;

            String s = element.getValue(instructions[position]);

            if (s.equals("ZZZ")) {
                found = true;
            } else {
                element = elementMap.get(s);
            }

            steps++;
        }
        return steps;
    }

    @Override
    public long task2(boolean isTest) {
        puzzleInputStream(isTest).forEach(e -> {
            if (e.indexOf("=") != -1) {
                String[] parts = e.split(" = ");
                elementMap.put(parts[0].trim(), new Elements(parts[1]));
            } else if (!e.isBlank()) {
                instructions = e.toCharArray();
            }
        });

        List<Long> startingElements = elementMap.entrySet().stream()
                .filter(e -> e.getKey().endsWith("A"))
                .map(e -> steps(e.getKey()))
                .collect(Collectors.toList());

        long steps = startingElements.get(0);
        for (int i = 1; i < startingElements.size(); i++) {
            steps = lcm(steps, startingElements.get(i));
        }
        return steps;
    }

    long steps(String key) {
        boolean found = false;
        int steps = 0;
        int instructionSize = instructions.length;
        Elements element = elementMap.get(key);

        while (!found) {
            int position = steps % instructionSize;

            String s = element.getValue(instructions[position]);

            if (s.endsWith("Z")) {
                found = true;
            } else {
                element = elementMap.get(s);
            }

            steps++;
        }
        return steps;
    }

    long lcm(long x, long y) {
        long max = Math.max(x, y);
        long min = Math.min(x, y);
        long lcm = max;
        while (lcm % min != 0) {
            lcm += max;
        }
        return lcm;
    }

    class Elements {
        String left;
        String right;

        Elements(String data) {
            String[] parts = data.replace("(", "").replace(")", "").split(",");
            left = parts[0].trim();
            right = parts[1].trim();
        }

        String getValue(char c) {
            if (c == 'L') return left;
            return right;
        }
    }
}
