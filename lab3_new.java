import java.util.*;
//import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import java.io.*;

public class lab3_new {

    public static void main(String args[]){
        //Read from the file


        //Make a table for instructions 
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("sfrr", "00000");
        map.put("lbr", "00001");
        map.put("sbr", "00010");
        map.put("mov", "00011");
        map.put("movr", "00100");
        map.put("xorr", "00101");
        map.put("or", "00110");

        map.put("andi", "10111");
        map.put("branch", "11000");
        map.put("jump", "11001");
        map.put("xori", "11010");
        map.put("addi", "11011");
        map.put("sfri", "11100");
        map.put("sfli", "11101");
        map.put("set", "11110");


        HashMap<String,String> regToCode = new HashMap<>();
        regToCode.put("r0", "0000");
        regToCode.put("r1", "0001");
        regToCode.put("r2", "0010");
        regToCode.put("r3", "0011");
        regToCode.put("r4", "0100");
        regToCode.put("r5", "0101");
        regToCode.put("r6", "0110");
        regToCode.put("r7", "0111");
        regToCode.put("r8", "1000");
        regToCode.put("r9", "1001");
        regToCode.put("r10", "1010");
        regToCode.put("r11", "1011");
        regToCode.put("r12", "1100");

        try {
            Scanner input = new Scanner(System.in); 
            String file = input.next();
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
        while ((line = reader.readLine()) != null) { 
            String[] strs = line.split("\\s+");
            String machinecode = processInstruction(map.get(strs[0]), strs[1], regToCode);
            System.out.println(line + ": " + machinecode);
            //write machine code to the file
        }
        reader.close();
        }
        catch(Exception e) {
            System.out.println("error in reading");
        }
    }

    static String processInstruction(String opcode, String regInfo, HashMap<String, String> regToCode) {
        String[]strs = regInfo.split(",");
        String machinecode = "";
        switch(opcode.charAt(0)) {
            //register
            case '0':
            machinecode = opcode + regToCode.get(strs[0])
            break;

            //value / immediate
            case '1':
            break;
            
            default: 
            System.out.println("error occured in parsing instruction");
            break;
        }
        return machinecode;
    }

    static String convertToBinary(int number, int bitNum) {
        String str = Integer.toBinaryString(number);
        //System.out.println("str: " + str);
        int gap = bitNum - str.length();
        for (int i = 0; i < gap; i++) {
            str = "0" + str;
        }
        //System.out.println(str);
        return str;
    }
}
