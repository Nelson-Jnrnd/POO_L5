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
            for(int i = 0; i < values.length; ++i){
                this.values[i] = values[i];
            }
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
        return (column > 0 && column <= n);
    }
    public boolean isRowInMatrice(int row){
        return (row > 0 && row <= m);
    }
    public boolean isInMatrice(int column, int row){
        return  isColumnInMatrice(column) && isRowInMatrice(row);
    }
    public int getValueAtCoordinate(int column, int row) {
        if(!isInMatrice(column, row))
            throw new RuntimeException("out of bounds"); // TOOD préciser exception
        return values[(row-1) * n + column-1];
    }
    public int[] getColumn(int column){
        if(!isColumnInMatrice(column))
            throw new RuntimeException("out of bounds"); // TOOD préciser exception

        int[] columnToReturn = new int[m];
        for (int i = 0; i < m; i++) {
            columnToReturn[i] = getValueAtCoordinate(column, i);
        }
        return columnToReturn;
    }
    public int[] getRow(int row){
        if(!isRowInMatrice(row))
            throw new RuntimeException("out of bounds"); // TOOD préciser exception

        int[] rowToReturn = new int[n];
        for (int i = 0; i < n; i++) {
            rowToReturn[i] = getValueAtCoordinate(i, row);
        }
        return rowToReturn;
    }

    // Operations -------------------------------------------------------------
    public void setValueAtCoordinate(int column, int row, int value){
        if(!isInMatrice(column, row))
            throw new RuntimeException("out of bounds"); // TOOD préciser exception
        values[(row-1) * n + column-1] = value % modulo;
    }

    public Matrice add(Matrice matrice){
        if(this.modulo != matrice.modulo){
            throw new RuntimeException("You can not add two matrix with different modulus");
        }

        Matrice result = new Matrice(Math.max(this.m, matrice.m), Math.max(this.n, matrice.n), matrice.modulo);
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

        Matrice result = new Matrice(Math.max(this.m, matrice.m), Math.max(this.n, matrice.n), matrice.modulo);
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

        Matrice result = new Matrice(Math.max(this.m, matrice.m), Math.max(this.n, matrice.n), matrice.modulo);
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

    public Matrice getTransposee(){
        int[] transpose = new int[n*m];
        for (int in = 0; in < n; in++) {
            for(int im = 0; im < m; im++){
                //transpose[in * n + im] =
            }
        }
    }
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
            }
        }
        return s.toString();
    }
}
