import java.util.*;

public class MyClass {
    public static void main(String args[]) {
      int x=10;
      int y=25;
      int z=x+y;

      System.out.println("Sum of x+y = " + z);
      
      LRU myclass = new LRU(4);
      
      myclass.insertCacheValue(1993, 27);
      myclass.insertCacheValue(1997, 23);
      myclass.insertCacheValue(1988, 32);
      myclass.insertCacheValue(1995, 26);
      myclass.insertCacheValue(1991, 29);
      myclass.insertCacheValue(1990, 30);

      
      
      System.out.println(myclass.getMostFrequentlyAccessedValue());
      
      System.out.println(myclass.getCacheValue(1993));
      
      System.out.println(myclass.cacheMap);
      
      

      
    }
    static class ListNode{
            
            int key;
            int value;
            ListNode prev;
            ListNode next;
            
            //constructor
            // how many times the key has been accessed
            ListNode(int key, int value){
                this.key = key;
                this.value = value;
                this.prev = null;
                this.next = null;
            }
        }
    
    static class LRU{
        private DoublyLinkedList cacheValues;
        private HashMap<Integer, ListNode> cacheMap;
        int maxSize;
        int currentSize;
        
        //constructor
        LRU(int maxSize){
            this.cacheValues = new DoublyLinkedList();
            this.cacheMap = new HashMap<>();
            this.maxSize = maxSize;
            this.currentSize = 0;
            
        }
        
        /*
        * insert values, so the hashmap will hold the values available in the cache
          and keep track of the least accessed value, and the recently accessed value
          --> tail and value of the doubly linked list
        *
        *
        */
        
        public void insertCacheValue(int key, int value){
            
            ListNode newNode = null;
            

            if(currentSize < maxSize){
                
                  // we should create the new node
                if(cacheMap.containsKey(key)){
                    replaceHeadValue(key)
                } else{
                    newNode = cacheValues.insertNode(key, value);
                
                    currentSize += 1;
                }

                
            } else {

                if(cacheMap.containsKey()){
                    replaceHeadValue(key)
                } else {
                    
                    // remove the least accessed cache
                    
                    ListNode tailValue = cacheValues.removeTailNode();
                    
                    cacheMap.remove(tailValue.key);
                    
                    newNode = cacheValues.insertNode(key, value);
                // the current size doesnot change since it is already at max capacity
                }

            }
            
            
            //update the key value pair
            this.cacheMap.put(key, newNode);
        }

        public void replaceHeadValue(int key){

            // get the node from the hashmap
            ListNode currentNode = cacheMap.get(key);

            ListNode currentHead = this.cacheValues.head;

            ListNode prevNode = currentNode.prev;
            ListNode nextNode = currentNode.next;

            prevNode.next = nextNode;
            nextNode.prev = prevNode;

            currentNode.next = currentHead;
            currentHead.prev = currentNode;

            this.cacheValues.head = currentNode;
        }
    
        
        public ListNode getMostFrequentlyAccessedValue(){
            
            ListNode answer = null;
            
            if(this.cacheMap.size() == 0){
                return answer;
            } else {
                
                answer = cacheValues.peekHeadValue();
            
                
                return answer;
            }
            
        }
        
        
        public HashMap<Integer, Integer> getCacheValue(int key){
            
            HashMap<Integer, Integer> answer = new HashMap<>();
            
            if(this.cacheMap.size() == 0){
                return answer;
            } else {
                
                if(!cacheMap.containsKey(key)){
                    
                    return answer;
                    
                } else{
                    
                    int cacheValue = cacheMap.get(key).value;
                    
                    answer.put(key, cacheValue);
                    
                    return answer;
                    
                }
                
            }
        }
    }
    
    
    static class DoublyLinkedList{
        
        private ListNode head;
        private ListNode tail;
        
        //constructor class
        DoublyLinkedList(){
            this.head = null;
            this.tail = null;
        }
        
        
        public ListNode insertNode (int key, int value) {
            
            ListNode newNode = new ListNode(key, value);
            
            
            if(head == null){
                this.head = newNode;
                this.tail = newNode;
                
                
            } else {
                // when we add a new node, it will be placed in the head, take constant time
                ListNode currHead = head;
                
                newNode.next = currHead;
                
                currHead.prev = newNode;
                
                this.head = newNode;
                
                
            }
            
            return newNode;
            
        }
        
        
        public ListNode removeTailNode(){
            
            ListNode answer = null;
            
            if(this.tail == null){
                System.out.println("There are no nodes avaialable!");
                return answer ;
            }
            
            ListNode currTail = this.tail;
         

            
            // here we have two cases, where the tail node is the only node in the linked list 
            // and when there are more nodes in the linked list
            
            if(currTail.prev == null){
                // this means that it is the the head and the tail, the only node in the linked list
                this.tail = null;
                this.head = null;
            
                
            } else {
                
                // we point the prevNode to be the tail

                ListNode tailValue = this.tail;
                
                this.tail = this.tail.prev;
                
                this.tail.next = null;

                tailValue = null;

                

            }

            
            return currTail;

            
        }
        
        public ListNode peekHeadValue(){
            
            
            if(this.head == null){
                return null;
            }
            
            
            return this.head;
        }
        
        
        public ListNode peekTailValue(){
            
            
            if(this.tail == null){
                return null;
            }
            
            
            return this.tail;
        }
        
        
        
    }
}