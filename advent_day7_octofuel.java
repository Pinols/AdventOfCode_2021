import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class main2 {

	public static List<Integer> check=new ArrayList<Integer>();
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String fileContent="";
		try {
			fileContent =new String(Files.readAllBytes(Paths.get("C:\\Users\\Stefano\\Desktop\\advent.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] crabsString=fileContent.split(",");
		int[]crabs=new int[crabsString.length];
		int i=0;
		for(String s:crabsString) {
			crabs[i]=Integer.parseInt(s);
			i++;
		}
		//String[] rows=fileContent.split("\r\n");
		i=0;
		long min=0;
		long check=0;
		for(i=0;i<2000;i++) {
			check=calcFuel(crabs,i);
			if(i==0)min=check;
			else if(check<min)min=check;
		}
		System.out.println("Sol A: "+min); //48373220787
	}
	/*private static int calcFuel(int[] crabs, int i) {
		int tot=0;
		int steps=0;
		for(int c:crabs) {
			if(c>i)steps=c-i;
			else steps=i-c;
			tot+=steps;
		}
		return tot;
	}*/
	static long calcFuel(int[] crabs, int i){
		long tot=0;
		long steps=0;
		long fuel=0;
		for(int c:crabs) {
			fuel=0;
			if(c>i)steps=c-i;
			else steps=i-c;
			for(int j=1;j<steps+1;j++) {
				fuel+=j;
			}
			tot+=fuel;
		}
		return tot;
	}
}