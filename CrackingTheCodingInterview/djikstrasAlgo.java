import java.util.*;

public class MyClass {
    public static void main(String args[]) {
      int x=10;
      int y=25;
      int z=x+y;

      System.out.println("Sum of x+y = " + z);
      
      HashMap<Integer, ArrayList<int[]>> graph = new HashMap<>();
      
      graph.put(1, new ArrayList<int[]>());
      graph.get(1).add(new int[]{2, 2});
      graph.get(1).add(new int[]{3, 5});
      graph.put(2, new ArrayList<int[]>());
      graph.get(2).add(new int[]{3, 1});
      graph.get(2).add(new int[]{1, 2});
      graph.get(2).add(new int[]{5, 3});
      graph.put(3, new ArrayList<int[]>());
      graph.get(3).add(new int[]{5, 4});
      graph.get(3).add(new int[]{4, 6});
      graph.get(3).add(new int[]{2, 1});
      graph.get(3).add(new int[]{1, 5});
      graph.put(5, new ArrayList<int[]>());
      graph.get(5).add(new int[]{2, 3});
      graph.get(5).add(new int[]{3, 4});
      graph.get(5).add(new int[]{4, 7});
      graph.put(4, new ArrayList<int[]>());
      graph.get(4).add(new int[]{5, 7});
      graph.get(4).add(new int[]{3, 6});
      
      generateShortestPathTree(graph, 1);

    }
    
    static public class PathObject{
        public int distance;
        public int previous;
        
        PathObject(int distance, int previous){
            this.distance = distance;
            this.previous = previous;
        }
    }
    
    // run time is O((V+E) log V) if we use an array it will be O(v^2)
    public static List<Integer> generateShortestPathTree(HashMap<Integer, ArrayList<int[]>> graph, int sourceVertex){
        
        // key take aways have the min heap only having the source vertex while assigining the distances 
        // and then every time you calculate a distance that is less than the prev one add it to the min heap and then explore its neighbours
        
        List<Integer> answer = new ArrayList<>();
        
        if(graph.isEmpty()){
            return answer;
        }
        
        /*
        * -> so we have each vertice has a path option so at the very beginning:
           1-> source vertice -> 0 path distance
           2-> infinity
           3-> infinity
           4-> infinity
           5-> infinity
           
           so we have the path distance and we can have them in a min heap where we choose the distance that 
           is the least and then we check its unvisited nodes and update the distance 
        *
        *
        *
        *
        **/
        
        // keeps track of the distances from the source object and is being continuously updated
        // vertex: pathobject<distance, previousVertex>
        HashMap<Integer, PathObject> distance = new HashMap<>();
        
        
        // the priority queue which holds the min distances.... and min distance every time is extracted, it has a custom comparator function that extracts the min distance
        // from the distance map 
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b)->{
            return distance.get(a).distance - distance.get(b).distance;
        });
        
        // create the distance... map set all the vertex to infinity/max distance except the source vertex and then have the source vertex in the min heap
        for(int vertex: graph.keySet())
        {
            if(vertex == sourceVertex){
                
                PathObject newObj = new PathObject(0, vertex);
                
                distance.put(vertex, newObj);
                
                minHeap.add(vertex);
                
                
            } else {
                
                PathObject newObj = new PathObject(Integer.MAX_VALUE, -1);
                
                distance.put(vertex, newObj);
            }
            
 
            
        }
        
        while(!minHeap.isEmpty()){
            int currentVertex = minHeap.poll();
            
            for(int[] vertex: graph.get(currentVertex))
            {
                int neighbourVertex = vertex[0];
                
                int vertexDistance = vertex[1];
                
                
                
                int newCalculatedDistance = distance.get(currentVertex).distance + vertexDistance;
                
             
                
                if(newCalculatedDistance < distance.get(neighbourVertex).distance)
                {
                    
                    
                    distance.get(neighbourVertex).distance = newCalculatedDistance;
                    
                    distance.get(neighbourVertex).previous = currentVertex;
                    
                    minHeap.add(neighbourVertex);
                
                    
                }

                
                
                
            }
            
            
            
            
        }
        
        
        for(int vertex : distance.keySet()){
            
            PathObject currObj = distance.get(vertex);
            
            
            answer.add(currObj.distance);
            
            System.out.println("----------------------------->");
            System.out.println("vertex...."+vertex);
            System.out.println("distance....."+currObj.distance);
            System.out.println("prev...."+ currObj.previous);
            System.out.println("----------------------------->");

        }
        
        
        System.out.println(answer);
        return answer;
    }
}