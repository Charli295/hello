import java.util.Scanner;

public class mergewala {

    // Merge function to combine two sorted arrays into a single sorted array
    public static void merge(int[] credits, int[] left, int[] right) {
        int i = 0, j = 0, k = 0; // Pointers for left, right, and merged array

        // Merge elements from left and right arrays into the original array
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) { // Pick the smaller element
                credits[k++] = left[i++];
            } else {
                credits[k++] = right[j++];
            }
        }

        // Copy remaining elements from the left array (if any)
        while (i < left.length) {
            credits[k++] = left[i++];
        }

        // Copy remaining elements from the right array (if any)
        while (j < right.length) {
            credits[k++] = right[j++];
        }
    }

    // Merge sort function to sort an array of integers
    public static void mergeSort(int[] credits) {
        if (credits.length < 2) {
            return; // Base case: array of size 1 is already sorted
        }

        // Divide the array into two halves
        int mid = credits.length / 2;
        int[] left = new int[mid];
        int[] right = new int[credits.length - mid];

        // Copy elements to the left and right arrays
        for (int i = 0; i < mid; i++) {
            left[i] = credits[i];
        }
        for (int i = mid; i < credits.length; i++) {
            right[i - mid] = credits[i];
        }

        // Recursively sort the left and right halves
        mergeSort(left);
        mergeSort(right);

        // Merge the sorted halves
        merge(credits, left, right);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: number of students
        System.out.print("Enter the number of students: ");
        int n = scanner.nextInt();

        int[] credits = new int[n]; // Array to store credits for each student

        // Input: credits obtained by each student
        System.out.println("Enter the credits obtained by each student: ");
        for (int i = 0; i < n; i++) {
            credits[i] = scanner.nextInt();
        }

        // Sort the credits array using merge sort
        mergeSort(credits);

        // Output: sorted credits
        System.out.println("Sorted credits: ");
        for (int credit : credits) {
            System.out.print(credit + " ");
        }

        scanner.close(); // Close the scanner to free resources
    }
}
