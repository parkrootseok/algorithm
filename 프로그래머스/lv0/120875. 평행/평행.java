import java.util.*;

class Solution {
    
     public double gradient(int x1, int y1, int x2, int y2) {

        return (y1 - y2) / (double) (x1 - x2);

    }

    public int solution(int[][] dots) {

//        ArrayList<line> gradients = new ArrayList<>();
        HashMap<Double, Double> map = new HashMap<>();
        
        for (int i = 0; i < dots.length; i++) {

            int x1 = dots[i][0], y1 = dots[i][1];

            for (int j = i + 1; j < dots.length; j++) {

                double a = gradient(x1, y1, dots[j][0], dots[j][1]);
                double b = y1 - a * x1;

                if (map.get(a) != null && (map.get(a) != b || map.get(a) == 0)) {
                    return 1;
                }

                map.put(a, b);

            }

        }

//        Collections.sort(gradients);
//
//        for (int i = 0; i < gradients.size() - 1; i++) {
//
//            if (gradients.get(i).gradient == gradients.get(i + 1).gradient) {
//                return 1;
//            }
//
//        }

        return 0;

    }
}