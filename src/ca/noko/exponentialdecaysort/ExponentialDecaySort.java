package ca.noko.exponentialdecaysort; /**
 * Created by Kevin Chan on 7/30/2014
 *
 * Purpose: Sorting algorithm based on probabilistic exponential decay. This is a bogo-sort
 * style shuffling algorithm that boosts the random shuffle with an exponential decay.
 */
import java.util.ArrayList;

public class ExponentialDecaySort {

    public static void main(String[] args)
    {
        System.out.println("A test of for loop properties");
        int maxValue = 10;
        for(int i = 0; i < maxValue; i++)
        {
            if ((i==4))
            {
                i++;
                maxValue--;
            }
            System.out.println(i);
        }
        maxValue = 10;
        for(int i = 0; i < maxValue; i++)
        {
            System.out.println(i);
        }
    }

    public void exponentialDecaySort(ArrayList<Integer> inputArray)
    {
        /**
         * Bogo-sort like loop which shuffles with an exponential decay
         * and checks to see if the input array is sorted.
         */

        while(inputNotSorted(inputArray))
        {
            exponentialDecayShuffle(inputArray);
        }

    }

    private boolean inputNotSorted(ArrayList<Integer> inputArray)
    {
        /**
         * Check to see if the inputArray is sorted.
         */
        for(int i = 1; i < inputArray.size(); i++)
        {
            if(inputArray.get(i) < inputArray.get(i-1))
            {
                return true;
            }
        }
        return false;
    }

    private boolean hasDecayed(int time, int width)
    {
        /**
         * Using the time and width, we calculate where we are on an exponential decay,
         * and take 1-f(time, width) as a probability to decay.
         */
        double decayParameter = 0.0;
        // width > 0 values are time/width, <= 0 values are ln(3.0+width)*time
        // We don't want ln(e+width) since for a value of width = 0, we'd get the same number
        // as for width = 1.
        if(width <= 0)
        {
            decayParameter = Math.log(3.0 + width)*time;
        }
        else
        {
            decayParameter = 1.0*time/width;
        }
        double decayProbability = 1.0 - Math.exp(-decayParameter);

        return (Math.random() <= decayProbability);
    }

    private void exponentialDecayShuffle(ArrayList<Integer> inputArray)
    {
        /**
         * The lower the number, the sooner it should decay. We remove from, and append to,
         * the inputArray in the order of decays.
         */
        int decaysRemaining = inputArray.size();
        int time = 0;

        while(decaysRemaining > 0)
        {
            time++;

            for (int idecay = 0; idecay < decaysRemaining; idecay++)
            {
                int currentValue = inputArray.get(idecay);
                if(hasDecayed(time, currentValue))
                {
                    inputArray.remove(idecay);
                    inputArray.add(currentValue);

                    idecay--;
                    decaysRemaining--;
                }
            }
        }
    }

    private void exponentialDecayShuffle(ArrayList<Integer> inputArray, int endpoint)
    {
        /**
         * The lower the number, the sooner it should decay. We remove from, and append to,
         * the inputArray in the order of decays.
         */

        for(int idecay = 0; idecay < endpoint; idecay++)
        {
            boolean hasDecayed = false;
            // Exponential decay using the variable in the input array as a "half-life".
            if(hasDecayed)
            {
                exponentialDecayShuffle(inputArray, endpoint - 1);
                break;
            }
        }
    }
}