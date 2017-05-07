public class HeapSort implements Sortable {


    @Override
    public int[] sort(int[] array) {
        createHeap(array);
        int heapSize = array.length - 1;

        for (int i = heapSize; i >= 0; i--) {
            swap(array, 0, heapSize--);
            rearrangeHeap(array, 0, heapSize);
        }

        return array;
    }

    private void createHeap(int[] array) {
        int heapSize = array.length - 1;
        for (int i = array.length / 2; i >= 0; i--)
            rearrangeHeap(array, i, heapSize);
    }

    private void rearrangeHeap(int[] array, int i, int heapSize) {
        int leftNode = 2 * i;
        int rightNode = leftNode + 1;
        int largest;
        if (leftNode <= heapSize && array[leftNode] > array[i])
            largest = leftNode;
        else
            largest = i;

        if (rightNode <= heapSize && array[rightNode] > array[largest])
            largest = rightNode;

        if (largest != i) {
            swap(array, largest, i);
            rearrangeHeap(array, largest, heapSize);
        }
    }


}

