import java.util.*;

class Solution {
    int cnt = 0;
    
    public int solution(int[] numbers, int target) {
        Queue<Entry> q = new ArrayDeque<>();
        q.offer(new Entry(0, 0)); //처음 노드 숫자 0:레벨 0
        
        while(!q.isEmpty()) {
            Entry curNode = q.poll(); //방문한 노드
            
            //현재 노드가 마지막 레벨(가장 깊은 레벨)이라면 반복 중지
            if(curNode.level == numbers.length) break;
            
            //다음 노드가 마지막 레벨이라면 타겟 숫자와 비교하여 카운트
            if(curNode.level+1 == numbers.length) {
                if(curNode.num + numbers[curNode.level] == target
                   || curNode.num - numbers[curNode.level] == target) cnt++; 
            }
            
            //방문한 노드 +/-인덱스값 노드 2개를 대기열에 추가
            q.offer(new Entry(curNode.num + numbers[curNode.level], curNode.level+1));
            q.offer(new Entry(curNode.num - numbers[curNode.level], curNode.level+1));
        }
        return cnt;
    }
}

class Entry {
    int num, level;
    public Entry(int num, int level) {
        this.num = num;
        this.level = level;
    }
}