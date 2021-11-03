public class Main {
    public static void main(String[] args){
        Matrice matrice = new Matrice(4, 3, 5, 1,3,1,1,3,2,4,2,1,0,1,0);
        Matrice matrice2 = new Matrice(5, 3, 5, 1,4,2,3,2,0,1,0,4,2,0,0,2,0,2);

        System.out.println("one\n" + matrice);
        System.out.println("two:\n" + matrice2);
        System.out.println("one + two\n" + matrice.add(matrice2));
        System.out.println("one - two\n" + matrice.sub(matrice2));
        System.out.println("one * two\n" + matrice.multiply(matrice2));
    }
}
