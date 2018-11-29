package TransClosure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class TransClosureCandyState {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

Scanner sc= null;

		
		File f = new File("RoadPairs.txt");
		
		HashMap<String,Integer> indices= new HashMap<String,Integer>(); 
		
		
			try {
				sc= new Scanner(f);
			
			if(sc.hasNextLine()){
			
			int numRoads= sc.nextInt();
			if(numRoads!=0){
			String stateStart="";
			
			int[][] arr= new int[numRoads][numRoads];

			//initialize all values of the array to 10000000 and 0 for each state to itself
			for(int i=0;i<numRoads;i++){
				for(int j=0;j<numRoads;j++){
				arr[i][j] =10000000;
				arr[i][i]=0;
				}
			}
			
			
			for(int i=0;i<numRoads;i++){	
			stateStart=sc.next();//get the state
			
			//puts key values of a state and its index in the matrix
				if(!indices.containsKey(stateStart)){
					indices.put(stateStart, i);
				}
				
				if(sc.hasNextLine()){
					sc.nextLine();
				}
			}//end for

			
			sc=new Scanner(f);
			
			
			
			
			
			HashMap<String,LinkedList<String>> directRoads= new HashMap<String,LinkedList<String>>(); 
			//HashMap<String,Boolean> visited= new HashMap<String,Boolean>();
			
				sc= new Scanner(f);
				
				//int numRoads= sc.nextInt();
				 stateStart="";
				String stateDestination="";
				
				sc.nextLine();
				for(int i=0;i<numRoads;i++){
					
				LinkedList<String> destinationStates = new LinkedList<String>();	//connections to the state
				stateStart=sc.next();//get the state
				sc.next();
				stateDestination=sc.next();//get the state that can be reached from stateStart
				
				
				//places a state as the key and a linked list of its connections as the value
					if(!directRoads.containsKey(stateStart)){
						destinationStates.add( stateDestination);
						directRoads.put(stateStart, destinationStates);
					}else{
						directRoads.get(stateStart).add(stateDestination);
					}
					
					if(sc.hasNextLine()){
						sc.nextLine();
					}
				}//end for
				
				
				
				//creates a list of keys of the state
				ArrayList<String> keys= new ArrayList<String>(directRoads.keySet());
				
			
				
				//initializing values to 1's where there is a connection
				for(String k: keys){
					int i=indices.get(k);//get the index for the state
					LinkedList<String> dests= directRoads.get(k);//all the connections from that state
					
					for(int j=0;j<dests.size();j++){
						int l=indices.get(dests.get(j));//gets the index of each connection
						
						arr[i][l]=1;//initializes the connections to 1
					}
				}
				
		
				
				
				
				
				
				//Floyd-Warshall algorithm
				
				for(int k=0;k<numRoads;k++){
					for (int i=0;i<numRoads;i++){
						for(int j=0;j<numRoads;j++){
							if(arr[i][j]>arr[i][k]+ arr[k][j]){
								arr[i][j]=arr[i][k]+ arr[k][j];
							}
						}//end j loop
					}//end i loop
					
				}//end k loop
				
				
			
				
				//Read in the the pairs that need to be found
				int numRoutes=sc.nextInt();
				
				int[]distances=new int[numRoutes];
				
				String startRoute="";
				String endRoute="";
				
				for(int i=0;i<numRoutes;i++){
					startRoute=sc.next();
					sc.next();
					endRoute=sc.next();
					
					//get the indices of the pairs
				if(indices.containsKey(startRoute )){//check if the start state is one of the states
					int a=indices.get(startRoute);
					if(indices.containsKey(endRoute)){//check if the end state is one of the states
						int b= indices.get(endRoute);
						distances[i]=arr[a][b];
					}else{
						distances[i]=10000000;
					}	
				}else{
					distances[i]=10000000;
				}
				
				}//for
				
				
				for(int d :distances){
					System.out.print(d+" ");
				}
				
				}else{
				System.out.println("Number of roads is road");
				}
			}else{
				System.out.println("File is empty");
			}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
