import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class ReportGenerator {

  private static final int DATASET_COUNT = 40;

  public static void main(String[] args) {
    // Using JFileChooser to allow the user to select the input file
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
    int returnValue = fileChooser.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      generateReport(selectedFile);
    }
  }

  private static void generateReport(File file) {
    try {
      List<String> lines = Files.readAllLines(file.toPath());

      // Prepare data for the JTable
      String[] columnNames = {"Data Set Size", "Average Critical Count", "Coefficient of Variance Count (%)", "Average Time (ns)", "Coefficient of Variance Time (%)"};
      Object[][] data = new Object[lines.size()][columnNames.length];

      for (int i = 0; i < lines.size(); i++) {
        String[] tokens = lines.get(i).split("\\s+");
        int dataSize = Integer.parseInt(tokens[0]);

        // Extract critical counts and times from tokens
        List<Long> counts = new ArrayList<>();
        List<Long> times = new ArrayList<>();
        for (int j = 1; j < tokens.length; j += 2) {
          counts.add(Long.parseLong(tokens[j]));
          times.add(Long.parseLong(tokens[j + 1]));
        }

        // Compute average and coefficient of variance for counts
        double averageCount = counts.stream().mapToLong(val -> val).average().orElse(0);
        double varianceCount = calculateCoefficientOfVariance(counts, averageCount);

        // Compute average and coefficient of variance for times
        double averageTime = times.stream().mapToLong(val -> val).average().orElse(0);
        double varianceTime = calculateCoefficientOfVariance(times, averageTime);

        // Populate the data array for the JTable
        data[i][0] = dataSize;
        data[i][1] = averageCount;
        data[i][2] = varianceCount;
        data[i][3] = averageTime;
        data[i][4] = varianceTime;
      }

      // Create the JTable and set it up inside a scroll pane
      JTable table = new JTable(data, columnNames);
      JScrollPane scrollPane = new JScrollPane(table);
      table.setFillsViewportHeight(true);

      // Display the table in a frame
      JFrame frame = new JFrame("Sorting Algorithm Benchmark Report");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(scrollPane);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);

    } catch (IOException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  private static double calculateCoefficientOfVariance(List<Long> values, double average) {
    double sumSquaredDifferences = values.stream()
        .mapToDouble(value -> Math.pow(value - average, 2))
        .sum();
    double standardDeviation = Math.sqrt(sumSquaredDifferences / values.size());
    return (standardDeviation / average) * 100; // Coefficient of variance as a percentage
  }
}
