import static java.lang.System.out;

/**
 * Implements Union Find using Quick-union, but with improvements.
 * 
 * <h5>Lecture: Quick Union Improvements (Week 1)</h5>
 * 
 * <h4>Costs</h4>
 * <ul>
 *   <li><strong>Initialize:</strong> N;</li>
 *   <li><strong>Union:</strong> lg N;</li>
 *   <li><strong>Find:</strong> lg N;</li>
 * </ul>
 * 
 * <p><strong>Improvement 1:</strong> modify quick-union to avoid tall trees. Link the root of smaller tree to the root of larger tree.</p>
 * <p><strong>Improvement 2:</strong> path compression, point the subtree to root component. Reduce levels.</p>
 * 
 * @see QuickUnionUF.java
 * @author eder.magalhaes
 */
public class QuickUnionWeightedUF {

    private int[] id;
    private int[] sz; //number of components in subtree
    private int count;

    public QuickUnionWeightedUF(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
		
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }
    
    public int count() {
        return this.count;
    }
    
    private int root(int i) {
        while (i != id[i]) {
            //path compression
        	id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
    
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
	
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        
        if (i == j) return;

        //choose the largest tree
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }
    
    public static void main(String[] args) {
        QuickUnionWeightedUF uf = new QuickUnionWeightedUF(10);
        uf.union(4, 6);
        uf.union(6, 0);
        uf.union(6, 1);
        uf.union(6, 2);
        uf.union(6, 3);
        uf.union(7, 4);
        uf.union(5, 9);
        uf.union(5, 7);
        uf.union(6, 5);
        uf.union(6, 8);
		
        out.print("After 10 unions: ");
        for (int i: uf.id)
            out.print(i+" ");
        
        out.printf("%n%s components connected", uf.count());
    }

}