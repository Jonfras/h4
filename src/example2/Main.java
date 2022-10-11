package example2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static List<MyCallable> callables = new ArrayList<>();
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
        for (int i = 0; i < chunks-1; i++) {
            start = i*length + 1;
            callables.add(new MyCallable(start, length));
        }
        start+=length;
        callables.add(new MyCallable(start, number%100));


        List<Future<Integer>> futures = new ArrayList<>();
        try {
            futures = exec.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int sum = futures.stream()
                .map(m -> {
                    try {
                        return (int) m.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .reduce(0, Integer::sum);

        System.out.println(sum);
        if (((number*number) + number)/2 == sum) System.out.println("richtig");
        else System.out.println("falsch");
        return;
    }
}
