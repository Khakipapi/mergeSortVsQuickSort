public class MergeSort extends AbstractSort {

  @Override
  public void sort(int[] array) {
    if (array == null) {
      return;
    }

    if (array.length > 1) {
      startSort();  // Start timing and initialize counter
      mergeSort(array, 0, array.length - 1);
      endSort();    // End timing
    }
  }

  private void mergeSort(int[] array, int leftStart, int rightEnd) {
    if (leftStart < rightEnd) {
      int middle = (leftStart + rightEnd) / 2;
      mergeSort(array, leftStart, middle);
      mergeSort(array, middle + 1, rightEnd);
      merge(array, leftStart, middle, rightEnd);
    }
  }

  private void merge(int[] array, int leftStart, int middle, int rightEnd) {
    // Create temporary arrays
    int[] leftTempArray = new int[middle - leftStart + 1];
    int[] rightTempArray = new int[rightEnd - middle];

    // Copy our sub-data to temporary arrays
    for (int i = 0; i < leftTempArray.length; ++i) {
      leftTempArray[i] = array[leftStart + i];
    }
    for (int j = 0; j < rightTempArray.length; ++j) {
      rightTempArray[j] = array[middle + 1 + j];
    }

    // Merge the temp arrays
    int leftIndex = 0, rightIndex = 0;
    int mergeIndex = leftStart;
    while (leftIndex < leftTempArray.length && rightIndex < rightTempArray.length) {
      if (leftTempArray[leftIndex] <= rightTempArray[rightIndex]) {
        array[mergeIndex] = leftTempArray[leftIndex];
        leftIndex++;
      } else {
        array[mergeIndex] = rightTempArray[rightIndex];
        rightIndex++;
      }
      mergeIndex++;
      incrementCount();  // Increment the critical operation count
    }

    // Copy the remaining elements of leftTempArray if any
    while (leftIndex < leftTempArray.length) {
      array[mergeIndex] = leftTempArray[leftIndex];
      leftIndex++;
      mergeIndex++;
      incrementCount();  // Increment for the comparisons
    }

    // Copy the remaining elements of rightTempArray if any
    while (rightIndex < rightTempArray.length) {
      array[mergeIndex] = rightTempArray[rightIndex];
      rightIndex++;
      mergeIndex++;
      incrementCount();  // Increment for the comparisons
    }
  }
}
