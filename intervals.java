public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if(firstList.length == 0 || secondList.length == 0){
            return new int[][]{};
        }
        
        /*
        * -> there is no interval when either list is empty
        * -> we know that there is an interval when we have:
            A_start_time A_end_time, B_start_time B_end_time;
            
            an interval occurs when 
            A_start_time <= B_end_time and A_end_time >= B_start_time
        *
        *  we need two pointers to traverse the two lists and get the intervals
           where what we will do is:
           interval_start_time = Math.max(A_start_time, B_start_time);
           interval_end_time = Math.min(A_end_time, B_end_time);
        *
        
        
        so we know that we have an interval, when starttime1 is less than endtime2 but endtime1 is greater than starttime2.
        to get the new interval we get the max of the start times and the min of the end times
        
        when advancing the pointers we advance the pointer with the least start time
        
        */
        
        List<int[]> foundIntervals = new ArrayList<>();
        
        int pointer1 = 0;
        int pointer2 = 0;
        
        while(pointer1 < firstList.length && pointer2 < secondList.length){
            
            int[] interval1 = firstList[pointer1];
            int[] interval2 = secondList[pointer2];
            
            int startTime1 = interval1[0];
            int startTime2 = interval2[0];
            
            int endTime1 = interval1[1];
            int endTime2 = interval2[1];
            
            if(startTime1 <= endTime2 && endTime1  >= startTime2){
                
                int[] newInterval = new int[]{
                    Math.max(startTime1, startTime2), 
                    Math.min(endTime1, endTime2)};
                
                foundIntervals.add(newInterval);
                
            }
            
            if(endTime1 < endTime2){
                
                pointer1 +=1;
                
            } else {
                
                pointer2 += 1;
                
            }
            
        }
        
        int[][] answer = new int[foundIntervals.size()][2];
        
        int index = 0;
        
        for(int[] interval: foundIntervals){
            
            answer[index] = interval;
            index++;
            
        }
        
        return answer;
        
    }