public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int len;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        len = 8;
        nextFirst = 0;
        nextLast = 1;
    }

    private void resizeExpand() {
        if(size < len) {
            return;
        }
        T[] expand = (T[]) new Object[items.length];
        System.arraycopy(items, (nextFirst + 1) % len, expand, 0 , len - nextFirst - 1);
        System.arraycopy(items, 0, expand, len - nextFirst - 1 , nextFirst + 1);
        items = expand;
        nextLast = len;
        len *= 2;
        nextFirst = len - 1;
    }

    public void addFirst(T item) {
        resizeExpand();
        items[nextFirst] = item;
        nextFirst -= 1;
        if(nextFirst < 0) {
            nextFirst += len;
        }
        size += 1;
    }

    public void addLast(T item) {
        resizeExpand();
        items[nextLast] = item;
        nextLast += 1;
        if(nextLast > len-1) {
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
        for(int i = nextFirst + 1 ; i < len; i++) {
            System.out.print(items[i] + " ");
        }
        for(int i = 0 ; i < nextFirst + 1; i++) {
            System.out.print(items[i] + " ");
        }
    }

    private void resizeReduce(){
        if(len < 16 || size * 4 >= len) {
            return;
        }
        T[] reduce = (T[]) new Object[items.length / 2];
        if (nextFirst < nextLast) {
            System.arraycopy(items, nextFirst + 1, reduce, 0 , size);
        }
        else {
            System.arraycopy(items, (nextFirst + 1) % len, reduce, 0 , len - nextFirst - 1);
            System.arraycopy(items, 0, reduce, len - nextFirst - 1 , nextLast - 1);
        }
        items = reduce;
        nextLast = size;
        len = len / 2;
        nextFirst = len - 1;
        resizeReduce();
    }

    public T removeFirst() {
        T remove = items [(nextFirst + 1) % len];
        nextFirst += 1;
        if(nextFirst > len - 1) {
            nextFirst -= len;
        }
        size -= 1;
        resizeReduce();
        return remove;
    }

    public T removeLast() {
        resizeReduce();
        T remove = items [(nextLast -1 + len) % len];
        nextLast -= 1;
        if(nextLast < 0) {
            nextLast += len;
        }
        size -= 1;
        resizeReduce();
        return remove;
    }

    public T get(int index){
        return items[(nextFirst + index + 1) % len];
    }

}
