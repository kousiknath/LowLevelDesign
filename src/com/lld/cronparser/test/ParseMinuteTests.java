package com.lld.cronparser.test;

import com.lld.cronparser.main.CronField;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParseMinuteTests {

    @Test
    public void OneValue() {
        CronField minute = CronField.parseMinute("1");
        assertEquals(minute.getItems(), List.of(1));
    }

    @Test
    public void Comma() {
        CronField minute = CronField.parseMinute("1,5,6");
        assertEquals(minute.getItems(), List.of(1,5,6));
    }

    @Test
    public void OneValueSlashOne() {
        CronField minute = CronField.parseMinute("1/1");
        assertEquals(minute.getItems(), List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
                18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42,
                43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59));
    }

    @Test
    public void Star() {
        CronField minute = CronField.parseMinute("*");
        assertEquals(minute.getItems(), List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39,
                40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59));
    }

    @Test
    public void Hyphen() {
        CronField minute = CronField.parseMinute("1-10");
        assertEquals(minute.getItems(), List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

    @Test
    public void StarSlash() {
        CronField minute = CronField.parseMinute("*/15");
        assertEquals(minute.getItems(), List.of(0, 15, 30, 45));
    }

    @Test
    public void OneValueSlash() {
        CronField minute = CronField.parseMinute("0/15");
        assertEquals(minute.getItems(), List.of(0, 15, 30, 45));
    }

    @Test
    public void OneValueSlash2() {
        CronField minute = CronField.parseMinute("1/15");
        assertEquals(minute.getItems(), List.of(1, 16, 31, 46));
    }

    @Test
    public void HyphenSlash() {
        CronField minute = CronField.parseMinute("1-48/15");
        assertEquals(minute.getItems(), List.of(1, 16, 31, 46));
    }

    @Test
    public void CommaSlash() {
        CronField minute = CronField.parseMinute("2,4,6/6");
        assertEquals(minute.getItems(), List.of(2, 4, 6, 12, 18, 24, 30, 36, 42, 48, 54));
    }

    // INVALID PARSING.
    @Test
    public void testParseSlashWithSpace() {
        assertThrows(IllegalArgumentException.class, () -> CronField.parseMinute("/15"));
    }

    @Test
    public void testParseSlashWithInvalidInterval() {
        // Step is greater than the range.
        assertThrows(IllegalArgumentException.class, () -> CronField.parseMinute("1/65"));
    }

    @Test
    public void testParseSlashWithInvalidStartTime() {
        assertThrows(IllegalArgumentException.class, () -> CronField.parseMinute("-1/5"));
    }

    @Test
    public void testParseSlashWithMultipleHyphen() {
        assertThrows(IllegalArgumentException.class, () -> CronField.parseMinute("1-2-3/5"));
    }

    @Test
    public void testParseSlashInvalidIntervalPattern() {
        assertThrows(IllegalArgumentException.class, () -> CronField.parseMinute("1/5-6"));
    }

    @Test
    public void testParseSlashInvalidIntervalChar() {
        assertThrows(IllegalArgumentException.class, () -> CronField.parseMinute("1/a"));
    }

    @Test
    public void testParseHyphenOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> CronField.parseMinute("1-200"));
    }

    @Test
    public void testParseCommaOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> CronField.parseMinute("1,200"));
    }

    @Test
    public void testParseCommaMissingChar() {
        assertThrows(IllegalArgumentException.class, () -> CronField.parseMinute("1,,200"));
    }

    @Test
    public void testParseCommaNotInteger() {
        assertThrows(IllegalArgumentException.class, () -> CronField.parseMinute("a,b"));
    }

    @Test
    public void testParseHyphenChar() {
        assertThrows(IllegalArgumentException.class, () -> CronField.parseMinute("a-b"));
    }

    @Test
    public void testParseHyphenSpecialChar() {
        assertThrows(IllegalArgumentException.class, () -> CronField.parseMinute("*-b"));
    }

    @Test
    public void testParseHyphenExtra() {
        assertThrows(IllegalArgumentException.class, () -> CronField.parseMinute("1--2"));
    }

}
