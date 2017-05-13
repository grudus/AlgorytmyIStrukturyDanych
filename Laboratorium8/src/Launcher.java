import java.util.Random;

public class Launcher {
    public static void main(String[] args) {
        int[][] arrays = {generateArray(5000), generateArray(10000), generateArray(50000), generateArray(100000)};

        for (int[] array : arrays) {
            for (ShellSort.IntervalType type : ShellSort.IntervalType.values()) {
                ShellSort[] algorithms = new ShellSort[]{new InsertInsertShellSort(type, array),
                        new BubbleInsertShellSort(type, array),
                        new InsertBubbleShellSort(type, array)};

                for (ShellSort algorithm : algorithms) {
                    final long time = System.nanoTime();
                    algorithm.sort();
                    System.out.printf("%s of type (%s) for %d elements: %d μs\n", algorithm, type.label, array.length, (System.nanoTime() - time) / 1000);
                }
            }
            System.out.println("______________");
        }
    }

    private static int[] generateArray(final int n) {
        Random random = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = random.nextInt();

        return arr;
    }

}
