import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        /*
         * i~j번째 수의 합 출력
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);

        int N = Integer.parseInt(st.nextToken()); //숫자 개수
        int M = Integer.parseInt(st.nextToken()); //연산 횟수

        //N개의 수를 입력받아 배열에 저장
        input = br.readLine();
        String[] arr = input.split(" ");

        // 누적 합 배열 **
        int[] preSum = new int[N + 1];
        for (int i=1; i<=N; i++) {
            preSum[i] = preSum[i-1] + Integer.parseInt(arr[i-1]);
        }

        //연산 & 결과값 저장 **
        int[] res = new int[M];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine()); // input
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            res[i] = preSum[end] - preSum[start-1];
        }
        
        //출력
        for(int r : res) {
            System.out.println(r);
        }
    }
}
