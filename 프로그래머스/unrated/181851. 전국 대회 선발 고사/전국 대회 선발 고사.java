import java.util.*;

class Solution {
    class Student implements Comparable<Student> {

        int num;
        int rank;

        public Student(int num, int rank) {
            this.num = num;
            this.rank = rank;
        }

        @Override
        public int compareTo(Student o) {
            return this.rank - o.rank;
        }

    }

    public int solution(int[] rank, boolean[] attendance) {
        int answer = 0;

        ArrayList<Student> candidate = new ArrayList<>();
        for (int i = 0 ; i < rank.length ; i++) {

            if (attendance[i]) {
                candidate.add(new Student(i, rank[i]));
            }

        }

        Collections.sort(candidate);

        int sum = 0, weight = 10000;
        for (Student s : candidate) {

            sum += s.num * weight;
            weight /= 100;

        }

        return sum;

    }
}