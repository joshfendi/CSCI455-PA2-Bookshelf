// Name: Joshua Fendi
// USC NetID: 5712969966
// CSCI455 PA2
// Spring 2024

import java.util.ArrayList; // import ArrayList
import java.util.Scanner;  // Import the Scanner class

/**
     This Program provides a user interface to
     1) create a BookShelf of books with positive heights,
     2) pick the position of a book,
     3) or put a book of a desired height,
     all while keeping the books in non-descending order
 */

public class BookshelfKeeperProg {
    public static void main(String[] args) {

        // Prompt user for input
        System.out.println("Please enter initial arrangement of books followed by newline:");

        // Scanner object
        Scanner in = new Scanner(System.in);
        String input = in.nextLine(); // read in next line

        // list of nums from user input
        ArrayList<Integer> numList = new ArrayList<>();
        boolean positiveInput = strToArrList(input,numList); // to convert input ot ArrList, and return a bool

        // Bookshelf
        Bookshelf shelf = new Bookshelf();
        if(positiveInput) { shelf = new Bookshelf(numList); } // update shelf if there is valid input

        if(positiveInput && !haveErrors(shelf)) {
            BookshelfKeeper keeperForShelf = new BookshelfKeeper(shelf); // Bookshelf Keeper for shelf
            System.out.println(keeperForShelf.toString()); // print current bookshelf

            // Prompt user to pick or put
            System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");

            // put or pick from bookshelf until user terminates with "end"
            pickOrPut(in, keeperForShelf, shelf);
        }

        System.out.println("Exiting Program."); // end program
    }


    // HELPER FUNCTIONS

    /**
     * Mutates the ArrayList called numList by adding the integers provided in the String input
     * Checks if inputs are valid (>= 1)
     *
     * Returns true if all inputs are valid (positive integers)
     *
     * PRE:
     *  input contains only white space and (positive or negative numbers)
     *  numList is empty
     */
    private static boolean strToArrList(String input, ArrayList<Integer> numList) {
        input = input.trim(); // remove leading and trailing white space

        String[] numStr = input.split("\\s+"); // extract nums seperated by white space

        // if there is an int
        if(!input.isEmpty()) {
            // check every num in numStr
            for(String num : numStr) {
                if(num.isEmpty()) { break; }
                if(Integer.parseInt(num) < 1) {
                    System.out.println("ERROR: Height of a book must be positive.");
                    return false;
                }

                numList.add(Integer.parseInt(num)); // add nums to numList
            }
        }

        return true; // all positive book heights
    }


    /**
     * Checks if the heights in a bookshelf are sorted in non-decreasing order and if there are all positive
     *
     * Returns true if there is an error
     */
    private static boolean haveErrors(Bookshelf shelf) {
        // Error Checking
        if(!shelf.isSorted()) {
            System.out.println("ERROR: Heights must be specified in non-decreasing order.");
            return true;
        }
        else if(!allPositive(shelf)) {
            System.out.println("ERROR: Height of a book must be positive.");
            return true;
        }
        return false;
    }


    /**
     * Checks if all books in shelf are positive heights
     *
     * Returns true if all books have a positive height
     */
    private static boolean allPositive(Bookshelf shelf) {
        // if there is a negative book height, return false
        for(int i = 0; i < shelf.size(); ++i) {
            if(shelf.getHeight(i) < 0) { return false; }
        }

        // no negative book height
        return true;
    }


    /**
     * Checks command input for either "pick", "put", or "end" commands, and carries either pick or put command
     * on shelf, or terminates the program. Program terminates with an error message if invalid input.
     */
    private static void pickOrPut(Scanner in, BookshelfKeeper keeperForShelf, Bookshelf shelf) {
        while(true) { // terminate when "end" or invalid command is inputted
            String line = in.nextLine();
            String[] str = line.split("\\s+"); // split by white space

            int offset = 0;
            if(str[0].isEmpty()) { offset = 1; } // takes care of initial white space
            String pickOrPutOrEnd = str[offset]; // extract from String array

            // invalid command
            if(!(pickOrPutOrEnd.equals("end") || pickOrPutOrEnd.equals("put") || pickOrPutOrEnd.equals("pick"))) {
                System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
                break;
            }

            if(pickOrPutOrEnd.equals("end")) { break; } // terminate the entire program
            int bookHeightOrPos = Integer.parseInt(str[1 + offset]);

            // put
            if(pickOrPutOrEnd.equals("put")) {
                // invalid height
                if(bookHeightOrPos < 0) {
                    System.out.println("ERROR: Height of a book must be positive.");
                    break;
                }
                keeperForShelf.putHeight(bookHeightOrPos);
            }
            // pick
            else {
                // invalid index
                if(bookHeightOrPos < 0 || bookHeightOrPos >= shelf.size()) {
                    System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
                    break;
                }
                keeperForShelf.pickPos(bookHeightOrPos);
            }

            System.out.println(keeperForShelf.toString());
        }
    }
}
