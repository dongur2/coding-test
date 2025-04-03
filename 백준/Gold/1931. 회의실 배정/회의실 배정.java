import java.util.*;
import java.io.*;

class Main {
    //회의실을 최대한 사용했을 때 회의 개수
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine()); //전체 회의 수

        int[][] table = new int[n][2];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            table[i][0] = start;
            table[i][1] = end;
        }

        Arrays.sort(table, new Comparator<int[]>(){
            @Override
            public int compare(int[] t1, int[] t2) {
                if(t1[1] == t2[1]) return t1[0] - t2[0];
                return t1[1] - t2[1];
            }
        });

        long answer = 0; //처음 회의 카운트
        int nowEnd = 0;

        for(int i=0; i<n; i++) {
            if(nowEnd <= table[i][0]) {
                nowEnd = table[i][1];
                answer++;
            }
        }
        

        //시작시간 빠른 순서대로 정렬 -> 같을 경우 종료시간 빠른 순서대로 정렬
        System.out.println(answer);
    }
}