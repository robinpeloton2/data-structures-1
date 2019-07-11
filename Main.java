import java.util.*;
import java.util.Arrays;
import java.util.stream.*;

class Main {


    static int[] printCity(int mat[][], final int cityCount)
    {
        int[] result = new int[cityCount];
        for(int i = 0; i < cityCount; i++) {
            mat[i][i] = Integer.MAX_VALUE;
            result[i] = Arrays.stream(mat[i]).min().getAsInt();
        }
        return result;
    }

    private static void testRuntime(int n) {
        Random r = new Random();
        int reps = 100; //Initialize number of repetitions to 100

        while(n <= 10000){
            System.out.println("-------------------------------");
            System.out.println("Current Matrix Size: " + n);
            System.out.println("-------------------------------");
            int[][] numbers = new int[n][n];

            for(int j = 0; j < n; j++) {
                for(int i = 0; i < n; i++) {
                    if(i == j) numbers[i][j] = 0;
                    else numbers[i][j] = Math.abs(r.nextInt());
                }
            }

            // Record mean and std deviation of performing an operation
            // reps times
            double sum = 0;
            double sumSquared = 0;

            for (int i = 0; i < reps; i++) {
                long t1 = System.nanoTime(); //Initialize start time

                printCity(numbers, n);

                //Get runtime in nanoseconds
                long t2 = System.nanoTime() - t1;

                // Convert to milliseconds to make the result more readable
                sum += (double) t2 / 1000000.0;
                sumSquared += (t2 / 1000000.0) * (t2 / 1000000.0);
            }
            //Calculate the mean time taken for each rep
            double mean = sum / reps;

            //Calculate the variance to see the range of the set from its mean
            double variance = sumSquared / reps - (mean * mean);

            //Calculate standard deviation to see how the mean runtime
            //can variate
            double stdDev = Math.sqrt(variance);

            // Print results to console
            System.out.println("Mean: " + mean);
            System.out.println("Variance: " + variance);
            System.out.println("Standard Deviation: " + stdDev);
            System.out.println();

            n+= 1000; //increase size of the matrix

//            if (n < 1000) {
//                n += 100;
//            }
//            else {
//                n += 1000;
//            }
        }
        System.out.println("Exit");
    }

    // Driver program to test above
    public static void main(String args[])
    {
        int mat[][] = { { 0, 58, 184, 271, 378, 379 },
                        { 58, 0, 167, 199, 351, 382 },
                        { 184, 167, 0, 43, 374, 370 },
                        { 271, 199, 43, 0, 394, 390 },
                        { 378, 351, 374, 394, 0, 47 },
                        { 379, 382, 370, 390, 47, 0 },
                };
        //Size of Matrix
        int n = mat.length;

//        printCity(mat, n);

        testRuntime(1000);

    }
}

