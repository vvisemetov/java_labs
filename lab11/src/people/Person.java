package people;

public class Person 
{
	private String name; private String surname; private int age;
	
	Person(){}; // Constructor
	
	Person(String p_name, String p_surname, int p_age) throws WrongAgeException // Constructor
	{
		name = p_name; // Set name
		surname = p_surname; // Set surname
		setAge(p_age); // Set age
	}
	
	public void setAge(int p_age) throws WrongAgeException 
	{
		if (p_age >= 0)  // Age of person can not be negative or zero
			age = p_age;
		else
			throw new WrongAgeException();
	}
	
	public String toString()
	{
		return (name + " " + surname + ", " + age + " years");
	}
}