public class MyClass {
    public static void main(String args[]) {

      
      TrieDataStructure newTrie = new TrieDataStructure();
      newTrie.insertWord("sharon");
      newTrie.insertWord("leon");
      System.out.println(newTrie.search("leon"));
    }
    
    public static class TrieDataStructure{
        TrieNode root; // this is the dummy node for our trie data structure
        
        //constructor
        TrieDataStructure(){
            this.root = new TrieNode('\0', false);
        }
        
        public void insertWord(String str){
            if(str.length() == 0){
                return;
            }
            
            char[] charArray = str.toCharArray();
            
            TrieNode current = this.root;
            
            for(int i=0; i<charArray.length; i++){
                char currentChar = charArray[i];
                // position of this character in the children of the root can be found by int[currentChar - 'a']

                if(current.children[currentChar - 'a'] == null){
                    // this means that it doesnot exists
                    TrieNode newNode;
                    
                    if(i == charArray.length-1){
                        newNode = new TrieNode(currentChar, true);
                    } else {
                        newNode = new TrieNode(currentChar, false);
                    }
                    
                    current.children[currentChar-'a'] = newNode;
                    
                }
                
                // advance the current pointer
                current = current.children[currentChar-'a'];
                
            }
        }
        
        public boolean search(String s){
            if(s.length() == 0){
                return false;
            }
            
            TrieNode searchString = helperMethod(s);
            
            return searchString != null && searchString.endOfWord;
        }
        
        
        public boolean startsWith(String prefix){
            if(prefix.length() == 0){
                return false;
            }
            
            TrieNode searchPrefix = helperMethod(prefix);
            
            return searchPrefix != null;
            
        }
        
        public TrieNode helperMethod(String s){
            TrieNode current = this.root;
            
            for(int i=0; i<s.length(); i++){
                
                char currentChar = s.charAt(i);
                
                if(current.children[currentChar-'a'] == null){
                    return null;
                }
                
                current = current.children[currentChar-'a'];
                
            }
            
            return current;
        }
        
        
        class TrieNode{
            
            private char nodeChar;
            private TrieNode[] children;
            private boolean endOfWord;
            //constructor
            TrieNode(char nodeChar, boolean endOfWord){
                this.nodeChar = nodeChar;
                this.endOfWord = endOfWord;
                this.children = new TrieNode[26];
            }
        }
    }
}
public static class SuffixTree{
        
        static TrieNode root;
        
        SuffixTree(String newString){
            // initialize it with an empty character
            this.root = new TrieNode();
            
            insertFromIndex(0, newString);
        }
        
        
        public static void insertFromIndex(int index, String string){
            
            TrieNode currNode = root;
            
            for(int i=0; i<string.length(); i++){
                
                char currentChar = string.charAt(i);
                
                if(!currNode.children.containsKey(currentChar)){
                    currNode.children.put(currentChar, new TrieNode());
                }
                
                TrieNode newNode = currNode.children.get(currentChar);
                
                for(int j=i+1; j<string.length(); j++){
                    
                    if(!newNode.children.containsKey(string.charAt(j))){
                        newNode.children.put(string.charAt(j), new TrieNode());
                    }
                    
                    if(j == string.length()-1){
                        newNode.children.get(string.charAt(j)).isEnd = true;
                    
                    
                }
                
            }
            
            
            
        }}
        
        public static boolean searchWord(String searchString){
            if(searchString.length() == 0){
                return false;
            }
            
            TrieNode currNode = root;
            
            for(int i=0; i<searchString.length(); i++){
                char currLetter = searchString.charAt(i);
                
                System.out.println("current node...."+currLetter);
                
                
        
                
                System.out.println("------------->");
                
                if(!currNode.children.containsKey(currLetter)){
                    
                    System.out.println("----------"+currLetter);
                    return false;
                }
                
                currNode = currNode.children.get(currLetter);
            }
            
            // System.out.println("--------------->");
            // System.out.println(currNode);
            
            return currNode.isEnd;
        }
        
    }
    
    
    
    public static class TrieNode{
        
        // basically it is a hash map, where we store characters 
        HashMap<Character, TrieNode> children;
        boolean isEnd;
        
        TrieNode(){
            this.children = new HashMap<>();
            this.isEnd = false;
        }
        
        
    }

import java.util.*;

public class MyClass {
    public static void main(String args[]) {
      int x=10;
      int y=25;
      int z=x+y;

      System.out.println("Sum of x+y = " + z);
      
      String[] names = new String[]{"sander", "amy", "ann", "micheal"};
      String[] numbers = new String[]{"123456789","234567890", "789123456","123123123"};
      
      searchPhoneNumber(names, numbers, "0009");
      
    }
    
    public static String searchPhoneNumber(String[] names, String[] numbers, String searchString){
        String answer = "NO CONTACT";
        
        if(names.length == 0 || numbers.length == 0 || searchString.length() == 0){
            return answer;
        }
        
        
        // create new Trie data structure
        TrieDataStructure newTrie = new TrieDataStructure();
        
        // add the numbers to the trie data structure
        for(int i=0; i<numbers.length; i++){
            
            String currString = numbers[i];
            
            newTrie.insertPhoneNumber(currString, i);
        }
        
        System.out.println(newTrie.root.children);
        
        // search for the numbers
        HashSet<Integer> foundSubstrings = newTrie.searchSubstring(searchString);
        
        
        if(foundSubstrings.size() == 0){
            return answer;
        }
        
        
        System.out.println(foundSubstrings);
        
        // for(int strIdx: foundSubstrings ){
            
        //     String currStr = names[strIdx];
            
        //     if(currStr.compareTo(answer) < 0){
        //         answer = currStr;
        //     }
        // }
        
        
        return answer;
        
        
    }
    
    static class TrieNode{

        private HashMap<Integer, TrieNode> children;
        private HashSet<Integer> positions;
        
        TrieNode(){
            this.children = new HashMap<>();
            this.positions = new HashSet<>();
        }
        
        public void addIndex(int index){
            this.positions.add(index);
        }
    }
    
    static class TrieDataStructure{
        private TrieNode root;
        
        private TrieDataStructure(){
            this.root = new TrieNode();
        }
        
        // time taken to create the trie data structure O(ns^2); same as space 0(n)
        public void insertPhoneNumber(String phoneNumber, int index){
            if(phoneNumber.length() == 0){
                return;
            }
            
            // insert at a specific index
            
            
            TrieNode currentNode = this.root;
            
            for(int i=0; i<phoneNumber.length(); i++){
                
                int currChar = Character.getNumericValue(phoneNumber.charAt(i));
                
                
                TrieNode newNode;
                
                if(!currentNode.children.containsKey(currChar)){
                    
                    newNode = new TrieNode();
                    
                    newNode.addIndex(index);
                    
                    currentNode.children.put(currChar, newNode);
                    
                } else{
                    
                    newNode = currentNode.children.get(currChar);
                    
                    newNode.addIndex(index);
                }
                
                TrieNode currNode = newNode;
                
                
                for(int j=i+1; j<phoneNumber.length(); j++){
                    

                    
                    int currChar2 = Character.getNumericValue(phoneNumber.charAt(j));
                    
                    if(!currNode.children.containsKey(currChar2)){
                        
                        TrieNode newNode2 = new TrieNode();
                        
                        newNode2.addIndex(index);
                        
                        
                        currNode.children.put(currChar2, newNode2);


                    } else {
                        currNode.children.get(currChar2).addIndex(index);
                    }
                    
                    currNode = currNode.children.get(currChar2);
 
                    
                }
                
                
            }

        }
        
        // time taken to search is O(m) time 
        public HashSet<Integer> searchSubstring(String searchString){
            
            HashSet<Integer> answer = new HashSet<>();
            
            if(searchString.length() == 0){
                return answer;
            }
            
            TrieNode currNode = this.root;
            
            for(int i=0; i<searchString.length(); i++){
                
                int currValue =Character.getNumericValue(searchString.charAt(i));
                
                if(!currNode.children.containsKey(currValue)){
                    
                    return answer;
                }
                
                currNode = currNode.children.get(currValue);
            }
            
            System.out.println(currNode);
            
            return currNode.positions;

        }
    }
}

// search long string from set of words
public class MyClass {
    public static void main(String args[]) {
      int x=10;
      int y=25;
      int z=x+y;

      System.out.println("Sum of x+y = " + z);
      
      TrieDataStructure newTrie = new TrieDataStructure();
      
      String[] words = new String[]{"this", "yo", "is", "a", "bigger", "string", "kappa"};
      
      for(String word: words){
          newTrie.insertWord(word);
      }
      
      newTrie.searchBigString("this is a big string");
    }
    
    
    static class TrieNode{
        private char charValue;
        private TrieNode[] children;
        private String endOfWord;
        
        
        TrieNode(char charValue, String endOfWord){
            this.charValue = charValue;
            this.children = new TrieNode[26];
            this.endOfWord = endOfWord;
        }
    }
    
    static class TrieDataStructure{
        public static TrieNode root;
        
        TrieDataStructure(){
            this.root = new TrieNode('\0', "");
        }
        
        
        public void insertWord(String newWord){
            if(newWord.length() == 0){
                return;
            }
            
            TrieNode currNode = this.root;
            
            for(int i=0; i<newWord.length(); i++){
                
                char currChar = newWord.charAt(i);
                
                String endOfWord = i == newWord.length()-1 ? newWord : "";
                
                if(currNode.children[currChar - 'a'] == null){
                    
                    TrieNode newNode = new TrieNode(currChar, endOfWord);
                    
                    currNode.children[currChar-'a'] = newNode;
                    
                }
                
                currNode = currNode.children[currChar-'a'];
                
            }
        }
        
        public static HashSet<String> searchBigString(String bigString){
            
            bigString = bigString.replaceAll("\\s", "");
            
            
            System.out.println(bigString);
            
            HashSet<String> answer = new HashSet<>();
            
            if(bigString.length() == 0){
                return answer;
            }
            
            TrieNode currNode = root;
            
            for(int i=0; i<bigString.length(); i++){
                
                findWords(i, bigString, currNode, answer);
            }
            
            
            
            
            return answer;
            
            
        }
        
        public static void findWords(int index, String searchString, TrieNode currNode, HashSet<String> answer){
            
            for(int i=index; i<searchString.length(); i++){
                
                char currChar = searchString.charAt(i);
                
                if(currNode.children[currChar - 'a'] != null){
                    
                    currNode = currNode.children[currChar-'a'];
                    
                    if(currNode.endOfWord.length() >= 1){
                        
                        answer.add(currNode.endOfWord);
                        
                    }
     
                    
                }
                
            }
            
        }
    }
}