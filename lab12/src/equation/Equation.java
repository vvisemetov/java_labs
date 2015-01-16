package equation;

public class Equation 
{
    double a; double b; double c; // Coefficients
    
    Equation(double n_a, double n_b, double n_c)  // Constructor
    {
        a = n_a; b = n_b; c = n_c;
        
        if (a == 0) throw new ZeroFirstCoefException("First coefficient of equation is zero");
    }
    
    public void Solve(ComplexNumber first_root, ComplexNumber second_root)
    {
    	double D = Math.pow(b,2) - 4*a*c;
		
    	if (D >= 0)
		{
			first_root.re = (-b + Math.sqrt(D))/(2*a);
			second_root.re = (-b - Math.sqrt(D))/(2*a);
			first_root.im = 0.0; second_root.im = 0.0;
		}
		else
		{
			first_root.re = second_root.re = -b/(2*a);
			first_root.im = Math.sqrt(Math.abs(D))/(2*a);
			second_root.im = -Math.sqrt(Math.abs(D))/(2*a);
		}
    	
    return;
    }
}