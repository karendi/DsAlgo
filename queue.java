Class Queue{
    int front;
    int rear;
    int size;
    int[] queueArray;


    Queue(int size){
        // while displaying a queue, you should start from front+1, but increase the i by (i+1)%size, and move till you get
        // to the (rear+1)%size
        // when using circular queue we start at 0, and when using two pointers method where we reset the pointers we start at -1
        this.front = 0;
        this.rear = 0;
        this.size = size;
        this.queueArray = new int[size];
    }

    private void enQueue(int val){
        // first check that the queue is not full
        int rear = (this.rear+1)%this.size;
        if(!isFull(rear)){
            this.rear = rear;
            this.queueArray[rear] = val;
        } else{
            System.out.println("The queue is full")
        }
    }

    private boolean isFull(rear){
        if(rear == this.front ){
            return true;
        }

        return false;
    }

    private void deQueue(){
        // first check that the queue is not empty
        int front = (this.front+1)%this.size;
        if(!isEmpty(front)){
            this.front = front;
            this.queueArray[this.front] = Integer.MIN_VALUE;
        } else{
            System.out.println("The queue is empty")
        }
    }

    private boolean isEmpty(front){
        if(front == this.rear){
            // // I should make our pointers point to the beginning of the array
            // this.front = -1;
            // this.rear = -1; -> we are now using a circular array
            return true;
        }

        return false;
    }
}