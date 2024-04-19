// 정수 3개를 오름차순 정렬해 출력
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        
        int[] num = Arrays.stream(input.split(" "))
                    .mapToInt(Integer::parseInt).sorted().toArray();
        
        for(int n : num) {
            System.out.print(n + " ");
        }
        
    }
}