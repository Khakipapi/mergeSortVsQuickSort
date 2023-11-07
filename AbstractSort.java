public abstract class AbstractSort {
  private long operationCount;
  private long startTime;
  private long endTime;

  // This method must be implemented by the concrete sorting classes.
  public abstract void sort(int[] array) throws UnsortedException;

  // Initializes counter and records the starting time.
  protected void startSort() {
    operationCount = 0;
    startTime = System.nanoTime();
  }

  // Computes the elapsed time and stores it in endTime.
  protected void endSort() {
    endTime = System.nanoTime() - startTime;
  }

  // Increments the count of the critical operation.
  protected void incrementCount() {
    operationCount++;
  }

  // Returns the count of critical operations.
  protected long getCount() {
    return operationCount;
  }

  // Returns the elapsed time of the sorting process.
  protected long getTime() {
    return endTime;
  }
}
