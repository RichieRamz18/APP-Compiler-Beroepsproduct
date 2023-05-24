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
        queue.insert(queue.getSize(), value);
    }

    @Override
    public T dequeue() {
       T temporary= queue.getFirst();
       queue.removeFirst();

       return temporary;
    }

    @Override
    public T peek() {
        return queue.getFirst();
    }

    @Override
    public int getSize() {
        return queue.getSize();
    }
}
