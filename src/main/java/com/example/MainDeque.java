package com.example;

import java.util.*;

public class MainDeque {

    public static void main(String [] args){

        Deque<Character> dequeCharacter = new ArrayDeque<>();

        Deque<Float> dequeFloat = new LinkedList<>();

        Queue<String> colaArregloCircular = new ArrayDeque<>();

        Queue<String> colaNodos = new LinkedList<>();

        dequeCharacter.addFirst('H');
        dequeCharacter.addLast('I');
        dequeCharacter.addLast('L');
        dequeCharacter.addLast('E');
        dequeCharacter.addLast('R');

        System.out.println(dequeCharacter);

        while ( !dequeCharacter.isEmpty() ){

            System.out.println(dequeCharacter.removeFirst());

        }
        
    }

}
