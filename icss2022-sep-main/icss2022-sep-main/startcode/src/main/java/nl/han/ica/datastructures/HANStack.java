package nl.han.ica.datastructures;

import java.util.ArrayList;
import java.util.List;

public class HANStack<T> implements IHANStack<T>{
    private HANLinkedList<T> stack;

    public HANStack (){
        stack = new HANLinkedList();
    }
    @Override
    public void push(T value) {
        stack.addFirst(value);
    }

    @Override
    public T pop() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }
}
