import java.util.*;

public class MyClass {
    public static void main(String args[]) {
      int x=10;
      int y=25;
      int z=x+y;

      System.out.println("Sum of x+y = " + z);
      
      List<String> answer =validIpAddresses("1921680");
      
      System.out.println(answer);
    }
    
    public static List<String> validIpAddresses(String str){
        List<String> answer = new ArrayList<>();
        
        if(str.length() == 0 || str.length() > 12 || str.length() < 4){
            return answer;
        }
        
        for(int i=1; i<i+3 && i < str.length(); i++){
            
            String[] currentStrings = new String[4];
            
            
           currentStrings[0]= str.substring(0, i);
            
            if(!isValid(currentStrings[0])){
                continue;
            }

            
            for(int j=i+1; j<j+3 && j < str.length(); j++){
                
                currentStrings[1] = str.substring(i, j);
                
                if(!isValid(currentStrings[1])){
                    continue;
                }
                
                
                for(int k=j+1; k<k+3 && k < str.length(); k++){
                    
                    currentStrings[2] = str.substring(j, k);
                    
                    currentStrings[3] = str.substring(k);
                    
                    
                    if(isValid(currentStrings[2]) && isValid(currentStrings[3])){
                        
                        StringBuilder currString = new StringBuilder();
                        
                        for(int idx=0; idx<currentStrings.length; idx++){
                            
                            String str2 = currentStrings[idx];
                            
                            currString.append(str2);
                            
                            if( idx != currentStrings.length-1){
                                
                                currString.append('.');
                                
                            }
                        
                        
                        }
                        
        
                        
                        answer.add(currString.toString());
                        
        
                        
                    }
            }
                
                
            }
        }
        
        return answer;
        
        
    }
    
    public static boolean isValid(String ipPart){
        if(ipPart.length() == 0){
            return false;
        }
        
        if(ipPart.length() > 1 && ipPart.charAt(0) == '0'){
            return false;
        }
        
        if((ipPart.length() != 1 && Integer.parseInt(ipPart) < 0 ) || Integer.parseInt(ipPart) > 255){
            return false;
        }
        
        return true;
    }

    class Program {
        // with palindromes we are always expanding from the centre  of a given character
  public static String longestPalindromicSubstring(String str) {
    // Write your code here.
		
		if(str.length() == 0 || str.length() == 1){
			return str;
		}
		/*
		* we create a 2d array the row and column will represent the indices that we 
		are considering at the moment
		eventually what we want to get is the longest palindromic subsequence from 0, to s.length()
		* How do we get palindromes? we expand from the centre and check if the characters match
		*
		******/
		
		int[][] dp = new int[str.length()][str.length()];
		
		for(int i=str.length(); i >= 0; i--){
            // so the i is like our left
			for(int j=i; i<str.length(); j++){
                // j is like our right
				// both i and j start at the same point then j and then they both expand
                // from the centre 
                if(str.charAt(i) == str.charAt(j)){
                    if(i == j){
                        dp[i][j] = 1;
                    } else{
                        // we need to add two to the string which did not include the current characters that we are currently
                        // considering
                        dp[i][j] = dp[i+1][j-1] + 2;
                    }
                } else{
                    // we need to find the greatest/longest palindromic substring excluding either of the mismatched characters
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
			}
        }
        
        return dp[0][str.length()-1];
  }

  public static String uniqueCharacters(String str){
        
    if(str.length() <= 1){
        return str;
    }
    
    
    HashMap<Character, Integer> seenCharacters = new HashMap<>();
    
    
    int[] maxStr = new int[]{0, 1};
    
    int startIndex = 0;
    
    
    
    
    for(int i=0; i<str.length(); i++){
        
        char currChar = str.charAt(i);
        
        if(seenCharacters.containsKey(currChar)){
            
            
            // update also the startIndex
            //max btwn the startindex and currChar+1;
            startIndex = Math.max(startIndex, seenCharacters.get(currChar) + 1);

        } 
        
        // when we are calculating the length so far we do i+1 and we also store it as i+1  because substring is exclusive
        // of the right index
        if(i+1 -startIndex > maxStr[1] - maxStr[0]){
            
            maxStr[0] = startIndex;
            maxStr[1] = i+1; // we store them like this because we will check i+1 - startIndex
            // hence we store i+1
        }
            
        // but we store it as i, because that is where the character is at and when we encounter a duplicate character,
        // we will move the start index to seenCharacter.get(currChar)+1
        seenCharacters.put(currChar, i);
            

    }
    
    
    return str.substring(maxStr[0], maxStr[1]);

}

public static String urlifyString(String str, int trueLength){
    if(str.length() == 0){
        return str;
    }
    
    // we need to count the number of spaces that are present, so what we need to do is iterate thro the true length
    // and then the new length will be spaceCount*2 + trueLength
    
    int spaceCount  = 0;
    

    
    for(int j=0; j<trueLength; j++){
        if(str.charAt(j) == ' '){
            spaceCount++;
        }
    }
    
    
    // this is the new length that we will have
    int index = trueLength + spaceCount * 2;
    
    // the new char array with the new length
    char[] newArr = new char[index];
    
    // remember to reduce by one and i will be used to traverse the original string and index will be used to insert the new characters to the new arr
    int i = trueLength-1;
    
    index-=1;
    
    while(i >= 0){
        
        if(str.charAt(i) == ' '){
            newArr[index--] = '0';

            newArr[index--] = '2';

            newArr[index--] = '%';
    
            
        } else {
            newArr[index--] = str.charAt(i);
        }
        
        i--;
        
    }
    
    

    
    return new String(newArr);
}
}

}
public static List<String> generateIpAddress(String str){
    List<String> answer = new ArrayList<>();
    
    if(str.length() < 4 || str.length() > 12){
        return answer;
    }
    
    // recursive helper method:
    // return type: void it will keep adding to the answer list it will modify the answer list
    // pass in the starting index
    helperMethod(answer, str, 0, new ArrayList<String>());
    
    System.out.println(answer);
    
    
    return answer;
}

public static void helperMethod(List<String> answer, String str, int index, List<String> currentStrings)
{
    if(index == str.length() && currentStrings.size() == 4){
        
        String newIpAddress = String.join(".", currentStrings);
        
        answer.add(newIpAddress);
        
        
        return;
    }
    
    for(int i=1; i<=3; i++){
        
        if(index+i > str.length()){
            return;
        }
        
        String substring = str.substring(index, index+i);
        
        if(validIpPart(substring)){
            
            currentStrings.add(substring);
            

            
            
            helperMethod(answer, str, index+i, currentStrings);
            
            // we should backtrack, undo what we had done to now consider the next combinations
            

            
            // to account for the dot added

            currentStrings.remove(currentStrings.size()-1);

            
            
        } 
        
        
        
        
    }
    
    
}

public static boolean validIpPart(String str){
    // cannot be an empty string or have more than 3 characters
    if(str.length() < 1 || str.length() > 3){
        return false;
    }
    
    
    // cannot have a leading zero
    if( str.charAt(0) == '0'){
        return false;
    }
    
    // cannot be greater than 255
    if(Integer.parseInt(str) > 255){
        return false;
    }
    
    // otherwise return true
    return true;

}