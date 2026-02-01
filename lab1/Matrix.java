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
                int common = this.mat[0].length;
                float[][] newMatArr = new float[rows][cols];
                for (int i = 0; i < rows; i++) {
                    for (int k = 0; k < cols; k++) {
                        float result = 0;
                        for (int j = 0; j < common; j++) {
                            result += this.mat[i][j] * object.getMat()[j][k];
                        }
                        newMatArr[i][k] = result;
                    }
                }
                return new Matrix(newMatArr);
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

            smallArr1[0][0] = this.mat[1][1];
            smallArr1[0][1] = this.mat[1][2];
            smallArr1[1][0] = this.mat[2][1];
            smallArr1[1][1] = this.mat[2][2];
            result += this.mat[0][0] * determinantOfTwo(new Matrix(smallArr1)).getVec()[0];

            smallArr2[0][0] = this.mat[1][0];
            smallArr2[0][1] = this.mat[1][2];
            smallArr2[1][0] = this.mat[2][0];
            smallArr2[1][1] = this.mat[2][2];
            result -= this.mat[0][1] * determinantOfTwo(new Matrix(smallArr2)).getVec()[0];

            smallArr3[0][0] = this.mat[1][0];
            smallArr3[0][1] = this.mat[1][1];
            smallArr3[1][0] = this.mat[2][0];
            smallArr3[1][1] = this.mat[2][1];
            result += this.mat[0][2] * determinantOfTwo(new Matrix(smallArr3)).getVec()[0];

            float[] newVecArr = new float[1];
            newVecArr[0] = result;

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
}
