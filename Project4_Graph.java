import java.io.*;
import java.util.*;

	class Project4_Graph<T> {

	    /*
	     * Variables
	     */
	    private Map<T, Integer> map;                
	    private ArrayList<LinkedList<Integer>> adjacencyLists;    
	    private int vertexValue = 0;                                      
	    private List<Integer> neighbors;                        
	    private StringBuilder recompliationOrder;  
	    private File fileInput;
	    private Scanner scanner;
	    private ArrayList<String[]> fileArray;
	    private String[] lineArray;
	    private String fileLine;
	    
	    /*
	     * construct graph
	     */
	    Project4_Graph() {
	    	
	    	adjacencyLists = new ArrayList<>();       
	        map = new HashMap<>();     
	    }

	    void buildGraph(ArrayList<T[]> tokensArray) {
	    	
	        for (T[] t : tokensArray) {   
	        	
	            for (int i = 0; i < t.length; i++) {   
	            	
	            	vertex(t[i]);     
	            	
	                if (i != 0) {      
	                	
	                	edge(t[0], t[i]);  
	                }
	            }
	        }
	    }
	    
	    /*
	     * tokenize file
	     */
	    @SuppressWarnings("resource")
		ArrayList<String[]> tokenize(String fileName) throws IOException {
			
	        fileInput = new File(fileName);   
	        scanner = new Scanner(fileInput).useDelimiter("\n"); 
	        fileArray = new ArrayList<>();  
	        int index = 0;

	        while (scanner.hasNext()) {  
	        	
	        	fileLine = scanner.next();
	            lineArray = fileLine.split("\\s"); 
	            fileArray.add(index,lineArray);                
	            index++;                                    
	        }
	        
	        scanner.close();
	        return fileArray;
	    }
		/*
		 * add vertex
		 */
	    private void vertex(T className) {
	    	
	        if (!map.containsKey(className)) {   
	        	
	        	map.put(className, vertexValue);               
	            LinkedList<Integer> adj = new LinkedList<>();   
	            adjacencyLists.add(vertexValue, adj);                           
	            vertexValue++;                                       
	        }
	    }
	    
	    /*
	     * add edge
	     */
	    private void edge(T vertexFrom, T vertexTo) {
	    	
	        int from = map.get(vertexFrom);    
	        int to = map.get(vertexTo);        
	        adjacencyLists.get(from).add(to);               
	    }
	    
	    /*
	     * returns sorted topological order
	     */
	    String topologicalOrder(T startVertex) throws InvalidClassNameE, ContainsCycleE {
	    	
	        if (map.get(startVertex) == null) {   
	        	
	            throw new InvalidClassNameE();      
	        }

	        recompliationOrder = new StringBuilder();         
	        neighbors = new ArrayList<>();          
	        depthFirstSearch(map.get(startVertex));     

	        return recompliationOrder.toString();
	    }
	    /*
	     * gets the name of the class from the index value
	     */
	    private String getName(int vertex) {
	    	
	        for (T t : map.keySet()) {   
	        	
	            if (map.get(t).equals(vertex)) {   
	            	
	                return t.toString();                    
	            }
	        }
	        
	        return "";
	    }
	    /*
	     * adds classname of visited vertexes to stringbuilder so that it can also detect cycles and throw exception
	     */
	    private void depthFirstSearch(int vertexValue) throws ContainsCycleE {
	    	
	    	recompliationOrder.append(getName(vertexValue)).append(" ");     
	    	
	        for (Integer i : adjacencyLists.get(vertexValue)) {    
	        	
	            if (neighbors.contains(i)) {    
	            	
	                throw new ContainsCycleE();       
	            }
	            
	            neighbors.add(i);                       
	            depthFirstSearch(i);                            
	        }
	    }
	}

