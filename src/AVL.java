import javax.sound.midi.Track;
import java.util.ArrayList;
import java.util.Random;

public class AVL<T extends Comparable<T>> extends BST<T>{


    /**
     * Method to rotate a node to the left
     * @param node the node to be rotated
     * @return the new parent node
     */
    private Node<T> leftRotate(Node<T> node, Tracker tracker){
        //each rotation will count as one data movement
        tracker.incDataMovement();

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
    private Node<T> rightRotate(Node<T> node, Tracker tracker){

        //each rotation will count as one data movement
        tracker.incDataMovement();

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
    private int getBalanceFactor(Node<T> node, Tracker tracker){
        if( node == null ){
            return 0;
        }

        int leftHeight = getHeight(node.left, tracker);
        int rightHeight = getHeight(node.right, tracker);
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
        l = getHeight(node.left, tracker);
        r = getHeight(node.right, tracker);

        return (Math.abs(l-r) <= 1 && isBalanced(node.left, tracker) && isBalanced(node.right, tracker));

    }


    @Override
    public Node<T> remove(Node<T> node, T value, Tracker tracker) {
        node = super.remove(node, value, tracker);

        if (node == null)
            return null;

        //Each iteration will count as a comparison
        tracker.incComparisons();

        int balance = getBalanceFactor(node, tracker);

        // Left Left Case
        if (balance > 1 && getBalanceFactor(node.left, tracker) >= 0)
            return rightRotate(node, tracker);

        // Left Right Case
        else if (balance > 1 && getBalanceFactor(node.left, tracker) < 0){
            node.left = leftRotate(node.left, tracker);
            return rightRotate(node, tracker);
        }

        // Right Right Case
        else if (balance < -1 && getBalanceFactor(node.right, tracker) <= 0)
            return leftRotate(node, tracker);

        // Right Left Case
        else if (balance < -1 && getBalanceFactor(node.right, tracker) > 0) {
            node.right = rightRotate(node.right, tracker);
            return leftRotate(node, tracker);
        }
        return node;
    }


    @Override
    public Node<T> insert(Node<T> node, T value, Tracker tracker){
       node = super.insert(node, value, tracker);

        //Each iteration will count as a comparison
        tracker.incComparisons();

       //balance
       int balance = getBalanceFactor(node, tracker);

        //left left rotation
        if( balance > 1 && value.compareTo(node.left.value) < 0 ){
            return rightRotate(node, tracker);
        }
        //Right Right rotation
        if( balance < -1 && value.compareTo(node.right.value) > 0 ){
            return leftRotate(node, tracker);
        }
        //Left Right rotation
        if( balance > 1 && value.compareTo(node.left.value) > 0 ){
            node.left = leftRotate(node.left, tracker);
            return rightRotate(node, tracker);
        }
        //Right Left rotation
        if( balance < -1 && value.compareTo(node.right.value) < 0 ){
            node.right = rightRotate(node.right, tracker);
            return leftRotate(node, tracker);
        }

       return node;
    }


    public static void main(String[] args) {


        Random rand = new Random();

        int [] nums = new int[10000];
        for( int i = 0; i < nums.length; i++){
            nums[i] = rand.nextInt(10000);
        }
        System.out.println("done with arr");

        AVL<Integer> tree = new AVL<>();

        for( Integer i : nums ){
            tree.insert(i);
        }


        Tracker deleteTracker = tree.remove(7);
        Tracker insertTracker = tree.insert(7);
        Tracker nodeCountTracker = tree.getNodeCount();
        Tracker getMinTracker = tree.getMin();

        System.out.println(deleteTracker);
        System.out.println(insertTracker);
        System.out.println(nodeCountTracker);
        System.out.println(getMinTracker);
        System.out.println(tree.getHeight());
    }





}
