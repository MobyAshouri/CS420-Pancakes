import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/* REFINED BY Moby Ashouri & Maymunah Hicks
 * ORIGINAL CODE BY Muhammad Sheri & Ali Kesserwani
 * 
 * There was a lack of comments, but variables were named well enough to
 * be able to figure out what was happening easily. Functions were easy
 * to follow.
 * 
 * A single class was used because of Java syntax requirements. The program
 * was separated into an appropriate number of functions.
 */

public class PancakeSort {
    public static void main(String[] args) {
        // Take text input from the keyboard directly
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the stacks of pancakes followed by an empty line to stop:");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) { // Check for empty line to stop input
                break;
            }
            // Create an array of integers from the line called pancakes, split at whitespaces
            int[] pancakes = Arrays.stream(line.trim().split("\\s+"))
                                   .mapToInt(Integer::parseInt)
                                   .toArray();

            // Print out the formatted original pancake stacks
            System.out.println("Original stack: " + Arrays.stream(pancakes)
                                                          .mapToObj(String::valueOf)
                                                          .collect(Collectors.joining(" ")));

            pancakeSort(pancakes);  // sort using the pancake sort algorithm
            System.out.println("0"); // Indicate the end of the sequence for this stack
        }

        // close the scanner and terminate the program
        scanner.close();
        System.out.println("Finished sorting all stacks.");
    }

    private static void pancakeSort(int[] pancakes) {
        for (int i = pancakes.length; i > 1; i--) {
            int maxIndex = findMaxIndex(pancakes, i);
            if (maxIndex != i - 1) {                                    // if the max index is not equal to 1 less than i
                if (maxIndex != 0) {                                        // and if max index is not zero...
                    flip(pancakes, maxIndex);                                   // flip the pancake at the maxIndex
                    System.out.print((pancakes.length - maxIndex) + " ");       // output the value of the flipped index relative to the right side of the pancake (array)
                }
                flip(pancakes, i - 1);                                      // then flip the pancake array at i-1
                System.out.print((pancakes.length - (i - 1)) + " ");        // output the value of the flipped index relative to the right side of the pancake (array)
            }
        }
    }

    private static void flip(int[] pancakes, int index) {
        int start = 0;
        while (start < index) {
            int temp = pancakes[start];             // define a temporary variable to hold elements of pancakes from the left of the array
            pancakes[start] = pancakes[index];      // swap the left side's element of the array with the right side's element of the array
            pancakes[index] = temp;                 // set the right side's element of the array to the stored temporary variable (completing the swap)
            start++;
            index--;                                // increment start and decrement index by 1.
        }
    }

    private static int findMaxIndex(int[] pancakes, int n) {
        // A simple function to search for the largest element within the pancake
        int maxIndex = 0;
        for (int i = 1; i < n; i++) {
            if (pancakes[i] > pancakes[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}