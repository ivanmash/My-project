  #include <SoftwareSerial.h>
  SoftwareSerial SIM800 (12, 13); // 12 - RX ARDUINO (TX - SIM800L) 13 - TX ARDUINO (RX - SIM800L)
  unsigned long my_timer1;
  //unsigned long my_timer2;
  unsigned long inter1 = 30*1000;
  //unsigned long inter2 = 60*1000;

  void setup() {//время модуля на настройку
  Serial.begin(9600);   //насторойка скорости платы 
  SIM800.begin(9600); //насторойка скорости модуля
  //SIM800.println("AT+CFUN=1,1");//проверка модуля на работоспособность
  delay(1000);
  SIM800.println("AT+CLIP=1;&W");
  delay(1000);
  SIM800.println("AT+GSMBUSY=0;&W");
  delay(1000);
  SIM800.println("AT+CMGF=1;&W");
  delay(1000);
  SIM800.println("AT+CNMI=1,2,0,0,0;&W");
  delay(1000);
  SIM800.println("AT+CSCLK=0;&W");
  delay(1000);
  SIM800.println("AT+CMGL=\"REC UNDEAD\";&W");
  delay(1000);
  Serial.println("Start!");
  pinMode(9, OUTPUT);   
  digitalWrite(9, HIGH);
  my_timer1 = millis();
  //my_timer2 = millis();
  }
  char incomingByte;
  String inputString;
  String number = "+79854591506";;
  String message;
  bool sol = false;
  void loop() 
  {
    if(SIM800.available()){
      delay(100);
        
      // Последовательный буфер
        inputString = ParseMessages(inputString);
        if(inputString.indexOf("ON") > -1)   
        {
          //message = "Start system.";
          //SendSMS(number, message);
          my_timer1 = millis();
          
          Serial.println("Hell");
          Serial.println(my_timer1);
        }
          
          while(inputString.indexOf("OFF") == -1 && inputString.indexOf("ON") > -1)
          { 
              //message = "System is working.";
              //SendSMS(number, message); 
              digitalWrite(9, LOW);
              //my_timer2 = millis();
              Serial.println(millis() - my_timer1);
              while(millis() - my_timer1 >= inter1)
              {
                digitalWrite(9, HIGH);
                inputString = ParseMessages(inputString);
                //Serial.println(millis() - my_timer2);
                if(millis() - my_timer1 >= inter1 + 60000 || inputString.indexOf("OFF") > -1 )
                {
                  my_timer1 = millis();
                  //my_timer2 = millis();
                  break;
                }
              }
          }
         if (inputString.indexOf("OFF") > -1){
            digitalWrite(9, HIGH);
            message = "System has stopped working.";
            SendSMS(number, message);
            }    
        delay(50);
        // Удалить сообщения
         
        if (inputString.indexOf("OK") == -1){
          SIM800.println("AT+CMGDA=\"DEL ALL\"");
          delay(1000);
          }
      inputString = "";
    }
  }
void SendSMS(String number, String message) 
{
  SIM800.print("AT+CMGS=\"+79854591506\"\r");
  delay(500);
  SIM800.print(message + "\r");
  delay(500);
  SIM800.write(0x1A);
  delay(500);
}

  String ParseMessages(String s)
  {
    char incomingBytes;
    while(SIM800.available())
    {
      incomingBytes = SIM800.read();
      s += incomingBytes; 
    }
    delay(10);      
    //Serial.println(s);
    s.toUpperCase();
    return s;
  }
