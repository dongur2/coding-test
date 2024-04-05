class Solution {
    public int solution(String my_string) {
        String[] arr = my_string.split(" ");
        
        int answer = Integer.parseInt(arr[0]);
        int i=1;
        while(true) {
            if(arr[i].equals("+")) {
                answer += Integer.parseInt(arr[i+1]);
                i+=2;
                
            } else if(arr[i].equals("-")) {
                answer -= Integer.parseInt(arr[i+1]);
                i+=2;
                
            } else {
                i++;
            }
            
            if(i>=arr.length) break;
        }
        
        return answer;
    }
}