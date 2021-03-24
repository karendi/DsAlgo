/*
* out degree + in degree --> adjacency matrix can rep both so for put degree check the row,
  for the in degree check the column 

  for adjacency list-> only has the out degree

  Directed graph |V|+|E| --> space
  Undirected graph |V| + |2E| --> space

  Directed Graph
  ---------------------
  -> direction of the edge is defined to a particular node
  Directed Acyclic Graph
  -----------------------
  -> A directed graph with no cycle

  Connected graph--> there is a path from every vertice to every other verice
  Complete graph --> there is an edge btwn all the vertices

*
*
*
*
*
*
*
*
* 
*/
public class MyClass {
    public static void main(String args[]) {
      int x=10;
      int y=25;
      int z=x+y;

      System.out.println("Sum of x+y = " + z);
      
      HashMap<Integer, HashSet<Integer>> adjList = new HashMap<>();
  
      adjList.put(1, new HashSet<>());
      
      adjList.get(1).add(2);
      adjList.get(1).add(3);
      
      
      adjList.put(2, new HashSet<>());
      
      adjList.get(2).add(1);
      adjList.get(2).add(3);
      adjList.get(2).add(4);
      
      adjList.put(3, new HashSet<>());
      
      adjList.get(3).add(4);
      adjList.get(3).add(2);
      adjList.get(3).add(1);
      
      adjList.put(4, new HashSet<>());
      
      
      adjList.get(4).add(2);
      adjList.get(4).add(3);
      adjList.get(4).add(5);
      adjList.get(4).add(6);
      
      adjList.put(5, new HashSet<>());
      adjList.put(6, new HashSet<>());
      
      
      List<Integer> bfsOrder = getBfs(adjList);
      
      System.out.println(bfsOrder);
      
      
    }
    
    public static List<Integer> getBfs(HashMap<Integer, HashSet<Integer>> adjList){
        
        List<Integer> answer = new ArrayList<>();
        
        if(adjList.isEmpty()){
            return answer;
        }
        /*
        * we first add the starting vertex, to the visited hashset and answer array and add it to the queue
        
        
        then we get into the loop and only add the vertex to the visited set, answer array and queue as long as it has not been visited.
        *
        *
        */
        
        ArrayDeque<Integer> queue = new ArrayDeque<>();
                
        HashSet<Integer> visited = new HashSet<>();
        
        
        queue.add(1);
        
        visited.add(1);
        
        answer.add(1);
        

        

        // order of V+|E| 
        
        while(!queue.isEmpty()){
            
            int currentVertex = queue.poll(); // the current vertex we are getting here has already been visited.
          
                 
            for(int vertex: adjList.get(currentVertex)){
                if(!visited.contains(vertex)){
                    
                    answer.add(vertex);
                    
                    
                    visited.add(vertex);
                    
                    
                    queue.add(vertex);
                }
            }
            
        }
        
        return answer;
    }
}

// preorder root left right-> same as dfs in graphs
 public static List<Integer> getDfs(HashMap<Integer, HashSet<Integer>>adjList){
        
        List<Integer> answer = new ArrayList<>();
        
        if(adjList.isEmpty()){
            return answer;
        }
        
        helperMethod(adjList, 3, new HashSet<>(), answer);
        
        return answer;
    }
    
    public static void helperMethod(HashMap<Integer, HashSet<Integer>> adjList, int currVertex, HashSet<Integer> visited, List<Integer> answer)
    {
        
        visited.add(currVertex);
        answer.add(currVertex);
        
        
        for(int vertex: adjList.get(currVertex)){
            if(!visited.contains(vertex)){
                helperMethod(adjList, vertex, visited, answer );
                
            }
        }
        
    }