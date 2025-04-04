import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int answer = fibonacci(n);
        System.out.println(answer);
    }

    public static int fibonacci(int num) {
        //0, 1일땐 그대로 리턴
        if(num == 0) return 0;
        else if(num == 1) return 1;

        return fibonacci(num-1) + fibonacci(num-2);
    }
}