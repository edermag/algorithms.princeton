import static java.lang.System.out;

/**
 * 3-Sum brute-force algorithm
 * 
 * <h5>Lecture: Observations (Week 1)</h5>
 * 
 * <p>This is a cubic algorithm.</p>
 * 
 * @author eder.magalhaes
 */
public class ThreeSum {

    public static int count(int[] a) {
        int N = a.length;
        int count = 0;
        for (int i = 0; i < N; i++) { //N
            for (int j = i + 1; j < N; j++) { //N^2
                for (int k = j + 1; k < N; k++) { //N^3
                    if (a[i] + a[j] + a[k] == 0) {
                    	count++;
                    }
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
