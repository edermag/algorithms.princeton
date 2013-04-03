import static java.lang.System.out;

/**
 * Implements a Stack using array, with fixed size.
 *  
 * <h5>Lecture: Generics (Week 2)</h5>
 * 
 * <p>
 *   This uses the same idea of <code>FixedCapacityStackOfStrings</code>, 
 *   but flexible with parameterized type.
 * </p>
 * 
 * @see FixedCapacityStackOfStrings.java
 * @author eder.magalhaes
 *
 * @param <Item> parameterized type of objects on the stack.
 */
public class FixedCapacityStack<Item> {

    private Item[] data;
    private int N;

    @SuppressWarnings("unchecked")
    public FixedCapacityStack(int capacity) {
        data = (Item[]) new Object[capacity]; //work around
    }

    public boolean isEmpty() {
        return N == 0;
    }
	
    public void push(Item item) {
        data[N++] = item;
    }
    
    public Item pop() {
    	Item item = data[--N];
        data[N] = null;
        return item;
    }
    
    public static void main(String[] args) {
        FixedCapacityStack<Integer> numbers = new FixedCapacityStack<Integer>(5);
        numbers.push(22);
        numbers.push(81);
        numbers.push(99);
        numbers.push(57);
        numbers.push(34);
        
        while (!numbers.isEmpty()) {
            out.printf("%s ",numbers.pop());
        }
    }
}

