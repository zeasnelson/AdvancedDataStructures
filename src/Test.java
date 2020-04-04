import java.util.Random;

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

        //Every function was implemented such that comparisons, data movement, nodes traversed,
        //start time, end time, etc... are tracked

        //set how large the test data will be
        System.out.println("Filling up trees");
        testTrees(10);
        testTrees(100);
        testTrees(1000);
        testTreesWithFile();



        //Random Numbers and sequential values
        HashTableWithSeparateChaining<Integer,Integer> sequentialNumbers1000 = new HashTableWithSeparateChaining();
        HashTableWithSeparateChaining<Integer,Integer> sequentialNumbers10000 = new HashTableWithSeparateChaining();

        //random
        HashTableWithSeparateChaining<Integer,Integer> Random1000 = new HashTableWithSeparateChaining();
        HashTableWithSeparateChaining<Integer,Integer> Random10000 = new HashTableWithSeparateChaining();


        //Reading from a File
        HashTableWithSeparateChaining<Integer,Character> Fileread = new HashTableWithSeparateChaining();

        Random Rand = new Random();
        //insert 1000 and 10000 and corona.txt

        int n = 1000;
        for(int i = 0; i< n; i++ ) {
            sequentialNumbers1000.insert(i,(Rand.nextInt(1000)+1));
        }
        //print out of n = 10 of the first map
        System.out.println("\n\n\n ---- HashMap with Sequential Data n = 1000 ---- ");
        System.out.println("First hashmap with n = 1,000 "+sequentialNumbers1000.insert(1000,(Rand.nextInt(1000)+1)));
        System.out.println(sequentialNumbers1000.get(999)); //search
        System.out.print( sequentialNumbers1000.remove(998)); //delete


        n = 10000;
        for(int i = 0; i< n; i++ ) {
            sequentialNumbers10000.insert(i,Rand.nextInt(10000));
        }
        System.out.println("\n\n\n ---- HashMap with Sequential Data n = 10000 ---- ");
        System.out.println("hashmap n = 10,000 "+sequentialNumbers10000.insert(10000,Rand.nextInt(10000)+1));
        System.out.println(sequentialNumbers10000.get(9999)); //search
        System.out.print( sequentialNumbers10000.remove(9998));//delete

        ///////-------------------------------->Random 1000, Random 10 000

        n = 1000;
        int SaveMe = 0;
        for(int i = 0; i< n; i++ ) {
            Random1000.insert(i,SaveMe = Rand.nextInt(1000));
        }
        System.out.println("\n\n\n ---- HashMap with Random Data n = 1000 ---- ");
        System.out.println("second hashmap with n = 1,000 "+Random1000.insert(1000,(Rand.nextInt(1000)+1)));
        System.out.println(Random1000.get(SaveMe)); //search
        System.out.print( Random1000.remove(SaveMe));//delete


        n = 10000;
        for(int i = 0; i < n; i++ ) {
            Random10000.insert(i,(SaveMe = Rand.nextInt(10000)+1));
        }
        System.out.println("\n\n\n ---- HashMap with Random Data n = 10000 ---- ");
        System.out.println("second hashmap with n = 10,000 "+Random10000.insert(10000,(Rand.nextInt(10000)+1)));
        System.out.println(Random10000.get(SaveMe)); //search
        System.out.print( Random10000.remove(SaveMe));//delete

        //Reading from a file
        n=0;
        //Read text file


        int randomChar = 0;
        FileIO.setIo("coronavirus.txt");
        int c = FileIO.getNextChar();
        while( c != -1 ){
            c = FileIO.getNextChar();
            Fileread.insert(n++,(char)c);
            randomChar = n;
        }//end while

        //place one more in the map to see comparions
        System.out.println("\n\n\n ---- HashMap with coronavirus Data n = 11186 ---- ");
        System.out.println("Total number of characters in the file are:" +(n-1)+" "+Fileread.insert(n++, '~'));
        System.out.println("file read" +Fileread.get(randomChar));
        System.out.println(Fileread.remove(randomChar));
    }

}
