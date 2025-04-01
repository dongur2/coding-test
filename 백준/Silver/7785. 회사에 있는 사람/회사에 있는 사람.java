import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

//현재 회사에 있는 사람 리턴
//인풋 10^6
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //n 입력 (로그 수)
        int n = Integer.parseInt(br.readLine());
        
        //출퇴근 로그 입력 O(n)
        Set<String> set = new TreeSet<>(Collections.reverseOrder());
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String status = st.nextToken();

            //출근시 추가, 퇴근시 삭제
            if(status.equals("enter")) set.add(name); //O(1)
            else set.remove(name); //O(1)
        }

        //남아있는 사람 사전 역순으로 출력
        set.forEach(l->System.out.println(l));
    }
}