class CORDIC
{
    static int ITERS = 16;
    static double[] theta_table = {
        0.785398163397448,
        0.463647609000806,
        0.244978663126864,
        0.124354994546761,
        0.062418809995957,
        0.031239833430268,
        0.015623728620476,
        0.007812341060101,
        0.003906230131966,
        0.001953122516478,
        0.000976562189559,
        0.000488281211194,
        0.000244140620149,
        0.000122070311893,
        0.000061035156174,
        0.000030517578115
    };
    
    static double compute_K(int n)
    {
        double k = 1.0;
        for (int i = 0; i < n; i++)
        {
            k *= 1/ Math.sqrt(1 + Math.pow(2, (-2 * i)));
        }
        
        return k;
    }
    
    static double[] CORDIC(double alpha, int n)
    {
        double K_n = compute_K(n);
        double theta = 0.0;
        double x = 1.0;
        double y = 0.0;
        int P2i = 1;
        for (int k = 0; k < theta_table.length; k++)
        {
            int sigma;
            if (theta < alpha)
            {
                sigma = 1;
            }
            else
            {
                sigma = -1;
            }
            
            theta += sigma * theta_table[k];
            double temp = x;
            x = x - sigma * y * P2i;
            y = sigma * P2i * temp + y;
            P2i /= 2;
        }
        
        return new double[] {x * K_n, y * K_n};
    }
    
    public static void main(String[] args)
    {
        System.out.println(CORDIC(1, ITERS)[0] + ", " + CORDIC(1, ITERS)[1]);
    }
}