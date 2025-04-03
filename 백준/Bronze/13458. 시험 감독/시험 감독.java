import java.util.*;
import java.io.*;

class Main {    
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine()); //시험장 개수

        int[] arr = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            int a = Integer.parseInt(st.nextToken()); //각 시험장 응시자 수
            arr[i] = a;
        }

        st = new StringTokenizer(br.readLine());
        int b = Integer.parseInt(st.nextToken()); //총감독관 감시자 수
        int c = Integer.parseInt(st.nextToken()); //부감독관 감시자 수

        long answer = 0;

        //총감독관은 무조건 1명
        for(int i=0; i<n; i++) {
            int p = arr[i];

            answer++; //총감
            p -= b; 

            if(p > 0) {
                answer += p / c; //부감
                if(p % c > 0) answer++;
            }
        }
        System.out.println(answer);
    }

}