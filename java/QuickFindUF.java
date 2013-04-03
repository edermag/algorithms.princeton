import static java.lang.System.out;

/**
 * Implements Union Find (dynamic connectivity) using Quick-find algorithm.
 * 
 * <h5>Lecture: Quick Find (Week 1)</h5>
 * 
 * <h4>Costs</h4>
 * <ul>
 *   <li><strong>Initialize:</strong> N;</li>
 *   <li><strong>Union:</strong> N;</li>
 *   <li><strong>Find:</strong> 1;</li>
 * </ul>
 * 
 * <p>Union operation is too expensive!</p>
 * 
 * @author eder.magalhaes
 */
public class QuickFindUF {

    private int[] id;
    private int count;

    public QuickFindUF(int N) {
        count = N;
        id = new int[N];
        
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    public int count() {
        return this.count;
    }

    public int find(int p) {
        return id[p];
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        if (connected(p, q))
            return;
        
        int pValue = id[p];
        int qValue = id[q];
        for (int i = 0; i < id.length; i++)
            if (id[i] == pValue) //change all 'p' id...
            	id[i] = qValue;
        
        count--;
    }

    public static void main(String[] args) {
        QuickFindUF uf = new QuickFindUF(10);
        uf.union(1, 9);
        uf.union(6, 0);
        uf.union(4, 5);
        uf.union(2, 9);
        uf.union(9, 7);
        uf.union(3, 6);
        
        out.print("After 6 unions: ");
        for (int i: uf.id)
        	out.print(i+" ");
        
        out.printf("%n%s components connected", uf.count());
    }

}
