/*
 * Interfacing Ubidots with esp8266.   
 */

#include "UbidotsESPMQTT.h" // Download the libray from here https://github.com/ubidots/ubidots-mqtt-esp

#define TOKEN "xxxxxxxxxxx" // Your Ubidots TOKEN
#define WIFINAME "xxxx" //Your SSID
#define WIFIPASS "xxxxxxxx" // Your Wifi Pass

int device1 = 1;
int device2 = 0;


Ubidots client(TOKEN);


void callback(char* topic, byte* payload, unsigned int length) {

  if ((String)topic == "/v1.6/devices/spatez/device1/lv")
  {
    int mytopic = atoi(topic);
    payload[length] = '\0';
    int mymsg = atoi ((const char*)payload);

    int val1 = mymsg;
    if(val1 == 1)
      digitalWrite(device1, HIGH);
    else if(val1 == 0)
      digitalWrite(device1, LOW);
  }

  else if ((String)topic == "/v1.6/devices/spatez/device2/lv")
  {

    int mytopic = atoi(topic);
    payload[length] = '\0';
    int mymsg = atoi ((const char*)payload);

    int val1 = mymsg;
    analogWrite(device2, val1);
    Serial.println(val1);
  } 
  else
  {}
}


void setup() {

  Serial.begin(115200);
  client.ubidotsSetBroker("industrial.api.ubidots.com"); // Sets the broker properly for the business account
  client.setDebug(true); // Pass a true or false bool value to activate debug messages
  client.wifiConnection(WIFINAME, WIFIPASS);
  pinMode(device1, OUTPUT);
  pinMode(device2, OUTPUT);
  client.begin(callback);
  client.ubidotsSubscribe("spatez", "device1");
  client.ubidotsSubscribe("spatez", "device2");
  


}

void loop() {
  // put your main code here, to run repeatedly:
  if (!client.connected()) {
    client.reconnect();
    client.ubidotsSubscribe("spatez", "device1");
    client.ubidotsSubscribe("spatez", "device2");
  }
  client.loop();
}
