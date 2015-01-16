package equation;

import static org.junit.Assert.*;
import org.junit.Test;

public class UnitTest {
	ComplexNumber CompNumb1 = new ComplexNumber(); 
	ComplexNumber CompNumb2 = new ComplexNumber();
	
	@Test
	public void testPositiveD()
	{
		Equation equation = new Equation(2, 22, -8);
		ComplexNumber first_root_check = new ComplexNumber(0.3523499553, 0);
		ComplexNumber second_root_check = new ComplexNumber(-11.3523499553, 0);
		
		equation.Solve(CompNumb1, CompNumb2);
		assertEquals(first_root_check, CompNumb1); 
		assertEquals(second_root_check, CompNumb2); 
	}
	
	@Test
	public void testNegativeD()
	{
		Equation equation = new Equation(1, 2, 5);
		ComplexNumber first_root_check = new ComplexNumber(-1.0, 2.0000000000);
		ComplexNumber second_root_check = new ComplexNumber(-1.0, -2.0000000000);
		
		equation.Solve(CompNumb1, CompNumb2);
		assertEquals(first_root_check, CompNumb1); 
		assertEquals(second_root_check, CompNumb2); 
	}
	
	@Test 
	public void testZeroD() 
	{
		Equation equation = new Equation (6, 12, 6);
		ComplexNumber root_check = new ComplexNumber(-1.0, 0);
		
		equation.Solve(CompNumb1, CompNumb2);
		assertEquals(root_check, CompNumb1); 
		assertEquals(root_check, CompNumb1); 
	}
	
	@Test (expected = ZeroFirstCoefException.class)
	public void testZeroFirstCoef()
	{
		Equation equation = new Equation(0, 17, 11);
	}
}