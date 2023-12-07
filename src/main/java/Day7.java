import java.util.*;
import java.util.stream.Collectors;

public class Day7 extends Day {

    static Map<Character, Integer> cardScores = new HashMap<>();

    static Map<Character, Integer> cardScoresPartTwo = new HashMap<>();

    static {
        cardScores.put('A', 1);
        cardScores.put('K', 2);
        cardScores.put('Q', 3);
        cardScores.put('J', 4);
        cardScores.put('T', 5);
        cardScores.put('9', 6);
        cardScores.put('8', 7);
        cardScores.put('7', 8);
        cardScores.put('6', 9);
        cardScores.put('5', 10);
        cardScores.put('4', 11);
        cardScores.put('3', 12);
        cardScores.put('2', 13);

        cardScoresPartTwo.put('A', 1);
        cardScoresPartTwo.put('K', 2);
        cardScoresPartTwo.put('Q', 3);
        cardScoresPartTwo.put('J', 14);
        cardScoresPartTwo.put('T', 5);
        cardScoresPartTwo.put('9', 6);
        cardScoresPartTwo.put('8', 7);
        cardScoresPartTwo.put('7', 8);
        cardScoresPartTwo.put('6', 9);
        cardScoresPartTwo.put('5', 10);
        cardScoresPartTwo.put('4', 11);
        cardScoresPartTwo.put('3', 12);
        cardScoresPartTwo.put('2', 13);
    }

    @Override
    public long task1(boolean isTest) {
        List<HandAndBid> lines = puzzleInputStream(isTest)
                .map(s -> new HandAndBid(s, false))
                .sorted()
                .collect(Collectors.toList());

        long sum = 0;
        for (int i = 0; i < lines.size(); i++) {
            sum += lines.get(i).bid * (i + 1);
        }

        return sum;
    }

    @Override
    public long task2(boolean isTest) {
        List<HandAndBid> lines = puzzleInputStream(isTest)
                .map(s -> new HandAndBid(s, true))
                .sorted()
                .collect(Collectors.toList());

        long sum = 0;
        for (int i = 0; i < lines.size(); i++) {
            sum += lines.get(i).bid * (i + 1);
        }

        return sum;
    }

    class HandAndBid implements Comparable<HandAndBid> {

        boolean jokers;

        Map<Character, Integer> handScores = new HashMap<>();

        List<Integer> sortedHandScores = new ArrayList<>();

        String hand;

        char[] cards;

        int bid;

        HandAndBid(String data, boolean jokers) {
            this.jokers = jokers;
            String[] parts = data.split(" ");
            this.hand = parts[0].trim();
            this.bid = Integer.parseInt(parts[1].trim());
            for (char c : this.hand.toCharArray()) {
                addHandScore(c);
            }

            cards = this.hand.toCharArray();

            if (jokers) handleJokers();

            sortedHandScores = handScores.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
        }

        void addHandScore(Character c) {
            handScores.merge(c, 1, Integer::sum);
        }

        void handleJokers() {

            if (handScores.size() == 1) {
                return;
            }

            if (handScores.get('J') != null) {

                Integer j = handScores.get('J');

                Optional<Map.Entry<Character, Integer>> charToUpdate = handScores.entrySet().stream()
                        .filter(e -> e.getKey() != 'J')
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .findFirst();

                handScores.remove('J');

                handScores.merge(charToUpdate.get().getKey(), j, Integer::sum);
            }
        }

        int handRank() {
            if (sortedHandScores.size() == 1) return 1;

            // four / full house
            if (sortedHandScores.size() == 2) {
                if (sortedHandScores.get(0) == 4) return 2;
                return 3;
            }

            // three, two pair
            if (sortedHandScores.size() == 3) {
                if (sortedHandScores.get(0) == 3) return 4;
                return 5;
            }

            // one pair
            if (sortedHandScores.size() == 4) return 6;

            return 7;
        }

        @Override
        public int compareTo(HandAndBid that) {
            if (this.handRank() < that.handRank()) return 1;
            if (this.handRank() > that.handRank()) return -1;

            // check chars in order
            for (int i = 0; i < 5; i++) {
                if (jokers) {
                    if (cardScoresPartTwo.get(this.cards[i]) < cardScoresPartTwo.get(that.cards[i])) {
                        return 1;
                    }
                    if (cardScoresPartTwo.get(this.cards[i]) > cardScoresPartTwo.get(that.cards[i])) {
                        return -1;
                    }
                } else {
                    if (cardScores.get(this.cards[i]) < cardScores.get(that.cards[i])) {
                        return 1;
                    }
                    if (cardScores.get(this.cards[i]) > cardScores.get(that.cards[i])) {
                        return -1;
                    }
                }
            }

            return 0;
        }
    }
}
