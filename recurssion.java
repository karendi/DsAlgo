public class MyClass {
    public static void main(String args[]) {
   
      
      System.out.println(dividePhoneNumber("0221985324"));
    }
    
    public static String dividePhoneNumber(String phoneNumber){
        if(phoneNumber.length() < 2){
            return new String();
            
        }
        /*
        * so we should be able to divide the number max is 3 characters and the min is 2 
        when we have 888-990-990
        if we have phoneNumber.length % 3 = 0; means we can continuously divide up the number into x amount of 3 equal parts
        when should we divide it in 2 parts when the re
        *
        *
        *
        **
        **/
        return helperMethod(phoneNumber, 0,new StringBuilder());
        
    }
    
    public static String helperMethod(String phoneNumber, int fromIndex, StringBuilder currString){
        if(fromIndex < phoneNumber.length()){
            
            int end = 0;
            boolean isEnd = false;
            
            // check if we can be able to divide it into 3 parts
            
            if(phoneNumber.length() - fromIndex >= 5 || (phoneNumber.length() - fromIndex) % 3 == 0){
                
                if(fromIndex+3 >= phoneNumber.length()){
                    end = phoneNumber.length();
                    
                    isEnd = true;
                    
                } else {
                    end = fromIndex+3;
                    
                }
       
                currString.append(phoneNumber.substring(fromIndex, end));
                
                if(!isEnd){
                    
                    currString.append("-");
                }
                
                helperMethod(phoneNumber, fromIndex+3, currString);
                
                
            } else{
                
                if(fromIndex+2 >= phoneNumber.length()){
                    
                    end = phoneNumber.length();
                    isEnd = true;
                    
                } else {
                    end += fromIndex+2;
                    
                }
                
                
                currString.append(phoneNumber.substring(fromIndex, end));
                
                if(!isEnd){
                    
                    currString.append("-");
                }
                
                helperMethod(phoneNumber, fromIndex+2, currString);
                
            }
        
        }
        
        return currString.toString();
        
    }

    public static List<String> findAllCombinations(String num, int limit){
        
        List<String> answer = new ArrayList<>();
        
        if(num.length() < limit){
            
            return answer;
        }
        
        char[] charArr = num.toCharArray();
        
        boolean[] seen = new boolean[charArr.length];
        
        findAllCombinationsHelperMethod(charArr, limit, answer, new StringBuilder(), seen);
        
        
        for(String str: answer){
            System.out.println(str);
        }
        
        return answer;
        
     
        
    }
    
    public static void findAllCombinationsHelperMethod(char[] charArr, int limit, List<String> answer, StringBuilder currentString, boolean[] seen){
        
        if(currentString.length() == limit){
            
            answer.add(currentString.toString());
            
        }
        
        for(int i=0; i<charArr.length; i++){
            
            if(seen[i]) continue;
            
            currentString.append(charArr[i]);
            seen[i] = true;
            
            findAllCombinationsHelperMethod(charArr, limit, answer, currentString, seen);
            
            currentString.deleteCharAt(currentString.length()-1);
            seen[i] = false;

        }
       
        
        
        
    }1  
}