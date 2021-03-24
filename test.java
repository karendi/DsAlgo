//i < nums.length
if(index >= nums.length){
    return answer;
}
// otherwise what should we do?
/****
 * [1, 2, 3]
 *  0 at index 0--> at whatever index we are we need to get to the very end
 * [0]
 * [0, 1]
 * [0, 1, 2]
 * ...... stop
 * [1]
 * [1, 2]
 * ...... stop
 * [2]
 *  we need a for loop -> start at a specified index that has come in, loop all the way to the end while creating subset
 */


 answer = [];


// we want to remove 1 from the equation
index +=1
for loop

mainMethod(int[] nums){
    if(nums.length == 0) return [];
    answer = new Array();
    answer.add(new Array());

    findPermutation(nums, 0, answer, new Array());
}


//helper method
 findPermutation(nums, index, answer, combinationAtIndex){

    // our base case is index < nums.length
    if(index >= nums.length){
        return answer
    }

//0 , 1,
    for(i=index, i<nums.length; i++){
        // we need to add the combination everytime?
        combinationAtIndex.add(nums[i]);
        //[1]
        answer.add(ArrayList<Integer>(combinationAtIndex));
        //[ [], [1], [1,2],  ]
   }
   //[3,2, 1][1, 2, 3]

   // we will use more space
   findPermutation(nums, index+1, answer, combinationAtIndex.clear());


}