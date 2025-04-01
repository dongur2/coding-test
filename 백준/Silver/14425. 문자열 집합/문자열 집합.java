import java.util.Set;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

//집합에 포함되는 문자열 개수
//인풋 10^4
class Main {
    //O(n + m) -> O(2 * 10^4)
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //n, m 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        //집합
        Set<String> set = new HashSet<>();
        //집합 입력(n)
        for(int i=0; i<n; i++) {
            String word = br.readLine();
            set.add(word); //O(1)
        }
        
        //대상 문자열(m)
        int cnt = 0;
        for(int i=0; i<m; i++) {
            String word = br.readLine();
            //집합에 포함되는지 확인
            if(set.contains(word)) cnt++; //O(1)
        }

        System.out.println(cnt);
    }
}