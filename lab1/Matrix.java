public class Matrix implements Algebraic{
    private float[][] mat;
    public Matrix(float[][] mat) {
        this.mat = mat;
    }

    public Matrix negate() {
        float[][] newMatArr = new float[this.mat.length][this.mat[0].length];
        for (int i = 0; i < this.mat.length; i++) {
            for (int k = 0; k < this.mat[i].length; k++) {
                newMatArr[i][k] = this.mat[i][k] * -1;
            }
        }
        return new Matrix(newMatArr);
    }

    public void negateAll() {
        for (int i = 0; i < this.mat.length; i++) {
            for (int k = 0; k < this.mat[i].length; k++) {
                this.mat[i][k] = this.mat[i][k] * -1;
            }
        }
    }

    public void addAll(Algebraic other) {
        if (other instanceof Matrix) {
            float[][] otherMat = ((Matrix) other).getMat();
            if (this.mat.length == otherMat.length && this.mat[0].length == otherMat[0].length) {
                for (int i = 0; i < this.mat.length; i++) {
                    for (int k = 0; k < this.mat[0].length; k++) {
                        this.mat[i][k] = this.mat[i][k] + otherMat[i][k];
                    }
                }
            }
        }
    }

    public void subtractAll(Algebraic other) {
        if (other instanceof Matrix) {
            float[][] otherMat = ((Matrix) other).getMat();
            if (this.mat.length == otherMat.length && this.mat[0].length == otherMat[0].length) {
                for (int i = 0; i < this.mat.length; i++) {
                    for (int k = 0; k < this.mat[0].length; k++) {
                        this.mat[i][k] = this.mat[i][k] - otherMat[i][k];
                    }
                }
            }
        }
    }

    public Matrix add(Algebraic other) {
        if (other instanceof Matrix) {
            float[][] otherMat = ((Matrix) other).getMat();
            if (this.mat.length == otherMat.length && this.mat[0].length == otherMat[0].length) {
                float[][] newMatArr = new float[this.mat.length][this.mat[0].length];
                for (int i = 0; i < this.mat.length; i++) {
                    for (int k = 0; k < this.mat[0].length; k++) {
                        newMatArr[i][k] = this.mat[i][k] + otherMat[i][k];
                    }
                }
                return new Matrix(newMatArr);
            }
        }
        return null;
    }

    public Matrix subtract(Algebraic other) {
        if (other instanceof Matrix) {
            float[][] otherMat = ((Matrix) other).getMat();
            if (this.mat.length == otherMat.length && this.mat[0].length == otherMat[0].length) {
                float[][] newMatArr = new float[this.mat.length][this.mat[0].length];
                for (int i = 0; i < this.mat.length; i++) {
                    for (int k = 0; k < this.mat[0].length; k++) {
                        newMatArr[i][k] = this.mat[i][k] - otherMat[i][k];
                    }
                }
                return new Matrix(newMatArr);
            }
        }
        return null;
    }

    public Algebraic multiply(Algebraic other) {
        if (other instanceof Matrix) {
            Matrix object = (Matrix) other;
            if (this.mat[0].length == object.getMat().length) {
                int rows = this.mat.length;
                int cols = object.getMat()[0].length;
                float[][] newMatrixArr = new float[rows][cols];
                float result = 0;
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        result = 0;
                        for (int n = 0; n < object.getMat().length; n++) {
                            result += this.mat[i][n] * object.getMat()[n][j];
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
            float result = 0;
            float[] newVecArr = new float[object.getVec().length];
            for (int i = 0; i < this.mat.length; i++) {
                for (int k = 0; k < this.mat[0].length; k++) {
                    result += this.mat[i][k] * object.getVec()[k];
                }
                newVecArr[i] = result;
                result = 0;
            }
            return new Vector(newVecArr);
        }
        return null;
    }

    public void multiplyAll(Algebraic other) {
        if (other instanceof Matrix) {
            Matrix object = (Matrix) other;
            if (this.mat[0].length == object.getMat().length) {
                int rows = this.mat.length;
                int cols = object.getMat()[0].length;
                float[][] newMatrixArr = new float[rows][cols];
                float result = 0;
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        result = 0;
                        for (int n = 0; n < object.getMat().length; n++) {
                            result += this.mat[i][n] * object.getMat()[n][j];
                        }
                        newMatrixArr[i][j] = result;
                    }
                }
                this.mat = newMatrixArr;
            }
        }
    }

    public Vector determinant() {
        int rows = this.mat.length;
        int cols = this.mat[0].length;

        if (rows == 2 && cols == 2){
            return determinantOfTwo(this);
        }
        else if (rows == 3 && cols == 3){
            float result = 0;
            float[][] smallArr1 = new float[2][2];
            float[][] smallArr2 = new float[2][2];
            float[][] smallArr3 = new float[2][2];

            for (int i = 0; i < 2; i++) {
                for (int k = 0; k < 2; k++) {
                    smallArr1[i][k] = this.mat[i+1][k+1];
                }
            }
            result += this.mat[0][0] * determinantOfTwo(new Matrix(smallArr1)).getVec()[0];

            for (int i = 0; i < 2; i++) {
                for (int k = 0; k < 2; k++) {
                    if (k == 0) {
                        smallArr2[i][k] = this.mat[i+1][k];
                    } else {
                        smallArr2[i][k] = this.mat[i+1][k+1];
                    }
                }
            }
            result -= this.mat[0][1] * determinantOfTwo(new Matrix(smallArr2)).getVec()[0];

            for (int i = 0; i < 2; i++) {
                for (int k = 0; k < 2; k++) {
                    smallArr3[i][k] = this.mat[i+1][k];
                }
            }
            result += this.mat[0][2] * determinantOfTwo(new Matrix(smallArr3)).getVec()[0];

            float[] newVecArr = new float[1];
            newVecArr[0] = result;
            float[][] newVec2DArr = new float[1][1];
            newVec2DArr[0][0] = result;
            this.mat = newVec2DArr;
            return new Vector(newVecArr);
        }
        else {
            return null;
        }
    }


    public Vector determinantOfTwo(Matrix other) {
        float[][] matArr = other.getMat();
        float[] newVecArr = new float[1];
        newVecArr[0] = matArr[0][0] * matArr[1][1] - matArr[0][1] * matArr[1][0];
        return new Vector(newVecArr); 
    }

    public float[][] getMat() {
        return this.mat;
    }

    @Override
    public String toString() {
        String output = "";

        for (int i = 0; i < this.mat.length; i++) {
            output += "|";
            for (int k = 0; k < this.mat[0].length; k++) {
                if (k > 0) {
                    output += " ";
                }
                output += String.format("%.2f", this.mat[i][k]);
            }
            output += "|\n";
        }

        return output;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Matrix) {
            Matrix object = (Matrix) obj;

            int mainRows = this.mat.length;
            int mainCols = this.mat[0].length;

            int comparedRows = object.getMat().length;
            int comparedCols = object.getMat()[0].length;

            if (mainRows == comparedRows) {
                if (mainCols == comparedCols) {
                    for (int i = 0; i < mainRows; i++) {
                        for (int k = 0; k < mainCols; k++) {
                            if (Math.abs(this.mat[i][k] - object.getMat()[i][k]) > Math.pow(10, -6)) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            }
        } 
        return false;
    }

    public int getCols() {
        return this.mat[0].length;
    }

    public void printRow(int row) {
        String output = "   |";

        for (int k = 0; k < this.mat[row].length; k++) {
            if (k > 0) {
                output += " ";
            }
            output += String.format("%.2f", this.mat[row][k]);
        }
        output += "|";

        System.out.print(output);
    }

    public void printRow(int row, String symbol) {
        String output = " " + symbol + " |";

        for (int k = 0; k < this.mat[row].length; k++) {
            if (k > 0) {
                output += " ";
            }
            output += String.format("%.2f", this.mat[row][k]);
        }
        output += "|";

        System.out.print(output);
    }
}
