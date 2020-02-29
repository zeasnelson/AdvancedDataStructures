public class Tracker {

    /**
     * Keeps count of number of swaps
     */
    private Long swaps;

    /**
     * Keeps count of number of comparisons
     */
    private Long comparisons;

    /**
     * The name of the algorithm hows operations are being tracked
     */
    private String algorithmName;

    /**
     * To store the time an algorithm started
     */
    private Long startTime;

    /**
     * To store the time at which an algorithm stopped
     */
    private Long endTime;


    public Tracker(String algorithmName) {
        Long zero = Long.valueOf(0);
        this.swaps = zero;
        this.comparisons = zero;
        this.algorithmName = algorithmName;
    }

    /**
     * Returns the difference of endTime and startTime
     * @return endTime - startTime
     */
    public Long getTotalTimeMilli(){
        if( endTime != null && startTime != null  ){
            return endTime - startTime;
        }
        return Long.valueOf(0);
    }

    /**
     * Increment the swaps count by one
     */
    public void incSwaps(){
        this.swaps++;
    }

    /**
     * Increment the comparisons count by one
     */
    public void incComparisons(){
        this.comparisons++;
    }


    /**
     * automatically stores the current time in milliseconds
     */
    public void setStartTime(){
        this.startTime = System.currentTimeMillis();
    }

    public Long getStartTime(){
        return this.startTime;
    }

    public Long getEndTime(){
        return this.endTime;
    }

    /**
     * automatically stores the current time in milliseconds
     */
    public void setEndTime(){
        this.endTime = System.currentTimeMillis();
    }

    public Long getSwaps() {
        return swaps;
    }

    public void setSwaps(Long swaps) {
        this.swaps = swaps;
    }

    public Long getComparisons() {
        return comparisons;
    }

    public void setComparisons(Long comparisons) {
        this.comparisons = comparisons;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    @Override
    public String toString() {
        return "Tracker{" +
                "swaps=" + swaps +
                ", comparisons=" + comparisons +
                ", algorithmName='" + algorithmName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", totalTime=" + getTotalTimeMilli() +
                '}';
    }

    public static void main(String[] args) throws InterruptedException {
        Tracker tk = new Tracker("Shell");
        tk.setStartTime();
        tk.incComparisons();
        tk.incComparisons();
        tk.incComparisons();
        tk.incComparisons();
        tk.incComparisons();
        tk.incComparisons();
        Thread.sleep(1000);

        tk.setEndTime();

        System.out.println(tk);

    }

}
