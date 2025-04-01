import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

//목적지에 도착할 때까지 모을 수 있는 최대 가치
//input: n 10^3 m 10^3
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //n, m
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        //지도 생성
        int[][] table = new int[n][m];  
        for(int r=0; r<n; r++) {
            st = new StringTokenizer(br.readLine());
            
            for(int c=0; c<m; c++) {
                int num = Integer.parseInt(st.nextToken());
                table[r][c] = num;
            }
        }

        //탐색
        computeMax(table, n, m);
    }

    public static void computeMax(int[][] table, int n, int m) {
        //dp: 각 칸의 누적 최대값
        int[][] dp = new int[n][m];
        dp[0][0] = table[0][0];
        
        //첫번째 줄은 왼->오 이동만 가능
        for(int c=1; c<m; c++) {
            dp[0][c] = dp[0][c-1] + table[0][c];
        }

        //그다음 줄부턴 세 방향에서 넘어온 값 중 최대 값으로 바인딩
        for(int r=1; r<n; r++) {
            //각 행마다 최대값 바인딩 전 임시 저장 배열
            int[] left = new int[m];
            int[] right = new int[m];
            
            //위에서 내려온 값
            for(int c=0; c<m; c++) {
                left[c] = dp[r-1][c] + table[r][c];
                right[c] = dp[r-1][c] + table[r][c];
            }

            //왼쪽에서 넘어온 값
            for(int c=1; c<m; c++) {
                left[c] = Math.max(left[c], left[c-1] + table[r][c]);
            }
            
            //오른쪽에서 넘어온 값
            for(int c=m-2; c>=0; c--) {
                right[c] = Math.max(right[c], right[c+1] + table[r][c]);
            }

            //왼/오 최대값 선택
            for(int c=0; c<m; c++) {
                dp[r][c] = Math.max(left[c], right[c]);
            }
        }

        System.out.print(dp[n-1][m-1]);
    }
}