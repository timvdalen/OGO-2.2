import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is the implementation of exercise 1A.
 */
public class Exercise1A
{
    // Representation ----------------------------------------------------------
    /** The input sequence from the inputfile. */
    private ArrayList<Double> input;
    /** The output, as specified in the exercise. */
    private double output;

    // Other variables ---------------------------------------------------------
    /** The inputfile to be read, for testing. */
    File inputFile = new File("FILE.txt");

    // Queries -----------------------------------------------------------------
        // None

    // Methods -----------------------------------------------------------------
    /**
     * A method to illustrate the behaviour of the described algorithm.
     *
     * @pre     true
     * @post    the output, as specified by the algorithm, is written to
     *          System.out
     */
    public void start()
    {
        // Initialization:
        Scanner scanner;
        output = 0; // In case there are no numbers, the sum is 0.
        
        try {
            scanner = new Scanner(inputFile);
            input = new ArrayList<Double>();

            // Collect all numbers from the input, assuming they are seperated
            // by a whitespace.
            while (scanner.hasNext()){
                input.add(scanner.nextDouble());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Exercise1A.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Run the algorithm.
        algorithm1A();

        // Print the output.
        System.out.println("" + output);
    }

    // Private methods ---------------------------------------------------------
    /**
     * A method that is the implementation of the described algorithm.
     *
     * @pre     input variable has been initialized
     * @post    output contains the sum of those input numbers of which the
     *          indices are specified by the Fibonacci sequence
     */
    private void algorithm1A(){
        // Temporary variables for the first two Fibonacci numbers; since we use
        // a help-method to calculate the next Fibonacci number, we do not have
        // to use a recursive method that calculates each Fibonacci number over
        // and over again.
        int fib0 = 0;
        int fib1 = 1;

        for (int j = 0; j < input.size(); j++)
        { // Iterate over all values of the input.
            output += input.get(fib0);
            // The output contains all the numbers, that are defined by the
            // indices of the Fibonacci sequence that belongs to this input.
            calcNextFibon(fib0, fib1); // Calculate the next Fibonacci numbers.
        }

        /*
        In Java, arrays and ArrayLists are indexed from 0, although sequences
        always start at index 1. We decided to keep the 0-index as starting point,
        to keep our code simple.
         */
    }

    /**
     * A helpmethod to calculate the next Fibonacci number, using fibFirst and
     * fibSecond. 
     *
     * @param   fibFirst  the n'th Fibonacci number
     * @param   fibSecond the n+1'th Fibonacci number
     * @pre     fibFirst and fibSecond contain a non-negative number
     * @post    fibFirst contains the n+1'th Fibonacci number and fibSecond
     *          contains the n+2'th Fibonacci number.
     */
    private void calcNextFibon(int fibFirst, int fibSecond){
        int temp = fibFirst; // Temporary save the n-th Fibonacci number.
        fibFirst = fibSecond; // The n'th Fibonacci number becomes the n+1'th.
        fibSecond = temp + fibSecond; // The n+1'th Fibonnaci becomes the
        // n+2'th Fibonnaci number, as defined by the Fibonacci sequence.
    }

    public static void main(String[] args) {
        new Exercise1A().start();
    }

}