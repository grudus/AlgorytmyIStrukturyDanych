public class BubbleSort implements Sortable {

    @Override
    public int[] sort(int[] array) {
        final int len = array.length;

        for (int i = 0; i < len; i++) {
            boolean alreadySorted = true;
            for (int j = 1; j < (len - i); j++) {
                if (array[j - 1] > array[j]) {
                    swap(array, j - 1, j);
                    alreadySorted = false;
                }
            }
            if (alreadySorted)
                break;
        }
        return array;
    }
}
