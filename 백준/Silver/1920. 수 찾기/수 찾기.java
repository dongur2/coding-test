import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        HashSet<Integer> Ns = new HashSet<>();
        StringTokenizer input = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            Ns.add(Integer.parseInt(input.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        input = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(input.nextToken());
            if (Ns.contains(num)) {
                bw.append("1\n");
            } else {
                bw.append("0\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();

    }
}
