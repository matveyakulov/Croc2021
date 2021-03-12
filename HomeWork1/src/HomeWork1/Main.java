package HomeWork1;

import HomeWork1.Calc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите колво байтов");
        Calc.printBytes(sc.nextDouble());

    }
}
