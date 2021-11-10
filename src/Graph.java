public class Graph {

    private Link [][] matrix;
    private int size;

    public Graph(Link [][] matrix){
        this.matrix = matrix;

    }
    public Graph(int size){
        this.matrix = new Link[size][size];
    }
    public Graph(Graph graph){
        this(graph.matrix);
    }

    public int getValueAt(int x, int y){
        if((x>=0)&&(y>=0)&&(x< matrix.length)&&(y< matrix.length)){

            return matrix[x][y].getValue();
        }
        else{// les exceptions
            throw new IllegalArgumentException(" index not correct Try again");
        }

    }

    public boolean hasLink(int x, int y) {
        if ((x >= 0) && (y >= 0) && (x < matrix.length) && (y < matrix.length)) {
            if (matrix[x][y].exists() == true) {
                return true;

            }
        }
        return false;

    }
    public void setValueAt(int x, int y, int value){
        if ((x >= 0) && (y >= 0) && (x < matrix.length) && (y < matrix.length)) {
            matrix[x][y].setValue(value);


        }
        else{
            throw new IllegalArgumentException("index not correct");
        }
    }

    @Override
    public String toString(){

        String result="   ";
        for(int i=0; i< matrix.length; i++){
            result = result.concat(i + "\t") ;

        }
        result = result.concat("\n   ");

        for(int i=0; i< matrix.length; i++){
            result = result.concat("-\t") ;
        }
        result = result.concat("\n   ");



        for (int i=0; i< matrix.length; i++ ){

            result = result.concat(i+ " |");
            for(int j=0;j< matrix.length; j++){
                result = result.concat(matrix[i][j].toString()+ "\t");
            }
            result = result.concat("\n");
        }
        return result;
    }



}
