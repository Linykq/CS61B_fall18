import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {   
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> testDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> stdDeque = new ArrayDequeSolution<>();
        String log = "";
        while (true) {
            int num = StdRandom.uniform(1000);
            int state = StdRandom.uniform(4);
            Integer testNumber;
            Integer stdNumber;
            switch (state) {
                case 0:
                    log = log + "addFirst(" + num + ")\n";
                    testDeque.addFirst(num);
                    stdDeque.addFirst(num);
                    break;
                case 1:
                    log = log + "addLast(" + num + ")\n";
                    testDeque.addLast(num);
                    stdDeque.addLast(num);
                    break;
                case 2:
                    if(stdDeque.isEmpty()) {
                        break;
                    }
                    log = log + "removeFirst()\n";
                    testNumber = testDeque.removeFirst();
                    stdNumber = stdDeque.removeFirst();
                    assertEquals(log, testNumber, stdNumber);
                    break;
                case 3:
                    if(stdDeque.isEmpty()) {
                        break;
                    }
                    log = log + "removeLast()\n";
                    testNumber = testDeque.removeLast();
                    stdNumber = stdDeque.removeLast();
                    assertEquals(log, testNumber, stdNumber);
                    break;
                default:
            }
        }
    }
}
