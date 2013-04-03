import static java.lang.System.out;

/**
 * A Queue of Strings by linked-list.
 *  
 * <h5>Lecture: Queues (Week 2)</h5>
 *  
 * <p>
 *   FIFO (First in First out), examine the item least recently added.
 *   Every operation takes a constant time in the worst case.
 *   But there is a little memory overhead, because <code>Node</code>.
 * </p>
 * 
 * @author eder.magalhaes
 */
public class LinkedQueueOfStrings {

    //2 directions
    private Node first, last;

    //linked-list node
    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }
	
    //put the string on the end of queue
    public void enqueue(String s) {
        Node oldlast = last;
        last = new Node();
        last.item = s;
        last.next = null;
        
        if (isEmpty())
            first = last;
        else
            oldlast.next = last;
    }
    
    //remove and return the first string
    public String dequeue() {
        String s = first.item;
        first = first.next;
        if (isEmpty())
            last = null;
        return s;
    }
    
    public static void main(String[] args) {
    	LinkedQueueOfStrings queue = new LinkedQueueOfStrings();
    	queue.enqueue("E");
    	queue.enqueue("X");
    	queue.enqueue("A");
    	queue.enqueue("M");
    	queue.enqueue("P");
        queue.enqueue("L");
        queue.enqueue("E");
        
        while (!queue.isEmpty()) {
            out.printf("%s ",queue.dequeue());
        }
    }
    
}
