#include <SoftwareSerial.h>
#include <TinyGPS.h>

//GPS
SoftwareSerial gpsSerial(6, 5);
TinyGPS gps;
void gpsdump(TinyGPS &gps);

//Wifi:
#define RX 10
#define TX 11
String AP = "andres";       // CHANGE ME
String PASS = "andres12"; // CHANGE ME
String HOST = "157.253.238.75";
String PORT = "8080";
int countTrueCommand, countTimeCommand;
boolean found = false;
boolean prendido = false;
SoftwareSerial esp8266(RX, TX);

//Buzzer
const int pinBuzzer = 9;
const int pinLDR = A0;
int threshold = 600;
int valorLDR = 0;

void setup()
{
  Serial.begin(9600);
  Serial.println("Empezando");
  delay(1000);
  //Conexion
  esp8266.begin(115200);
  sendCommand("AT", 5, "OK", false);
  sendCommand("AT+CWMODE=1", 5, "OK", false);
  sendCommand("AT+CWJAP=\"" + AP + "\",\"" + PASS + "\"", 20, "OK", true);
  //Buzzer
  pinMode(pinLDR, INPUT);
  gpsSerial.begin(9600);
  Serial.print("Sizeof(gpsobject) = ");
  Serial.println(sizeof(TinyGPS));
  delay(1000);
}

void loop() {
  esp8266.listen();
  String getData = "GET /materiales/api/maleta/prendido";
  sendCommand("AT+CIPMUX=1", 5, "OK", false);
  sendCommand("AT+CIPSTART=0,\"TCP\",\"" + HOST + "\"," + PORT, 15, "OK", false);
  sendCommand("AT+CIPSEND=0," + String(getData.length() + 4), 4, ">", false);
  esp8266.println(getData);
  countTrueCommand++;
  esp8266.println("+IPD,12:");
  String inData = "";
  while (esp8266.available())
  {
    inData = inData + esp8266.readString();
    delay(100);
  }
  sendCommand("AT+CIPCLOSE=0", 5, "OK", false);
  if (inData.indexOf("true") > 0) {
    prendido = true;
    Serial.println("Prendido!");
    String Slat = "";
    String Slon = "";
    gpsSerial.listen();
    bool newdata = false;
    unsigned long start = millis();
    while (millis() - start < 10000) {
      if (gpsSerial.available()) {
        char c = gpsSerial.read();
        if (gps.encode(c)) {
          newdata = true;
          break;  // uncomment to print new data immediately!
        }
      }
    }
    if (newdata) {
      long lat, lon;
      unsigned long age, chars;
      unsigned short sentences, failed;
      gps.get_position(&lat, &lon, &age);
      Serial.print("Lat/Long(10^-5 deg): "); Serial.print(lat); Serial.print(", "); Serial.println(lon);
      Slon = String(lon);
      Slat = String(lat);
      gps.stats(&chars, &sentences, &failed);
    }
    // Manda la info al servidor.
    esp8266.listen();

    String getData2 = "GET /materiales/api/ping?lat="+Slat+"&lon="+Slon;
    sendCommand("AT+CIPMUX=1", 5, "OK", false);
    sendCommand("AT+CIPSTART=0,\"TCP\",\"" + HOST + "\"," + PORT, 15, "OK", false);
    sendCommand("AT+CIPSEND=0," + String(getData2.length() + 4), 4, ">", false);
    esp8266.println(getData2);
    countTrueCommand++;
    esp8266.println("+IPD,12:");
    String inData2 = "";
    while (esp8266.available())
    {
      inData2 = inData2 + esp8266.readString();
      delay(100);
    }
    sendCommand("AT+CIPCLOSE=0", 5, "OK", false);

  }
  if (inData.indexOf("false") > 0) {
    if (prendido == true) {
      Serial.println("Apagado!");
    }
    prendido = false;
  }
  valorLDR = analogRead(pinLDR);
  if (valorLDR > threshold && prendido == true) {
    tone(pinBuzzer, 440);
  }
  else {
    noTone(pinBuzzer);
  }
}

void sendCommand(String command, int maxTime, char readReplay[], boolean avisar) {
  while (countTimeCommand < (maxTime * 1))
  {
    esp8266.println(command);//at+cipsend
    if (esp8266.find(readReplay)) //ok
    {
      found = true;
      break;
    }
    countTimeCommand++;
  }
  if (found == true)
  {
    if (avisar == true)
    {
      Serial.println("Conectado!");
    }
    countTrueCommand++;
    countTimeCommand = 0;
  }
  if (found == false)
  {
    if (avisar == true)
    {
      Serial.println("Fail");
    }
    countTrueCommand = 0;
    countTimeCommand = 0;
  }
  found = false;
}
