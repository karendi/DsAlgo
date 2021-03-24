import java.util.*;

public class MyClass {
    public static void main(String args[]) {
      int x=10;
      int y=25;
      int z=x+y;

      System.out.println("Sum of x+y = " + z);
      
      System.out.println(findPattern("lsharonp", "sharon"));
    }
    
    public static boolean findPattern(String s1, String s2){
        if(s1.length() == 0 || s2.length() == 0 )return false;
        
        // I have made an assumption that the strings are in lower case so that I can comfortably work with their ascii values
        
        // we also need to check that our pattern is not greater than the matching string that we have
        if(s2.length() > s1.length()) return false;
        
        // we can get the pattern for the pattern we are matching
        int[] patternArray = new int[26];
        int[] patternArray2 = new int[26];
        
        int index1 = 0;
        
        // this is the hash for the pattern string
        for(int i=0; i<s2.length(); i++){
            patternArray[s2.charAt(i)-'a']++;
        }
        
        // we are looking for s2.length() window
        for(index1=0; index1<s2.length(); index1++){
            patternArray2[s1.charAt(index1)-'a']++;
        }
        
        // we can check if the first s2.length of the first string are equal and we don't have to loop over
        if(Arrays.equals(patternArray, patternArray2)){
            return true;
        } else {
            int initialIndex = 0;
        }
        
        // int newStart = s1.length()+s2.length();
        // System.out.println("new start...."+index1);
        
       
        for(int i=index1; i<s1.length(); i++){

            // we should always maintain a window of length s2.length
            // if we get here, that means that our two patterns don't match, what should we do?
            // we should add the next character, from our 2nd string and remove the very first character at index 0 from our pattern
            // we need to find a way to keep track of the index from the start where we are reducing the pattern
            patternArray2[s1.charAt(initialIndex)-'a']--;
            patternArray2[s1.charAt(i)-'a']++;
            
            if(Arrays.equals(patternArray, patternArray2)){
                return true;
            }
            // we increase our inital index
            initialIndex += 1;
            
        }
            
        }
        
        
        return false;
        
        
    }
}