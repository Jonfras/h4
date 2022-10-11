package net.htlgkr.krejo.example1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Task {
    ArrayList<Integer> numbersList = new ArrayList<>(10);
    Scanner s;

    List<Chunker> chunkers = new ArrayList<>();


    //Predicate<Integer> isDivisible = (Double dividend, int divisor) -> dividend % divisor == 0;


    public void readCSV() {
        //s = new Scanner("numbers.csv");
        try {
            List<String> stringList = Files.lines(Path.of("numbers.csv")).toList();
            stringList.forEach(line -> {
                String[] arr = line.split(":");
                for (String s :
                        arr) {
                    try {
                        numbersList.add(Integer.parseInt(s));
                        System.out.println("Zahl eingelesen");
                    } catch (Exception e) {
                        System.out.println("Üngültige Zahl");
                    }
                }

            });

            //stringList.stream().filter(x -> x.)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> divideNumbers(int number, int chunks) {
        if (chunks > numbersList.size()) chunks = numbersList.size() - 1;
        int chunksize = Math.ceilDiv(numbersList.size(), chunks);
        ExecutorService exec = Executors.newScheduledThreadPool(chunks);
        chunkers.add(new Chunker(numbersList.subList((0), (chunksize)), number));
        int end = 0;
        for (int i = 1; end < numbersList.size(); i++) {
            int start = (chunksize * (i));//if einbauen um zu schauen ob index vorhanden
            int tempChunksize = checkIndex(start, chunksize);
            end = tempChunksize + start;
           // System.out.println(start + "-" + end);
            chunkers.add(new Chunker(numbersList.subList(start, end), number));
            if (end >= numbersList.size()) break;
        }

        List<Future<List<Integer>>> futureList = new ArrayList<>();
        try {
            futureList = exec.invokeAll(chunkers);
        } catch (InterruptedException e) {
            System.out.println("Fehler beim Eerstellen der Callables");
        }
        List<Integer> allNumbers = new ArrayList<>();
        for (Future<List<Integer>> future : futureList) {
            try {
                allNumbers.addAll(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        allNumbers.forEach(System.out::println);
        System.out.println("-------------------------");
        return null;
    }


    private int checkIndex(int start, int chunksize) {
        int size = 0;
        if (numbersList.size() < start) return size;
        for (int i = 0; i < chunksize; i++) {
            try {
                if (numbersList.get(start + i) != null)
                    size++;
            } catch (Exception e) {
                return size;
            }
        }
        return size;
    }
}
