import static java.lang.System.out;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Implements a Queue (FIFO) with resizing array.
 * 
 * <h5>Lecture: Generics (Week 2)</h5>
 * 
 * <p>
 *   This uses the same idea of <code>ResizingArrayQueueOfStrings</code>, 
 *   but flexible with parameterized type.
 * </p>
 * 
 * <p>This API was improved on lecture about Iterators.</p>
 * 
 * @author eder.magalhaes
 *
 * @param <Item> parameterized type of objects on the stack.
 */
public class ResizingArrayQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int N = 0;
    private int first, last;
    
    @SuppressWarnings("unchecked")
    public ResizingArrayQueue() {
        items = (Item[]) new Object[1];
    }
    
    public boolean isEmpty() {
    	return N == 0;
    }

    public void enqueue(Item item) {
    	//check the size (grow)
    	if (N == items.length)
            resize(2 * items.length);
		
    	items[last++] = item;
        
        if (last == items.length)
            last = 0;
        
        N++;
    }
    
    public Item dequeue() {
    	Item item = items[first];
        items[first] = null;
        N--;
        first++;
        
        if (first == items.length)
            first = 0;
        
        //check the size (shrink)
        if (N > 0 && N == items.length/4)
            resize(items.length / 2);
		
        return item;
    }
    
    private void resize(int capacity) {
    	@SuppressWarnings("unchecked")
        Item[] copy = (Item[]) new Object[capacity];
        
        for (int i=0; i < N; i++)
            copy[i] = items[first + i]; //copy items from first point
        
        items = copy;
        first = 0;
        last = N;
    }
    
    /**
     * Implements a iterator for Queue, using FIFO.
     * 
     * <h5>Lecture: Iterators (Week 2)</h5>
     */
    private class ArrayIterator implements Iterator<Item> {
        private int i = first;
        
        @Override
        public boolean hasNext() {
            return i < N;
        }
        
        @Override
        public Item next() {
            if (!hasNext()) 
                throw new NoSuchElementException();
            Item item = items[i + first];
            i++;
            return item;
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }
    
    public static void main(String[] args) {
    	ResizingArrayQueue<String> queue = new ResizingArrayQueue<String>();
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
    	
    	for (String s: queue) {
    	    out.printf("%s ",s);
    	}
    }
}
