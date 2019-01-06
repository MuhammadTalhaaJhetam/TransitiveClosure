package TransClosure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class CandyStates {
	private int arr[][];
	private HashMap<String,Integer> cities;
	private LinkedList<Integer> destinationDistances;
	
	
	//Constructor
	public CandyStates(HashMap<String,Integer> cityNames){

		this.cities= cityNames;
		this.arr= new int[this.cities.size()][this.cities.size()];
		this.destinationDistances=new LinkedList<Integer>();
		
		//initialize the array
		for(int i=0;i<this.arr.length;i++){
			for(int k=0;k<this.arr[0].length;k++){
				if(i==k){
					arr[i][k]=0;
				}else{
					arr[i][k]=10000000;
				}
			}
		}
	}
	
	
	public void initializeConnections(){
		//initialize the connections of the cities into the array
		File f= new File("RoadPairs.txt");
		try {
			Scanner sc= new Scanner(f);
			
			int numRoads=sc.nextInt();
			
			sc.nextLine();
			String start="";
			String destination="";
			int startIndex=0;
			int destinationIndex=0;
			
			for(int i=0;i<numRoads;i++){
				start=sc.next();
				sc.next();
				destination=sc.next();
			
				startIndex=this.cities.get(start);			
				destinationIndex=this.cities.get(destination);
				
				this.arr[startIndex][destinationIndex]=1;
				this.arr[destinationIndex][startIndex]=1;
				if(sc.hasNextLine()){
					sc.nextLine();
				}
			}
			
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	private void calculateDistances(){
		//Floyd-Warshall algorithm
		for(int k=0;k<this.arr.length;k++){
			for (int i=0;i<this.arr.length;i++){
				for(int j=0;j<this.arr.length;j++){
					if(this.arr[i][j]>this.arr[i][k]+ this.arr[k][j]){
						this.arr[i][j]=this.arr[i][k]+ this.arr[k][j];
					}
				}
			}	
		}
	}
	
	
	private void getResults(){
		//get distances between specified cities
		File f= new File("Roadpairs.txt");
		try {
			Scanner sc= new Scanner(f);
			int numRoads=sc.nextInt();
			sc.nextLine();
			for(int i=0;i<numRoads;i++){
				sc.nextLine();
			}
			int numRoadChecks= sc.nextInt();
			sc.nextLine();
			
			String start="";
			String destination="";
			int startIndex=0;
			int destinationIndex=0;
			
			for(int i=0;i<numRoadChecks;i++){
				start=sc.next();
				sc.next();
				destination=sc.next();
				
				startIndex=cities.get(start);
				destinationIndex=cities.get(destination);
				
				destinationDistances.add(this.arr[startIndex][destinationIndex]);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public int[][] getArr() {
		return arr;
	}


	public void setArr(int[][] arr) {
		this.arr = arr;
	}


	public HashMap<String, Integer> getCities() {
		return cities;
	}


	public void setCities(HashMap<String, Integer> cities) {
		this.cities = cities;
	}


	public LinkedList<Integer> getDestinationDistances() {
		return destinationDistances;
	}


	public void setDestinationDistances(LinkedList<Integer> destinationDistances) {
		this.destinationDistances = destinationDistances;
	}


	public void printDistances(){
		//print out the distances
		calculateDistances();
		getResults();
		
		for(int i=0;i<this.destinationDistances.size();i++){
			System.out.print(this.destinationDistances.get(i)+" ");
		}	
	}

}
