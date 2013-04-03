import static java.lang.System.out;

/**
 * Implements a <i>Quicksort</i> (partitioning).
 * 
 * <h5>Lecture: Quicksort (Week 3)</h5>
 * 
 * <p>Another logarithmic approach to sort data.
 *  Faster than Mergesort, because of less data movement.</p>
 * 
 * @author eder.magalhaes
 */
public class QuickSort {

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
    
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        
        while (true) {
            while (less(a[++i], a[lo])) //find on left
                if (i == hi) 
                    break;
            
            while (less(a[lo], a[--j])) //find on right
                if (j == lo) 
                    break;
            
            if (i >= j) 
                break;
            
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
    
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo)
            return;
        
        int j = partition(a, lo, hi);
        sort(a, lo, j-1); //before
        sort(a, j+1, hi); //after
    }
    
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);// performance guarantee
        sort(a, 0, a.length - 1);
    }
    
    public static void main(String[] args) {
        Integer data[] = new Integer[] {72, 11, 44, 90, 43, 81, 10, 13, 94, 26, 41, 50};
        sort(data);
        
        out.print("Array sorted by Quicksort: ");
        for (Integer x: data) {
            out.printf("%s ",x);
        }
    }

}
