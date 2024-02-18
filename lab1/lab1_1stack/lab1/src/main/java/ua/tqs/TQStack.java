package ua.tqs;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TQStack<T> {
    

    private LinkedList<T> stack = new LinkedList<T>();
    private Integer bound = null;

    public TQStack() {
        stack = new LinkedList<T>();
    }

    public TQStack(int bound) {
        this.bound = bound;
        stack = new LinkedList<T>();
    }


    public void pop(){
        if (stack.size() == 0){
            throw new NoSuchElementException();
        }
        else{
            stack.remove(stack.size()-1);
        }
    }

    public void push(T n){
        stack.add(n);
        if (bound != null && stack.size() > bound){
            throw new IllegalStateException();
        }
    }

    public T peek(){
        if (stack.size() == 0){
            throw new NoSuchElementException();
        }
        else{
            return stack.get(stack.size()-1);
        }
    }

    public int size(TQStack<T> array){
        return array.stack.size();
        
    }

    public boolean isEmpty(TQStack<T> array){
        if (array.stack.size() == 0){
            return true;
        }
        return false;
    }

    public boolean contains(T n){
        if (stack.contains(n) && stack.indexOf(n) == stack.size()-1){
            return true;
        }
        else{
            return false;
        }
    }
    
}
