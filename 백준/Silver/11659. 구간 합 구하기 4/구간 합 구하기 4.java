import java.io.*;
import java.util.*;

//구간 합 구하기 1
public class Main {
    /*
     * 수 N개가 주어졌을 때, i번째 수부터 j번째 수까지 합을 구하는 프로그램을 작성하시오.
     * I> 첫째 줄에 수의 개수 N과 합을 구해야 하는 횟수 M, 둘째 줄에는 N개의 수(0< 숫자 <=1000), 셋째 줄부터 M개의 줄에는 합을 구해야 하는 구간 i와 j가 주어진다.
     * O> 총 M개의 줄에 입력으로 주어진 i번째 수부터 j번째 수까지 합을 출력한다.
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        //구간합 계산
        int[] table = new int[N + 1];
        for(int i=1; i<=N; i++) {
            table[i] = table[i-1] + Integer.parseInt(st.nextToken());
        }

        //인덱스를 받아 해당하는 구간합 구하기
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int sum = table[end] - table[start-1];
            sb.append(sum).append("\n");
        }

        System.out.println(sb);
    }
}