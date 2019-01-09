package main;

import java.io.*;
import java.util.*;

/**
 *
 * @author trisb
 */
public class RecursiveDigitSum {

    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scan = new Scanner(System.in); 
        File file = new File("src/test/text.txt");
        Scanner scan = new Scanner(file);
        String num = scan.next();
        int n = scan.nextInt();
        double w = 0;
        for (int i = 0; i < num.length(); i++) {
            w += Double.parseDouble(num.substring(i, i + 1));
        }
        w *= n % 9;
        while (w > 9) {
            w = recursiveDigitNum(w);
        }
        System.out.println((int) w);
    }

    public static double recursiveDigitNum(double num) {
        if (num < 10) {
            return num;
        } else {
            return (int) (num % 10) + recursiveDigitNum((int) num / 10);
        }
    }
}
