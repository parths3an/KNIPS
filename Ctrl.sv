import definitions::*;
// control decoder
// inputs from instrROM, ALU flags
// outputs to program_counter (fetch unit)
module Ctrl (
  input[ 8:0] Instruction,	   // machine code
  input       ZERO,			   // ALU out[7:0] = 0
              BEVEN,		   // ALU out[0]   = 0
  output logic jump_en,
               branch_en
  );
// jump on right shift that generates a zero
always_comb
  //TODO: Not sure abut the jump
  if((Instruction[4:0] ==  kbranch) && ZERO)
    jump_en = 1;
  else
    jump_en = 0;

//setting mem_write
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
assign branch_en = BEVEN;
endmodule

  ///				cmp r5, r4
   //				beq jump_label