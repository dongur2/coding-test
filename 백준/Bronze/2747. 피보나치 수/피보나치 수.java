import java.util.*;

class Main {
    static int[] arr; //계산된 값 저장
    
    //n번째 피보나치 수
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        arr = new int[n+1];
        Arrays.fill(arr, -1);
        
        int answer = fibonacci(n);
        System.out.println(answer);
    }

    public static int fibonacci(int num) {
        if(num == 0) return 0;
        else if(num == 1) return 1;

        //이미 계산했던 값일 경우 즉시 리턴
        if(arr[num] > -1) return arr[num];

        //값을 저장하면서 리턴
        return arr[num] = fibonacci(num-2) + fibonacci(num-1);
    }
}