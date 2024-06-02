import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    /*
    * ## 받을 수 있는 팁의 최댓값 출력 ##
    * 팁 = 원래 생각 - (받은 등수 - 1)
    * */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); // 사람 수
        Long[] tips = new Long[N];

        //원래 생각한 팁을 입력받아서 배열에 저장
        for(int i=0; i<N; i++){
            tips[i] = scanner.nextLong();
        }

        //내림차순 정렬
        Arrays.sort(tips, Comparator.reverseOrder());

        //팁 계산: 음수일 경우 0으로 처리
        long maxSum = 0;
        for(int i=0; i<tips.length; i++){
            maxSum += Math.max(tips[i]-i, 0);
        }

        System.out.println(maxSum);
    }
}
