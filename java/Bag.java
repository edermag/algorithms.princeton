import static java.lang.System.out;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Another kind of collections. In Bag the order doesn't matter.
 * 
 * <h5>Lecture: Iterators (Week 2)</h5>
 * 
 * <p>In this case Bag works like a stack.</p>
 * 
 * @author eder.magalhaes
 * 
 * @param <Item> parameterized type of objects on the stack.
 */
public class Bag<Item> implements Iterable<Item> {

    private int N;
    private Node first;
    
    private class Node {
        private Item item;
        private Node next;
    }
    
    public void add(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }
    
    public int size() {
        return N;
    }
    
    public boolean isEmpty() {
    	return first == null;
    }
	
    /**
     * Implements a iterator for Stack, using LIFO.
     * 
     * <h5>Lecture: Iterators (Week 2)</h5>
     */
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        
        @Override
        public boolean hasNext() {
            return current != null;
        }
        
        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            
            Item item = current.item;
            current = current.next;
            return item;
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    
    public static void main(String[] args) {
        Bag<Integer> bag = new Bag<Integer>();
        bag.add(-20);
        bag.add(17);
        bag.add(31);
        bag.add(99);
        
        for (Integer x: bag) {
            out.printf("%s ",x);
        }
    }
}