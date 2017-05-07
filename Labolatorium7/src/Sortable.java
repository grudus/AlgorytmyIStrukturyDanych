public interface Sortable {
    int[] sort(int[] array);

    default void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    default String name() {
        return getClass().getSimpleName();
    }
}
