public class Sort {

    // quicksort
    public static void quicksort(WordFrequency[] array, int p, int r) {
        if (r <= p) return;
        int i = partition(array, p, r);
        quicksort(array, p, i-1);
        quicksort(array, i+1, r);
    }

    public static int partition(WordFrequency array[], int p, int r){
        int i = p-1, j = r;
        WordFrequency v = array[r];

        for (;;){
            while (array[++i].getFrequency() > v.getFrequency());
            while (v.getFrequency() > array[--j].getFrequency())
                if (j == p) break;
            if (i >= j) break;
            swap(array, i, j);
        }
        swap(array, i, r);
        return i; }

    private static void swap(WordFrequency[] array, int i, int j) {
        WordFrequency temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}