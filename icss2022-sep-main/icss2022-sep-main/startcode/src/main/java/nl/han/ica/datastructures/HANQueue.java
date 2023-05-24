package nl.han.ica.datastructures;

public class HANQueue<T> implements IHANQueue<T> {
    private HANLinkedList<T> queue;

    public HANQueue(){
        queue = new HANLinkedList<>();
    }
    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public boolean isEmpty() {
        return queue.getSize() == 0;
    }

    @Override
    public void enqueue(T value) {
        
    }

    @Override
    public T dequeue() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
