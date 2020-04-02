import java.io.File;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Random;

public class Test {

    /**
     * Generate n random numbers, can be duplicate numbers
     * @param n The range of the random numbers 0 - n
     * @return An array that contains all random numbers of size n
     */
    public static int [] randomNumbers(int n){
        Random rand = new Random();
        int [] numbers = new int[n];
        for( int i = 0; i < n; i++ ){
            numbers[i] = rand.nextInt(n);
        }
        return numbers;
    }

    /**
     * Generate n random unique numbers - no duplicates
     * @param n The range of the random numbers 0 - n
     * @return An array containing all numbers generated of size n
     */
    public static int[] uniqueRandomNumbers(int n){
        Random rand = new Random();
        int [] repeatedNum = new int[n];
        int [] uniqueNum = new int[n];

        for( int i = 0; i < n; i++ ){
            repeatedNum[i] = 0;
        }

        for(int i = 0; i < n; ){
            int ranNum = rand.nextInt(n);
            if( repeatedNum[ranNum] == 0 ){
                uniqueNum[i] = ranNum;
                repeatedNum[ranNum]++;
                i++;
            }
        }
        return uniqueNum;
    }

    public static void main(String[] args) {


        FileIO.setIo("coronavirus.txt");
        int c = FileIO.getNextChar();
        while( c != -1 ){
            System.out.print((char)c);
            c = FileIO.getNextChar();
        }

        /*
        //set how large the test data will be
        //I wouldn't exceed 1000
        int n = 10000;

        System.out.println("Generating data");
        int [] notUniqueNum = randomNumbers(n);
        int [] uniqueNum = uniqueRandomNumbers(n);

        AVL<Integer> avlUniqueNum = new AVL<>();
        AVL<Integer> avlRandomNum = new AVL<>();
        BST<Integer> bstUniqueNum = new BST<>();
        BST<Integer> bstRandom    = new BST<>();

        System.out.println("Filling up trees");
        for( int i = 0; i < n; i++ ){
            avlRandomNum.insert(notUniqueNum[i]);
            avlUniqueNum.insert(uniqueNum[i]);
            bstRandom.insert(notUniqueNum[i]);
            bstUniqueNum.insert(uniqueNum[i]);
        }

        //To keep track of records
        ArrayList<Tracker> records = new ArrayList<>();

        System.out.println("That's it baby\n\n");

        //Every function was implemented such that comparisons, data movement, nodes traversed,
        //start time, end time, etc... are tracked

        //Get the tracking info from the avl tree with random numbers(not unique numbers)
        System.out.println("AVL tree with random numbers");
        records.add(avlRandomNum.insert(n+1));
        records.add(avlRandomNum.remove(n+1));
        records.add(avlRandomNum.uniqueNodeCount());
        records.add(avlRandomNum.getNodeCount());
        records.add(avlRandomNum.contains(n));
        records.add(avlRandomNum.get(n));
        records.add(avlRandomNum.getHeight());
        records.add(avlRandomNum.getMin());
        records.add(avlRandomNum.getMax());
        records.add(avlRandomNum.inOrder());
        records.add(avlRandomNum.preOrder());
        records.add(avlRandomNum.postOrder());
        //print all records
        for( Tracker record : records ){
            System.out.println(record);
        }

        //Get the tracking info from the AVL tree with unique random numbers
        System.out.println("\n\nAVL tree with unique random numbers");
        records = new ArrayList<>();//clear the previous records
        records.add(avlUniqueNum.insert(n+1));
        records.add(avlUniqueNum.remove(n+1));
        records.add(avlUniqueNum.uniqueNodeCount());
        records.add(avlUniqueNum.getNodeCount());
        records.add(avlUniqueNum.contains(n));
        records.add(avlUniqueNum.get(n));
        records.add(avlUniqueNum.getHeight());
        records.add(avlUniqueNum.getMin());
        records.add(avlUniqueNum.getMax());
        records.add(avlUniqueNum.inOrder());
        records.add(avlUniqueNum.preOrder());
        records.add(avlUniqueNum.postOrder());
        //print all records
        for( Tracker record : records ){
            System.out.println(record);
        }

        //Get the tracking info from the BST with unique random numbers(not unique numbers)
        System.out.println("\n\nBST with random numbers");
        records = new ArrayList<>();//clear the previous records
        records.add(bstRandom.insert(n+1));
        records.add(bstRandom.remove(n+1));
        records.add(bstRandom.uniqueNodeCount());
        records.add(bstRandom.getNodeCount());
        records.add(bstRandom.contains(n));
        records.add(bstRandom.get(n));
        records.add(bstRandom.getHeight());
        records.add(bstRandom.getMin());
        records.add(bstRandom.getMax());
        records.add(bstRandom.inOrder());
        records.add(bstRandom.preOrder());
        records.add(bstRandom.postOrder());
        //print all records
        for( Tracker record : records ){
            System.out.println(record);
        }

        //Get the tracking info from the BST with unique random numbers
        System.out.println("\n\nBST with unique random numbers");
        records = new ArrayList<>();//clear the previous records
        records.add(bstUniqueNum.insert(n+1));
        records.add(bstUniqueNum.remove(n+1));
        records.add(bstUniqueNum.uniqueNodeCount());
        records.add(bstUniqueNum.getNodeCount());
        records.add(bstUniqueNum.contains(n));
        records.add(bstUniqueNum.get(n));
        records.add(bstUniqueNum.getHeight());
        records.add(bstUniqueNum.getMin());
        records.add(bstUniqueNum.getMax());
        records.add(bstUniqueNum.inOrder());
        records.add(bstUniqueNum.preOrder());
        records.add(bstUniqueNum.postOrder());
        //print all records
        for( Tracker record : records ){
            System.out.println(record);
        }
        */
    }

}
