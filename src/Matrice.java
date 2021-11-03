/**
 * Authors:     Alen Bijelic, Nelson Jeanrenaud
 * Date:        03.11.2021
 *
 * Description: Matrice allows the user to create a custom sized matrix. You have the choice to specifie values
 *              that will be filled in the matrix. Otherwise, matrix's values are randomly filled with values
 *              between 0 and modulus. Another way to create matrix is using the copy constructor.
 *              Some methods allow the user to perform mathematical operations on matrix.
 *
 * Comments:    Matrix with width and height under 1 can not be created. Same for modulus.
 */
public class Matrice {
    private int n;
    private int m;
    private int modulo;
    private int[] values;

    private static final Addition add = new Addition();
    private static final Substraction sub = new Substraction();
    private static final Multiply mul = new Multiply();

    private final int MIN_WIDTH_VALUE = 1;
    private final int MIN_HEIGHT_VALUE = 1;

    private final int MIN_MODULO_VALUE = 1;

    private final int DEFAULT_COORDINATE_VALUE = 0;

    /**
     * Matrice constructor
     * @param n Matrix width
     * @param m Matrix height
     * @param modulo Modulus which will define the max value of matrix values
     * @param values Fill matrix with these values (optional)
     * @throws RuntimeException Throw error if there is inconsistency with inbound data
     */
    public Matrice(int n, int m, int modulo, int... values) throws RuntimeException {

        // Initial checks
        // Throw error if width and length are smaller than expected
        if (n < MIN_WIDTH_VALUE || m < MIN_HEIGHT_VALUE)
            throw new RuntimeException("Invalid matrix width or height");
        // Throw error if modulo is smaller than expected
        if (modulo < MIN_MODULO_VALUE)
            throw new RuntimeException("Invalid modulus");
        // Throw error if values length is greater than it can actually fit in matrix
        if (values.length > n * m)
            throw new RuntimeException("Matrix is not large enough to handle values");

        this.n = n;
        this.m = m;
        this.modulo = modulo;
        this.values = new int[n*m];

        // Sets given values in matrix
        if (values.length != 0) {
            for (int i = 0; i < values.length; ++i) {
                this.values[i] = Math.floorMod(values[i], modulo);
            }
        } else {
            fillMatriceWithRandomValues();
        }
    }

    /**
     * Copy matrix constructor
     * @param m Matrice which attributes will be copied
     */
    public Matrice(Matrice m){
        this(m.n, m.m, m.modulo, m.values);
    }


    /**
     * Check if a column is in matrix
     * @param column Column to check
     * @return If the column is in the matrix
     */
    public boolean isColumnInMatrice(int column){
        return (column >= 0 && column < n);
    }

    /**
     * Check if a row is in matrix
     * @param row Row to check
     * @return If the row is in the matrix
     */
    public boolean isRowInMatrice(int row){
        return (row >= 0 && row < m);
    }

    /**
     * Check if a coordinate is in matrix
     * @param row Row id of the coordinate
     * @param column Column id of the coordinate
     * @return If the coordinate is in the matrix
     */
    public boolean isInMatrice(int row, int column){
        return  isColumnInMatrice(column) && isRowInMatrice(row);
    }

    /**
     * Get value of a coordinate
     * @param row Row id of the coordinate
     * @param column Column id of the coordinate
     * @return The value at the specific coordinate
     */
    public int getValueAtCoordinate(int row, int column) throws RuntimeException {
        if(!isInMatrice(row, column))
            throw new RuntimeException("out of bounds"); // TOOD préciser exception
        return values[(row) * n + column];
    }

    /**
     * Get all the values of the matrice
     * @return array with all the values of the matrix, from left to right, top to bottom
     */
    public int[] getValues(){
        return values;
    }
    // Operations -------------------------------------------------------------

    /**
     * Set value of a coordinate
     * @param row Row id of the coordinate
     * @param column Column id of the coordinate
     * @param value The value to be set at the specific coordinate
     * @throws RuntimeException Throw error if coordinate is not in the matrix
     */
    public void setValueAtCoordinate(int row, int column, int value) throws RuntimeException {
        if(!isInMatrice(row, column))
            throw new RuntimeException("out of bounds");
        values[(row) * n + column] = Math.floorMod(value, modulo);
    }

    /**
     * Compute mathematical operation between two matrix
     * @param matrice Matrix to compute the operation
     * @param op Operation type
     * @return Result of the operation
     * @throws RuntimeException Throws error if both modulus are different
     */
    public Matrice operation(Matrice matrice, Operation op) throws RuntimeException{
        if(this.modulo != matrice.modulo){
            throw new RuntimeException("You can not compute operation on two matrix with different modulus");
        }

        Matrice result = new Matrice(Math.max(this.n, matrice.n), Math.max(this.m, matrice.m), matrice.modulo);
        for(int i = 0; i < result.m; ++i){
            for(int j = 0; j < result.n; ++j){
                // Check if the values can be added. Otherwise, the value is set to default value.
                int a = (this.isInMatrice(i, j) ? getValueAtCoordinate(i, j) : DEFAULT_COORDINATE_VALUE);
                int b = (matrice.isInMatrice(i, j) ? matrice.getValueAtCoordinate(i, j) : DEFAULT_COORDINATE_VALUE);
                result.setValueAtCoordinate(i, j, op.compute(a, b));
            }
        }
        return result;
    }

    /**
     * Compute addition between two matrix
     * @param matrice Matrix to compute addition
     * @return Result of the addition
     */
    public Matrice add(Matrice matrice){
        return operation(matrice, add);
    }

    /**
     * Compute substraction between two matrix
     * @param matrice Matrix to compute substraction
     * @return Result of the substraction
     */
    public Matrice sub(Matrice matrice){
        return operation(matrice, sub);
    }

    /**
     * Compute multiplication between two matrix
     * @param matrice Matrix to compute multiplication
     * @return Result of the multiplication
     */
    public Matrice multiply(Matrice matrice){
        return operation(matrice, mul);
    }

    /**
     * Fill matrix coordinates with random values between 0 and modulus
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
     * Build string with values for each row and column
     * @return Matrix values
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