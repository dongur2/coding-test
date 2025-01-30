import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//구간 합 구하기 2
public class Main {
    /*
    * N×N개의 수가 N×N 크기의 표에 채워져 있다. (x1, y1)부터 (x2, y2)까지 합을 구하는 프로그램을 작성하시오.
    * I> 첫째 줄에 표의 크기 N과 합을 구해야 하는 횟수 M, 둘째 줄부터 N개의 줄에는 표에 채워져 있는 수가 1행부터 차례대로 주어진다
    * 다음 M개의 줄에는 네 개의 정수 x1, y1, x2, y2 가 주어지며, (x1, y1)부터 (x2, y2)의 합을 구해 출력
    * O> 총 M줄에 걸쳐 (x1, y1)부터 (x2, y2)까지 합을 구해 출력한다.
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //구간합 배열 저장
        long[][] table = new long[N+1][N+1];
        for(int r=1; r<=N; r++) {
            st = new StringTokenizer(br.readLine());

            for(int c=1; c<=N; c++) {
                table[r][c] = table[r][c - 1] + Long.parseLong(st.nextToken());
            }
        }

        //해당하는 구간합 구하기
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken()), y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken()), y2 = Integer.parseInt(st.nextToken());

            long sum = 0;
            for(int row = x1; row<=x2; row++) {
                sum += table[row][y2] - table[row][y1 - 1];
            }
            sb.append(sum).append("\n");
        }

        System.out.print(sb);
    }
}
