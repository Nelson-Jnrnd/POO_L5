/**
 * Authors:     Alen Bijelic, Nelson Jeanrenaud
 * Date:        03.11.2021
 *
 * Description: Program that tests all the features of the Matrice class for many cases.
 */
public class Main {
    public static void main(String[] args) {
        // Test basique.........
        System.out.println("Test du constructeur : ");
        System.out.println("Avec des matrices carrées...");
        System.out.println("3..");
        testMatrice(3, 3, 3, 3, 5, 5);
        System.out.println("2..");
        testMatrice(2, 2, 2, 2, 5, 5);
        System.out.println("1..");
        testMatrice(1, 1, 1, 1, 5, 5);

        System.out.println("Avec des matrices pas carrées...");
        testMatrice(4, 3, 5, 3, 5, 5);
        testMatrice(1, 3, 3, 1, 5, 5);
        testMatrice(3, 6, 3, 6, 5, 5);

        // Test de valeurs limites.......
        // Dimension impossibles.......
        try {
            System.out.println("Avec un n nul...");
            testMatrice(0, 3, 5, 3, 5, 5);
        } catch (RuntimeException ex) {
            System.out.println("Exception...Correct");
        }
        try {
            System.out.println("Avec un m nul...");
            testMatrice(4, 0, 5, 3, 5, 5);
        } catch (RuntimeException ex) {
            System.out.println("Exception...Correct");
        }
        try {
            System.out.println("Avec un n négatif...");
            testMatrice(-4, 3, 5, 3, 5, 5);
        } catch (RuntimeException ex) {
            System.out.println("Exception...Correct");
        }
        try {
            System.out.println("Avec un m négatif...");
            testMatrice(4, -3, 5, 3, 5, 5);
        } catch (RuntimeException ex) {
            System.out.println("Exception...Correct");
        }
        try {
            System.out.println("Avec n et m négatif...");
            testMatrice(-4, -3, 5, 3, 5, 5);
        } catch (RuntimeException ex) {
            System.out.println("Exception...Correct");
        }
        /// MODULO................
        try {
            System.out.println("Avec un modulo négatif...");
            testMatrice(4, 3, 5, 3, -5, -5);
        } catch (RuntimeException ex) {
            System.out.println("Exception...Correct");
        }
        try {
            System.out.println("Avec un modulo nul...");
            testMatrice(4, 3, 5, 3, 0, 0);
        } catch (RuntimeException ex) {
            System.out.println("Exception...Correct");
        }
        try {
            System.out.println("Avec des modulos différents...");
            testMatrice(4, 3, 5, 3, 4, 3);
        } catch (RuntimeException ex) {
            System.out.println("Exception...Correct");
        }

        // Valeures prédéfinies....
        System.out.println("Avec des valeurs par défauts...");
        testMatrice(2, 2, 2, 2, 4, 4, 2, 3, 3, 2);
        System.out.println("Avec moins de valeurs par défauts que de cellules...");
        testMatrice(2, 2, 2, 2, 4, 4, 2, 1);
        try {
            System.out.println("Avec plus de valeurs par défauts que de cellules...");
            testMatrice(2, 2, 2, 2, 4, 4, 2, 1, 3, 3, 2, 3);
        } catch (RuntimeException ex) {
            System.out.println("Exception...Correct");
        }
        System.out.println("Avec des valeurs par défauts plus grande que le modulo...");
        testMatrice(2, 2, 2, 2, 4, 4, 5, 12, 51, 102);
        System.out.println("Avec des valeurs par défauts négatives...");
        testMatrice(2, 2, 2, 2, 4, 4, -2, -4, -3, -1);
    }

    public static void testMatrice(int n1, int m1, int n2, int m2, int modulo1, int modulo2, int... values) {
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
