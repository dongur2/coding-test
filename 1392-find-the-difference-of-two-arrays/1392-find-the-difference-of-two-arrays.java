import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
/*
    인덱스 0으로 시작하는 숫자 배열 nums1, nums2가 주어졌을 때, 2개 리스트로 이루어진 정답 리스트를 반환할 것
    answer[0]: nums2에 포함되지 않고 중복되지 않는 nums1의 숫자로 이루어진 리스트 
    answer[1]: nums1에 포함되지 않고 중복되지 않는 nums2의 숫자로 이루어진 리스트 
    각 리스트 원소의 순서는 상관 없음 
 */
class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> answer = new ArrayList<>();
        answer.add(new ArrayList<>()); answer.add(new ArrayList<>());

        //중복 비허용
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for(int n:nums1) {
            set1.add(n);
        }
        for(int n:nums2) {
            set2.add(n);
        }

        //상대 배열에 포함되지 않는 수 
        set1.forEach(s -> {
            if(!set2.contains(s)) answer.get(0).add(s);
        });
        set2.forEach(s -> {
            if(!set1.contains(s)) answer.get(1).add(s);
        });

        return answer;
    }
}