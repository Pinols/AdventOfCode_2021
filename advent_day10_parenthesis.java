import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class main2 {
	static List<Integer> is=new ArrayList<Integer>();
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String fileContent="";
		try {
			fileContent =new String(Files.readAllBytes(Paths.get("C:\\Users\\Stefano\\Desktop\\advent.txt")));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		// \r\n
		int i=0,j=0;
		int tot=0;
		String[] rows=fileContent.split("\r\n");
		for(String s:rows) {
			int n=scoreA(s);
			if(n>0)tot+=n;
			else is.add(i);
			i++;
		}
		System.out.println("sol A: "+tot);
		i=0;
		List<Long> scores=new ArrayList<Long>();
		for(String s:rows) {
			if(is.contains(i))scores.add(scoreB(s));
			i++;
		}
		Collections.sort(scores);
		System.out.println("Sol B: "+scores.get(scores.size()/2));
	}
	@SuppressWarnings("unused")
	private static long scoreB(String s) {
		int tonde=0,quadre=0,graffe=0,angolari=0;
		StringBuilder sb=new StringBuilder();
		char[] chars=s.toCharArray();
		Deque<Character> stack= new ArrayDeque<Character>();
		Character[] charArr= new String(chars).chars().mapToObj(c -> (char) c)
				.toArray(Character[]::new);
		for(Character c:charArr) {
			if(c.equals('('))stack.push(')');
			else if(c.equals('['))stack.push(']');
			else if(c.equals('{'))stack.push('}');
			else if(c.equals('<'))stack.push('>');
			else if(c.equals(')')&&(stack.poll()!=c)) stack.push(')');
			else if(c.equals(']')&&(stack.poll()!=c)) stack.push(']');
			else if(c.equals('}')&&(stack.poll()!=c)) stack.push('}');
			else if(c.equals('>')&&(stack.poll()!=c)) stack.push('>');
		}
		long tot=0;
		Map<Character,Integer> map=new LinkedHashMap<Character,Integer>();
		for(Character c:stack) {
			if(c.equals(')'))tonde++;
			else if(c.equals(']'))quadre++;
			else if(c.equals('}'))graffe++;
			else if(c.equals('>'))angolari++;
		}
		for(Character c:stack) {
			if(c.equals(')'))map.put(c,1);
			else if(c.equals(']'))map.put(c,2);
			else if(c.equals('}'))map.put(c,3);
			else if(c.equals('>'))map.put(c,4);
		}
		for(Character c:stack) {
			tot=(tot*5)+map.get(c);
		}
		return tot;
	}
	@SuppressWarnings("unused")
	private static int scoreA(String s) {
		char expected=' ';
		int tot=0;
		Deque<Character> stack= new ArrayDeque<Character>();
		char[] chars=s.toCharArray();
		Character[] charArr= new String(chars).chars().mapToObj(c -> (char) c)
				.toArray(Character[]::new);
		for(Character c:charArr) {
			if(c.equals('('))stack.push(')');
			else if(c.equals('['))stack.push(']');
			else if(c.equals('{'))stack.push('}');
			else if(c.equals('<'))stack.push('>');
			else if(c.equals(')')&&(stack.poll()!=c))return 3;
			else if(c.equals(']')&&(stack.poll()!=c))return 57;
			else if(c.equals('}')&&(stack.poll()!=c))return 1197;
			else if(c.equals('>')&&(stack.poll()!=c))return 25137;
		}
		return 0;
	}
}