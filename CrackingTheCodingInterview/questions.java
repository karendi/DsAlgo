import java.util.*;

public class MyClass {
    public static void main(String args[]) {
      int x=10;
      int y=25;
      int z=x+y;

      System.out.println("Sum of x+y = " + z);
      
      int[] answer = swapNums(new int[]{4, 1, 2, 1, 1, 2}, new int[]{3, 6, 3, 3});
      System.out.println(Arrays.toString(answer));
    }
    
    public static int[] swapNums(int[] arr1, int[] arr2){
        
        int[] answer = new int[2];
        
        if(arr1.length == 0 || arr2.length == 0){
            return answer;
        }
        
        /*
        * arr1 -> 11
          arr2 -> 15
          15+11 = 26/2 = 13
          so each should have sum 13
          
         sumA - a + b = sumB -b + a
         sumA - sumB = 2a - 2b
         (sumA-sumB)/2 = a-b
          
          difference = a-b
          difference - a = -b
          a - difference = b
          
          (15-11) = 4/2
          2 = a+b
          so we are looking for elements that might add up to 2 from a and b
          
          13-11 = 2 -->
          15-13 = 2---> but there is no 2 but there is 3
        *we get the nearest number to 2 maybe and send the difference from the second array to the other array
        *
        *
        *
        *
        *
        *
        **/
        int sum1=0;
        int sum2=0;
        
        //O(n)
        for(int i=0; i<arr1.length; i++){
            sum1+=arr1[i];
        }
        
        //O(m)
        for(int i=0; i<arr2.length; i++){
            sum2+=arr2[i];
        }
        
  
        int difference = (sum1 - sum2)/2;
        
        // a+b = difference
        // b = difference-a
        
        HashSet<Integer> secondArray = new HashSet<>();
        
        //(O(m))
        for(int num: arr2){
            secondArray.add(num);
        }


        //(0(n))
        for(int i=0; i<arr1.length; i++){
            int currentDiff = arr1[i]-difference;
            if(secondArray.contains(currentDiff)){
                answer[0] = arr1[i];
                answer[1] = Math.abs(difference-arr1[i]);
            }
        }
        
        
        
        //.....O(2n + 2m) --> O(n+m)
        
        
       return answer; 
        
    }
    public static List<String> getWords(String digits, Set<String> availableWords){
        
        List<String> answer = new ArrayList<>();
        
        if(digits.length() == 0 || availableWords.size() == 0){
            return answer;
        }
        
        // character mapping
        
        HashMap<Character, HashSet<Character>> charMap = new HashMap<>();
        charMap.put('0', new HashSet<Character>());
        charMap.put('1', new HashSet<Character>());
        charMap.put('2', new HashSet<Character>(Arrays.asList('a', 'b', 'c')));
        charMap.put('3', new HashSet<Character>(Arrays.asList('d', 'e', 'f')));
        charMap.put('4', new HashSet<Character>(Arrays.asList('g', 'h', 'i')));
        charMap.put('5', new HashSet<Character>(Arrays.asList('j', 'k', 'l')));
        charMap.put('6', new HashSet<Character>(Arrays.asList('m', 'n', 'o')));
        charMap.put('7', new HashSet<Character>(Arrays.asList('p', 'q', 'r', 's')));
        charMap.put('8', new HashSet<Character>(Arrays.asList('t', 'u', 'v')));
        charMap.put('9', new HashSet<Character>(Arrays.asList('w', 'x', 'y', 'z')));

        // you can reuse the same character, can check if each word can be formed from the digits
        for(String word: availableWords){
            foundWord(word, charMap, digits, 0, 0, answer);
             
        }
        
        
        return answer;
        
    }
    
    public static void foundWord(String word, HashMap<Character, HashSet<Character>> charMap, String digits, int index, int count, List<String> answer){
        
        System.out.println("word...."+ word);
        System.out.println("index........."+ index);
        System.out.println("count......"+count);

        // if we get to the end of the string
        if(index >= word.length()){
            if(count == word.length()){
                answer.add(word);
                return;
            } else {
                return;
            }
        }
        
        
        if(index < word.length()){
            
            // should keep advancing the string index and check if the character exists in the given digits
            
            char currentChar = word.charAt(index);
            
            // looping thro the digits 
            
            for(int i=0; i<digits.length(); i++){
                
                char currDigit = digits.charAt(i);
                
                
                //get the character set from the hashmap
                HashSet<Character> currentCharSet = charMap.get(currDigit);
                // System.out.println(Arrays.toString(charMap[currDigit-'0']));
                // this can be constant time with the use of hashset
                
                if(currentCharSet.contains(currentChar)){
                
                    
                    count++;
                    break;
                    
                } else {
                    index = word.length();
                    break;
                }
        }
        
        // once you do this you should be able to call the method again advancing the index
        foundWord(word, charMap, digits, index+1, count, answer);
            
        }
        
        
    }

    public static int[] setPeakAndValleys(int[] arr){
        if(arr.length == 0){
            return arr;
        }
        /*
        * we'll keep checking from index 1 and check the left and right neighbours
        the trio should be okay if the middle number is the greatest and among the neighbours small --->big---> small
        or we should have small---> big--> small
        if it is not what should we do we should swap the middle number and the greatest among the neighbours
        *
        */
        
        for(int i=1; i<arr.length-1; i++){
            int left = arr[i-1];
            int right = arr[i+1];
            int mid = arr[i];
            int temp;
            
            // this is a good peak
            if(left < mid && right < mid || left > mid && right > mid){
                continue;
            } else if (left < mid && right > mid){
                //small, big, big
                //big, small, big
                temp = arr[mid];
                arr[mid] = arr[left];
                arr[left] = temp;
                
            } else if (right < mid && left > mid){
                //big, big, small
                
                temp = arr[mid];
                arr[mid] = arr[right];
                arr[right] = temp;

            } else if(left < mid && mid < right) {
                // small, small, big
                temp = arr[mid];
                arr[mid] = right;
                arr[right] = temp;
            } else{
                // big, small, small
                temp = arr[mid];
                arr[mid] = left;
                arr[left] = mid;
            }

        }
        
        return arr;
    }
}