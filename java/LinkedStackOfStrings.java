import static java.lang.System.out;

/**
 * A Stack of Strings by linked-list.
 *  
 * <h5>Lecture: Stacks (Week 2)</h5>
 *  
 * <p>
 *   LIFO (Last in First out), examine the item most recently added.
 *   Every operation takes a constant time in the worst case.
 *   But there is a little memory overhead, because <code>Node</code>.
 * </p>
 * 
 * @author eder.magalhaes
 */
public class LinkedStackOfStrings {

    private Node first = null;

    //linked-list node
    private class Node {
        String item;
        Node next;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    //put string on top
    public void push(String s) {
        Node old = first;
        first = new Node();
        first.item = s;
        first.next = old;
    }
    
    //remove and return the top string
    public String pop() {
        String s = first.item;
        first = first.next;
        return s;
    }
    
    public static void main(String[] args) {
        LinkedStackOfStrings stack = new LinkedStackOfStrings();
        stack.push("E");
        stack.push("X");
        stack.push("A");
        stack.push("M");
        stack.push("P");
        stack.push("L");
        stack.push("E");
        
        while (!stack.isEmpty()) {
            out.printf("%s ",stack.pop());
        }
    }
}