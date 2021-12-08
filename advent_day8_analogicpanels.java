import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
		// \r\n
		String[] rows=fileContent.split("\r\n");
		List<String> inputs=new ArrayList<String>();
		List<String> outputs=new ArrayList<String>();
		for(String s:rows) {
			inputs.add(s.split(" @ ")[0]);
			outputs.add(s.split(" @ ")[1]);
		}
		System.out.println("solA: "+countA(outputs));
		// part B
		System.out.println("solb?? "+countB(inputs,outputs));
	}
	private static int countB(List<String> inputs, List<String> outputs) {
		int total=0;
		int i=0;
		for(String s:inputs) {
			String zero="",uno="",due="",tre="",quattro="",cinque="",sei="",sette="",otto="",nove="";
			List<String> unk=new ArrayList<String>();
			for(String z:s.split(" ")) {				
				if(z.length()==2)uno=z;
				else if(z.length()==3)sette=z;
				else if(z.length()==4)quattro=z;
				else if(z.length()==7)otto=z;
				else unk.add(z);
			}
			for(String z:unk) {
				int n=z.length();
				if(n==6&&findMatch(z,uno)==5) {
					sei=z;
				}
				else if(n==6&&findMatch(z,quattro)==2) {
					nove=z;
				}
				else if(n==6) {
					zero=z;
				}				
				if(n==5&&findMatch(z,uno)==3) {
					tre=z;
				}
				else if(n==5&&findMatch(z,quattro)==3) {
					due=z;
				}
				else if(n==5) {
					cinque=z;
				}
			}
			//process outputs finally?
			StringBuilder sb=new StringBuilder("");
			int subtot=0;
			for(String c:outputs.get(i).split(" ")) {
				int n=c.length();
				if(n==2)sb.append('1');
				else if(n==4)sb.append('4');
				else if(n==3)sb.append('7');
				else if(n==7)sb.append('8');
				else if(n==6)sb.append(length6(c,zero,sei,nove));
				else if(n==5)sb.append(length5(c,due,tre,cinque));
			}
			subtot=Integer.parseInt(sb.toString());
			total=total+subtot;
			i++;
		}		
		return total;
	}
	private static char length5(String c, String due, String tre, String cinque) {
		char[] temp=c.toCharArray();
		Arrays.sort(temp);
		c=new String(temp);
		temp=due.toCharArray();
		Arrays.sort(temp);
		due=new String(temp);
		temp=tre.toCharArray();
		Arrays.sort(temp);
		tre=new String(temp);
		temp=cinque.toCharArray();
		Arrays.sort(temp);
		cinque=new String(temp);
		if(c.equals(due))return '2';
		else if(c.equals(tre))return '3';
		else if(c.equals(cinque))return '5';
		return 'x';
	}
	
	private static char length6(String c, String zero, String sei, String nove) {
		char[] temp=c.toCharArray();
		Arrays.sort(temp);
		c=new String(temp);
		temp=zero.toCharArray();
		Arrays.sort(temp);
		zero=new String(temp);
		temp=sei.toCharArray();
		Arrays.sort(temp);
		sei=new String(temp);
		temp=nove.toCharArray();
		Arrays.sort(temp);
		nove=new String(temp);
		if(c.equals(zero))return '0';
		else if(c.equals(sei))return '6';
		else if(c.equals(nove))return '9';
		return 'z';
	}
	
	private static int findMatch(String z, String uno) {
		int n=z.length();
		for(char c:uno.toCharArray()) {
			for(char d:z.toCharArray()) {
				if(c==d)n--;
			}
		}		
		return n;
	}
	
	private static int countA(List<String> outputs) {
		int count=0;
		for(String s:outputs) {
			for(String z:s.split(" ")) {
				if(z.length()==2||z.length()==3||z.length()==4||z.length()==7) {
					count++;
				}
			}
		}
		return count;
	}
}