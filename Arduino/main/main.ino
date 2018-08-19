/* Sweep
 by BARRAGAN <http://barraganstudio.com>
 This example code is in the public domain.

 modified 8 Nov 2013
 by Scott Fitzgerald
 http://www.arduino.cc/en/Tutorial/Sweep
*/

#include <Servo.h>

Servo myservo1;  // create servo object to control a servo
Servo myservo2;
int time = 60;

void setup() {
  pinMode(1, OUTPUT);           // set pin to input
  digitalWrite(1, HIGH);       // turn on pullup resistors
  myservo1.attach(9);  // attaches the servo on pin 9 to the servo object
  myservo2.attach(10);
  delay(500);  
  //reset intial position
  myservo1.write(90);  
  delay(500);   
  myservo2.write(90);
  delay(500);
}

void square(){
    int pos;
    for (pos = 90; pos >= 80; pos -= 5) {
      myservo2.write(pos);              
      delay(20);                       
    }
    for (pos = 90; pos >= 80; pos -= 5) {
      myservo1.write(pos);              
      delay(20);                       
    }
    for (pos = 80; pos <= 90; pos += 5) {
      myservo2.write(pos);              
      delay(20);                       
    }
    for (pos = 80; pos <= 90; pos += 5) {
      myservo1.write(pos);              
      delay(20);                       
    }
}

void triangle(){
    int pos;
    for (pos = 90; pos >= 80; pos -= 1) {
      myservo2.write(pos);              
      delay(8);                       
    }
    for (pos = 90; pos >= 80; pos -= 1) {
      myservo1.write(pos);              
      delay(8);                       
    }
    for (pos = 80; pos <= 90; pos += 1) {
      digitalWrite(1, LOW);
      myservo1.write(pos);
      delay(1.8); 
      myservo2.write(pos); 
      delay(1.8);   
      digitalWrite(1, HIGH);                              
    }
}

void loop() {
    digitalWrite(1, HIGH);       // turn on pullup resistors
//    square();
//    triangle();
}



