public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int len;
    private static int limit = 16;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        len = 8;
        nextFirst = 0;
        nextLast = 1;
    }

    private void resizeExpand() {
        T[] expand = (T[]) new Object[len * 2];
        System.arraycopy(items, (nextFirst + 1) % len, expand, 0, len - nextFirst - 1);
        System.arraycopy(items, 0, expand, len - nextFirst - 1, nextFirst + 1);
        items = expand;
        nextLast = len;
        len *= 2;
        nextFirst = len - 1;
    }

    public void addFirst(T item) {
        if (size == len) {
            resizeExpand();
        }
        items[nextFirst] = item;
        nextFirst -= 1;
        if (nextFirst < 0) {
            nextFirst += len;
        }
        size += 1;
    }

    public void addLast(T item) {
        if (size == len) {
            resizeExpand();
        }
        items[nextLast] = item;
        nextLast += 1;
        if (nextLast > len  - 1) {
            nextLast -= len;
        }
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if ((nextFirst + 1) % len < (nextLast - 1 + len) % len) {
            for (int i = (nextFirst + 1) % len; i < nextLast; i++) {
                System.out.print(items[i] + " ");
            }
        } else {
            for (int i = (nextFirst + 1) % len; i < len; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < nextLast; i++) {
                System.out.print(items[i] + " ");
            }
        }
        System.out.println();
    }

    private void resizeReduce() {
        T[] reduce = (T[]) new Object[len / 2];
        if ((nextFirst + 1) % len < (nextLast - 1 + len) % len) {
            System.arraycopy(items, (nextFirst + 1) % len, reduce, 0, size);
        } else {
            System.arraycopy(items, (nextFirst + 1) % len, reduce, 0, len - nextFirst - 1);
            System.arraycopy(items, 0, reduce, len - nextFirst - 1, nextLast - 1);
        }
        items = reduce;
        nextLast = size;
        len = len / 2;
        nextFirst = len - 1;
        if (len >= limit && len > 4 * size) {
            resizeReduce();
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T remove = items[(nextFirst + 1) % len];
        items[(nextFirst + 1) % len] = null;
        nextFirst += 1;
        if (nextFirst > len - 1) {
            nextFirst -= len;
        }
        size -= 1;
        if (len >= limit && len > 4 * size) {
            resizeReduce();
        }
        return remove;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T remove = items[(nextLast - 1 + len) % len];
        items[(nextLast - 1 + len) % len] = null;
        nextLast -= 1;
        if (nextLast < 0) {
            nextLast += len;
        }
        size -= 1;
        if (len >= limit && len > 4 * size) {
            resizeReduce();
        }
        return remove;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[(nextFirst + index + 1) % len];
    }

}
