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
  );

//jump signal set
always_comb  jump_en = Intruction[4:0] == kjump ? 1 : 0;
//branch signal set
assign branch_en = BEVEN;


//setting mem_write: ask about if we needed to add mem_write and mem_read in ctrl as signals?
 if((Instruction[4:0] ==  ksbr))
    mem_write = 1;
  else
    mem_write = 0;

//setting mem_read
 if((Instruction[4:0] ==  klbr))
    mem_read = 1;
    memToReg = 1; 
  else
    mem_read = 0;
    memToReg = 0; 

//
// branch every time ALU result LSB = 0 (even)
//assign branch_en = BEVEN;
endmodule

  ///				cmp r5, r4
   //				beq jump_label
