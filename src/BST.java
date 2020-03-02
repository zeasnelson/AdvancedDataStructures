public class BST<T extends Comparable<T>>{

    private Node<T> root;
    private int nodeCount;

    public BST(){
        this.root = null;
        this.nodeCount = 0;
    }

    /**
     * Delete all nodes
     */
    public void clear(){
        this.root = null;
        this.nodeCount = 0;
    }

    /**
     * Get the root of the tree
     * @return the root node
     */
    public Node<T> getRoot(){
        return this.root;
    }

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
     * Get the total number of nodes in the tree
     * @return node count
     */
    public int getNodeCount(){
        return nodeCount;
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
     * Check if a node has two children
     * @param node The node to be checked
     * @return True if has two children, false otherwise
     */
    public Boolean hasTwoChildren(Node<T> node){
        return (node.right != null && node.left != null);
    }

    /**
     * Check if a node has one child
     * @param node The node to be checked
     * @return True if has one children, false otherwise
     */
    public Boolean hasOneChild(Node<T> node){
        return (node.left == null && node.right != null ) ||
                (node.left != null && node.right == null );
    }

    /**
     * Function to delete only leaf nodes
     * @param parent parent of the leaf node
     * @param child the leaf node
     * @return true if it was deleted, false otherwise
     */
    private Boolean deleteLeaf(Node<T> parent, Node<T> child){
        if( parent == null && child == null ){
            return false;
        }
        else if( parent == null ){
            root = null;
        }
        else if( child.value.compareTo(parent.value) < 0 ){
            parent.left = null;
        }
        else if( child.value.compareTo(parent.value) > 0 )
            parent.right = null;

        nodeCount--;
        return true;
    }

    /**
     * Method to remove nodes that have only one child
     * @param parent The parent of the node with only one child
     * @param child a node with only one child
     * @return
     */
    private Boolean deleteWithOneChild(Node<T> parent, Node<T> child){
        if( parent == null && child == null ){
            return false;
        }
        else if( parent == null ){
            if( child.left != null ){
                root = child.left;
            }
            else
                root = child.right;
        }
        else {
            if( child.value.compareTo(parent.value) > 0 ){
                if( child.left != null )
                    parent.right = child.left;
                else
                    parent.right = child.right;
            }
            if( child.value.compareTo(parent.value) < 0 ){
                if( child.left != null )
                    parent.left = child.left;
                else
                    parent.left = child.right;
            }
        }
        nodeCount--;
        return true;
    }

    /**
     * Method to delete node that have two children
     * @param parent The parent node of a node with two children
     * @param child a node with two children
     * @return true if it was deleted, false otherwise
     */
    private Boolean deleteWithTwoChild(Node<T> parent, Node<T> child){
        if( parent == null && child == null ){
            return false;
        }
        else {
            Node<T> prev = null;
            Node<T> current = child.right;
            if( current.left == null ){
                child.value = current.value;
                child.right = current.right;
            }
            else{
                while (current.left != null){
                    prev = current;
                    current = current.left;
                }
                child.value = current.value;
                prev.left = null;
            }
        }
        nodeCount--;
        return true;
    }


    /**
     * Method to remove any node
     * @param value The value of the node to be removed
     * @return true if it was removed, false otherwise
     */
    public Boolean remove(T value){
        Node<T> previous = null;
        Node<T> current = root;
        while (current != null){
            if( value.compareTo(current.value) == 0 ){
                if( isLeaf(current) ){
                    return deleteLeaf(previous, current);
                }
                else if( hasOneChild(current) ){
                    return deleteWithOneChild(previous, current);
                }
                else if( hasTwoChildren(current) ){
                    return deleteWithTwoChild(previous, current);
                }
            }
            else if( value.compareTo(current.value) < 0 ){
                previous = current;
                current = current.left;
            }
            else if( value.compareTo(current.value) > 0 ){
                previous = current;
                current = current.right;
            }
        }
        return null;
    }

    /**
     * helper method for insert method
     * @param value the value of the new node to be added
     */
    public Node<T> insert(T value){
        return root = insert(root, value);
    }

    public Node<T> insert(Node<T> node, T value){
        if( node == null ){
            node = new Node(value);
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
     * Get the tree height
     * @return the height of the tree
     */
    public int getHeight(){return getHeight(root);}
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
    public Node<T> getMax(){
        Node<T> current = root;
        while (current.right != null){
            current = current.right;
        }

        return current;
    }

    /**
     * Find the min value from the tree
     * @return the min value
     */
    public Node<T> getMin(){
        Node<T> current = root;
        while (current.left != null){
            current = current.left;
        }

        return current;
    }

    /**
     * inOrder print of the tree
     */
    public  void inOrder(){ inOrder(root);}
    private void inOrder(Node<T> node){
        if( node != null ) {
            inOrder(node.getLeft());
            System.out.println(node.getValue());
            inOrder(node.getRight());
        }
    }

    /**
     * pre order print
     */
    public void preOrder(){preOrder(root);    }
    private void preOrder(Node<T> node){
        if( node != null ) {
            System.out.println(node.getValue());
            preOrder(node.getLeft());
            preOrder(node.getRight());
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
            System.out.println(node.getValue());
        }
    }

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.preOrder();
        System.out.println("Tree height: " + tree.getHeight());

    }

}
