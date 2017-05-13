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

    public enum IntervalType {
        _3HPLUS1 {
            @Override
            int[] generateIntervals(int length) {
                List<Integer> intervals = new ArrayList<>();
                for (int i = 1; i < length; i = 3 * i + 1)
                    intervals.add(i);

                intervals.sort(reverseOrder());
                return toArray(intervals);
            }
        },
        _2KMINUS1 {
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
        _2KPLUS1 {
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
        FIBONACCI {
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

        abstract int[] generateIntervals(int length);

        private static int[] toArray(List<Integer> integers) {
            return integers.stream().mapToInt(i -> i).toArray();
        }
    }

}
