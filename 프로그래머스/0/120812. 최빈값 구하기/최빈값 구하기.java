import java.util.*;

class Solution {
//     * ArrayList 풀이
//     private int checkMaxAndChangeAnswer(int[] array, List<Integer> answer, int max, int count, int index) {
//         if (max == 0 || max == count) {
//             max = count;
//             answer.add(array[index]);
            
//         } else if(max < count) {
//             max = count;
//             answer.clear();
//             answer.add(array[index]);
//         } 
        
//         return max;
//     }
    
//     public int solution(int[] array) {
//         if(array.length == 1) {
//             return array[0];
//         }
        
//         List<Integer> answer = new ArrayList<>();
        
//         int max = 0;
//         int count = 1;
        
//         Arrays.sort(array);
    
//         for(int i=1; i<array.length; i++) {
//             if(array[i-1] == array[i]) {
//                 count++;
                
//                 if(i == array.length - 1)  {
//                     max = checkMaxAndChangeAnswer(array, answer, max, count, i);
//                 }
                
//             } else {
//                 max = checkMaxAndChangeAnswer(array, answer, max, count, i-1);
//                 count = 1;
//             }
//         }
        
//         if(answer.size() == 1) {
//             return answer.get(0);
//         } else {
//             return -1;
//         }
//     }
    
//      * Map 풀이: getOrDefault(key, defaultValue)
        public int solution(int[] array) {
            Map<Integer, Integer> countMap = new HashMap<>();
            
            int answer = 0;
            int max = 0;
            
            for(int number : array) {
                int count = countMap.getOrDefault(number, 0) + 1;
                
                if(max < count) {
                    max = count;
                    answer = number;
                                       
                } else if (max == count) {
                    answer = -1;
                }
                
                countMap.put(number, count);
            }
            
            return answer;
        }
}