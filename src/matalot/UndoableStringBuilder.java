package matalot;

import java.text.SimpleDateFormat;
import java.util.Stack;

/**
 * This class use the StringBuilder class methods to make our UndoableStringBuilder class
 * We crate methods using the methods that StringBuilder have
 * The idea of this class is to give us the latest thing that the object had
 * This UndoableStringBuilder class take a StringBuilder and give us the perv word or sentence that this StringBuilder had with using stack
 * @author Matan & Maor
 */

public class UndoableStringBuilder {
    private StringBuilder sb;
    private Stack<StringBuilder> stack;

    /**
     * The constructor take the objects and make new object (empty)
     */
    public UndoableStringBuilder() {
        this.sb = new StringBuilder();
        this.stack = new Stack<>();
    }

    /**
     * This function take the StringBuilder that we crate and add them a string
     * @param str the string that we add to the StringBuilder
     * @return the StringBuilder after added the string to him
     */
    public UndoableStringBuilder append(String str) {
        StringBuilder prev = new StringBuilder(sb);
        stack.push(prev);
        sb.append(str);
        return this;
    }

    /**
     *
     * @param start
     * @param end
     * @return
     */
    public UndoableStringBuilder delete(int start, int end) {
        if (sb.length() == 0) {
            throw new IllegalStateException("you cant delete from empty string");  // need to check what exception need to be here
        }
        try {
            StringBuilder prev = new StringBuilder(sb);
            sb.delete(start, end);
            stack.push(prev);
        } catch (IndexOutOfBoundsException err) {
            System.err.println(err);
            err.printStackTrace();
        }
        return this;
    }

    /**
     *
     * @param offset
     * @param str
     * @return
     */
    public UndoableStringBuilder insert(int offset, String str){
        StringBuilder prev = new StringBuilder(sb);
        try {
            stack.push(prev);
            sb.insert(offset, str);
        } catch (StringIndexOutOfBoundsException err) {
            System.err.println("your offset of:" + offset + " is out of bounds, the offset need to be: 0<=offset");
            err.printStackTrace();
        }
        return this;
    }

    /**
     *
     * @param start
     * @param end
     * @param str
     * @return
     */

    public UndoableStringBuilder replace(int start, int end, String str) {
        StringBuilder prev = new StringBuilder(sb);
        try {
            sb.replace(start, end, str);
            stack.push(prev);
        } catch (IndexOutOfBoundsException err) {
            System.err.println("the index cant lower then 0");
        }catch(NullPointerException err){
            System.err.println("cant use null for replace method!");
            err.printStackTrace();
        }
        return this;
    }

    /**
     * revrese the string
     *
     * @return the object the we did the changes of ( the StringBuilder sb)
     */
        public UndoableStringBuilder reverse() {
        // sb = "", stack = {}
//        reverse() , stack{""}
        // undo()
        // sb = ""
        StringBuilder reverseSB = new StringBuilder(sb);
        sb.reverse();
        stack.push(reverseSB);

        return this;
    }

    /**
     *
     */
    public void undo() {
        if (stack.isEmpty()) return;
        sb = stack.pop();
    }

    /**
     * @return the StringBuilder as string and not is address
     */
    @Override
    public String toString() {
        return sb.toString();
    }

    /**
     *
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        return sb.toString().equals(o);
    }

    public static void main(String[] args) {
        UndoableStringBuilder undostringBuilder = new UndoableStringBuilder();
        undostringBuilder.append("Maor");
        String s = null;
        undostringBuilder.insert(1, s);
        System.out.println(undostringBuilder);
        undostringBuilder.delete(1, 5);
        System.out.println(undostringBuilder);
        undostringBuilder.replace(2,6,s);
        System.out.println(undostringBuilder);
    }
}
