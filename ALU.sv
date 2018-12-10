// Create Date:    2016.10.15
// Module Name:    ALU 
// Project Name: 
// Dependencies: 
//
// Revision 0.01 - File Created
// Additional Comments: 
//   combinational (unclocked) ALU
import definitions::*;			  // includes package "definitions"
module ALU(
  input [ 7:0] INPUTA,      	  // data inputs
               INPUTB,
  input [ 4:0] OP,				  // ALU opcode, part of microcode
  input        SC_IN,             // shift in/carry in 
  output logic [7:0] OUT,		  // or:  output reg [7:0] OUT,
  output logic SC_OUT,			  // shift out/carry out
  output logic ZERO,              // zero out flag
  output logic BEVEN              // LSB of input B = 0
    );
	 
  op_mne op_mnemonic;			  // type enum: used for convenient waveform viewing
	
	//TODO: 
  always_comb begin
    {SC_OUT, OUT} = 0;
// single instruction for both LSW & MSW
  case(OP)
   	
  //might need to follow the lecture
  //================================
/*  kLSH : {SC_OUT, OUT} = {INPUTA, SC_IN};  	       // shift left 
    kRSH : {OUT, SC_OUT} = {SC_IN, INPUTA};			   // shift right
    or shifting by 1 multiple times
 */
    ksfrr: 
		 begin 
			INPUTA = INPUTA >> (INPUTB_int - 1);
			[7:0]t = INPUTA & 1b'1; // last shifted bit is saved
			OUT = INPUTA >> 1;
			SC_OUT = t[0];
			end 	
   
    ksfri:
			begin
			INPUTA = INPUTA >> (INPUTB - 1);
			[7:0]t = INPUTA & 1b'1; // last shifted bit is saved
			OUT = INPUTA >> 1;
			SC_OUT = t[0];
			end 
		ksfli:
			begin
				INPUTA = INPUTA << (INPUTB - 1);
				[7:0]t = INPUTA & 8b'128; // last shifted bit is saved
				OUT = INPUTA << 1;
				SC_OUT = t >> 7;
			end 
//=============================================
	/* 
	 	klbr:
			begin
				
			end	
	 	ksbr: 
			begin

			end
	 */ 
	 kmov://DONE
			begin
				OUT = INPUTA;
				SC_OUT = 0; 
			end
    kmovr://DONE
			begin
				OUT = INPUTB;
				SC_OUT = 0; 
			end
		kxorr://DONE
			begin
				OUT = INPUTA ^ INPUTB;
				SC_OUT = 0; 
			end
		korr://DONE
			begin
				OUT = INPUTA | INPUTB;
				SC_OUT = 0; 
			end
		kandi://DONE
			begin
				OUT = INPUTA & INPUTB;
				SC_OUT = 0; 
			end
		kxori://DONE
			begin
				OUT = INPUTA ^ INPUTB;
				SC_OUT = 0; 
			end 
		kaddi://DONE
			begin
				{SC_OUT, OUT} = {1'b0,INPUTA} + INPUTB + SC_IN; 
			end 
		kset:
			begin
				OUT = INPUTB;
        SC_OUT = 0; 
			end
	end 
	 /*
	 kADD : {SC_OUT, OUT} = {1'b0,INPUTA} + INPUTB + SC_IN;    // add w/ carry-in & out

    kSH:        
		begin
		[1:0] temp = INPUTB & 1b'1;  // signal
		INPUTB = INPUTB >> 1;
		if (temp == 1b'1) {
			//do left shift
			INPUTA = INPUTA << (INPUTB - 1);
			[7:0]t = INPUTA & 8b'128; // last shifted bit is saved
			OUT = INPUTA << 1;
			SC_OUT = t >> 7;
		} else [
			//do right shift
			INPUTA = INPUTA >> (INPUTB - 1);
			[7:0]t = INPUTA & 1b'1; // last shifted bit is saved
			OUT = INPUTA >> 1;
			SC_OUT = t[0];
		}
    end


	kLSH : {SC_OUT, OUT} = {INPUTA, SC_IN};  	       // shift left 
    kRSH : {OUT, SC_OUT} = {SC_IN, INPUTA};			   // shift right
//  kRSH : {OUT, SC_OUT} = (INPUTA << 1'b1) | SC_IN;
 	kXOR : begin 
 	         OUT    = INPUTA^INPUTB;  				   // exclusive OR
			 SC_OUT = 0;							   // possible convenience
		   end
    kAND : begin                                       // bitwise AND
             OUT    = INPUTA & INPUTB;
			 SC_OUT = 0;
		   end
    kSUB : begin
	         OUT    = INPUTA + (~INPUTB) + SC_IN;	   // check me on this!
			 SC_OUT = 0;                               // check me on this!
	       end
    
    kOR : begin
		OUT = INPUTA | INPUTBL
                 SC_OUT = 0;
          end


    default: {SC_OUT,OUT} = 0;						   // no-op, zero out
 
*/
 endcase


// option 2 -- separate LSW and MSW instructions
//    case(OP)
//	  kADDL : {SC_OUT,OUT} = INPUTA + INPUTB ;    // LSW add operation
//	  kLSAL : {SC_OUT,OUT} = (INPUTA<<1) ;  	  // LSW shift instruction
//	  kADDU : begin
//	            OUT = INPUTA + INPUTB + SC_IN;    // MSW add operation
//                SC_OUT = 0;   
//              end
//	  kLSAU : begin
//	            OUT = (INPUTA<<1) + SC_IN;  	  // MSW shift instruction
//                SC_OUT = 0;
//               end
//      kXOR  : OUT = INPUTA ^ INPUTB;
//	  kBRNE : OUT = INPUTA - INPUTB;   // use in conjunction w/ instruction decode 
//  endcase
	case(OUT)
	  1'b0     : ZERO = 1'b1;
	  default : ZERO = 1'b0;
	endcase
//$display("ALU Out %d \n",OUT);
    op_mnemonic = op_mne'(OP);
  end
  //set the signal for branch
  always_comb BEVEN = opcode[8:4] == kbranch && INPUTA[3:0] == 3'b000;
//OP == 3'b101; //!INPUTB[0];               // note [0] -- look at LSB only
// always_comb	branch_enable = opcode[8:6]==3'b101? 1 : 0;  
//always_comb branch_enable =
endmodule



	   /*
			Left shift

            
			  input a = 10110011   sc_in = 1

              output = 01100111
			  sc_out =	1

							   */
