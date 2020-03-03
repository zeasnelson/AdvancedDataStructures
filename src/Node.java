public class Node<T extends Comparable<T>> {

    protected Node<T> left;
    protected Node<T> right;
    protected T value;

    public Node(){}

    public Node(T value){
        this.value = value;
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
                "leftNode=" + left +
                ", rightNode=" + right +
                ", data=" + value +
                '}';
    }
}
