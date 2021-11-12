package model;

public abstract class GraphDisplayer {

    public static void displayShorterPaths(Graph graph){
        int graphSize = graph.getSize();
        System.out.println("Shorter Paths for each summit : ");
        for(int i = 0 ; i < graphSize ; ++i) {
            for (int j = 0; j < graphSize; ++j)
                if(graph.hasLink(i, j))
                    if(j != graphSize - 1)
                        System.out.print(j + " -> " + i + " = " + graph.getValueAt(i, j) + "\t\t");
                    else
                        System.out.print(j + " -> " + i + " = " + graph.getValueAt(i, j));
            System.out.println();
        }
    }
}
