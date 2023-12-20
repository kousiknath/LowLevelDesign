package com.lld.cronparser.main;

public class CronExpression {
    CronField[] fields;
    String expression;
    String command;

    public CronExpression() {
    }

    private CronExpression(CronField minutes,
                           CronField hours,
                           CronField daysOfMonth,
                           CronField months,
                           CronField daysOfWeek,
                           String command,
                           String expression) {

        this.fields = new CronField[]{minutes, hours, daysOfMonth, months, daysOfWeek};
        this.command = command;
        this.expression = expression;
    }

    // 5 Fields + 1 more field
    public static CronExpression Parse(String Expression) {
        // TODO: Expression should not be empty.
        assert !Expression.isEmpty();

        String[] portions = Expression.split(" ");
        // Ignoring the command portion as of now.
        if (portions.length != 6) {
            throw new IllegalArgumentException("Invalid count of parts");
        }

        try {
            // if you are here, all good.
            CronField minute = CronField.parseMinute(portions[0]);
            CronField hour = CronField.parseHour(portions[1]);
            CronField dayOfMonth = CronField.parseDayOfMonth(portions[2]);
            CronField month = CronField.parseMonth(portions[3]);
            CronField dayOfWeek = CronField.parseDayOfWeek(portions[4]);
            String command = portions[5];

            return new CronExpression(minute, hour, dayOfMonth, month, dayOfWeek, command, Expression);

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
        res.append("COMMAND : " + command);
        return res.toString();
    }

    public CronField[] getFields() {
        return fields;
    }
}
