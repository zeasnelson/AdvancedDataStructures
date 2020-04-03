public class Tracker {

    /**
     * To store the output of the function being tracked
     */
    private String funcOutput;

    /**
     * To store the function parameter value/s
     */
    private String parameters;

    /**
     * Name of the algorithm/data structure
     */
    private String algorithmName;

    /**
     * Operation name being performed by the algorithm
     */
    private String operationName;

    /**
     * Nodes traversed
     */
    private Long nodesTraversed;

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


    public Tracker(String operationName) {
        Long zero = Long.valueOf(0);
        this.dataMovement = zero;
        this.comparisons = zero;
        this.nodesTraversed = zero;
        this.algorithmName = "";
        this.operationName = operationName;
        this.funcOutput = "";
        this.parameters = "";
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
     * Increment the nodesTraversed by one
     */
    public void incNodesTraversed(){
        this.nodesTraversed++;
    }


    /**
     * automatically stores the current time in milliseconds
     */
    public void setStartTime(){
        this.startTime = System.currentTimeMillis();
    }


    /**
     * automatically stores the current time in milliseconds
     */
    public void setEndTime(){
        this.endTime = System.currentTimeMillis();
    }

    public String getFuncOutput() {
        return funcOutput;
    }

    public void setFuncOutput(String funcOutput) {
        this.funcOutput = funcOutput;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public Long getStartTime(){
        return this.startTime;
    }

    public Long getEndTime(){
        return this.endTime;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public Long getNodesTraversed() {
        return nodesTraversed;
    }

    public void setNodesTraversed(Long nodesTraversed) {
        this.nodesTraversed = nodesTraversed;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
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
    public String toString(){
        return "Operation Name:            " + this.operationName +
                (this.parameters.equals("") ? ""  : "\nParameter/s value/s:       " + this.parameters)     +
                (this.funcOutput.equals("") ? ""  : "\nOutput:                    " + this.funcOutput)     +
                (this.nodesTraversed == 0   ? ""  : "\nNumber of nodes traversed: " + this.nodesTraversed) +
                (this.dataMovement == 0     ? ""  : "\nSwaps/Data moved:          " + this.dataMovement)   +
                (this.comparisons == 0      ? ""  : "\nNumber of comparisons:     " +this.comparisons)     +

                "\nStart time:                " + this.startTime + " milliseconds " +
                "\nEnd time:                  " + this.endTime +  " milliseconds " +
                "\nTotal time:                " + getTotalTimeMilli() + "  milliseconds\n";
    }

}
