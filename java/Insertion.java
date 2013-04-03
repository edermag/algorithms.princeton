import static java.lang.System.out;

/**
 * Implements a <i>Insertion sort</i>.
 * 
 * <h5>Lecture: Insertion sort (Week 3)</h5>
 * 
 * <p>
 *  In this algorithms i iterates over entries from left to right. 
 *  Then j iterate over the each element on right, looking for the smaller than i.
 * </p>
 * 
 * <p>In the <strong>best case</strong>, if the array is in ascending order, 
 * insertion sort makes N-1 compares and 0 exchanges.</p>
 * 
 * <p>In the <strong>worst case</strong>, if the array is in descending order, 
 * insertion sort makes ~1/2 N ^ 2 compares and ~1/2 N ^ 2 exchanges. Quadratic!</p>
 * 
 * @author eder.magalhaes
 */
public class Insertion {

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j-1]))
                    exch(a, j, j-1);
                else
                    break;
            }
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
        Integer[] data = new Integer[] {15, 41, 59, 96, 98, 35, 87, 80, 91, 74};
        sort(data);
        
        out.print("Array sorted by Insertion sort: ");
        for (Integer x: data) {
            out.printf("%s ",x);
        }
    }
}
