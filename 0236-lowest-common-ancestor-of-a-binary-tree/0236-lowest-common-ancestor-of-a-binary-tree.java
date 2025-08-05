/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 //주어진 두개 노드의 가장 가까운 공통 조상 노드 
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root; //p/q 찾았으면 리턴 

        TreeNode left = lowestCommonAncestor(root.left, p, q); //왼쪽 트리에서 p/q 찾기 
        TreeNode right = lowestCommonAncestor(root.right, p, q); //오른쪽 트리에서 p/q 찾기
        
        //왼쪽과 오른쪽 모두에서 찾았으면 가장 가까운 조상 노드는 루트
        if(left != null && right != null) return root; 
        //한쪽에서만 찾았으면 -> 더 위에 있는 노드가 리턴될 것이므로, 찾은 쪽에서 리턴된 노드를 리턴 
        return left != null ? left : right;
    }
}