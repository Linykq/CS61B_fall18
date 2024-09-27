public class LinkedListDeque<T> {
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        public Node(Node prev, Node next) {
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private Node sentinelFirst;
    private Node sentinelLast;

    public LinkedListDeque() {
        size = 0;
        sentinelFirst = new Node(null, null);
        sentinelLast = new Node(null, null);
        sentinelFirst.next = sentinelLast;
        sentinelLast.prev = sentinelFirst;
    }

    public void addFirst(T item) {
        Node first = new Node(item, sentinelFirst, sentinelFirst.next);
        sentinelFirst.next.prev = first;
        sentinelFirst.next = first;
        size += 1;
    }

    public void addLast(T item) {
        Node last = new Node(item, sentinelLast.prev, sentinelLast);
        sentinelLast.prev.next = last;
        sentinelLast.prev = last;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node curr = sentinelFirst.next;
        while (curr.next != null) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T remove = sentinelFirst.next.item;
        sentinelFirst.next.next.prev = sentinelFirst;
        sentinelFirst.next = sentinelFirst.next.next;
        size -= 1;
        return remove;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T remove = sentinelLast.prev.item;
        sentinelLast.prev.prev.next = sentinelLast;
        sentinelLast.prev = sentinelLast.prev.prev;
        size -= 1;
        return remove;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node curr = sentinelFirst;
        for (int i = 0; i <= index; i++) {
            curr = curr.next;
        }
        return curr.item;
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinelFirst.next, index);
    }

    private T getRecursiveHelper(Node curr, int index) {
        if (index == 0) {
            return curr.item;
        }
        return getRecursiveHelper(curr.next, index - 1);
    }

}
