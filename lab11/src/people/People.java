package people;

import java.util.Collection;
import java.util.ArrayList;

public class People 
{
	private ArrayList<Person> peopleArray = new ArrayList<Person>(); 
	
	People() {}; // Constructor
	
	// **
	// Add people
	public void add(int index, Person person)	{
		peopleArray.add(index, person);
	}
	
	public boolean add(Person person)	{
		return peopleArray.add(person);
	}
	
	public boolean addAll(int index, Collection<Person> collection)	{
		return peopleArray.addAll(index, collection);
	}
	
	public boolean addAll(Collection<Person> collection)	{
		return peopleArray.addAll(collection);
	}
	// **
	
	// **
	// Remove people
	public void clear()	{
		peopleArray.clear();
	}
	
	public Person remove(int index)	{
		return peopleArray.remove(index);
	}
	//**
	
	public String toString()
	{
		StringBuffer sbuf = new StringBuffer();
		
		for (Person person : peopleArray)
		{
			sbuf.append(person.toString() + "\n"); // List of persons
		}
		
		return sbuf.toString();
	}
	
}