import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MapPrintStreamTest {
	
	MapPrintStream ps = null;
	File fileMap;
	Map<String, String> myMap;
	Map<Integer, String> myMap2;
	
	@Before
	public void Init() throws FileNotFoundException
	{
		fileMap = new File("Map.txt");
		ps = new MapPrintStream(fileMap);
		myMap = new TreeMap<String, String>();
		myMap2 = new TreeMap<Integer, String>();
	}
	
	@After
	public void closeStream()
	{
		ps.close();
	}
	
	@Test
	public void testMapStrStr() throws IOException {
		
		myMap.put("1", "one");
		myMap.put("2", "two");
		myMap.put("3", "three");
		myMap.put("4", "four");
		myMap.put("5", "five");
		myMap.put("6", "six");
		myMap.put("7", "seven");
		myMap.put("8", "eight");
		myMap.put("9", "nine");
		ps.println(myMap);
				
		assertEquals("1 one\n2 two\n3 three\n4 four\n5 five\n6 six\n7 seven\n8 eight\n9 nine\n", fileToString(fileMap));
	}
	
	@Test
	public void testMapIntStr() throws IOException {

		myMap2.put(1, "one");
		myMap2.put(2, "two");
		myMap2.put(3, "three");
		myMap2.put(4, "four");
		myMap2.put(5, "five");
		myMap2.put(7, "seven");
		myMap2.put(16, "sixteen");
		myMap2.put(24, "twenty_four");
		ps.println(myMap2);
				
		assertEquals("1 one\n2 two\n3 three\n4 four\n5 five\n7 seven\n16 sixteen\n24 twenty_four\n", fileToString(fileMap));
	}

	public String fileToString(File file) throws IOException
	{
		String str; 
		StringBuffer sbuf = new StringBuffer();
	
		BufferedReader bufr = new BufferedReader(new FileReader(file));
		
		while ((str = bufr.readLine()) != null) {
			sbuf.append(str + "\n");
		}
		bufr.close();
		
		return sbuf.toString();
	}
}