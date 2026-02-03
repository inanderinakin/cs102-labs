public class LTMatrix extends Matrix {

    public LTMatrix(float[][] mat) {
        super(isValidLowerTriangular(mat) ? mat : new float[0][0]);
    }

    private static boolean isValidLowerTriangular(float[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        if (rows != cols) {
            System.out.println("Matrix must be square for a lower triangular matrix.");
            return false;
        }
        
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < cols; k++) {
                if (k > i && mat[i][k] != 0) {
                    System.out.println("Matrix is not a lower triangular matrix");
                    return false;
                }
            }
        }
        
        return true;
    }

    public LTMatrix negate() {
        float[][] matrixArr = super.getMat();
        int rows = matrixArr.length;
        int cols = matrixArr[0].length;
        float[][] newMatrixArr = new float[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < cols; k++) {
                if (k <= i) {
                    newMatrixArr[i][k] = matrixArr[i][k] * -1;
                }
                else {
                    newMatrixArr[i][k] = 0;
                }
            }
        }
        return new LTMatrix(newMatrixArr);
    }

    public Matrix subtract(Algebraic other) {
        float[][] matrixArr = super.getMat();
        int rows = matrixArr.length;
        int cols = matrixArr[0].length;
        float[][] newMatrixArr = new float[rows][cols];

        if (other instanceof LTMatrix) {
            Matrix object = (Matrix) other;
            int otherRows = object.getMat().length;
            int otherCols = object.getMat()[0].length;
            if (rows == otherRows && cols == otherCols) {
                for (int i = 0; i < rows; i++) {
                    for (int k = 0; k < cols; k++) {
                        if (k <= i) {
                            newMatrixArr[i][k] = matrixArr[i][k] - object.getMat()[i][k];
                        }
                        else {
                            newMatrixArr[i][k] = 0;
                        }
                    }
                }
                return new LTMatrix(newMatrixArr);
            }
            else {
                return null;
            }
        }
        else if (other instanceof Matrix) {
            Matrix object = (Matrix) other;
            int otherRows = object.getMat().length;
            int otherCols = object.getMat()[0].length;
            if (rows == otherRows && cols == otherCols) {
                for (int i = 0; i < rows; i++) {
                    for (int k = 0; k < cols; k++) {
                        newMatrixArr[i][k] = matrixArr[i][k] - object.getMat()[i][k];
                    }
                }
                return new Matrix(newMatrixArr);
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    public Matrix add(Algebraic other) {
        float[][] matrixArr = super.getMat();
        int rows = matrixArr.length;
        int cols = matrixArr[0].length;
        float[][] newMatrixArr = new float[rows][cols];

        if (other instanceof LTMatrix) {
            Matrix object = (Matrix) other;
            int otherRows = object.getMat().length;
            int otherCols = object.getMat()[0].length;
            if (rows == otherRows && cols == otherCols) {
                for (int i = 0; i < rows; i++) {
                    for (int k = 0; k < cols; k++) {
                        if (k <= i) {
                            newMatrixArr[i][k] = matrixArr[i][k] + object.getMat()[i][k];
                        }
                        else {
                            newMatrixArr[i][k] = 0;
                        }
                    }
                }
                return new LTMatrix(newMatrixArr);
            }
            else {
                return null;
            }
        }
        else if (other instanceof Matrix) {
            Matrix object = (Matrix) other;
            int otherRows = object.getMat().length;
            int otherCols = object.getMat()[0].length;
            if (rows == otherRows && cols == otherCols) {
                for (int i = 0; i < rows; i++) {
                    for (int k = 0; k < cols; k++) {
                        newMatrixArr[i][k] = matrixArr[i][k] + object.getMat()[i][k];
                    }
                }
                return new Matrix(newMatrixArr);
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    public Vector determinant() {
        float[][] matrixArr = super.getMat();
        float result = 1;
        for (int i = 0; i < matrixArr.length; i++) {
            result *= matrixArr[i][i];
        }
        float[] newVecArr = new float[1];
        newVecArr[0] = result;  
        return new Vector(newVecArr);
    }
}
