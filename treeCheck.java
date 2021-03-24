public class MyClass {
    public static void main(String args[]) {
      int x=10;
      int y=25;
      int z=x+y;

      System.out.println("Sum of x+y = " + z);
      System.out.println(treesToReduce(new int[]{3, 7, 4, 5}));
    }
    
    public static int treesToReduce(int[] trees){
        if(trees.length == 0 || trees.length == 1) return 0;
        
        
        /*
        * [5, 4, 3, 2, 6]
        
        
        big small small -- this is wrong 
        big small big
        small big small
        
             5, 2, 3, 2, 6
             
             5, 4, 3
             big small small
             
             4, 3, 2
             big small small
             
             
          
          [3, 7, 4, 5]
          
          So what is the pattern? What should I be looking for
          1. Adjacent numbers should not be equal to each other
          2. Adjacent numbers should not be reducing
          3. Adjacent numbers should not be increasing
          
          - So one thing is we wont consider the first index 0 because there is nothing to its left
          - We should check for mismatches?
          - Because in the case of 5, 4, 3, 2, 6,--> we can only shorten the trees
          - 3 elements, 1: d
            - so what do we do when we encounter a reducing pair in the case of 4, 3, 2
        *   - we have to check the next element we know that from ...... to 4 is fine
        but there is an issue btwn 4, 3 and if we are to change 3 it will affect its next element
        so we know that 3-1 = 2
        2, 3, 2
        we cannot decrease 3 because we can't have adjacent elements decreasing 4, 3
        so when we get to a mismatch, we should reduce and the only way to reduce is to reduce the
        larger element to a number that is less 1 than the adjacent element to its right
        
        
        
        currentNum = 4
        nextNum = 3
        
        if currentNum > nextNum 
        
        small big small
        big   small big
        
        
        *
        **/
        int reduce = 0;
        int index = 1 ;// we start at index1
        
        
        while(index < trees.length){
            // what are the conditions wer are looking for
            boolean checkTwo = false;
            int nextNum = 0;
            
            int prevNum = trees[index-1]; // the prev 1
            int middleNum = trees[index]; // constant space
            
            
            if(index+1 >= trees.length){
                checkTwo = true;
            }else {
                nextNum = trees[index+1];
            }
            
            
            if(checkTwo){
                // we are just checking two elements at this point,we just check that they are not equal
                // they should be non decreasing,or increasing [1, 2]
                
                if( prevNum == middleNum){
                    prevNum -= 1;
                    reduce += 1;
                }
            } else {
                // prev current next
                
                // prev =
                
                if( prevNum >= middleNum  && middleNum >= nextNum || prevNum >= middleNum && middleNum <= nextNum){
                    //big big small   // big small small
                   // big big small // big small small //
                   // [5, 4, 3, 2, 6]
                   // 5. 4 3 --> 3 greater --> min(5, 3) 3-1 = 2
        
                   // 4 3 2 --> big small small
                   //3 2 6
                   
                   
                   // 5 2 3 2 6
                   //5 2 3 1 tree 
                   //2 3 2
                    int minNum = Math.min(prevNum, nextNum);
                    
                    minNum -= 1; 
                    int diff = middleNum - minNum;
                    
                    //curent index
                    trees[index] = diff;
                    
                    reduce += 1;
                }
            }
            
            index++;
        }
        
        return reduce;
        
        
    }
}