import static java.lang.System.out;

/**
 * Improve the use of arrays to build a Stack of Strings, with resizing array.
 * 
 * <h5>Lecture: Resizing Arrays (Week 2)</h5>
 * 
 * <p>Resizing works:</p>
 * <ul>
 *   <li><strong>Grow:</strong> when required doubles the size of array (<code>push</code>);</li>
 *   <li><strong>Shrink:</strong> halve size of array when the array is one-half full (1/4) (<code>pop</code>);</li>
 * </ul>
 * 
 * <p>
 *   <strong>Invariant:</strong> array is between 25% and 100% full.
 *   Every operation takes constant amortized time and less wasted space here.
 *   But Linked-list takes constant time in the worst case.
 * </p>
 * 
 * @see FixedCapacityStackOfStrings.java
 * @author eder.magalhaes
 */
public class ResizingArrayStackOfStrings {

    private String[] s;
    private int N = 0;

    public ResizingArrayStackOfStrings() {
        s = new String[1];
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public void push(String item) {
        //check the size (grow)
        if (N == s.length)
            resize(2 * s.length); //repeated doubling
		
        s[N++] = item;
    }
    
    public String pop(){
        String item = s[--N];
        s[N] = null;
        
        //check the size (shrink)
        if (N > 0 && N == s.length/4)
            resize(s.length / 2);
		
        return item;
    }
	
    private void resize(int capacity) {
        //cost of inserting first N items is ~ 3N
        String[] copy = new String[capacity];
        
        for (int i=0; i < N; i++)
            copy[i] = s[i]; //copy items
        
        s = copy;
    }
    
    public static void main(String[] args) {
    	ResizingArrayStackOfStrings stack = new ResizingArrayStackOfStrings();
    	stack.push("R");
    	stack.push("E");
    	stack.push("S");
    	stack.push("I");
    	stack.push("Z");
    	stack.push("I");
    	stack.push("N");
    	stack.push("G");
    	stack.push("S");
    	stack.push("T");
    	stack.push("A");
    	stack.push("C");
    	stack.push("K");
        
        while (!stack.isEmpty()) {
            out.printf("%s ",stack.pop());
        }
    }
}