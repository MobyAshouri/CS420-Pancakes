import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/* REFINED BY Moby Ashouri & Maymunah Hicks
 * ORIGINAL CODE BY Shouzab Khan & Eric Lim
 * 
 * Shortage of comments, added a few more to flesh out documentation.
 * 
 * Java required a single class in this instance.
 */

public class PancakeSortingSolution {

    public static void main(String[] args) {
        // take input for number of pancakes
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of pancakes in the stack (or 0 to exit): ");
        while (true) {
            int numPancakes = scanner.nextInt();
            if (numPancakes == 0) {
                break;
            }   // if it is 0 then break

            if (numPancakes <= 0) {
                System.out.println("Invalid input. Please enter a positive number of pancakes.");
                continue;
            }   // less than 0, reiterate

            int[] pancakeStack = new int[numPancakes];
            System.out.println("Enter the diameters of the pancakes (separated by spaces): ");

            for (int i = 0; i < numPancakes; i++) {
                pancakeStack[i] = scanner.nextInt();
            }       // take in integers from the scanner and set to values to the stack

            ArrayList<Integer> sortedFlips = pancakeSort(pancakeStack.clone());

            System.out.println("Original stack: " + Arrays.stream(pancakeStack)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" ")));     // print the original stack

            System.out.println("Sequence of flips to sort the stack:");
            for (int flip : sortedFlips) {
                System.out.print(flip + " ");
            }       // print the flips required to sort
            System.out.println("0"); // Ensure the final flip position is always included
        }

        scanner.close();
        System.out.println("Finished sorting all stacks.");
    }

    // Function to perform pancake sorting
    private static ArrayList<Integer> pancakeSort(int[] pancakeStack) {
        // PANCAKE SORT ALGORITHM
        ArrayList<Integer> flips = new ArrayList<>();
        for (int i = pancakeStack.length; i > 1; i--) {
            int maxIndex = findMaxDiameterIndex(pancakeStack, i);
            if (maxIndex != i - 1) {
                if (maxIndex != 0) {
                    flip(pancakeStack, maxIndex);
                    flips.add(pancakeStack.length - maxIndex);
                }
                flip(pancakeStack, i - 1);
                flips.add(pancakeStack.length - (i - 1));
            }
        }
        return flips;
    }

    // Function to flip the pancake stack at a given index
    private static void flip(int[] pancakeStack, int index) {
        // utilizes a temporary variable
        int start = 0;
        while (start < index) {
            int temp = pancakeStack[start];
            pancakeStack[start] = pancakeStack[index];
            pancakeStack[index] = temp;
            start++;
            index--;
        }
    }

    // Function to find the index of the pancake with the maximum diameter
    private static int findMaxDiameterIndex(int[] pancakeStack, int n) {
        int maxIndex = 0;
        for (int i = 1; i < n; i++) {
            if (pancakeStack[i] > pancakeStack[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}