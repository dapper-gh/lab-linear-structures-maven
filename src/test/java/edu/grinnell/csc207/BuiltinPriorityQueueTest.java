package edu.grinnell.csc207;

import org.junit.jupiter.api.Test;
import java.util.Comparator;

import edu.grinnell.csc207.linear.BuiltinPriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class StringComparator implements Comparator<String> {
    public int compare(String str1, String str2) {
      // Efficiency hack: If two strings occupy the same memory
      // they are equal.
      if (str1 == str2) { return 0; }
      // Safety check: If either string is null, compareTo may fail,
      // so we make sure neither is null.  We treat null as "smaller"
      // than any other string.
      if (str1 == null) { return -1; }
      if (str2 == null) { return 1; }
      // Finally, we can use the built-in `compareTo` method.
      return str1.compareTo(str2);
    } // compare(String, STring)
  } // StringComparator
  
  class IntComparator implements Comparator<Integer> {
    public int compare(Integer i, Integer j) {
      // While this method sometimes gets implemented as i-j, that
      // implementation presents overflow risks, so we choose a
      // somewhat more verbose approach.
      if (i < j) { return -1; }
      else if (j < i) { return 1; }
      else return 0;
    } // compare(Integer, Integer)
  } // IntegerComparator

public class BuiltinPriorityQueueTest {
    @Test
    public void basicTest() throws Exception {
        BuiltinPriorityQueue<Integer> pq = new BuiltinPriorityQueue<Integer>(8, new IntComparator());

        pq.put(0);
        pq.put(-1);
        pq.put(2);
        pq.put(0);

        assertEquals(-1, pq.peek(), "First element peeked should be -1");
        assertEquals(-1, pq.get(), "First element should be -1");

        assertEquals(0, pq.peek(), "Second element peeked should be 0");
        assertEquals(0, pq.get(), "Second element should be 0");

        assertEquals(0, pq.peek(), "Third element peeked should be 0");
        assertEquals(0, pq.get(), "Third element should be 0");

        assertEquals(2, pq.peek(), "Fourth element peeked should be 2");
        assertEquals(2, pq.get(), "Fourth element should be 2");
    } // basicTest()

    @Test
    public void isFullTest() throws Exception {
        BuiltinPriorityQueue<Integer> pq = new BuiltinPriorityQueue<Integer>(2, new IntComparator());

        pq.put(0);
        pq.put(1);

        assertTrue(pq.isFull(), "Should be full after adding 2 elements");
    } // isFullTest()

    @Test
    public void isEmptyTest() throws Exception {
        BuiltinPriorityQueue<Integer> pq = new BuiltinPriorityQueue<Integer>(8, new IntComparator());

        pq.put(2);
        pq.put(-1);
        pq.put(2);

        pq.get();
        pq.get();
        pq.get();

        assertTrue(pq.isEmpty(), "Should be empty after removing all three elements");
    } // isEmptyTest()


    @Test
    public void testMaxValues() throws Exception {
        BuiltinPriorityQueue<Integer> pq = new BuiltinPriorityQueue<Integer>(8, new IntComparator());

        pq.put(Integer.MIN_VALUE);
        pq.put(Integer.MAX_VALUE);
        pq.put(Integer.MAX_VALUE - 1);
        pq.put(Integer.MIN_VALUE + 1);

        assertEquals(Integer.MIN_VALUE, pq.get(), "First element should be MIN_VALUE");
        assertEquals(Integer.MIN_VALUE + 1, pq.get(), "Second element should be MIN_VALUE + 1");
        assertEquals(Integer.MAX_VALUE - 1, pq.get(), "Third element should be MAX_VALUE - 1");
        assertEquals(Integer.MAX_VALUE, pq.get(), "Fourth element should be MAX_VALUE");
    }
}
