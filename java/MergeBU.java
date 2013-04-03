import static java.lang.System.out;

/**
 * Implements a <i>Bottom-up Mergesort</i>, without recursion.
 * 
 * <h5>Lecture: Bottom-up Mergesort (Week 3)</h5>
 * 
 * @see Merge.java
 * @author eder.magalhaes
 */
public class MergeBU {

    private static Comparable[] aux;
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++ )
            if (less(a[i], a[i-1]))
                return false;
        
        return true;
    }
    
    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        //copy the array
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        
        int i = lo, j = mid + 1;
        
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }
    
    public static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        
        for (int sz = 1; sz < N; sz = sz + sz)
            for (int lo = 0; lo < N-sz; lo += sz + sz)
                merge(a, lo, lo+sz+1, Math.min(lo+sz+sz-1, N-1));
    }
    
    public static void main(String[] args) {
        Integer[] data = new Integer[] {55, 18, 31, 79, 48, 88, 81, 38, 10, 90, 84, 44};
        sort(data);
        
        out.print("Array sorted by Bottom-up Mergesort: ");
        for (Integer x: data) {
            out.printf("%s ",x);
        }
    }
}
