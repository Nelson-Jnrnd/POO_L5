/**
 *
 */
public class Matrice {
    private int n;
    private int m;
    private int modulo;
    private int[] values;

    private static final Addition add = new Addition();
    private static final Substraction sub = new Substraction();
    private static final Multiply mul = new Multiply();

    // Matrix 1 by 1 are accepted
    private final int MIN_WIDTH_VALUE = 1;
    private final int MIN_HEIGHT_VALUE = 1;

    private final int MIN_MODULO_VALUE = 1;

    /**
     *
     * @param n
     * @param m
     * @param modulo
     * @param values
     * @throws RuntimeException
     */
    public Matrice(int n, int m, int modulo, int ... values) throws RuntimeException {

        // Initial checks
        // Throw error if width and length are smaller than expected
        if(n < MIN_WIDTH_VALUE && m < MIN_HEIGHT_VALUE)
            throw new RuntimeException("Invalid matrix width or height");
        // Throw error if modulo is smaller than expected
        if(modulo < MIN_MODULO_VALUE)
            throw new RuntimeException("Invalid modulus");
        // Throw error if values length is greater than it can actually fit in matrix
        if(values.length > n * m)
            throw new RuntimeException("Matrix is not large enough to handle values");


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

    /**
     *
     * @param m
     */
    public Matrice(Matrice m){
        this(m.n, m.m, m.modulo, m.values);
    }

    // Get Matrice state -------------------------------------------

    /**
     *
     * @return
     */
    public int getM(){
        return this.m;
    }

    /**
     *
     * @return
     */
    public int getN(){
        return this.n;
    }

    /**
     *
     * @param column
     * @return
     */
    public boolean isColumnInMatrice(int column){
        return (column >= 0 && column < n);
    }

    /**
     *
     * @param row
     * @return
     */
    public boolean isRowInMatrice(int row){
        return (row >= 0 && row < m);
    }

    /**
     *
     * @param row
     * @param column
     * @return
     */
    public boolean isInMatrice(int row, int column){
        return  isColumnInMatrice(column) && isRowInMatrice(row);
    }

    /**
     *
     * @param row
     * @param column
     * @return
     */
    public int getValueAtCoordinate(int row, int column) throws RuntimeException {
        if(!isInMatrice(row, column))
            throw new RuntimeException("out of bounds"); // TOOD préciser exception
        return values[(row) * n + column];
    }

    /**
     *
     * @param column
     * @return
     */
    public int[] getColumn(int column) throws RuntimeException {
        if(!isColumnInMatrice(column))
            throw new RuntimeException("out of bounds"); // TOOD préciser exception

        int[] columnToReturn = new int[m];
        for (int i = 0; i < m; i++) {
            columnToReturn[i] = getValueAtCoordinate(i, column);
        }
        return columnToReturn;
    }

    /**
     *
     * @param row
     * @return
     */
    public int[] getRow(int row) throws RuntimeException {
        if(!isRowInMatrice(row))
            throw new RuntimeException("out of bounds"); // TOOD préciser exception

        int[] rowToReturn = new int[n];
        for (int i = 0; i < n; i++) {
            rowToReturn[i] = getValueAtCoordinate(row, i);
        }
        return rowToReturn;
    }

    // Operations -------------------------------------------------------------

    /**
     *
     * @param row
     * @param column
     * @param value
     */
    public void setValueAtCoordinate(int row, int column, int value) throws RuntimeException {
        if(!isInMatrice(row, column))
            throw new RuntimeException("out of bounds");
        values[(row) * n + column] = Math.floorMod(value, modulo);
    }

    /**
     *
     * @param matrice
     * @param op
     * @return
     * @throws RuntimeException
     */
    public Matrice operation(Matrice matrice, Operation op) throws RuntimeException{
        if(this.modulo != matrice.modulo){
            throw new RuntimeException("You can not sum two matrix with different modulus");
        }

        Matrice result = new Matrice(Math.max(this.n, matrice.n), Math.max(this.m, matrice.m), matrice.modulo);
        for(int i = 0; i < result.m; ++i){
            for(int j = 0; j < result.n; ++j){
                // Check if the values can be added. Otherwise the value is 0
                int a = (this.isInMatrice(i, j) ? getValueAtCoordinate(i, j) : 0);
                int b = (matrice.isInMatrice(i, j) ? matrice.getValueAtCoordinate(i, j) : 0);
                result.setValueAtCoordinate(i, j, op.compute(a, b));
            }
        }
        return result;
    }

    /**
     *
     * @param matrice
     * @return
     */
    public Matrice add(Matrice matrice){
        return operation(matrice, add);
    }

    /**
     *
     * @param matrice
     * @return
     */
    public Matrice sub(Matrice matrice){
        return operation(matrice, sub);
    }

    /**
     *
     * @param matrice
     * @return
     */
    public Matrice multiply(Matrice matrice){
        return operation(matrice, mul);
    }

    /**
     *
     */
    public void fillMatriceWithRandomValues(){
        for(int i = 0; i < m; ++i){
            for(int j = 0; j < n; ++j){
                int randomValue = (int) (Math.random() * this.modulo);
                setValueAtCoordinate(i, j, randomValue);
            }
        }
    }

    /**
     *
     * @return
     */
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