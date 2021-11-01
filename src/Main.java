public class Main {
    public static void main(String[] args){
        Matrice matrice = new Matrice(3, 3, 10);
        Matrice matrice2 = new Matrice(3, 3, 10);
        System.out.println("matrice 1 :\n" + matrice);
        System.out.println("matrice 2 :\n" + matrice2);
        System.out.println("matrice 1 + matrice 2 :\n" + matrice.add(matrice2));
    }
}
