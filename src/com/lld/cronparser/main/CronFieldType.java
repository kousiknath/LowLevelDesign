package com.lld.cronparser.main;

public enum CronFieldType {

    MINUTE("MINUTE", new Range(0, 59)),
    HOUR("HOUR", new Range(0, 23)),
    DAY_OF_MONTH("DAY OF MONTH", new Range(1, 28, 31)),
    MONTH("MONTH", new Range(1, 12)),
    DAY_OF_WEEK("DAY OF WEEK", new Range(1, 7));

    private final String name;
    private final Range range;

    private CronFieldType(String name, Range range) {
        this.name = name;
        this.range = range;
    }

    @Override
    public String toString() {
        return name;
    }

    public Range getRange() {
        return range;
    }

    public String getName() {
        return name;
    }

    // value the value against the type.
    public boolean validateValue(int value) {
        return value >= this.getRange().getMinSmallest() && value <= this.getRange().getMaxLargest();
    }
}
