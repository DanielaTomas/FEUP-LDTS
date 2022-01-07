public class Land {
    int[][] matrix;
    Land(int[][] m) {
        this.matrix = new int[60][30];
        for(int i = 0; i < 60; i++)
            for(int j = 0; j < 30; j++)
               this.matrix[i][j] = m[i][j];
    }
    public int[][] getMatrix() {
        return matrix;
    }
    public int getType(int i, int j){
       return matrix[i][j];
    }
    public void setType(int i, int j, int type) {
        this.matrix[i][j] = type;
    }
}