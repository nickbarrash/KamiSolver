
public class KamiMove {
	
	int id;
	int color;
	
	public KamiMove(int id, int color) {
		this.id = id;
		this.color = color;
	}

	public String toString(){
		return id + " " + color;
	}
}