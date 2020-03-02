public class AVL<T extends Comparable<T>> extends BST<T>{


    private Node<T> leftRotate(Node<T> node){
        Node<T> rightNode = node.right;
        Node<T> leftNode  = rightNode.left;

        rightNode.left = node;
        node.right = leftNode;

        return rightNode;
    }

    private Node<T> rightRotate(Node<T> node){
        Node<T> leftNode  = node.left;
        Node<T> rightNode = leftNode.right;

        leftNode.right = node;
        node.left = rightNode;

        return leftNode;
    }

    private int getBalanceFactor(Node<T> node){
        if( node == null ){
            return 0;
        }

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return leftHeight - rightHeight;
    }


    public Node<T> insert(T data){
        Node<T> root = getRoot();
        root = insert(root, data);
        setRoot(root);
        return root;
    }

    @Override
    public Node<T> insert(Node<T> node, T value){
       if( node == null ){
           node = new Node<T>(value);
       }
       if( value.compareTo(node.getValue()) < 0 ){
           node.left =  insert(node.left, value);
       }
       if( value.compareTo(node.getValue()) > 0 ){
           node.right = insert(node.right, value);
       }

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


    public static void main(String[] args) {

        AVL<Integer> avl = new AVL<>();

        avl.insert(1);
        avl.insert(2);
        avl.insert(3);
        avl.insert(4);
        avl.insert(5);
        System.out.println("Tree height is: " + avl.getHeight());

        avl.inOrder();

    }





}
