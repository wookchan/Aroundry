//YWROBOT
//Compatible with the Arduino IDE 1.0
//Library version:1.1
#include <LiquidCrystal_I2C.h>
#include <String.h>
#include <SoftwareSerial.h>

LiquidCrystal_I2C lcd(0x27,16,2);  // set the LCD address to 0x27 for a 16 chars and 2 line display

String userid="RJtCsewtd3bYaxCr8rgh-EefV4oeUCYxROPX-op91b4";
String machineseq="3";

int btnTime = 3;
int btnStart = 5;
int startState = 0;
int startCnt = 0;
int remainTime = 0;

int startTime = 0; // 세탁 시작시간
int laundryTime = 3600; // 세탁시간 1시간 

void printResponse();
void connectWifi();
void httpclient(String char_input); 

// 3.3V 연결
SoftwareSerial ESP8266(10, 11);
String SSID = "hanul201";
String PASSWORD = "hanul201";


void setup(){  

    #ifndef ESP8266
      while (!Serial); // for Leonardo/Micro/Zero
    #endif
    Serial.begin(9600);
    ESP8266.begin(9600);
    Serial.println("Hello!");
  
    pinMode(btnTime, INPUT);
    pinMode(btnStart, INPUT);
    
    lcd.init();                      // initialize the lcd 
    // Print a message to the LCD.
    lcd.backlight();
    lcd.setCursor(0,0);
    lcd.print("Hello, world!");

    connectWifi();
 
}

void loop(){  
    int startVal = digitalRead(btnStart);
    if(startVal == 1){
        // 만약 시작버튼이 눌리면, 남아있는 시간이 0 이면 세탁시작
        if(startState == 0 && remainTime == 0){
            // 세탁시간을 60분(3600초) 주고 세탁시작, 임시로 5분
            remainTime = 60;
            startTime = millis()/1000;
            startState = 1;  

            
            String input_str = String(remainTime)+"&userid="+userid+"&machineseq="+machineseq;
              
            // 여기에 최초로 서버로 시간보내는것을 넣으면 된다
            httpclient(input_str);
        }       
    
    }
    // 세탁시간이 남아있으면 1초마다 시간을 갱신한다
    if(millis()/1000 - startTime <= remainTime){
        if(millis()/1000 - startTime >= 1){
            startTime = millis()/1000;
            remainTime--;

            // 1분에 한번씩 보내기
            if(remainTime%20 == 0){
               
                String input_str = String(remainTime)+"&userid="+userid+"&machineseq="+machineseq;
              
                // 여기에 서버로 시간보내는것을 넣으면 된다
                httpclient(input_str);
                //httpclient(String(remainTime));
            }  
            
            int hour = remainTime/3600;  // 남은 시간
            int minute = remainTime/60;  // 남은 분
            int second = remainTime%60;  // 남은 초

            lcd.clear();
            lcd.setCursor(2,0);
            lcd.print(hour);
            lcd.setCursor(3,0);
            lcd.print(" : ");
            lcd.setCursor(6,0);
            lcd.print(minute);
            lcd.print(" : ");
            lcd.setCursor(10,0);
            lcd.print(second);
        } 
    // 세탁시간이 끝나면 세탁시간을 0으로 초기화 하고 기다린다 
    }else{
      remainTime = 0;
      lcd.setCursor(0,0);
      lcd.print("Waiting Start ...");

      startState = 0;
    }


    if(ESP8266.available()) {

        // start server on port 80
        Serial.println("Start the server ..."); 
        
//        String cmd = "AT+CWMODE=3";  // Server로 접속     
//        ESP8266.println(cmd);
//        delay(1000);
//        printResponse();     
        
//        cmd = "AT+CIPSERVER=1,80";  // 0:close mode  1:open mode 
        ESP8266.println("AT+CIPSERVER=1,80");
        printResponse();
        delay(200);

        ESP8266.println("AT+CIPMUX=1"); //
        delay(200);
        printResponse(); 

        ESP8266.println("AT+CIPSTO=3"); // SET SERVER TIMEOUT
        delay(200);
        printResponse();
      
        Serial.println("ESP8266.available()!!!");
  
        String result = "";
      
        result = ESP8266.readStringUntil('?');
        Serial.println("수신 정보 << " + result + " >> "); 
  
        int firstIndex = result.indexOf('[');
        int lastIndex = result.indexOf(']');
       
        String data = result.substring(firstIndex, lastIndex + 1); 
        delay(200);
        Serial.println("Json 정보 << "+data+" >> "); 
        delay(200);
    
    }  
  
}

void printResponse() {
  while (ESP8266.available()) {
    Serial.println(ESP8266.readStringUntil('\n'));
  }  
}

// 서버로 보내기  
void httpclient(String char_input){
      
//    ESP8266.println("AT+CIPMUX=0"); //
//    delay(1500);
//    printResponse();
    
    // start server on port 80
    Serial.println("close the server ..."); 
    //String cmddd = "AT+CIPSERVER=0";  // 0:close mode  1:open mode 
    ESP8266.println("AT+CIPSERVER=0");
    printResponse();
    delay(1500);

    ESP8266.println("AT+CIPMUX=0"); //
    delay(1500);
    printResponse();
    
    delay(100);
    Serial.println("connect TCP...");
    String cmdd = "AT+CIPSTART=\"TCP\",\"192.168.0.48\",8989";
    ESP8266.println(cmdd);
    //delay(500);
    printResponse();
    //delay(500);
    if(Serial.find("ERROR")) return;
    
    Serial.println("Send data...");
    Serial.println(char_input);
    
    String url=char_input;
    //String cmd="GET /project/iotReg? HTTP/1.0\r\n\r\n";
    String cmd = "GET /laundry/iotData?restTime="+url+" HTTP/1.0\r\n";  
    ESP8266.println("AT+CIPSEND=" + String(cmd.length() + 4));
    delay(1000); 
    //printResponse();
    //Serial.println("AT+CIPSEND=0," + String(cmd.length() + 4));      
    //delay(5000);
//    if(ESP8266.find(">"))
//    {
//      Serial.print(">");
//    }else
//    {
//      ESP8266.println("AT+CIPCLOSE");
//      Serial.println("connect timeout");
//      delay(1000);
//      return;
//    }
//    delay(500);
    //delay(1000);     
    ESP8266.println(cmd);
    Serial.println(cmd);
    delay(2000);
    if(Serial.find("ERROR")) {
      ESP8266.println("AT+RST"); 
      delay(1000);
      printResponse();
      return;
    }
    ESP8266.println("AT+CIPCLOSE");
    delay(1000);
    printResponse();
//    if(Serial.find("busy")){      
//      ESP8266.println("AT+RST"); 
//      delay(1000);
//      printResponse();
//    }
    
    delay(100);
}  


// esp8266 at command 명령어 찾아보기
void connectWifi() {

  while (1) {
    Serial.println("Wifi connecting ...");
//    String cmd = "AT+CWMODE=1";  // Client 접속     
//    ESP8266.println(cmd);
//    delay(1000);
//    printResponse(); 

    String cmd = "AT+CWMODE=3";  // Server로 접속     
    ESP8266.println(cmd);
    delay(1000);
    printResponse(); 

    cmd = "AT+CWJAP=\"" + SSID + "\",\"" + PASSWORD + "\""; // WIFI 접속
    Serial.println(cmd);
    ESP8266.println(cmd);
    delay(5000);
    
    if (ESP8266.find("OK")) {
      Serial.println("Wifi connected!!!");

//      cmd = "AT+CIFSR";   // IP 주소 알아내기
//      ESP8266.println(cmd);
//      delay(1500);
//      printResponse(); 
//      delay(1500);

//      ESP8266.println("AT+CIPMUX=1"); // 다중접속
//      delay(1500);
//      printResponse();

      // start server on port 80
//      Serial.println("Start the server ..."); 
//      cmd = "AT+CIPSERVER=1,80";  // 0:close mode  1:open mode // 기본포트 333 -> AT+CIPSERVER=1,80
//      ESP8266.println(cmd);
//      printResponse();
//      delay(1500);
//
//       ESP8266.println("AT+CIPSTO=5"); // SET SERVER TIMEOUT
//       delay(1000);
//       printResponse();
//
//      Serial.println("Waiting the server ..."); 

      break;
    }
  }

}
