package matalot;

import static org.junit.jupiter.api.Assertions.*;

class UndoableStringBuilderTest {

    @org.junit.jupiter.api.Test
    void append() {
        UndoableStringBuilder usb = new UndoableStringBuilder();
        assertEquals("", usb.toString());
        usb.append("123");
        assertNotEquals("", usb.toString());
        assertEquals("123", usb.toString());
        usb.append("hello");
        assertEquals("123hello",usb.toString());


    }

    @org.junit.jupiter.api.Test
    void delete() {
        UndoableStringBuilder usb = new UndoableStringBuilder();
        assertThrows(IllegalStateException.class, () -> usb.delete(0, 3)); //this to catch the throw exception that we have in the func
        usb.append(":Maor");
        usb.delete(0, 1);
        assertEquals("Maor", usb.toString());
        usb.delete(-1, 1);
    }

    @org.junit.jupiter.api.Test
    void insert() {
        UndoableStringBuilder usb = new UndoableStringBuilder();
        usb.append("Matan");
        assertEquals("Matan",usb.toString());
        String s="Maor";
        usb.insert(2,s);
        assertEquals("MaMaortan",usb.toString());
        usb.delete(2,6);
        assertEquals("Matan",usb.toString());
        String s1=null;
        usb.insert(5,s1);
        assertEquals("Matannull",usb.toString());
        usb.insert(-1,s1);
        }

    @org.junit.jupiter.api.Test
    void replace() {
        UndoableStringBuilder usb=new UndoableStringBuilder();
        usb.append("Shay");
        assertEquals("Shay",usb.toString());
        String s=null;
        usb.replace(1,4,s);
        assertEquals("Shay",usb.toString());
        String s1="Matan";
        usb.replace(1,4,s1);
        assertEquals("SMatan",usb.toString());
        String s2="Hii";
        usb.replace(-1,2,s2);
        assertEquals("SMatan",usb.toString());
    }

    @org.junit.jupiter.api.Test
    void reverse() {
        UndoableStringBuilder usb=new UndoableStringBuilder();
        usb.reverse();
        assertEquals("",usb.toString());
        usb.append(null);
        assertEquals("null",usb.toString());
        usb.reverse();
        assertEquals("llun",usb.toString());
        usb.append("hii");
        assertEquals("llunhii",usb.toString());

    }

    @org.junit.jupiter.api.Test
    void undo() {
        UndoableStringBuilder usb=new UndoableStringBuilder();
        usb.undo();
        assertEquals("",usb.toString());
        usb.append("Shaked");
        String s="Insert";
        usb.insert(2,s);
        usb.reverse();
        usb.delete(6,8);
        assertEquals("dekatrnIhS",usb.toString());
        usb.undo();
        assertEquals("dekatresnIhS",usb.toString());
        usb.undo();
        assertEquals("ShInsertaked",usb.toString());
        usb.undo();
        assertEquals("Shaked",usb.toString());
        usb.undo();
        assertEquals("",usb.toString());
        usb.undo();
        assertEquals("",usb.toString());

    }
}