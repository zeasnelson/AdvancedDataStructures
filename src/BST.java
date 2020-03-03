public class BST<T extends Comparable<T>> {


    /**
     * Get the number of nodes in the tree
     * @param root
     * @return
     */
    public int getNodeCount(Node<T> root){
        if( root == null ){
            return 0;
        }
        else{
            return 1 + getNodeCount(root.left) + getNodeCount(root.right);
        }
    }


    /**
     * Method to remove any node
     * @param value The value of the node to be removed
     * @param node Root node
     * @return true if it was removed, false otherwise
     */
    public Node<T> remove(Node<T> node, T value){
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
            else {
                Node<T> temp = getMin(node.right);
                node.value = temp.value;
                node.right = remove(node.right, temp.value);
            }
        }
        return node;
    }


    /**
     * Given a root node, insert the value in the tree
     * @param node Root node
     * @param value Value to be inserted
     * @return the inserted node
     */
    public Node<T> insert(Node<T> node, T value){
        if( node == null ){
            node = new Node<>(value);
        }
        if( value.compareTo(node.value) < 0 ){
            node.left =  insert(node.left, value);
        }
        if( value.compareTo(node.value) > 0 ){
            node.right = insert(node.right, value);
        }
        return node;
    }



    /**
     * Check weather the tree is empty
     * @return true if empty, false otherwise
     */
    public Boolean isEmpty(Node<T> root){
        return root == null;
    }


    /**
     * Check whether tree contains a value
     * @param value the value to be searched
     * @param root root node
     * @return true if found, false otherwise
     */
    public Boolean contains(Node<T> root, T value){

        Node<T> current = root;
        while( current != null ){
            if( value.compareTo(current.value) == 0 ){
                return true;
            }
            else if( value.compareTo(current.value) < 0 ){
                current = current.left;
            }
            else if( value.compareTo(current.value) > 0 ){
                current = current.right;
            }
        }
        return false;
    }


    /**
     * Get the a node in the tree that matches a value
     * @param value the value of the node to be searched
     * @param root root node
     * @return the node with the value
     */
    public Node<T> get(Node<T> root, T value){
        Node<T> current = root;
        while (current != null){

            if( value.compareTo(current.value) == 0 ){
                return current;
            }
            else if( value.compareTo(current.value) < 0 ){
                current = current.left;
            }
            else if( value.compareTo(current.value) > 0 ){
                current = current.right;
            }
        }
        return null;
    }


    /**
     * Check if a node is a leaf node
     * @param node The node to be checked
     * @return True if is leaf, false otherwise
     */
    public Boolean isLeaf(Node<T> node){
        return (node.left == null && node.right == null);
    }



    /**
     * Get the tree height
     * @return the height of the tree
     */
    public int getHeight(Node<T> node){
        if( node == null ){
            return 0;
        }
        int lh = getHeight(node.left);
        int rh = getHeight(node.right);

        return 1 + Math.max(lh, rh);
    }


    /**
     * Find the max value from the tree
     * @return the max value
     */
    public Node<T> getMax(Node<T> node){
        while (node.right != null){
            node = node.right;
        }
        return node;
    }


    /**
     * Find the min value from the tree
     * @return the min value
     */
    public Node<T> getMin(Node<T> node){
        while (node.left != null){
            node = node.left;
        }
        return node;
    }


    /**
     * inOrder print of the tree
     */
    public void inOrder(Node<T> node){
        if( node != null ) {
            inOrder(node.left);
            System.out.print(node.value + " ");
            inOrder(node.right);
        }
    }


    /**
     * pre order print
     */
    public void preOrder(Node<T> node){
        if( node != null ) {
            System.out.print(node.value + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }


    /**
     * post order print
     */
    public void postOrder(Node<T> node){
        if( node != null ) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.print(node.getValue() + " ");
        }
    }

}
