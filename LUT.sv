module LUT(
  input[1:0] addr,
  output logic[15:0] Target
  );

always_comb 
  case(addr)		   //-16'd30;
	
  
  /*
  2'b00:   Target = 16'hffff;//-1
	2'b01:	 Target = 16'h0f03;
	2'b10:	 Target = 16'h0003;
	default: Target = 16'h0;
*/


  // can save addr for branch

  4'b0000: Target = 0;
  4'b0001: Target = 1;
  4'b0010: Target = 4
  4'b0011: Target = 61;
  4'b0100: Target = 62;
  4'b0101: Target = 63;
  4'b0110: Target = 32;
  4'b0111: Target = 64;
  4'b1000: Target = 255;
  4'b1001: Target = 51;
  4'b1010: Target = 59;
  4'b1011: Target = 41;
  4'b1100: Target = 22;
  



  //4'b0110: Target = ;
  //4'b0111: Target = ;

  endcase

endmodule
