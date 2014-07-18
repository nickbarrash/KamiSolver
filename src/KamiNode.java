import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;


public class KamiNode {
	boolean isCombined;
	int color;	
	int id;
	HashSet<KamiNode> neighborHash;
	int x;
	int y;
	
	public KamiNode(int color, int x, int y, int id){
		this.color = color;
		neighborHash = new HashSet<KamiNode>();
		isCombined = false;
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public KamiNode(KamiNode n){
		isCombined = n.isCombined;
		color = n.color;
		id = n.id;
		x = n.x;
		y = n.y;
		neighborHash = new HashSet<KamiNode>();
	}
	
	public void setColor(int newColor){
		color = newColor;
		// absorb all nodes that are the same color
	}
	
	public void absorbNode(KamiNode n){
		// add node's neighbors
		for(KamiNode newNeighbor : n.neighborHash){
			if(newNeighbor != this)
				neighborHash.add(newNeighbor);
		}		
		
		// fix neighbor references to absorbed node
		for(KamiNode neighbor : neighborHash){
			neighbor.neighborHash.remove(n);
			neighbor.neighborHash.add(this);
		}
		
		neighborHash.remove(n);
	}
	
	public void addAndHashNeighbor(KamiNode n){
		if(!neighborHash.contains(n)){
			neighborHash.add(n);
		}
	}	
}
