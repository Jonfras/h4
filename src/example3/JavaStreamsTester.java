package example3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JavaStreamsTester {

    static List<String> strings = new ArrayList<>();
    static List<Integer> numbers = new ArrayList<>();
    static final String separator = ";";

    public static void main(String[] args) {


        strings.add("wurschti1");
        strings.add("");
        strings.add("wurschti2");
        strings.add("null");
        strings.add("wurschti3");
        strings.add("wurschti4");
        strings.add("wurschti5");
        strings.add(null);
        strings.add(null);
        strings.add("for");
        strings.add("tni");
        strings.add("te");

        System.out.println("Number of Empty Strings: " + getCountEmptyString(strings));
        System.out.println("Number of Strings with Length 3: " + getCountLenth3(strings));
        System.out.println("String without empty Strings: " + deleteEmptyStrings(strings));
        System.out.println("All notNull Strings merged together: " + getMergedString(strings, separator));

        System.out.println("__________________");
        System.out.println();

        System.out.println("Random numbers:");
        for (int i = 0; i < 6; i++) {
            numbers.add((int) (Math.random() * 100));
            System.out.println(numbers.get(i));
        }
        System.out.println("___________________");
        System.out.println();


        System.out.println("Square Numbers: " + getSquares(numbers));
        System.out.println("Highest Number: " + getMax(numbers));
        System.out.println("Lowest Number: " + getMin(numbers));
        System.out.println("Sum: " + getSum(numbers));
        System.out.println("Average: " + getAverage(numbers));
    }

    private static int getCountEmptyString(List<String> strings) {
        return strings.stream()
                .filter(s -> s == null ||s.equals("")).
                toList().size();
    }

    private static int getCountLenth3(List<String> strings) {
        return strings.stream()
                .filter(Objects::nonNull)
                .filter(s -> s.length() == 3)
                .toList().size();
    }

    private static List<String> deleteEmptyStrings(List<String> strings) {
        return strings.stream().filter(Objects::nonNull)
                .filter(s -> !s.equals(""))
                .toList();
    }

    private static String getMergedString(List<String> strings, String separator) {
        return strings.stream()
                .filter(Objects::nonNull)
                .toList()
                .stream()
                .collect(Collectors.joining(separator));
        //.map(s -> s+=separator)
    }

    private static List<Integer> getSquares(List<Integer> numbers) {
        return numbers.stream()
                .map(x -> x * x)
                .toList();
    }

    private static int getMax(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .toList()
                .get(numbers.size() - 1);
    }

    private static int getMin(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .findFirst()
                .get();
    }

    private static int getSum(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, Integer::sum);
    }

    private static int getAverage(List<Integer> numbers) {
        return (int) numbers.stream()
                .mapToInt(Integer::intValue)
                .average().getAsDouble();
                //.reduce(0, Integer::sum)/ numbers.size();
    }
}
