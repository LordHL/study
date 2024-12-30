package cn.ktl.lab.springmvc.utils;

import java.time.Instant;

public class SnowflakeIdGenerator {
    private static final long EPOCH = 1704067200000L; // January 1, 2024 12:00:00 AM
    private static final long NODE_ID_BITS = 10L;
    private static final long SEQUENCE_BITS = 12L;
    private static final long SEQUENCE_MASK = -1L ^ (-1L << SEQUENCE_BITS);
    private volatile long lastTimestamp = -1L;
    private volatile long sequence = 0L;
    private final long nodeId;

    public SnowflakeIdGenerator(long nodeId) {
        this.nodeId = nodeId;
    }
    private long getTimestamp() {
        return Instant.now().toEpochMilli() - EPOCH;
    }

    private long waitMillis(long currentTimestamp) {
        while (currentTimestamp <= lastTimestamp) {
            currentTimestamp = getTimestamp();
        }
        return currentTimestamp;
    }

    public synchronized long generateId() {
        long currentTimestamp = getTimestamp();
        if(currentTimestamp < lastTimestamp) {
            throw new IllegalStateException("Invalid system clock to generate Id");
        }
        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if(sequence == 0) {
                // No values left in the sequence, wait for currentTimestamp
                currentTimestamp = waitMillis(currentTimestamp);
            }
        } else {
            // reset the sequence for the next millisecond
            sequence = 0;
        }
        lastTimestamp = currentTimestamp;
        long id = currentTimestamp << (NODE_ID_BITS + SEQUENCE_BITS) 
            | (nodeId << SEQUENCE_BITS)
            | sequence;
        return id;
    }

    public static void main(String[] args) {
        SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(1);
        long l = snowflakeIdGenerator.generateId();

        System.out.println(String.valueOf(l).length());
    }
}
