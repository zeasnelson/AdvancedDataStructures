

public class Test {



    public static void main(String[] args) {


        //create trees
        AVL<Integer> AVLTree = new AVL<>();
        BST<Integer> binarySearchTree = new BST<>();
        AVL<Character> textAVL = new AVL<>();
        BST<Character> textBST = new BST<>();

        //set how large the test data will be
        //Don't exceed 1000
        int n = 1000;
        System.out.println("Filling up trees");
        for( int i = 0; i < n; i++ ){
            AVLTree.insert(i);
            binarySearchTree.insert(i);
        }

        //Read text file
        FileIO.setIo("coronavirus.txt");
        int c = FileIO.getNextChar();
        while( c != -1 ){
            c = FileIO.getNextChar();
            textAVL.insert((char)c);
            textBST.insert((char)c);
        }


        //Every function was implemented such that comparisons, data movement, nodes traversed,
        //start time, end time, etc... are tracked

        //Get the tracking info from the avl tree
        System.out.println("\n\n\n ---- AVL with data from text file ---- ");
        System.out.println(textAVL.insert('8'));
        System.out.println(textAVL.remove('7'));
        System.out.println(textAVL.contains('8'));
        System.out.println(textAVL.get('@'));
        System.out.println(textAVL.getHeight());
//
//        //Get the tracking info from the BST tree
        System.out.println("\n\n ---- BST with data from text file ---- ");
        System.out.println(textBST.insert('8'));
        System.out.println(textBST.remove('7'));
        System.out.println(textBST.contains('8'));
        System.out.println(textBST.get('@'));
        System.out.println(textBST.getHeight());


        //Get the tracking info from the avl tree
        System.out.println("\n\n ---- AVL with increasing numbers 0,1,2,...,n---- ");
        System.out.println(AVLTree.insert(n));
        System.out.println(AVLTree.remove(n));
        System.out.println(AVLTree.contains(n-1));
        System.out.println(AVLTree.get(n-1));
        System.out.println(AVLTree.getHeight());


        //Get the tracking info from the BST
        System.out.println("\n\n---- BST with increasing numbers 0,1,2,...,n---- ");
        System.out.println(binarySearchTree.insert(n));
        System.out.println(binarySearchTree.remove(n));
        System.out.println(binarySearchTree.contains(n-1));
        System.out.println(binarySearchTree.get(n-1));
        System.out.println(binarySearchTree.getHeight());




    }

}
