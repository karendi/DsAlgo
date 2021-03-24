// Combinations vs Permutations
//  Combinations: [1, 2, 3][1], [2], [3] , [1, 2], [1, 3] subsets-> no duplicates, r items 2, 3, 4, 5
//    To get r length of combinations from string/array, we can be able to get the subsets and return only a subset when it has length r
//    To get all combinations from empty to all combinations we keep adding to the existing subsets without any constraints
//    Incase we have duplicates in our array and we don’t want duplicated values, when adding to the list of combinations:
//        For example we have an array int[ ] numArray = new int [ ] { 1, 2, 3, 4, 5 };
//        Then we have our output which will be a resizable array so we have List<List<Integer>> answer = new ArrayList<>( );
//        Which initially has an empty List<Integer> ..which we will make copy of and add it to the answer  ist.
//        So what will happen is, when we encounter duplicates? How do we ensure that we will encounter the duplicates consecutively we sort the array, and if our current nums[ I ] == nums[1] , when appending to the list we start at the previous size, which is before we added the previous duplicate number. We are doing this so that we get the permutations of this current number, with the previous duplicate number only, and not with all the numbers before the previous duplicate number, because we are sure that it has been formed.
//    The iterative approach:
                Public List<List<Integer>> findSubsets( int[ ] numbers){    

                    List<List<Integer>> answer = new ArrayList<>( ); // an empty list [[],[]]
                    if(numbers.length == 0 ) {
                        return  answer;
                    }
                    1, 2, 3,  1, 2, 3, 4, 2 -> 1, 2, ,2, 3,4
                    [ [], [1]],|----> [2], [1, 2]//duplicate subsets --> 1, 2,2, 2, 2, 3 
                    answer.add(emptylist)
                    // we create the list that we will be appending to and have an empty list in it
                    List<Integer> emptyList = new ArrayList<>( );
                    answer.add(emptyList);
                    [[]]

                    // we iterate through the numbers array, copying the already existing lists in the currentAppend and adding the current number, then pushing it to our currentAppend
                    for(int I=0; I<numbers.length; I++){
                        int size = answer.size(); // we get the size here because if we work with its size while still appending it will result in a never ending loop
                        for(int j = 0; j<size; j++){
                            // here we are now looping through the list of already formed combinations, making a copy adding the current number to the copy and then adding it to our answer
                            List<Integer> copy = currentAppend.get(j);
                            copy.add(numbers[I]);
                            answer.add(copy);
                        }
                    }
                    return answer;
                }
          // If we wanted to check for duplicates what we would do is: our for loop will have to be tweaked how? The first thing that we will have to do is sort our Array.
                 int size = 0; 
                 int start = 0;
                for(int i=0; i<numbers.length; I++){
                     if( I > 0 && numbers[I] == numbers[I-1]) {
                      // what we do is we initialise our start point to the previous size of the array list before we added the duplicate number, 
                      //the size value that will come in will be the previous size without the duplicate number before it combinations
                       start = size;
                    } else {
                      start = 0;
                    }
                    
                    size = answer.size(); // we get the size here because if we work with its size while still appending it will result in a never ending loop
                    for(int j = start;  j<size;  j++){
                         // here we are now looping through the list of already formed combinations, making a copy adding the current number to the copy and then adding it to our answer
                         List<Integer> copy = currentAppend.get(j);
                          copy.add(numbers[I]);
                          answer.add(copy);
                        }
                }

            //  If we wanted to add r sized combinations only to the array list, we would have to check the size of the copy before adding it to out final answer.Meaning we would have to use two arrays. Which will used a lot of space.
        /**
         *  The recursive solution:
              What will be the parameters that we pass in to our helper function? What do we need to reduce so that our function can terminate
              helperMethod(numberArray, currentIndex, currentPermutation, lengthOfOurArray);
              every time we should be able to reduce the currentIndex why?
              when we start with currentIndex == 0,
              we make a copy of the currentPermutation and add nums[0];
              then we increase the index again so our currentPermutation is added nums[1]--> so it becomes [nums[0]]
              then we add nums[nums.length-1]
             What  will be our base case?it
         * 
         *  
         * */  
        public List<List<Integer>> getCombinations(int[] nums){

            // if we are concerned about not having any sort of duplicates, what we should do is first sort the array
            /**
             * 
             *  Arrays.sort(nums);
             *  Then we store the combinations in a hashset so in our case,
             *  we'd have the answer arrayList, but we'd be appending our combinations to a hashset
             *  then in the end we will add our hashset to the arrayList.
             * 
             */

            List<List<Integer>> answer = new ArrayList<>();
            if(nums.length == 0) return answer;
            List<Integer> currCombination = new ArrayList<>();
            helperMethod(nums, 0, currCombination, nums.length, answer);

        }

        public void currCombination(int[] nums, int index, List<Integer> currCombination, int len, List<List<Integer>> answer){
            // always add the currCombination, by making a deep copy
            answer.add(new ArrayList<>(currCombination));

            for(int i=index; i<len; i++){
                currCombination.add(nums[i]);
                currCombination(nums, i+1, currCombination, len, answer);
                currCombination.remove(currCombination.size()-1);
            }
        }
/**
 * 
 * *
 * * For permutation, we care about how the numbers appear, so whenver we use a number we mark it as visited
 *   so we have a boolean array with the visited or not , then for permutations each combination must be equal
 *   to the specific array length that is have all the nums appearing in a new index.
 * 
 *   The difference between permutation and combination is:
 *   1. for combination we keep changing the start point in our for loop because we want to add [1], [2], [3], but for permutation, 
 *      we always want to start at at 0 and just go through all the numbers. 
 *   2. we always add every combination when calling our function recursively, but for permutations, we only add when the current permutation
 *      has the same length as the num length
 *   3. When we don't want duplicates, we sort both arrays in both the combination and permutation. How do we handle it? 
 *        a. in permutation the condition is if the current index > 0 && nums[index] == nums[index-1] && visited[index-1], we continue and do nothing
 *        b. For combination, we sort the array, then we add the combinations to a hashset instead.
 * 
 */
public static List<List<Integer>> getPermutations(int[] num){
    List<List<Integer>> answer = new ArrayList<>();
    if(num.length == 0) return answer;

    boolean[] visited = new boolean[num.length];
    List<Integer> currentPermutation = new ArrayList<>();

    helperMethod(answer, visited, num, currentPermutation);
}

public void helperMethod(List<List<Integer> answer, int[] nums,  boolean[] visited, List<Integer> currentPermutation, int index){
    // when should we add the currentPermutation, only when it hits the length of the array
    if(nums.length == currentPermutation.size()){
        answer.add(new ArrayList<>(currentPermutation));
        return;
    }

    for(int i= 0 ; i<nums.length; i++){
        if(visited[i]) continue;

        currentPermutation.add(nums[i]);
        visited[i] = true;
        helperMethod(answer, nums, visited, currentPermutation);

        visited[i] = false;
        currentPermutation.remove(currentPermutation.size()-1);
    }
}

         