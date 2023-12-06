import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class Day5 extends Day {

    List<Long> seeds = new ArrayList<>();

    List<SeedRange> seedRanges = new ArrayList<>();
    List<Entry> seedToSoilList = new ArrayList<>();
    List<Entry> soilToFertilizerList = new ArrayList<>();
    List<Entry> fertilizerToWaterList = new ArrayList<>();
    List<Entry> waterToLightList = new ArrayList<>();
    List<Entry> lightToTemperatureList = new ArrayList<>();
    List<Entry> tempToHumidityList = new ArrayList<>();
    List<Entry> huidityToLocationList = new ArrayList<>();

    List<Entry> currentList;

    @Override
    public long task1(boolean isTest) {

        List<String> lines = puzzleInputStream().collect(Collectors.toList());

        for (String line : lines) {
            if (line.startsWith("seeds")) {
                handleSeeds(line);
                continue;
            }

            if (line.startsWith("seed-to-soil")) {
                currentList = seedToSoilList;
                continue;
            }

            if (line.startsWith("soil-to-fertilizer")) {
                currentList = soilToFertilizerList;
                continue;
            }
            if (line.startsWith("fertilizer-to-water")) {
                currentList = fertilizerToWaterList;
                continue;
            }

            if (line.startsWith("water-to-light")) {
                currentList = waterToLightList;
                continue;
            }

            if (line.startsWith("light-to-temperature")) {
                currentList = lightToTemperatureList;
                continue;
            }

            if (line.startsWith("temperature-to-humidity")) {
                currentList = tempToHumidityList;
                continue;
            }
            if (line.startsWith("humidity-to-location")) {
                currentList = huidityToLocationList;
                continue;
            }

            if (!line.isBlank()) {
                handleEntry(line);
            }
        }

        long lowest = Long.MAX_VALUE;

        for (Long seed : seeds) {
            long location = processSeed(seed);
            if (location < lowest) {
                lowest = location;
            }
        }
        System.out.println("Lowest: " + lowest);

        return lowest;
    }

    @Override
    public long task2(boolean isTest) {

        List<String> lines = puzzleInputStream().collect(Collectors.toList());

        for (String line : lines) {
            if (line.startsWith("seeds")) {
                handleSeedsPartTwo(line);
                continue;
            }

            if (line.startsWith("seed-to-soil")) {
                currentList = seedToSoilList;
                continue;
            }

            if (line.startsWith("soil-to-fertilizer")) {
                currentList = soilToFertilizerList;
                continue;
            }
            if (line.startsWith("fertilizer-to-water")) {
                currentList = fertilizerToWaterList;
                continue;
            }

            if (line.startsWith("water-to-light")) {
                currentList = waterToLightList;
                continue;
            }

            if (line.startsWith("light-to-temperature")) {
                currentList = lightToTemperatureList;
                continue;
            }

            if (line.startsWith("temperature-to-humidity")) {
                currentList = tempToHumidityList;
                continue;
            }
            if (line.startsWith("humidity-to-location")) {
                currentList = huidityToLocationList;
                continue;
            }

            if (!line.isBlank()) {
                handleEntry(line);
            }
        }

        long lowest = Long.MAX_VALUE;

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        List<Callable<Long>> tasks = new ArrayList<>();

        for (SeedRange s : seedRanges) {
            tasks.add(() -> s.getLowest());
        }

        try {
            List<Future<Long>> futures = executor.invokeAll(tasks);

            for (Future<Long> future : futures) {
                Long res = future.get();
                if (res < lowest) {
                    lowest = res;
                }
            }

        } catch (Exception e) {
        }

        return lowest;
    }

    long processSeed(Long seed) {
        long soil = searchList(seed, seedToSoilList);

        long fertilizer = searchList(soil, soilToFertilizerList);
        long water = searchList(fertilizer, fertilizerToWaterList);
        long light = searchList(water, waterToLightList);
        long temperature = searchList(light, lightToTemperatureList);
        long humidity = searchList(temperature, tempToHumidityList);
        long location = searchList(humidity, huidityToLocationList);

        return location;
    }

    private Long searchList(Long searchNumber, List<Entry> list) {
        for (Entry e : list) {
            if (e.getMappingIfPresent(searchNumber) != null) {
                return e.getMappingIfPresent(searchNumber);
            }
        }
        return searchNumber;
    }

    void handleSeeds(String line) {
        // seeds: 79 14 55 13
        String[] parts = line.split(": ");
        String[] numbers = parts[1].split(" ");
        for (String number : numbers) {
            seeds.add(Long.parseLong(number));
        }
    }

    void handleSeedsPartTwo(String line) {
        // seeds: 79 14 55 13
        String[] parts = line.split(": ");
        String[] numbers = parts[1].split(" ");

        for (int i = 0; i < numbers.length; i += 2) {
            Long start = Long.parseLong(numbers[i]);
            Long range = Long.parseLong(numbers[i + 1]);
            seedRanges.add(new SeedRange(start, range));
        }
    }

    void handleEntry(String line) {
        Entry entry = new Entry(line);
        currentList.add(entry);
    }

    class SeedRange {
        long start;
        long range;

        SeedRange(Long start, Long range) {
            this.start = start;
            this.range = range;
        }

        Long getLowest() {
            long lowest = Long.MAX_VALUE;
            for (long i = start; i < (start + range); i++) {
                long location = processSeed(i);
                if (location < lowest) {
                    lowest = location;
                }
            }
            return lowest;
        }
    }

    class Entry {

        long destinationStart;
        long sourceStart;

        long range;

        Entry(String data) {
            String[] parts = data.split(" ");
            sourceStart = Long.parseLong(parts[1]);
            destinationStart = Long.parseLong(parts[0]);
            range = Long.parseLong(parts[2]);
        }

        Long getMappingIfPresent(Long entry) {
            if (entry >= sourceStart && entry <= (sourceStart + range)) {
                Long offset = entry - sourceStart;
                return destinationStart + offset;
            }

            return null;
        }
    }
}
