import static java.lang.System.out;


/**
 * Implements a <i>Quicksort</i> Dijkstra 3-way. Solution for an array with duplicate keys.
 * 
 * <h5>Lecture: Duplicate keys (Week 3)</h5>
 * 
 * @author eder.magalhaes
 */
public class QuickSort3Way {

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++ )
            if (less(a[i], a[i-1]))
                return false;
        
        return true;
    }
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }
    
    private static void sort (Comparable[] a, int lo, int hi) {
        if (hi <= lo)
            return;
        
        int lt = lo, gt = hi;
        
        Comparable v = a[lo];
        int i = lo;
        
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            
            if (cmp < 0)
                exch(a, lt++, i++);
            else if (cmp > 0)
                exch(a, i, gt--);
            else 
                i++;
        }
        
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }
    
    public static void main(String[] args) {
        Integer data[] = new Integer[] {50, 50, 70, 38, 50, 19, 65, 17, 92, 95};
        sort(data);
        
        out.print("Array sorted by Quicksort 3-way: ");
        for (Integer x: data) {
            out.printf("%s ",x);
        }
    }

}
