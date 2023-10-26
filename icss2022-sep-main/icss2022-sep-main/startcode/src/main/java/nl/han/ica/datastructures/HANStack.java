package nl.han.ica.datastructures;
public class HANStack<T> implements IHANStack<T>{
    private HANLinkedList<T> stack;

    public HANStack (){
        stack = new HANLinkedList<>();
    }
    @Override
    public void push(T value) {
        stack.addFirst(value);
    }

    @Override
    public T pop() {
        if(isEmpty()){
            throw new IllegalStateException("Stack is empty");
        }
        T value = stack.getFirst();
        stack.removeFirst();
        return value;
    }

    @Override
    public T peek() {
        if(isEmpty()){
            throw new IllegalStateException("Stack is empty");
        }
        return stack.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

//    @Override
//    public void removeChild(T value) {
//        stack.removeChild(value);
//    }
}
