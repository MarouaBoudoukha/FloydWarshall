import exceptions.AbsorbentException;
import exceptions.ExitException;
import exceptions.FileException;
import model.*;

import java.util.Scanner;

/**
 * Main class in which to start and enroll the program by offering the user's input choices.
 */
public class FloydWarshallProject {
    private static final String[] GRAPHS_NAMES = {"test.txt", "test2.txt", "test3.txt", "test4.txt", "testAbsorbent.txt"};
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            System.out.println("Welcome to the Floyd-Warshall automatic solver !");
            String graphName;
            while ((graphName = graphChoiceScanner()) != null) {
                try {
                    Graph graph = GraphCreator.createFromFile(FloydWarshallProject.class.getClassLoader().getResourceAsStream(graphName));
                    System.out.println(graph);
                    System.out.println("---------------Floyd Warshall-------------");
                    Graph shorterPath = FloydWarshall.calculateShorterPath(graph);
                    GraphDisplayer.displayShorterPaths(graph);
                } catch (AbsorbentException e) {
                    System.err.println("Cannot calculate shorter paths because there is a absorbent path in the graph!");
                } catch (FileException e) {
                    System.err.println("Error reading file : \n" + e.getMessage());
                }
            }
        } catch (ExitException ignored) {
        }
    }

    /**
     * Scans the user's input choice between the graphs or exiting the program by checking the input text
     *
     * @return Users graph choice
     * @throws ExitException Whether the user has chosen to exit and stop the program or not
     */
    private static String graphChoiceScanner() throws ExitException {
        System.out.println("Please choose a graph from those available (1, etc...) or exit :");
        displayGraphChoices();
        String choice = scanner.nextLine();
        int nbChoice;
        while (!choice.matches("\\d+") || (nbChoice = Integer.parseInt(choice)) <= 0 || nbChoice > (GRAPHS_NAMES.length + 1)) {
            System.out.println("Invalid choice !\n\nPlease choose again :");
            displayGraphChoices();
            choice = scanner.nextLine();
        }
        if (nbChoice == GRAPHS_NAMES.length + 1) throw new ExitException();
        return GRAPHS_NAMES[nbChoice - 1];
    }

    /**
     * Displays the graph choices in the terminal
     */
    private static void displayGraphChoices() {
        for (int i = 1; i <= GRAPHS_NAMES.length; i++) {
            String[] splitName = GRAPHS_NAMES[i - 1].split("\\.");
            String fileName = splitName[0] != null ? splitName[0] : GRAPHS_NAMES[i - 1];
            System.out.println(i + ") " + fileName);
        }
        System.out.println((GRAPHS_NAMES.length + 1) + ") Exit");
    }
}