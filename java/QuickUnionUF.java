import static java.lang.System.out;

/**
 * Another way to implement Union Find, using Quick-union.
 * 
 * <h5>Lecture: Quick Union (Week 1)</h5>
 * 
 * <h4>Costs</h4>
 * <ul>
 *   <li><strong>Initialize:</strong> N;</li>
 *   <li><strong>Union:</strong> N;</li>
 *   <li><strong>Find:</strong> N;</li>
 * </ul>
 * 
 * <p>Find operation is too expensive!</p>
 * 
 * <p>Link components by root id.
 * 
 * @author eder.magalhaes
 */
public class QuickUnionUF {

    private int[] id;
    private int count;

    public QuickUnionUF(int N) {
        count = N;
        id = new int[N];
        
        for (int i = 0; i < N; i++)
            id[i] = i;
    }
	
    public int count() {
        return this.count;
    }
	
    private int root(int i) {
        //look for the root
        while (i != id[i]) {
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

        id[i] = j;
        count--;
    }
	
    public static void main(String[] args) {
        QuickUnionUF uf = new QuickUnionUF(10);
        uf.union(8, 1);
        uf.union(7, 0);
        uf.union(6, 2);
        uf.union(1, 3);
        uf.union(4, 9);
        uf.union(4, 1);
        uf.union(6, 0);
        uf.union(7, 1);
        uf.union(9, 5);
		
        out.print("After 9 unions: ");
        for (int i: uf.id)
            out.print(i+" ");
        
        out.printf("%n%s components connected", uf.count());
    }

}
