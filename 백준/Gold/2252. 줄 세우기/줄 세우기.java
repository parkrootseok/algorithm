import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import sun.security.krb5.internal.crypto.crc32;

/***
 * BOJ_2252_줄세우기
 * @author parkrootseok
 * 
 * - N명의 학생들을 키순서로 정렬
 * - 두 학생의 키를 비교하는 방법을 사용
 * 
 * 1. 학생들의 총 번호수와 비교  횟수를 받는다.
 * 
 */

public class Main {
	
	static class Student implements Comparable<Student> {
		
		int number;
		List<Student> back;
		int inDegree;
		
		Student(int number) {
			this.number = number;
			this.back = new ArrayList<>();
			this.inDegree = 0;
		}
		
		@Override
		public int compareTo(Student o) {
			int diff = this.inDegree - o.inDegree;
			
			// 진입 차수가 더 작거나 같을 경우 유지
			if(diff <= 0) {	
				return -1;
			}
			
			// 진입  차수가 더 클 경우 변경
			else {
				return 1;
			}
		
		}
		
		
	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;

	static final int TOTAL_STUDENT_NUMBER = 4;
	
	static int totalStudentNumber;
	static int compareNumber;
	static int[] student;
	
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 학생들의 총 번호수와 비교  횟수를 받는다.
		inputs = br.readLine().trim().split(" ");
		totalStudentNumber = Integer.parseInt(inputs[0]);
		compareNumber = Integer.parseInt(inputs[1]);
		
		
		// 2. 학생을 관리하는 배열을 초기화
		Student[] students = new Student[totalStudentNumber + 1];
		for(int curStudentNumber = 1; curStudentNumber <= totalStudentNumber; curStudentNumber++) {
			students[curStudentNumber] = new Student(curStudentNumber);
		}
		
		// 3. 학생들에 대한 비교 순위를 받는다.
		for(int curCompare = 0; curCompare < compareNumber; curCompare++) {
			
			// 두 학생의 번호를 받는다.
			inputs = br.readLine().trim().split(" ");
			int first = Integer.parseInt(inputs[0]);
			int last = Integer.parseInt(inputs[1]);
			
			// first학생의 뒤에 last 학생을 추가
			students[first].back.add(students[last]);
			
			// last 학생에 대한 진입차수를 증가;
			students[last].inDegree++;
			
		}
		
		// 4. 위상 정렬을 수행
		// 한 정점의 진입 차수가 0이 아니라는 것은 자신보다 우선 순위가 높은 학생이 있음을 의미
		// 즉, 진입 차수가 0이라면 자신의 앞에 학생이 없을을 의미
		PriorityQueue<Student> pq = new PriorityQueue<>(
			
		);
		
		for(int curStudentNumber = 1; curStudentNumber <= totalStudentNumber; curStudentNumber++) {
			
			// 진입 차수가 0인 학생들을 추가
			if(students[curStudentNumber].inDegree == 0) {
				pq.add(students[curStudentNumber]);
			}
			
		}
		
		while(!pq.isEmpty()) {
			
			// 현재 학생
			Student curStudent = pq.poll();
			
			sb.append(curStudent.number).append(" ");
			
			for(Student back : curStudent.back) {
				
				// 현재 학생의 다음 순서에 존재하는 학생들에 대한 진입 차수를 감소
				back.inDegree--;
				
				// 만약, 감소시킨 진입차수가 0이라면 큐에 삽입
				if(back.inDegree == 0) {
					pq.add(back);
				}
				
			}
			
		}
		
		
		bw.write(sb.toString());
		bw.close();

	}

}