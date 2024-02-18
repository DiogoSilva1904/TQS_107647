package ua.tqs;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TQStackTest{
    private TQStack stack;
    private TQStack boundedStack;


    @BeforeEach
    public void setUp(){
        stack = new TQStack();
        boundedStack = new TQStack(5);
    }

    @AfterEach
    public void erase(){
        stack = null;
        boundedStack = null;
    }

    @Test
    public void onInitEmpty(){
        assertTrue(stack.isEmpty(stack));
        //assertEquals(0, stack.size(stack));
    }

    @Test
    public void onInitSize(){

        assertEquals(0, stack.size(stack));
    }

    @Test
    public void PushN(){
        stack.push("ffjjjk");
        stack.push("ffjjjk");
        assertEquals(2, stack.size(stack));
    }

    @Test
    public void PushPop(){
        stack.push("ffjjjk");
        stack.push(1);
        stack.pop();
        assertFalse(stack.contains(1));
    }

    @Test
    public void PushPeek(){
        stack.push("ffjjjk");
        stack.push(1);
        assertEquals(1, stack.peek());
        assertEquals(2, stack.size(stack));
    }


    @Test
    public void PushNPopN(){
        stack.push("aaaa");
        stack.push(1111);
        stack.pop();
        stack.pop();
        assertEquals(0, stack.size(stack));
    }

    @Test
    public void PopEmpty(){
        assertThrows(NoSuchElementException.class , () -> {stack.pop();},"Stack is empty and pop is not allowed.");

    }

    @Test
    public void PeekEmpty(){
        assertThrows(NoSuchElementException.class , () -> {stack.peek();},"Stack is empty and peek is not allowed.");
    }
    
    @Test
    public void PushBound(){
        for (int i = 0; i < 5; i++){
            boundedStack.push(i);
        }
        assertThrows(IllegalStateException.class , () -> {boundedStack.push(6);},"Stack is full and push is not allowed.");
    }
} 
