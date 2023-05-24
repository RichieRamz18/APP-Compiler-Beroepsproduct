package nl.han.ica.datastructures;

public class HANLinkedList<T> implements IHANLinkedList<T> {
    private LinkedListNode<T> head;
    private int size;

    public HANLinkedList(){
        head = new LinkedListNode<>(null);
        size = 0;
    }


    @Override
    public void addFirst(T value) {
        LinkedListNode<T> newNode = new LinkedListNode<>(value);
        newNode.next = head.next;
        head.next = newNode;
        size++;
    }

    @Override
    public void clear() {
        head.next = null;
        size = 0;
    }

    @Override
    public void insert(int index, T value) {
        
    }

    @Override
    public void delete(int pos) {

    }

    @Override
    public T get(int pos) {
        return null;
    }

    @Override
    public void removeFirst() {

    }

    @Override
    public T getFirst() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
