public class InsertSort implements Sortable {

    @Override
    public int[] sort(int[] array) {
        int temp;
        final int len = array.length;
        for (int i = 1; i < len; i++) {
            for(int j = i ; j > 0 ; j--){
                if(array[j] < array[j-1]){
                   swap(array, j, j-1);
                }
            }
        }
        return array;
    }
}
