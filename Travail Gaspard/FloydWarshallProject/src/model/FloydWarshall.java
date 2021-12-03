package model;

import exceptions.AbsorbentException;

/**
 * Enrolls the entire FloydWarshall on a Graph to find the shorter path between any two summits of this graph.
 */
public abstract class FloydWarshall {

    /**
     * Calculates the shorter path between each two summits in a graph according to the algorithm
     *
     * @param graph Graph from which to calculate the paths
     * @throws IllegalArgumentException Whether or not there is an absorbent path in the graph
     */
    public static Graph calculateShorterPath(Graph graph) throws IllegalArgumentException, AbsorbentException {
        for (int i = 0; i < graph.getSize(); ++i)
            if (hasAbsorbingCircuit(graph, i))
                throw new AbsorbentException("Absorbent path");
        int graphSize = graph.getSize();
        Graph shorterPathGraph = new Graph(graph);
        for (int k = 0; k < graphSize; ++k)
            for (int i = 0; i < graphSize; ++i)
                for (int j = 0; j < graphSize; ++j)
                    if (i != j) {
                        if (shorterPathGraph.hasLink(i, k) && shorterPathGraph.hasLink(k, j)) {
                            int value = shorterPathGraph.getValueAt(i, k) + shorterPathGraph.getValueAt(k, j);
                            if (!shorterPathGraph.hasLink(i, j) || value < shorterPathGraph.getValueAt(i, j))
                                shorterPathGraph.setValueAt(i, j, value);
                        }
                    } else shorterPathGraph.setValueAt(i, j, 0);
        return shorterPathGraph;
    }

    /**
     * Executes Bellman-Ford algorithm on the input graph taking source as starting vertex
     * Detects the presence or not of a negative-weight path in the graph
     *
     * @param graph  Graph on which to carryout Bellman-Ford algorithm
     * @param source starting vertex to engage relaxation of edges
     * @return false if no negative-weight path found, true otherwise
     */
    private static boolean hasAbsorbingCircuit(Graph graph, int source) {
        int numberOfVertices = graph.getSize();
        int numberOfEdges = graph.getEdges().size();
        int[] dist = new int[numberOfVertices]; //distance from source

        // Step 1: Initialize distances from src to all other
        // vertices as INFINITE except distance of source set to 0
        for (int i = 0; i < numberOfVertices; ++i) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[source] = 0;

        // Step 2: Relax all edges |numberOfVertices| - 1 times. A simple
        // shortest path from source to any other vertex can
        // have at-most |numberOfVertices| - 1 edges
        for (int i = 1; i < numberOfVertices; ++i) {
            for (int j = 0; j < numberOfEdges; ++j) {
                Edge edge = graph.getEdges().get(j);
                int u = edge.getSource();
                int v = edge.getDestination();
                int weight = edge.getWeight();

                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }

        // Step 3: check for negative-weight cycles. The above
        // step guarantees shortest distances if graph doesn't
        // contain negative weight cycle. If we get a shorter
        // path, then there is a cycle.
        for (int j = 0; j < numberOfEdges; ++j) {
            Edge edge = graph.getEdges().get(j);
            int u = edge.getSource();
            int v = edge.getDestination();
            int weight = edge.getWeight();

            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                return true;
            }
        }

        return false;

    }
}