public class Vector implements Algebraic {
    private float[] vec;

    public Vector(float[] vec) {
        this.vec = vec;
    }

    public Vector negate() {
        float[] newVecArr = new float[this.vec.length];
        for (int i = 0; i < this.vec.length; i++) {
            newVecArr[i] = this.vec[i] * -1;
        }
        return new Vector(newVecArr);
    }

    public Vector add(Algebraic other) {
        if (other instanceof Vector && this.vec.length == ((Vector) other).getVec().length) {
            float[] newVecArr = new float[this.vec.length];

            for (int i = 0; i < this.vec.length; i++) {
                newVecArr[i] = this.vec[i] + ((Vector) other).getVec()[i];
            }
            return new Vector(newVecArr);
        }
        else {
            return null;
        }
    }

    public Vector subtract(Algebraic other) {
        if (other instanceof Vector && this.vec.length == ((Vector) other).getVec().length) {
            float[] newVecArr = new float[this.vec.length];

            for (int i = 0; i < this.vec.length; i++) {
                newVecArr[i] = this.vec[i] - ((Vector) other).getVec()[i];
            }
            return new Vector(newVecArr);
        }
        else {
            return null;
        }
    }

    public Vector multiply (Algebraic other) {
        if (other instanceof Vector && this.vec.length == ((Vector) other).getVec().length) {
            float result = 0;
            for (int i = 0; i < this.vec.length; i++) {
                result += this.vec[i] * ((Vector) other).getVec()[i];
            }
            float[] newVecArr = new float[1];
            newVecArr[0] = result;
            return new Vector(newVecArr);
        } 
        else {
            return null;
        }
    }

    public Vector crossproduct(Algebraic other) {
        if (other instanceof Vector && this.vec.length == ((Vector) other).getVec().length) {
            float[] newVecArr = new float[this.vec.length];
            newVecArr[0] = this.vec[1] * ((Vector) other).getVec()[2] - this.vec[2] * ((Vector) other).getVec()[1];
            newVecArr[1] = this.vec[2] * ((Vector) other).getVec()[0] - this.vec[0] * ((Vector) other).getVec()[2];
            newVecArr[2] = this.vec[0] * ((Vector) other).getVec()[1] - this.vec[1] * ((Vector) other).getVec()[0];
            return new Vector(newVecArr);
        } 
        else {
            return null;
        }
    }

    public float[] getVec() {
        return this.vec;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Vector && this.vec.length == ((Vector) other).getVec().length) {
            for (int i = 0; i < this.vec.length; i++) {
                if (Math.abs(this.vec[i] - ((Vector) other).getVec()[i]) < Math.pow(10, -6)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public String toString() {
        String output = "";

        for (int i = 0; i < this.vec.length; i++) {
            output += "|" + String.format("%.2f", this.vec[i]) + "| \n";
        }

        return output;
    }
}