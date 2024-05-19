import java.util.ArrayList;

// Name: Joshua Fendi
// USC NetID: 5712969966
// CSCI455 PA2
// Spring 2024
public class TestAssert {
    public static void main(String[] args) {

        ArrayList<Integer> books = new ArrayList<Integer>();
        books.add(1);
        books.add(2);
        books.add(3);
        Bookshelf shelf = new Bookshelf(books); // satisfies precondition of being in ascending order
        shelf.addFront(0);

        ArrayList<Integer> books2 = new ArrayList<Integer>();
        books2.add(3);
        books2.add(2);
        books2.add(1);
        Bookshelf shelf2 = new Bookshelf(books2); // DOES NOT satisfies precondition of being in ascending order
    }
}
