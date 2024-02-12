
Analysis of Merge sort and Quick sort 

This semester, I have chosen to do my projects on Merge and Quick Sort. Merge Sort is a basic divide and conquer technique in computer science that is used to sort arrays or lists. It works by dividing the input array into two halves, sorting each half recursively, and then joining the two sorted halves into a single sorted array. This method ensures that the temporal complexity is always O(n log n) for all circumstances - worst, average, and best. The fundamental disadvantage of the approach is its O(n) space complexity, which is required for the temporary arrays utilized during the merging phase. Despite this, Merge Sort is highly successful for sorting huge data sets, particularly when dealing with data that cannot be held in memory at the same time, making it a popular choice for external sorting. 
In comparison, QuickSort is another divide and conquer algorithm, QuickSort, is well-known for its efficiency in practice, particularly for in-memory sorting. It chooses a pivot element and partitions the array so that elements less than the pivot are on one side and greater elements are on the other, applying these steps recursively on the sub-arrays. The temporal complexity in the average situation is O(n log n), which is similar to Merge Sort, but it can decline to O(n2) in the worst scenario, especially if the pivot selection is poor. However, in practice, this is frequently addressed by employing randomized or hybrid procedures for pivot selection. QuickSort has a lower space complexity (O(log n)) than Merge Sort due to its in-place nature, and its cache-efficient behavior typically makes it faster for arrays. However, it is not often stable, which means it does not retain the order of equal components, which can be a deciding factor when picking between QuickSort and Merge Sort for specific applications.

High-level Pseudocode 
MergeSort
pseudocode:
 
SortArray(array, left, right)
 
If you turn left:
Find the middle point m = (left + right) / 2 and then use MergeSort to sort the first half: MergeSort(array, left, m).
For the second half, use MergeSort: MergeSort(array, m + 1, right).
Merge the two portions that were separated in the previous steps.
(array, left, middle, and right)
 
Make two temporary arrays, L[] and R[], and copy the data into them.
Return the temporary arrays to array[left...right].
During merging, the operationCount is incremented for each comparison.
 
Quick Sort pseudocode:
 
(array, low, high) QuickSort
 
If low is greater than high, then pi = Partition(array, low, high), where pi is the partitioning index.
Call QuickSort(array, low, pi - 1) to sort the array.
QuickSort(array, pi + 1, high) is the method to use.
Partitioning (array, low, and high)
 
Choose one of the array's pivot elements. (This is usually the last piece.)
Rearrange the array so that elements less than the pivot are on the left and elements greater than the pivot are on the right.
For each comparison with the pivot, increment operationCount.
The partitioning index is returned.
 
BigΘ 
Merge Sort
Time Complexity: O(n log n) in all circumstances (best, average, worst) since it always divides the array into two halves and merges two halves in linear time.
Because of the temporary arrays utilized in the merge step, the space complexity is O(n).
Quick Sort
Average and best case time complexity: O(n log n), assuming the pivot divides the array roughly in half.
The worst-case scenario is O(n2), which occurs when the lowest or largest element is always chosen as the pivot.
Due to the recursion stack, space complexity is O(log n) in the best scenario but O(n) in the worst case.

JVM warm up
It is important to deal with JVM (Java Virtual Machine) warm-up when benchmarking Java algorithms like Merge Sort and QuickSort to get correct results. During the JVM warm-up time, the JVM improves how code runs, which can cause performance to be patchy at first. To deal with this, I begin with a warm-up. To do this, the methods are run several times with different sets of data before the real tests start. This gives the JVM a chance to improve the code and keep performance stable.
I also make sure that the testing area stays the same. For all tests, this means using the same JVM settings and not doing anything else that could slow down the machine. This helps make sure that any changes in speed are caused by the algorithms and not by outside factors.
Finally, I run the tests more than once and focus on the later results, which are less affected by the JVM warm-up. I use a benchmarking tool called JMH (Java Microbenchmark Harness) sometimes. It is made for accurate testing of Java speed. To get a full picture of how well the algorithms work, I also try them with different types and sizes of data. This method helps me reduce problems linked to JVM warm-up, which makes it easier for me to see how well Merge Sort and QuickSort work in Java.

Critical Operations 
I often use the number of comparisons as the most important process when judging how well sorting algorithms like Merge Sort and QuickSort work. This choice is based on many important factors. To begin, similarities are a key feature that all sorting algorithms have in common, which makes them a useful way to judge things. This is very important because it lets you compare different sorting methods in a fair and uniform way. Also, the time complexity of the algorithm is directly linked to the number of comparisons that are done during the sorting process. In Merge Sort, each merge step involves comparing parts from two halves. In QuickSort, on the other hand, the division phase needs comparisons to set elements in order around the pivot. Counting these comparisons gives me a clear and measurable way to judge how well these algorithms work by getting close to their theoretical time complexity in real life. 
Focusing on comparisons also gives you unique information about how each algorithm works and how it behaves that you might not get when studying other processes. Counting comparisons show that Merge Sort always uses the divide-and-conquer method, no matter what kind of data it is given. It talks about how QuickSort's pivot selection and partitioning speed affect how well it works overall. It's important to note that this figure doesn't change based on hardware or implementation details. Unlike measuring execution time, which can change based on the hardware or how it is implemented, comparisons give a measure that doesn't depend on the hardware. This is important for studying these algorithms theoretically and getting a sense of their basic features, which are not affected by things like processing power or memory speed. I hope that this way will help me get a completer and more unbiased picture of how well each algorithm works, especially when it comes to different kinds of input data.
![image](https://github.com/Khakipapi/CMSC451/assets/74410806/9b0140df-38b6-4b19-9cb7-95be4de91018)

 
Merge Sort Graph:
The graph shows that as the size of the data set grows, so does the number of critical processes. This is in line with what you'd expect from Merge Sort, which works in O(n log n) time.
As assumed, the execution time goes up as the size of the data set does.
It looks like the variation in execution time changes over time, with some data set sizes having a lot more variation than others.
 ![image](https://github.com/Khakipapi/CMSC451/assets/74410806/8a689d16-3af0-4180-97c3-6463f9118a59)

Quick Sort Graph:
Like the size of the data set, the number of important processes for Quick Sort goes up as well. However, there are some changes that could be caused by the pivot choice or the way the data set was initially organized.
The execution time follows a similar pattern, with changes that show performance can change depending on how the data is distributed or which pivot is chosen.
The coefficient of variance for Quick Sort is not always the same. This means that the original order of the data may have a bigger effect on how well Quick Sort works than on Merge Sort.

Comparison in Performance:
Merge Sort: If the coefficient of variance is zero, it means that the number of crucial processes stays the same no matter what the input size is. This is what you would expect since Merge Sort compares things based on the size of the set of data rather than the order in which they were entered.
Quick Sort: The number of important processes changes, as shown by coefficients of variance that are not zero. This shows that the starting order of the data changes how well Quick Sort works, which is why the number of compares changes.
 
Execution Time:
When you use Merge Sort, the time it takes to run depends on how much data you have. This could be because of things like the extra work that goes into copying arrays during the merge steps or changes in how much space is used in the processor cache.
Quick Sort: Like Merge Sort, the time it takes to run increases with the size of the data set, but the changes are bigger. In line with what we know about how Quick Sort works in theory, the performance can change a lot based on which pivot is chosen.

Significance of the coefficient of variance results:
When I do my research, the coefficient of variance (CV) tells me how different the data points are from the mean. When comparing the spread between datasets that may have different scales or units, this figure comes in very handy. When it comes to sorting algorithm success, looking at the CV helps me figure out how consistently the algorithm works.
For Merge Sort, I saw that the CV for critical processes was always zero, no matter what size dataset I looked at. This is what I expected from this algorithm. The number of comparisons that Merge Sort does is set by the size of the data set, not by how it was initially ordered. This means that the number of critical processes should not change. On the other hand, there was some variation in the CV for execution time. This suggests that things outside of the algorithm itself, like the performance of the JVM or the load on the system, may be affecting the real run times.
On the other hand, Quick Sort had a non-zero CV for the key operations count, which meant that its performance was not always the same. This shows how Quick Sort depends on the pivot choice, which can mean a different number of steps depending on how the pivot splits the dataset. This means that Quick Sort's performance depends more on the order of the data you give it at the start, which makes execution times less predictable.
The most important thing to learn from the CV results is how sensitive the algorithms are to data. Merge Sort has a low CV in key operations, which means that the number of operations is stable and predictable no matter what the input is. This is a good trait to have in situations where performance consistency is important. On the other hand, Quick Sort's higher CV means that its performance can change more rapidly when different inputs are used. This knowledge is very important when picking the right sorting algorithm for a job. For example, if I know ahead of time what the data is like, I might choose Merge Sort because it works well in most situations or Quick Sort because it works well in most situations, but I would still be aware of the worst-case scenarios.
Overall, these new information about the CV is very helpful because it helps us use these methods in real life, even though they are more complicated than they seem in theory. They help me think about not only how fast an algorithm can run on average, but also how it might run differently in different cases, which is just as important for making sure that software works well.

Results compare to Big-Θ :
The theory mostly matches what I see in practice, but there are a few differences when I look at my real results next to the Big-Θ  analysis.
 
Merge Sort works pretty well. That's exactly how my important tasks get faster as the data gets bigger, just like the Big-Θ  tells me to expect. The theory says it should be consistent and reliable, and it is. It makes sense that Merge Sort will act the same way no matter what kind of data I give it because the key operation count doesn't change.
In Quick Sort, things get a little brighter. The theory says that O(n log n) is the most likely case, and I do see that trend most of the time. But the number of key operations can change a lot, and the Big-¸ analysis doesn't really show this. This is because Quick Sort can go wrong if the pivot isn't picked correctly, which leads to the worst-case O(n²) situation. The average case is what I thought it would be, but the real runtimes show that Quick Sort has bad days, especially when datasets are small or not in the right order.
This teaches me that the Big-Θ  is a good starting point, but it doesn't tell me everything. You know what the normal weather is like for a place but not if it will rain next Thursday type of sitiuation. The tests I do in the real world give me that extra information and show me that these programs can handle anything I give them.

In conclusion, after delving into the data and crunching the figures, I've gained a deeper understanding of the sorting algorithms, particularly in terms of how they work in the actual world against what the Big-Θ  analysis tells us.
My observations for Merge Sort confirm what the textbooks say: it's dependable and its performance is as consistent as a clock, with the number of operations scaling exactly as the O(n log n) complexity implies. This is exactly what I expected from an algorithm that doesn't care if the data is sorted, reversed, or utterly random.
Quick Sort revealed its eccentric side to me. In theory, it may be as efficient as Merge Sort on average, but my tests revealed that it's a bit of a gamble. It sorts quickly at times and slowly at others, depending on how lucky it is with the pivot. The non-zero coefficient of variation in the crucial operation counts highlights this variance. 
This project has served as a reminder of the difference between theoretical analysis and empirical data. The Big- is a fantastic place to start, but it doesn't necessarily give the whole story. You must roll up your sleeves and put things through their paces to see how algorithms work under various scenarios. This type of hands-on analysis can help us make educated selections about the sorting algorithm to utilize for the task at hand.

Reference: 
Baeldung, & Baeldung. (2023, November 15). How to warm up the JVM | Baeldung. Baeldung. https://www.baeldung.com/java-jvm-warmup
GeeksforGeeks. (2022, June 13). Java program for QuickSort. https://www.geeksforgeeks.org/java-program-for-quicksort/
GeeksforGeeks. (2023a, July 28). Java program for Merge sort. https://www.geeksforgeeks.org/java-program-for-merge-sort/
GeeksforGeeks. (2023b, November 2). Analysis of algorithms big big theta notation. https://www.geeksforgeeks.org/analysis-of-algorithms-big-theta-notation/
Zaric, S. (2023, September 7). How to write data analysis reports in 9 easy steps. Databox. https://databox.com/data-analysis-report






