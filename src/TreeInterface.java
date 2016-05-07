/**
 * Created by Azat Zaripov on 06.05.16.
 */
public interface TreeInterface {
    void add(int val);
    Node find(int val);
    void delete(int val);
    int size();
    Node minimum();
    Node maximum();
}
