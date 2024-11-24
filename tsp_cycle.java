import java.util.Arrays;

public class tsp_cycle {
    // Number of cities
    static int N = 4;
    
    // Define the distance matrix
    static int[][] tsp = {
        {-1, 10, 15, 20},
        {10, -1, 35, 25},
        {15, 35, -1, 30},
        {20, 25, 30, -1}
    };

    // A method to calculate the minimum cost of the TSP cycle
    static int tspCycle(int currentCity, boolean[] visited, int count, int cost, int startCity) {
        // If all cities have been visited and we can return to the start city
        if (count == N && tsp[currentCity][startCity] != -1) {
            return cost + tsp[currentCity][startCity];
        }

        // Initialize minimum cost to a large value
        int minCost = Integer.MAX_VALUE;

        // Try to visit the remaining cities
        for (int city = 0; city < N; city++) {
            if (!visited[city] && tsp[currentCity][city] != -1) {
                visited[city] = true;
                int tempCost = tspCycle(city, visited, count + 1, cost + tsp[currentCity][city], startCity);
                minCost = Math.min(minCost, tempCost);
                visited[city] = false;
            }
        }
        return minCost;
    }

    public static void main(String[] args) {
        boolean[] visited = new boolean[N];
        Arrays.fill(visited, false);

        // Start from city 0
        visited[0] = true;
        int minCost = tspCycle(0, visited, 1, 0, 0);

        System.out.println("The minimum cost of the TSP cycle is: " + minCost);
    }
}
