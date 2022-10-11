package example2;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    private int start;
    private int length;
    private int sum;

    public int getSum() {
        return sum;
    }

    public MyCallable(int start, int length) {
        this.start = start;
        this.length = length;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


    @Override
    public Integer call() throws Exception {
        for (int i = start; i < start + length; i++) {
            sum+=i;
        }
        System.out.println(sum);
    return sum;
}
}
