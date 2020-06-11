package hash.map.impl;

import java.util.Objects;

public class MyHashMap implements MyMap<Integer, Long> {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75f;
    private Node[] bucket;
    private int size;

    public MyHashMap() {
        bucket = new Node[DEFAULT_INITIAL_CAPACITY];
    }

    @Override
    public void put(Integer key, Long value) {
        if ((double) size / bucket.length >= DEFAULT_LOAD_FACTOR) {
            resize();
        }
        Node newNode = new Node(key, value);
        int index = hash(key);
        if (bucket[index] == null) {
            bucket[index] = newNode;
            size++;
        } else if (Objects.equals(bucket[index].key, key) || bucket[index].key == null) {
            bucket[index] = newNode;
        } else {
            while (bucket[index] != null && !(Objects.equals(bucket[index].key, key)
                    && Objects.equals(bucket[index].value, value))) {
                index++;
                if (index > size) {
                    resize();
                    index = hash(key);
                }
            }
            bucket[index] = newNode;
            size++;
        }
    }

    @Override
    public Long getValue(Integer key) {
        int index = hash(key);
        while (bucket[index] != null || index <= size) {
            if (Objects.equals(bucket[index].key, key)) {
                return bucket[index].value;
            }
            index++;
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

    private void resize() {
        Node[] oldBucket = bucket;
        bucket = new Node[bucket.length * 2];;
        size = 0;
        for (Node element : oldBucket) {
            if (element != null) {
                put(element.key, element.value);
            }
        }
    }

    private static class Node {
        private Integer key;
        private Long value;

        Node(Integer key, Long value) {
            this.key = key;
            this.value = value;
        }
    }
}
