import definitions::*;
// control decoder
// inputs from instrROM, ALU flags
// outputs to program_counter (fetch unit)
module Ctrl (
  input[8:0] Instruction,	   // machine code
  input       ZERO,			   // ALU out[7:0] = 0
              BEVEN,		   // ALU out[0]   = 0
  output logic jump_en,
               branch_en,
					mem_read,
					mem_write,
					memToReg
					
  );

//jump signal set
always_comb
jump_en = Instruction[4:0] == kjump ? 1 : 0;

//setting mem_write: ask about if we needed to add mem_write and mem_read in ctrl as signals?
always_comb mem_write =  Instruction[4:0] ==  ksbr? 1 : 0;
 
//setting mem_read
always_comb
 if(Instruction[4:0] ==  klbr) begin
    mem_read = 1;
    memToReg = 1; 
	 end
  else  begin
    mem_read = 0;
    memToReg = 0;
	end 
	
//branch signal set
assign branch_en = BEVEN;
//
// branch every time ALU result LSB = 0 (even)
//assign branch_en = BEVEN;
endmodule

  ///				cmp r5, r4
   //				beq jump_label
