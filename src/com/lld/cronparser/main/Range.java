package com.lld.cronparser.main;

public class Range {
    /**
     * The smallest minimum value.
     */
    private final int minSmallest;
    /**
     * The largest minimum value.
     */
    private final int minLargest;
    /**
     * The smallest maximum value.
     */
    private final int maxSmallest;
    /**
     * The largest maximum value.
     */
    private final int maxLargest;

    public Range(int min, int max) {
        this.minSmallest = min;
        this.minLargest = min;
        this.maxSmallest = max;
        this.maxLargest = max;
    }

    public Range(int min, int maxSmallest, int maxLargest) {
        this.minSmallest = min;
        this.minLargest = min;
        this.maxSmallest = maxSmallest;
        this.maxLargest = maxLargest;
    }

    public int getMinSmallest() {
        return minSmallest;
    }

    public int getMinLargest() {
        return minLargest;
    }

    public int getMaxSmallest() {
        return maxSmallest;
    }

    public int getMaxLargest() {
        return maxLargest;
    }

    @Override
    public String toString() {
        return "Range{" +
                "minSmallest=" + minSmallest +
                ", minLargest=" + minLargest +
                ", maxSmallest=" + maxSmallest +
                ", maxLargest=" + maxLargest +
                '}';
    }
}
