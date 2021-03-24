import java.util.*;

public class MyClass {
    public static void main(String args[]) {
      int x=10;
      int y=25;
      int z=x+y;

      System.out.println("Sum of x+y = " + z);
      
      TrieDataStructure newTrie = new TrieDataStructure();
      
      String[] words = new String[]{"AND", "BOOL", "BONFIRE", "CATCH", "CASE", "CHAR"};
      
      for(String word: words){
          newTrie.addWord(word);
      }
      
      newTrie.searchUniquePrefix();
    }
    
    public static class TrieNode{
        
        private char currChar;
        private TrieNode[] children;
        private int frequency;
        
        TrieNode(char currChar){
            
            this.currChar = currChar;
            this.children = new TrieNode[26];
            this.frequency = 1;
        }
        
        public void increaseFreq(){
            this.frequency += 1;
        }
    }
    
    public static class TrieDataStructure{
        private static TrieNode root;
        
        TrieDataStructure(){
            this.root = new TrieNode('\0');
        }
        
        
        public static void addWord(String word){
            if(word.length() == 0){
                return;
            }
            
            TrieNode currentNode = root;
            
            for(int i=0; i<word.length(); i++){
                char currChar = word.charAt(i);
                
                int diff = currChar - 'A';

                
                if(currentNode.children[currChar - 'A'] == null){
                    
                    TrieNode newNode = new TrieNode(currChar);
                    
                    currentNode.children[currChar-'A'] = newNode;
                    
                    currentNode = currentNode.children[currChar - 'A'];
                    
                } else{
                     currentNode = currentNode.children[currChar-'A'];
                     
                     currentNode.increaseFreq();
                    
                }
                
            }
        }
        
        public static List<String> searchUniquePrefix(){
            
            List<String> answer = new ArrayList<>();
            
            helperMethod(answer, root, new StringBuilder());
            
            return answer;
           

        }
        
        public static void helperMethod(List<String> answer, TrieNode node, StringBuilder currString)
        {
            if(node == null){
                return;
            }
            
 
            // what are the base cases?
            if(node.frequency == 1 && currString.length() >= 1){
                
                answer.add(currString.toString());
 
            } else {
                
                
                for(int i=0; i<node.children.length; i++){
                    
                    TrieNode currNode = node.children[i];
                    
                    if(currNode != null){
                        
                        currString.append(currNode.currChar);
                        
                        helperMethod(answer, currNode, currString);
                        
                        currString.deleteCharAt(currString.length()-1);
                        
                    }
                    
                }
                
            }
            
        }

        public static List<String> findUniqueKCharactersSubstring(String str, int k){
        
            List<String> answer = new ArrayList<>();
            
            if(str.length() == 0 || str.length() < k){
                return answer;
            }
            
            // we can store the unique characters in a hashset
            int[] charMap = new int[26];
            
            // what we can do is keep a pointer from the first index that we added the character to the hashset
            int leftPointer = 0;
            
            int index = 0;
       
            // O(n) --> constant time added O(26n)
            while(index < str.length() || uniqueChars(charMap) == k){
                
                
                if(uniqueChars(charMap) == k){
                    
                    int end = index > str.length() ? str.length() : index;
                    
                    String newSubstring = str.substring(leftPointer, index);
                    
                    char leftChar = str.charAt(leftPointer);
                    
                    charMap[leftChar - 'a']--;
                    
                    leftPointer += 1;
                    
                    answer.add(newSubstring);
                }
                
                if(index < str.length()){
                    char currChar = str.charAt(index);
                    
                    charMap[currChar - 'a']++;
                    
                    index++;
                }
            }
            
            System.out.println(answer);
            
            return answer;
            
        }
        
        public static int uniqueChars(int[] charMap){
            
            int numberOfUniqueChars = 0;
            
            for(int i=0; i<charMap.length; i++){
                
                if(charMap[i] > 0){
                    numberOfUniqueChars+=1;
                }
                
            }
            
            return numberOfUniqueChars;
        }

        public static int minDeletionsToMakePalindrome(String str){
            if(str.length() <= 1){
                return 0;
            }
            
            // we create a two dp length that will keep track of indices
            //e.g i--->j will rep the string indices
            // when it comes to palindromes we expand from the centre
            // meaning left++right--
            
            int[][] dp = new int[str.length()][str.length()];
            
            for(int i=str.length()-1; i>=0; i--){
                for(int j=i; j <= str.length()-1; j++){
                
                    
                    if(str.charAt(i) == str.charAt(j)){
                        
                        if(i == j){
                            
                            dp[i][j] = 1;
                            
                            
                        } else {
                            
                            dp[i][j] = dp[i+1][j-1] + 2;
                            
                        }
                        
    
                        
                    } else {
                        
                        dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                        
                    }
                    
                }
            }
            
            
            int longestPalindromicSubstring = dp[0][str.length()-1];
            
            return str.length() - longestPalindromicSubstring;
            
        }
        
        
    }
}