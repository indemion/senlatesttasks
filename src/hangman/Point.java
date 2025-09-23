package hangman;

class Point {
    private final Coordinate coordinate;
    private final char symbol;

    Point(int x, int y, char symbol) {
        this.coordinate = new Coordinate(x, y);
        this.symbol = symbol;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public char getSymbol() {
        return symbol;
    }
}
