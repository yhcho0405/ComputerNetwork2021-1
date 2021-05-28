// Table 25.16			A simple TCP server program

import java.net.*;
import java.io.*;

public class TCPServer
{
	Socket sock;
	InputStream recvStream;
	OutputStream sendStream;
	String request;
	String response = "aaa";

	TCPServer (Socket s) throws IOException, UnknownHostException
	{
	sock = s;
	recvStream = sock.getInputStream ();
	sendStream = sock.getOutputStream ();
	} // End constructor

	void getRequest ()
	{
		try
		{
			int dataSize;
			while ((dataSize = recvStream.available ()) == 0);
			byte [] recvBuff = new byte [dataSize];
			recvStream.read (recvBuff, 0, dataSize);
			request = new String (recvBuff, 0, dataSize);
		}
		catch (IOException ex)
		{
			System.err.println ("IOException in getRequest");
		}
	} // End getRequest

	void process()
	{
		// Add code to process the request string and create response string.
		response = new String(request);
		System.out.println("Received message is : " + response);
	} // End process

	void sendResponse ()
	{
		try
		{
			byte [] sendBuff = new byte [response.length ()];
			sendBuff = response.getBytes ();
			sendStream.write (sendBuff, 0, sendBuff.length);
		}
		catch (IOException ex)
		{
			System.err.println ("IOException in sendResponse");
		}
	} // End sendResponse

	void close ()
	{
		try
		{
			recvStream.close ();
			sendStream.close ();
			sock.close ();
		}
		catch (IOException ex)
		{
			System.err.println ("IOException in close");
		}
	} // End close

	public static void main (String [] args) throws IOException
	{
		int port = 62012; // Provide port number
		if ( args.length >= 1 )
			port = Integer.parseInt(args[0]);
		System.out.println("Port Number is " + port);
		ServerSocket listenSock = new ServerSocket (port);
		while (true)
		{
			TCPServer server = new TCPServer (listenSock.accept ());
			server.getRequest ();
			server.process ();
			server.sendResponse ();
			server.close ();
		}
	} // End of main

}// End of TCPServer class
