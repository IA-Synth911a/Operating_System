import java.net.*;
import java.io.*;

public class Server
{
	public static void main(String[] args) throws IOException
	{
		ServerSocket Server_Socket = new ServerSocket(8000);
		Socket Client_Socket = Server_Socket.accept();
		
		if (Client_Socket.isConnected())
		{
			System.out.println("Client Connected. \n");
			
			InputStreamReader Catcher = new InputStreamReader(Client_Socket.getInputStream());
			BufferedReader Buffer = new BufferedReader(Catcher);
			
			PrintWriter Thrower = new PrintWriter (Client_Socket.getOutputStream());
			int Temp_Catcher;
			
			while (true)
			{
				Temp_Catcher = Catcher.read();
				if (Temp_Catcher == -1)
				{
					System.out.println("Client Disconnected");
					break;
				}
				
				String Temp = String.valueOf((char)Temp_Catcher) + Buffer.readLine();
				System.out.println(Temp);
				System.out.println("Client: " + Temp);
				
				Thrower.println(Temp);
				Thrower.flush();
			}
		}
		
		Client_Socket.close();
		Server_Socket.close();
	}
}