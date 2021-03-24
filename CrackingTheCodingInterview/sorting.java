/*
** sorting techniques-> stable where in the case of duplicate elements, the order does not change from the prev sorting
-> comparison based                                   -> index based sorting techniques(0(n) time they are faster but take alot of space)
-Bubble, insertion, selection ->(O(n^2))                 -Radix sort
-Heap, Merge, Quick, Tree ->(O(nlogn))                   -Count sort
**                                                       -Bucket/Bin sort

bubble sort is adaptive by adding a flag to check if the data is not sorted. Adaptive sort refers to a sort that takes advantage
of the sorted order and does not require computational resources to do extra work. It is also stable it maintains the prev sorted order,
in the case of duplicates

bubble sorts compares n-1 times these are the passes
-> and every time you compare you compare till n-1-i where i is the current pass we are on.

Insertion Sort
------------------------
->  Insert element in a sorted position , larger elements are shifted when encountered.In array we don't have to find the position
O(n) --> shifting  for the array
-> Linked list there is no shifting required.
-> We assume that one element/ first element in the array is sorted and we start inserting the elements.
-> Take out the element and compare it with the elements 
-> it is similar to bubble sort where we have passes and the comparison loops
-> No of passes->(n-1), no of comparisons->(n^2)
-> Insertion sort is designed for linked list.. there is no shifting required

Insertion sort -> shifts while bubble sort, you swap
it is adaptive without adding a flag, in its nature.... it is adaptive no unecessary work 
when sorted it runs in O(n)
-> when array is not sorted by a couple of elements then inserion sort will take very little time
It is stable it won't change the order in case of duplicates
-> only bubble sort and insertion sort are adapative-> no unnecessary work in case where the array is sorted
3 algorithms are stable, i.e don't change prev sorted order-> bubble, insertion, merge
**

Stable sorting algorithms-> mergesort, bubblesort, insertion sort
Unstable -> selection sort, heapsort, quicksort

What matters when choosing a sorting algorithm -> time, memory, if data is restricted, stability


Selection Sort
------------------------
 -> Also requires n-1 passes just like bubble sort and insertion sort
 -> After one pass, we got the smallest element in the array, while for bubble sort after the first pass, we get the max number

**
**/
// for insertion sort, there is no way to get the correct order up until we get to the nth element
public static int[] insertionSort(int[] arr){
    // when insertion sort when the list is sorted then it will run in O(n)
    if(arr.length == 0){
        return arr;
    }
    
    //passes, we assume that the first number is sorted
    // we are just shifting the elements best for linked list
    for(int i=1; i<arr.length; i++){
        int prevIndex = i-1;
        int currNum = arr[i];
        
        while(prevIndex >=0 && arr[prevIndex] > currNum){
            arr[prevIndex+1] = arr[prevIndex];
            prevIndex--;
        }
        
        arr[prevIndex+1] = currNum;
    }
    
    
    return arr;
}

// for bubble sort we get the greatest element after the very first pass, so when reversing we will get the smallest number after the very first pass if we invert the comparator
public static String reverseBubbleSort(String num){
    // we know that on the very first pass, we have to carry out n-1 passes we will have sorted the greatest number
    // and it will be in the last position of the array so if we want the greatest number, what we need to do is get our number 
    // in the increasing order so in. the case we have 1233567 the greateset number would be 7653321
    // I will apply reverse bubble sort where we must execute n-1 passes, 
    
    if(num.length() == 0 || num.length() == 1){
        return num;
    }
    
    char[] arr = num.toCharArray();
    
    int arrLen = arr.length;
    
    for(int i=0; i<arrLen-1; i++){
        
        //introduce a flag that will enable us not do duplicate work that
        // is in the case that the number is already sorted in the ascending order
        // i.e make it adaptive
        boolean sorted = true;
        for(int j=0; j<arrLen-1-i; j++){
            
            if(arr[j] < arr[j+1]){
                // we are moving the less numbers down and moving the greater numbers to the top
                // if j < j+1, what we want to  do is move j+1 to the position that is currently j's
                sorted = false;
                swap(arr, j, j+1);
            }
        }
        
        if(sorted){
            break;
        }
        
        // 
        
    }
    
    return new String(arr);
}

/**
 * * for selection sort we get the least number at the very first pass
 * * also takes n-1 passes
 * *
 * * for n elements we swap O(n) swaps -> good for less number of swaps, perform only one pass you get the smallest elements
 *   K smaller elements
 *   adaptive-> helps reduce unecessary work,
 *  insertion sort is adaptive in nature while for bubble sort, we introduce a flag to make it adaptive
 *  stable-> does it maintain the prev order of sorting 
 * * it is not adaptive so it will always take O(n^2)
 *  it looses the original order, it is not stable, it will not preserve the order
 *  performs minimum number of swaps, and gives k smallest elements
 * 
 */
public static String selectionSort(String num){
    if(num.length() == 0 || num.length() ==1){
        return num;
    }
    
    char[] numArr = num.toCharArray();
    
    // we need an outer loop for the number of passes required i.e n-1
    // we will get the smallest number first after the very first pass
    // it is not adaptive so it will always take O(n^2) time but time need for swaps O(1) min swaps
    // we need j , k two pointers to check
    for(int i=0; i<numArr.length-1; i++){
        int k;
        for(int j=k=i; j<numArr.length; j++){
            
            if(numArr[j] < numArr[k]){
                k=j;
            }
        }
        
        swap(numArr, i, k);
    }
    
    return new String(numArr);
}

public static void swap(char[] arr, int index1, int index2){
    char temp = arr[index1];
    arr[index1] = arr[index2];
    arr[index2] = temp;
}

/*
* -> when the list is sorted in ascending order  or descending order it takes O(n^2) this is the worst case scenario
  -> the base case is when the numbers to be sorted are  one
  -> the best case scenario is when we sort/partion from middle and the work done is O(nlogn)
  -> average case time -> 0(nlogn)
*
**/
public static int[] quickSort(int[] nums, int low, int high){
       
    int j;
    
    if(low < high){
        j = partitionMethod(nums, low, high);
        quickSort(nums, low, j-1);
        quickSort(nums, j+1, high);
    }
    
    return nums;

}

public static int partitionMethod(int[] nums, int low, int high){
    
    // base case where we should just return i.e
    int pivot = nums[low];
    int i = low;
    int j = high;
    
    while(i < j){
        
        while(nums[i] <= pivot){
            i++;
        }
        
        
        while(nums[j] > pivot){
            j--;
        }
        
        if(i < j){
            swap(nums, i, j);
        }
        
    }
    
    // swap with the original low element, that is the pivot with j that is why we need to assign i and j to low and high
    // so that I don't loose my low 
    swap(nums, low, j);
    return j;

}
/**
 * *
 * * For merge sort, what we do is we view the input array as lists wth just one number, 
 *   so ideally each number is sorted as it is only one, then what we do is merge the numbers from the list
 *   and it results in a tree of height log(n) and each time n elements are merged hence the runtime is O(nlogn)
 *   total number of passes are log n hence the for loop will be for(i=2; i<= n; i*=2)
 * * You need low, high and mid
 * *
 * *
 * *
 * 
 */
  
   public static int partitionHelperMethod(int[] arr, int low, int high){
       int i = low;
       int j = high;
       int pivot = arr[low];
       
        System.out.println("low...."+low);
        
        System.out.println("high...."+high);
       
       while(i < j){
           
           while(arr[i] <= pivot){
               i++;
           }
           
           while(arr[j] > pivot){
               j--;
           }
           
           if(i < j){
               swap(arr, i, j);
           }
 
       }
       
       // we swap the pivot and j
       swap(arr, low, j);
       return j;
       
   }

   public static void mergeArr(int[] arr, int high, int mid, int low){
        
    int i= low;
    
    int j = mid+1;
    
    int k = low;
    
    int[] newArr = new int[100];
    
    System.out.println("index i....")
    
    while(i <= mid && j <= high){
        if(arr[i] < arr[j]){
            newArr[k] = arr[i];
            i++;
        } else{
            newArr[k] = arr[j];
            j++;
        }
        
        k++;
        
        System.out.println(newArr);
    }
    
    
    while(i <= mid){
        newArr[k] = arr[i];
        k++;
        i++;
    }
    
    while(j <= high){
        newArr[k] = arr[j];
        k++;
        j++;
    }
    
    
    //copy to the original array
    for(int index=low; index<=high; index++){
        arr[index]  = newArr[index];
    }
    
    
}




public static int[] mergeSort(int[] arr, int low, int high){
    int mid;
    if(low < high){
        mid = (low+high)/2;
        
        mergeSort(arr, low, mid);
        
        mergeSort(arr, mid+1, high);
        
        mergeArr(arr, low, mid, high);

    }
    
    // so we have 
    return arr;
    
}

/*
* CRACKING THE CODING INTERVIEW -> SORTING AND SEARCHING QUESTIONS
*
*
*
*
*
**/
public int sparseSearch(String[] strArr, String searchString){
    if(strArr.length == 0){
        return -1;
    }

    int low = 0;
    int high = strArr.length-1;

    while(low <= high){
        int mid = (low+high)/2;

        // we must consider the empty spaces, our mid might be an empty string
        if(strArr[mid].length() == 0){
            // we need to find the nearest non empty string either to the left or to the right
            int left = mid-1;
            int right = mid+1;

            while(true){
                // we cannot find the string in the preferred region
                if(left < low && right > high){
                    return -1;
                }

                if(left >= low && strArr[left].length() > 0){
                    mid = left;
                    break;
                }

                if(right <= high && strArr[right].length() > 0){
                    mid = right;
                    break;
                }

                right++;
                left--
            }

            // we do the normal binary search 
            if(strArr[mid].equals(searchString)){
                return mid;
            }

            if(strArr[mid].compareTo(searchString) < 0){
                low = mid+1;
            } else{
                high = mid-1;
            }
        }
        

    }
}

public static int findInRotatedArray(int[] nums, int target){
    if(nums.length == 0){
        return -1;
    }
    
    int low=0;
    int high=nums.length-1;
    
    /*
    * 1. we have to assume that we have 2 arrays and we should be able to check during our search
    if we are in the first sorted array or the second sorted array
    * how do we check for this we compare the number at mid with the start and the end nums 
    */
    while(low <= high){
        int mid = low + (high -low)/2;
        
        if(nums[mid] == target){
            return mid;
        }
        
        // check if we have found the first sorted array and if the num is in the first sorted array
        if(nums[mid] >= nums[low]){
            // we check if our target num is in the first sorted array
            if(nums[low] <= target && nums[mid] > target){
                high = mid-1;
            } else {
                low = mid+1;
            }
        } else{
            // we are in the second sorted array and now we should check if our target num is in this second array
            if(nums[mid] <= target && nums[high] > target){
                low = mid;
            }else{
                high = mid-1;
            }
        }
        
    }
    
    
    return -1;
}
/*
* The first way we employ a two pass sort, we first sort then merge. The sort employs a K-merge approach rather than a series 
of 2 way merge as merge sort does: 
    1. We are working with 20GB of data and lets say 1GB of ram, we know that at 
    any moment we are able to sort 1GB worth of data in memory
    2. We can read 1GB data from the file and sort it in memory using quicksort,
       as it is in place and no more additional memory is required. 
    3. We write out the sorted data to an external file .. results in having 20 chunks of sorted data
    4. we perform a K-merge where we read data from the 20 chunks and merge them to a file
    5. For our memory we have to keep consideration of input buffer for each of our 20 chunks and the output buffer,
       where the merge will happen. Once the output buffer is full we should write it to our file and empty it.
       When either of the input buffers is full we should read in more data from the chunks
       Ideally the output buffer should be greater in size than the total allocated input buffers for increased performance
       
*
*
*
*
*
*/

// worst case scenario we might have to search in linear time O(n)
public boolean searchRotatedDuplicateArray(int[] nums, int target) {
    if(nums.length == 0){
        return false;
    }
    /*
    * we will apply binary search but cater for special cases 
      where we find that the mid number that advances our binary search is a duplicate number 
      we should move it forward till we get the num that is not similar and assign the prev index to our mid index;
    *
    *
    */
    int start = 0;
    int end = nums.length-1;
    
    while(start <= end){
        
        int mid = start + (end-start)/2;

        // continue with the normal binary search
        if(nums[mid] == target){
            return true;
        }
        
        // only adjust the mid index if it has duplicate values
        if(nums[mid] == nums[mid+1]){
            int next = mid+1;
            
            while(true){
                // if we get to the very end of the list/array, meaning we are not able to get the number
                if(next >= nums.length){
                    return false;
                } 
                
                if(nums[next] != nums[mid]){
                    mid = next-1;
                    break;
                }
                
                next +=1;
            }
            
        }
  
        // since the array is rotated we have to check if we are in a sorted array at the moment
        if(nums[mid] >= nums[start]){
            // we are in the 'first' array, we check that our target is contained in this arrray
            if(nums[start] <= target && nums[mid] > target){
                // we are in the right region
                end = mid-1;
            } else{
                // we should search onwards
                start = mid+1;
            }
        } else{
            // we are currently in the 'second' array
            if(nums[mid] <= target && nums[end] > target){
                start = mid+1;
            } else{
                end = mid-1;
            }
        }
 
    }
    
    
    return false;
    
}