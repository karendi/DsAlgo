public class MyClass {
    public static void main(String args[]) {
      int x=10;
      int y=25;
      int z=x+y;

      System.out.println("Sum of x+y = " + z);
    }
    
    public List<String[]> scheduler(List<String[]> calendar1, List<String[]> calendar2, String[] bounds1, String bounds2, int duration)
    {
        List<String[]> answer = new ArrayList<>();
        
        if(calendar1.size() == 0 || calendar2.size() == 0 || duration == 0) return answer;
        
        //1. we have 24 hours in a day,so we should view it as from '0.00 to start time, he is in a meeting or rather occuppied and from end time to 23.59 also occupied'
        List<int[]> updatedCalendar1 = updateCalendar(calendar1, bounds1); 
        List<int[]> updatedCalendar2 = updateCalendar(calendar2, bounds2);
        List<int[]> mergedCalendar = mergeCalendars(updatedCalendar1, updatedCalendar2);
        List<int[]> flattenedCalendar = flattenCalendar(mergedCalendar);
        List<int[]> getMeetingIntervals = getMeetingPoints(flattenedCalendar, duration);
        
       
    }
    
    
    public List<int[]> updateCalendar(List<String[]> calendar, dailyBound){
        
        List<int[]> newCalendar = new ArrayList<>();
        
        String[] newStartMeeting = new String[]{"0:00", dailyBound[0]};
        
        String[] newLastMeeting = new String[]{dailyBound[1], "23.59"};
        
        newCalendar.add(convertToMinutes(newStartMeeting));
        
        for(int i=0; i<calendar.size(), i++){
            newCalendar.add(convertToMinutes(calendar.get(i));
        }
        
        newCalendar.add(convertToMinutes(newLastMeeting));
        
        
    }
    
    public List<int[]> mergeCalendars(List<int[]> calendar1, List<int[]> calendar2)
    {
        List<int[]> mergedCalendar = new ArrayList<>();
        mergeCalendar.add(calendar1.get(0));
        
        int i, j = 0;
        
        while(i < calendar1.size() && j < calendar2.size()){
            // we will sort them according to the start time
            if( calendar1[i][0] < calendar2[j][0] ){
                mergedCalendar.add(calendar1.get(i));
                i+=1;
            } else {
                mergedCalendar.add(calendar2.get(i));
                j+=1;
            }
        }
        
        
        while(i < calendar1.size()){
            mergedCalendar.add(calendar1.get(i));
            i+=1;
        }
        
        while(j < calendar.size()){
            mergedCalendar.add(calendar2.get(j));
            j+=1;
        }
        
        return mergedCalendar;

    }
    
    
    public List<int[]> flattenCalendar(List<int[]> calendar)
    {
        List<int[]> newCalendar = new ArrayList<>();
        newCalendar.add(calendar.get(0));
        
        for(int i=1; i<calendar.size(); i++){
            
            int[] previousMeeting = newCalendar.get(newCalendar.size()-1);
            int[] currentMeeting = calendar.get(i);
            
            // meaning that the meetings overlap
            if(previousMeeting[1] >= currentMeeting[0]){
                // we know that the merged calendar is sorted in terms of start time so meaning that the prev meeting is less in terms of start time
                newCalendar.add(new int[]{ previousMeeting[0], 
                                           Math.max(previousMeeting[1], currentMeeting[1]) 
                    
                                        });
            } else {
                newCalendar.add(currentMeeting);
            }
            
        }
        
        return newCalendar;
        
    }
    
    public List<int[]> getMeetingPoints(List<int[]> calendar, int duration){
        for(int )
    }
    
    public int[] convertToMinutes(String[] time){
        int[] newTime = new int[2];
        
        for(int i=0; i<2; i++){
            String[] availableTimes = time[i].split(':');
            int newTime = 60 * Integer.parseInt(availableTimes[0]) + Integer.parseInt(availableTimes[1]);
            newTime[i] = newTime;
        }
        
        return newTime;
    }
}