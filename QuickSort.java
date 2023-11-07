public class QuickSort extends AbstractSort {
  private long count = 0;
  private long timeElapsed = 0;

  @Override
  public void sort(int[] array) throws UnsortedException {
    resetCount();
    startSort(); // Initialize counter and record starting time
    quickSort(array, 0, array.length - 1);
    endSort(); // Compute the elapsed time

    if (!isSorted(array)) {
      throw new UnsortedException("The array is not sorted.");
    }
  }

  private void quickSort(int[] array, int low, int high) {
    if (low < high) {
      int pi = partition(array, low, high);

      quickSort(array, low, pi - 1);
      quickSort(array, pi + 1, high);
    }
  }

  private int partition(int[] array, int low, int high) {
    int pivot = array[high];
    int i = (low - 1);
    for (int j = low; j < high; j++) {
      incrementCount(); // Critical operation: comparison
      if (array[j] < pivot) {
        i++;
        swap(array, i, j);
      }
    }
    swap(array, i + 1, high);
    return i + 1;
  }

  private void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  private boolean isSorted(int[] array) {
    for (int i = 0; i < array.length - 1; i++) {
      if (array[i] > array[i + 1]) {
        return false;
      }
    }
    return true;
  }

  @Override
  protected void startSort() {
    count = 0;
    timeElapsed = System.nanoTime();
  }

  @Override
  protected void endSort() {
    timeElapsed = System.nanoTime() - timeElapsed;
  }

  @Override
  protected void incrementCount() {
    count++;
  }

  @Override
  protected long getCount() {
    return count;
  }

  @Override
  protected long getTime() {
    return timeElapsed;
  }

  private void resetCount() {
    count = 0;
  }
}
