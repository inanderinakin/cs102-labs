import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean isVector = false;
        System.out.println("Enter a vector or matrix:");
        System.out.print("Enter number of rows and columns (n x m): ");
        String rowsAndCols = scan.nextLine();

        int rows = Integer.parseInt(rowsAndCols.substring(0, rowsAndCols.indexOf(" ")));
        rowsAndCols = rowsAndCols.substring(rowsAndCols.indexOf(" ") + 1, rowsAndCols.length());
        int cols = Integer.parseInt(rowsAndCols.substring(0, rowsAndCols.length()));

        if (rows == 1) {
           isVector = true;
        }
        else {
            isVector = false;
        }

        if (isVector) {
            System.out.print("Enter vector elements separated by spaces: ");
            String rawInput = scan.nextLine();
            float[] inputArr = new float[cols];
            for (int i = 0; i < cols; i++) {
                inputArr[i] = Float.parseFloat(rawInput.substring(0, rawInput.indexOf(" ") + 1));
                rawInput = rawInput.substring(rawInput.indexOf(" "), rawInput.length());
            }
            Vector inputVector = new Vector(inputArr);
            System.out.println(inputVector);

            System.out.println("Select an operation: ");
            System.out.println("1: Negate");
            System.out.println("2: Add");
            System.out.println("3: Subtract");
            System.out.println("4: Multiply");
            System.out.println("5: Cross Product");
            System.out.println("6: Compare");
            System.out.println("7: Exit");
            System.out.print("Enter your choice: ");
            int choice = scan.nextInt();

            if(scan.hasNextInt()){
                if (choice == 1) {
                    Vector negatedVector = inputVector.negate();
                    System.out.println(inputVector);
                    System.out.println(negatedVector);
                }
            }
        }
    }
}
