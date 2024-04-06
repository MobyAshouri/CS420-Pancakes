import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PancakeSort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the stacks of pancakes followed by an empty line to stop:");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) { // Check for empty line to stop input
                break;
            }
            int[] pancakes = Arrays.stream(line.trim().split("\\s+"))
                                   .mapToInt(Integer::parseInt)
                                   .toArray();

            System.out.println("Original stack: " + Arrays.stream(pancakes)
                                                          .mapToObj(String::valueOf)
                                                          .collect(Collectors.joining(" ")));
            pancakeSort(pancakes);
            System.out.println("0"); // Indicate the end of the sequence for this stack
        }

        scanner.close();
        System.out.println("Finished sorting all stacks.");
    }

    private static void pancakeSort(int[] pancakes) {
        for (int i = pancakes.length; i > 1; i--) {
            int maxIndex = findMaxIndex(pancakes, i);
            if (maxIndex != i - 1) {
                if (maxIndex != 0) {
                    flip(pancakes, maxIndex);
                    System.out.print((pancakes.length - maxIndex) + " ");
                }
                flip(pancakes, i - 1);
                System.out.print((pancakes.length - (i - 1)) + " ");
            }
        }
    }

    private static void flip(int[] pancakes, int index) {
        int start = 0;
        while (start < index) {
            int temp = pancakes[start];
            pancakes[start] = pancakes[index];
            pancakes[index] = temp;
            start++;
            index--;
        }
    }

    private static int findMaxIndex(int[] pancakes, int n) {
        int maxIndex = 0;
        for (int i = 1; i < n; i++) {
            if (pancakes[i] > pancakes[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}