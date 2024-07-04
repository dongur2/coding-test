import java.util.*;

public class Main {
    /*
    * A,B,C 를 각각 수 & 문자열로 생각했을 때, A+B-C를 출력
    * 1~3줄 각각 정수 A,B,C (1<=n<=1000)
    * 첫줄에 수로 생각했을 때 결과, 둘째줄에 문자열로 생각했을 때 결과 출력
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        
        System.out.println(A+B-C);
        System.out.println(Integer.parseInt(""+A+B)-C);
    }
}