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
        System.out.println("Время добавления элемента " + node + " = " + time + " мс");
    }

    @Override
    public Node find(int val) {
        return find(root, val, 0);
    }

    @Override
    public Node maximum() {
        if (root != null) {
            return max(root);
        } else {
            return null;
        }
    }

    @Override
    public Node minimum() {
        if (root != null) {
            return min(root);
        } else {
            return null;
        }
    }

    @Override
    public void delete(int val) {
        Node toDelete = find(val);
        if (toDelete != null) {
            if (toDelete.getRight() == null && toDelete.getLeft() == null) {
                if (toDelete.getParent() == null) {
                    root = null;
                } else {
                    if (toDelete.getParent().getValue() < toDelete.getValue()) {
                        toDelete.getParent().setRight(null);
                    }
                    if (toDelete.getParent().getValue() > toDelete.getValue()) {
                        toDelete.getParent().setLeft(null);
                    }
                }
            }

            if (toDelete.getLeft() != null && toDelete.getRight() == null) {
                if (toDelete.getParent().getValue() < toDelete.getValue()){
                    toDelete.getParent().setRight(toDelete.getLeft());
                    toDelete.getLeft().setParent(toDelete.getParent());
                }
                if (toDelete.getParent().getValue() > toDelete.getValue()){
                    toDelete.getParent().setLeft(toDelete.getLeft());
                    toDelete.getLeft().setParent(toDelete.getParent());
                }
            }

            if (toDelete.getLeft() == null && toDelete.getRight() != null) {
                if (toDelete.getParent().getValue() < toDelete.getValue()){
                    toDelete.getParent().setRight(toDelete.getRight());
                    toDelete.getRight().setParent(toDelete.getParent());
                }
                if (toDelete.getParent().getValue() > toDelete.getValue()){
                    toDelete.getParent().setLeft(toDelete.getRight());
                    toDelete.getRight().setParent(toDelete.getParent());
                }
            }

            if (toDelete.getLeft() != null && toDelete.getRight() != null){
                Node leftestInRight = leftestInRight(root.getRight());
                if (leftestInRight.getRight() == null){
                    leftestInRight.getParent().setLeft(null);
                    utilToDeleteMethod(leftestInRight, toDelete);
                } else {
                    leftestInRight.getRight().setParent(leftestInRight.getParent());
                    leftestInRight.getParent().setLeft(leftestInRight.getRight());
                    utilToDeleteMethod(leftestInRight, toDelete);
                }
            }
            size--;
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

    private Node find(Node currentNode, int val, int lvl){
        if (currentNode == null){
            return null;
        } else if (currentNode.getValue() == val) {
            currentNode.setLvl(lvl);
            return currentNode;
        } else if (val > currentNode.getValue()){
            return find(currentNode.getRight(), val, lvl+1);
        } else {
            return find(currentNode.getLeft(), val, lvl+1);
        }
    }

    private Node max(Node currentNode){
        if (currentNode.getRight() == null){
            return currentNode;
        } else {
            return max(currentNode.getRight());
        }
    }

    private Node min(Node currentNode){
        if (currentNode.getLeft() == null){
            return currentNode;
        } else {
            return min(currentNode.getLeft());
        }
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

    private Node leftestInRight(Node currentNode){
        if (currentNode.getLeft() == null){
            return currentNode;
        } else {
            return leftestInRight(currentNode.getLeft());
        }
    }

    private void utilToDeleteMethod(Node leftestInRight, Node toDelete){
        leftestInRight.setLeft(toDelete.getLeft());
        leftestInRight.setRight(toDelete.getRight());
        leftestInRight.getLeft().setParent(leftestInRight);
        leftestInRight.getRight().setParent(leftestInRight);
        leftestInRight.setParent(null);
        if (toDelete.getParent() != null) {
            leftestInRight.setParent(toDelete.getParent());
            if (toDelete.getParent().getValue() > toDelete.getValue()){
                toDelete.getParent().setLeft(leftestInRight);
            } else {
                toDelete.getParent().setRight(leftestInRight);
            }
        } else {
            root = leftestInRight;
        }
    }
}
