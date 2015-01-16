package equation;

public class ComplexNumber 
{
	private static double delta = 0.00001;
	
	public Double im; public Double re;
	
	ComplexNumber() // Constructor
	{
		im = 0.0;
		re = 0.0; 
	}
	
	ComplexNumber(double n_re, double n_im) // Constructor 
	{
		this.im = n_im;
		this.re = n_re;
	}

	@Override
	public boolean equals(Object object)
	{
        if (this == object) return true;
        if (object == null) return false;
        if (getClass() != object.getClass()) return false;
		 
        return ((Math.abs(this.re - ((ComplexNumber)object).re) < delta) && (Math.abs(this.im - ((ComplexNumber)object).im) < delta));
	}
	
	public String toString()
	{
		if (im == 0)
			return re.toString();
		else 
		{
			return (re + ((im > 0) ? "+" : "") + im + "i");
		}
	}

}