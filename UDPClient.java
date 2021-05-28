// Table 25.13			A simple UDP client program

import java.net.*;
import java.io.*;

public class UDPClient
{
	final int buffSize = 200;		// Add buffer size
	DatagramSocket sock;
	String request = "Hello UDP Server";
	String response;
	InetAddress servAddr;
	int servPort;

	UDPClient (DatagramSocket s, String sName, int sPort) throws UnknownHostException
	{
		sock = s;
		servAddr = InetAddress.getByName (sName);
		servPort = sPort;
	} // End constructor

	void makeRequest ()
	{
		// Code to create the request string to be added here.
	} // End makeRequest

	void sendRequest ()
	{
		try
		{
			byte [] sendBuff = new byte [buffSize];
			sendBuff = request.getBytes ();
			DatagramPacket sendPacket = new DatagramPacket (sendBuff, sendBuff.length, servAddr, servPort);
			sock.send(sendPacket);
		}
		catch (SocketException ex)
		{
			System.err.println ("SocketException in sendRequest");
		}
		catch (IOException ex)
		{
			System.err.println ("IOException in sendRequest");
		}
	}// End sendRequest

	void getResponse ()
	{
		try
		{
			byte [] recvBuff = new byte [buffSize];
			DatagramPacket recvPacket = new DatagramPacket (recvBuff, buffSize);
			sock.receive (recvPacket);
			recvBuff = recvPacket.getData ();
			response = new String (recvBuff, 0, recvBuff.length);
		}
		catch (SocketException ex)
		{
			System.err.println ("SocketException in getRequest");
		}
		catch (IOException ex)
		{
			System.err.println ("IOException in getRequest");
		}
	} // End getResponse

	void useResponse ()
	{
		// Code to use the response string needs to be added here.
	}

	void close ()
	{
		sock.close ();
	} // End close

	public static void main (String [] args) throws IOException, SocketException
	{
		int servPort = 52012;			//Add server port number
		final String servName = "127.0.0.1";		//Add server name
		if ( args.length >= 1 )
			servPort = Integer.parseInt(args[0]);
		System.out.println("Port number is " + servPort);
		DatagramSocket sock  = new DatagramSocket ();
		UDPClient client = new UDPClient (sock, servName, servPort);
		client.makeRequest ();
		client.sendRequest ();
		client.getResponse ();
		client.useResponse ();
		client.close ();
	} // End of main

} // End of UDPClient class
