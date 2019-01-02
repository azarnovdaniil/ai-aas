package ru.daniilazarnov.bot.core.emotional.functions;

import org.apache.commons.math3.complex.Complex;
import ru.daniilazarnov.common.model.data.Appraisal;
import ru.daniilazarnov.common.util.TriFunction;

import java.util.function.BinaryOperator;

public final class AppraisalFunctions {

    private AppraisalFunctions() {
    }

    private static final double R = 0.01;

    public static final BinaryOperator<Appraisal> targetAppraisalFunc = (action, target) -> {
        Complex result = target.getValue().multiply(1 - R).add(action.getValue().multiply(R));

        return Appraisal.valueOf(result);
    };

    public static final BinaryOperator<Appraisal> actorAppraisalFunc = (action, actor) -> {
        Complex result = actor.getValue().multiply(1 - R).add(action.getValue().conjugate().multiply(R));

        return Appraisal.valueOf(result);
    };

    public static final TriFunction<Appraisal, Appraisal, Appraisal, Double> likelyHoodFunc = (action, actor, target) -> {
        double result = actor.getValue().conjugate().add(target.getValue()).multiply(action.getValue()).getReal();

        return result > 0.0 ? result : 0.0;
    };

}
