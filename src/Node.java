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


    public Node(T value){
        this.value = value;
        this.left = null;
        this.right = null;
    }



    //getters and setters
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
                '}';
    }
}
