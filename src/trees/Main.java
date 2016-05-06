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
//        for (int i = 0; i < NUMBER_OF_NODES; i++) {
//            Random random = new Random();
//            int number = (int) (Math.random() * (MAX-MIN) + MIN);
//            tree.add(number);
//        }
        tree.add(3);
        tree.add(1);
        tree.add(2);
        tree.add(9);
        tree.add(10);
        tree.add(5);
        tree.add(4);
        tree.add(7);
        tree.add(6);
        tree.add(8);
        tree.delete(4);
        tree.delete(5);
        tree.delete(3);
        tree.delete(6);

        System.out.println(tree);
        System.out.println(tree.size());
    }
}
