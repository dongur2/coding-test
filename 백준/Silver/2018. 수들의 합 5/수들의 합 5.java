import java.util.Scanner;

//연속된 자연수의 합 구하기
public class Main {
    /*
    * 자연수 N을 만들 수 있는 연속된 자연수의 합 개수
    * (1 ≤ N ≤ 10,000,000)
    * 사용하는 자연수 <= N
    * 2초
    * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = 1;

        int N = sc.nextInt(); //자연수 N

        long sum = 1;
        int start = 1, end = 1;

        while(end < N) {
            if(sum < N) {
                end++; sum += end;
            } else if(sum > N) {
                sum -= start; start++;
            } else {
                cnt++;

                end++;
                sum += end;
            }
        }

        System.out.print(cnt);
    }
}
