//FLoating point number to an Integer 
import java.util.*;
import java.lang.*;

public class program4
{
//  Assuming the sign bit is 0
    //r0 = I[7:0], r1 = I[15:8]
    public static void main(String[] args) {
        short I = 32750; 
        short r0 = I;               //#1
        byte r1; 
        byte E;
        short r3;
//      System.out.println(Integer.toBinaryString(0xFFFF & I));

    byte s = (byte) 0;              
    r1 = s;                         //#2
    E = (byte) 29;                  //#3
    r3 = I;                         //#4
    System.out.println("Before entering the loop r3 is: " + Integer.toBinaryString(0xFFFF & r3));

    //#4
    for(int i =0;  i <15; i++)
    {
        byte msb = (byte) ((r3 << 13)& 0xffff);
        if ( msb == 0)
        {
            System.out.println(Integer.toBinaryString(msb));
        }
        System.out.println("The msb is: " + Integer.toBinaryString(msb));
        System.out.print("After finding msb r3 is: " +  Integer.toBinaryString(0xFFFF & r3));
    }
}
}