import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

//대칭 차집합 원소 개수
class Main {
    //input: 10^5
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //각 원소 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        Set<Integer> aSet = new HashSet<>();
        Set<Integer> bSet = new HashSet<>();

        //원소 추가
        addNum(br, aSet, a); //O(a)
        addNum(br, bSet, b); //O(b)

        //서로 추가되지 않은 원소 카운트
        int answer = 0;
        answer += checkNum(aSet, bSet); //O(a)
        answer += checkNum(bSet, aSet); //O(b)

        System.out.print(answer);
    } //O(2a + 2b)

    public static void addNum(BufferedReader br, Set<Integer> set, int size) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<size; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }
    }

    public static int checkNum(Set<Integer> base, Set<Integer> another) {
        int cnt = 0;
        for(int b:base) {
            if(!another.contains(b)) cnt++;
        }
        return cnt;
    }
}