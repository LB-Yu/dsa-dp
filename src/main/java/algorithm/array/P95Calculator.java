package algorithm.array;

public class P95Calculator {

    // 到目前为止出现过的数据点总数
    private long totalCount;
    // 所有区间，[0]为当前区间内出现过的数据点数，[1]为当前区间内所有出现过的数据点的平均值
    private long[][] ranges;

    // 大于这个值表示为异常值
    private int errorTime;

    public P95Calculator(int errorTime) {
        this.errorTime = errorTime;
        int len = errorTime + 2;
        ranges = new long[len][2];
    }

    /**
     * 添加一个元素
     * */
    public void add(int time) {
        int idx = getRangeIndex(time);
        ranges[idx][1] = ((ranges[idx][0] * ranges[idx][1]) + time) / (ranges[idx][0] + 1);
        ranges[idx][0] += 1;
        totalCount++;
    }

    public int getP95() {
        int i = errorTime + 1;
        long curSum = 0;
        long p5Num = (long) (totalCount * 0.05);
        while (i >= 0 && curSum <= p5Num) {
            curSum += ranges[i][0];
            --i;
        }
        i++;
        return (int) ranges[i][1];
    }

    /**
     *  一种简单的区间划分策略，在实际场景中可能需要调整
     * */
    private int getRangeIndex(int time) {
        if (time <= 1) return 1;                      // 小于1或等于1均认为是1
        else if (time <= errorTime) return time;      // 大于1且小于等于errorTime的时候每个值作为一个区间
        else return errorTime + 1;                    // 大于errorTime的时候认为是异常值
    }

    public static void main(String[] args) {
        P95Calculator p95Calculator = new P95Calculator(200);
        // p95Calculator.add()添加数据
        p95Calculator.getP95();
    }
}
