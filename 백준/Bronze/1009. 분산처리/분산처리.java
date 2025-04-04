import java.util.*;
import java.io.*;
import java.math.BigInteger;

class Main {
    //마지막 데이터가 처리되는 컴퓨터 번호 (10대)
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine()); //테스트 개수

        for(int i=0; i<t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            BigInteger tests = BigInteger.valueOf(a);
            tests = tests.modPow(BigInteger.valueOf(b), BigInteger.valueOf(10)); //총 데이터 개수를 10으로 나눈 나머지
            
            int now = tests.intValue(); //마지막
            
            if(now == 0) now = 10;
            System.out.println(now);
        }
    }
}