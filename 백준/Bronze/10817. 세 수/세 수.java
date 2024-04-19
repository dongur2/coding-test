// 공백으로 구분되어 주어지는 정수 3개 중 두 번째로 큰 정수를 출력
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int[] nums = Arrays.stream(input.split(" "))
                     .mapToInt(Integer::parseInt).sorted().toArray();
        System.out.println(nums[1]);
    }
}