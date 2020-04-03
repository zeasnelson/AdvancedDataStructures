
public class BST<T extends Comparable<T>> {

    private Node<T> root;

    public BST(){
        root = null;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    /**
     * Perform a node count but return a count of its operation instead.
     * Tracks comparisons, data movements, number of nodes traversed etc
     * @return A Tracker obj
     */
    public Tracker getNodeCount(){
        Tracker tracker = new Tracker("getNodeCount");
        tracker.setStartTime();
        int output = getNodeCount(root, tracker);
        tracker.setEndTime();
        tracker.setFuncOutput(Integer.toString(output));
        return tracker;
    }

    /**
     * Get the number of nodes in the tree
     * @param root root node
     * @param tracker An object to keep track of comparisons, data swaps, etc
     * @return the number of nodes
     */
    public int getNodeCount(Node<T> root, Tracker tracker){
        if( root == null ){
            return 0;
        }
        else {
            tracker.incNodesTraversed();
            return 1 + getNodeCount(root.left, tracker) + getNodeCount(root.right, tracker);
        }
    }


    /**
     * Remove a node from a tree but return a count of its operations instead.
     * Tracks comparisons, data movements, number of nodes traversed etc
     * @return A Tracker obj
     * @param value Value to be removed from the tree
     */
    public Tracker remove(T value){
        Tracker tracker = new Tracker("remove");
        tracker.setParameters(value.toString());
        tracker.setStartTime();
        this.root = remove(this.root, value, tracker);
        tracker.setEndTime();
        return tracker;
    }

    /**
     *  Remove a node from a tree
     * @param node the root node
     * @param value Value to be removed
     * @param tracker An object to keep track of comparisons, data swaps, etc
     * @return The root of the tree
     */
    public Node<T> remove(Node<T> node, T value, Tracker tracker){

        if( node == null ){
            return null;
        }
        else if( value.compareTo(node.value) < 0 ){
            //track the number of comparisons
            tracker.incComparisons();
            node.left = remove(node.left, value, tracker);
        }
        else if( value.compareTo(node.value) > 0){
            //track the number of comparisons
            tracker.incComparisons();
            node.right = remove(node.right, value, tracker);
        }
        else{
            //track the number of comparisons
            tracker.incComparisons();

            // node with only one child or no child
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;

            // node with two children: Get the inorder successor (smallest
            // in the right subtree
            node.value = getMin(node.right).value;

            // Delete the inorder successor
            node.right = remove(node.right, node.value, tracker);

        }
        return node;
    }


    /**
     * Insert a node into a tree but return a count of its operations instead.
     * Tracks comparisons, data movements, number of nodes traversed etc
     * @return A Tracker obj
     * @param value Value to be inserted into the tree
     */
    public Tracker insert(T value){
        Tracker tracker = new Tracker("insert");
        tracker.setParameters(value.toString());
        tracker.setStartTime();
        root = insert(root, value, tracker);
        tracker.setEndTime();
        return tracker;
    }


    /**
     *  Insert a node in a tree
     * @param node The root node of the tree
     * @param value The value to be inserted
     * @param tracker An object to keep track of comparisons, data swaps, etc
     * @return the root node of the tree
     */
    public Node<T> insert(Node<T> node, T value, Tracker tracker){

        if( node == null ){
            //Track number of comparisons, there should be one at each level
            tracker.incComparisons();
            node = new Node<>(value);
        }
        if( value.compareTo(node.value) < 0 ){
            //Track number of comparisons, there should be one at each level
            tracker.incComparisons();
            node.left =  insert(node.left, value, tracker);
        }
        else if( value.compareTo(node.value) > 0 ){
            //Track number of comparisons, there should be one at each level
            tracker.incComparisons();
            node.right = insert(node.right, value, tracker);
        }
        return node;
    }



    /**
     * Check weather the tree is empty
     * @return true if empty, false otherwise
     */
    public Boolean isEmpty(){
        return this.root == null;
    }


    /**
     * Check if the tree contains a given value but return a count of its operations instead.
     * Tracks comparisons, data movements, number of nodes traversed etc
     * @param value The value to be checked if it exists in the tree
     * @return A Tracker obj
     */
    public Tracker contains(T value){
        Tracker tracker = new Tracker("contains");
        tracker.setParameters(value.toString());
        tracker.setStartTime();
        Boolean output = contains(this.root, value, tracker);
        tracker.setEndTime();
        tracker.setFuncOutput(Boolean.toString(output));
        return tracker;
    }


    /**
     * Check whether tree contains a value
     * @param value the value to be searched
     * @param root root of the tree
     * @param tracker An object to keep track of comparisons, data swaps, etc
     * @return true if found, false otherwise
     */
    public Boolean contains(Node<T> root, T value, Tracker tracker){

        Node<T> current = root;
        while( current != null ){

            if( value.compareTo(current.value) == 0 ){
                //Track number of comparisons, there should be one at each level
                tracker.incComparisons();
                return true;
            }
            else if( value.compareTo(current.value) < 0 ){
                //Track number of comparisons, there should be one at each level
                tracker.incComparisons();
                current = current.left;
            }
            else if( value.compareTo(current.value) > 0 ){
                //Track number of comparisons, there should be one at each level
                tracker.incComparisons();
                current = current.right;
            }
        }
        return false;
    }



    /**
     * Get an element from a tree but return a count of its operations instead.
     * Tracks comparisons, data movements, number of nodes traversed etc
     * @param value Value to be found
     * @return A Tracker obj
     */
    public Tracker get(T value){
        Tracker tracker = new Tracker("get");
        tracker.setParameters(value.toString());
        tracker.setStartTime();
        Node<T> output = get(this.root, value, tracker);
        tracker.setEndTime();
        tracker.setFuncOutput((output == null ? "none" : output.value).toString());
        return tracker;
    }


    /**
     * Get the a node in the tree that matches a value
     * @param root root of the tree
     * @param tracker An object to keep track of comparisons, data swaps, etc
     * @param value the value of the node to be searched
     * @return the node with the value
     */
    public Node<T> get(Node<T> root, T value, Tracker tracker){

        Node<T> current = root;
        while (current != null){

            tracker.incComparisons();
            tracker.incNodesTraversed();

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
     * Get the height of a tree but return a count of its operations instead.
     * Tracks comparisons, data movements, number of nodes traversed etc
     * @return A Tracker obj
     */
    public Tracker getHeight(){
        Tracker tracker = new Tracker("getHeight");
        tracker.setStartTime();
        int output = getHeight(this.root);
        tracker.setEndTime();
        tracker.setFuncOutput(Integer.toString(output));
        return tracker;
    }

    /**
     * Get the tree height
     * @param node root node of the tree
     * @return the height of the tree
     */
    public int getHeight(Node<T> node){
        if( node == null ){
            return 0;
        }
        //No comparisons, track number of nodes were visited
        int lh = getHeight(node.left);
        int rh = getHeight(node.right);



        return 1 + Math.max(lh, rh);
    }


    /**
     * Get the max value from a tree but return a count of its operations instead.
     * Tracks comparisons, data movements, number of nodes traversed etc
     * @return A Tracker obj
     */
    public Tracker getMax(){
        Tracker tracker = new Tracker("getMax");
        tracker.setStartTime();
        T output = getMax(this.root);
        tracker.setEndTime();
        tracker.setFuncOutput((output == null ? "none" : output.toString()));
        return tracker;
    }


    /**
     * Find the max value from the tree
     * @param node root of the tree
     * @return the max value
     */
    public T getMax(Node<T> node){
        while (node.right != null){
            node = node.right;
        }
        if( node != null )
            return node.value;
        return null;
    }


    /**
     * Get the min value from a tree but return a count of its operations instead.
     * Will start from the root node
     * @return A Tracker obj
     */
    public Node<T> getMin(){
        return getMin(root);
    }


    /**
     * Find the min value from the tree
     * @param node the root node of the tree
     * @return the min value
     */
    public Node<T> getMin(Node<T> node){
        while (node.left != null){
            node = node.left;
        }

        return node;
    }


    /**
     * inOrder traverse a tree but return a count of its operations instead.
     * Tracks comparisons, data movements, number of nodes traversed etc
     * @return A Tracker obj
     */
    public Tracker inOrder(){
        Tracker tracker = new Tracker("inOrder");
        tracker.setStartTime();
        inOrder(this.root, tracker);
        tracker.setEndTime();
        return tracker;
    }


    /**
     * inOrder print of the tree
     * @param tracker An object to keep track of comparisons, data swaps, etc
     * @param node the root node of the tree
     */

    public Tracker inOrder(Node<T> node, Tracker tracker){
        if( node != null ) {
            //track the number of nodes visited
            tracker.incNodesTraversed();

            inOrder(node.left, tracker);
            System.out.print(node.value + " ");
            inOrder(node.right, tracker);
        }
        return tracker;
    }


    /**
     * preOrder traversal of a tree but return a count of its operations instead.
     * Tracks comparisons, data movements, number of nodes traversed etc
     * @return A Tracker obj
     */
    public Tracker preOrder(){
        Tracker tracker = new Tracker("preOrder");
        tracker.setStartTime();
        preOrder(this.root, tracker);
        tracker.setEndTime();
        tracker.setFuncOutput("To large to display");
        return tracker;
    }


    /**
     * pre order print
     * @param tracker An object to keep track of comparisons, data swaps, etc
     * @param node the root node of the tree
     */
    public void preOrder(Node<T> node, Tracker tracker){
        if( node != null ) {
            //count number of nodes traversed
            tracker.incNodesTraversed();

            System.out.print(node.value + " ");
            preOrder(node.left, tracker);
            preOrder(node.right, tracker);
        }
    }

    /**
     * postOrder traverse a tree but return a count of its operations instead.
     * Tracks comparisons, data movements, number of nodes traversed etc
     * @return A Tracker obj
     */
    public Tracker postOrder(){
        Tracker tracker = new Tracker("postOrder");
        tracker.setStartTime();
        postOrder(this.root, tracker);
        tracker.setEndTime();
        return tracker;
    }

    /**
     * post order print
     * @param tracker An object to keep track of comparisons, data swaps, etc
     * @param node the root node of the tree
     */
    public void postOrder(Node<T> node, Tracker tracker){
        if( node != null ) {
            //count the number of nodes traversed
            tracker.incNodesTraversed();

            postOrder(node.getLeft(), tracker);
            postOrder(node.getRight(), tracker);
            //System.out.print(node.getValue() + " ");
        }
    }


}
