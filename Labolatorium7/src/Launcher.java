import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.util.Arrays.copyOf;
import static java.util.Arrays.sort;

public class Launcher {
    public static final int N = 7000;
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        int[] array = generateArray(N);

        Sortable[] algorithms = {new InsertSort(), new BubbleSort(), new SelectSort(), new QuickSort(), new HeapSort(), new MergeSort()};

        System.out.println("___________UNSORTED ARRAY__________");
        publish(algorithms, array);

        sort(array);

        System.out.println("__________SORTED ARRAY__________");
        publish(algorithms, array);

        reverse(array);

        System.out.println("___________ARRAY SORTED DESCENDING__________");
        publish(algorithms, array);

    }

    private static void reverse(int[] b) {
        for (int left = 0, right = b.length - 1; left < right; left++, right--) {
            int temp = b[left];
            b[left] = b[right];
            b[right] = temp;
        }
    }

    private static void publish(Sortable[] algorithms, int[] array) {
        Map<Sortable, Long> aaa = new HashMap<>();
        int n = 10;
        for (Sortable algorithm : algorithms) {
            int[] copy = copyOf(array, N);
            aaa.put(algorithm, 0L);
            for (int i = 0; i < n; i++) {
                long time = System.nanoTime();
                algorithm.sort(copy);
                long delta = System.nanoTime() - time;
                aaa.put(algorithm, aaa.get(algorithm) + delta);
            }
            System.out.printf("%s sorts in %d micros\n", algorithm.name(), aaa.get(algorithm) / n / 1000);
        }
        System.out.println();
    }

    private static int[] generateArray(final int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = RANDOM.nextInt(10_000);
        }
        return arr;
    }
}
