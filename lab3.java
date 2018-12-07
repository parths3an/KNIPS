import java.util.*;
//import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import java.io.*;

public class lab3 {

    public static void main(String args[]){
        //Read from the file


        //Make a table for instructions 
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("add", "000");
        map.put("lb", "101");
        map.put("sb", "110");
        map.put("lsf", "111");
        map.put("jump", "100");
        map.put("xor", "001");
        map.put("or", "010");
        map.put("and", "011");


        HashMap<String,String> regToCode = new HashMap<>();
        regToCode.put("rs", "00");
        regToCode.put("rt", "01");
        regToCode.put("r1", "10");
        regToCode.put("r2", "11");
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
        switch(opcode) {
            //ADD
            case "000":
            String signal = strs[2];
            machinecode = "" + signal;
            if (signal.equals("1")) {
                //register
                String register = strs[1];
                machinecode = "0" + regToCode.get(register) + machinecode; //make it 3 bits
            } else {
                String imm = strs[1];
                machinecode = convertToBinary(Integer.parseInt(imm), 3) + machinecode;
            }
            machinecode = opcode + regToCode.get(strs[0]) + machinecode;
            break;

            //xor
            case "001":
            machinecode = opcode + regToCode.get(strs[0]) + regToCode.get(strs[1]) + regToCode.get(strs[2]);
            break;

            //or
            case "010":
            machinecode = opcode + regToCode.get(strs[0]) + regToCode.get(strs[1]) + regToCode.get(strs[2]);
            break;
            
            //and
            case "011":
            machinecode = opcode + regToCode.get(strs[0]) + regToCode.get(strs[1]) + regToCode.get(strs[2]);
            break;

            //jump/branch
            case "100":
            machinecode = opcode + regToCode.get(strs[0]) + convertToBinary(Integer.parseInt(strs[1]), 4);
            break;

            //load
            case "101":
            machinecode = opcode + regToCode.get(strs[0]) + convertToBinary(Integer.parseInt(strs[1]), 4);
            break;

            //store
            case "110":
            machinecode = opcode + regToCode.get(strs[0]) + convertToBinary(Integer.parseInt(strs[1]), 4);
            break;

            //left/right shift
            case "111":
            machinecode = opcode + regToCode.get(strs[0]) + convertToBinary(Integer.parseInt(strs[1]), 3) +  strs[2];
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