import java.util.Scanner;

class Main {
    //n!
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int answer = factorial(n);
        System.out.println(answer);
    }

    public static int factorial(int num) {
        if(num == 0 || num == 1) return 1;
        return num * factorial(num-1);
    }
}