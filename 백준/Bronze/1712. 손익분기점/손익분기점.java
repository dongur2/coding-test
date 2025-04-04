import java.util.*;
import java.io.*;

class Main {
    //손익분기점 판매량
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        int c1 = Integer.parseInt(st.nextToken()); //고정비용
        int c2 = Integer.parseInt(st.nextToken()); //가변비용
        int price = Integer.parseInt(st.nextToken()); //가격

        //가격보다 가변비용이 높으면 분기점 불가
        if(price <= c2) System.out.println(-1);
        //고정비용 상쇄 개수를 팔면 이익 0 -> 1개 더 팔면 이익
        else System.out.println((c1 / (price - c2)) + 1);
    }
}