public class Matrix implements Algebraic{
    private float[][] mat;
    public Matrix(float[][] mat) {
        this.mat = mat;
    }

    public Matrix negate() {
        float[][] newMatArr = new float[this.mat.length][];
        for (int i = 0; i < this.mat.length; i++) {
            for (int k = 0; k < this.mat[i].length; i++) {
                newMatArr[i][k] = this.mat[i][k] * -1;
            }
        }
        return new Matrix(newMatArr);
    }

    public Matrix add(Algebraic other) {
        if (other instanceof Matrix && this.mat.length == ((Matrix) other).getMat().length) {
            float[][] newMatArr = new float[this.mat.length][];
            for (int i = 0; i < this.mat.length; i++) {
                for (int k = 0; k < this.mat[i].length; i++) {
                    newMatArr[i][k] = this.mat[i][k] + ((Matrix) other).getMat()[i][k];
                }
            }
            return new Matrix(newMatArr);
        }
        return null;
    }

    public Matrix subtract(Algebraic other) {
        if (other instanceof Matrix && this.mat.length == ((Matrix) other).getMat().length) {
            float[][] newMatArr = new float[this.mat.length][];
            for (int i = 0; i < this.mat.length; i++) {
                for (int k = 0; k < this.mat[i].length; i++) {
                    newMatArr[i][k] = this.mat[i][k] - ((Matrix) other).getMat()[i][k];
                }
            }
            return new Matrix(newMatArr);
        }
        return null;
    }

    public Matrix multiply(Algebraic other) {
        int size = this.mat.length * ((Matrix) other).getMat()[0].length;
        float result = 0;
        if (other instanceof Matrix) {
            float[][] newMatArr = new float[size][];
            if (this.mat[0].length == ((Matrix) other).getMat().length) {
                for (int i = 0; i < newMatArr.length; i++) {
                    for (int k = 0; k < newMatArr[i].length; i++) {
                        result += 
                    }
                }
            }
            else {
                System.out.println("No!");
            }
        }
    }

    public float[][] getMat() {
        return this.mat;
    }
}
