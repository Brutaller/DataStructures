package trees;

import java.util.Random;

/**
 * Created by Azat Zaripov on 06.05.16.
 */
public class Main {

    private static final int NUMBER_OF_NODES = 5000;
    private static final int MAX = 500;
    private static final int MIN = -500;

    public static void main(String[] args) {
        TreeInterface tree = new BinaryTree();
        for (int i = 0; i < NUMBER_OF_NODES; i++) {
            Random random = new Random();
            int number =(int) (Math.random() * (MAX-MIN) + MIN);
            tree.add(number);
        }
        System.out.println(tree);
        System.out.println(tree.size());
        System.out.println(tree.find(19));
    }
}
