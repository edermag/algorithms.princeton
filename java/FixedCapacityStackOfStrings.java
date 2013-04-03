import static java.lang.System.out;

/**
 * A Stack of Strings by array (fixed).
 *  
 * <h5>Lecture: Stacks (Week 2)</h5>
 * 
 * <p>This is simple way to implement a Stack, with fixed capacity.</p>
 * <p>Poor than <code>LinkedStackOfStrings</code>, but saves memory.</p>
 * 
 * @see LinkedStackOfStrings.java
 * @author eder.magalhaes
 */
public class FixedCapacityStackOfStrings {

    private String[] data;
    private int N;

    public FixedCapacityStackOfStrings(int capacity) {
        data = new String[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }
	
    public void push(String item) {
        data[N++] = item;
    }
    
    public String pop() {
        String item = data[--N];
        data[N] = null;
        return item;
    }
    
    public static void main(String[] args) {
    	FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(10);
    	stack.push("F");
    	stack.push("I");
    	stack.push("X");
    	stack.push("E");
    	stack.push("D");
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

