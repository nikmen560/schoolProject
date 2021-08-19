public interface Showable<T> {
    void show();
    T get(int id);
    boolean add();
    boolean update(int id);
    boolean remove(int id);
}
