import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * BOJ_1931_회의실배정
 * @author parkrootseok
 */

public class Main {

	static class Meeting implements Comparable<Meeting> {
		
		int start;
		int end;
		
		public Meeting(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Meeting o) {
			return this.end != o.end ? this.end - o.end : this.start - o.start;
		}
		
	}
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	
	static int meetingNumber;
	static Meeting[] meetings;
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
	
		meetingNumber = Integer.parseInt(br.readLine().trim());
		
		meetings = new Meeting[meetingNumber];
		for(int curMeeting = 0; curMeeting < meetingNumber; curMeeting++) {
			
			inputs = br.readLine().trim().split(" ");					
			meetings[curMeeting] = new Meeting(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
			
		}
		
		Arrays.sort(meetings);
	
		List<Meeting> result = new ArrayList<>();
		result.add(meetings[0]);
		for(int curMeeting = 1; curMeeting < meetingNumber; curMeeting++) {
			
			if(result.get(result.size() - 1).end <= meetings[curMeeting].start) {
				result.add(meetings[curMeeting]);
			}
			
		}
		
		sb.append(result.size());
		bw.write(sb.toString());
		bw.close();
		
	}

}