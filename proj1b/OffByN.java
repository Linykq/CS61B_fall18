public class OffByN implements CharacterComparator {
    private int gap;

    public OffByN(int N) {
        this.gap = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == gap;
    }
}
