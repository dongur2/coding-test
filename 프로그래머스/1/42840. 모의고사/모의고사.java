import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        //각 수포자의 정답 배열 생성 
        int[] ans1 = {1, 2, 3, 4, 5};
        int[] ans2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] ans3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        //각 수포자의 정답을 맞춘 개수를 저장할 배열 생성 [3]
        int[] cnt = new int[3];
        
        //주어진 정오표 배열의 인덱스를 이용해 첫 문제부터 마지막 문제까지 순회:
        for(int i=0; i<answers.length; i++) {
            //각 수포자가 정답을 맞췄는지 확인 - 인덱스%{각 수포자의 정답 배열 크기}로 접근
            //정답을 맞췄다면(정오표 배열값과 수포자 정답 배열값이 같을 경우) 정답 개수 저장 배열의 값을 증가
            if(answers[i] == ans1[i%5]) cnt[0]++;
            if(answers[i] == ans2[i%8]) cnt[1]++; 
            if(answers[i] == ans3[i%10]) cnt[2]++;
        }
        
        //가장 많이 맞춘 정답 개수 구하기
        int top = 0;
        for(int c:cnt) {
            top = Math.max(top, c);
        }
        
        //최다 정답 개수를 가진 인덱스를 배열에 저장하여 리턴
        List<Integer> list = new ArrayList<>();
        for(int i=0 ; i<3; i++) {
            if(top == cnt[i]) list.add(i+1);
        }
        
        return list.stream().mapToInt(l->l).toArray();
    }
}