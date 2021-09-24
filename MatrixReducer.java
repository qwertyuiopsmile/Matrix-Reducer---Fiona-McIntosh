import java.util.*;

public class MatrixReducer {
    public static void main(String args[]) {

        // take input to see dimsensions of the AUGMENTED array
        Scanner sc = new Scanner(System.in);
        int rows;
        int columns;
        System.out.println("How many rows in your AUGMENTED matrix?");
        rows = sc.nextInt();
        System.out.println("How many columns in your AUGMENTED matrix?");
        columns = sc.nextInt();

        double[][] matrix = new double[rows][columns];

        // take input to fill in the AUGMENTED ARRAY
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[0].length; y++) {
                System.out.println(
                        "Enter the number in row " + (((x + 10) % 10) + 1) + " and column " + (((y + 10) % 10) + 1));
                double temp = sc.nextDouble();
                matrix[x][y] = temp;
            }

        }

        //initial printing of the matrix to show the user what thye just inputted
        printMatrix(matrix);

        //some variables to use so my functions know how deep into the matrix they are
        int counter = 1;
        int round = 0;


        // this WORKS! :D
        // it makes it go through to have all leading ones (not rref, but ref)
        int keepGoing = 1;
        while (keepGoing != 0) {
            matrix = makeOnes(matrix, round);
            round++;
            matrix = subtractForDiagnols(matrix, counter);
            counter++;

            int tracker = 0;
            for (int x = 0; x < matrix.length; x++) {
                for (int y = 0; y < x + 1; y++) {
                    tracker += matrix[x][y];
                }
            }
            if (tracker == matrix.length)
                keepGoing = 0;
        }


        //tabs exists to check if the sum of all the numbers (except those in the last column) equals the number of rows to check if we are in rref or not
        int tabs = 0;
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[0].length - 1; y++) {
                tabs += matrix[x][y];
            }
        }


        //as long as we are not in rref, this set of functions will go
        //takes it from eschelon form to RREF!
        int roundNumber = 2;

        while (tabs!=0)
        {

            finishIt(matrix, roundNumber);
            roundNumber++;
            tabs = 0;
            for (int x = 0; x < matrix.length; x++) {
                for (int y = 0; y < matrix[0].length - 1; y++) {
                    tabs += matrix[x][y];
                }
            }
        }

    }






//-----------------------------------------------------------------------END OF MAIN FUNCTION--------------------------------------------------------------------
//BELOW ARE THE FUNCTIONS USED


    // function to print the matrix
    public static void printMatrix(double[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println();
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(Math.round(arr[i][j] * 1000.0) / 1000.0 + "   ");
            }
        }

        System.out.println();
        System.out.println();
    }




    // make first values in each row equal to 1
    public static double[][] makeOnes(double[][] matrix, int round) {
        for (int i = round; i < matrix.length; i++) {
            double temp = matrix[i][round];

            if (temp != 0) {
                for (int j = round; j < matrix[round].length; j++) {
                    matrix[i][j] = (matrix[i][j] / temp);
                }
            } else {
                Integer notAZero = null;
                double valueAtNotAZero = -1000;
                for (int j = round; j < matrix[round].length; j++) {
                    if (matrix[i][j] != 0) {
                        notAZero = j;
                        valueAtNotAZero = matrix[i][j];
                        break;
                    }

                }
                if (notAZero != null) {
                    for (int j = notAZero; j < matrix[round].length; j++) {
                        matrix[i][j] = (matrix[i][j] / valueAtNotAZero);
                    }
                }
            }
        }

        printMatrix(matrix);
        return matrix;
    }




    // subtracts rows from each other to try to get the diagnol line of ones for
    // RREF form
    public static double[][] subtractForDiagnols(double[][] matrix, int counter) {
        if (matrix[counter - 1][counter - 1] == 0)
        {    
        //this deals with leading zeros by just adding rows together so they are no longer zeros
        //clever, huh? :P
            matrix = addRowBValuestoRowA(matrix, counter, counter - 1, 2);
        }
        for (int x = counter; x < matrix.length; x++) {
            for (int y = 0; y < matrix[counter].length; y++) {
                matrix[x][y] = (matrix[x][y] - matrix[counter - 1][y]);
            }
        }

        printMatrix(matrix);
        return matrix;
    }




    //switches two rows given the matrix and the numbers of the two rows that need to be switched
    public static double[][] switchRows(double[][] matrix, int rowA, int rowB) {
        double[] holder = new double[matrix[rowA].length];
        for (int x = 0; x < matrix[rowA].length; x++) {
            holder[x] = matrix[rowA][x];
        }
        for (int x = 0; x < matrix[rowA].length; x++) {
            matrix[rowA][x] = matrix[rowB][x];
        }
        for (int x = 0; x < matrix[rowB].length; x++) {
            matrix[rowB][x] = holder[x];
        }

        printMatrix(matrix);
        System.out.println("printed matrix after switching rows " + rowA + " and " + rowB);
        return matrix;
    }





    //starting from bottom row to second to top, multiples and subtracts for the above rows to get all zeros (except where we have the leading 1's)
    public static double[][] finishIt(double[][] matrix, int roundNumber)
    {
        int countHold = 1;
            for (int x=matrix.length-roundNumber; x>=0; x--)
        {
            double temp = matrix[x][matrix[0].length-roundNumber];
            addRowBValuestoRowA(matrix, x, x+countHold, temp);
            countHold++;
            printMatrix(matrix);
        }
        return matrix;
    }


    
    //function to CHANGE ROW A by adding row B times 'multiplierToB' to it
    public static double[][] addRowBValuestoRowA(double[][] matrix, int RowA, int RowB, double multiplierToB)
    {
        for (int x=0; x<matrix[0].length; x++)
        {
            matrix[RowA][x] += (-1)*multiplierToB*matrix[RowB][x];
        }

        return matrix;
    }

}

//I finished :)