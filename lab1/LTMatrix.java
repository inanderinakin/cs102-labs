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
                    for (int k = 0; k <= i; k++) {
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
                    for (int k = 0; k <= i; k++) {
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

    public Algebraic multiply(Algebraic other) {
        float[][] matrixArr = super.getMat();
        if (other instanceof LTMatrix) {
            LTMatrix object = (LTMatrix) other;
            float[][] otherArr = object.getMat();
            if (matrixArr[0].length == otherArr.length) {
                int rows = matrixArr.length;
                int cols = otherArr[0].length;
                float[][] newMatrixArr = new float[rows][cols];
                float result = 0;
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j <= i && j < cols; j++) {
                        result = 0;
                        for (int k = j; k <= i && k < otherArr.length; k++) {
                            result += matrixArr[i][k] * otherArr[k][j];
                        }
                        newMatrixArr[i][j] = result;
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
            float[][] otherArr = object.getMat();
            if (matrixArr[0].length == otherArr.length) {
                int rows = matrixArr.length;
                int cols = otherArr[0].length;
                float[][] newMatrixArr = new float[rows][cols];
                float result = 0;
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        result = 0;
                        for (int k = 0; k < otherArr.length; k++) {
                            result += matrixArr[i][k] * otherArr[k][j];
                        }
                        newMatrixArr[i][j] = result;
                    }
                }
                return new Matrix(newMatrixArr);
            }
            else {
                return null;
            }
        }
        else if (other instanceof Vector) {
            Vector object = (Vector) other;
            float[] vecArr = object.getVec();
            if (matrixArr[0].length == vecArr.length) {
                float[] newVecArr = new float[matrixArr.length];
                float result = 0;
                for (int i = 0; i < matrixArr.length; i++) {
                    result = 0;
                    for (int k = 0; k < matrixArr[0].length; k++) {
                        result += matrixArr[i][k] * vecArr[k];
                    }
                    newVecArr[i] = result;
                }
                return new Vector(newVecArr);
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

    @Override
    public boolean equals(Object other) {
        float[][] matrixArr = super.getMat();
        if (other instanceof LTMatrix) {
            LTMatrix object = (LTMatrix) other;
            float[][] otherArr = object.getMat();
            int rows = matrixArr.length;
            int cols = matrixArr[0].length;
            if (rows == otherArr.length && cols == otherArr[0].length) {
                for (int i = 0; i < rows; i++) {
                    for (int k = 0; k <= i; k++) {
                        if (Math.abs(matrixArr[i][k] - otherArr[i][k]) > Math.pow(10, -6)) {
                            return false;
                        }
                    }
                }
                return true;
            }
            return false;
        }
        else if (other instanceof Matrix) {
            Matrix object = (Matrix) other;
            float[][] otherArr = object.getMat();
            int rows = matrixArr.length;
            int cols = matrixArr[0].length;
            if (rows == otherArr.length && cols == otherArr[0].length) {
                for (int i = 0; i < rows; i++) {
                    for (int k = 0; k < cols; k++) {
                        if (Math.abs(matrixArr[i][k] - otherArr[i][k]) > Math.pow(10, -6)) {
                            return false;
                        }
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }
}
