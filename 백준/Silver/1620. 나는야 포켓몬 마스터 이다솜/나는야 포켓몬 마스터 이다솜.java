import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

//이름 -> 번호, 번호 -> 이름
class Main {
    //input: 10^5
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //n, m
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        //도감 등록 O(n)
        String[] arr = new String[n+1];
        Map<String, Integer> dict = new HashMap<>();
        
        for(int i=1; i<=n; i++) {
            String name = br.readLine();
            arr[i] = name;
            dict.put(name, i);
        }

        //문제
        for(int i=0; i<m; i++) { //O(m * n)
            String q = br.readLine();
            char c = q.charAt(0); //첫글자
            
            //숫자일 경우 < 65
            if((int)c < 65) System.out.println(arr[(Integer.parseInt(q))]); //O(1)
            
            //이름일 경우 >= 65
            else System.out.println(dict.get(q)); //O(1)
        }

    } //O(n + m*n)
}