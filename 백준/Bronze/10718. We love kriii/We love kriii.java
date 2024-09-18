import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		sb.append("강한친구 대한육군").append("\n").append("강한친구 대한육군");
		bw.write(sb.toString());
		bw.close();

	}

}