package hangman;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Canvas {
    private final char DEFAULT_CHARACTER = ' ';
    private final int width;
    private final int height;
    private final Map<Coordinate, Point> points = new HashMap<>();

    Canvas(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addPoint(Point point) {
        this.points.put(point.getCoordinate(), point);
    }

    public void addPoints(List<Point> points) {
        this.points.putAll(points.stream().collect(Collectors.toMap(p -> p.getCoordinate(), p -> p)));
    }

    public void draw() {
        StringBuilder sb = new StringBuilder();
        Coordinate coordinate = new Coordinate(0, 0);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                coordinate.setX(x);
                coordinate.setY(y);
                if (points.containsKey(coordinate)) {
                    sb.append(points.get(coordinate).getSymbol());
                } else {
                    sb.append(DEFAULT_CHARACTER);
                }
            }
            sb.append(System.lineSeparator());
        }

        System.out.println(sb);
    }
}
