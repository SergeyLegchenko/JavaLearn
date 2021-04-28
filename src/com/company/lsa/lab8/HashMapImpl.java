package com.company.lsa.lab8;

public class HashMapImpl<K, V> {
    // не понятно зачем    private float loadFactor = 0.75f;
    private final int capacity = 100;
    @SuppressWarnings("unchecked")
    private final Entry<K, V>[] table = new Entry[capacity];
    private int size = 0;

    private int hashing(int hashCode) {
        int location = hashCode % (capacity/2) + capacity/2;
//        System.out.println("Location:" + location);
        return location;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(K key) {
        if (key == null && table[0].getKey() == null) {
            return true;
        }
        if (key==null) return false;
        //todo Нужно обработать случай NullPointerException
        //поставил проверку на null выше
        int location = hashing(key.hashCode());
        Entry<K, V> e = table[location];
        return e != null && e.getKey() == key;
    }

    public boolean containsValue(V value) {
        for (Entry<K, V> entry : table) {
            if (entry != null && entry.getVal() == value) {
                return true;
            }
        }
        return false;
    }

    public V get(K key) {
        V ret = null;
        if (key == null) {
            Entry<K, V> e = table[0];
            if (e != null) {
                return e.getVal();
            }
        } else {
            int location = hashing(key.hashCode());
            Entry<K, V> e = table[location];
            if (e != null && e.getKey() == key) {
                return e.getVal();
            }
        }
        //всегда null
        return ret;
    }

    public V put(K key, V val) {
        V ret = null;
        if (key == null) {
            ret = putForNullKey(val);
            return ret;
        } else {
            int location = hashing(key.hashCode());
            if (location >= capacity) {
                System.out.println("Rehashing required");
                return null;
            }
            Entry<K, V> e = table[location];
            if (e != null && e.getKey() == key) {
                ret = e.getVal();
            } else {
                Entry<K, V> eNew = new Entry<>();
                eNew.setKey(key);
                eNew.setVal(val);
                table[location] = eNew;
                size++;
            }
        }
        return ret;
    }

    private V putForNullKey(V val) {
        Entry<K, V> e;
        e = table[0];
        V ret = null;
        if (e != null && e.getKey() == null) {
            ret = e.getVal();
            e.setVal(val);
            return ret;
        } else {
            Entry<K, V> eNew = new Entry<>();
            eNew.setKey(null);
            eNew.setVal(val);
            table[0] = eNew;
            size++;
        }
        //всегда null
        return ret;
    }

    public Object remove(Object key) {
        int location = hashing(key.hashCode());
        Object ret = null;
        if (table[location].getKey() == key)
            table[location]=null;
//            System.arraycopy(table, location + 1, table, location, table.length - location-1);
        //всегда null
        return ret;
    }

    @Override
    public String toString() {
        String s="[";
        for (Entry<K, V> e : table)
            if (e!=null)
                s+= " Loc:"+hashing(e.key.hashCode())+":"+e.toString();
        s+="]";
        return s;
    }

}
