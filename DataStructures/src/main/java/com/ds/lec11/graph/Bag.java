package com.ds.lec11.graph;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 图的邻接数组泛型类
 */
public class Bag<Item> implements Iterable<Item> {

    private Node<Item> first;
    private int n; //number of elements in bag


    //helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Bag() {
        this.first = null;
        this.n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void add(Item item) {
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<>(first);
    }

    private class ListIterator<Item> implements Iterator<Item> {

        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Bag<String> bag = new Bag<>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String item = scanner.next();
            if ("END".equalsIgnoreCase(item))
                break;
            bag.add(item);
        }
        System.out.println("size of bag == " + bag.size());
        for (String s : bag) {
            System.out.println(s);
        }
    }
}
