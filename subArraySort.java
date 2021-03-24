import java.util.*;

public class MyClass {
    public static void main(String args[]) {
      int x=10;
      int y=25;
      int z=x+y;

      System.out.println("Sum of x+y = " + z);
      
      int[] answer = subarraySort(new int[]{1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19});
      
      for(int xy: answer){
          System.out.println(xy);
      }
    }



  public static int[] subarraySort(int[] array) {
		int[] answer = new int[]{-1, -1};
		
		if(array.length == 0) return answer;
		
		if(array.length == 1) return answer;
		/*
		* we iterate through the array keeping track of the largest and smallest unsorted elements
		How do we know that a number is unsorted?
		the left and right are not less than and greater than the current number
		*
		*/
		
		int maxUnsortedNum = Integer.MIN_VALUE;
		int minUnsortedNum = Integer.MAX_VALUE;
		
		for(int i=0; i<array.length-1; i++){
		    // this just checks for the unsorted numbers
			if( i > 0 && array[i] < array[i-1] || array[i] > array[i+1] ){
			    maxUnsortedNum = Math.max(maxUnsortedNum, array[i]);
			    minUnsortedNum = Math.min(minUnsortedNum, array[i]);
			}
		}
		
		int leftIndex = 0;
		int rightIndex = array.length-1;
		
		while(array[leftIndex] <= minUnsortedNum ){
		    leftIndex+=1;
		}
		
		while(array[rightIndex] >= maxUnsortedNum){
		    rightIndex-=1;
		}
		
		answer[0]=leftIndex;
		answer[1]=rightIndex;
		
		return answer;
  
}
}