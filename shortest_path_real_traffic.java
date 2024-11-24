import java.util.*;

class Node {
    public int node;
    public int weight;

    public Node(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}

class Graph {
    public int vertices;
    public List<List<Node>> adjacencyList;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest, int weight) {
        adjacencyList.get(src).add(new Node(dest, weight));
        adjacencyList.get(dest).add(new Node(src, weight)); 
    }

    public void shortestPath(int source, int destination) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        
        int[] distances = new int[vertices];
        int[] parent = new int[vertices]; 

        for (int i = 0; i < vertices; i++) {
            distances[i] = Integer.MAX_VALUE; 
            parent[i] = -1; 
        }
        distances[source] = 0;

        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.remove();
            int u = current.node;

            if (u == destination) {
                System.out.println("Shortest distance from " + source + " to " + destination + " is: " + distances[destination]);
                printPath(source, destination, parent);
                return;
            }

            for (int i = 0; i < adjacencyList.get(u).size(); i++) {
                Node neighbor = adjacencyList.get(u).get(i);
                int newDist = distances[u] + neighbor.weight;
                
                if (newDist < distances[neighbor.node]) {   // dist[u]+wt < dist[v]
                    distances[neighbor.node] = newDist;
                    parent[neighbor.node] = u; 
                    pq.add(new Node(neighbor.node, newDist));
                }
            }
        }
        System.out.println("No path exists from " + source + " to " + destination);
    }


    public void printPath(int source, int destination, int[] parent) {
        //Base case
        if (destination == source) {
            System.out.print(source);  
            return;
        }
    
        printPath(source, parent[destination], parent);  
        System.out.print(" -> " + destination); 
    }
    
}

public class shortest_path_real_traffic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int edgesCount = scanner.nextInt();

        Graph graph = new Graph(vertices);

        System.out.println("Enter each edge in the format: src dest weight");
        for (int i = 0; i < edgesCount; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(src, dest, weight);
        }

        System.out.print("Enter the source vertex: ");
        int source = scanner.nextInt();

        System.out.print("Enter the destination vertex: ");
        int destination = scanner.nextInt();

        graph.shortestPath(source, destination);
        scanner.close();
    }
}


