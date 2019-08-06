package top20;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

public class Sol146 extends LinkedHashMap<Integer, Integer> {
    /*
    146. LRU Cache
    Design and implement a data structure for Least Recently Used Cache (LRU) cache.
    It should support the following operations: get and put.
    get(key)--get the value (will always be positive) of the key if the key exists in the cache,
    otherwise return -1
    put(key, value) --set or insert the value if the key is not already present.
    When the cache reached its capacity, it should invalidate the least recently used item before
    inserting a new item.
    The cache is initialized with a positive capacity.
    Follow up:
    Could you do both operations in O(1) time complexity?
     */

    /*
    Approach 1: Ordered dictionary
    Intuition:
    We are asked to implement the structure which provides the following operations in Q(1) time.
    1. get the key/ check if the key exists.
    2. put the key
    3. delete the first added key (the least used)
    The first two operations in O(1) time are provided by the standard hashmap.
    The last one is provided by linked list.
    There is a structure called ordered dictionary, it combines behind both hashmap and linkedlist.
    In Java it is called LinkedHashMap.
     */

    private int capacity;

    public Sol146(int capacity){
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key){
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value){
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest){
        return size() > capacity;
    }

    /*
    Approach 2: Hashmap + DoubleLinkedList
    Intuition:
    The problem can be solved with a hashmap that keeps track of the keys and
    its values in the double linked list.
    So it is O(1) for put and get operations.
    And allows to remove the first added node in O(1) time as well.
    One advantage of double linked list is that the node can remove itself
    without other reference. In addition, it takes constant time to add and
    remove nodes from the head or tail.
     */

    private void addNode(DLinkedNode node){
        // always add the new node right after head.
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node){
        // remove an existing node from the double linked list
        DLinkedNode prev = node.prev;
        DLinkedNode next = node.next;

        prev.next = next;
        next. prev = prev;
    }

    private void moveToHead(DLinkedNode node){
        // move certain node in between to the head
        removeNode(node);
        addNode(node);
    }

    private DLinkedNode popTail(){
        // pop the current tail
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

    private Hashtable<Integer, DLinkedNode> cache =
            new Hashtable<Integer, DLinkedNode>();
    private int size;
    private int capacityII;
    private DLinkedNode head, tail;
}

class DLinkedNode{
    int key;
    int value;
    DLinkedNode prev;
    DLinkedNode next;
}
