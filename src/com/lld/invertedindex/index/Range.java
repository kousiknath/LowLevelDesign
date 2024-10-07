package com.lld.invertedindex.index;

public class Range implements Comparable<Range> {
    private Integer start;
    private Integer end; // Excluding end in the Range => [start, end)
    public Range(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }

    @Override
    public int compareTo(Range o) {
        if (o == null) {
            return 1;
        }

        return Integer.compare(this.start, o.start);
    }
}
