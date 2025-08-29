/*
    >>> 큐가 비어있으면 [0,0], 비어있지 않으면 [최대,최소] 반환
    
    <이중 우선순위 큐> --> 힙(트리)로 내림차순(max heap) 
    1. | 숫자: 삽입
    2. D 1: 최댓값 삭제 -> 맨 앞 삭제
    3. D -1: 최솟값 삭제 -> 맨 뒤 삭제 
*/
import java.util.TreeSet;
class Solution {
    public int[] solution(String[] operations) {
        TreeSet<Integer> heap = new TreeSet<>((o1,o2) -> o2 - o1); //내림차순 힙
        
        for(String o : operations) {
            String[] splited = o.split(" ");
            
            if(splited[0].equals("I")) heap.add(Integer.parseInt(splited[1]));
            else {
                if(heap.isEmpty()) continue;
                if(splited[1].equals("1")) heap.pollFirst();
                else heap.pollLast();
            }
        }
        
        return heap.isEmpty() ? new int[] {0, 0} : new int[] {heap.first(), heap.last()};
    }
}