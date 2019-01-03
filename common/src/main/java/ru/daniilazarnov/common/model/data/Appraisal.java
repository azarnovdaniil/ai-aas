package ru.daniilazarnov.common.model.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.math3.complex.Complex;
import ru.daniilazarnov.common.model.serialization.json.deserializers.AppraisalDeserializer;
import ru.daniilazarnov.common.model.serialization.json.serializers.AppraisalSerializer;

import java.util.Objects;

@JsonSerialize(using = AppraisalSerializer.class)
@JsonDeserialize(using = AppraisalDeserializer.class)
public class Appraisal {

    private static final Appraisal ZERO = new Appraisal(0.0, 0.0);

    private final double valence;
    private final double dominance;

    private Appraisal(double valence, double dominance) {
        this.valence = valence;
        this.dominance = dominance;
    }

    public static Appraisal valueOf(Complex value) {
        return new Appraisal(value.getReal(), value.getImaginary());
    }

    public static Appraisal valueOf(double valence, double dominance) {
        return new Appraisal(valence, dominance);
    }

    public static Appraisal zero() {
        return ZERO;
    }

    public Complex getValue() {
        return Complex.valueOf(valence, dominance);
    }

    public double getValence() {
        return valence;
    }

    public double getDominance() {
        return dominance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appraisal appraisal = (Appraisal) o;
        return Double.compare(appraisal.valence, valence) == 0 &&
                Double.compare(appraisal.dominance, dominance) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valence, dominance);
    }
}
