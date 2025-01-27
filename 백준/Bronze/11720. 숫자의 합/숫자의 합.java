import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int count = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        String input = br.readLine();

        int sum = 0;
        for (int i = 0; i < input.length(); i++) {
            int t = Character.getNumericValue(input.charAt(i));
            sum += t;
        }

        bw.write(sum + "");

        bw.close();
        br.close();
    }
}
