import java.util.*;

class Main {
    //각 자리수를 더한 숫자
    //주어진 수 오른쪽 + 더한 수 오른쪽
    //원래 수로 돌아오는 데 필요한 횟수?
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //1. 입력
        int n = sc.nextInt();

        int cnt = 0;
        int base = n, newNum = -1;
        while(true) {
            //2. 횟수 카운트
            cnt++;

            //3. 각 자리의 합
            int sum = 0;
            //한자리수일 경우 그대로
            if(base / 10 == 0) sum += base;
            //아닐 경우
            else sum += (base / 10) + (base % 10);

            //4. 새로운 수 만들기(원래 일의 자리 + 새로운 일의 자리)
            newNum = ((base % 10) * 10) + sum % 10;

            //5. 입력받은 수 == 새로운 수 비교
            //같으면 횟수 리턴
            if(n == newNum) {
                System.out.println(cnt);
                break;
            } else { //다르면 진행
                base = newNum;
                newNum = -1;
            }
        }
    }
}