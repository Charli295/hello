import java.util.*;

class Assign3B {
    private Map<String, List<Edge>> adjList;

    public Assign3B() {
        adjList = new HashMap<>();
    }

    public void addFlight(String cityA, String cityB, int cost) {
        adjList.putIfAbsent(cityA, new ArrayList<>());
        adjList.putIfAbsent(cityB, new ArrayList<>());
        
        adjList.get(cityA).add(new Edge(cityB, cost));
        adjList.get(cityB).add(new Edge(cityA, cost));
    }

    public boolean isConnected() {
        if (adjList.isEmpty()) {
            return true;
        }

        Set<String> visited = new HashSet<>();
        String startCity = adjList.keySet().iterator().next();
        bfs(startCity, visited);

        return visited.size() == adjList.size();
    }

    private void bfs(String startCity, Set<String> visited) {
        Queue<String> queue = new LinkedList<>();
        queue.add(startCity);
        visited.add(startCity);

        while (!queue.isEmpty()) {
            String currentCity = queue.poll();

            for (Edge edge : adjList.get(currentCity)) {
                if (!visited.contains(edge.destination)) {
                    visited.add(edge.destination);
                    queue.add(edge.destination);
                }
            }
        }
    }

    public void printGraph() {
        for (String city : adjList.keySet()) {
            System.out.print(city + " -> ");
            for (Edge edge : adjList.get(city)) {
                System.out.print(edge.destination + " (" + edge.cost + "), ");
            }
            System.out.println();
        }
    }

    private class Edge {
        String destination;
        int cost;

        public Edge(String destination, int cost) {
            this.destination = destination;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        Assign3B flightGraph = new Assign3B();
        
        flightGraph.addFlight("CityA", "CityB", 3);
        flightGraph.addFlight("CityA", "CityC", 5);
        flightGraph.addFlight("CityB", "CityC", 2);
        flightGraph.addFlight("CityC", "CityD", 4);

        flightGraph.printGraph();

        if (flightGraph.isConnected()) {
            System.out.println("The graph is connected.");
        } else {
            System.out.println("The graph is NOT connected.");
        }
    }
}
