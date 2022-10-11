package example2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main {
    public static List<MyRunnable> runnables = new ArrayList<>();
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        do {
            System.out.println("number:");
            number = Integer.parseInt(scanner.nextLine());
        }while (number<=0);
        int start = 0;
        int length = 100;
        int chunks = Math.ceilDiv(number, 100);
        ExecutorService exec = Executors.newScheduledThreadPool(chunks);
        for (int i = 0; i < chunks; i++) {
            MyRunnable mr = new MyRunnable(i*length, length);
        }
        
    }
}
