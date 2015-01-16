package people;

public class WrongAgeException extends Exception
{
	WrongAgeException(){
		super("Person can not have negative or zero age"); 
	};
}