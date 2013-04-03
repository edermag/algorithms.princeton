import static java.lang.System.out;

import java.util.Arrays;

/**
 * Another way to implement 3-Sum algorithm, using binary search.
 * 
 * <h5>Lecture: Order-of-Growth Classifications (Week 1)</h5>
 * 
 * <p>Improve performance using binary search. A program with N^2 log N running time.</p>
 * 
 * @see ThreeSum.java
 * @see BinarySearch.java
 * 
 * @author eder.magalhaes
 */
public class ThreeSumFast {

    public static int count(int[] a) {
        int N = a.length;
        Arrays.sort(a);
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
            	int k = BinarySearch.search(-(a[i] + a[j]), a);
            	if (k > j) {
            		count++;
            	}
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] data = new int[] { 30, -40, -20, -10, 40, 0, 10, 5 };

        out.println(count(data));
    }
}
