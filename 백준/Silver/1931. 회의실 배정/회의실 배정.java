import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /*
    * ## 회의 최대 개수
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Integer> map = new HashMap<>();

        //1. 회의 수 입력
        int N = Integer.parseInt(br.readLine());

        //2. 시작/종료 시간을 2차원 배열에 저장
        int[][] table = new int[N][2];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            table[i][0] = Integer.parseInt(st.nextToken()); // start
            table[i][1] = Integer.parseInt(st.nextToken()); // end
        }

        /* 3. 종료시간 기준으로 오름차순 정렬: 빨리 끝나는 회의순 */
        Arrays.sort(table, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) return o1[0] - o2[0]; // 종료가 같을 경우, 시작이 빠른 순
                return o1[1] - o2[1];
            }
        });

        //4. 회의 카운트: 이전 회의 종료 시간 <= 다음 회의 시작
        int cnt = 0;
        int preEnd = 0;

        for(int i = 0; i < N; i++) {
            if(preEnd <= table[i][0]) {
                preEnd = table[i][1];
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
