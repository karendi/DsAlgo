public class MyClass {
    public static void main(String args[]) {
      int x=10;
      int y=25;
      int z=x+y;

      System.out.println("Sum of x+y = " + z);
      System.out.println(longestPeak(new int[]{1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3}));
    }
    
    public static int longestPeak(int[] array) {
    // Write your code here.
		/*
		treat each number as the mid point
		traverse the left and right side
		check that i > left
		so traverse the left side till you get to a point where the current index is not greater than the prev
		traverse th right side till you get to a point where the right side is not less than the next element
		then 
		*
		* 1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3
		true, true, true, false, 
		
		peak should be? at index i, all the numbers to the left should be less than
		but not equal to each other since we don't want a plateau and all the numbers
		to its right should be decreasing and not equal to each other
		so by this reasoning we can't have peaks at index 0 or index array.length-1
	

   we can start at index 1
	 if index1-1 -> index 0 is greater than or equal to index1 then we ignore it
	 at index2 -> if the prev index which is index 1 is less than and not equal to
	 index2 then we can start checking the new subarray[1, 2]
	 at index 3 -> if the prev index is not less than then we undo the subarray we were creating 
	 and start all over again
	 
	 1. What matters is what was prev, if we find that the number in the previous array
	 was less than the current, we add it to the subarray of peaks
	 2. The moment we find out that the prev number is greater than or equal to the current number, we 
	 reset the subarray
		*
		***********/
		int maxLength = Integer.MIN_VALUE;
		
		if(array.length == 0) return -1;
		
		for(int i=1; i<array.length-2; i++){
			// the only time we would do anything is if the current number is greater than the prev and the next is less than the current
			int prev = array[i-1];
			int next = array[i+1];
			int currentNum = array[i];
			
			if(currentNum > prev && currentNum < next){
				//this is the only time that we want to explore our options
				int left =  i-2;
				int right = i+2;
				
				while(left >= 0 && array[left] > array[left+1]){
				    left-=1;
				}
				
				while(right < array.length && array[right] > array[right-1]){
				    right+=1;
				}
				
				maxLength = Math.max(maxLength, right-left);
				
			
			}
		}
		
		return maxLength;
		

  }
	
}