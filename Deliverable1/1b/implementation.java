import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class FindLow {   
    

    /**
     * This method first calculates the cube root of all
     * the natural numbers on the input, then it puts all
     * of them in an arraylist, sorts the arraylist,
     * removes non-unique values and picks out the lower
     * median, this lower median is returned.
     * @throws IllegalArgumentException When a number in the
     * sequince doens't have a natural number as it's cube
     * root
     * @throws IllegalArgumentException When input is empty
     * @param stream The input stream with integers
     * disjunct bij spaces
     * @return The lower mean from the cubed roots of the
     * unique input integers
     */
    public static int FindLowMed(InputStream stream){
        Scanner scanner = new Scanner (stream);
        ArrayList<Integer> n = new ArrayList<Integer>();
        int lowerMedian;
        double input;
        int result;

        while (scanner.hasNextDouble()){           
            //only integers matter to us
            input = scanner.nextDouble();
            //round for approaching the root
            //(for example 1000^(1/3) = 9,999999999 -> 10)
            result = (int) Math.round(Math.cbrt(input));

            if(Math.pow(result,3) == input){            
                //ignore for which there doesn't exist an n^3 (n is a natural 
                //number) which is equal to it
                n.add((int) input);
            }                
        }

        if(n.isEmpty()){
            throw new IllegalArgumentException(
                "incorrect input: (usable) input is empty");
        }

        //sort the arraylist
        Collections.sort(n);

        for(int i = 0; i < n.size() - 1; i++){
            //remove all non-unique values
            if(n.get(i) == n.get(i+1)){
                n.remove(i+1);
                i--;
            }
        }

        lowerMedian = n.get((int) (n.size()-1)/2);

        return lowerMedian;
    }
    
    public static void main(String[] args) {
        FindLow test = new FindLow();
        System.out.println(test.FindLowMed(System.in));
    }
}
