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
        int E;
        byte r31;
        byte r32;
      System.out.println(Integer.toBinaryString(0xFFFF & I));

    byte s = (byte) 0;              
    r1 = s;                                            //#2
    E =  29;                                           //#3
    r31 = (byte) (I & 0xFFFF);      //I[7:0]           //#4
    r32 = (byte) ( I >> 8 & 0xFFFF); // I[14:8]
    System.out.println("Before entering the loop r31 is: " + Integer.toBinaryString(0xFF & r31));
    System.out.println("Before entering the loop r32 is: " + Integer.toBinaryString(0xFF & r32));

    byte r14 = (byte) 1 << 6;
    byte r4 = (byte) 1 << 4;
    byte r3 = (byte) 1 << 3; 
    //System.out.println("r14 is: " + Integer.toBinaryString(0xFFFF & r14));
    //System.out.println("r4 is: " + Integer.toBinaryString(0xFFFF & r4));
    //System.out.println("r3 is: " + Integer.toBinaryString(0xFF & r3));
    
    //#5
    for (int i=0; i < 7; i++)
    {
        if ( ((byte)(r32 & r14) >> 6) == 0)
        {
            r31 = (byte) (r31 << 1);
            r32 = (byte) (r32 << 1);
            E--; 
            System.out.print("updated r31: " +Integer.toBinaryString(0xFF & r31));
            System.out.print("updated r32: " +Integer.toBinaryString(0xFF & r32));
            
        }
        else{
           break; 
        }
}

byte checkR3 = (byte)((r31 & r3) >> 3);
byte checkR4 = (byte)((r31 & r4) >> 4);
byte checkFirst3 = (byte) ((r31 << 5)) ; 
byte first3 = (byte) (0x00 &  0x00);

System.out.print("Check first3 : " +Integer.toBinaryString(0xFF & checkFirst3));
System.out.print("Check first3 : " +Integer.toBinaryString(0xFF & first3));

//#6.
if (checkR3 == 1 && checkR4 == 1)
{
    //How to do this for two seprate bits 
    //add r3 +8

}

if (checkR3 == 1 && checkR4 == 0 )
{

}

}
}