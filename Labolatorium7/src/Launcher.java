import java.util.Arrays;
import java.util.Random;

import static java.util.Arrays.toString;

public class Launcher {
    public static final int N = 6000;
    private static final Random RANDOM = new Random();
    public static void main(String[] args) {
        int[] array = generateArray(N);

        Sortable[] algorithms = {new InsertSort(), new BubbleSort(), new SelectSort(), new QuickSort(), new HeapSort(), new MergeSort()};
        final int size = algorithms.length;

        for (int i = 0; i < size; i++)
            System.out.println(Arrays.toString(algorithms[i].sort(Arrays.copyOf(array, N))));
    }

    private static int[] generateArray(final int n) {
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = RANDOM.nextInt(10_000);
        }
        return arr;
    }
}
