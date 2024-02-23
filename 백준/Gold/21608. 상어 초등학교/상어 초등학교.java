import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * BOJ_21608_상어초등학교
 * @author parkrootseok
 * 
 * - 상어 초등학교엔 N by N 크기의 교실이 존재
 * - 학교에 다니는 학생 수는 N^2
 * - 학생의 순서는 존재(학생마다 좋아하는 학생 4명 존재)
 * - 다음 규칙을 이용해 자리 선택(한 칸에는 학생 한 명이고, |r1 - r2| + |c1 - c2| = 1 이라면 (r1, c1) / (r2, c2)는 인접)
 * - 자리 선택 방법
 *   - 비어있는 칸 중 인접한 칸에 좋아하는 학생이 가장 많은 칸으로 자리 선택
 *   - 위를 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리 선택
 *   - 위를 만족하는 칸도 여러 개이면, 행의 번호가 가장 작은 칸으로, 그래도 많으면 열의 번호가 가장 작은 칸으로
 * 
 * 1. 교실의 크기를 입력 받는다.
 * 2. 각 학생이 좋아하는 사람의 목록을 받는다.
 * 3. 자리 선택을 시작한다.
 *  3-1. 비어있는 칸 중 인접한 칸에 좋아하는 학생이 가장 많은 칸으로 자리 선택
 *  3-2. 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리 선택
 *  3-3. 여러 개이면, 행의 번호가 가장 작은 칸으로, 그래도 많으면 열의 번호가 가장 작은 칸으로 선택
 * 4. 위 조건을 만족하는 자리에 착석 
 * 5. 만족도 구하기
 *  
 *  
 */

public class Main {

	static class Student {

		String number;
		List<String> favorites = new ArrayList<>();
		int x;
		int y;

		public Student(String number, String[] favorites) {

			this.number = number;
			for(String f : favorites) {
				this.favorites.add(f);
			}
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public List<String> getFavorites() {
			return favorites;
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	static int size;
	static int[] seatInfo;
	static Student[][] classRoom;
	static Student student;

	public static boolean getSeatInformation(Student s, int row, int cols) {
		
		if(classRoom[row][cols] != null) {
			// 비어있지 않다면 이미 자리가 있으므로 종료
			return false;
		}
		
		int fCount = 0, eCount = 0;
		for (int index = 0; index < dx.length; index++) {

			int nx = row + dx[index];
			int ny = cols + dy[index];

			// 다음 위치가 올바른 인덱스일 때
			if ((0 < nx && nx <= size && 0 < ny & ny <= size)) {
				
				// 비어있다면 카운트
				if (classRoom[nx][ny] == null) { 
					eCount++;
				} 
				
				// 좋아하는 학생이라면 카운트
				else if (s.getFavorites().contains(classRoom[nx][ny].getNumber())) { 
					fCount++;
				}

			}

		}
		
		// 빈 좌서의 수와 좋아하는 학생의 수에 대한 정보를 저장
		seatInfo[0] = fCount;
		seatInfo[1] = eCount;
		
		return true;

	}
	
	public static int calcHappy(int row, int cols) {
		
		int fCount = 0;
		for (int index = 0; index < dx.length; index++) {

			int nx = row + dx[index];
			int ny = cols + dy[index];

			// 인접한 칸이 비어있지 않으면
			if ((0 < nx && nx <= size && 0 < ny & ny <= size)) {

				// 좋아하는 학생인지 확인
				if (classRoom[row][cols].getFavorites().contains(classRoom[nx][ny].getNumber())) {
					fCount++;
				}
				
			}

		}
		
		return fCount;
		
	}
	
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 교실의 크기를 입력 받는다.
		size = Integer.parseInt(br.readLine().trim());
		classRoom = new Student[size + 1][size + 1];
		seatInfo = new int[2];
		
		// 2. 각 학생이 좋아하는 사람의 목록을 받는다.
		for (int index = 1; index <= size * size; index++) {
			inputs = br.readLine().trim().split(" ");
			student = new Student(inputs[0], Arrays.copyOfRange(inputs, 1, inputs.length));

			// 3. 자리 선택을 시작한다
			// 행의 번호가 가장 작은 칸으로, 그래도 많으면 열의 번호가 가장 작은 칸으로 선택하기 위해 인덱스를 뒤에서 부터 시작
			int emax = -1, fmax = -1;
			for (int row = size; 1 <= row; row--) {

				for (int cols = size; 1 <= cols; cols--) {

					// 3-1. 좌석이 비어있다면
					if(getSeatInformation(student, row, cols)) {
						
						// 좋아하는 학생수가 제일 많은 칸을 사용하고
						if (fmax < seatInfo[0]) {
							fmax = seatInfo[0];
							emax = seatInfo[1];
							student.setX(row);
							student.setY(cols);
						} 
						
						// 3-2. 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리 선택
						else if (fmax == seatInfo[0] && emax < seatInfo[1]){
							fmax = seatInfo[0];
							emax = seatInfo[1];
							student.setX(row);
							student.setY(cols);
						}
						
					}

				}

			}
			
			// 4. 위 조건을 만족하는 자리에 착석
			classRoom[student.x][student.y] = student;

		}
		
		// 5. 만족도 구하기
		int answer = 0;
		for (int row = size; 1 <= row; row--) {

			for (int cols = size; 1 <= cols; cols--) {

				int fCount = calcHappy(row, cols);
				
				switch(fCount) {
					case 1:
						answer += 1;
						break;
					case 2:
						answer += 10;
						break;
					case 3:
						answer += 100;
						break;
					case 4:
						answer += 1000;
						break;
				}

			}

		}

		sb.append(answer).append("\n");
		bw.write(sb.toString());
		bw.close();

		return;

	}

}