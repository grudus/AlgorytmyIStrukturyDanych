public class BubbleSort implements Sortable {

    @Override
    public int[] sort(int[] array) {
        final int len = array.length;
        int temp;
        for (int i = 0; i < len; i++)
            for (int j = 1; j < (len - i); j++) {
                if (array[j - 1] > array[j]) {
                    swap(array, j - 1, j);
                }
            }
        return array;
    }
}
