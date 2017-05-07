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
        for (Sortable algorithm : algorithms) {
            int[] copy = copyOf(array, N);
            long time = System.nanoTime();
            algorithm.sort(copy);
            long delta = System.nanoTime() - time;
            System.out.printf("%s sorts in %d millis\n", algorithm.name(), delta / 1000);
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
