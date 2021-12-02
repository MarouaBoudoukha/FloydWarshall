package model;

/**
 * Representation of an edge between 2 vertices
 */
public class Edge {
    private final int source, destination,weight;

    Edge(final int source, final int destination, final int weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public int getDestination() {  return destination;}

    public int getSource() {return source;}

    public int getWeight() {return weight;}
}
