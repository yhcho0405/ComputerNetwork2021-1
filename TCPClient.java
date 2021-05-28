// Table 25.17			A simple TCP client program

import java.net.*;
import java.io.*;

public class TCPClient
{
	Socket sock;
	OutputStream sendStream;
	InputStream recvStream;
	String request = "Hello~ Computer Network";
	String response;

	TCPClient (String server, int port, String str) throws IOException, UnknownHostException
	{
	sock = new Socket (server, port);
	sendStream = sock.getOutputStream ();
	recvStream = sock.getInputStream ();
	request = new String(str);
	} // End constructor

	void makeRequest ()
	{
		// Add code to make the request string here.
		//request = "Hello~ Computer Network";
	} // End makeRequest

	void sendRequest ()
	{
		try
		{
			byte [] sendBuff = new byte [request.length ()];
			sendBuff = request.getBytes ();
			sendStream.write (sendBuff, 0, sendBuff.length);
			System.out.println ("Send Request: " + request);
		}
		catch (IOException ex)
		{
			System.err.println ("IOException in sendRequest");
		}
	} // End sendRequest

	void getResponse ()
	{
		System.out.println ("getResponse");
		try
		{
			int dataSize;
			while ((dataSize = recvStream.available ()) == 0);
			byte [] recvBuff = new byte [dataSize];
			recvStream.read (recvBuff, 0, dataSize);
			response = new String (recvBuff, 0, dataSize);
		}
		catch (IOException ex)
		{
			System.err.println ("IOException in getResponse");
		}
	}// End getResponse

	void useResponse ()
	{
		// Add code to use the response string here.
		System.out.println ("use Responses: " + response);
	} // End userResponse

	void close ()
	{
		try
		{
		sendStream.close ();
		recvStream.close ();
		sock.close ();
		}
		catch (IOException ex)
		{
			System.err.println ("IOException in close");
		}
	} // End close

	public static void main (String [] args) throws IOException
	{
		int servPort = 62012;				// Provide server port
		final String servName = "127.0.0.1";			// Provide server name
		String reqStr = "Hello~ Computer Network";;
		if ( args.length >= 1 )
		  servPort = Integer.parseInt(args[0]);
		System.out.println("Port number is " + servPort);
		if ( args.length >= 2 )
		  reqStr = args[1];
		else
		  System.out.println("no arguemnts. default argument is " + reqStr);
		TCPClient client = new TCPClient (servName, servPort, reqStr);
		client.makeRequest ();
		client.sendRequest ();
		client.getResponse ();
		client.useResponse ();
		client.close ();
	}// End of main

}// End of TCPClient class
