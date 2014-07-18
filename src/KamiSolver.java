import java.util.PriorityQueue;

public class KamiSolver {
	public int targetMoves;
	public KamiGraph problem;
	public PriorityQueue<KamiSolution> solutionQueue;

	public KamiSolver(int target, KamiGraph g) {
		targetMoves = target;
		problem = g;
	}

	public KamiSolution solveProblem() {
		solutionQueue = new PriorityQueue<KamiSolution>();
		solutionQueue.add(new KamiSolution());
		// heuristic search
		while (!solutionQueue.isEmpty()) {
			KamiSolution current = solutionQueue.poll();
			//System.out.println(current.moves.size() + " " + current.aStarPriority);
			KamiGraph g = problem.doSolution(current);
			if (g.nodes.size() == 1){
				System.out.println("--- ANSWER ---");
				return current;
			}
			generateSolutions(g, current);
		}
		System.out.println("No answer :(");
		return null;
	}

	public void generateSolutions(KamiGraph g, KamiSolution baseSolution) {
		for (KamiNode node : g.nodes.values()) {
			for (int color : problem.colors.keySet()) {
				KamiSolution newSolution = baseSolution.clone();
				KamiGraph newGraph = g.clone();
				KamiMove newMove = new KamiMove(node.id, color);
				int heuristic = newGraph.doMove(newMove);
				// add solution if its on track to meet target moves
				if (heuristic + baseSolution.moves.size() <= targetMoves && newGraph.validLastMove) {
					newSolution.addMove(node.id, color, heuristic);
					solutionQueue.add(newSolution);
				}
			}
		}
	}
}
