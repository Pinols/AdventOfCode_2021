package cavessss;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class mainCaves {
	static int steps=0;
	static int currentStepFlashes=1;
	static int flashesA=0;
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String fileContent="";
		try {
			fileContent =new String(Files.readAllBytes(Paths.get("C:\\Users\\Stefano\\Desktop\\Day 12_WIP\\advent.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// \r\n
		int i=0,j=0;
		List<Cave> caves=new ArrayList<Cave>();
		List<String> names=new ArrayList<String>();
		for(String s:fileContent.split("\r\n")) {
			String c1="";
			String c2="";
			c1=s.split("-")[0];
			c2=s.split("-")[1];
			if(!names.contains(c1)) {
				names.add(c1);
			}
			if(!names.contains(c2)) {
				names.add(c2);
			}
		}
		for(String s:names) {
			Cave c=new Cave();
			c.name=s;
			if(s.equals("start"))c.type="start";
			else if(s.equals("end"))c.type="end";
			else {
				if(Character.isUpperCase(s.charAt(0))){
					c.type="big";
				}
				else {
					c.type="small";
				}
			}
			caves.add(c);
		}
		for(String s:fileContent.split("\r\n")) {
			for(Cave c:caves) {
				if(c.name.equals(s.split("-")[0])) {
					for(Cave a:caves) {
						if(a.name.equals(s.split("-")[1]))c.connections.add(a);
					}
				}
				if(c.name.equals(s.split("-")[1])) {
					for(Cave a:caves) {
						if(a.name.equals(s.split("-")[0]))c.connections.add(a);
					}
				}
			}
		}
		//start paths
		long paths=0;
		for(Cave c:caves) {
			if(c.type.equals("start")) {
				paths=c.step(0);			
			}
		}
		System.out.println("Sol A:"+paths);
		paths=0;
		for(Cave c:caves) {
			if(c.type.equals("start")) {
				paths=c.stepB(0);			
			}
		}
		System.out.println("  ");
		System.out.println("sol B: "+Cave.paths+" OR maybe "+paths);
	}
}