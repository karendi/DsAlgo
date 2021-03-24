// heap can be implemented by an array, you add elements to the array and the relationship btwn the parent and children is 
// parent = i
// left = i*2
// right = i*2 +1
// what if we start at 0 ? parent = 0, left = 1, right = 2, what is the relationship?
// 0+1... 0+2....left ---> 1--> 1*2 +1 1*2 +2 
//  0 ----> 1, 2
//  1 ----> 3, 4
//  2 ----> 5, 6
// that is the relationship parent = i, left = 2*i + 1, right = 2*i +2 
public class BinaryHeap{

    // we have the array, will use a resizable array
    List<Integer> heap;
    int size;
    int topElement;

    //constructor
    BinaryHeap(){
        // we create a new arraylist and set the size at 0
        heap = new ArrayList<>();

        heap.add(-Integer.MIN_VALUE);

    }

    /* For inserting a value into the heap, we add at the very last spot in the array
     * because we want to have a complete binary tree
     * and we will have to readjust the heap and ensure everything is in place
     * we are moving from the leaf to the root
     * 
     */
    public void insert(int value){
        if(heap.size() == 1){
            // there is an empty heap
            heap.add(value);

            return;
        } else {
            // we should always add at the last place in the array
            // because we want a complete binary tree
            heap.add(value);

            // we check that the heap is in order, this will take log(n)

            // this will represent the parent index
            int parentIndex = heap.size()-1;

            // we should continue as long as the child is less than the parent,
            // limit is when we get to the top element
            while( value > heap.get(parentIndex/2) && parentIndex  >= 1){

                heap.set(parentIndex, heap.get(parentIndex/2));
                // we traverse
                parentIndex = parentIndex/2;

            }

            heap.set(parentIndex, value);

        }
    }

    /*
    ** when deleting an element we replace the root with the the leaf node i.e the last element of the array
    ** then we re-adjust the heap we are moving from root to leaf , so the limit is array.size()-1
    * always delete the root
    * 
    */
    public int pop(){
        if(heap.size() == 1){
            // the heap is empty
            return -1;
        } else {
            // this is the return value 
            int returnValue = heap.get(1);

            // we set the last element in the array to the root
            int leafItem = heap.get(heap.size()-1);

            heap.set(1, leafItem);

            // we remove the last element
            heap.remove(heap.size()-1);

            // we need to re-adjust the heap
            int start = 1;
            int childIndex = start*2

            while(start < heap.size()){
                // we check which child node is greater
                if(heap.get(childIndex+1) > heap.get(childIndex)){
                    childIndex += 1;
                }

                if(heap.get(start) < heap.get(childIndex)){
                    // move the child to the root and the root to the child index
                    int temp = heap.get(start);
                    heap.set(start, heap.get(childIndex));
                    heap.set(childIndex, temp);
                    start = childIndex;
                    childIndex = start*2;
                } else {
                    // the heap is in good order
                    break;
                }
            }

            return returnValue;

        }
    }



}