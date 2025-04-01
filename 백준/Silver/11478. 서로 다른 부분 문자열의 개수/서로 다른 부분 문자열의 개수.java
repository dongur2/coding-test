import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

//주어진 문자열에 포함된 부분문자열 개수
class Main {
    //input: 10^3
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //base string
        String st = br.readLine();

        //중복비허용 -> 세트
        Set<String> set = new HashSet<>();

        //부분문자열 추가
        for(int len=1; len<=st.length(); len++) { //O(n)
            int left = 0;
            int right = left + len;
            
            while(right <= st.length()) {
                String sub = st.substring(left, right);
                set.add(sub); //O(n)

                left++;
                right++;
            }
        }

        System.out.print(set.size());
        
    } //O(10^6)

}