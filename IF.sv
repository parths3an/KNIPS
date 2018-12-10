// Design Name:    basic_proc
// Module Name:    IF 
// Project Name: 
// Description: 
// Dependencies: 
//
// Revision: 
// Revision 0.01 - File Created
// Additional Comments: 
//
module IF(
  input Branch_abs,			// JUMP   
  input Branch_rel_en,  // CONDITIONAK BRANCH
  input ALU_zero,
  input [15:0] Target,
  input Init,
  input Halt,
  input CLK,
  output logic[15:0] PC
  );
	 
  always_ff @(posedge CLK)
	if(Init)
	  PC <= 0;
	else if(Halt)
	  PC <= PC;
    //TODO: Only use one of the below else if, use abs
	else if(Branch_abs)	      // unconditional absolute jump
	  PC <= Target;
	else if(Branch_rel_en && ALU_zero) // conditional relative jump
	  PC <= Target + PC; // if absolute, then directly the target
	else
	  PC <= PC+1;		  // default increment

endmodule
