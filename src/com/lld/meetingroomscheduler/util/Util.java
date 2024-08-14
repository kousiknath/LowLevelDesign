package com.lld.meetingroomscheduler.util;

import com.lld.meetingroomscheduler.model.Duration;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Util {
    public static long toEpoch(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static int compareDuration(Duration o1, Duration o2) {
        Long o1StartEpoch = Util.toEpoch(o1.getStart());
        Long o1EndEpoch = Util.toEpoch(o1.getEnd());
        Long o2StartEpoch = Util.toEpoch(o2.getStart());
        Long o2EndEpoch = Util.toEpoch(o2.getEnd());

        if (o1StartEpoch.equals(o2StartEpoch) && o1EndEpoch.equals(o2EndEpoch)) {
            return 0;
        }

        if (o1StartEpoch < o2StartEpoch) {
            return -1;
        } else if (o1StartEpoch > o2StartEpoch) {
            return 1;
        }

        return o1EndEpoch.compareTo(o2EndEpoch);
    }
}
