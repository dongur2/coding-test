import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

//조건 동시 만족 명단
class Main {
    //input: 10^5
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //n, m
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();
        //n O(n)
        for(int i=0; i<n; i++) {
            String name = br.readLine();
            set.add(name);
        }

        //m - 위에서 이미 언급된 사람 명단 추가 O(m)
        List<String> list = new ArrayList<>();
        for(int i=0; i<m; i++) {
            String name = br.readLine();
            if(set.contains(name)) list.add(name);
        }

        Collections.sort(list); //사전순 O(KlogK)
        System.out.println(list.size());
        list.forEach(l->System.out.println(l));
    }
}