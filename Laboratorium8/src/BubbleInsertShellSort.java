public class BubbleInsertShellSort extends ShellSort {

    protected BubbleInsertShellSort(IntervalType type, int[] array) {
        super(type, array);
    }

    @Override
    public int[] sort() {
        for (int i = 0; i < intervals.length - 1; i++) {
            bubbleSort(intervals[i]);
        }
        insertSort(1);
        return array;
    }
}
