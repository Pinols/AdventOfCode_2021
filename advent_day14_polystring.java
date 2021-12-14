import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class main2 {

	
	//@SuppressWarnings("unused")
	public static void main(String[] args) {
		String fileContent="";
		try {
			fileContent =new String(Files.readAllBytes(Paths.get("C:\\Users\\Stefano\\Desktop\\advent.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int i=0;
		Map<String,String> rules=new HashMap<String,String>();
		for(String s:fileContent.split("\r\n")) {
			rules.put(s.split(" -> ")[0], s.split(" -> ")[1]);
		}
		// \r\n
		StringBuilder start=new StringBuilder("PHOSBSKBBBFSPPPCCCHN");
		Map<String,Long> pairs=new HashMap<String,Long>();
		for(i=1;i<start.length();i++) {
			String s=""+start.toString().toCharArray()[i-1]+start.toString().toCharArray()[i];
			if(pairs.keySet().contains(s)) {
				pairs.put(s, (pairs.get(s)+1));
			}
			else pairs.put(s, 1L);
		}
		i=0;
		do {
			pairs=doStep(pairs,rules);
			i++;
		}while(i<40);
		Map<Character,Long> nums=new HashMap<Character,Long>();
		for(String s:pairs.keySet()) {
			if(!nums.containsKey(s.charAt(0)))nums.put( s.charAt(0) , pairs.get(s) );
			else nums.put( s.charAt(0) , (pairs.get(s)+nums.get(s.charAt(0)) ));
		}
		long[] arr=new long[10];
		i=0;
		for(Character c:nums.keySet()) {
			if(c.equals(start.charAt(start.length()-1))) {
				System.out.println(c+": "+nums.get(c)+1);
				arr[i]=(nums.get(c)+1);
			}
			else {
				System.out.println(c+": "+nums.get(c));
				arr[i]=nums.get(c);
			}
			i++;
		}
		Arrays.sort(arr);
		System.out.println("final sol: "+(arr[9]-arr[0]));
	}
	private static Map<String, Long> doStep(Map<String, Long> pairs, Map<String, String> rules) {
		Map<String, Long> result=new TreeMap<String,Long>();
		for(String rule:rules.keySet()) {
			for(String pair:pairs.keySet()) {
				if(pair.equals(rule)) {
					String s=""+pair.charAt(0)+rules.get(rule);
					String z=""+rules.get(rule)+pair.charAt(1);
					if(result.containsKey(s))result.put(s,(result.get(s)+pairs.get(pair)));
					else result.put(s, pairs.get(pair));
					if(result.containsKey(z))result.put(z,(result.get(z)+pairs.get(pair)));
					else result.put(z, pairs.get(pair));
				}
			}
		}
		return result;
	}
}