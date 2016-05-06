package trees;

import treeNodes.Node;

/**
 * Created by Azat Zaripov on 06.05.16.
 */
public class BinaryTree implements TreeInterface {

    private Node root;
    private int size;

    public BinaryTree() {this.size = 0;}

    @Override
    public int size(){
        return this.size;
    }

    @Override
    public void add(int node){
        long start = System.currentTimeMillis();
        if (root == null){
            Node node1 = new Node();
            node1.setValue(node);
            root = node1;
            size++;
        } else {
            add(root, node);
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("Время добавления элемента " + node + " = " + time);
    }

    private void add(Node currentNode, int nodeVal){
        if (currentNode.getValue() > nodeVal){
            if (currentNode.getLeft() == null){
                Node node = new Node();
                node.setParent(currentNode);
                node.setValue(nodeVal);
                currentNode.setLeft(node);
                size++;
            } else {
                add(currentNode.getLeft(), nodeVal);
            }
        } else if (currentNode.getValue() < nodeVal){
            if (currentNode.getRight() == null){
                Node node = new Node();
                node.setParent(currentNode);
                node.setValue(nodeVal);
                currentNode.setRight(node);
                size++;
            } else {
                add(currentNode.getRight(), nodeVal);
            }
        }
    }

    @Override
    public Node find(int val) {
        return find(root, val);
    }

    private Node find(Node currentNode, int val){
        if (currentNode == null){
            return null;
        } else if (currentNode.getValue() == val) {
            return currentNode;
        } else if (val > currentNode.getValue()){
            return find(currentNode.getRight(), val);
        } else {
            return find(currentNode.getLeft(), val);
        }
    }

    @Override
    public void delete(int val) {

    }

    private void getNode(StringBuilder sb, Node currentNode, int lvl){
        sb.append(currentNode.getValue());
        sb.append("[");
        sb.append(lvl + 1);
        sb.append("]");
        sb.append(" ");
        if (currentNode.getLeft() != null) {
            getNode(sb, currentNode.getLeft(), lvl + 1);
        }
        if (currentNode.getRight() != null) {
            getNode(sb, currentNode.getRight(), lvl + 1);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (root != null) {
            sb.append(root.getValue());
            sb.append("[");
            sb.append(0);
            sb.append("]");
            sb.append(" ");
        } else
            return "";
        if (root.getLeft() != null){
            getNode(sb, root.getLeft(), 0);
        }
        if (root.getRight() != null){
            getNode(sb, root.getRight(), 0);
        }
        return new String(sb);
    }
}
