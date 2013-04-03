import static java.lang.System.out;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements a Stack (LIFO) with linked-list.
 *  
 * <h5>Lecture: Generics (Week 2)</h5>
 * 
 * <p>
 *   This uses the same idea of <code>LinkedStackOfStrings</code>, 
 *   but flexible with parameterized type.
 * </p>
 * 
 * <p>This API was improved on lecture about Iterators.</p>
 * 
 * @author eder.magalhaes
 *
 * @param <Item> parameterized type of objects on the stack.
 */
public class Stack<Item> implements Iterable<Item>{

    private Node first = null;

    private class Node {
        Item item;
        Node next;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public void push(Item item) {
        Node old = first;
        first = new Node();
        first.item = item;
        first.next = old;
    }
    
    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
    }
    
    /**
     * Implements a iterator for Stack, using LIFO.
     * 
     * <h5>Lecture: Iterators (Week 2)</h5>
     */
    private class ListIterator implements Iterator<Item> {
        //pointer begins with first
        private Node current = first;
        
        @Override
        public boolean hasNext() {
            return current != null;
        }
        
        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
        	
            //similar to pop
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
        Stack<Integer> numbers = new Stack<Integer>();
        numbers.push(50);
        numbers.push(20);
        numbers.push(63);
        numbers.push(92);
        numbers.push(81);
        numbers.push(12);
        numbers.push(7);
        
        for (Integer i: numbers) {
            out.printf("%s ",i);
        }
	}
}
