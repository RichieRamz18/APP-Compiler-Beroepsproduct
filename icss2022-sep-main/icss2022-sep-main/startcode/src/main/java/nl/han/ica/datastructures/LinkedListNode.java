package nl.han.ica.datastructures;

public class LinkedListNode<T> {
    T value;
    LinkedListNode<T> next;

    LinkedListNode(T value){
        this.value = value;
        next = null;
    }

}
