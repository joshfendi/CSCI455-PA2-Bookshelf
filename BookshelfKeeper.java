// Name: Joshua Fendi
// USC NetID: 5712969966
// CSCI455 PA2
// Spring 2024


/**
 * Class BookshelfKeeper
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in
 * non-decreasing order by height, with the restriction that single books can only be added
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 */
public class BookshelfKeeper {

   /**
    Representation invariant:
    - thisSortedBookshelf is sorted in non-descending order
    - totalOperationsCount >= 0
    - lastPickOrPutCount >= 0
    */

   // <add instance variables here>
   Bookshelf thisSortedBookshelf;
   int totalOperationsCount;
   int lastPickOrPutCount;


   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {
      thisSortedBookshelf = new Bookshelf();
      totalOperationsCount = 0;
      lastPickOrPutCount = 0;

      assert isValidBookshelfKeeper();
   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    *
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {
      thisSortedBookshelf = sortedBookshelf;
      totalOperationsCount = 0;
      lastPickOrPutCount = 0;

      assert isValidBookshelfKeeper();
   }

   /**
    * Removes a book from the specified position in the bookshelf and keeps bookshelf sorted
    * after picking up the book.
    *
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    *
    * PRE: 0 <= position < getNumBooks()
    */
   public int pickPos(int position) {
      Bookshelf tempShelf = new Bookshelf();
      int countMutatorCalls = 0;

      // start from front
      if(position <  getNumBooks() - position) {
         // remove front of thisSortedBookshelf and put into tempShelf
         for(int i=0; i<position; ++i) {
            tempShelf.addFront(thisSortedBookshelf.removeFront());
            ++countMutatorCalls;
         }

         thisSortedBookshelf.removeFront(); // remove picked pos
         ++countMutatorCalls;

         // put books back into thisSortedBookshelf
         while(tempShelf.size() > 0){
            thisSortedBookshelf.addFront(tempShelf.removeFront());
            ++countMutatorCalls;
         }
      }

      // start from back
      else {
         // remove back of thisSortedBookshelf and put into tempShelf
         int numBooks = getNumBooks();
         for(int i=0; i< numBooks - position - 1; ++i) {
            tempShelf.addLast(thisSortedBookshelf.removeLast());
            ++countMutatorCalls;
         }

         thisSortedBookshelf.removeLast(); // remove picked pos
         ++countMutatorCalls;

         // put books back into thisSortedBookshelf
         while(tempShelf.size() > 0){
            thisSortedBookshelf.addLast(tempShelf.removeLast());
            ++countMutatorCalls;
         }
      }

      totalOperationsCount += countMutatorCalls; // add to totalOperationsCount
      lastPickOrPutCount = countMutatorCalls; // count of last pickPos call
      assert isValidBookshelfKeeper();
      return countMutatorCalls;
   }

   /**
    * Inserts book with specified height into the shelf.  Keeps the contained bookshelf sorted
    * after the insertion.
    *
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    *
    * PRE: height > 0
    */
   public int putHeight(int height) {
      Bookshelf tempShelf = new Bookshelf();
      int countMutatorCalls = 0;


      int tempPos = thisSortedBookshelf.size() / 2;
      // if bookshelf is empty
      if(thisSortedBookshelf.size() == 0) {
         // insert height
         thisSortedBookshelf.addLast(height);
         ++countMutatorCalls;
      }
      // not empty
      else {
         // start from front
         if(height < thisSortedBookshelf.getHeight(tempPos)) {
            // remove from front until get to value where height value is <= value
            while(height > thisSortedBookshelf.getHeight(0)) {
               tempShelf.addLast(thisSortedBookshelf.removeFront());
               ++countMutatorCalls;
            }

            // insert height
            thisSortedBookshelf.addFront(height);
            ++countMutatorCalls;

            // insert the rest
            while(tempShelf.size() > 0) {
               thisSortedBookshelf.addFront(tempShelf.removeLast());
               ++countMutatorCalls;
            }
         }

         else {
            // start from back
            while(height < thisSortedBookshelf.getHeight(getNumBooks() - 1)) {
               tempShelf.addLast(thisSortedBookshelf.removeLast());
               ++countMutatorCalls;
            }

            // insert height
            thisSortedBookshelf.addLast(height);
            ++countMutatorCalls;

            // insert the rest
            while(tempShelf.size() > 0) {
               thisSortedBookshelf.addLast(tempShelf.removeLast());
               ++countMutatorCalls;
            }
         }
      }


      totalOperationsCount += countMutatorCalls; // add to totalOperationsCount
      lastPickOrPutCount = countMutatorCalls; // count of last putHeight call
      assert isValidBookshelfKeeper();
      return countMutatorCalls;
   }

   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   public int getTotalOperations() {
      int opCount = totalOperationsCount;
      assert isValidBookshelfKeeper();
      return opCount;
   }

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {
      int numBooks = thisSortedBookshelf.size();
      assert isValidBookshelfKeeper();
      return numBooks;
   }

   /**
    * Returns string representation of this BookshelfKeeper. Returns a String containing height
    * of all books present in the bookshelf in the order they are on the bookshelf, followed
    * by the number of bookshelf mutator calls made to perform the last pick or put operation,
    * followed by the total number of such calls made since we created this BookshelfKeeper.
    *
    * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
    *
    */
   public String toString() {
      String str = thisSortedBookshelf.toString() + " " + lastPickOrPutCount + " " + totalOperationsCount;
      assert isValidBookshelfKeeper();
      return (str);
   }

   /**
    * Returns true iff the BookshelfKeeper data is in a valid state.
    * (See representation invariant comment for details.)
    */
   private boolean isValidBookshelfKeeper() {
      // check if BookshelfKeeper is valid
      if(!thisSortedBookshelf.isSorted() || !(totalOperationsCount >= 0) || !(lastPickOrPutCount >= 0)) {
         return false;
      }
      // passed all test, BookshelfKeeper is Valid
      else { return true; }
   }

   // add any other private methods here
}

