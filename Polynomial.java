
public class Polynomial
{
    public double[] array;
    public Polynomial() {

        array = new double[]{0};
    }
    public Polynomial(double[] x){

        array = x;
    }
    public Polynomial add(Polynomial x)
    {
        double[] nex;
        if (x.array.length == 0) //Zero Cases
        {
            return this;
        }
        if (array.length == 0)
        {
            return x;
        }
        nex = new double[(x.array.length > array.length) ? x.array.length : array.length];
        if (x.array.length > array.length) //Non- Zero Case
        {
            for (int i= 0; i < x.array.length ; i++)
            {
                if(i < array.length )
                {
                    nex[i] = x.array[i] + array[i];
                }
                else
                {
                    nex[i] = x.array[i];
                }
            }
        }
        else
        {
            for (int i =0; i < array.length ; i++)
            {
                if(i < x.array.length )
                {
                    nex[i] = x.array[i] + array[i];
                }
                else
                {
                    nex[i] = array[i];
                }
            }
        }
        return new Polynomial(nex);
    }
    public double evaluate(double x){
        if (array.length != 0) //non-zero array case
        {
            double num = 0;
            double power = 0;
            for (int i = 0 ; i < array.length ; i++)
            {
                num = num + array[i]*Math.pow(x,power);
                power = power + 1;
            }
            return num;
        }
        return 0;
    }
    public Boolean hasRoot(double x)
    {
        if (array.length != 0) //non-zero array case
        {
            if (evaluate(x) == 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }

}