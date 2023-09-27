class Solution {
   public String solution(String polynomial) {

        String[] split = polynomial.split("[ ][+][ ]");

        if (split.length == 0) {
            return polynomial;
        }

        int x = 0, con = 0;
        for (String p : split) {

            if (p.contains("x")) {
                if (p.equals("x")) {
                    x += 1;
                } else {
                    x += Integer.parseInt(p.replace("x", ""));
                }
            } else {
                con += Integer.parseInt(p);
            }

        }

        StringBuilder sb = new StringBuilder();

        if (x != 0 && con != 0) {
            if (x == 1) {
                sb.append("x"  + " + " + con);
            } else {
                sb.append(x + "x"  + " + " + con);
            }
        } else if (x != 0) {
            if (x == 1) {
                sb.append("x");
            } else {
                sb.append(x + "x");
            }
        } else {
            sb.append(con + "");
        }

        return sb.toString();

    }
}