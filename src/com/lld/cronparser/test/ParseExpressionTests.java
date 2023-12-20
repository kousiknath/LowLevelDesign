package com.lld.cronparser.test;

import com.lld.cronparser.main.CronExpression;
import com.lld.cronparser.main.CronField;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParseExpressionTests {

    @Test
    public void Test1() {
        CronExpression exp = CronExpression.Parse("*/15 0 1,15 * 1-5 ls");
        assertEquals(exp.getFields()[0].getItems(), List.of(0, 15, 30, 45));
        assertEquals(exp.getFields()[1].getItems(), List.of(0));
        assertEquals(exp.getFields()[2].getItems(), List.of(1, 15));
        assertEquals(exp.getFields()[3].getItems(), List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        assertEquals(exp.getFields()[4].getItems(), List.of(1, 2, 3, 4, 5));
    }

    @Test
    public void Test2() {
        CronExpression exp = CronExpression.Parse("5,10 0 2 * * ls");
        assertEquals(exp.getFields()[0].getItems(), List.of(5, 10));
        assertEquals(exp.getFields()[1].getItems(), List.of(0));
        assertEquals(exp.getFields()[2].getItems(), List.of(2));
        assertEquals(exp.getFields()[3].getItems(), List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        assertEquals(exp.getFields()[4].getItems(), List.of(1, 2, 3, 4, 5, 6, 7));
    }


    @Test
    public void TestException() {
        assertThrows(IllegalArgumentException.class, () -> CronExpression.Parse("1 0 2 1 15 ls"));
    }
}
