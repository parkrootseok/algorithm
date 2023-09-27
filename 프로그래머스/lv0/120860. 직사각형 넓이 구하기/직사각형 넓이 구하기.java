class Solution {
    public int solution(int[][] dots) {

        int maxX = Integer.MIN_VALUE, minX = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE, minY = Integer.MAX_VALUE;

        for (int i = 0 ; i < dots.length ; i++) {

            maxX = Math.max(maxX, dots[i][0]);
            minX = Math.min(minX, dots[i][0]);
            maxY = Math.max(maxY, dots[i][1]);
            minY = Math.min(minY, dots[i][1]);

        }

        return (Math.abs(maxX - minX)) * (Math.abs(maxY - minY));

    }
    
}