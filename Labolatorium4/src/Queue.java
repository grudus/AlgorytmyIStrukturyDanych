public interface Queue<T> {
    void insert(T element);

    T remove();

    boolean isEmpty();

    boolean isFull();

}
