import java.util.*;

// Class representing a Student with name and credits
class Student {
    String name; // Student's name
    int credits; // Credits earned by the student

    // Constructor to initialize a student object
    Student(String name, int credits) {
        this.name = name;
        this.credits = credits;
    }
}

public class quicksort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompting the user to enter the number of students
        System.out.print("Enter the number of students: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline

        // Array to hold student objects
        Student[] students = new Student[n];
        
        // Taking input for each student
        for (int i = 0; i < n; i++) {
            System.out.print("Enter name of student " + (i + 1) + ": ");
            String name = scanner.nextLine(); // Input for name
            System.out.print("Enter credits for " + name + ": ");
            int credits = scanner.nextInt(); // Input for credits
            scanner.nextLine(); // Consume the leftover newline
            students[i] = new Student(name, credits); // Create and store student object
        }

        // Sort students based on credits in descending order
        quickSort(students, 0, n - 1);

        // Display top 3 students based on credits
        System.out.println("Top 3 students based on credits:");
        for (int i = 0; i < Math.min(3, n); i++) { // Print only top 3 or less if fewer students
            System.out.println((i + 1) + ". " + students[i].name + " - " + students[i].credits + " credits");
        }

        scanner.close(); // Close the scanner to free resources
    }

    // QuickSort algorithm to sort students based on their credits
    public static void quickSort(Student[] students, int low, int high) {
        if (low < high) {
            // Partition the array and get the pivot index
            int pivotIndex = partition(students, low, high);
            // Recursively sort the elements before and after the pivot
            quickSort(students, low, pivotIndex - 1);
            quickSort(students, pivotIndex + 1, high);
        }
    }

    // Partition method to arrange elements around the pivot
    private static int partition(Student[] students, int low, int high) {
        int pivot = students[high].credits; // Select the last element's credits as the pivot
        int i = low - 1; // Index for the smaller element

        // Loop through the array to compare each element with the pivot
        for (int j = low; j < high; j++) {
            if (students[j].credits > pivot) { // For descending order
                i++; // Increment the index for the smaller element
                swap(students, i, j); // Swap the elements
            }
        }
        // Place the pivot element in its correct position
        swap(students, i + 1, high);
        return i + 1; // Return the index of the pivot
    }

    // Swap two elements in the array
    private static void swap(Student[] students, int i, int j) {
        Student temp = students[i]; // Temporary storage for swapping
        students[i] = students[j];
        students[j] = temp;
    }
}
