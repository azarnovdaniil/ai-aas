package ru.daniilazarnov.calc.botmemory;

import org.apache.commons.math3.complex.Complex;
import ru.daniilazarnov.calc.domain.Appraisal;

import java.util.function.BinaryOperator;

final class AppraisalFunctions {

    private AppraisalFunctions() {
    }

    private static final double R = 0.01;

    static final BinaryOperator<Appraisal> targetAppraisalFunc = (action, target) -> {
        Complex result = target.getValue().multiply(1 - R).add(action.getValue().multiply(R));

        return Appraisal.valueOf(result);
    };

    static final BinaryOperator<Appraisal> actorAppraisalFunc = (action, actor) -> {
        Complex result = actor.getValue().multiply(1 - R).add(action.getValue().conjugate().multiply(R));

        return Appraisal.valueOf(result);
    };

}
