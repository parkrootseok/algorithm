import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;


class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int H, W, N;
    static int ANSWER;

    static String[][] map;
    static Tank tank;

    static int[] dx = {0, 1, 1, 1};
    static int[] dy = {1, 0, 1, -1};

    static String FLAT = ".";
    static String BRICK = "*";
    static String IRON = "#";
    static String WATER = "-";

    static String UP = "^";
    static String DOWN = "v";
    static String LEFT = "<";
    static String RIGHT = ">";

    static class Tank {

        int x;
        int y;
        String direction;

        public Tank(int x, int y, String direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public void up() {

            this.direction = UP;

            if (isValid(this.x - 1, this.y) && canMove(this.x - 1, this.y)) {
                map[x][y] = FLAT;
                this.x--;
            }

            map[x][y] = this.direction;

        }

        public void down() {

            this.direction = DOWN;

            if (isValid(this.x + 1, this.y) && canMove(this.x + 1, this.y)) {
                map[x][y] = FLAT;
                this.x++;
            }

            map[x][y] = this.direction;

        }

        public void left() {

            this.direction = LEFT;

            if (isValid(this.x, this.y - 1) && canMove(this.x, this.y - 1)) {
                map[x][y] = FLAT;
                this.y--;
            }

            map[x][y] = this.direction;


        }

        public void right() {

            this.direction = RIGHT;

            if (isValid(this.x, this.y + 1) && canMove(this.x, this.y + 1)) {
                map[x][y] = FLAT;
                this.y++;
            }

            map[x][y] = this.direction;


        }

        public void shoot() {

            int move = 0;

            if (this.direction.equals(UP)) {

                int nx = this.x - 1;
                int y = this.y;

                while (isValid(nx, y) && !map[nx][y].equals(IRON)) {

                    if (map[nx][y].equals(BRICK)) {
                        map[nx][y] = FLAT;
                        return;
                    }

                    nx--;
                }

            } else if (this.direction.equals(DOWN)) {

                int nx = this.x + 1;
                int y = this.y;

                while (isValid(nx, y) && !map[nx][y].equals(IRON)) {

                    if (map[nx][y].equals(BRICK)) {
                        map[nx][y] = FLAT;
                        return;
                    }

                    nx++;

                }

            } else if (this.direction.equals(LEFT)) {

                int x = this.x;
                int ny = this.y - 1;

                while (isValid(x, ny) && !map[x][ny].equals(IRON)) {

                    if (map[x][ny].equals(BRICK)) {
                        map[x][ny] = FLAT;
                        return;
                    }

                    ny--;

                }
            } else {

                int x = this.x;
                int ny = this.y + 1;

                while (isValid(x, ny) && !map[x][ny].equals(IRON)) {

                    if (map[x][ny].equals(BRICK)) {
                        map[x][ny] = FLAT;
                        return;
                    }

                    ny++;

                }

            }

        }

        public boolean isValid(int nx, int ny) {

            if (0 <= nx && nx < H && 0 <= ny && ny < W) {
                return true;
            }

            return false;

        }

        public boolean canMove(int nx, int ny) {

            if (!map[nx][ny].equals(FLAT)) {
                return false;
            }

            return true;

        }

    }

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + " ");

            String[] inputs = br.readLine().split(" ");
            H = Integer.parseInt(inputs[0]);
            W = Integer.parseInt(inputs[1]);

            map = new String[H][W];
            for (int j = 0; j < H; j++) {
                map[j] = br.readLine().split("");
            }

            for (int j = 0; j < H; j++) {
                for (int k = 0; k < W; k++) {
                    if (map[j][k].equals(UP)) {
                        tank = new Tank(j, k, map[j][k]);
                    } else if (map[j][k].equals(DOWN)) {
                        tank = new Tank(j, k, map[j][k]);
                    } else if (map[j][k].equals(LEFT)) {
                        tank = new Tank(j, k, map[j][k]);
                    } else if (map[j][k].equals(RIGHT)) {
                        tank = new Tank(j, k, map[j][k]);
                    }
                }
            }

            N = Integer.parseInt(br.readLine());
            inputs = br.readLine().split("");
            for (String input : inputs) {

                switch (input) {

                    case "U":
                        tank.up();
                        break;
                    case "D":
                        tank.down();
                        break;
                    case "L":
                        tank.left();
                        break;
                    case "R":
                        tank.right();
                        break;
                    case "S":
                        tank.shoot();
                        break;

                }

            }

            for (int j = 0; j < H; j++) {
               String m = Arrays.stream(map[j]).collect(Collectors.joining());
                bw.write(m + "\n");
            }

        }

        bw.close();
    }

}