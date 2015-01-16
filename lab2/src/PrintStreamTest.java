import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class PrintStreamTest {
	
	PrintStream ps = null;
	File myFile;
	
	@Before
	public void Init() throws FileNotFoundException
	{
		myFile = new File("myFile.txt");
		ps = new PrintStream(myFile);
	}
	
	@After
	public void closeStream()
	{
		ps.close();
	}
	
	@Test
	public void testAppend() throws IOException
	{
		ps.append("haveaniceday", 2, 6); ps.append(" strrrrr "); ps.append(" 14 15 16 23 dssdfasa");
		
		assertEquals("vean strrrrr  14 15 16 23 dssdfasa\n", fileToString(myFile));
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testAppendIndexOutOfBounds() throws FileNotFoundException
	{
		ps.append("fghr76t", 3, 9);
	}
	
	@Test
	public void testPrint() throws IOException {
		ps.print(1230856); ps.print(75634523); ps.print(" string ssstr");
		ps.print(true); ps.print(2342342); ps.print(myFile);
		ps.print(false); ps.print(" text "); ps.print(-12.78333);
		
		assertEquals("123085675634523 string ssstrtrue2342342myFile.txtfalse text -12.78333\n", fileToString(myFile));
	}
	
	@Test
	public void testPrintLn() throws IOException
	{
		ps.println(1230856); ps.println(75634523); ps.println(" string ssstr");
		ps.println(true); ps.println(2342342); ps.println(myFile);
		ps.println(false); ps.println(" text "); ps.println(-12.78333);
		
		assertEquals("1230856\n75634523\n string ssstr\ntrue\n2342342\nmyFile.txt\nfalse\n text \n-12.78333\n", fileToString(myFile));
	}
	
	@Test
	public void testWrite() throws IOException
	{
		ps.write(88); ps.write(88); ps.write(13); ps.write(90);
		assertEquals("XX\nZ\n" ,fileToString(myFile));
	}
	
	@Test (expected = FileNotFoundException.class)
	public void testFileNotFound() throws FileNotFoundException
	{
		myFile = new File(":\\asdasdadasdsasdwrongpath");
		ps = new PrintStream(myFile);
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