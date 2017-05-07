public class QuickSort implements Sortable {

    @Override
    public int[] sort(int[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int pivotIndex = partition(array, start, end);
            quickSort(array, start, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, end);
        }
    }

    private int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int partitionIndex = start;
        for (int i = start; i < end; i++) {
            if (array[i] <= pivot)
                swap(array, i, partitionIndex++);
        }
        swap(array, partitionIndex, end);
        return partitionIndex;
    }
}
