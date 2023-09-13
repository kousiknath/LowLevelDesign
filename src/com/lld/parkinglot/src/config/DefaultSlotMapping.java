package com.lld.parkinglot.src.config;

import com.lld.parkinglot.src.constant.SlotType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    This slot mapping is default and global. Ideally, each parking lot can have its own slot mapping since
    slot size may vary across parking lots.

    For simplicity, we are keeping a global mapping.
 */
public class DefaultSlotMapping {
    public static class SlotInfo {
        SlotType mappedSlotType;
        int count;

        public SlotInfo(SlotType slotType, int count) {
            this.mappedSlotType = slotType;
            this.count = count;
        }

        public SlotType getMappedSlotType() {
            return mappedSlotType;
        }

        public int getCount() {
            return count;
        }
    }
    private Map<SlotType, List<SlotInfo>> slotMapping;

    public DefaultSlotMapping() {
        this.slotMapping = new HashMap<>();
        putSlotMapping();
    }

    private void putSlotMapping() {
        SlotInfo XSmall1x = new SlotInfo(SlotType.X_SMALL, 1);
        SlotInfo XSmall2x = new SlotInfo(SlotType.X_SMALL, 2);
        SlotInfo XSmall4x = new SlotInfo(SlotType.X_SMALL, 4);
        SlotInfo XSmall8x = new SlotInfo(SlotType.X_SMALL, 8);
        SlotInfo XSmall16x = new SlotInfo(SlotType.X_SMALL, 16);

        this.slotMapping.put(SlotType.X_SMALL, List.of(XSmall1x));
        this.slotMapping.put(SlotType.SMALL, List.of(XSmall2x));
        this.slotMapping.put(SlotType.MEDIUM, List.of(XSmall4x));
        this.slotMapping.put(SlotType.LARGE, List.of(XSmall8x));
        this.slotMapping.put(SlotType.X_LARGE, List.of(XSmall16x));
    }

    public List<SlotInfo> getSlotMappingFor(SlotType slotType) {
        return this.slotMapping.get(slotType);
    }
}
