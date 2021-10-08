#include"MorseCodes.h"

const int led = 13;
const int ANALOG_PIN = 1;

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
  analogReference(DEFAULT);
}


void convertIncomingCharsToMorseCode() {
  // TODO
//  if (Serial.available())
//  {
//    char readChar = Serial.read();
//    if (readChar != '\n') {
//      Serial.print(morseEncode(readChar));
//      Serial.print(" ");
//    } else {
//      Serial.println();
//    }
//  }

  // Read value 0 = 0 V 1023 = 1.1 V

//  byte val;
//  
//   if(analogRead(ANALOG_PIN) > 255){
//      val = 255;
//   }
//   else{
//      val = analogRead(ANALOG_PIN);
//    }

    //debug signal
    Serial.println(analogRead(ANALOG_PIN));

//    //The Jaff handshake protocol lol
//    Serial.print('s');
//    Serial.print(analogRead(ANALOG_PIN));
//    Serial.print('e');


//    int test = 45;
//
//    Serial.print('s');
//    Serial.print(test);
//    Serial.print('e');


}




void loop() {
  // No Need to Modify this.  Put most of your code in "convertIncomingCharsToMorseCode()"
  convertIncomingCharsToMorseCode();
}
