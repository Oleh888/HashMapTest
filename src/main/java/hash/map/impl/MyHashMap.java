package hash.map.impl;

import java.util.Objects;

public class MyHashMap implements MyMap<Integer, Long> {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75f;
    private Node<Integer, Long>[] bucket;
    private int size;

    public MyHashMap() {
        bucket = new Node[DEFAULT_INITIAL_CAPACITY];
    }

    @Override
    public void put(Integer key, Long value) {
        if ((double) size / bucket.length >= DEFAULT_LOAD_FACTOR) {
            resize();
        }
        Node<Integer, Long> newNode = new Node<>(key, value);
        int index = hash(key);
        if (bucket[index] == null) {
            bucket[index] = newNode;
            size++;
        } else {
            Node<Integer, Long> current = getCurrent(bucket[index], newNode);
            if (Objects.equals(current.key, key)) {
                current.value = value;
            } else {
                current.next = newNode;
                size++;
            }
        }
    }

    @Override
    public Long getValue(Integer key) {
        Node<Integer, Long> current = bucket[hash(key)];
        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public int getSize() {
        return size;
    }

    private int hash(Integer key) {
        return (key == null) ? 0 : Math.abs(key.hashCode()) % bucket.length;
    }

    private Node<Integer, Long> getCurrent(
            Node<Integer, Long> startNode, Node<Integer, Long> newNode) {
        Node<Integer, Long> current = startNode;
        while (current.next != null) {
            if (Objects.equals(current.key, newNode.key)) {
                return current;
            }
            current = current.next;
        }
        return current;
    }

    private void resize() {
        Node<Integer, Long>[] oldBucket = bucket;
        bucket = new Node[bucket.length * 2];
        size = 0;
        for (Node<Integer, Long> element : oldBucket) {
            while (element != null) {
                put(element.key, element.value);
                element = element.next;
            }
        }
    }

    private static class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
