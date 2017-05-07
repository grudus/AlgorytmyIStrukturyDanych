public class SelectSort implements Sortable {

    @Override
    public int[] sort(int[] array) {
        int temp;
        final int len = array.length;
        int max;
        for (int i = 0; i < len; i++) {
            max = i;
            for (int j = i + 1; j < len; j++) {
                if (array[j] < array[max])
                    max = j;
            }

           swap(array, max, i);
        }
        return array;
    }

}
