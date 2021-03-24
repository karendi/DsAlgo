import java.util.*;

public class MyClass {
    public static void main(String args[]) {
      int x=10;
      int y=25;
      int z=x+y;

      System.out.println("Sum of x+y = " + z);
      
      List<List<Integer>> answer = findTriplets(new int[]{2, 7, 4, 9, 5, 1, 3}, 10);
      
      for(List<Integer> xy: answer){
          System.out.println(xy);
      }
    }
    
    public static List<List<Integer>> findTriplets(int[] arr, int sum){
        
        List<List<Integer>> answer = new ArrayList<>();
        
        // sort the array
        Arrays.sort(arr);
        
        if(arr.length == 0) return answer;
        
        for(int i=0; i<arr.length-3; i++){
            helperMethod(arr, i, i+1, sum, answer);
        }
        
        return answer;
    }
    
    public static void helperMethod(int[] arr, int current, int left, int sum, List<List<Integer>> answer){
        // what is the base case???
        // we should stop when left cannot be advanced anymore
        // keep increasing left 
        
        if(left > arr.length-3){
            return;
        }
        
        for(int i=arr.length-1; i > left; i--){
            int currentSum = arr[current] + arr[left] + arr[i];
            
            if(currentSum <= sum){
                List<Integer> newList = new ArrayList<>();
                
                newList.add(arr[current]);
                newList.add(arr[left]);
                newList.add(arr[i]);
                
                answer.add(newList);
            }
        }
        
        // always moving the left pointer
        helperMethod(arr, current, left+1, sum, answer);
        
     
    }
}