
public class int2float {
		
		public static void main (String args[]) {
	    int I1 = (int) 32111; //0
			
			int tempStore1 = (int)((I1>>>8)&0xff);
			int tempStore2 = (int)(I1&0xFF);

			int correctBias=0;
			int signBit = (tempStore1 & 0xff) >>> 7;
			
			int bias = 29; //29 in hex;
			int floatOutput1 = (int) ((tempStore1&0x7F));
			System.out.println(Integer.toBinaryString((floatOutput1)));
	
			int floatOutput2 = tempStore2; 
			
			System.out.println(Integer.toBinaryString((floatOutput2)));
			System.out.println(Integer.toBinaryString(floatOutput1));
			 System.out.println(Integer.toBinaryString(floatOutput2));
			
			
			// ADJUSTING EXPONENT
			
			for(int i =0; i<16;i++) {
				
				// Continue shift left until first reg traversed
				
				byte msb = (byte) ((floatOutput1 >>> 7)& 0xff);
				if(msb ==0) {
					floatOutput1 = ((floatOutput1 &0xff)<< 1);
					floatOutput1 = (floatOutput1 + ((floatOutput2 & 0xff) >>> 7));

					floatOutput2 = ((floatOutput2&0x7F)<<1)&0xff;
					bias--;
				} else if (msb==1) {
					break;
				} 
			}
			
	        // ROUNDING
	        
	        int seventhElement = (floatOutput2>>>7);
	        int sixthElement =(((floatOutput2<<1)&0xff)>>>7);
	        int fifthElement = (((floatOutput2<<2)&0xff)>>>7);
	        int fourthElement = (((floatOutput2<<3)&0xff)>>>7);
	        int thirdElement =(((floatOutput2<<4)&0xff)>>>7);
	        int secondElement = (((floatOutput2<<5)&0xff)>>>7);
	        int firstElement = (((floatOutput2<<6)&0xff)>>>7);
	        int zerothElement = (((floatOutput2<<7)&0xff)>>>7);
	        
	        
	        if( fourthElement == 1 && thirdElement ==1){
	        	if (fifthElement==1 && sixthElement ==1  && seventhElement==1) {
	        		floatOutput1 = (floatOutput1 + 1);
	        		floatOutput2 = (floatOutput2 &0x0F);
	        		} 
	        		floatOutput2 =  (floatOutput2+8);
	      
	        	} else if (fourthElement==0 && thirdElement==1 && (secondElement==1 || firstElement==1 || zerothElement ==1)) {
	        	floatOutput2 =  (floatOutput2+8);
	        }
	        
	       //OVERFLOW CHECK
	        byte msb = (byte) ((floatOutput1 & 0xff) >>> 7);
	        if (msb == 1) {
	        	
	        	byte lsb = (byte)(floatOutput1 &0x1);
	        	floatOutput2 =  (floatOutput2 >>>1);
	        			if (lsb==1) {
	        			  
	        			  floatOutput2 = (floatOutput2 + (1<<7));
	        			}
	        	floatOutput1 = (floatOutput1 >>>1)&0xff;
	        	bias++;
	        }
	        floatOutput2 = floatOutput2&0xff;
		    
		    int exponent =(bias)&0xff;
		    int fraction = (floatOutput1<<4 | (floatOutput2>>>4)&0xff)&0x3FF;
		    int machineCodewoSign = exponent<<10 | fraction; 
		    System.out.println(Integer.toBinaryString(signBit));
		    System.out.println(Integer.toBinaryString(exponent));
		    System.out.println(Integer.toBinaryString(fraction));
		    System.out.println(Integer.toBinaryString(machineCodewoSign));
	
		}	

}
