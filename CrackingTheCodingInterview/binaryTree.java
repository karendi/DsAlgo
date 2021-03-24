 public static class TreeNode{
        
        int nodeValue;
        TreeNode right;
        TreeNode left;
        
        TreeNode(int nodeValue){
            this.nodeValue = nodeValue;
            this.right = null;
            this.left = null;
        }
    }
    
    public static class BinarySearchTree{
        
        public static TreeNode root;
        
        BinarySearchTree(){
            
            this.root = null;
            
        }
        
        
        
        public static void insertValueRecursive(int newValue){
            
            TreeNode currentNode = root;
            
            helperMethodRecursive(newValue, currentNode);
            
            
        }
        
        public static TreeNode helperMethodRecursive(int newValue, TreeNode node){
            
            if(node == null){
                return new TreeNode(newValue);
            }
            
            if(node.nodeValue > newValue){
                node.left = helperMethodRecursive(newValue, node.left);
            }
            
            if(node.nodeValue < newValue){
                node.right = helperMethodRecursive(newValue, node.right);
            }
            
            return node;
            
        }
        
        
        public static TreeNode helperMethodArrayRecursive(int[] arr, int start, int end){
            if(start > end){
                return null;
            }
            
            
            
            
            int mid =  start == end? start : (start +end)/2;
            
            TreeNode newNode = new TreeNode(arr[mid]);
      
            
            
            newNode.left = helperMethodArrayRecursive(arr, start, mid-1);
            
            newNode.right = helperMethodArrayRecursive(arr, mid+1, end);
            
            return newNode;
            
        }
        
        public static void printTreeInOrder(){
            
            List<Integer> nodes = new ArrayList<>();
            
            TreeNode currentNode = root;
            
            ArrayDeque<TreeNode> stack = new ArrayDeque<>();
            
            
            
            
            while(currentNode != null || !stack.isEmpty()){
                
                while(currentNode != null){
                    stack.push(currentNode);
                    currentNode = currentNode.left;
                }
                
                
        
                TreeNode currNode = stack.pop();
                
           
                
                nodes.add(currNode.nodeValue);
                
                if(currNode.right != null){
                    currentNode = currNode.right;
                }
                
                
            }
       
            System.out.println(nodes);
        }
        
        
    }
     public static List<LinkedList<Integer>> getLinkedListDepth(){
            
            List<LinkedList<Integer>> answer = new ArrayList<>();
            
            if(root == null){
                return answer;
            }
            
            helperMethod(root, 0, answer);
            
            return answer;
            
        }
        
        public static void helperMethod(TreeNode root, int depth, List<LinkedList<Integer>> answer){
            if(root == null){
                return;
            }
            
            if(answer.size() <= depth){
                
                LinkedList<Integer> newList = new LinkedList<>();
                answer.add(newList);
                
            }
            
            answer.get(depth).add(root.nodeValue);
            
            helperMethod(root.left, depth+1, answer);
            
            helperMethod(root.right, depth+1, answer);
        }
        
        
        public static boolean balancedBinaryTree(TreeNode root){
            // this is where the depth does not differ by more than 2 btwn the subtrees of any node in the tree
            
            if(root == null){
                return true;
            }
            
            
            int left = helperMethod2(root.left);
            
            int right = helperMethod2(root.right);
            
            
            if( Math.abs(left-right) <= 1 &&
                balancedBinaryTree(root.left) &&
                balancedBinaryTree(root.right)
            ){
                return true;
            }
            
        
            
            return false;
        }
        
        public static int helperMethod2(TreeNode node){
            
            if(node == null){
                
                return 0;
                
            }
            
            
            return 1+ Math.max(helperMethod2(node.left), helperMethod2(node.right));
            
            
            
        }


        // we should be able to check that each of the nodes are balanced and if they are not then we should bubble up without checking back to the root and say we are not balanced
        public static int balancedTreeOptimal(TreeNode node){

            if(node == null){
                return -1;
            }

            int leftHeight = balancedTreeOptimal(node.left);

            if(leftHeight == Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }

            int rightHeight = balancedTreeOptimal(node.right);

            if(rightHeight == Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            } //this is sort of an error code that will bubble up till the top of the node

            int difference = Math.abs(rightHeight-leftHeight);

/
            if(difference > 1){
                // where our error code originates
                return Integer.MIN_VALUE;

            } else {
                
                return 1 + Math.max(leftHeight, rightHeight);
            }


        }

        public static boolean balancedTreeOptimal2(){

            return balancedTreeOptimal != Integer.MIN_VALUE;

        }

        /*
        * when it comes to checking for a valid BST what we should do is :
        There are 2 methods that come to min
        one , do an inorder traversal and keep  track of the minvalue which gets updated everytime we pop out a node from
        the stack.
        So if we ever find that the node value is less than or equal to in the case where our binary tree  does not contain duplicates
        we should return false
        *

        The recursive method we will have to do it twice check : pass in the lower and higher values, then check that that subtree is also valid
        so call the recursive method again, this is the same as checking for a balanced tree. we check yes that the current subtree is balanced then
        we recursively check that the other subtress are balanced too

        * When looking for an inorder successor of a binary tree:
        1. Where the right subtree is not null, then we should get the left most child of the right subtree. It will look something like this:
        TreeNode node = currentNode.right;

        while(node.left != null){
            node = node.left;
        }

        return node;

        2. We should check the parent. So the successor will be, travel up the parent pointer until you see a node which is the left child of its parent.
        The parent of such a node will be the successor. Because we will need to visit that node first and then explore the left child.

        TreeNode node = currentNode;

        // we are moving as long as the parents we are seeing have the current node as the right subtree, meaning we have already visited them!


        while(node != null && node.parent.right == node){
            node = node.parent;
        }

        retunr node.parent;
        *
        *
        *
        **/

        /*
        
        * check if one tree is the subtree of another tree:
        1.apply postorder traversal -> gets a copy of the tree and keep track of the null nodes in the tree,
        ]so get the string version of both trees and check if string 1 is a substring of string 2. takes extra space

        *
        *
        *
        *
        * */
        public boolean isSubTree(TreeNode tree1, TreeNode tree2){
            if(tree2 == null){
                return true; // a null node is a subtree of any tree
            }

            StringBuilder string1 = new StringBuilder();
            StringBuilder string2 = new StringBuilder();

            getString(tree1, string1);
            getString(tree2, string2);

            return string2.indexOf(string1) >= 0;


        }


        public void StringBuilder getString(TreeNode node, StringBuilder str){
            if(node == null){
                str.append("X");
                return;
            }

            // preorder traversal Root, Left, Right
            str.append(node.data);

            getString(node.left, str);
            getString(node.right, str);
        }


        public boolean checkSubstree(TreeNode tree1, TreeNode tree2){
            if(tree2 == null){
                return true;
            }

            return isSubtree(tree1, tree2);
        }

        public boolean isSubTree(TreeNode tree1, TreeNode tree2){
            if(tree1 == null){
                return false;
                // the big tree is false and there is no way we can get the second tree
            }

//check if the tree is at the root node
            if(tree1.data == tree2. data && treeMatch(tree1, tree2)){
                return true;
            }

            // else check if the tree will be i n the left or right subtree
            return isSubTree(tree1.left, tree2) || isSubTree(tree2.right, tree2);
        }

        public boolean treeMatch(TreeNode tree1, TreeNode tree2){
            if(tree1 == null && tree2 == null){
                return true;
            }

            if(tree1.data != tree2.data){
                return false;
            }

            if(tree1.data == tree2.data && treeMatch(tree1.left, tree2.left) && treeMatch(tree2.right, tree1.right)){
                return true;
            }

            return false;
        }