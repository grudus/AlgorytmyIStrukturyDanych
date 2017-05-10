public class InsertSort implements Sortable {

    @Override
    public int[] sort(int[] array) {
        final int len = array.length;
        for (int i = 0; i < len; i++) {
            final int copyNumber = array[i];
            int j = i;
            while (j > 0 && copyNumber < array[j-1]) {
                array[j] = array[j-1];
                j--;
            }
            array[j] = copyNumber;
        }
        return array;
    }
}
