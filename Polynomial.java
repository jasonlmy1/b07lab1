import java.io.*;
import java.util.Scanner;

public class Polynomial
{
    public double[] nums;
    public int[] expo;
    public Polynomial() {
        expo = new int[]{0};
        nums = new double[]{0};
    }
    public Polynomial(double[] x, int[]y){
        expo = y;
        nums = x;
    }

    public Polynomial(File file) throws IOException
    {
        BufferedReader breader = new BufferedReader(new FileReader(file));
        String polytext = breader.readLine();
        breader.close();

        polytext = polytext.replace("-", "+-"); //replace - with +-
        String[] polysplit = polytext.split("\\+"); //split the poly into single ones
        nums = new double[polysplit.length];
        expo = new int[polysplit.length];
        int i = 0;
        while(i < polysplit.length)
        {
            if(polysplit[i].length() != 0)
            {
                String [] box = polysplit[i].split("x"); //put single poly to box
                nums[i] = Double.parseDouble(box[0]);
                if(box.length == 1)
                {
                    expo[i] = 0;
                }
                else
                {
                    expo[i] = Integer.parseInt(box[1]);
                }
            }
            i++;
        }

    }



    public void saveToFile(String filename) throws IOException
    {
        String poly = "";
        FileWriter fwriter = new FileWriter(filename);
        for (int i = 0; i < nums.length; i++) //construct my string
        {
            poly += nums[i] + "x" + expo[i] + "+";
        }
        poly = poly.replace("+-", "-"); //use + to split string
        poly = poly.replace("x0", "");
        poly += "Endchecker";
        poly = poly.replace("+Endchecker", "");
        poly = poly.replace(".0", "");
        fwriter.write(poly);
        fwriter.close();
    }


    public int biglen(Polynomial x)
    {
        int len = 0;
        int [] check = new int[x.expo.length];
        int fool = 0;
        for(int i=0; i< x.expo.length ; i++)
        {

                if (checker(x.expo[i],check) == -1 && x.expo[i] != -1 && x.expo[i] != 0)
                {
                    check[i] = x.expo[i];
                }
                if(x.expo[i] == 0 && fool == 0 && x.nums[i] != 0)
                {
                    len = len + 1;
                    fool = 1;
                }
        }

        for (int h = 0; h < check.length; h++)
        {
            if (check[h] != 0 )
            {
                len = len +1;
            }
        }
         return len;
    }

    public Polynomial add(Polynomial x)
    {
        int [] vex;
        double[] nex;
        double[] temp;
        if (x.nums.length == 0) //Zero Cases
        {
            return this;
        }
        nex = new double[nums.length + x.nums.length];
        vex = new int[nums.length + x.nums.length];
        temp = new double[x.nums.length];
        for (int i = 0; i< x.nums.length; i++) //make temp = x.expo
        {
            temp[i]= x.nums[i];
        }

        for (int h =0; h < vex.length; h++) //add my poly to new large array first
        {
            if(h < expo.length)
            {
                nex[h]= nums[h];
                vex [h] = expo[h];
            }
        }


        for (int j = 0; j < vex.length; j++) //add x into the new poly (can have zeros)
        {
            int i = 0;
            while(i < x.nums.length)
            {
                if(vex[j] == x.expo[i] && temp[i] != 0)
                {

                    nex[j] = nex[j] + x.nums[i];
                    vex[j] = x.expo[i];
                    temp[i] = 0;
                    if(nex[j] == 0)
                    {
                        vex[j] = -1;
                    }
                }
                if(vex[j] == 0 && nex[j] == 0.0 && temp[i] != 0)
                {
                    nex[j] = nex[j] + x.nums[i];
                    vex[j] = x.expo[i];
                    temp[i] = 0;
                }
                i++;

            }
        }
        int [] nozeroex;
        double[] nozeroco;
        Polynomial pool = new Polynomial(nex,vex); //make a correct size array now
        nozeroex= new int[biglen(pool)];
        nozeroco = new double[biglen(pool)];
        for (int h = 0;h < nozeroco.length; h++) //now add the non-zeros into the array
        {
            int g = 0;
            while (g < vex.length)
            {
                if (nex[0] == 0 && vex[0] == -1)
                {
                    g++;
                }
                if(g < vex.length && nex[g] != 0 && vex[g] != -1)
                {
                    if(nozeroco[h] == 0)
                    {
                        nozeroco[h] = nex[g];
                        nozeroex[h] = vex[g];
                        nex[g] = 0;
                        vex[g] = -1;
                    }
                    g++;
                }
            }

        }
        return new Polynomial(nozeroco,nozeroex);
    }
    public double evaluate(double x){
        double num = 0.0;
        if (nums.length != 0) //non-zero array case
        {
            for (int i = 0 ; i < nums.length ; i++)
            {
                num = num + nums[i]*Math.pow(x,expo[i]);
            }
            return num;
        }
        return num;
    }
    public Boolean hasRoot(double x)
    {
        if (nums.length != 0) //non-zero array case
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

    public int checker(int x,int[] y)
    {

        int count = -1;
        for(int i= 0; i < y.length; i++)
        {
            if (y[i] == x)
            {
                count++;
            }
        }
        return count;
    }

    public int getlen(int []y) //get the length of the total different amount of expo
    {
        int [] num;
        num = new int [y.length];
        int fooler = 0;
        int len = y.length;
        for (int i = 0 ; i < y.length; i++)
        {
            if (y[i] == 0 && fooler == 0)
            {
                len = len - checker(y[i],y);
                fooler =  1;
            }
            else
            {
                if(checker(y[i],y) > 0 && checker(y[i],num) < 0)
                {
                    num[i] = y[i];
                    len = len - checker(y[i],y);
                }
            }
        }
        return len;
    }

    public Polynomial multiply(Polynomial x)
    {
        int [] boxe;
        double[] boxco; // for the with zeros
        double [] newco;
        int [] newe;
        if (x.nums.length == 0) //Zero Cases
        {
            return this;
        }
        boxco = new double[x.nums.length* nums.length];
        boxe = new int[x.nums.length* nums.length];
        int c = 0;
        for (int i = 0; i < nums.length; i++) //expand the poly
        {
            for (int j = 0; j < x.nums.length;j++)
            {
                boxco[c] = nums[i] * x.nums[j];
                boxe[c] = expo[i] + x.expo[j];
                c++;
            }
        }
        newco = new double[getlen(boxe)];
        newe = new int[getlen(boxe)];
        for (int h = 0; h < newco.length; h++) //add the expanded poly(no redundant)
        {
            int g = 0;
            while ( g < boxco.length)
            {
                if(newe[h] == 0 && newco[h] == 0 && boxco[g]!= 0)
                {
                        newco[h] = newco[h] + boxco[g];
                        newe[h] = boxe[g];
                        boxco[g] = 0;
                }
                if (newe[h] == boxe[g] && boxco[g] != 0)
                {
                    newco[h] = newco[h] + boxco[g];
                    newe[h] = boxe[g];
                    boxco[g] = 0;
                }
                g++;
            }
        }
        return new Polynomial(newco,newe);

    }
}







