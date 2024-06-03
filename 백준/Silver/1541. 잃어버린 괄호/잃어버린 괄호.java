import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /*
    * ## 식의 최소값 출력
    * 최대한 큰 수를 빼기: - 등장 이후로는 계속 빼기 가능
    * * 첫 숫자는 무조건 양수
    * */
    public static void main(String[] args) throws IOException {
        int sum = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //1. 식 입력 - 음수가 있다면 음수부터 계속 뺄 것이므로 -> '-'를 기준으로 나누기
        String[] sentence = br.readLine().split("-");

        //2. 나눈 식 별로 합
        for(int i=0; i<sentence.length; i++){
            int tempSum = 0;

            String[] splitedNumForAdd = sentence[i].split("\\+");
            for(String s:splitedNumForAdd) {
                tempSum += Integer.parseInt(s);
            }

            // 첫 번째로 나뉘기 전까지 무조건 양수
            if(i==0) sum += tempSum;
            // 이후 빼기
            else sum -= tempSum;
        }

        System.out.println(sum);
    }
}
