package com.company.lsa.lab8;

class Entry<K, V> {
    K key;
    V val;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getVal() {
        return val;
    }

    public void setVal(V val) {
        this.val = val;
    }

    @Override
    public int hashCode() {
        return key != null ? 13 * 11 + key.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Entry<?, ?> e = (Entry<?, ?>) o;
        return this.key == e.key;
    }
}
