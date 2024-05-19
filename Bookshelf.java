// Name: Joshua Fendi
// USC NetID: 5712969966
// CSCI455 PA2
// Spring 2024

import java.util.ArrayList;

/**
 * Class Bookshelf
 * Implements idea of arranging books into a bookshelf.
 * Books on a bookshelf can only be accessed in a specific way so books don’t fall down;
 * You can add or remove a book only when it’s on one of the ends of the shelf.   
 * However, you can look at any book on a shelf by giving its location (starting at 0).
 * Books are identified only by their height; two books of the same height can be
 * thought of as two copies of the same book.
 */

public class Bookshelf {
   /**
    Representation invariant:
    height of each book >= 1, in thisPileOfBooks
    */

   // instance variables
   private ArrayList<Integer> thisPileOfBooks;

   /**
    * Creates an empty Bookshelf object i.e. with no books
    */
   public Bookshelf() {
      // initialize
      thisPileOfBooks = new ArrayList<>(); // empty ArrayList

      assert isValidBookshelf();  // sample assert statement (you will be adding more of these calls)
   }

   /**
    * Creates a Bookshelf with the arrangement specified in pileOfBooks. Example
    * values: [20, 1, 9].
    *
    * PRE:
    * pileOfBooks contains an array list of 0 or more positive numbers
    * representing the height of each book.
    */
   public Bookshelf(ArrayList<Integer> pileOfBooks) {
      // initialize
      thisPileOfBooks = new ArrayList<Integer>(pileOfBooks); // non-empty ArrayList
      assert isValidBookshelf();
   }

   /**
    * Inserts book with specified height at the start of the Bookshelf, i.e., it
    * will end up at position 0.
    *
    * PRE: height > 0 (height of book is always positive)
    */
   public void addFront(int height) {
      ArrayList<Integer> tempPileOfBooks = new ArrayList<Integer>();
      tempPileOfBooks.add(height);

      // add all books in thisPileOfBooks to tempPileOfBooks
      for(int i=0; i<thisPileOfBooks.size(); ++i) {
         tempPileOfBooks.add(thisPileOfBooks.get(i));
      }

      thisPileOfBooks = tempPileOfBooks; // asign

      assert isValidBookshelf();
   }

   /**
    * Inserts book with specified height at the end of the Bookshelf.
    *
    * PRE: height > 0 (height of book is always positive)
    */
   public void addLast(int height) {
      ArrayList<Integer> tempPileOfBooks = new ArrayList<Integer>();

      // add all books in thisPileOfBooks to tempPileOfBooks
      for(int i=0; i<thisPileOfBooks.size(); ++i) {
         tempPileOfBooks.add(thisPileOfBooks.get(i));
      }

      tempPileOfBooks.add(height);

      thisPileOfBooks = tempPileOfBooks;

      assert isValidBookshelf();
   }

   /**
    * Removes book at the start of the Bookshelf and returns the height of the
    * removed book.
    *
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeFront() {
      int frontHeight = thisPileOfBooks.get(0); // height of first book

      ArrayList<Integer> tempPileOfBooks = new ArrayList<Integer>(); // temporary pile

      // add relevent books to temp pile
      for(int i=1; i<thisPileOfBooks.size(); ++i) {
         tempPileOfBooks.add(thisPileOfBooks.get(i));
      }


      thisPileOfBooks = tempPileOfBooks; // asign

      assert isValidBookshelf();
      return frontHeight;
   }

   /**
    * Removes book at the end of the Bookshelf and returns the height of the
    * removed book.
    *
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeLast() {
      int backHeight = thisPileOfBooks.get(thisPileOfBooks.size()-1); // height of first book

      ArrayList<Integer> tempPileOfBooks = new ArrayList<Integer>(); // temporary pile

      // add relevent books to temp pile
      for(int i=0; i<thisPileOfBooks.size()-1; ++i) {
         tempPileOfBooks.add(thisPileOfBooks.get(i));
      }

      thisPileOfBooks = tempPileOfBooks; // asign

      assert isValidBookshelf();
      return backHeight;
   }

   /*
    * Gets the height of the book at the given position.
    *
    * PRE: 0 <= position < this.size()
    */
   public int getHeight(int position) {
      int pos = thisPileOfBooks.get(position);
      assert isValidBookshelf();
      return pos;
   }

   /**
    * Returns number of books on this Bookshelf.
    */
   public int size() {
      int size = thisPileOfBooks.size();
      assert isValidBookshelf();
      return size;
   }

   /**
    * Returns string representation of this Bookshelf. Returns a string with the height of all
    * books on the bookshelf, in the order they are in on the bookshelf, using the format shown
    * by example here:  “[7, 33, 5, 4, 3]”
    */
   public String toString() {
      String str = new String("");

      str += "[";

      // concatenate all of the books in thisPileOfBooks
      for(int i = 0; i < thisPileOfBooks.size(); ++i) {
         str += thisPileOfBooks.get(i);

         if(i == thisPileOfBooks.size() - 1) { break; } // avoid addition ", "
         str += ", ";
      }

      str += "]";

      assert isValidBookshelf();
      return str;
   }

   /**
    * Returns true iff the books on this Bookshelf are in non-decreasing order.
    * (Note: this is an accessor; it does not change the bookshelf.)
    */
   public boolean isSorted() {
      // no books in pile
      if(thisPileOfBooks.isEmpty()) {
         assert isValidBookshelf();
         return true;
      }

      // theres at least 1 book in pile
      int prev = thisPileOfBooks.get(0);

      for(int i=1; i<thisPileOfBooks.size(); ++i) {
         int next = thisPileOfBooks.get(i);
         if(prev > next) {
            assert isValidBookshelf();
            return false;
         } // not in ascending order
         prev = next;
      }

      assert isValidBookshelf();
      return true;
   }

   /**
    * Returns true iff the Bookshelf data is in a valid state.
    * (See representation invariant comment for more details.)
    */
   private boolean isValidBookshelf() {

      for(int height : thisPileOfBooks) {
         if(height < 1) {
            return false;
         }
      }

      return true;
   }
}

