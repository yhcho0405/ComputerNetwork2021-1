// Table 25.12			A simple UDP server program

import java.net.*;
import java.io.*;

public class UDPServer
{
	final int buffSize = 200;		// Add buffer size.
	DatagramSocket sock;
	String request = "req";
	String response = "rsp";
	InetAddress clientAddr;
	int clientPort;

	UDPServer (DatagramSocket s)
	{
		sock = s;
	} // End constructor

	void getRequest ()
	{
		try
		{
			byte [] recvBuff = new byte [buffSize];
			DatagramPacket recvPacket = new DatagramPacket (recvBuff, buffSize);
			sock.receive (recvPacket);
			recvBuff = recvPacket.getData ();
			request = new String (recvBuff, 0, recvBuff.length);
			System.out.println ("received data is " + request);
			clientAddr = recvPacket.getAddress ();
			clientPort = recvPacket.getPort ();
		}
		catch (SocketException ex)
		{
			System.err.println ("SocketException in getRequest");
		}
		catch (IOException ex)
		{
			System.err.println ("IOException in getRequest");
		}
	}// End getResponse

	void process ()
	{
		// Add code for processing the request and creating the response.
	} // End process

	void sendResponse()
	{
		try
		{
			byte [] sendBuff = new byte [buffSize];
			sendBuff = response.getBytes ();
			DatagramPacket sendPacket = new DatagramPacket (sendBuff, sendBuff.length, clientAddr, clientPort);
			sock.send(sendPacket);
		}
		catch (SocketException ex)
		{
			System.err.println ("SocketException in sendResponse");
		}
		catch (IOException ex)
		{
			System.err.println ("IOException in sendResponse");
		}
	} // End sendResponse

	public static void main (String [] args) throws IOException, SocketException
	{
		int port = 52012;		// Add server port number.
		if ( args.length >= 1 )
			port = Integer.parseInt(args[0]);
		System.out.println("Port number is " + port);
		DatagramSocket sock  = new DatagramSocket (port);
		while (true)
		{
			UDPServer server = new UDPServer (sock);
			server.getRequest ();
			server.process ();
			server.sendResponse ();
		}
	} // End of main

} // End of UDPServer class
