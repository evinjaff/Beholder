#include"MorseCodes.h"

const int led = 13;

// Argument: Any character
// Return Value: Either:
//                  1) If the character is a letter, the upper case equivalent.  
//                  2) If the character is not a letter, the original value.
char toUpper(char c) {
  if (c > 96 && c < 123) 
  {
    return c - 32;
  }
  else 
  {
    return c;
  }
}

void setup() {
  Serial.begin(9600);
  pinMode(led, OUTPUT);
}


void convertIncomingCharsToMorseCode() {
  // TODO
  if (Serial.available())
  {
    char readChar = Serial.read();
    if (readChar != '\n') {
      Serial.print(morseEncode(readChar));
      Serial.print(" ");
    } else {
      Serial.println();
    }
  }
}




void loop() {
  // No Need to Modify this.  Put most of your code in "convertIncomingCharsToMorseCode()"
  convertIncomingCharsToMorseCode();
}
