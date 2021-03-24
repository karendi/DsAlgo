/*
* 2 methods for improving linear search:
*
* transposition
  move to head
*
* ---> as long as you are traversing a linked list, you have to ensure or cater for null nodes that can be present.
  ---> ensure as you are traversing that your pointer is not null
  ---> when deleting a linked list node we should delete in from heap, in java we can do that by assigining that pointer to null
  ---> before performing any action on the node you have been traversing check that it is not null


*
*
* \*/

/*
        * so when evaluating an infix expression to postfix expression, which the compiler can easily evaluate in linear time
        --> you have the prescedence so we know : (+-) prescedence is 1, (*, /) prescedence is 2, (()) prescednec is 3,
        the higher the number the greater the prescedence
        -> so what we will do is create a postfix expression and then evaluate it, we will need stacks
        -> so to be able to create a postfix expression: any time we encounter an operand we add it to a string
        -> when we encounter an operator, we add it to the stack, on conditions:
            1. only if the stack is empty
            2. the element at the top is of less prescedence.
        -> if we encounter elements at the top of the stack are of greater or equal prescedence, then what we do is empty the stack
        adding it to the postfix expression
        ->
        * when creating postfix expression--> add the operators to the stack as long as the stack is empty and the element at
        the top has the less prescedence

        when evaluating a postfix expression---> we, add the operands to the stack. When we get an operator we pop two elements
        the left and right number and then we evaluate the result...then add the result to the stack again
        -> the answer will be the last number in the stack
        *
        *
        *
        *
        *
        *
        *
        *
        *
        *
*/
public static String createPostFixExpression(String expression){
    if(expression.length() <= 1){
        return expression;
    }
    
    ArrayDeque<Character> stack = new ArrayDeque<>();
    
    HashMap<Character, Integer> prescedence = new HashMap<>();
    
    prescedence.put('*', 2);
    prescedence.put('/', 2);
    prescedence.put('+', 1);
    prescedence.put('-', 1);
    
    StringBuilder answer = new StringBuilder();
    
    
    for(int i=0; i<expression.length(); i++){
        
        char currChar = expression.charAt(i);
        
        if(prescedence.containsKey(currChar)){
            
            if(stack.isEmpty()){
                
                stack.push(currChar);
                continue;
                
            } else {
                
                
                char top = stack.peek();
                
                if(prescedence.get(currChar) > prescedence.get(top)){
                    
                    stack.push(currChar);
                    
                    
                } else {
                    
                    while(!stack.isEmpty()){
                        
                        answer.append(stack.pop());
                        
                    }
                    
                    stack.push(currChar);
                    
                    
                }
                
                
            }
            
        
            
            
        } else {
            
            
            answer.append(currChar);
            
            
        }
        
    }
    
    while(!stack.isEmpty()){
        
        answer.append(stack.pop());
    }
    
    System.out.println(answer);
    
    
    
    return answer.toString();

}

public static int evaluatePostFixExpression(String expression){
    // when evaluating, we add the operands to the stack now
    // so when we encounter an operand we push it in the stack, when we encounter an operator, we pop two operands from the stack and the first operand popped 
    // will be the right number and the second number popped is the left number
    
    // then we evaluate leftNumber operator rightNumber.. then the result gotten we add it to the stack, evenutally our answer will be the number left in the stack
    // same prescedence operators in an expression, you can just evaluate the expression linearly
    
    if(expression.length() == 0){
        return -1;
    }
    
    ArrayDeque<Integer> stack = new ArrayDeque<>();
    
    int answer = 0;
    
    HashSet<Character> operators = new HashSet<>();
    operators.add('*');
    operators.add('/');
    operators.add('+');
    operators.add('-');
    
    
   
    
    for(int i=0; i < expression.length(); i++){
        
        char currChar = expression.charAt(i);
        
        if(!operators.contains(currChar)){
            
            stack.push(Character.getNumericValue(currChar));
            
        } else {
            
            int rightNumber = stack.pop();
            int leftNumber = stack.pop();
            System.out.println(".rightnumber....."+rightNumber);
            
            System.out.println("leftnumber...."+leftNumber);
            int result = 0;
            
            if(currChar == '*'){
                
                result = leftNumber * rightNumber;
                
            } else if (currChar == '/'){
                
                result = leftNumber / rightNumber;
                
            } else if(currChar == '+'){
                
                 result = leftNumber  + rightNumber;
                
            } else if (currChar == '-'){
                
                
                result = leftNumber - rightNumber;
                
                
            }
            
            System.out.println(result);
            
            stack.push(result);
            
            
    
            
            
        }
        
        
    }
    
    
    
    System.out.println(stack.peek());
    
    return stack.pop();
}
public static class ListNode{
    int data;
    ListNode next;
    
    ListNode(int data){
        this.data = data;
    }
}

public static class LinkedListDataStructure{
    
    public static ListNode head;
    
    LinkedListDataStructure(){
        this.head = null;
    }
    
    public static ListNode addData(int value, ListNode node){
        
        if(head == null){
            
            head = new ListNode(value);
            
        } else {
            
            ListNode currNode = head;
            
            ListNode prev = null;
            
            while(currNode != null){
                
                prev = currNode;
                
                currNode = currNode.next;
                
            }
            
            prev.next = new ListNode(value);
            
            prev.next.next = currNode;
            
        }

        
        
        return head;
        
        
    }
    
    public static void displayNodes(ListNode node, List<Integer> answer)
    {
        if(node == null){
            return;
        }
        
        answer.add(node.data);
        
        displayNodes(node.next, answer);
        
        
    }
    
    public static void insertAtIndex(int index, int value){
        
        if(head == null){
            
            head = new ListNode(value);
            
            System.out.println("Inserted at the beginning");
            
            return;
            
        }
        
        
        ListNode currNode = head;
        
        ListNode prevNode = null;
        
        int position = 1;
        
        while(position != index && currNode != null){
            
            prevNode = currNode;
            currNode = currNode.next;
            position += 1;
        }
        
        if(currNode != null){
            // this means that the index we have somewhere to go
            
            prevNode.next = new ListNode(value);
            
            prevNode.next.next = currNode;
            
        }
        

        
        
    }
    
}

public static class StackObject{
        
    private int inputValue;
    private int maxValueSoFar;
    private int minValueSoFar;
    
    //consructor
    StackObject(int inputValue, int maxValue, int minValue){
        this.inputValue = inputValue;
        this.maxValueSoFar = inputValue > maxValue ? inputValue : maxValue;
        this.minValueSoFar = inputValue < minValue ? inputValue : minValue;
    }
    
}


public static class MinMaxStack{
    
    // we need to keep track of the min and max value at each point of insertion
    // so what data structure would work?
    // an array that List< stackObject>
    
    private static List<StackObject> minMaxStack;
    private static int length;
    
    MinMaxStack(){
        
        //initializes the min max stack 
        // used a resizable array
        this.minMaxStack = new ArrayList<>();
        this.length=0; 
    }
    
    
    public static void push(int value){
        
         StackObject newObj; 
         
        // first check that our stack is empty or not
        if(length == 0){
            
            newObj = new StackObject(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
            
            minMaxStack.add(newObj);
            
            length += 1;
            
        } else {
            
            StackObject prevValues = minMaxStack.get(length-1);
            
            newObj = new StackObject(value, prevValues.maxValueSoFar, prevValues.minValueSoFar);
            
            minMaxStack.add(newObj);
            
            length+=1;
            
        }
    }
    
    
    public static int pop(){
        
        if(length == 0){
            
            return -1;
        }
        
        
        int lastIndex = length-1;
        
        int value = minMaxStack.get(lastIndex).inputValue;
        
        minMaxStack.remove(lastIndex);
        
        length-=1;
        
        return value;
        
    }
    
    
    public static int getMinValue(){
        
        if(length== 0){
            
            return -1;
        }
        
        int lastIndex = length-1;
        
        int value = minMaxStack.get(lastIndex).minValueSoFar;
        
        return value;
        
        
    }
    
    public static int getMaxValue(){
        
        if(length== 0){
            
            return -1;
        }
        
        int lastIndex = length-1;
        
        int value = minMaxStack.get(lastIndex).maxValueSoFar;
        
        return value;
    }
    
}
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        
        if(head ==  null){
            return head;
        }
        
        // we need to keep track of the nodes that have been created
        HashMap<Node, Node> oldToNewNodeMap = new HashMap<>();
        
        Node dummyNode = new Node(0);
        
        // pointer for traversing as we create the new clone
        Node current = dummyNode;
        
        // we are concered in making sure that we create a new node i.e the current head and link to the random pointer
        while(head != null){
            
            if(oldToNewNodeMap.containsKey(head)){
                
                current.next = oldToNewNodeMap.get(head);
                
            } else{
                // we create the new node and add it to our hashmap
                Node newNode = new Node(head.val);
                
                oldToNewNodeMap.put(head, newNode);
                
                current.next = oldToNewNodeMap.get(head);
                
            }
            
            // we populate the random pointer
            if(head.random != null){
                
                if(oldToNewNodeMap.containsKey(head.random)){
                    
                    oldToNewNodeMap.get(head).random =
                        
                        oldToNewNodeMap.get(head.random);
                    
                } else {
                    
                    Node newRandomNode = new Node(head.random.val);
                    
                    oldToNewNodeMap.put(head.random, newRandomNode);
                    
                    oldToNewNodeMap.get(head).random = newRandomNode;
                    
                    
                }
                
            }
        
        
            
            current = current.next;
            
            
            
            // advance the head pointer
            head = head.next;
        }
        
        return dummyNode.next;
        
        
    }
}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        
        int lengthA = getLength(headA);
        
        int lengthB = getLength(headB);
        
        int difference = Math.abs(lengthA-lengthB);
        
        ListNode currNode1 = headA;
        
        ListNode currNode2 = headB;
        
        
        while(currNode1 != null && currNode2 != null){
            
            if(difference != 0){
                

                char alteredNode = 'a';
                
                ListNode node = null;
                
                if(lengthA > lengthB){
                    
                    alteredNode = 'a';
                    node = currNode1;
                    
                } else {
                    
                    alteredNode = 'b';
                    node = currNode2;
                    
                }
                
                while(difference != 0){
                    
                    node = node.next;
                    difference -= 1;
                    
                }
                
                if(alteredNode == 'a'){
                    
                    currNode1 = node;
                    
                }else{
                    
                    currNode2 = node;
                }
 
            }
            
            
            if(currNode1 == currNode2){
                
                return currNode1;
            }
 
            currNode1 = currNode1.next;
            
            currNode2 = currNode2.next;

        }
        
        return null;
        
        
        
    }
    
    public int getLength(ListNode node){
        
        int count = 0;
        
        ListNode currNode = node;
        
        while(currNode != null){
            count += 1;
            currNode = currNode.next;
        }
        
        return count;
        
        
    }
}