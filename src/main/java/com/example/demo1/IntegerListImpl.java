package com.example.demo1;
import java.util.Arrays;
import java.util.Objects;

public class IntegerListImpl implements IntegerList {
    private Integer[] strings;
    private int stringSize;

    public IntegerListImpl(int size) {
        strings = new Integer[size];
        stringSize = 0;
    }

    public IntegerListImpl(Integer... args) {
        strings = new Integer [args.length];
        System.arraycopy(args, 0,strings, 0, args.length);
        stringSize = strings.length;
    }
    @Override
    public Integer add(Integer item) {
        if (stringSize == strings.length) {
            resize(stringSize + 1);
        }
        strings[stringSize] = item;
        stringSize++;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkBound(index);
        if (stringSize == strings.length) {
            resize(stringSize + 1);
        }
        for (int i = stringSize; i > index; i--) {
            strings[i] = strings[i-1];
        }
        strings[index] = item;
        stringSize++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkBound(index);
        strings[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException();
        }
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        checkBound(index);
        Integer result = strings[index];
        for (int i = index + 1; i < stringSize; i++) {
            strings[i-1] = strings[i];
        }
        stringSize--;
        return result;
    }

    @Override
    public boolean contains(Integer item) {
        sort();
        return search(item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < stringSize; i++) {
            if (strings[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = stringSize - 1; i >= 0; i--) {
            if (strings[i].equals(item))
                return i;
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkBound(index);
        return strings[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new InvalidArgumentException();
        }
        if (stringSize != otherList.size()) {
            return false;
        }
        for (int i = 0; i < stringSize; i++) {
            if (!Objects.equals(strings[i], otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return stringSize;
    }

    @Override
    public boolean isEmpty() {
        return stringSize ==0;
    }

    @Override
    public void clear() {
        Arrays.fill(strings, 0, stringSize, null);
        stringSize = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] arr = new Integer[stringSize];
        System.arraycopy(strings, 0, arr, 0, stringSize);
        return arr;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < stringSize; i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(strings[i]);
        }
        result.append("]");
        return result.toString();
    }

    private  void checkBound(int index) {
        if (index < 0 || index >= stringSize) {
            throw new StringListIndexOutBoundsException();
        }
    }

    private void resize(int newSize) {
        int size = stringSize * 2;
        size = Math.max(size, newSize);
        Integer[] newStrings = new Integer[size];
        System.arraycopy(strings, 0, newStrings, 0, stringSize);
        strings = newStrings;
    }

    private void sort() {
        int in, out;
        for (out = 1; out < stringSize; out++) {
            Integer temp = strings[out];
            in = out;
            while (in > 0 && strings[in - 1] >= temp) {
                strings[in] = strings[in - 1];
                in--;
            }
            strings[in] = temp;
        }
    }

    private boolean search(Integer item) {
        int lo = 0;
        int hi = stringSize -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) /2;
            if (strings[mid].compareTo(item) == 0) {
                return true;
            } else if (strings[mid].compareTo(item) < 0) {
                lo = mid + 1;
            } else {
                hi = mid -1;
            }
        }
        return false;
    }
}
