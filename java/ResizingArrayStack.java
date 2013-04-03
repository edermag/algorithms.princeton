import static java.lang.System.out;

import java.util.Iterator;

/**
 * Implements a Stack (LIFO) with resizing array.
 *  
 * <h5>Lecture: Generics (Week 2)</h5>
 * 
 * <p>
 *   This uses the same idea of <code>ResizingArrayStackOfStrings</code>, 
 *   but flexible with parameterized type.
 * </p>
 * 
 * <p>This API was improved on lecture about Iterators.</p>
 * 
 * @author eder.magalhaes
 *
 * @param <Item> parameterized type of objects on the stack.
 */
public class ResizingArrayStack<Item> implements Iterable<Item>{

    private Item[] items;
    private int N = 0;

    @SuppressWarnings("unchecked")
    public ResizingArrayStack() {
    	items = (Item[]) new Object[1];
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public void push(Item item) {
        //check the size (grow)
        if (N == items.length)
            resize(2 * items.length);
		
        items[N++] = item;
    }
    
    public Item pop(){
    	Item item = items[--N];
    	items[N] = null;
        
        //check the size (shrink)
        if (N > 0 && N == items.length/4)
            resize(items.length / 2);
		
        return item;
    }
	
    private void resize(int capacity) {
        @SuppressWarnings("unchecked")
        Item[] copy = (Item[]) new Object[capacity];
        
        for (int i=0; i < N; i++)
            copy[i] = items[i];
        
        items = copy;
    }
    
    /**
     * Implements a iterator for Stack, using LIFO with reverse iterator.
     * 
     * <h5>Lecture: Iterators (Week 2)</h5>
     */
    private class ReverseArrayIterator implements Iterator<Item> {
        
        private int i = N;
        
        @Override
        public boolean hasNext() {
            return i > 0;
        }
        
        @Override
        public Item next() {
            return items[--i];
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    @Override
    public Iterator<Item> iterator() {
    	return new ReverseArrayIterator();
    }
    
    public static void main(String[] args) {
    	ResizingArrayStack<String> stack = new ResizingArrayStack<String>();
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
        
        for (String s: stack) {
            out.printf("%s ",s);
        }
    }

}