package model;

import exceptions.AbsorbentException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (hasAbsorbentPath(graph))
            throw new AbsorbentException("Absorbent path");
        int graphSize = graph.getSize();
        Graph shorterPathGraph = new Graph(graph);
        for (int k = 0; k < graphSize; ++k) {
            for (int i = 0; i < graphSize; ++i) {
                for (int j = 0; j < graphSize; ++j) {
                    if (i != j) {
                        if (shorterPathGraph.hasLink(i, k) && shorterPathGraph.hasLink(k, j)) {
                            int value = shorterPathGraph.getValueAt(i, k) + shorterPathGraph.getValueAt(k, j);
                            if (!shorterPathGraph.hasLink(i, j) || value < shorterPathGraph.getValueAt(i, j))
                                shorterPathGraph.setValueAt(i, j, value);
                        }
                    } else shorterPathGraph.setValueAt(i, j, 0);
                }
            }
        }
        System.out.println(shorterPathGraph);
        return shorterPathGraph;
    }

    /**
     * Checks with there is any absorbent path in a graph according to the * algorithm
     *
     * @param graph Graph from which to check the absorbent paths
     * @return Whether or not there is an absorbent path in the graph
     */
    private static boolean hasAbsorbentPath(Graph graph) {
        int cpt = 0;
        Map<Integer, Integer> shorterPaths = new HashMap<>();
        List<Integer> modifiedSummits = new ArrayList<>();
        modifiedSummits.add(0);
        shorterPaths.put(0, 0);
        while (cpt < graph.getSize()) {
            List<Integer> temp = new ArrayList<>(modifiedSummits);
            for (Integer summit : temp) {
                for (Integer successor : graph.getSuccessors(summit)) {
                    if (!shorterPaths.containsKey(successor)) {
                        shorterPaths.put(successor, graph.getValueAt(summit, successor));
                        modifiedSummits.add(successor);
                    }
                    else if(graph.getValueAt(summit, successor) < shorterPaths.get(successor)){
                            shorterPaths.put(successor, graph.getValueAt(summit, successor));
                            modifiedSummits.add(successor);
                    }
                }
                modifiedSummits.remove(summit);
            }
            if(modifiedSummits.isEmpty()) return false;
            ++cpt;
        }
        return true;
    }
}