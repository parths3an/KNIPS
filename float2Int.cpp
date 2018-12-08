//Float2Int
#include<iostream>
#include <bitset>

using namespace std; 
//msh = most significant half
//lsh = least significant half

int Float2Int(char msh,char lsh)
{

return 1; 
}

int main ()
{
//The converted Int value
int result; 
char msh = 0xaF;
char lsh = 0x81;
char resultLowerByte = 0x00;
char resultUpperHalf = 0x00;
const int baseForBitShifting = 25; 
bitset<8> mshInBinary(msh);
bitset<8> lshInBinary(lsh);
cout << mshInBinary; 
cout << "\n" << lshInBinary << endl; 
char signBit = (msh >> 7 ) & 1; 

//cout << "\n"<<(int)signBit;

//3: Isolate the exponent
char exponentMask = 0x7c;
char exponent =  ((msh & exponentMask) >> 2) & 0x1F;
int r2 = (int) exponent;

//bitset<8> testBitSet(exponentMask);
//cout << testBitSet << endl;
//bitset<8> testBitSet2(exponent);
//cout << (int) exponent<< endl;

//4: Isolate Mentisa
bitset<16> mshIn16Binary(msh);
bitset<16> tempInBinary(0x0003);
bitset<16> mshFirstTwo = (mshIn16Binary & tempInBinary) << 8;
//cout << "Two bits are: " << mshFirstTwo << endl;
bitset<16> mentisaBitMask(0x00FF);
//cout << mentisaBitMask << endl;
bitset<16> lshInBits(lsh);
bitset<16> lowerMentisa = mentisaBitMask & lshInBits;
bitset<16> fullMentisa = lowerMentisa | mshFirstTwo;
bitset<16> shiftedBits;
bitset<16> stickyBits;
bitset<16> allOnes(0xFFFF);
bitset<1> sticky(0x0);

//5: 
if (r2 != 0)
{
    bitset<16> hiddenBIt(0x0400);
    fullMentisa = fullMentisa | hiddenBIt;
}

//6. Exponent Regimes
if (r2 >= 30 && r2 <32)
{
    //force output 
    if (signBit == 1)
    {//Negative overflow 
        result = 0xFFFF;
    }
    else 
    {//positive overflow
        result = 0x7FFF;
    }
}
else if (r2 >= 25 and r2 <30)
{
    fullMentisa = fullMentisa << (r2 - baseForBitShifting);
    result = (int) fullMentisa.to_ulong();
    cout << "Full mentisa with program is: " << fullMentisa << result;
}
else if (r2 >= 14 and r2 < 25)
{
    //#TODO
    int shiftRightCount = 25 - r2;
    int shiftLeftCount = 16 - shiftRightCount; 
    stickyBits =  fullMentisa << (shiftLeftCount-1); 
    stickyBits = stickyBits >> (shiftLeftCount - 1);
    stickyBits = stickyBits & allOnes;
    //Find the sticky bit by going through by oring each of the bits 

    for(int i=0; i < stickyBits.size(); i++)
    {
        sticky[0] = sticky[0] | (int) stickyBits[i];
    }
   
    bitset<1> roundingBit;    
    //Add and do whatever needs to do 
    
}
else if (r2 <= 13)
{
   fullMentisa = 0x0000;
}
//Creating two bytes from the answer:
    //Createing the lower half
    
    for (int i=0; i < 8; i++)
    {
        char mask = 0x01;
        bool check = fullMentisa.test(i);
        if (check)
        {//The bit is 1 
            mask = mask << i;
            resultLowerByte = resultLowerByte | mask; 
        }
        /*
        else 
        {//The bit is 0
         //No need to set the bit
        }
        */
    }
    //Creating the upper Half
for (int i=8; i < 16; i++)
    {
        char mask = 0x01;
        bool check = fullMentisa.test(i);
        if (check)
        {//The bit is 1 
            mask = mask << (i - 8);
            resultUpperHalf = resultUpperHalf | mask; 
        }
        /*
        else 
        {//The bit is 0
         //No need to set the bit
        }
        */
    }

}