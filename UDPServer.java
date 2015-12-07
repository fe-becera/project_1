
/*
  Becera, Felicitas R. 
  Project 1

  TO COMPILE:
  Run
  >javac UDPServer.java
  >java UDPServer

  then run,

  >javac UDPClient.java
  >java UDPClient

*/
import java.io.*; 
import java.net.*; 
  
class UDPServer { 
  public static void main(String args[]) throws Exception 
    { 
     try
     { 
      DatagramSocket serverSocket = new DatagramSocket(9876); 
  
      byte[] receiveData = new byte[1024]; 
      byte[] sendData  = new byte[1024]; 
      int synchronizationNumber=0;
      int ackBit, syncBit, finishBit, wSize;
      //time delay for 2 seconds
      //network timeout for 4 seconds
      //disconnection 10 seconds

      while(true) 
        { 
  
          receiveData = new byte[1024]; 

          DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 

          System.out.println ("Waiting for datagram packet");

          serverSocket.receive(receivePacket); 
         // serverSocket.setSoTimeout(10);
         // serverSocket.setDelay(2);
          String sentence = new String(receivePacket.getData()); 
          InetAddress IPAddress = receivePacket.getAddress(); 
          int port = receivePacket.getPort(); 


        //  if(serverSocket.setDelay(2)){
          System.out.println ("From: " + IPAddress + ":" + port);
          System.out.println ("Message: " + sentence);
        //}
         // serverSocket.setSoTimeout(10);
          String lowerSentence = sentence.toLowerCase(); 
          sendData = lowerSentence.getBytes(); 
          DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port); 
  
          serverSocket.send(sendPacket); 
          serverSocket.close();
        } 

     }
      catch (SocketException ex) {
        System.out.println("UDP Port 9876: occupied");
        System.exit(1);
      }

    } 
}  