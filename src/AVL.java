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
    public Boolean isBalanced(Node<T> node){
        int l, r;
        if( node == null ){
            return true;
        }
        l = getHeight(node.left);
        r = getHeight(node.right);

        return (Math.abs(l-r) <= 1 && isBalanced(node.left) && isBalanced(node.right));

    }


    @Override
    public Node<T> remove(Node<T> node, T value) {
        node = super.remove(node, value);

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
    public Node<T> insert(Node<T> node, T value){
       node = super.insert(node, value);

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
        Node<Integer> root = null;

        root = avl.insert(root, 1);
        root = avl.insert(root, 2);
        root = avl.insert(root, 3);
        root = avl.insert(root, 4);
        root = avl.insert(root, 5);
        root = avl.insert(root, 6);

        System.out.println("delete");
        root = avl.remove(root, 6);
        root = avl.remove(root, 5);
        root = avl.remove(root, 1);
        root = avl.remove(root, 3);
        root = avl.insert(root, 5);
        root = avl.insert(root, 7);
        root = avl.insert(root, 9);
        root = avl.remove(root, 7);
        System.out.println();


        avl.preOrder(root);
        System.out.println("\nmax is: " + avl.getMax(root).value);
        System.out.println("min is: " + avl.getMin(root).value);
        System.out.println("node count: " + avl.getNodeCount(root));
        System.out.println("is empty: " + avl.isEmpty(root));
        System.out.println("contains: " + avl.contains(root, 9));
        System.out.println("contains non: " + avl.contains(root, 99));
        System.out.println("get: " + avl.get(root, 5));
        System.out.println("get non: " + avl.get(root, 88));
        System.out.println("is leaf: " + avl.isLeaf(root));
        System.out.println("is leaf: " + avl.isLeaf(new Node<>()));
        System.out.println("height: " + avl.getHeight(root));
        System.out.println("height non: " + avl.getHeight(new Node<>()));
        System.out.println(avl.isBalanced(root));

    }





}
