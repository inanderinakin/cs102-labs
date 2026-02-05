import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Algebraic alg = inputAlgebraic(true);
        
        boolean isWorking = true;
        boolean isModified = false;
        while (isWorking) {
            if (alg instanceof Vector) {
                Scanner scan = new Scanner(System.in);
                Vector inputVector = (Vector) alg;
                int cols = inputVector.getCols();
                System.out.println("Select an operation: ");
                System.out.println("1: Negate");
                System.out.println("2: Add");
                System.out.println("3: Subtract");
                System.out.println("4: Multiply");
                System.out.println("5: Cross Product");
                System.out.println("6: Compare");
                System.out.println("7: Exit");
                System.out.print("Enter your choice: ");

                if(scan.hasNextInt()){
                    int choice = scan.nextInt();
                    if (choice == 1) {
                        Vector negatedVector = inputVector.negate();
                        for (int i = 0; i < cols; i++) {
                            if (i == Math.floor(cols / 2)) {
                                inputVector.printRow(i, "-");
                                negatedVector.printRow(i, "=");
                            }
                            else {
                                inputVector.printRow(i);
                                negatedVector.printRow(i);
                            }
                            System.out.print("\n");
                        }

                        inputVector.negateAll();
                    }

                    else if (choice == 2) {
                        Algebraic newAlg = inputAlgebraic(false);
                        if (newAlg instanceof Vector) {
                            Vector newVector = (Vector) newAlg;
                            Vector addedVector = inputVector.add(newAlg);

                            for (int i = 0; i < cols; i++) {
                                if (i == Math.floor(cols / 2)) {
                                    inputVector.printRow(i);
                                    newVector.printRow(i, "+");
                                    addedVector.printRow(i, "=");
                                }
                                else {
                                    inputVector.printRow(i);
                                    newVector.printRow(i);
                                    addedVector.printRow(i);
                                }
                                System.out.print("\n");
                            }
                            inputVector.addAll(newVector);
                        }
                        else {
                            System.out.println("Invalid operation");
                        }
                    }
                    else if (choice == 3) {
                        Algebraic newAlg = inputAlgebraic(false);
                        if (newAlg instanceof Vector) {
                            Vector newVector = (Vector) newAlg;
                            Vector subtractedVector = inputVector.subtract(newAlg);

                            for (int i = 0; i < cols; i++) {
                                if (i == Math.floor(cols / 2)) {
                                    inputVector.printRow(i);
                                    newVector.printRow(i, "+");
                                    subtractedVector.printRow(i, "=");
                                }
                                else {
                                    inputVector.printRow(i);
                                    newVector.printRow(i);
                                    subtractedVector.printRow(i);
                                }
                                System.out.print("\n");
                            }
                            inputVector.subtractAll(newVector);
                        }
                    }
                    else if (choice == 4) {
                        Algebraic newAlg = inputAlgebraic(false);
                        if (newAlg instanceof Vector) {
                            Vector newVector = (Vector) newAlg;
                            Vector multipliedVector = inputVector.multiply(newAlg);

                            for (int i = 0; i < cols; i++) {
                                if (i == Math.floor(cols / 2)) {
                                    inputVector.printRow(i);
                                    newVector.printRow(i, "+");
                                    multipliedVector.printRow(i, "=");
                                }
                                else {
                                    inputVector.printRow(i);
                                    newVector.printRow(i);
                                    multipliedVector.printRow(i);
                                }
                                System.out.print("\n");
                            }
                            inputVector.multiplyAll(newVector);
                        }
                    }

                    else if (choice == 5) {
                        Algebraic newAlg = inputAlgebraic(false);
                        if (newAlg instanceof Vector) {
                            Vector newVector = (Vector) newAlg;
                            Vector crossedVector = inputVector.crossproduct(newAlg);

                            for (int i = 0; i < cols; i++) {
                                if (i == Math.floor(cols / 2)) {
                                    inputVector.printRow(i);
                                    newVector.printRow(i, "+");
                                    crossedVector.printRow(i, "=");
                                }
                                else {
                                    inputVector.printRow(i);
                                    newVector.printRow(i);
                                    crossedVector.printRow(i);
                                }
                                System.out.print("\n");
                            }
                            inputVector.crossAll(newVector);
                        }
                    }
                    else if (choice == 6) {

                    }
                    else if (choice == 7) {
                        System.out.println("Exiting...");
                        isWorking = false;
                    }
                }
            }
            else if (alg instanceof Matrix) {
                Scanner scan = new Scanner(System.in);
                Matrix inputMatrix = (Matrix) alg;
                int cols = inputMatrix.getCols();
                System.out.println("Select an operation: ");
                System.out.println("1: Negate");
                System.out.println("2: Add");
                System.out.println("3: Subtract");
                System.out.println("4: Multiply");
                System.out.println("5: Cross Product");
                System.out.println("6: Compare");
                System.out.println("7: Exit");
                System.out.print("Enter your choice: ");

                if(scan.hasNextInt()){
                    int choice = scan.nextInt();
                    if (choice == 1) {
                        Matrix negatedMatrix = inputMatrix.negate();
                        for (int i = 0; i < cols; i++) {
                            if (i == Math.floor(cols / 2)) {
                                inputMatrix.printRow(i, "-");
                                negatedMatrix.printRow(i, "=");
                            }
                            else {
                                inputMatrix.printRow(i);
                                negatedMatrix.printRow(i);
                            }
                            System.out.print("\n");
                        }

                        inputMatrix.negateAll();
                    }

                    // else if (choice == 2) {
                    //     Algebraic newAlg = inputAlgebraic(false);
                    //     if (newAlg instanceof Vector) {
                    //         Vector newVector = (Vector) newAlg;
                    //         Vector addedVector = inputVector.add(newAlg);

                    //         for (int i = 0; i < cols; i++) {
                    //             if (i == Math.floor(cols / 2)) {
                    //                 inputVector.printRow(i);
                    //                 newVector.printRow(i, "+");
                    //                 addedVector.printRow(i, "=");
                    //             }
                    //             else {
                    //                 inputVector.printRow(i);
                    //                 newVector.printRow(i);
                    //                 addedVector.printRow(i);
                    //             }
                    //             System.out.print("\n");
                    //         }
                    //         inputVector.addAll(newVector);
                    //     }
                    //     else {
                    //         System.out.println("Invalid operation");
                    //     }
                    // }
                    // else if (choice == 3) {
                    //     Algebraic newAlg = inputAlgebraic(false);
                    //     if (newAlg instanceof Vector) {
                    //         Vector newVector = (Vector) newAlg;
                    //         Vector subtractedVector = inputVector.subtract(newAlg);

                    //         for (int i = 0; i < cols; i++) {
                    //             if (i == Math.floor(cols / 2)) {
                    //                 inputVector.printRow(i);
                    //                 newVector.printRow(i, "+");
                    //                 subtractedVector.printRow(i, "=");
                    //             }
                    //             else {
                    //                 inputVector.printRow(i);
                    //                 newVector.printRow(i);
                    //                 subtractedVector.printRow(i);
                    //             }
                    //             System.out.print("\n");
                    //         }
                    //         inputVector.subtractAll(newVector);
                    //     }
                    // }
                    // else if (choice == 4) {
                    //     Algebraic newAlg = inputAlgebraic(false);
                    //     if (newAlg instanceof Vector) {
                    //         Vector newVector = (Vector) newAlg;
                    //         Vector multipliedVector = inputVector.multiply(newAlg);

                    //         for (int i = 0; i < cols; i++) {
                    //             if (i == Math.floor(cols / 2)) {
                    //                 inputVector.printRow(i);
                    //                 newVector.printRow(i, "+");
                    //                 multipliedVector.printRow(i, "=");
                    //             }
                    //             else {
                    //                 inputVector.printRow(i);
                    //                 newVector.printRow(i);
                    //                 multipliedVector.printRow(i);
                    //             }
                    //             System.out.print("\n");
                    //         }
                    //         inputVector.multiplyAll(newVector);
                    //     }
                    // }

                    // else if (choice == 5) {
                    //     Algebraic newAlg = inputAlgebraic(false);
                    //     if (newAlg instanceof Vector) {
                    //         Vector newVector = (Vector) newAlg;
                    //         Vector crossedVector = inputVector.crossproduct(newAlg);

                    //         for (int i = 0; i < cols; i++) {
                    //             if (i == Math.floor(cols / 2)) {
                    //                 inputVector.printRow(i);
                    //                 newVector.printRow(i, "+");
                    //                 crossedVector.printRow(i, "=");
                    //             }
                    //             else {
                    //                 inputVector.printRow(i);
                    //                 newVector.printRow(i);
                    //                 crossedVector.printRow(i);
                    //             }
                    //             System.out.print("\n");
                    //         }
                    //         inputVector.crossAll(newVector);
                    //     }
                    // }
                    // else if (choice == 6) {

                    // }
                    // else if (choice == 7) {
                    //     System.out.println("Exiting...");
                    //     isWorking = false;
                    }
                }
            }
        }

    public static Algebraic inputAlgebraic(boolean isStart) {
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
            String rawInput = scan.nextLine() + " ";
            
            float[] inputArr = new float[cols];
            for (int i = 0; i < cols; i++) {
                inputArr[i] = Float.parseFloat(rawInput.substring(0, rawInput.indexOf(" ")));
                rawInput = rawInput.substring(rawInput.indexOf(" ") + 1, rawInput.length());
            }

            Vector inputVector = new Vector(inputArr);
            if (isStart) {
                System.out.println(inputVector);
            }
            return inputVector;
        }
        else {
            System.out.println("Enter matrix elements separated by spaces: ");
            float[][] inputArr = new float[rows][cols];
            for (int i = 0; i < rows; i++) {
                String rawInput = scan.nextLine() + " ";
                for (int k = 0; k < cols; k++) {
                    inputArr[i][k] = Float.parseFloat(rawInput.substring(0, rawInput.indexOf(" ")));
                    rawInput = rawInput.substring(rawInput.indexOf(" ") + 1, rawInput.length());
                }
            }
            
            Matrix inputMatrix = new Matrix(inputArr);
            if (isStart) {
                System.out.println(inputMatrix);
            }
            return inputMatrix;
        }
        
    }
}
