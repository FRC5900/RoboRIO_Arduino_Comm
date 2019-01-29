void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(LED_BUILTIN, OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  digitalWrite(LED_BUILTIN, HIGH);
  
  if(Serial.available()) {
    byte value = Serial.read();

    if(value == 0x12) {
      Serial.println("Hello! I received code 0x12");
    }
  }
  delay(200);
  digitalWrite(LED_BUILTIN, LOW);
  delay(200);
  
}
