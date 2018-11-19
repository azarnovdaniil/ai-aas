package ru.daniilazarnov.calc.domain;

import org.apache.commons.math3.complex.Complex;

public class Appraisal {

    private final Complex value;

    private Appraisal(Complex value) {
        this.value = value;
    }

    public static Appraisal valueOf(Complex value){
        return new Appraisal(value);
    }

    public static Appraisal valueOf(double valence, double dominance){
        return new Appraisal(Complex.valueOf(valence, dominance));
    }

    public Complex getValue() {
        return value;
    }

    public double getValence() {
        return value.getReal();
    }

    public double getDominance() {
        return value.getImaginary();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Appraisal appraisal = (Appraisal) o;

        return value != null ? value.equals(appraisal.value) : appraisal.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
