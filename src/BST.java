public class BST<T extends Comparable<T>> {

    private Node<T> root;
    private int nodeCount;

    public BST(){
        this.root = null;
        this.nodeCount = 0;
    }



    /**
     * Method to remove any node
     * @param value The value of the node to be removed
     * @return true if it was removed, false otherwise
     */
    public Node<T> remove(T value){
        return root = remove(root, value);
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
            nodeCount--;
        }
        return node;
    }


    /**
     * helper method for insert method
     * @param value the value of the new node to be added
     */
    public Node<T> insert(T value){
        return root = insert(root, value);
    }


    /**
     * Given a root node, insert the value in the tree
     * @param node Root node
     * @param value Value to be inserted
     * @return the inserted node
     */
    private Node<T> insert(Node<T> node, T value){
        if( node == null ){
            node = new Node<T>(value);
            nodeCount++;
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
     * Delete all nodes
     */
    public void clear(){
        this.root = null;
        this.nodeCount = 0;
    }


    /**
     * Increase thee node count variable by one
     */
    public void incNodeCount(){
        this.nodeCount++;
    }

    /**
     * decrease the node count by one
     */
    public void decNodeCount(){
        if( nodeCount > 0 ){
            nodeCount--;
        }
    }


    /**
     * Get the total number of nodes in the tree
     * @return node count
     */
    public int getNodeCount(){
        return nodeCount;
    }


    /**
     * Get the root of the tree
     * @return the root node
     */
    public Node<T> getRoot(){
        return this.root;
    }


    /**
     * Set the root node of the tree
     * @param node a node
     */
    public void setRoot(Node<T> node){
        this.root = node;
    }


    /**
     * Check weather the tree is empty
     * @return true if empty, false otherwise
     */
    public Boolean isEmpty(){
        return nodeCount == 0;
    }


    /**
     * Check whether tree contains a value
     * @param value the value to be searched
     * @return true if found, false otherwise
     */
    public Boolean contains(T value){

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
     * @return the node with the value
     */
    public Node<T> get(T value){
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
    public int getHeight(){
        return getHeight(this.root);
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
    public  void inOrder(){ inOrder(root);}
    private void inOrder(Node<T> node){
        if( node != null ) {
            inOrder(node.left);
            System.out.print(node.value + " ");
            inOrder(node.right);
        }
    }


    /**
     * pre order print
     */
    public void preOrder(){preOrder(root);    }
    private void preOrder(Node<T> node){
        if( node != null ) {
            System.out.print(node.value + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }


    /**
     * post order print
     */
    public void postOrder(){postOrder(root);}
    private void postOrder(Node<T> node){
        if( node != null ) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.print(node.getValue() + " ");
        }
    }

}
