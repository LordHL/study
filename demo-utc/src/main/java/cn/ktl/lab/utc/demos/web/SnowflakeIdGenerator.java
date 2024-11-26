package cn.ktl.lab.utc.demos.web;

/**
 * @Author lin ho
 * Des: TODO
 */
public class SnowflakeIdGenerator {

    // 起始时间戳，建议设置为项目启动前的固定时间，单位：毫秒
    private static final long START_TIMESTAMP = 1577836800000L; // 2020-01-01 00:00:00

    // 各部分占用位数
    private static final long SEQUENCE_BITS = 12L;    // 序列号占用的位数
    private static final long MACHINE_BITS = 5L;      // 机器ID占用的位数
    private static final long DATA_CENTER_BITS = 5L;  // 数据中心ID占用的位数

    // 各部分的最大值
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_BITS); // 最大数据中心ID
    private static final long MAX_MACHINE_ID = ~(-1L << MACHINE_BITS);         // 最大机器ID
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);          // 最大序列号

    // 各部分的位移
    private static final long MACHINE_SHIFT = SEQUENCE_BITS;                   // 机器ID左移位数
    private static final long DATA_CENTER_SHIFT = SEQUENCE_BITS + MACHINE_BITS; // 数据中心ID左移位数
    private static final long TIMESTAMP_SHIFT = DATA_CENTER_SHIFT + DATA_CENTER_BITS; // 时间戳左移位数

    // 数据中心ID和机器ID
    private final long dataCenterId;
    private final long machineId;
    private long sequence = 0L; // 序列号
    private long lastTimestamp = -1L; // 上一次的时间戳

    /**
     * 构造器
     *
     * @param dataCenterId 数据中心ID
     * @param machineId    机器ID
     */
    public SnowflakeIdGenerator(long dataCenterId, long machineId) {
        if (dataCenterId > MAX_DATA_CENTER_ID || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("DataCenter ID can't be greater than %d or less than 0", MAX_DATA_CENTER_ID));
        }
        if (machineId > MAX_MACHINE_ID || machineId < 0) {
            throw new IllegalArgumentException(String.format("Machine ID can't be greater than %d or less than 0", MAX_MACHINE_ID));
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    /**
     * 生成唯一ID（线程安全）
     *
     * @return 唯一ID
     */
    public synchronized long nextId() {
        long currentTimestamp = System.currentTimeMillis();

        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate ID");
        }

        if (currentTimestamp == lastTimestamp) {
            // 如果当前时间与上次生成ID的时间相同，则递增序列
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0L) {
                // 序列号用尽（4096），等待下一毫秒
                currentTimestamp = getNextMillisecond();
            }
        } else {
            // 不在同一毫秒内，重置序列号
            sequence = 0L;
        }

        lastTimestamp = currentTimestamp;

        // 生成最终的ID
        return ((currentTimestamp - START_TIMESTAMP) << TIMESTAMP_SHIFT) // 时间戳部分
                | (dataCenterId << DATA_CENTER_SHIFT)                    // 数据中心ID部分
                | (machineId << MACHINE_SHIFT)                           // 机器ID部分
                | sequence;                                              // 序列号部分
    }

    /**
     * 获取下一毫秒时间戳，直到大于上一次时间戳
     */
    private long getNextMillisecond() {
        long millisecond = System.currentTimeMillis();
        while (millisecond <= lastTimestamp) {
            millisecond = System.currentTimeMillis();
        }
        return millisecond;
    }

    // 测试示例
    public static void main(String[] args) {
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1, 1);
        for (int i = 0; i < 10; i++) {
            System.out.println(idGenerator.nextId());
        }
    }
}

