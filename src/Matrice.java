import java.lang.reflect.Array;

public class Matrice {
    private int n;
    private int m;
    private int modulo;
    private int[] values;

    // Constructors-------------------------------------------------
    public Matrice(int n, int m, int modulo, int ... values) {
        if(values.length > n * m){
            throw new RuntimeException("Matrix is not large enough to handle values");
        }

        this.n = n;
        this.m = m;
        this.modulo = modulo;
        this.values = new int[n*m];

        // Sets given values in matrix
        if(values.length != 0) {
            System.arraycopy(values, 0, this.values, 0, values.length);
        }
        else{
            fillMatriceWithRandomValues();
        }
    }

    // Get Matrice state -------------------------------------------

    public int getM(){
        return this.m;
    }
    public int getN(){
        return this.n;
    }
    public boolean isColumnInMatrice(int column){
        return (column >= 0 && column < n);
    }
    public boolean isRowInMatrice(int row){
        return (row >= 0 && row < m);
    }
    public boolean isInMatrice(int row, int column){
        return  isColumnInMatrice(column) && isRowInMatrice(row);
    }
    public int getValueAtCoordinate(int row, int column) {
        if(!isInMatrice(row, column))
            throw new RuntimeException("out of bounds"); // TOOD préciser exception
        return values[(row) * n + column];
    }
    public int[] getColumn(int column){
        if(!isColumnInMatrice(column))
            throw new RuntimeException("out of bounds"); // TOOD préciser exception

        int[] columnToReturn = new int[m];
        for (int i = 0; i < m; i++) {
            columnToReturn[i] = getValueAtCoordinate(i, column);
        }
        return columnToReturn;
    }
    public int[] getRow(int row){
        if(!isRowInMatrice(row))
            throw new RuntimeException("out of bounds"); // TOOD préciser exception

        int[] rowToReturn = new int[n];
        for (int i = 0; i < n; i++) {
            rowToReturn[i] = getValueAtCoordinate(row, i);
        }
        return rowToReturn;
    }

    // Operations -------------------------------------------------------------
    public void setValueAtCoordinate(int row, int column, int value){
        if(!isInMatrice(row, column))
            throw new RuntimeException("out of bounds"); // TOOD préciser exception
        values[(row) * n + column] = value % modulo;
    }

    public Matrice add(Matrice matrice){
        if(this.modulo != matrice.modulo){
            throw new RuntimeException("You can not add two matrix with different modulus");
        }

        Matrice result = new Matrice(Math.max(this.n, matrice.n), Math.max(this.m, matrice.m), matrice.modulo);
        for(int i = 0; i < Math.min(this.m, matrice.m); ++i){
            for(int j = 0; j < Math.min(this.n, matrice.n); ++j){
                // Check if the values can be added. Otherwise the value is 0
                if(this.isInMatrice(i, j) && matrice.isInMatrice(i, j)){
                    int a = getValueAtCoordinate(i, j), b = matrice.getValueAtCoordinate(i, j);
                    result.setValueAtCoordinate(i, j, a + b);
                }
                else{
                    result.setValueAtCoordinate(i, j, 0);
                }
            }
        }
        return result;
    }

    public Matrice sub(Matrice matrice){
        if(this.modulo != matrice.modulo){
            throw new RuntimeException("You can not substract two matrix with different modulus");
        }

        Matrice result = new Matrice(Math.max(this.n, matrice.n), Math.max(this.m, matrice.m), matrice.modulo);
        for(int i = 0; i < Math.min(this.m, matrice.m); ++i){
            for(int j = 0; j < Math.min(this.n, matrice.n); ++j){
                // Check if the values can be added. Otherwise the value is 0
                if(this.isInMatrice(i, j) && matrice.isInMatrice(i, j)){
                    int a = getValueAtCoordinate(i, j), b = matrice.getValueAtCoordinate(i, j);
                    result.setValueAtCoordinate(i, j, a - b);
                }
                else{
                    result.setValueAtCoordinate(i, j, 0);
                }
            }
        }
        return result;
    }

    public Matrice sum(Matrice matrice){
        if(this.modulo != matrice.modulo){
            throw new RuntimeException("You can not sum two matrix with different modulus");
        }

        Matrice result = new Matrice(Math.max(this.n, matrice.n), Math.max(this.m, matrice.m), matrice.modulo);
        for(int i = 0; i < Math.min(this.m, matrice.m); ++i){
            for(int j = 0; j < Math.min(this.n, matrice.n); ++j){
                // Check if the values can be added. Otherwise the value is 0
                if(this.isInMatrice(i, j) && matrice.isInMatrice(i, j)){
                    int a = getValueAtCoordinate(i, j), b = matrice.getValueAtCoordinate(i, j);
                    result.setValueAtCoordinate(i, j, a * b % result.modulo);
                }
                else{
                    result.setValueAtCoordinate(i, j, 0);
                }
            }
        }
        return result;
    }

   /* public Matrice getTransposee(){
        int[] transpose = new int[n*m];
        for (int in = 0; in < n; in++) {
            for(int im = 0; im < m; im++){
                //transpose[in * n + im] =
            }
        }
    }*/
    // TODO : est-ce que on utilise des index à 0 ou 1 ?
    public void fillMatriceWithRandomValues(){
        for(int i = 0; i < m; ++i){
            for(int j = 0; j < n; ++j){
                int randomValue = (int) (Math.random() * this.modulo);
                setValueAtCoordinate(i, j, randomValue);
            }
        }
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < m; ++i){
            for(int j = 0; j < n; ++j){
                s.append(getValueAtCoordinate(i, j));
                if(j != n-1)
                    s.append('-');
            }
            s.append('\n');
        }
        return s.toString();
    }
}
