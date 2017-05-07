public class QuickSort implements Sortable {
    @Override
    public int[] sort(int[] array) {
        quicksort(array, 0, array.length - 1);
        return array;
    }

    private void quicksort(int array[], int x, int y) {

        int i, j, v, temp;

        i = x;
        j = y;
        v = array[(x + y) / 2];
        do {
            while (array[i] < v)
                i++;
            while (v < array[j])
                j--;
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        while (i <= j);
        if (x < j)
            quicksort(array, x, j);
        if (i < y)
            quicksort(array, i, y);
    }
}
