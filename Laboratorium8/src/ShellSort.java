import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.reverseOrder;

public abstract class ShellSort {
    protected final int[] intervals;
    protected int[] array;

    protected ShellSort(IntervalType type, int[] array) {
        this.intervals = type.generateIntervals(array.length);
        this.array = Arrays.copyOf(array, array.length);
    }

    public abstract int[] sort();

    protected void insertSort(int interval) {
        final int len = array.length;

        for (int start = 0; start < interval; start++) {
            for (int i = start + interval; i < len; i += interval) {
                final int copyNumber = array[i];
                int j = i;
                while (j >= interval && copyNumber < array[j - interval]) {
                    array[j] = array[j - interval];
                    j -= interval;
                }
                array[j] = copyNumber;
            }
        }
    }

    protected void bubbleSort(int interval) {
        final int len = array.length;
        boolean alreadySorted;
        do {
            alreadySorted = true;
            for (int i = 0; i < interval; i++) {
                for (int j = i + interval; j < len; j += interval) {
                    final int previousIndex = j - interval;
                    if (array[previousIndex] > array[j]) {
                        swap(array, previousIndex, j);
                        alreadySorted = false;
                    }
                }
            }
        } while (!alreadySorted);

    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public enum IntervalType {
        _3HPLUS1("3h + 1") {
            @Override
            int[] generateIntervals(int length) {
                List<Integer> intervals = new ArrayList<>();
                for (int i = 1; i < length; i = 3 * i + 1)
                    intervals.add(i);

                intervals.sort(reverseOrder());
                return toArray(intervals);
            }
        },
        _2KMINUS1("k^2 - 1") {
            @Override
            int[] generateIntervals(int length) {
                List<Integer> intervals = new ArrayList<>();
                int interval = 1;
                int i = 1;
                while (interval < length) {
                    intervals.add(interval);
                    interval = (int) (Math.pow(2, ++i) - 1);
                }

                intervals.sort(reverseOrder());
                return toArray(intervals);
            }
        },
        _2KPLUS1("k^2 + 1") {
            @Override
            int[] generateIntervals(int length) {
                List<Integer> intervals = new ArrayList<>();
                int interval = 1;
                int i = 1;
                while (interval < length) {
                    intervals.add(interval);
                    interval = (int) (Math.pow(2, i++) + 1);
                }


                intervals.sort(reverseOrder());
                return toArray(intervals);
            }
        },
        FIBONACCI("Fibonacci algorithm") {
            @Override
            int[] generateIntervals(int length) {
                List<Integer> intervals = new ArrayList<>();

                int fibo = 1;
                int fiboPrev = 1;
                while (fibo < length) {
                    int temp = fibo;
                    intervals.add(fibo);
                    fibo += fiboPrev;
                    fiboPrev = temp;
                }

                intervals.sort(reverseOrder());
                return toArray(intervals);
            }
        };

        public final String label;

        IntervalType(String label) {
            this.label = label;
        }

        abstract int[] generateIntervals(int length);

        private static int[] toArray(List<Integer> integers) {
            return integers.stream().mapToInt(i -> i).toArray();
        }
    }

}
