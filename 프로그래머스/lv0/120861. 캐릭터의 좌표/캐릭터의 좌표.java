class Solution {
    public int[] solution(String[] keyinput, int[] board) {

        int[] result = new int[2];

        int xLimit = board[0] / 2, yLimit = board[1] / 2;
        for (String direction : keyinput) {

            switch (direction) {
                case "right":
                    if (result[0] < xLimit) {
                        result[0]++;
                    }
                    break;
                case "left":
                    if (result[0] > -1 * xLimit) {
                        result[0]--;
                    }
                    break;
                case "up":
                    if (result[1] < yLimit) {
                        result[1]++;
                    }
                    break;
                case "down":
                    if (result[1] > -1 * yLimit) {
                        result[1]--;
                    }
                    break;
                default:
                    break;
            }

        }

        return result;

    }

}