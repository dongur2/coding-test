import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

//나머지 합 구하기
public class Main {
    /*
    * 연속된 부분 구간의 합이 M으로 나누어 떨어지는 구간의 개수를 구하는 프로그램을 작성하시오.
    * I> 첫째 줄에 N과 M, 둘째 줄에 N개의 수
    * O> 연속된 부분 구간의 합이 M으로 나누어 떨어지는 구간의 개수를 출력한다.
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //주어지는 숫자 개수
        int M = Integer.parseInt(st.nextToken()); //몫

        long[] table = new long[N+1]; //0 고정

        //구간합으로 저장
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            table[i] = table[i - 1] + Long.parseLong(st.nextToken());
        }

        Map<Long, Long> rMap = new HashMap<>(); //나머지 개수 저장

        //구간합을 몫으로 나눈 나머지로 업데이트
        for(int i=0; i<=N; i++) {
            long r = table[i] % M;
            rMap.put(r, rMap.getOrDefault(r, 0L)+1); //나머지가 같은 구간합 개수를 맵에 저장
        }

        //나머지가 같은 구간합으로 만들 수 있는 2쌍 조합 총 개수 구하기 nCr = n! / (n-r)!r!
        long answer = 0;
        for(long cnt : rMap.values()) {
            answer += cnt * (cnt - 1) / 2;
        }
        System.out.print(answer);
    }
}