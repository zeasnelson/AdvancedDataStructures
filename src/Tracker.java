public class Tracker {

    /**
     * Name of the algorithm/data structure
     */
    private String algorithmName;

    /**
     * Operation name being performed by the algorithm
     */
    private String operationName;

    /**
     * Keeps count of number of swaps
     */
    private Long dataMovement;

    /**
     * Keeps count of number of comparisons
     */
    private Long comparisons;

    /**
     * To store the time an algorithm started
     */
    private Long startTime;

    /**
     * To store the time at which an algorithm stopped
     */
    private Long endTime;


    public Tracker(String algorithmName, String operationName) {
        Long zero = Long.valueOf(0);
        this.dataMovement = zero;
        this.comparisons = zero;
        this.algorithmName = algorithmName;
        this.operationName = operationName;
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
    public void incDataMovement(){
        this.dataMovement++;
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

    public Long getDataMovement() {
        return dataMovement;
    }

    public void setDataMovement(Long dataMovement) {
        this.dataMovement = dataMovement;
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
                "swaps=" + dataMovement +
                ", comparisons=" + comparisons +
                ", algorithmName='" + algorithmName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", totalTime=" + getTotalTimeMilli() +
                '}';
    }

}
