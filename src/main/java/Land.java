public class Land {
    int[][] matrix;
    Land(int[][] m) {
        this.matrix = new int[m.length][m[0].length];
        for(int i = 0; i < m.length; i++)
            for(int j = 0; j < m[0].length; j++)
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