package com.lld.cronparser.model;

import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;

public class CronField {

    // Here we will store the occurrences of this cronField.

    List<Integer> items = new ArrayList<>();
    CronFieldType type;
    public CronField(CronFieldType type) {
        items = new ArrayList<>();
        this.type = type;
    }

    @Override
    public String toString() {
        return type + " : " + items;
    }

    /*
        Now we have split the work into specific fields, we can now do something.
        Each field can have [",", "/", "*", "-"]


         */
    private static CronField parseField(String value, CronFieldType type) {
        // VALIDATION
        if (value.isEmpty() || value.isBlank()) throw new IllegalArgumentException("Value should not be empty");
        CronField f = new CronField(type);
        // HANDLE COMMA
        String[] fields = value.split(",");
        // 1-20/5,30-45/5
        for (String field : fields) {
            // HANDLE SLASH WHICH GIVES STEP INFORMATION.
            // slashes can be combined with ranges to specify step values
            int slashPos = field.indexOf('/');
            if (slashPos == -1) {
                // no slash present => no steps, take the full range if present.
                // Only range left. Parse it using the type information and validate the ranges.
                Range r = parseRange(field, type);
                // once you have a valid range, set it properly in the list that you have.
                // TODO: SET THE RANGE HERE.
                setRange(f, r);
            }
            else {
                // there is step.
                String rangeStr = field.substring(0, slashPos);
                String step = field.substring(slashPos + 1);
                Range range = parseRange(rangeStr, type);
                // 1 => It means start from 1 and go till end., it should populate things properly.
                int hyphenPos = rangeStr.indexOf('-');
                // TODO: I don't think this below piece of code is needed.
//                if (hyphenPos == -1) {
//                    // it is just 1 value, along with step, you need to start from 1 and keep going
//                    // until you exhaust the type.
//                    // TODO: I don't know how this will populated in particular cases, will see when we come to it.
//                    range = new Range(range.getMinSmallest(), type.getRange().getMaxLargest());
//                    // setRange(f, range);
//                }
                int delta = Integer.parseInt(step);
                if (delta <= 0) {
                    throw new IllegalArgumentException("Step is less than 0");
                }
                setRange(f, range, delta);
            }
        }
        return f;
    }


    // *
    // a-b
    // a
    private static Range parseRange(String value, CronFieldType type) {
        if (value.equals("*")) {
            return type.getRange();
        } else {
            int hyphenPos = value.indexOf('-');
            if (hyphenPos == -1) {
                // there is just 1 integer we need to parse.
                // If there is a parsing error, we will directly throw from here.
                int res = Integer.parseInt(value);
                // once we have parsed the int, we need to validate it against the type.
                boolean status = type.validateValue(res);
                if (status) {
                    return new Range(res, res);
                } else {
                    throw new IllegalArgumentException("Type validation failed for : " + type.getName());
                }
            } else {
                // we have a-b range that we can handle.
                String leftHalf = value.substring(0, hyphenPos);
                String rightHalf = value.substring(hyphenPos + 1);
                int left = Integer.parseInt(leftHalf);
                int right = Integer.parseInt(rightHalf);
                boolean leftStatus = type.validateValue(left);
                boolean rightStatus = type.validateValue(right);
                if (leftStatus && rightStatus) {
                    // there is some special case for DAY_OF_WEEK. Not sure what that is. They set the
                    // value of 7 to 0
                    return new Range(left, right);

                } else {
                    throw new IllegalArgumentException("Type validation failed for : " + type.getName());
                }
            }
        }
    }

    public static CronField parseMinute(String field) {
        return parseField(field, CronFieldType.MINUTE);
    }

    public static CronField parseHour(String field) {
        return parseField(field, CronFieldType.HOUR);

    }

    public static CronField parseDayOfMonth(String field) {
        return parseField(field, CronFieldType.DAY_OF_MONTH);

    }

    public static CronField parseMonth(String field) {
        return parseField(field, CronFieldType.MONTH);

    }

    public static CronField parseDayOfWeek(String field) {
        return parseField(field, CronFieldType.DAY_OF_WEEK);

    }

    private static void setRange(CronField f, Range r) {
        for (int start = r.getMinSmallest(); start <= r.getMaxLargest(); start++) {
            f.items.add(start);
        }
    }

    private static void setRange(CronField f, Range r, int delta) {
        for (int start = r.getMinSmallest(); start <= r.getMaxLargest(); start = start + delta) {
            f.items.add(start);
        }
    }

}
