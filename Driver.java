import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main(String [] args) throws IOException {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double[] c1 = {6,5};
        int[] cc1 = {0,3};
        Polynomial p1 = new Polynomial(c1, cc1);
        double[] c2 = {-9,-2};
        int[] cc2 = {4,1};
        Polynomial p2 = new Polynomial(c2, cc2);
        Polynomial s = p1.add(p2);

        /*System.out.println("----------after add 1-2 ------------");
        for (int i = 0; i < s.nums.length; i++)
        {
          System.out.println("the value is" + s.nums[i]);
          System.out.println("the power is" + s.expo[i]);
        }


        System.out.println("----------after add 2-1------------");
        s = p2.add(p1);
        for (int i = 0; i < s.nums.length; i++)
        {
            System.out.println("the value is" + s.nums[i]);
            System.out.println("the power is" + s.expo[i]);
        }


         */
        System.out.println("s(0.1) = " + s.evaluate(0.1));

        if (s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");
        /*Polynomial g = p1.multiply(p2);
        System.out.println("----------multiply 1-2------------");
        for (int i = 0; i < g.nums.length; i++)
        {
            System.out.println("the value is" + g.nums[i]);
            System.out.println("the power is" + g.expo[i]);
        }

        g=p2.multiply(p1);
        System.out.println("----------multiply 2-1------------");
        for (int i = 0; i < g.nums.length; i++)
        {
            System.out.println("the value is" + g.nums[i]);
            System.out.println("the power is" + g.expo[i]);
        }

         */
        File n = new File("C:\\Users\\Jason\\Desktop\\CSCB07\\b07lab1\\tester.txt");
        String str = "C:\\\\Users\\\\Jason\\\\Desktop\\\\CSCB07\\\\b07lab1\\\\tester.txt";
        Polynomial file = new Polynomial(n);
        s.saveToFile(str);
    }
}
