package people;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class UnitTest {
	
	ArrayList<Person> collectAdd = new ArrayList<Person>();
	People peopleTest = new People();
	
	@Before
	public void setPeople() throws Exception {
		peopleTest.add(new Person("Steve", "Ballmer", 58));
		peopleTest.add(new Person("Steve", "Jobs", 56));
		peopleTest.add(new Person("Linus", "Torvalds", 45));
		peopleTest.add(new Person("Bill", "Gates", 59));
		
		collectAdd.add(new Person("Kenneth", "Thompson", 71));
		collectAdd.add(new Person("Dennis", "Ritchie", 73));
	}
	
	/******** Add people tests ********/
	
	@Test
	public void testAdd() throws WrongAgeException {
		peopleTest.add(new Person("Bjarne", "Stroustrup", 63));
		assertEquals("Steve Ballmer, 58 years\n"
				+ "Steve Jobs, 56 years\n"
				+ "Linus Torvalds, 45 years\n"
				+ "Bill Gates, 59 years\n"
				+ "Bjarne Stroustrup, 63 years\n", 
				peopleTest.toString());
	}
	
	@Test
	public void testAddWithIndex() throws WrongAgeException
	{
		peopleTest.add(2, new Person("Bjarne", "Stroustrup", 63));
		assertEquals("Steve Ballmer, 58 years\n"
				+ "Steve Jobs, 56 years\n"
				+ "Bjarne Stroustrup, 63 years\n"
				+ "Linus Torvalds, 45 years\n"
				+ "Bill Gates, 59 years\n",
				peopleTest.toString());
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddWithIndexOutOfBounds()
	{
		peopleTest.add(8, new Person());
	}
	
	@Test
	public void testAddAll() throws WrongAgeException
	{
		peopleTest.addAll(collectAdd);
		assertEquals("Steve Ballmer, 58 years\n"
				+ "Steve Jobs, 56 years\n"
				+ "Linus Torvalds, 45 years\n"
				+ "Bill Gates, 59 years\n"
				+ "Kenneth Thompson, 71 years\n"
				+ "Dennis Ritchie, 73 years\n",
				peopleTest.toString());
	}
	
	@Test
	public void testAddAllWithIndex()
	{
		peopleTest.addAll(2, collectAdd);
		assertEquals("Steve Ballmer, 58 years\n"
				+ "Steve Jobs, 56 years\n"
				+ "Kenneth Thompson, 71 years\n"
				+ "Dennis Ritchie, 73 years\n"
				+ "Linus Torvalds, 45 years\n"
				+ "Bill Gates, 59 years\n",
				peopleTest.toString());
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddAllWithIndexOutOfBounds()
	{
		peopleTest.addAll(8, collectAdd);
	}
	
	/******** Remove people tests ********/
	
	@Test
	public void testClear()
	{
		peopleTest.clear();
		assertEquals("", peopleTest.toString());
	}
	
	@Test
	public void testRemove()
	{
		peopleTest.remove(0);
		assertEquals("Steve Jobs, 56 years\n"
				+ "Linus Torvalds, 45 years\n"
				+ "Bill Gates, 59 years\n",
				peopleTest.toString());
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testRemoveWithIndexOutOfBounds()
	{
		peopleTest.remove(8);
	}
	
}