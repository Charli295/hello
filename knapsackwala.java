import java.util.*;

public class knapsackwala {

    public static double calculateKnapsackValue(int[] val, int[] weight, int W) {
        int n = val.length;

        double[][] ratio = new double[n][2];
        //0th col -> idx    1st col -> ratio

        for (int i = 0; i < n; i++) {
            ratio[i][0] = i; 
            ratio[i][1] = val[i] / (double) weight[i]; 
        }

        // Ascending order
        Arrays.sort(ratio, Comparator.comparingDouble(o -> o[1]));

        int capacity = W;
        double finalVal = 0;

        // Greedy approach 
        for (int i = ratio.length - 1; i >= 0; i--) {                       //Desending order
            int idx = (int) ratio[i][0]; 

            if (capacity >= weight[idx]) {
                // Include full item
                capacity -= weight[idx];
                finalVal += val[idx];
            } else {
                // Fractional part
                finalVal += (ratio[i][1] * capacity);
                break; 
            }
        }

        return finalVal; 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        int[] val = new int[n];
        int[] weight = new int[n];

        System.out.println("Enter the value and weight of each item:");
        for (int i = 0; i < n; i++) {
            System.out.print("Value of item " + (i + 1) + ": ");
            val[i] = scanner.nextInt();
            System.out.print("Weight of item " + (i + 1) + ": ");
            weight[i] = scanner.nextInt();
        }

        System.out.print("Enter the knapsack capacity: ");
        int W = scanner.nextInt();


        double finalVal = calculateKnapsackValue(val, weight, W);


        System.out.println("Final Value: " + finalVal);

        scanner.close();
    }

    
}
