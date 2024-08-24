class MandelbrotSet
{
    public static void main(String[] args)
    {
        int size = 255; //odd number
        int sizer = (int)(size * 2.25); //stretched real axis to make up for BlueJ characters being taller than they are wide
        
        int iterations = 100;
        
        double xcenter = -0.6700030042637188832; //real part of the cetner
        double ycenter = -0.45766058770579065435; //imag part of the center
        double radius = 0.0040942484709020731792; //radius of the graph (in each cardinal direction from the center)
        
        double stepreal = (2 * radius)/(sizer - 1);
        double stepimag = (2 * radius)/(size - 1);
        
        
        
        ComplexNumber[][] c = new ComplexNumber[size][sizer]; 
        
        //Makes complex plot, iterates through complex plot using formula, then prints value
        for (int n = 0; n < c.length; n++)
        {
            for (int k = 0; k < c[0].length; k++)
            {
                double real = xcenter + stepreal * (k - (sizer - 1)/2);
                double imag = ycenter + stepimag * -1 * (n - (size - 1)/2);
                
                c[n][k] = new ComplexNumber(real, imag);
                
                ComplexNumber z = new ComplexNumber();
                z = c[n][k];
                
                int count = 0; //counts how many iterations it takes to converge for shading
                boolean a = true;
                
                while (a)
                {
                    z = z.square().add(c[n][k]);
                    count++;
                    if ((z.mod() > 2) || (count >= 100))
                    {
                        a = false;
                    }
                }
                
                if (z.mod() <= 2)
                {
                    System.out.print("X");
                }
                else
                {
                    if (count <= 25)
                    {
                        System.out.print("/");
                    }
                    else if (count <= 50)
                    {
                        System.out.print("/");
                    }
                    else if (count <= 75)
                    {
                        System.out.print(".");
                    }
                    
                    else
                    {
                        System.out.print("X");
                    }
                }
            }
            System.out.println();
        }
    }
}
