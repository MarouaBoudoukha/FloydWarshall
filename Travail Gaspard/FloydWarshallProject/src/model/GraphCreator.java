package model;

import exceptions.FileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates a Graph with a certain file input (.txt) by reading each line according to the format defined in the project
 */
public abstract class GraphCreator {

    public static Graph createFromFile(InputStream fileStream) throws FileException{
        int cpt = 0, size, nbArcs = 0;
        Link[][] matrix = null;
        List<Edge> edges = new ArrayList<Edge>();
        try {
            InputStreamReader streamReader = new InputStreamReader(fileStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            while ((line = reader.readLine()) != null){
                if(cpt == 0) {
                    if (!line.matches("^\\d+$"))
                        throw new FileException("First line doesn't match file pattern");
                    size = Integer.parseInt(line);
                    matrix = new Link[size][size];
                } else if(cpt == 1) {
                    if (!line.matches("^\\d+$"))
                        throw new FileException("Second line doesn't match file pattern");
                    nbArcs = Integer.parseInt(line);
                } else {
                    if (!line.matches("\\d+[\\s*-?\\d*]*") || (line.split("\\s+").length != 3))
                        throw new FileException("Line ".concat(String.valueOf(cpt + 1).concat(" doesn't match file pattern")));
                    String[] connections = line.split("\\s+");
                    matrix[Integer.parseInt(connections[0])][Integer.parseInt(connections[1])] = new Link(Integer.parseInt(connections[2]));

                    edges.add(new Edge(Integer.parseInt(connections[0]), Integer.parseInt(connections[1]), Integer.parseInt(connections[2])));
                }
                ++cpt;
            }
            if (cpt - 2 != nbArcs)
                throw new FileException("Incorrect arc amount");
        } catch (IOException  e) {
            throw new FileException("Cannot read file provided : \n" + e.getMessage());
        }
        Graph graph = new Graph(matrix);
        graph.setEdges(edges);

        return graph;
    }
}