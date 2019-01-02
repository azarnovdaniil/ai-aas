package ru.daniilazarnov.common.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.math3.complex.Complex;
import ru.daniilazarnov.common.serialization.json.deserializers.AppraisalDeserializer;
import ru.daniilazarnov.common.serialization.json.serializers.AppraisalSerializer;

import java.util.Objects;

@JsonSerialize(using = AppraisalSerializer.class)
@JsonDeserialize(using = AppraisalDeserializer.class)
public class Appraisal {

    private static final Appraisal ZERO = new Appraisal(Complex.valueOf(0.0, 0.0));

    private final Complex value;

    private Appraisal(Complex value) {
        this.value = value;
    }

    public static Appraisal valueOf(Complex value) {
        return new Appraisal(value);
    }

    public static Appraisal valueOf(double valence, double dominance) {
        return new Appraisal(Complex.valueOf(valence, dominance));
    }

    public static Appraisal zero() {
        return ZERO;
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

        return Objects.equals(value, appraisal.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

}
