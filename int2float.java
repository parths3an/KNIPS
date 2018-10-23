
public class int2float {
		
		public static void main (String args[]) {
	    int I1 = (int) 111; //0
			
			int tempStore1 = (int)((I1>>>8)&0xff);
			int tempStore2 = (int)(I1&0xFF);
//			System.out.println(Integer.toBinaryString((tempStore1)));
//			System.out.println(Integer.toBinaryString((tempStore2)));
			int correctBias=0;
			
			
			int signBit = (tempStore1 & 0xff) >>> 7;
			
			int bias = 29; //29 in hex;
			int floatOutput1 = (int) ((tempStore1&0x7F));
			System.out.println(Integer.toBinaryString((floatOutput1)));
	
			int floatOutput2 = tempStore2; 
			
			System.out.println(Integer.toBinaryString((floatOutput2)));

			
			
			// ADJUSTING EXPONENT
			
			for(int i =0; i<7;i++) {
				
				// Continue shift left until first reg traversed
				
				byte msb = (byte) ((floatOutput1 >>> 6)& 0xff);
//				System.out.println(Integer.toBinaryString(msb));
				if(msb ==0) {
					floatOutput1 = (byte) ((floatOutput1 &0xff)<< 1);
//					System.out.println(Integer.toBinaryString((floatOutput1)));
					floatOutput1 = (byte) (floatOutput1 + ((floatOutput2 & 0xff) >>> 7));
//					System.out.println(Integer.toBinaryString((floatOutput2 & 0xff) >>> 7));
					floatOutput2 = (((floatOutput2&0x7F)<<1)&0xff);
					System.out.println(Integer.toBinaryString((floatOutput2)));
//				    
//					System.out.println(Integer.toBinaryString((floatOutput1)));
					bias--;
				} 
//				System.out.println(floatOutput1);

				if (msb==1) {
					correctBias =1;
					break;
				} 
			}
			
            for(int i =0; i<8;i++) {
            	
            	byte msb = (byte) ((floatOutput2 & 0xff) >>> 7);
            	
            	if(correctBias ==1 || msb==1) {
            		break;
            	}
				// Continue shift left until first reg traversed
				
//				System.out.println(Integer.toBinaryString(msb));
				if(msb ==0) {
					floatOutput2 = (byte) ((floatOutput2&0xff << 1)&0xff);
//					 System.out.println(Integer.toBinaryString((floatOutput2)));
					bias--;
				} 
			}
			
	        // ROUNDING
	        
	        int eightElement = (floatOutput2>>>7);
	        int seventhElement =(((floatOutput2<<1)&0xff)>>>7);
	        int sixthElement = (((floatOutput2<<2)&0xff)>>>7);
	        int fifthElement = (((floatOutput2<<3)&0xff)>>>7);
	        int fourthElement =(((floatOutput2<<4)&0xff)>>>7);
	        int thirdElement = (((floatOutput2<<5)&0xff)>>>7);
	        int secondElement = (((floatOutput2<<6)&0xff)>>>7);
	        int firstElement = (((floatOutput2<<7)&0xff)>>>7);
	        
//	        System.out.println(Integer.toBinaryString((floatOutput2)));
//	        System.out.println(Integer.toBinaryString(firstElement));
	        
	        
	        if( fifthElement == 1 && fourthElement ==1){
	        	if (sixthElement==1 && seventhElement ==1  && eightElement==1) {
	        		floatOutput1 = (byte) (floatOutput1 + 1);
	        		floatOutput2 = (byte)(floatOutput2 &0x0F);
	        		} else {
	        			floatOutput2 = (byte) (floatOutput2+8);
	        			}
	        	}
	        if (fifthElement==0 && fourthElement==1 && (thirdElement==1 || secondElement==1 || firstElement ==1)) {
	        	floatOutput2 = (byte) (floatOutput2+8);
	        }
	       
	        
	       //OVERFLOW CHECK
	        byte msb = (byte) ((floatOutput1 & 0xff) >>> 7);
	        if (msb == 1) {
	        	byte lsb = (byte)(floatOutput1 &0x1);
	        			if (lsb==1) {
	        			  floatOutput2 = (byte) (floatOutput2 >>>1);
	        			  floatOutput2 = (byte) (floatOutput2 + (1<<7));
	        			}
	        	floatOutput1 = (byte) (floatOutput1 >>>1);
	        	bias++;
	        }
	        floatOutput2 = floatOutput2&0xff;
	        
	        
	        System.out.println(bias);
	  
		    System.out.println(Integer.toBinaryString(floatOutput1));
		    
		    System.out.println(Integer.toBinaryString(floatOutput2));
		    int exponent =bias-15;
		    int fraction = floatOutput1<<3 | floatOutput2>>>5;
		    System.out.println(Integer.toBinaryString(signBit));
		    System.out.println(Integer.toBinaryString(exponent));
		    System.out.println(Integer.toBinaryString(fraction));
		    
		}
		

}