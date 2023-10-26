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
        LinkedListNode<T> current = getNode(index - 1);
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    @Override
    public void delete(int pos) {
        if(pos < 0 || pos >= size){
            throw new IndexOutOfBoundsException();
        }
        LinkedListNode<T> current = getNode(pos - 1);
        current.next = current.next.next;
        size--;
    }

    @Override
    public T get(int pos) {
        if(pos < 0 || pos >= size){
            throw new IndexOutOfBoundsException();
        }
        LinkedListNode<T> node = getNode(pos);
        return node.value;
    }

    @Override
    public void removeFirst() {
        if(isEmpty()){
            throw new IllegalStateException("Can't remove from an empty list");
        }
        head.next = head.next.next;
        size--;
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

//    @Override
//    public void removeChild(T value) {
//        LinkedListNode<T> current = head.next;
//
//        while (current.next != null) {
//            if (current.next.value.equals(value)) {
//                current.next = current.next.next;
//                size--;
//                return;
//            }
//            current = current.next;
//        }
//    }

    public boolean isEmpty(){
        return size == 0;
    }

    private LinkedListNode<T> getNode(int pos){
        LinkedListNode<T> current = head.next;
        for(int i = 0; i < pos; i++){
            current = current.next;
        }
        return current;
    }
}
