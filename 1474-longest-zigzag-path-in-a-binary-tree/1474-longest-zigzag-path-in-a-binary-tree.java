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
 //지그재그로 갈 수 있는 가장 긴 거리 
class Solution {
    int cnt = 0;

    public int longestZigZag(TreeNode root) {
        dfs(root.left, true, 1);
        dfs(root.right, false, 1);

        return cnt;
    }

    private void dfs(TreeNode curr, boolean prevLeft, int path) {
        if(curr == null) return;

        cnt = Math.max(cnt, path);

        //현재가 왼쪽이면 다음은 오른쪽 - 경로 끊기면 1로 초기화 
        dfs(curr.right, false, prevLeft ? path + 1 : 1);
        //현재가 오른쪽이면 다음은 왼쪽
        dfs(curr.left, true, !prevLeft ? path + 1 : 1);
    }
}