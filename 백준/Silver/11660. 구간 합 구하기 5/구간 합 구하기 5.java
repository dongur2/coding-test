import java.io.*;
import java.util.*;


public class Main {
    /*
     * 주어진 배열 시작 위치 ~ 끝 위치의 합 출력 - 사각형 영역 내 합
     */
    public static void main(String[] args) throws IOException {

        //1. 배열 크기 N, 합 연산 횟수 M 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //2. N크기의 배열 입력
        int[][] arr = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j=1; j<=N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //3. 누적합 구하기
        int[][] sum = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + arr[i][j];
            }
        }

        //4. 연산
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int res = sum[x2][y2] - sum[x1-1][y2] - sum[x2][y1-1] + sum[x1-1][y1-1];
            System.out.println(res);
        }

        /*
        * 사각형에서 맞닿은 모서리는 빼고, 마주보는 모서리는 더함
        * */
    }
}