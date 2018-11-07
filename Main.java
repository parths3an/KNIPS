import java.util.*;
class Main {
	
	//static int encoder = 0xe1;
	public static void main(String[] args) {
    String str = "mr waston, please come over";
		String r1 = encode(str,(char) 0x21, 9, (char)0xd4);
    String d1 = decode(r1);

    //String r2 = encode(str, (char)0xd5, 9, (char)0xd4);
    //String d2 = decode(r2);

    //String r3 = encode(str, (char)0xc4, 9, (char)0xd4);
    //String r4 = encode(str, (char)0xb7, 9, (char)0xd4);
    //String r5 = encode(str, (char)0xb9, 9, (char)0xd4);
    //String r6 = encode(str, 0xb2, 9);
    //String r7 = encode(str, 0xfa, 9);
    //String r8 = encode(str, 0xf3, 4);

  System.out.println("encoded:    "+r1 + "\noriginal:    " + d1);
	}

	static String encode(String str, char encoder, int padding, char pattern) {
    //prepend one byte 
    String spaces = "";
    for (int i = 0; i < padding; i++) spaces= spaces + " "; 
    str = spaces + str;
    char[] chars = str.toCharArray();
    
		for (int i = 0; i < chars.length; i++) {
			char b = chars[i];
		  
      //Encrypped the character  
      int newC = encoder ^ b & 0xff;
      chars[i] = (char)newC;
		
      //Update the encoder
      int left = encoder << 1;
      int counter = 4;
      encoder = (char)(encoder & pattern);
      while (counter != 0) {
        char shifted = (char) (encoder >>> counter); 
        encoder = (char) (shifted ^ encoder);
        counter = counter / 2;
      }
      char right = (char)(encoder & 1); 
      encoder =(char) ((left | right) & 0xff);

    }
		return new String(chars);
	}
  
  static String decode(String str){
    
    // xor the first byte with space
    char[]arr = str.toCharArray();
    char heighest = arr[0];
    char encoder = (char)((char)0x20 ^ heighest);
    
    char[]patterns = new char[]{(char)0xe1, (char)0xd4, (char)0xc6, (char)0xb8, (char)0xb4, (char)0xb2, (char)0xfa, (char)0xf3};
	  // encoder = (encoder << 1) + ^(encoder & pattern)	
    char realPattern = 'a';
    for (char pattern : patterns) {
      String encoded = encode("", encoder, 8, pattern);
      if (encoded.equals(str.substring(0,8))) {
        realPattern = pattern;
        break;
      }  
    }
    
   //Decode the message
   System.out.println("encoder: " + encoder + "\npattern:" + realPattern);
   char[] chars = str.toCharArray();
    
		for (int i = 0; i < chars.length; i++) {
			char b = chars[i];
		  
      //Encrypped the character  
      int newC = encoder ^ b & 0xff;
      chars[i] = (char)newC;
		
      //Update the encoder
      int left = encoder << 1;
      int counter = 4;
      encoder = (char)(encoder & realPattern);
      while (counter != 0) {
        char shifted = (char) (encoder >>> counter); 
        encoder = (char) (shifted ^ encoder);
        counter = counter / 2;
      }
      char right = (char)(encoder & 1); 
      encoder =(char) ((left | right) & 0xff);

    }
		return new String(chars);
  }
}
