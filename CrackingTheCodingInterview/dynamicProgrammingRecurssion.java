public int static noOfWays(int n){
    if(n == 0 || n == 1) return 1;

    // so what we have is 3 different ways to get to a stair case
    /*
    * when we are at the first step we just have one way to get there,
    at the second step we can either go one step or two steps so that is 2,
    at the third step we can either go one step one step one step
    or one step two step
    or one 3 step hop
    *
    */

    int[] dp = new int[n];
    // our index 0 will act as our 1st step
    dp[0] = 1;

    for(int i=1; i<n; i++){
        if(i > 1){
            dp[i] =+ dp[i-1];
        }

        if(i > 2){
            dp[i] =+ dp[i-2];
        }

        if(i > 3){
            dp[i] =+ dp[i-3];
        }
    }

    return dp[i-1];

}
public static List<String> findPath(String[][] grid){
    List<String> answer = new ArrayList<>();
    
    if(grid.length == 0) return answer;
    
    /*
    * we can get to grid[row][col] either from grid[row-1][col] i.e the right
      and either grid[row][col-1]
      
      so we can start from the very end or the last grid
      we will know that we have found the path when we get to the origin that is row==0 and col==0
      
    * how do we traverse? what are the valid paths:
    1. they have no obstacle and are within the bounds
    *
    */
    
    helperMethod(grid, answer, grid.length-1, grid[0].length-1);
    return answer;
}

public static boolean helperMethod(String[][] grid, List<String> answer, int row, int col){
    // this is the base case, otherwise, we should continue to traverse the grid
    if(row == 0 && col == 0){
        return true;
    }
    
    // we should check that the col and row are not out of bounds and that it is not a grid that has an obstacle
    if(row < 0 || col < 0 || grid[row][col] == "X"){
        return false;
    }
    
    // else we should be able to traverse by reducing the col/row
    //right, we should only add the current grid item if it has a neighbour that is traversable
    if(helperMethod(grid, answer, row-1, col) || helperMethod(grid, answer, row, col-1)){
        answer.add("["+ row +"]"+ "["+ col + "]");

        // we should change the grid, because we don't want to traverse it again
        grid[row][col] = "X";
    }
    
    return true;
   
}

public static int findMagicIndex(int[] arr){
    if(arr.length == 0) return -1;

    if(arr.length == 1 && arr[0] == 1) return 1;
    else return -1;

    return helperMethod(arr, 0, arr.length-1);
}

public static int helperMethod(int[] arr, int start, int end){
    if(start > end){
        return -1;
    }

    int mid = (start+end)/2;

    if(arr[mid] == mid) return mid;

    // if the mid index is greater than the number at the mid index
    // 0, 1, 2, 5
    if(mid > arr[mid]){
        return helperMethod(arr, mid+1, end);
    }

    if(mid < arr[mid]){
        return helperMethod(arr, start, mid-1);
    }

    return -1;
}

public static boolean helperMethodDuplicates(int[] arr, int start, int end, List<Integer> answer){
    if(start >= end){
        return false;
    }
    
    int midIndex = start + (end-start)/2;
    
    if(arr[midIndex] == midIndex){
        return true;
    }
    
    int leftIndex = Math.min(arr[midIndex], midIndex-1);
    if( helperMethodDuplicates(arr, start, leftIndex, answer)){
        answer.add(leftIndex);
    }

   int rightIndex = Math.max(midIndex+1, arr[midIndex]);

   if(helperMethodDuplicates(arr, rightIndex, end, answer)){
       answer.add(rightIndex);
   }

   return false;
}

public static List<List<Integer>> generateSubsets(int[] arr){
    
    List<List<Integer>> answer = new ArrayList<>();
    
    if(arr.length == 0) return answer;
    
    answer.add(new ArrayList<Integer>());
    
    for(int num=0; num<arr.length; num++){
        int currentSize = answer.size();
        
        for(int list=0; list<currentSize; list++){
            
            // make a copy of the current list and add the current number
            List<Integer> copy = new ArrayList<>(answer.get(list));
            copy.add(arr[num]);
            answer.add(copy);
        }
    }
    
    
    return answer; 
    
}

public static int knapSackProblem(int[][] items, int weight){

    // we need to create a 2d array to keep track of the the currentProfit that we have
    // so the col will represent the weights and the row the different items in consideration
    int[][] currentProfit = new int[items.length+1][weight+1];

    // so the first col will rep the weight when it is zero and the profit will be zero
    // the first row will rep when we have no item in consideration and the profit will be zero

    for(int item=1; item<currentProfit.length; item++){
        for(int weight=1; weight<currentProfit[0].length; weight++ ){
            int currentItemWeight = items[item-1][1];
            int currentItemProfit = items[item-1][0];

            if(currentItemWeight > weight){
                // then we won't consider this item and get the prev profit when the item was not in consideration
                currentProfit[item][weight] = currentProfit[item-1][weight];

            }else {

                int newProfit = currentProfit[item-1][weight-currentItemWeight] + currentItemProfit;

                currentProfit[item][weight] = Math.max(currentProfit[item-1][weight], newProfit);
            }
        }
    }

    return currentProfit[currentProfit.length-1][currentProfit[0].length-1];

}

public static int noOfWaysToMakeChange(int[] coins, int amount){

    // we need a 2d array to keep track of the various number of ways to make change
    // so the row will represent the addition of a new coin to be considered and the col will represent the various amount we are considering from 0
    // so when we have zero amount, we have one way of making change, i.e no way

    int[][] noOfWays = new int[coins.length+1][amount+1];

    //the difference btwn this and the knapsack problem is that we still reconsider the same coin with the remaining amount

    // so we know that when the amount is zero, there is only one way to make change and that is one way no way

    for(int row=0; row<noOfWays.length; row++){
        noOfWays[row][0] = 1;
    }

    for(int coin=1; coin < noOfWays.length; coin++){
        for(int amount=1; amount<noOfWays[0].length; amount++){
            if(coins[coin-1] > amount){
                noOfWays[coin][amount] = noOfWays[coin-1][amount];
            } else {
                noOfWays[coin][amount] = noOfWays[coin-1][amount] + noOfWays[coin][amount-coins[coin-1]];
            }
        }
    }


    return noOfWays[noOfWays.length-1][noOfWays[0].length-1];



}
public static int numbersInPi(String numbersPi, List<String> availableNums)
{
    if(numbersPi.length() == 0 || availableNums.size() == 0){
        return 0;
    }
    
    // we can have the cache which can be used to store the number of spaces from index 1-2
    HashMap<String, Integer> cache = new HashMap<>();
    
    HashSet<String> uniqueNums = new HashSet<>(availableNums);
    
    int[] answer = new int[1];
    
    answer[0] = Integer.MAX_VALUE;
    
    
    return helperMethod(numbersPi, uniqueNums, 0, 0, answer, cache);
    
    
}

public static int helperMethod(String numbersPi, HashSet<String> uniqueNums, 
                               int index, int currentSpaces, int[] answer, 
                               HashMap<String, Integer> cache)
{
    if(index == numbersPi.length())
    {
        return currentSpaces;
        
    }
    
    for(int i=index; i<numbersPi.length(); i++){
        
        // we should be able to get the substring from the cache
        StringBuilder currSubstring = new StringBuilder();
        currSubstring.append(index);
        currSubstring.append(i+1);
        
    
        
        if(cache.containsKey(currSubstring.toString())){
        
            
            currentSpaces += cache.get(currSubstring.toString());
            
            helperMethod(numbersPi, uniqueNums, i+1, currentSpaces, answer, cache);
            
        } else {

            
            String subStr = numbersPi.substring(index, i+1);
            
            
            if(uniqueNums.contains(subStr)){
                
                
                cache.put(currSubstring.toString(),  1);
                
                currentSpaces += 1;
                
                helperMethod(numbersPi, uniqueNums, i+1, currentSpaces, answer, cache );
                
            } 

            
        }
        
    }
    
    
    return Math.min(answer[0], currentSpaces);
    
}