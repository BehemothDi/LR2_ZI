public class Main {
    public static void main(String[] args) {
        double P = 1e-6;
        double T = 1;
        double V = 1.008e7;

        PassGenerator generator = new PassGenerator(V, T, P);

        for (int i = 0; i < 10; i++) {
            System.out.println("\u001B[32m" + generator.getNext()  + "\u001B[0m");
            System.out.println("---------------------------------------------------");
        }

    }
}