
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
        if (x.array.length == 0) //Zero Cases
        {
            return this;
        }
        if (array.length == 0)
        {
            return x;
        }
        if (array.length >= x.array.length) // Non Zero-Cases
        {
            for (int i = 0; i<x.array.length;i++)
            {
                array[i] = x.array[i]+array[i];
            }
            return this;
        }
        else
        {
            for (int i = 0; i<array.length;i++)
            {
                x.array[i] = x.array[i]+array[i];
            }
            return x;
        }
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