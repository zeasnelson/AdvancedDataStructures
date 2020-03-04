public class Node<T extends Comparable<T>> {

    /**
     * left child
     */
    protected Node<T> left;

    /**
     * right child
     */
    protected Node<T> right;

    /**
     * Value of the node
     */
    protected T value;

    /**
     * if duplicates are being inserted, user a counter to indicate the # of repetitions
     */
    protected int valueCount;


    public Node(T value){
        this.value = value;
        this.left = null;
        this.right = null;
        this.valueCount = 0;
    }



    //getters and setters

    public int getValueCount() {
        return valueCount;
    }

    public void setValueCount(int valueCount) {
        this.valueCount = valueCount;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "left=" + left +
                ", right=" + right +
                ", value=" + value +
                ", valueCount=" + valueCount +
                '}';
    }
}
