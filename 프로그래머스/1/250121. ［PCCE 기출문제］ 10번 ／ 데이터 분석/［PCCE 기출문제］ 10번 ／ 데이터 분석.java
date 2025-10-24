//주어진 데이터 중 조건을 만족하는 데이터만 정렬
import java.util.List; import java.util.ArrayList; import java.util.Arrays;
class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        //주어진 대상 컬럼의 인덱스
        int idx = -1;
        if(ext.equals("code")) idx = 0; //코드
        else if(ext.equals("date")) idx = 1; //제조일
        else if(ext.equals("maximum")) idx = 2; //최대 수량
        else idx = 3; //현재 수량 
        
        //조건: 대상 컬럼 ext이 val_ext보다 작은 데이터
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<data.length; i++) {
            int target = data[i][idx];
            if(target < val_ext) list.add(i);
        }
        
        //sort_by 컬럼 기준 오름차순 정렬
        final int sortIdx;
        if(sort_by.equals("code")) sortIdx = 0; //코드
        else if(sort_by.equals("date")) sortIdx = 1; //제조일
        else if(sort_by.equals("maximum")) sortIdx = 2; //최대 수량
        else sortIdx = 3; //현재 수량 
        
        int[][] answer = new int[list.size()][data[0].length];
        for(int i=0; i<list.size(); i++) {
            int curr = list.get(i);
            for(int j=0; j<data[0].length; j++) {
                answer[i][j] = data[curr][j];
            }
        }
        
        Arrays.sort(answer, (o1, o2) -> o1[sortIdx]-o2[sortIdx]);
        
        return answer;
    }
}