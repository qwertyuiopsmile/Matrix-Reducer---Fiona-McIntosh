import java.util.*;

public class LUDecomposer {

    public static void main(String[] args) {
        System.out.println("This will make an LU decomposition of a squre matrix, if possible.");
        Scanner sc = new Scanner(System.in);
        int dimension;
        System.out.println("What is the dimension of your matrix?");
        dimension = sc.nextInt();

        double[][] matrix = new double[dimension][dimension];
        double[][] shadowMatrix = new double[dimension][dimension];


        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[0].length; y++) {
                System.out.println(
                        "Enter the number in row " + (((x + 10) % 10) + 1) + " and column " + (((y + 10) % 10) + 1));
                double temp = sc.nextDouble();
                matrix[x][y] = temp;
            }

        }


        printMatrix(matrix);
        int round = 0;
        while (round < matrix.length) {

            if (matrix[round][round]==0)
            {
                System.out.println("This matrix cannot undergo LU Decomposition. Sorry!");
                System.exit(0);
            }

            for (int j = 0; j < matrix[0].length; j++) {
                System.out.println("This is matrix L with the next column added into it!");
                for (int x=round; x<matrix.length; x++)
                {
                    shadowMatrix[x][j] = matrix[x][j];
                }
                printMatrix(shadowMatrix);

                for (int i = round + 1; i < matrix.length; i++) {
                    if (matrix[i][j] != 0) {
                        double multiplier = -1 * (matrix[i][j] / matrix[round][round]);
                        matrix = addMultiple(matrix, round, i, multiplier);
                        //shadowMatrix = addMultipleShadow(shadowMatrix, round, i, multiplier);
                    }

                }


                round++;
            }
        }

        shadowMatrix = makeOnes(shadowMatrix, dimension);
        System.out.println("Lastly, we divide the columns such that the leading number is one for the L matrix. We get the following:");
        printMatrix(shadowMatrix);
        System.out.println("Matrix L is equal to the following matrix:");
        printMatrix(shadowMatrix);
        System.out.println("Matrix U is equal to the folloiwng matrix:");
        printMatrix(matrix);




    }

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

    public static double[][] addMultiple(double[][] matrixx, int rowA, int rowB, double multiple) {
        double[][] matrix = matrixx;
        for (int x = 0; x < matrix[rowA].length; x++) {
            matrix[rowB][x] += multiple * matrix[rowA][x];
        }

        System.out.println("In matrix U's progression: Added " + multiple + " times row " + rowA + 1 + " to row " + rowB + 1);

        System.out.println("U =");
        printMatrix(matrix);

        return matrix;

    }

    public static double[][] makeOnes(double[][] matrix, int rows)
    {

        double temp;
for (int x=0; x<rows; x++)
{
    temp = matrix[x][x];
    for (int y=0; y<rows; y++)
    {

        matrix[y][x] = matrix[y][x]/temp;
    }
}


        return matrix;
    }

}