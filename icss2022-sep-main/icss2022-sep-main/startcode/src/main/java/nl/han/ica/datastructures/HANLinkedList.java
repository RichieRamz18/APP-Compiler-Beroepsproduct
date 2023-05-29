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
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        if(index == 0){
            addFirst(value);
            return;
        }
        LinkedListNode<T> newNode = new LinkedListNode<>(value);
        LinkedListNode<T> current;
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
        if(isEmpty()){
            throw new IllegalStateException("Can't remove from an empty list");
        }
    }

    @Override
    public T getFirst() {
        if(isEmpty()){
            throw new IllegalArgumentException("List is empty");
        }
        return head.next.value;
    }

    @Override
    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }
}
