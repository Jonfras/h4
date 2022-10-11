package net.htlgkr.krejo.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Task t = new Task();
        t.readCSV();
        int chunks = -1;
        int divisor = 0;
        do {
        System.out.println("Number of chunks:");
        System.out.println("Enter 0 to end Program");
        try {
            chunks = Integer.parseInt(s.nextLine());
            if (chunks==0) break;
            System.out.println("divisor:");
            divisor = Integer.parseInt(s.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Unexpected value");
        }
            t.divideNumbers(divisor, chunks);
        }while (true);

    }
}
