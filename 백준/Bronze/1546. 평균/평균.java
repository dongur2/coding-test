import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

//        점수 중 최댓값을 M, 모든 점수를 점수/M*100으로 수정
//        예를 들어, 세준이의 최고점이 70이고, 수학점수가 50이었으면 수학점수는 50/70*100이 되어 71.43점이 된다.
//        세준이의 성적을 위의 방법대로 새로 계산했을 때, 새로운 평균을 구하는 프로그램을 작성하시오.

//        첫째 줄에 시험 본 과목의 개수 N이 주어진다. 이 값은 1000보다 작거나 같다.
//        둘째 줄에 세준이의 현재 성적이 주어진다. 이 값은 100보다 작거나 같은 음이 아닌 정수이고, 적어도 하나의 값은 0보다 크다.

        int N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());

        StringTokenizer score = new StringTokenizer(br.readLine());
        float M = 0; // 최대값
        float scores[] = new float[N]; // 점수 저장할 배열
        for (int i = 0; i < N; i++) {
            scores[i] = Integer.parseInt(score.nextToken());
            if (M < scores[i]) { // 비교해서 최대값 설정
                M = scores[i];
            }
        }

        // 최대값이 나왔으니 이제 조작해주면서 평균 구해주기
        float sum = 0;
        for (int i = 0; i < N; i++) {
            scores[i] = (scores[i] / M) * 100; // 조작한 점수로 다시 배열에 넣어주기
            sum += scores[i]; // 평균을 위한 합
        }

        float avg = sum / N; // 평균 구하기

        bw.write(avg + "");

        bw.close();
        br.close();

    }
}
