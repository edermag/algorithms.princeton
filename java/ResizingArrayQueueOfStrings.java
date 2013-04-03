import static java.lang.System.out;

/**
 * Use of resizing arrays to build a Queue of Strings.
 * 
 * <h5>Lecture: Queues (Week 2)</h5>
 * 
 * <p>Resizing works:</p>
 * <ul>
 *   <li><strong>Grow:</strong> when required doubles the size of array (<code>enqueue</code>);</li>
 *   <li><strong>Shrink:</strong> halve size of array when the array is one-half full (1/4) (<code>dequeue</code>);</li>
 * </ul>
 * 
 * <p>
 *   <strong>Invariant:</strong> array is between 25% and 100% full.
 *   Every operation takes constant amortized time and less wasted space here.
 *   But Linked-list takes constant time in the worst case.
 * </p>
 * 
 * @see LinkedQueueOfStrings.java
 * @author eder.magalhaes
 */
public class ResizingArrayQueueOfStrings {

    private String[] s;
    private int N = 0;
    private int first, last;
    
    public ResizingArrayQueueOfStrings() {
        s = new String[1];
    }
    
    public boolean isEmpty() {
    	return N == 0;
    }

    public void enqueue(String item) {
    	if (N == s.length)
            resize(2 * s.length); //repeated doubling
		
        s[last++] = item;
        
        if (last == s.length)
            last = 0; //reset last pointer
        
        N++;
    }
    
    public String dequeue() {
    	String item = s[first];
        s[first] = null;
        N--;
        first++;
        
        if (first == s.length)
            first = 0; //reset first pointer
        
        //check the size (shrink)
        if (N > 0 && N == s.length/4)
            resize(s.length / 2);
		
        return item;
    }
    
    private void resize(int capacity) {
        //cost of inserting first N items is ~ 3N
        String[] copy = new String[capacity];
        
        for (int i=0; i < N; i++)
            copy[i] = s[first + i]; //copy items from first point
        
        s = copy;
        first = 0;
        last = N;
    }
    
    public static void main(String[] args) {
    	ResizingArrayQueueOfStrings queue = new ResizingArrayQueueOfStrings();
    	queue.enqueue("R");
    	queue.enqueue("E");
    	queue.enqueue("S");
    	queue.enqueue("I");
    	queue.enqueue("Z");
    	queue.enqueue("I");
    	queue.enqueue("N");
    	queue.enqueue("G");
    	queue.enqueue("Q");
    	queue.enqueue("U");
    	queue.enqueue("E");
    	queue.enqueue("U");
    	queue.enqueue("E");
    	
    	while (!queue.isEmpty())
    	    out.printf("%s ",queue.dequeue());
    }
}
