import static java.lang.System.out;

/**
 * Implements a <i>Selection sort</i>.
 * 
 * <h5>Lecture: Selection sort (Week 3)</h5>
 * 
 * <p>
 *  This algorithms scans entries from left to right.
 *  Put the entries on left in ascending order.
 * </p>
 * 
 * <p>Selection Sort uses (N-1) + (N - 2) + ... + 1 + 0 ~ N² / 2 compares and N exchanges.</p>
 * 
 * <p>Running time insensitive to input. Quadratic time, even if input is sorted.</p>
 * 
 * @author eder.magalhaes
 */
public class Selection {

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
			
            for (int j = i+1; j < N; j++) {
                if (less(a[j], a[min]))
                    min = j;
            }
            exch(a, i, min);
        }
    }
    
    //check if the first argument is smaller than second
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    //swap elements (i by j)
    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
    public static void main(String[] args) {
        Integer[] data = new Integer[] {28, 96, 51, 86, 69, 68, 42, 24, 52, 27};
        sort(data);
        
        out.print("Array sorted by Selecion sort: ");
        for (Integer x: data) {
            out.printf("%s ",x);
        }
    }

}
