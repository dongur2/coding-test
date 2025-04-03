import java.util.*;
import java.io.*;

class Main {
    //모두가 돈을 인출하는 데 필요한 최소 시간
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine()); //사람 수

        int[] times = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }

        //빨리 끝나는 사람부터 앞으로
        Arrays.sort(times);

        //소요 시간 누적
        for(int i=1; i<n; i++) {
            times[i] += times[i-1];
        }
        
        long answer = Arrays.stream(times).sum();
        System.out.println(answer);
    }
}