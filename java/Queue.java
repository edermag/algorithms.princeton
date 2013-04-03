import static java.lang.System.out;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements a Queue (FIFO) with linked-list.
 * 
 * <h5>Lecture: Generics (Week 2)</h5>
 * 
 * <p>
 *   This uses the same idea of <code>LinkedQueueOfStrings</code>, 
 *   but flexible with parameterized type.
 * </p>
 * 
 * <p>This API was improved on lecture about Iterators.</p>
 * 
 * @author eder.magalhaes
 *
 * @param <Item> parameterized type of objects on the stack.
 */
public class Queue<Item> implements Iterable<Item> {

    private Node first, last;
    
    private class Node {
        private Item item;
        private Node next;
    }
	
    public boolean isEmpty() {
        return first == null;
    }
    
    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        
        if (isEmpty())
            first = last;
        else
            oldlast.next = last;
    }
    
    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        
        if (isEmpty()) 
            last = null;
		
        return item;
    }
    
    /**
     * Implements a iterator for Queue, using FIFO.
     * 
     * <h5>Lecture: Iterators (Week 2)</h5>
     */
    private class QueueIterator implements Iterator<Item> {
        
        private Node current = first;
        
        public boolean hasNext() {
            return current != null;
        }
        
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            
            Item item = current.item;
            current = current.next;
            return item;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
	
    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    public static void main(String[] args) {
    	Queue<String> queue = new Queue<String>();
    	queue.enqueue("E");
    	queue.enqueue("X");
    	queue.enqueue("A");
    	queue.enqueue("M");
    	queue.enqueue("P");
        queue.enqueue("L");
        queue.enqueue("E");
        
        for (String s: queue) {
            out.printf("%s ",s);
        }
    }
}
