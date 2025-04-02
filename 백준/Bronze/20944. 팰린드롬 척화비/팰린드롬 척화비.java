import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        //중간
        int mid = n/2;

        //앞
        StringBuilder prefix = new StringBuilder();

        for(int i=0; i<mid; i++) {
            prefix.append('a');
        }

        //최종 단어
        StringBuilder answer = new StringBuilder();
        answer.append(prefix); //앞 붙이고

        if(n%2 == 1) answer.append('a'); //홀수길이일 경우 중간 글자 추가

        answer.append(prefix.reverse()); //앞문자열을 뒤집어서 최종 단어에 추가

        System.out.println(answer.toString());
    }
}