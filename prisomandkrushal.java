import java.util.*;

// Class to represent a pair of node and weight for the adjacency list and priority queue
class Pair {
    int node, weight;
    
    public Pair(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}

// Class to represent a graph
class Graph {
    int V; // Number of vertices in the graph
    List<List<Pair>> adjList; // Adjacency list to store the graph

    public Graph(int V) {
        this.V = V;
        adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>()); // Initialize adjacency lists for each vertex
        }
    }

    // Method to add an edge to the graph (undirected)
    public void addEdge(int src, int dest, int weight) {
        adjList.get(src).add(new Pair(dest, weight)); // Add edge from src to dest
        adjList.get(dest).add(new Pair(src, weight)); // Add edge from dest to src
    }

    // Prim's algorithm to find the Minimum Spanning Tree (MST)
    public void primMST() {
        int[] distance = new int[V];  // Stores the minimum weight to connect to the MST
        boolean[] visited = new boolean[V]; // Tracks visited nodes
        int[] parent = new int[V]; // Stores the parent of each node in the MST
        
        // Initialize all distances as infinite and visited as false
        for (int i = 0; i < V; i++) {
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        // Priority queue to pick the minimum weight edge
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));

        distance[0] = 0; // Start from node 0 with weight 0
        pq.add(new Pair(0, 0));
        parent[0] = -1; // Root of the MST has no parent

        while (!pq.isEmpty()) {
            Pair current = pq.remove(); // Get the node with the smallest weight
            int u = current.node;
            visited[u] = true; // Mark the node as visited

            // Check all neighbors of the current node
            for (Pair neighbor : adjList.get(u)) {
                int v = neighbor.node; // Neighbor node
                int weight = neighbor.weight; // Weight of the edge to the neighbor

                // Update distance if a smaller weight edge is found, and the node is not visited
                if (!visited[v] && weight < distance[v]) {
                    distance[v] = weight;
                    pq.add(new Pair(v, distance[v])); // Add the updated distance to the queue
                    parent[v] = u; // Set the parent of the neighbor
                }
            }
        }

        // Print the resulting MST
        printMST(parent, distance);
    }

    // Method to print the MST and its total weight
    public void printMST(int[] parent, int[] distance) {
        System.out.println("MST Graph -");
        System.out.println("Edge \tWeight");
        int totalWeight = 0;
        for (int i = 1; i < V; i++) { // Start from 1 as 0 is the root
            System.out.println(parent[i] + " - " + i + "\t" + distance[i]); // Edge and its weight
            totalWeight += distance[i]; // Accumulate the total weight of the MST
        }
        System.out.println("Total weight of MST: " + totalWeight);
    }
}

public class prisomandkrushal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of nodes
        System.out.print("Enter the number of nodes: ");
        int nodes = scanner.nextInt();

        // Create a graph with the specified number of nodes
        Graph graph = new Graph(nodes);

        // Input number of edges
        System.out.print("Enter the number of edges: ");
        int edgesCount = scanner.nextInt();

        // Input edge details
        System.out.println("Enter each edge in the format: src dest weight");
        for (int i = 0; i < edgesCount; i++) {
            int src = scanner.nextInt(); // Source node
            int dest = scanner.nextInt(); // Destination node
            int weight = scanner.nextInt(); // Weight of the edge
            graph.addEdge(src, dest, weight); // Add the edge to the graph
        }

        // Find and print the MST using Prim's algorithm
        graph.primMST();
        scanner.close(); // Close the scanner to free resources
    }
}
