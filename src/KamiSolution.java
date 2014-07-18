import java.util.ArrayList;


public class KamiSolution implements Comparable<KamiSolution>{
	
	ArrayList<KamiMove> moves;
	int aStarPriority;
	
	public KamiSolution(){
		moves = new ArrayList<KamiMove>();
		aStarPriority = 0;
	}
	
	public KamiSolution(ArrayList<KamiMove> moves, int heuristic){
		this.moves = moves;
		this.aStarPriority = heuristic;
	}
	
	public int compareTo(KamiSolution o) {
		return o.aStarPriority < aStarPriority ? 1 : (o.aStarPriority == aStarPriority ? 0 : -1);  
	}
	
	public void addMove(int id, int color, int heuristic){
		moves.add(new KamiMove(id, color));
		aStarPriority = heuristic + moves.size();
	}
	
	public int numMoves(){
		return moves.size();
	}
	
	public KamiSolution clone(){
		ArrayList<KamiMove> newMoves = new ArrayList<KamiMove>();
		for(int i = 0; i < moves.size(); i++){
			newMoves.add(moves.get(i));
		}
		return new KamiSolution(newMoves, aStarPriority);
	}
	
	public String toString(){
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < moves.size(); i++){
			str.append(moves.get(i) + "\n");
		}
		return str.toString();
	}
	
}
