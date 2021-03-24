function findPermutations(str){

    if(str.length == 0) return [];

    let visited = [];
    let answer = [];

    helperMethod(answer, str, visited, [], "");

    return answer;
}

function helperMethod(answer, str, visited, currPerm, currPerm)
{
    if(currPerm.length == str.length){
        answer.push(currPerm);
        return;
    }

    for(i=0; i<str.length; i++){
        if(visited[i] == 1) continue;

        currPerm += str[i];
        visited[i] = 1;
        helperMethod(answer, str, visited, currPerm, str);

        currPerm.substring(0, currPerm.length()-1);
        visited[i] = 0;
    }

}

findPermutations("abc");