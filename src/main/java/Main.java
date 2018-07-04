public class Main {
    public static void main(String[] args) {
        int tablica[] = {1,2,3,4,5,6,7,8};
        int parzysta;

        for (int i = 1; i <= tablica.length; i++){
            if(i % 2 == 0){
                System.out.println(i);
            }
        }
    }
}
