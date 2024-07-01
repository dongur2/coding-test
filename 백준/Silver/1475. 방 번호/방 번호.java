import java.util.Scanner;

public class Main {
    /*
    * 조건 - 1세트: 0~9 (6<->9 가능)
    * 주어진 방번호를 완성하기 위해 *필요한 세트의 최소 개수* 리턴
    */
    public static void main(String[] args) {
        //1. 숫자별 필요 개수를 저장할 배열 생성: 인덱스 == 해당 숫자
        int[] nums = new int[10];

        //2. 방번호 입력
        Scanner sc = new Scanner(System.in);
        int room = sc.nextInt();

        //3. 방번호를 한글자씩 잘라 배열에 횟수 카운트
        while(room > 0) {
            nums[room % 10]++; // 숫자 카운트
            room /= 10;
        }

        //4. 6과 9는 서로 치환 가능: 인덱스 6,9 - 둘 차이가 2이상일 경우, 작은 쪽을 중간값까지 증가
        if (Math.abs(nums[6] - nums[9]) >= 2) {
            int middle = (nums[6] + nums[9]) / 2;
            if(nums[6] < nums[9]) nums[9] -= middle - nums[6];
            else nums[6] -= middle - nums[9];
        }

        //5. 필요한 세트 수 카운트: 카운트된 수 중 가장 큰 수가 최소값이 됨
        int cnt = 0;
        for(int i=0; i<10; i++) {
            cnt = Math.max(cnt, nums[i]);
        }

        System.out.println(cnt);
    }
}