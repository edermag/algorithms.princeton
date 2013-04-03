import static java.lang.System.out;

/**
 * Implements a <i>Shellsort</i>.
 * 
 * <h5>Lecture: Shellsort (Week 3)</h5>
 * 
 * <p>This algorithms move entries more than one position at a time by h-sorting the array.</p>
 * 
 * <p>Works like a Insertion sort, but compare elements in boundary (subarray).</p>
 * 
 * <p>In the <strong>worst case</strong> the number of compares used by shellsort 
 *  with 3x + 1 increment is O(N ^ 3/2).</p>
 * 
 * @author eder.magalhaes
 */
public class Shell {

    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1; //boundary subarray
        
        while (h < N/3)
            h = 3 * h + 1; //1, 4, 13, 40 ...
        
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                //insertion sort
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                exch(a, j, j - h);
            }
            
            h = h / 3;
        }
    }
	
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
    public static void main(String[] args) {
    	Integer[] data = new Integer[] {95, 29, 57, 82, 41, 83, 52, 21, 94, 79};
        sort(data);
        
        out.print("Array sorted by Shellsort: ");
        for (Integer x: data) {
            out.printf("%s ",x);
        }
    }
}
