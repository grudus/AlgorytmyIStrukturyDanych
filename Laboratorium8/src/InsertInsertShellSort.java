public class InsertInsertShellSort extends ShellSort {

    public InsertInsertShellSort(IntervalType type, int[] array) {
        super(type, array);
    }

    @Override
    public int[] sort() {
        for (int interval : intervals) {
            insertSort(interval);
        }
        return array;
    }
}
