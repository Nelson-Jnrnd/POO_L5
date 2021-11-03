/**
 * Authors:     Alen Bijelic, Nelson Jeanrenaud
 * Date:        03.11.2021
 * <p>
 * Description: Program that tests all the features of the Matrice class for many cases.
 */
public class Main {
    public static void main(String[] args) {
        // Creation
        randomConstructorShouldBeCorrect();
        valuesConstructorShouldBeCorrect();
        valuesConstructorWithNotEnoughValuesShouldBeCorrect();
        valuesConstructorWithTooManyValuesShouldThrowException();
        matrixWithNullDimensionsShouldThrowException();
        matrixWithNegativeDimensionsShouldThrowException();
        matrixWithNullModuloShouldThrowException();
        matrixWithNegativeModuloShouldThrowException();

        // Operations
        operationsShouldBeCorrect();
        operationsWith2DifferentModuloShouldThrowException();
        customOperationsShouldBeCorrect();

        // Graphics
        matricesDisplayShouldBeCorrect();
    }

    // Creation test
    public static void randomConstructorShouldBeCorrect() {
        boolean testStatus = true;
        int[] inputs = {1, 2, 3, 4, 5, 10, 20};
        for (int input : inputs) {
            int modulo = 4;
            Matrice testMatrice = new Matrice(input, input, modulo);

            for (int value : testMatrice.getValues()) {
                if (value >= modulo || value < 0) {
                    testStatus = false;
                    break;
                }
            }
        }
        System.out.println("Test - Random constructor should create correct matrices : " + testStatus);
    }

    public static void valuesConstructorShouldBeCorrect() {
        boolean testStatus = true;
        int[] values = {1, 2, 0, 33, 51};

        int n = 4, m = 4, modulo = 4;
        Matrice testMatrice = new Matrice(n, m, modulo, values);

        for (int value : testMatrice.getValues()) {
            if (value >= modulo || value < 0) {
                testStatus = false;
                break;
            }
        }
        System.out.println("Test - values constructor should create correct matrices : " + testStatus);
    }

    public static void valuesConstructorWithTooManyValuesShouldThrowException() {
        boolean testStatus = true;
        int[] values = {1, 2, 2, 3, 4};

        int n = 2, m = 2, modulo = 4;
        try {
            Matrice testMatrice = new Matrice(n, m, modulo, values);
            testStatus = false;
        } catch (RuntimeException ignored) {
        } finally {
            System.out.println("Test - values constructor with too many values should throw exception : " + testStatus);
        }
    }

    public static void valuesConstructorWithNotEnoughValuesShouldBeCorrect() {
        boolean testStatus = true;
        int[] values = {1, 2, 2};

        int n = 5, m = 5, modulo = 4;
        Matrice testMatrice = new Matrice(n, m, modulo, values);

        for (int value : testMatrice.getValues()) {
            if (value >= modulo || value < 0) {
                testStatus = false;
                break;
            }
        }
        System.out.println("Test - values constructor with not enough values should create correct matrices : " + testStatus);
    }

    public static void matrixWithNullDimensionsShouldThrowException() {
        boolean testStatus = true;

        int n = 3, m = 3, modulo = 4;
        try {
            Matrice testMatrice = new Matrice(0, m, modulo);
            testStatus = false;
        } catch (RuntimeException ignored) {
        }
        try {
            Matrice testMatrice = new Matrice(n, 0, modulo);
            testStatus = false;
        } catch (RuntimeException ignored) {
        }
        try {
            Matrice testMatrice = new Matrice(0, 0, modulo);
            testStatus = false;
        } catch (RuntimeException ignored) {
        }
        System.out.println("Test - matrices with null dimension should throw exception : " + testStatus);
    }

    public static void matrixWithNegativeDimensionsShouldThrowException() {
        boolean testStatus = true;

        int modulo = 4;
        int[] testValues = {1, 3, 2, 10, 14};
        for (int testValue : testValues) {
            try {
                Matrice testMatrice = new Matrice(-1 * testValue, testValue, modulo);
                testStatus = false;
            } catch (RuntimeException ignored) {
            }
            try {
                Matrice testMatrice = new Matrice(testValue, -1 * testValue, modulo);
                testStatus = false;
            } catch (RuntimeException ignored) {
            }
            try {
                Matrice testMatrice = new Matrice(-1 * testValue, -1 * testValue, modulo);
                testStatus = false;
            } catch (RuntimeException ignored) {
            }
        }
        System.out.println("Test - matrices with negative dimension should throw exception : " + testStatus);
    }

    public static void matrixWithNullModuloShouldThrowException() {
        boolean testStatus = true;

        int n = 2, m = 2, modulo = 0;
        try {
            Matrice testMatrice = new Matrice(n, m, modulo);
            testStatus = false;
        } catch (RuntimeException ignored) {
        } finally {
            System.out.println("Test - matrices with a null modulo should throw exception : " + testStatus);
        }
    }

    public static void matrixWithNegativeModuloShouldThrowException() {
        boolean testStatus = true;

        int[] testValues = {-1, -2, -42, -31};
        int n = 2, m = 2;
        for (int testValue : testValues) {
            try {
                Matrice testMatrice = new Matrice(n, m, testValue);
                testStatus = false;
            } catch (RuntimeException ignored) {
            }
        }
        System.out.println("Test - matrices with a negative modulo should throw exception : " + testStatus);
    }

    // Operation test

    public static void operationsShouldBeCorrect() {
        boolean testStatus = true;
        int modluo = 5;
        Matrice testMatrice = new Matrice(4, 4, modluo);
        Matrice testMatrice2 = new Matrice(4, 4, modluo);

        int[] matriceValues = testMatrice.getValues();
        int[] matrice2Values = testMatrice2.getValues();
        int[] results = testMatrice.add(testMatrice2).getValues();

        for (int i = 0; i < results.length; i++) {
            if (results[i] != Math.floorMod(matriceValues[i] + matrice2Values[i], modluo)) {
                testStatus = false;
                break;
            }
        }
        System.out.println("Test - custom operations should be correct : " + testStatus);
    }

    public static void operationsWith2DifferentModuloShouldThrowException() {
        boolean testStatus = true;

        try {
            Matrice matrice = new Matrice(5, 5, 2);
            Matrice matrice2 = new Matrice(5, 5, 3);
            matrice.add(matrice2);
            testStatus = false;
        } catch (RuntimeException ignored) {
        }
        System.out.println("Test - opertions with different moodulos should throw exception : " + testStatus);
    }

    public static void customOperationsShouldBeCorrect() {
        boolean testStatus = true;

        Equals ope = new Equals();
        Matrice testMatrice = new Matrice(4, 4, 5);
        Matrice testMatrice2 = new Matrice(4, 4, 5);

        int[] matriceValues = testMatrice.getValues();
        int[] matrice2Values = testMatrice2.getValues();
        int[] results = testMatrice.operation(testMatrice2, ope).getValues();

        for (int i = 0; i < results.length; i++) {
            if (results[i] != (matriceValues[i] == matrice2Values[i] ? 1 : 0)) {
                testStatus = false;
                break;
            }
        }
        System.out.println("Test - custom operations should be correct : " + testStatus);
    }

    public static void matricesDisplayShouldBeCorrect() {
        System.out.println("Test - Display of matrices should be correct :");
        testMatrixOperations(3, 2, 3, 2, 4, 4);
    }

    public static void testMatrixOperations(int n1, int m1, int n2, int m2, int modulo1, int modulo2, int... values) {
        System.out.println("n1 " + n1 + " m1 " + m1 + " n2 " + n2 + " m2 " + m2);
        System.out.println("modulo1 " + modulo1 + " modulo2 " + modulo2);

        Matrice matrice = new Matrice(n1, m1, modulo1, values);
        Matrice matrice2 = new Matrice(n2, m2, modulo2, values);

        System.out.println("one\n" + matrice);
        System.out.println("two:\n" + matrice2);
        System.out.println("one + two\n" + matrice.add(matrice2));
        System.out.println("one - two\n" + matrice.sub(matrice2));
        System.out.println("one * two\n" + matrice.multiply(matrice2));
    }
}

class Equals implements Operation {

    @Override
    public int compute(int a, int b) {
        return (a == b ? 1 : 0);
    }
}