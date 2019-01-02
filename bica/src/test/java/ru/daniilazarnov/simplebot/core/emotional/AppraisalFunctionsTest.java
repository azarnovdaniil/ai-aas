package ru.daniilazarnov.simplebot.core.emotional;

import org.junit.jupiter.api.Test;
import ru.daniilazarnov.common.model.data.Appraisal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.daniilazarnov.bot.core.emotional.functions.AppraisalFunctions.*;

class AppraisalFunctionsTest {

    private static final Double PRECISION = 0.01;

    @Test
    void targetAppraisalFunc() {
        Appraisal expected = Appraisal.valueOf(1.0, 1.0);
        Appraisal target = Appraisal.valueOf(1.0, 1.0);
        Appraisal action = Appraisal.valueOf(1.0, 1.0);

        assertEquals(expected, targetAppraisalFunc.apply(action, target));
    }

    @Test
    void actorAppraisalFunc() {
        Appraisal expected = Appraisal.valueOf(1.0, 0.98);
        Appraisal actor = Appraisal.valueOf(1.0, 1.0);
        Appraisal action = Appraisal.valueOf(1.0, 1.0);

        assertEquals(expected, actorAppraisalFunc.apply(action, actor));
    }

    @Test
    void likelyHoodFunc() {
        Appraisal actor = Appraisal.valueOf(1.0, 1.0);
        Appraisal target = Appraisal.valueOf(1.0, 1.0);
        Appraisal action = Appraisal.valueOf(1.0, 1.0);

        assertEquals(2.0, likelyHoodFunc.apply(action, actor, target), PRECISION);
    }

}
