package cavessss;

import java.util.ArrayList;
import java.util.List;

public class Cave {
	public static int paths=0;
	public String name="";
	public String type="";
	public boolean visited=false;
	public boolean visited2=false;
	public static boolean canVisitSmallTwice=true;
	public int intvisited=0;
	public List<Cave> connections=new ArrayList<Cave>();
	
	public int step(int tot) {
		if(this.type.equals("small"))this.visited=true;
		if(this.type.equals("end")) {
			return tot+1;
		}
		for(Cave c:this.connections) {
			if(!c.type.equals("start")) {
				if(c.visited==false) {
					tot=c.step(tot);
				}
			}
		}
		this.visited=false;
		return tot;
	}

	public int stepB(int tot) {
		if(this.type.equals("small")&&this.visited==true)this.visited2=true;
		if(this.type.equals("small"))this.visited=true;
		if(this.type.equals("end")) {
			paths++;
			return (tot+1);
		}
		if(this.visited2==true) {
			canVisitSmallTwice=false;
		}

		for(Cave c:this.connections) {
			if(!c.type.equals("start")) {
				if(canVisitSmallTwice==false) {
					if(c.visited==true)continue;
				}
				if(c.visited2==false) {
					tot=c.stepB(tot);
				}
			}
		}
		if(this.visited2==true) {
			this.visited2=false;
			canVisitSmallTwice=true;
			return tot;
		}
		if(this.visited) {
			this.visited=false;
			return tot;
		}
		return tot;
	}
}