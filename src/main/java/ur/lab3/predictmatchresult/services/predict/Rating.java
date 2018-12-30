package ur.lab3.predictmatchresult.services.predict;

import weka.core.stopwords.Null;

public class Rating {

    private Integer actual;
    private Integer min;
    private Integer max;

    public Rating(Integer actual, Integer min, Integer max) {
        if (actual == null || min == null || max == null) throw new NullPointerException();
        if (actual < min || actual > max) throw new IllegalArgumentException("actual < min or actual > max");

        this.actual = actual;
        this.min = min;
        this.max = max;
    }

    public double getNormalizedForm() {
        int rangeFrom = 0;
        int rangeTo = 1;

        return calculateNormalized(actual, min, max, rangeFrom, rangeTo);
    }

    private static double calculateNormalized(double A, double minA, double maxA, double C, double D) {
        return (A - minA) / (maxA - minA) * (D - C) + C;
    }

    public Integer getActual() {
        return actual;
    }

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }
}
