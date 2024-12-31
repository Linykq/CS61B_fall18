package byog.Core;

public class Position {
    int elements[];

    public Position() {
        elements = new int[2];
    }
    public Position(int x, int y) {
        elements = new int[2];
        elements[0] = x;
        elements[1] = y;
    }

    public int getX() {
        return elements[0];
    }

    public int getY() {
        return elements[1];
    }

    public Position addX(int x) {
        return new Position(elements[0] + x, elements[1]);
    }

    public Position addY(int y) {
        return new Position(elements[0], elements[1] + y);
    }
}
