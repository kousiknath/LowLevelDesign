package com.lld.cronparser.model;

import java.util.Arrays;

public class CronExpression {
    CronField[] fields;
    String expression;

    public CronExpression() {
    }

    private CronExpression(CronField minutes,
                           CronField hours,
                           CronField daysOfMonth,
                           CronField months,
                           CronField daysOfWeek,
                           String expression) {

        this.fields = new CronField[]{minutes, hours, daysOfMonth, months, daysOfWeek};
        this.expression = expression;
    }

    // 5 Fields + 1 more field
    public static CronExpression Parse(String Expression) {
        // TODO: Expression should not be empty.
        String[] portions = Expression.split(" ");
        // Ignoring the command portion as of now.
        if (portions.length != 5) {
            throw new IllegalArgumentException("Expression has more parts to it.");
        }

        try {
            // if you are here, all good.
            CronField minute = CronField.parseMinute(portions[0]);
            CronField hour = CronField.parseHour(portions[1]);
            CronField dayOfMonth = CronField.parseDayOfMonth(portions[2]);
            CronField month = CronField.parseMonth(portions[3]);
            CronField dayOfWeek = CronField.parseDayOfWeek(portions[4]);

            return new CronExpression(minute, hour, dayOfMonth, month, dayOfWeek, Expression);

        } catch (IllegalArgumentException ex) {
            // figure out a way to properly display messages here.
            throw new IllegalArgumentException(ex);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (var f : fields) {
            res.append(f.toString());
            res.append(System.lineSeparator());
        }
        return res.toString();
    }
}
