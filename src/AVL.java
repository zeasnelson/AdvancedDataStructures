import java.util.Random;

public class AVL<T extends Comparable<T>> extends BST<T>{


    /**
     * Method to rotate a node to the left
     * @param node the node to be rotated
     * @return the new parent node
     */
    private Node<T> leftRotate(Node<T> node){

        Node<T> rightNode = node.right;
        Node<T> leftNode  = rightNode.left;

        rightNode.left = node;
        node.right = leftNode;

        return rightNode;
    }

    /**
     * Method to rotate to the left
     * @param node the node to be rotated
     * @return the new parent node
     */
    private Node<T> rightRotate(Node<T> node){

        Node<T> leftNode  = node.left;
        Node<T> rightNode = leftNode.right;

        leftNode.right = node;
        node.left = rightNode;

        return leftNode;
    }

    /**
     * Calculate the balance factor of a node
     * @param node The node to check the balance factor
     * @return the balance factor
     */
    private int getBalanceFactor(Node<T> node){
        if( node == null ){
            return 0;
        }

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return leftHeight - rightHeight;
    }


    /**
     * Check if a node is balanced or not
     * @param node the node to be checked
     * @return true if is balanced, false otherwise
     */
    public Boolean isBalanced(Node<T> node, Tracker tracker){
        int l, r;
        if( node == null ){
            return true;
        }
        l = getHeight(node.left);
        r = getHeight(node.right);

        return (Math.abs(l-r) <= 1 && isBalanced(node.left, tracker) && isBalanced(node.right, tracker));

    }

    @Override
    public Tracker remove(T value){
        return super.remove(value);
    }


    @Override
    public Node<T> remove(Node<T> node, T value, Tracker tracker) {
        node = super.remove(node, value, tracker);

        if (node == null)
            return null;

        int balance = getBalanceFactor(node);

        // Left Left Case
        if (balance > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);

        // Left Right Case
        else if (balance > 1 && getBalanceFactor(node.left) < 0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Right Case
        else if (balance < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);

        // Right Left Case
        else if (balance < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    @Override
    public Tracker insert(T value){
        return super.insert(value);
    }

    @Override
    public Node<T> insert(Node<T> node, T value, Tracker tracker){
       node = super.insert(node, value, tracker);

       //balance
       int balance = getBalanceFactor(node);

        //left left rotation
        if( balance > 1 && value.compareTo(node.left.value) < 0 ){
            return rightRotate(node);
        }
        //Right Right rotation
        if( balance < -1 && value.compareTo(node.right.value) > 0 ){
            return leftRotate(node);
        }
        //Left Right rotation
        if( balance > 1 && value.compareTo(node.left.value) > 0 ){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //Right Left rotation
        if( balance < -1 && value.compareTo(node.right.value) < 0 ){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

       return node;
    }

}
