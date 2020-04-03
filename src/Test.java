
public class Test {



    public static void testTrees(int n){
        //create trees
        AVL<Integer> AVLTree = new AVL<>();
        BST<Integer> binarySearchTree = new BST<>();

        for( int i = 0; i < n; i++ ){
            AVLTree.insert(i);
            binarySearchTree.insert(i);
        }

        //Get the tracking info from the avl tree
        System.out.println("\n\n ---- AVL with increasing numbers 0,1,2,...," + n + " ----- ");
        System.out.println(AVLTree.insert(n));
        System.out.println(AVLTree.remove(n));
        System.out.println(AVLTree.contains(n-1));
        System.out.println(AVLTree.get(n-1));

        //Get the tracking info from the BST
        System.out.println("\n\n---- BST with increasing numbers 0,1,2,...," + n + " ----- ");
        System.out.println(binarySearchTree.insert(n));
        System.out.println(binarySearchTree.remove(n));
        System.out.println(binarySearchTree.contains(n-1));
        System.out.println(binarySearchTree.get(n-1));

    }


    public static void testTreesWithFile(){
        AVL<Character> textAVL = new AVL<>();
        BST<Character> textBST = new BST<>();

        //Read text file
        FileIO.setIo("coronavirus.txt");
        int c = FileIO.getNextChar();
        while( c != -1 ){
            textAVL.insert((char)c);
            textBST.insert((char)c);
            c = FileIO.getNextChar();
        }

        //Get the tracking info from the avl tree
        System.out.println("\n\n\n ---- AVL with data from text file ---- ");
        System.out.println(textAVL.insert('8'));
        System.out.println(textAVL.remove('7'));
        System.out.println(textAVL.contains('8'));
        System.out.println(textAVL.get('@'));

        //Get the tracking info from the BST tree
        System.out.println("\n\n ---- BST with data from text file ---- ");
        System.out.println(textBST.insert('8'));
        System.out.println(textBST.remove('7'));
        System.out.println(textBST.contains('8'));
        System.out.println(textBST.get('@'));
    }


    public static void main(String[] args) {

        //Don't exceed 1000
        int n = 1000;

        //Every function was implemented such that comparisons, data movement, nodes traversed,
        //start time, end time, etc... are tracked

        //set how large the test data will be
        System.out.println("Filling up trees");
        testTrees(10);
        testTrees(100);
        testTrees(1000);
        testTreesWithFile();



    }

}
