import java.util.*;

public class MyClass {
    public static void main(String args[]) {
      int x=10;
      int y=25;
      int z=x+y;

      System.out.println("Sum of x+y = " + z);
      HashMap<Integer, int[]> graph = new HashMap<>();
      
      graph.put(0, new int[]{3});
      graph.put(1, new int[]{4,0});
      graph.put(4, new int[]{3, 6});
      graph.put(3, new int[]{4, 5});
      graph.put(5, new int[]{6});
      graph.put(2, new int[]{7});
      graph.put(6, new int[]{7});
      
      
      
      List<List<Integer>> answer = findPathBtwnVertices(graph, 0, 7);
      
      for(List<Integer> xy: answer ){
          System.out.println(xy);
      }
    }
    
    public static List<List<Integer>> findPathBtwnVertices(HashMap<Integer, int[]> graph, int start, int stop){
        List<List<Integer>> answer = new ArrayList<>();

        if(graph.size() == 0) return answer;
        
        HashSet<Integer> visited = new HashSet<>();
        
        findPaths(answer, graph, new ArrayList<Integer>(), stop, start, visited );

        return answer;
    }
    
    public static void findPaths( List<List<Integer>> answer, HashMap<Integer, 
                                  int[]> graph, List<Integer> currentPath, 
                                  int stop, int currentVertex, HashSet<Integer> visited) {
        
        // what is the base case, when we get to the end vertice/ destination
        if(currentVertex == stop){
            // we should add the path,make a copy of the current list/array
            currentPath.add(currentVertex);
            answer.add(new ArrayList<>(currentPath));
            return;
        }
        
        
        // we should only add when we don't have a dead end, if we are here, that means that we have not
        // gotten to the destination yet and need to explore neighbours
        if(graph.get(currentVertex).length > 0){
            // add the current vertex
            currentPath.add(currentVertex);
            visited.add(currentVertex);
            
            for(int vertex: graph.get(currentVertex)){
                if(!visited.contains(vertex)){
                     findPaths(answer, graph, currentPath, stop, vertex, visited);
                     
                     // otherwise we should backtrack and remove the element
                     visited.remove(currentPath.get(currentPath.size()-1));
                     currentPath.remove(currentPath.size()-1);
                }
            }
            
            
        }
        
        return;
        
    }
}


import java.util.*;

public class MyClass {
    public static void main(String args[]) {

    
      HashMap<Integer, int[]> graph = new HashMap<>();
      
      graph.put(1, new int[]{2,3});
      graph.put(4, new int[]{3, 2, 5, 6});
      graph.put(3, new int[]{1, 5, 4});
      graph.put(5, new int[]{3, 4});
      graph.put(2, new int[]{4, 1});
      graph.put(6, new int[]{4});
      
      
      HashMap<Integer, List<Integer>> answer = createDirectedGraph(graph, 1);
      
      for(int key: answer.keySet()){
          System.out.println("key..."+key);
          System.out.println(answer.get(key));
      }


    }
    
    public static HashMap<Integer, List<Integer>> createDirectedGraph(HashMap<Integer, int[]> undirectedGraph, int start){
        
        // the answer that we will have to return
        HashMap<Integer, List<Integer>> directedGraph = new HashMap<>();
        
        // check if the graph provided is valid
        if(undirectedGraph.isEmpty()){
            return directedGraph;
        }
        
  
        
        // seperated it into paths from the two end vertices
        List<List<Integer>> paths1 = new ArrayList<>();
        List<List<Integer>> paths2 = new ArrayList<>();

        HashSet<Integer> visited = new HashSet<>();
        
        findPaths(paths1, undirectedGraph, 1, new ArrayList<Integer>(), 6, visited);
        findPaths(paths2, undirectedGraph, 1, new ArrayList<Integer>(), 5, visited);
        
 
        
        // now for each path gotten from the vertices
        // , we should check which paths can be created without any overlap/connected vertices, that is what we want to avoid
        List<List<Integer>> uniquePaths = new ArrayList<>();

        // we choose one list of paths to iterate through and check that t
        // here are no duplicate values in one of the paths of the second paths
        for(List<Integer>path: paths1){
            if(uniquePaths.size()==2){
                // we should stop once we find unique paths from both vertices and use them to create the directed graph
                break;
            } else{
                // else continue finding the unique paths
                findUniquePaths(path, paths2, uniquePaths, 0);
            }
        }


        // we have our unique paths and we should create our new directed graph
        int index1 = 0;
        int index2 = 0;
        
        List<Integer> uniquePath1 = uniquePaths.get(0);
        List<Integer> uniquePath2 = uniquePaths.get(1);

    
        while(index1 < uniquePath1.size()-1){
            List<Integer> children = new ArrayList<>();
            children.add(uniquePath1.get(index1+1));
        
            directedGraph.put(uniquePath1.get(index1), children);
            index1 += 1;
        }
        
        while(index2 < uniquePath2.size()-1){
            List<Integer> children = new ArrayList<>();
            children.add(uniquePath2.get(index2+1));
            
            directedGraph.put(uniquePath2.get(index2), children );
            index2 += 1;
        }
        
        
        
        
        return directedGraph;
    }
    
    public static void findUniquePaths(List<Integer> path, List<List<Integer>> paths2, List<List<Integer>> uniquePaths, int start){
        
        // check that we are not of bounds when checking for unique vertices in the second path
        if(start >= paths2.size()){
            return;
        }
        
        
        HashSet<Integer> currentPath2 = new HashSet<>(paths2.get(start));
        
        // we always start from the first index because we are interested in the mid values and not the start
        // or the end coz we are sure that the start values are the same and that the end values/vertices are different
        int index = 1;
        
        while(index <= path.size()-1){
            if(currentPath2.contains(path.get(index))){
                break;
            }
            index += 1;
        }
        
        
        if(index == path.size()-1){
            uniquePaths.add(path);
            uniquePaths.add(paths2.get(start));
            return;
        } else {
               
        
        // we continue checking the other paths
            findUniquePaths(path, paths2, uniquePaths, start+1);

        }

    }

    public static boolean findCycle(HashMap<Integer, List<Integer> adjList, int vertex, HashSet<Integer> visited, HashSet<Integer> inStack){
        // what is the base case?

        if(inStack.contains(vertex)){
            return true;
        }

        if(visited.contains(vertex)){
            return false;
        }

        visited.add(vertex);
        
        inStack.add(vertex);

        List<Integer> children = adjList.get(vertex);

        for(int i=0; i<children.size(); i++){
            if(!visited.contains(chilren.get(i))){
                findCycle(adjList, children.get(i), visited, inStack)
            }
        }

        // after we are done traversing the particular node we remove it from the inStack set
        inStack.remove(vertex);

        return false;
    }
    
    public static void findPaths( List<List<Integer>> paths, HashMap<Integer, 
                                  int[]> undirectedGraph, int start, List<Integer> currentPath, 
                                  int currentVertex, HashSet<Integer> visited )
    {
        
        // what are the base cases? when we get to the start
        if(currentVertex == start){
            currentPath.add(start);
            paths.add(new ArrayList<>(currentPath));
            return;
        }
        
        // we can get to a dead end, so we must check for this
        if(undirectedGraph.get(currentVertex).length > 0){
              currentPath.add(currentVertex);
              visited.add(currentVertex);
            
            for(int vertex: undirectedGraph.get(currentVertex)){
                
                if(!visited.contains(vertex)){
                    
                    findPaths(paths, undirectedGraph, start, currentPath, vertex, visited);
                    // we backtrack, to check for other paths
                    
                    visited.remove(currentPath.get(currentPath.size()-1));
                    currentPath.remove(currentPath.size()-1);
                }
                
            }
        
        } 
        return;
      

                                      
                                  
        
        
    }
}