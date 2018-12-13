import definitions::*;
// control decoder
// inputs from instrROM, ALU flags
// outputs to program_counter (fetch unit)
module Ctrl (
  input[8:0] Instruction,	   // machine code
  input       ZERO,			   // ALU out[7:0] = 0
              BEVEN,		   // ALU out[0]   = 0
  output logic jump_en,
               branch_en
               data_outA,
               data_outB
  );
//jump signal set
always_comb  jump_en = Intruction[4:0] == kjump ? 1 : 0;
//branch signal set
assign branch_en = BEVEN;


logic [  7:0] lutOut
logic [ 3:0] lutIn;

LUT lut1 (
.addr lutIn,
.Target (lutOut)
);

//lutIn = 1;
 //will contain the value of the lutOut = lutOut 

//setting mem_write: ask about if we needed to add mem_write and mem_read in ctrl as signals?

//TODO: Do a switch case for instrutions and set the value for input A and B 

always_comb
  case(ksbr):
 if((Instruction[4:0] ==  ksbr))
    mem_write = 1;
  else
    mem_write = 0;

//setting mem_read
case(klbr):
 if((Instruction[4:0] ==  klbr))
    mem_read = 1;
    memToReg = 1; 
  else
    mem_read = 0;
    memToReg = 0; 
case(ksfrr):
  //TODO: set the controls and value for dataOutA and dataOutB here which will be passed into the 

case(kmov):
case(kmovr):

case(kxorr):

case(korr):

case(kandi):
case(kbranch):
case(kjump):
case(kxori):
case(kaddi):
case(ksfri):
case(ksfli):
case(kset):



// branch every time ALU result LSB = 0 (even)
endcase
endmodule

  ///				cmp r5, r4
   //				beq jump_label
