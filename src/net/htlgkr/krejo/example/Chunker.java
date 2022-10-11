package net.htlgkr.krejo.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class Chunker implements Callable<List<Integer>> {
    List<Integer> chunk;

    int divisor;

    public Chunker(List<Integer> chunk, int divisor) {
        this.chunk = chunk;
        this.divisor = divisor;
    }


    @Override
    public List<Integer> call() throws Exception {
        System.out.println();
        List<Integer> ni = new ArrayList<>();
        ni = chunk.stream().filter(x -> x % divisor == 0).toList();
        return ni;
    }
}
