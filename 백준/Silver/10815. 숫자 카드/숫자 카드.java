import java.util.*;
import java.io.*;

//주어지는 숫자 중 가지고 있는 카드에 있는 숫자 체크
//인풋 개수 10^5
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //가진 카드 개수 입력
        int n = Integer.parseInt(br.readLine());
        
        //카드의 숫자 입력
        Set<Integer> cards = new HashSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            cards.add(num);
        }
        
        //주어지는 숫자 개수 입력
        int m = Integer.parseInt(br.readLine());
        
        //주어지는 숫자 입력
        st = new StringTokenizer(br.readLine());
        
        int[] answer = new int[m];
        for(int i=0; i<m; i++) {
            int num = Integer.parseInt(st.nextToken());

            //카드 중에 숫자가 존재하는지 확인
            if(cards.contains(num)) answer[i] = 1;
        }
        
        //결과 리턴
        Arrays.stream(answer).forEach(a->System.out.print(a + " "));
    }
}