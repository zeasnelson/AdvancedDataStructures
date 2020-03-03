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


    public Boolean isBalanced(Node<T> node){
        int l, r;
        if( node == null ){
            return true;
        }
        l = getHeight(node.left);
        r = getHeight(node.right);

        return (Math.abs(l-r) <= 1 && isBalanced(node.left) && isBalanced(node.right));

    }


    public Node<T> remove(Node<T> node, T value) {
        if( node == null ){
            return null;
        }
        if( value.compareTo(node.value) < 0 ){
            node.left = remove(node.left, value);
        }
        else if( value.compareTo(node.value) > 0){
            node.right = remove(node.right, value);
        }
        else{
            // node with only one child or no child
            if (isLeaf(node)){
                Node<T> temp = node.left;
                if (temp == null)
                    temp = node.right;

                // No child case
                node = temp;
            }
            else{
                Node<T> temp = getMin(node.right);
                node.value = temp.value;
                node.right = remove(node.right, temp.value);
            }

        }

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
    public Node<T> insert(T data){
        Node<T> root = getRoot();
        root = insert(root, data);
        setRoot(root);
        return root;
    }


    private Node<T> insert(Node<T> node, T value){
       if( node == null ){
            incNodeCount();
           node = new Node<>(value);
       }
       if( value.compareTo(node.getValue()) < 0 ){
           node.left =  insert(node.left, value);
       }
       if( value.compareTo(node.getValue()) > 0 ){
           node.right = insert(node.right, value);
       }

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


    public static void main(String[] args) {

        AVL<Integer> avl = new AVL<>();

        avl.insert(1);
        avl.insert(2);
        avl.insert(3);
        avl.insert(4);
        avl.insert(5);
        avl.insert(6);

        System.out.println("delete");
        System.out.println(avl.remove(6).value);
        System.out.println(avl.remove(5).value);
        System.out.println(avl.remove(1).value);
        System.out.println();


    }





}
