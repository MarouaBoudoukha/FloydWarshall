package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A Graph object to represent a certain graph by a two dimension value array according to the links between each summit.
 */
public class Graph {
    private Link[][] matrix;
    private final int size;

    public Graph(Link[][] matrix) {
        this.matrix = matrix;
        this.size = matrix.length;
    }

    public Graph(int matrixSize) {
        this.matrix = new Link[matrixSize][matrixSize];
        this.size = matrixSize;
    }

    public Graph(Graph graph) {
        this(Arrays.stream(graph.getMatrix()).map(Link[]::clone).toArray(Link[][]::new));
    }

    public Link[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Link[][] matrix) {
        this.matrix = matrix;
    }

    public int getSize() {
        return size;
    }

    /**
     * Returns the value of a link between two summits with this link exists in the matrix
     *
     * @param x Row index (origin summit)
     * @param y Column index (destination summit)
     * @return Link's value
     */
    public int getValueAt(int x, int y) {
        if (x < 0 || y < 0 || x >= size || y >= size)
            throw new IllegalArgumentException("Incorrect index : " + x + "," + y);
        if (!hasLink(x, y))
            throw new IllegalArgumentException("No link for this index");
        return matrix[x][y].getValue();
    }

    /**
     * Checks whether or not there is a link between two summits in the matrix.
     *
     * @param x Row index (origin summit)
     * @param y Column index (destination summit)
     * @return If the link exists
     */
    public boolean hasLink(int x, int y) {
        if (x < 0 || y < 0 || x >= size || y >= size)
            throw new IllegalArgumentException("Incorrect index : " + x + "," + y);
        return matrix[x][y] != null && matrix[x][y].exists();
    }

    /**
     * Sets a link value between two summits in the matrix.
     *
     * @param x     Row index (origin summit)
     * @param y     Column index (destination summit)
     * @param value New link value to set
     */
    public void setValueAt(int x, int y, int value) {
        if (x < 0 || y < 0 || x >= size || y >= size)
            throw new IllegalArgumentException("Incorrect index : " + x + "," + y);
        if (matrix[x][y] == null)
            matrix[x][y] = new Link(value);
        else matrix[x][y].setValue(value);
    }

    public List<Integer> getSuccessors(int x) {
        List<Integer> successors = new ArrayList<>();
        if (x < 0 || x >= size)
            throw new IllegalArgumentException("Incorrect index : " + x);
        for (int i = 0; i < size; ++i)
            if (matrix[x][i] != null && matrix[x][i].exists())
                successors.add(i);
        return successors;
    }

    @Override
    public String toString() {
        return writeMatrix();
    }

    /**
     * Creates a text representation of a graph by its matrix and the link values between the summits.
     *
     * @return Graph's text representation
     */
    private String writeMatrix() {
        String result = writeBeginning();
        for (int i = 0; i < size; i++) {
            if (i < 10)
                result = result.concat(String.valueOf(i).concat(" |\t"));
            else
                result = result.concat(String.valueOf(i).concat("|\t"));
            for (int j = 0; j < size; j++) {
                String value = matrix[i][j] == null ? "-" : String.valueOf(matrix[i][j]);
                result = result.concat(value).concat("\t");
            }
            result = result.concat("\n");
        }
        return result.concat("\n");
    }

    /**
     * Creates the first, second and third lines of the graph's text representation
     *
     * @return Matrix text beginning
     */
    private String writeBeginning() {
        String result = "Matrix : \n  \t";
        for (int i = 0; i < size; i++)
            result = result.concat(String.valueOf(i).concat("\t"));
        result = result.concat("\n  \t");
        for (int i = 0; i < size; i++)
            result = result.concat("- \t");
        return result.concat("\n");
    }
}
