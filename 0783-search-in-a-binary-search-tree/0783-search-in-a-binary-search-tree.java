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
 //val을 루트로 시작하는 트리의 루트 노드 리턴 
class Solution {
    TreeNode res = null;

    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null) return null;
        search(root, val);
        return res;
    }

    private void search(TreeNode curr, int val) {
        if(curr == null) return;
        if(curr.val == val) res = curr;

        search(curr.left, val);
        search(curr.right, val);
    }
}