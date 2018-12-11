//This file defines the parameters used in the alu
package definitions;
    
//TODO:     
// Instruction map
   /* Given
    const logic [4:0]kADD  = 3'b000;
    const logic [4:0]kLSH  = 3'b001;
    const logic [4:0]kRSH  = 3'b010;
    const logic [4:0]kXOR  = 3'b011;
    const logic [4:0]kAND  = 3'b100;
	const logic [4:0]kSUB  = 3'b101;
	const logic [4:0]kCLR  = 3'b110;
*/
    const logic [4:0]ksfrr = 5'b00000;
    const logic [4:0]klbr = 5'b00001;
    const logic [4:0]ksbr = 5'b00010;
    const logic [4:0]kmov = 5'b00011;
    const logic [4:0]kmovr = 5'b00100;
    const logic [4:0]kxorr = 5'b00101;
    const logic [4:0]korr = 5'b00110;
    
    const logic [4:0]kandi = 5'b10111;
    const logic [4:0]kbranch = 5'b11000;
    const logic [4:0]kjump = 5'b11001;
    const logic [4:0]kxori = 5'b11010;
    const logic [4:0]kaddi = 5'b11011;
    const logic [4:0]ksfri = 5'b11100;
    const logic [4:0]ksfli = 5'b11101;
    const logic [4:0]kset = 5'b11110;
    
// enum names will appear in timing diagram
    typedef enum logic[4:0] {
        sfrr, lbr, sbr, mov,
        movr, xorr, orr,andi, branch,
        jump, xori, addi, sfri, sfli, set
         } op_mne;
    
endpackage // definitions
