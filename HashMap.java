// HashMap Implementation using Linear Probing Collision Resolution Technique. 

import java.util.*;
import java.io.*;

class Pair<k extends Comparable<k>,v> {
    private k key;
    private v value;
    public Pair() {}
    public Pair(k key,v value) {
        this.key = key;
        this.value = value;
    }
    public k getKey() {return this.key;}
    public v getValue() {return this.value;}
}

public class HashMap<k extends Comparable<k>,v>{
    private Pair<k,v> HashTable[];
    private int currSize;
    private int maxSize;
    private boolean bit[];
    public HashMap() {
        this.currSize = 0;
        this.maxSize = 10;
        HashTable = new Pair[maxSize];
        bit = new boolean[maxSize];
        for(int i=0;i<maxSize;i++) {
            HashTable[i] = null;
            bit[i] = false;
        }
    }
    public HashMap(int capacity) {
        this.currSize = 0;
        this.maxSize = capacity;
        HashTable = new Pair[maxSize];
        bit = new boolean[maxSize];
        for(int i=0;i<maxSize;i++) {
            HashTable[i] = null;
            bit[i] = false;
        }
    }
    private int Hash(k key) {
        return key.hashCode()%maxSize;
    }
    private int linearProbing(k key,int home) {
        int idx = home;
        do {
            if(HashTable[idx]==null)
                return idx;
            idx = (idx+1)%maxSize;
        }while(idx!=home);
        return -1;
    }
    public boolean put(k key,v value) {
        int home = Hash(key);
        int index = linearProbing(key,home);
        if(index!=-1) {
            HashTable[index] = new Pair<>(key,value);
            bit[index] = true;
            ++currSize;
            return true;
        } else {
            return false;
        }
    }
    public v get(k key) {
        int home = Hash(key);
        int idx = home;
        do {
            if(HashTable[idx].getKey()==key)
                return HashTable[idx].getValue();
            idx = (idx+1)%maxSize;
        }while(idx!=home && bit[idx]!=false);
        return null;
    }
    public boolean containsKey(k key) {
        int home = Hash(key);
        int idx = home;
        do {
            if(HashTable[idx].getKey()==key)
                return true;
            idx = (idx+1)%maxSize;
        }while(idx!=home && bit[idx]!=false);
        return false;
    }
    public int size() {
        return this.currSize;
    }
    public boolean empty() {
        return this.currSize==0;
    }
}
