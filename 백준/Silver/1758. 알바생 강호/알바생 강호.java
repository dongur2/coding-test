import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    /*
    * ## 받을 수 있는 팁의 최댓값 출력 ##
    * 팁 = 원래 생각 - (받은 등수 - 1)
    *
    * - 메모리: 104896 KB, 시간: 976 ms
    * ** Scanner -> BufferedReader 변경
    * ** 팁이 음수가 될 경우 반복문 멈춤 (그 뒤로도 어차피 음수)
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 사람 수
        Integer[] tips = new Integer[N]; // 각 팁은 100,000보다 작으므로 Integer (합은 Long)

        //원래 생각한 팁을 입력받아서 배열에 저장
        for(int i=0; i<N; i++){
            tips[i] = Integer.parseInt(br.readLine());
        }

        //내림차순 정렬
        Arrays.sort(tips, Comparator.reverseOrder());

        //팁 계산: 음수일 경우 0으로 처리
        long res = 0;
        for(int i=0; i<N; i++){
            int tip = tips[i] - i;

            if(tip < 0) break;
            res += tip;
        }

        System.out.println(res);
    }
}
