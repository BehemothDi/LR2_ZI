import java.util.*;

public class PassGenerator {
    double V, T, P;
    private int passLength, alphabetLength;
    private String password;
    private final StringBuilder[] aList = new StringBuilder[4];

    PassGenerator(double V, double T, double P){
        this.V = V;
        this.T = T;
        this.P = P;
    }

    public String getNext() {
        System.out.println("Оценка: \u001B[35m" + getFloorS() + "\u001B[0m");

        calc_PassLen_AlphabetLen(getFloorS());
        constructAlphabet();
        constructPassword();

        return this.password;
    }
    private long getFloorS(){
        return (long) Math.ceil( V * T / P);
    }
    private void calc_PassLen_AlphabetLen(long floorS){
        Random rand = new Random();

        do {
            this.passLength = rand.nextInt(6, 20);
            this.alphabetLength = rand.nextInt(20, 51);
        } while (Math.pow(this.alphabetLength, this.passLength) < floorS);

        System.out.println("Выбранная длина пароля: " + this.passLength);
        System.out.println("Выбранная длина алфавита: " + this.alphabetLength);
    }

    private void constructAlphabet() {
        System.out.println("Генерирую алфавит. Результат:");

        int upL, lowL, spL, numL;
        Random rand = new Random();

        do {
            upL = rand.nextInt(1, 27);
            lowL = rand.nextInt(1, 27);
            spL = rand.nextInt(1, 8);
            numL = rand.nextInt(1, 10);
        }   while (upL + lowL + spL +numL != this.alphabetLength);

        char cToAdd;
        for (int i = 0; i < 4; i++) {
            aList[i] = new StringBuilder();
        }

        for (int i = 0; i < lowL; i++) {
            do {
                cToAdd = getRandomEngLower();
            } while (aList[0].indexOf(cToAdd + "") != -1);
            aList[0].append(cToAdd);
        }
        for (int i = 0; i < upL; i++) {
            do {
                cToAdd = getRandomEngUpper();
            } while (aList[1].indexOf(cToAdd + "") != -1);
            aList[1].append(cToAdd);
        }
        for (int i = 0; i < spL; i++) {
            do {
                cToAdd = getRandomSpecial();
            } while (aList[2].indexOf(cToAdd + "") != -1);
            aList[2].append(cToAdd);
        }
        for (int i = 0; i < numL; i++) {
            do {
                cToAdd = getRandomNumber();
            } while (aList[3].indexOf(cToAdd + "") != -1);
            aList[3].append(cToAdd);
        }

        System.out.println("\u001B[33m" + aList[0] + aList[1] + aList[2] + aList[3] + "\u001B[0m");
    }
    private void constructPassword() {
        System.out.println("Генерирую пароль. Результат:");

        StringBuilder s = new StringBuilder();
        int upL, lowL, spL, numL;
        Random rand = new Random();

        do {
            upL = rand.nextInt(1, 27);
            lowL = rand.nextInt(1, 27);
            spL = rand.nextInt(1, 8);
            numL = rand.nextInt(1, 10);
        }   while (upL + lowL + spL +numL != this.passLength);


        char cToAdd;

        for (int i = 0; i < lowL; i++) {
            cToAdd = getRandomEngLower(aList[0].toString());
            s.append(cToAdd);
        }
        for (int i = 0; i < upL; i++) {
            cToAdd = getRandomEngUpper(aList[1].toString());
            s.append(cToAdd);
        }
        for (int i = 0; i < spL; i++) {
            cToAdd = getRandomSpecial(aList[2].toString());
            s.append(cToAdd);
        }
        for (int i = 0; i < numL; i++) {
            cToAdd = getRandomNumber(aList[3].toString());
            s.append(cToAdd);
        }

        ArrayList<Character> temp = new ArrayList<>();
        for (Character c : s.toString().toCharArray()) {temp.add(c);}
        Collections.shuffle(temp);
        StringBuilder result = new StringBuilder();

        temp.forEach(result::append);


        this.password = result.toString();
    }

    private char getRandomNumber(){
        Random rand = new Random();
        return  (char) rand.nextInt(48,58);
    }
    private char getRandomNumber(String s){
        Random rand = new Random();
        return s.charAt(rand.nextInt(0,s.length()));
    }
    private char getRandomEngLower() {
        Random rand = new Random();
        return (char) rand.nextInt(97,123);
    }
    private char getRandomEngLower(String s) {
        Random rand = new Random();
        return s.charAt(rand.nextInt(0,s.length()));
    }
    private char getRandomEngUpper(){
        Random rand = new Random();
        return (char) rand.nextInt(65,91);
    }
    private char getRandomEngUpper(String s){
        Random rand = new Random();
        return s.charAt(rand.nextInt(0,s.length()));
    }
    private char getRandomSpecial() {
        Random rand = new Random();
        return (char) rand.nextInt(33, 40);
    }
    private char getRandomSpecial(String s) {
        Random rand = new Random();
        return s.charAt(rand.nextInt(0,s.length()));
    }


}
