// Name: Joshua Fendi
// USC NetID: 5712969966
// CSCI455 PA2
// Spring 2024

import java.util.ArrayList;

public class BookshelfTester {
    public static void main(String[] args) {

        // EXERCISE 2
        // test default constructor
        Bookshelf shelf1 = new Bookshelf(); // isValidBookshelf

        System.out.println("EMPTY TO STRING: " + shelf1.toString());
        // test 4 more public Bookshelf methods
        // addFront
        shelf1.addFront(5);
        System.out.println("add 5 to front of shelf1: " + shelf1.toString());
        assert(shelf1.toString().equals("[5]"));

        // addLast
        shelf1.addLast(2);
        System.out.println("add 2 to last of shelf1: " + shelf1.toString());
        assert(shelf1.toString().equals("[5, 2]"));

        // removeFront
        System.out.println("front height removed: " + shelf1.removeFront());
        System.out.println("remove front: " + shelf1.toString());
        assert(shelf1.toString().equals("[2]"));

        // removeLast
        System.out.println("back height removed: " + shelf1.removeLast());
        System.out.println("remove last: " + shelf1.toString());
        assert(shelf1.toString().equals("[]"));





        // test constructor with parameters
        ArrayList<Integer> books = new ArrayList<Integer>();
        books.add(1);
        books.add(9);
        books.add(20);

        Bookshelf shelf2 = new Bookshelf(books);





        // test toString method
        System.out.println("original shelf2: " + shelf2.toString());
        assert(shelf2.toString().equals("[1, 9, 20]"));


        // EXERCISE 3
        // test 4 more public Bookshelf methods
        // addFront
        shelf2.addFront(5);
        System.out.println("add 5 to front of shelf2: " + shelf2.toString());
        assert(shelf2.toString().equals("[5, 1, 9, 20]"));

        // addLast
        shelf2.addLast(2);
        System.out.println("add 2 to last of shelf2: " + shelf2.toString());
        assert(shelf2.toString().equals("[5, 1, 9, 20, 2]"));

        // removeFront
        System.out.println("front height removed: " + shelf2.removeFront());
        System.out.println("remove front: " + shelf2.toString());
        assert(shelf2.toString().equals("[1, 9, 20, 2]"));

        // removeLast
        System.out.println("back height removed: " + shelf2.removeLast());
        System.out.println("remove last: " + shelf2.toString());
        assert(shelf2.toString().equals("[1, 9, 20]"));

        // EXERCISE 4
        // test remaining 3 public methods
        // getHeight
        assert(shelf2.getHeight(0) == 1);
        System.out.println("First index of shelf2 " + shelf2.getHeight(0));

        assert(shelf2.getHeight(1) == 9);
        assert(shelf2.getHeight(2) == 20);

        // size
        assert(shelf2.size() == 3);

        // isSorted
        assert(shelf2.isSorted()); // shelf2 is sorted
    }
}