function getCombinations(nums){
    if(nums.length == 0) return [];
    let answer = []; // the a
    helperMethod(nums, [], 0, answer, 0);
    return answer;
}

function helperMethod(nums, currentCombination, index, answer, y){
    // always add the currentCombination to our answer [], [1], [2] the currentpErm == nums.length calcul answer.size(=3)
    answer.push(Array.from(currentCombination)); // deep copy
    for(let i=index; i<nums.length; i++){ // acts as our base case
        currentCombination.push(nums[i]); 
        helperMethod(nums, currentCombination, i+1, answer); //we get here then we stop

        // the only time we pop is when we can't recurse cant call our method any more so that is when index is 3
        currentCombination.pop(); //index1 2, 1--> 2, index 1 [1]
        
        // at index 2 -> 
        // currentCombination -[1, 2] we have already seen them
        // index - 2
        y += 1;
        /****
         * 1, 2, 3 at index 3  2 ----> waiting 3, 2,  1, 0
         * 1, 2, 3 at index2 -loop??? 1, 2 -2 1, 2,3 
         * 1, 2 at index 1
         * index 1 -> [1]
         * 
         */
    }
}

getCombinations([1, 2, 3]);