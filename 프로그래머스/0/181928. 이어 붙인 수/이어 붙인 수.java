class Solution {
    private int my(int[] num_list) {
        StringBuilder odd = new StringBuilder();
        StringBuilder even = new StringBuilder();
        
        for(int num:num_list) {
            if(num % 2 == 0) even.append(num);
            else odd.append(num);
        }
        
        return Integer.parseInt(odd.toString()) + Integer.parseInt(even.toString());
    }
    
    private int refer(int[] num_list) {
        int odd = 0;
        int even = 0;
        
        for(int num:num_list) {
            if(num % 2 == 0) {
                even *= 10;
                even += num;
            } else {
                odd *= 10;
                odd += num;
            }
        }
        
        return odd + even;
    }
    
    public int solution(int[] num_list) {
        return refer(num_list);
    }
}