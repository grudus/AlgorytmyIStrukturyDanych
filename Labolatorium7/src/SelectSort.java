public class SelectSort implements Sortable {

    @Override
    public int[] sort(int[] array) {
        final int len = array.length;
        int minIndex;
        for (int i = 0; i < len; i++) {
            minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (array[j] < array[minIndex])
                    minIndex = j;
            }

           swap(array, minIndex, i);
        }
        return array;
    }

}
