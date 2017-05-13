public class InsertBubbleShellSort extends ShellSort {

    protected InsertBubbleShellSort(IntervalType type, int[] array) {
        super(type, array);
    }

    @Override
    public int[] sort() {
        for (int i = 0; i < intervals.length - 1; i++) {
            insertSort(intervals[i]);
        }
        bubbleSort(1);
        return array;
    }
}
