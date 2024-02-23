class Solution {
    public int[] solution(int[][] score) {

        int[] rank = new int[score.length];


        for (int i = 0 ; i < score.length ; i++) {

            int r = 1;
            double avg = (score[i][0] + score[i][1]) / 2.0;

            for (int j = 0 ; j < score.length ; j++) {

                if (avg < (score[j][0] + score[j][1]) / 2.0 && i != j) {
                    r++;
                }

            }

            rank[i] = r;

        }

        return rank;

    }
}