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
 //노드의 값을 더했을 때 타겟이 되는 개수 
class Solution {
    int cnt = 0;

    public int pathSum(TreeNode root, int targetSum) {
        if(root == null) return cnt;

        dfs(root, targetSum, 0);
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);

        return cnt;    
    }

    private void dfs(TreeNode curr, int target, long sum) {
        if(curr == null) return;
        
        sum += curr.val;

        if(target == sum) cnt++;

        dfs(curr.left, target, sum);
        dfs(curr.right, target, sum);
    }
}