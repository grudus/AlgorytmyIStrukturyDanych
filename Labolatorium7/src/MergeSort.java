import java.util.Arrays;

public class MergeSort implements Sortable {


    @Override
    public int[] sort(int[] array) {
        mergeSort(array);
        return array;
    }

    private void mergeSort(int[] array) {
        int n = array.length;
        if (n < 2) return;
        int mid = n / 2;
        int[] leftArray = Arrays.copyOfRange(array, 0, mid);
        int[] rightArray = Arrays.copyOfRange(array, mid, n);
        mergeSort(leftArray);
        mergeSort(rightArray);
        mergeArrays(leftArray, rightArray, array);
    }

    private void mergeArrays(int[] leftArray, int[] rightArray, int[] parent) {
        int nL = leftArray.length;
        int rL = rightArray.length;
        int i, j, k;
        i = j = k = 0;
        while (i < nL && j < rL) {
            if (leftArray[i] <= rightArray[j])
                parent[k++] = leftArray[i++];
            else
                parent[k++] = rightArray[j++];
        }
        while (i < nL)
            parent[k++] = leftArray[i++];
        while (j < rL)
            parent[k++] = rightArray[j++];
    }

}
