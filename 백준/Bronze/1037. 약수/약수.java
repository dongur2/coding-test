import java.util.*;
import java.io.*;

class Main {
    //주어진 수를 약수로 가지고 있는 수
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int idx = Integer.parseInt(br.readLine());

        int[] arr = new int[idx];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<idx; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }
    
        Arrays.sort(arr); //정렬

        //가장 작은 약수 * 가장 큰 약수
        System.out.println(arr[0] * arr[idx-1]);
    }
}