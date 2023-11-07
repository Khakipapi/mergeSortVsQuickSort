import java.util.Arrays;
import java.util.Random;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SortBenchmark {

  // Make sure this array is initialized with non-zero sizes
  private static final int[] SIZES = {10, 20, 50, 100, 200, 400, 800, 1600, 3200, 6400, 12800, 25600};

  public static void main(String[] args) {
    // Create instances of your sorters
    AbstractSort mergeSort = new MergeSort();
    AbstractSort quickSort = new QuickSort();

    // Generate all data sets
    int[][][] datasets = generateAllDataSets(SIZES);

    // Perform benchmarking
    try {
      benchmarkSort(mergeSort, datasets, "MergeSortData.txt");
      benchmarkSort(quickSort, datasets, "QuickSortData.txt");
    } catch (UnsortedException e) {
      System.err.println("Error during sorting: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private static int[][][] generateAllDataSets(int[] sizes) {
    int[][][] datasets = new int[sizes.length][40][];

    for (int i = 0; i < sizes.length; i++) {
      for (int j = 0; j < 40; j++) {
        datasets[i][j] = generateRandomArray(sizes[i]);
      }
    }
    return datasets;
  }

  private static int[] generateRandomArray(int size) {
    Random random = new Random();
    int[] data = new int[size];
    for (int i = 0; i < size; i++) {
      data[i] = random.nextInt(); // or some range of ints
    }
    return data;
  }

  public static void benchmarkSort(AbstractSort sorter, int[][][] datasets, String fileName)
      throws UnsortedException {
    try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
      for (int i = 0; i < datasets.length; i++) {
        System.out.println("Sorting size: " + SIZES[i]); // Informative output
        out.print(SIZES[i]);
        for (int j = 0; j < datasets[i].length; j++) {
          int[] dataCopy = Arrays.copyOf(datasets[i][j], datasets[i][j].length);
          sorter.sort(dataCopy); // Sort the data
          if (!verifySorted(dataCopy)) { // Verify it's sorted
            throw new UnsortedException("Data not sorted correctly for size " + SIZES[i]);
          }
          out.printf(" %d %d", sorter.getCount(), sorter.getTime()); // Write results
        }
        out.println();
        out.flush(); // Explicitly flush after each dataset
      }
    } catch (IOException e) {
      System.err.println("IO Exception occurred: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private static boolean verifySorted(int[] data) {
    for (int i = 0; i < data.length - 1; i++) {
      if (data[i] > data[i + 1]) {
        return false;
      }
    }
    return true;
  }
}
