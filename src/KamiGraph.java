import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class KamiGraph {
	public HashMap<Integer, KamiNode> nodes;
	public HashMap<Integer, Integer> colors;
	// last performed move removed nodes
	public boolean validLastMove = true;
	
	// ENCODE THE MOVES AND COPY THE GRAPH AND SIMULATE EACH MOVE - OUTPUT "SOLUTION OBJECT" THAT TIES METRICS TO MOVE SEQUENCE

	public KamiGraph(KamiGrid g) {
		nodes = new HashMap<Integer, KamiNode>();
		colors = new HashMap<Integer, Integer>();
		KamiNode[][] nodeArr = new KamiNode[g.colorGrid.length][g.colorGrid[0].length];
		for (int i = 0; i < g.colorGrid.length; i++) {
			for (int j = 0; j < g.colorGrid[0].length; j++) {
				nodeArr[i][j] = new KamiNode(g.colorGrid[i][j], i, j, i * g.colorGrid[0].length + j);
				System.out.print((i * g.colorGrid[0].length + j) + " ");
				incrementColor(g.colorGrid[i][j]);
			}
			System.out.println();
		}
		System.out.println();

		for (int i = 0; i < g.colorGrid.length; i++) {
			for (int j = 0; j < g.colorGrid[0].length; j++) {
				if (i > 0)
					nodeArr[i][j].addAndHashNeighbor(nodeArr[i - 1][j]);
				if (i < g.colorGrid.length - 1)
					nodeArr[i][j].addAndHashNeighbor(nodeArr[i + 1][j]);
				if (j > 0)
					nodeArr[i][j].addAndHashNeighbor(nodeArr[i][j - 1]);
				if (j < g.colorGrid[0].length - 1)
					nodeArr[i][j].addAndHashNeighbor(nodeArr[i][j + 1]);
			}
		}
		fixGraph(nodeArr[0][0], new HashSet<KamiNode>(), new HashSet<KamiNode>());
		System.out.println(g);
		System.out.println(this);
		System.out.println(graphHeuristic());
	}
	
	public KamiGraph(HashMap<Integer, KamiNode> nodes, HashMap<Integer, Integer> colors){
		this.colors = colors;
		this.nodes = nodes;
	}

	public KamiNode fixGraph(KamiNode n, HashSet<KamiNode> visited, HashSet<KamiNode> removed) {
		// stop if visited or removed
		if(visited.contains(n) || removed.contains(n))
			return null;
		nodes.put(n.id, n);
		visited.add(n);
		
		// combine connected tiles of the same color
		ArrayList<KamiNode> newNeighborsArr = new ArrayList<KamiNode>();
		for(KamiNode neighbor : n.neighborHash){
			assert n != neighbor;
			if(n.color == neighbor.color){
				newNeighborsArr.add(neighbor);
			}
		}
		
		// if neighbors are touching any same colored neighbors continue to combine
		int newNeighbor = 0;
		while(newNeighbor < newNeighborsArr.size()){
			for(KamiNode newNeighborNeighbor : newNeighborsArr.get(newNeighbor).neighborHash){
				if(n.color == newNeighborNeighbor.color && n != newNeighborNeighbor){
					newNeighborsArr.add(newNeighborNeighbor);
				}
			}
			n.absorbNode(newNeighborsArr.get(newNeighbor));
			// its been absorbed so ignore it in the future
			removed.add(newNeighborsArr.get(newNeighbor));
			newNeighbor++;
		}
		
		// recurse for rest of graph
		KamiNode[] tmpArr = new KamiNode[n.neighborHash.size()];
		n.neighborHash.toArray(tmpArr);
		for(int i = 0; i < tmpArr.length; i++){
			fixGraph(tmpArr[i], visited, removed);
		}
		
		return n;
	}
	
	public KamiGraph doSolution(KamiSolution solution){
		KamiGraph g = clone();
		for(int m = 0; m < solution.moves.size(); m++){
			g.doMove(solution.moves.get(m));
		}
		return g;
	}
	
	public int doMove(KamiMove move){
		KamiNode n = nodes.get(move.id);
		decrementColor(n.color);
		n.color = move.color;
		incrementColor(n.color);
		int numNodes = nodes.size();
		// absorb same colored neighbors
		ArrayList<KamiNode> toAbsorb = new ArrayList<KamiNode>();
		for(KamiNode neighbor : n.neighborHash){
			if(n.color == neighbor.color){
				toAbsorb.add(neighbor);
			}
		}
		for(int i = 0; i < toAbsorb.size(); i++){
			n.absorbNode(toAbsorb.get(i));
			nodes.remove(toAbsorb.get(i).id);
			decrementColor(n.color);
		}
		validLastMove = nodes.size() < numNodes;
		return graphHeuristic();
	}
	
	public KamiGraph clone(){
		HashMap<Integer, KamiNode> newNodes = new HashMap<Integer, KamiNode>();
		// clone nodes
		for(KamiNode n : nodes.values()){
			newNodes.put(n.id, new KamiNode(n));
		}
		// connect clones
		for(KamiNode n : nodes.values()){
			for(KamiNode neighbor : n.neighborHash){
				newNodes.get(n.id).neighborHash.add(newNodes.get(neighbor.id));
			}
		}		
		// copy colors
		HashMap<Integer, Integer> newColors = new HashMap<Integer, Integer>();
		for(int c : colors.keySet()){
			newColors.put(c, colors.get(c));
		}
		return new KamiGraph(newNodes, newColors);
	}
	
	public void incrementColor(int colorId){
		if(colors.containsKey(colorId)){
			colors.put(colorId, colors.get(colorId) + 1);
		} else {
			colors.put(colorId, 1);
		}
	}
	
	public void decrementColor(int colorId){
		if(colors.get(colorId) == 1)
			colors.remove(colorId);
		else
			colors.put(colorId, colors.get(colorId) - 1);
	}
	
	public int graphHeuristic(){
		int minDist = -1;
		for(KamiNode n : nodes.values()){
			int nextDist = furthestNodeDistance(n);
			minDist = minDist > -1 ? Math.min(nextDist, minDist) : nextDist;
		}
		return minDist;
	}
	
	public int furthestNodeDistance(KamiNode n){
		HashSet<KamiNode> foundNodes = new HashSet<KamiNode>();
		Queue<KamiNode> bfs = new LinkedList<KamiNode>();
		bfs.add(n);
		bfs.add(null);
		int distance = 0;
		while(!bfs.isEmpty()){
			KamiNode current = bfs.poll();
			if(!foundNodes.contains(current)){
				for(KamiNode neighbor : current.neighborHash){
					if(!foundNodes.contains(neighbor))
						bfs.add(neighbor);
				}
			}
			foundNodes.add(current);
			if(bfs.peek() == null){
				bfs.poll();
				if(bfs.size() > 0){
					bfs.add(null);
					distance++;
				}
			}
		}
		return distance;
	}
	
	public String toString(){
		StringBuilder str = new StringBuilder();
		for(KamiNode n : nodes.values()){
			str.append(n.color + " :");
			for(KamiNode neighbor : n.neighborHash){
				str.append(" " + neighbor.color);
			}
			str.append("\n");
		}
		return str.toString();
	}
}