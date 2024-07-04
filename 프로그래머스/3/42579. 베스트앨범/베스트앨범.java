import java.util.*;
import java.util.stream.*;

class Solution {
    /*
    * 장르별 최다 재생 노래 2개씩 모은 앨범에 수록된 각 노래의 고유 번호를 순서대로 리턴
    */
    public int[] solution(String[] genres, int[] plays) {
        //1. 장르별 총 재생횟수 계산 -> Map<String(장르), Integer(총 재생횟수)>
        Map<String, Integer> wholePlayMap = new HashMap<>();
        for(int i=0; i<genres.length; i++) {
            wholePlayMap.put(genres[i], wholePlayMap.getOrDefault(genres[i], 0) + plays[i]);
        } //pop: 3100, classic: 1450
        
        //2. 장르를 총 재생횟수 기준으로 내림차순 정렬하여 리스트로 변환
        List<String> genresList = wholePlayMap.keySet().stream()
            .sorted((g1, g2) -> wholePlayMap.get(g2).compareTo(wholePlayMap.get(g1)))
            .collect(Collectors.toList()); // pop, classic
        
        /*
        * 3.
        * 장르별로 {고유번호:재생횟수} 맵을 만들어서 
        * Map<String, Map<Integer, Integer>>에 키를 장르로 해서 값을 맵으로 넣기
        */
        Map<String, Map<Integer, Integer>> genreWithSongsMap = new HashMap<>();
        for(String genre:genresList) {
            Map<Integer, Integer> map = new HashMap<>();
            
            // 현재 반복문으로 돌고있는 장르라면, {고유번호, 재생횟수}를 map에 추가
            for(int i=0; i<genres.length; i++) {
                if(genres[i].equals(genre)) map.put(i, plays[i]);
            }
            
            // {장르: {고유번호:재생횟수}}
            genreWithSongsMap.put(genre, map);
        }
        
        /*
        * 4. 
        * 장르별로 앨범에 수록할 노래 선정
        */
        List<Integer> album = new ArrayList<>();
        for(String genre:genresList) {
            Map<Integer, Integer> map = genreWithSongsMap.get(genre);
            
            map.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue())
                .limit(2)
                .map(entry -> album.add(entry.getKey()))
                .collect(Collectors.toList());
        }
        
        return album.stream().mapToInt(n->n).toArray();
    }
}