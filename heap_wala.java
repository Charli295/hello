import java.util.*;

public class heap_wala {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
       
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        System.out.print("Enter the number of students: ");
        int n = scanner.nextInt();

        System.out.println("Enter the marks obtained by each student:");
        for (int i = 0; i < n; i++) {
            int marks = scanner.nextInt();
            maxHeap.add(marks); // Add to max-heap
            minHeap.add(marks); // Add to min-heap
        }

        int maxMarks = maxHeap.peek();
        int minMarks = minHeap.peek();

        System.out.println("Maximum marks obtained: " + maxMarks);
        System.out.println("Minimum marks obtained: " + minMarks);

        scanner.close();
    }
}
