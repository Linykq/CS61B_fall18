public class LinkedListDeque<T> {
    public class Node{
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
    private Node SentinelFirst;
    private Node SentinelLast;

    public LinkedListDeque() {
        size = 0;
        SentinelFirst = new Node(null, null);
        SentinelLast = new Node(null, null);
        SentinelFirst.next = SentinelFirst;
        SentinelLast.prev = SentinelFirst;
    }

    public void addFirst(T item) {
        Node first = new Node(item, SentinelFirst, SentinelFirst.next);
        SentinelFirst.next.prev = first;
        SentinelFirst.next = first;
        size += 1;
    }

    public void addLast(T item) {
        Node last = new Node(item, SentinelLast.prev, SentinelLast);
        SentinelLast.prev.next = last;
        SentinelLast.prev = last;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
       Node curr = SentinelFirst;
        while(curr != null) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
    }

    public T removeFirst() {
        T remove = SentinelFirst.next.item;
        SentinelFirst.next.next.prev = SentinelFirst;
        SentinelFirst.next = SentinelFirst.next.next;
        size -= 1;
        return remove;
    }

    public T removeLast() {
        T remove = SentinelLast.prev.item;
        SentinelLast.prev.prev.next = SentinelLast;
        SentinelLast.prev = SentinelLast.prev.prev;
        size -= 1;
        return remove;
    }

    public T get(int index) {
        Node curr = SentinelFirst;
        for(int i = 0; i <= index; i++) {
            curr = curr.next;
        }
        return curr.item;
    }

    public T getRecursive(int index) {
        if(index >= size) {
            return null;
        }
        return getRecursiveHelper(SentinelFirst.next, index);
    }

    public T getRecursiveHelper(Node curr, int index) {
        if(index == 0) {
            return curr.item;
        }
        return getRecursiveHelper(curr.next, index - 1);
    }

}
