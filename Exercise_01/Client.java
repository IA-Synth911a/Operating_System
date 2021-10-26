import java.util.Scanner;

import java.net.*;
import java.io.*;

public class Client
{
	public static void main(String[] args) throws IOException
	{
		Socket Client_Socket = new Socket("127.0.0.1", 8000);
		
		InputStreamReader Catcher = new InputStreamReader(Client_Socket.getInputStream());
		BufferedReader Buffer = new BufferedReader(Catcher);
		
		PrintWriter Thrower = new PrintWriter (Client_Socket.getOutputStream());
		Scanner Input = new Scanner(System.in);
		
		System.out.println("Input -1 to close the connection. \n");
		
		while (true)
		{
			System.out.print("Input: ");
			String Input_String = Input.nextLine();
			
			if (Input_String.equals("-1"))
			{
				Client_Socket.close();
				break;
			}
			
			Thrower.println(Input_String);
			Thrower.flush();
			
			String Temp = Buffer.readLine();
			System.out.println("Server: " + Temp);
		}
		
		Input.close();
	}
}