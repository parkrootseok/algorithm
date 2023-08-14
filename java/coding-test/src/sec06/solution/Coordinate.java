package sec06.solution;

public class Coordinate implements Comparable<Coordinate> {

    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int compareTo(Coordinate o) {    // 정렬 기준(오름차순) 정의

        if (this.x == o.x) {
            return this.y - o.y;
        } else {
            return this.x - o.x;
        }

    }

}
