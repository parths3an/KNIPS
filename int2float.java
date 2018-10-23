
public class int2float {
		
		public static void main (String args[]) {
			
			byte I1 = (byte) 128; //0
			byte I2 = (byte) 120; //123
			
			byte tempStore1 = I1;
			byte tempStore2 = I2;
			int correctBias=0;
			
			int signBit = (tempStore1 & 0xff) >>> 7;
			
			byte bias = 0x1D; //29 in hex;
			byte floatOutput1 = (byte) ((tempStore1 <<1));
			System.out.println(Integer.toBinaryString((floatOutput1)));
	
			byte floatOutput2 = tempStore2; 
			
			
//			System.out.println(Integer.toBinaryString(I2>>1));
			
			
			// ADJUSTING EXPONENT
			
			for(int i =0; i<7;i++) {
				
				// Continue shift left until first reg traversed
				
				byte msb = (byte) ((floatOutput1 >>> 6)& 0xff);
				System.out.println(Integer.toBinaryString(msb));
				if(msb ==0) {
					floatOutput1 = (byte) ((floatOutput1 &0xff)<< 1);
					System.out.println(Integer.toBinaryString((floatOutput1)));
					floatOutput1 = (byte) (floatOutput1 + ((floatOutput2 & 0xff) >>> 7));
					floatOutput2 = (byte) ((floatOutput2 << 1)&0xff);
				    
					System.out.println(Integer.toBinaryString((floatOutput1)));
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
				
				System.out.println(Integer.toBinaryString(msb));
				if(msb ==0) {
					floatOutput2 = (byte) ((floatOutput2 << 1)&0xff);
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
	        
	        System.out.println(Integer.toBinaryString((floatOutput2)));
	        System.out.println(Integer.toBinaryString(firstElement));
	        
	        
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
	        System.out.println(bias);
		    System.out.println(Integer.toBinaryString(floatOutput1));
		    System.out.println(Integer.toBinaryString(floatOutput2));
		}
		

}
