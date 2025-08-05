/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
 //양쪽 리프노드 순서 같음 
class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> left = new ArrayList<>(); 
        List<Integer> right = new ArrayList<>();

        dfs(root1, left);
        dfs(root2, right);

        //리프노드 개수 다르면 아님 
        if(left.size() != right.size()) return false;

        //순서 다르면 아님 
        for(int i=0; i<left.size(); i++) {
            if(!left.get(i).equals(right.get(i))) return false;
        }    
        return true;
    }

    private void dfs(TreeNode curr, List<Integer> list) {
        if(curr == null) return;

        //리프 노드일 경우 데이터 추가하고 중지 
        if(curr.left == null && curr.right == null) {
            list.add(curr.val); return;
        }
        
        dfs(curr.left, list);
        dfs(curr.right, list);
    }
}