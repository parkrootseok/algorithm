import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * BOJ_2458_키순서
 * @author parkrootseok
 * 
 * 1. 테스트 케이스 횟수 입력
 * 2. 테스트 케이스 입력
 *  2-1. 학생들의 수와 비교 횟수 받기
 *  2-3. 비교 정보 받기
 * 3. 자신의 키 순서가 몇 번째 인지 알 수 학생수의 수를 구한다.
 *  3-1. 자신보다 키 큰 모든 사람을 방문
 *  3-2. 자신보다 키 작은 모든 사람을 방문
 *  3-3. 방문하지 않은 학생이 한 명이라도 있다면 실패
 **/
class Main {
	
	static class Student {

		int index;
		List<Student> bigger;
		List<Student> smaller;

		public Student(int index) {
			this.index = index;
			this.bigger = new ArrayList<>();
			this.smaller = new ArrayList<>();
		}

	}
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	
	static Student[] students;
	static int studentNumber;
	static int compareNumber;
	static int possibleCount;
	
	public static void input() throws NumberFormatException, IOException {
		
		// 2-1. 학생들의 수와 비교 횟수 받기
		inputs = br.readLine().trim().split(" ");
		studentNumber = Integer.parseInt(inputs[0]);
		compareNumber = Integer.parseInt(inputs[1]);
		
		students = new Student[studentNumber + 1];
		for (int idx = 1; idx <= studentNumber; idx++) {
			students[idx] = new Student(idx);
		}
		
		// 2-2. 비교 정보 받기
		for (int curCompareNumber = 0; curCompareNumber < compareNumber; curCompareNumber++) {
			inputs = br.readLine().trim().split(" ");
			int smaller = Integer.parseInt(inputs[0]);
			int bigger = Integer.parseInt(inputs[1]);
			
			students[smaller].bigger.add(students[bigger]);
			students[bigger].smaller.add(students[smaller]);
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
			
		// 2. 테스트 케이스 입력
		input();

		// 3. 자신의 키 순서가 몇 번째 인지 알 수 학생수의 수를 구한다.
		possibleCount = 0;
		for (int curStudent = 1; curStudent <= studentNumber; curStudent++) {

			if (check(curStudent)) {
				possibleCount++;
			}

		}

		sb.append(possibleCount).append("\n");
		bw.write(sb.toString());
		bw.close();
		
		return;
	
	}
	
	public static boolean check(int start) {
		
		int count = 0;
		boolean[] isVisited = new boolean[studentNumber + 1];
		Queue<Student> studentQ = new ArrayDeque<>();
		
		// 3-1. 자신보다 키 큰 모든 사람을 방문
		studentQ.add(students[start]);
		isVisited[start] = true;
		while(!studentQ.isEmpty()) {
			
			Student student = studentQ.poll();
			
			for(Student bigger : student.bigger) {
				
				if (isVisited[bigger.index]) {
					continue;
				}
				
				
				studentQ.add(bigger);
				isVisited[bigger.index] = true;
				count++;
				
			}
			
		}

		// 3-2. 자신보다 키 작은 모든 사람을 방문
		studentQ.add(students[start]);
		while(!studentQ.isEmpty()) {
			
			Student student = studentQ.poll();
			
			for(Student smaller : student.smaller) {
				
				if (isVisited[smaller.index]) {
					continue;
				}
				
				studentQ.add(smaller);
				isVisited[smaller.index] = true;
				count++;
				
			}
			
		}
		
		
		// 3-3. 방문하지 않은 학생이 한 명이라도 있다면 실패
		if (count != studentNumber - 1) {
			return false;
		}
		
		return true;
		
	}

}