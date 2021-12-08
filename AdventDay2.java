import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class main2 {
	static int depth=0;
	static int horizontal=0;
	static int aim=0;

	public static void main(String[] args) {
		
		String fileContent="";
		try {
			fileContent =new String(Files.readAllBytes(Paths.get("C:\\Users\\Stefano\\Desktop\\advent.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}		
		String sarr[]=fileContent.split("\r\n");		
		String [] cmds=new String[sarr.length];
		int amounts[]=new int[sarr.length];		
		int j=0;
		try {
			for(String s:sarr) {
				cmds[j]=s.split(" ")[0];
				amounts[j]=Integer.parseInt((s.split(" ")[1]));
				j++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
		for(int i=0;i<cmds.length;i++) {
			if(cmds[i].equals("up"))up(amounts[i]);
			else if(cmds[i].equals("down"))down(amounts[i]);
			else if(cmds[i].equals("forward"))forward(amounts[i]);
		}
		System.out.println(""+depth+" "+horizontal+" "+(depth*horizontal));		
		
	}
	public static void down(int x) {
			//depth+=x;
			aim+=x;
		}
	public static void up(int x) {
		//depth-=x;
		aim-=x;
	}
	public static void forward(int x) {
		horizontal+=x;
		depth=(depth+(aim*x));
	}

}
 