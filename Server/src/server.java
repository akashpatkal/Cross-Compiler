import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author akash
 *
 */
public class server {
	ServerSocket server;
	Socket socket;
	int clientNumber;

	public server() {

		clientNumber = 0;
		try {
			server = new ServerSocket(9092);
			System.out.println("Server is running At "+server.getInetAddress()+":"+ server.getLocalPort() );
			while (true) {
                
				socket = server.accept();
				System.out.println("Client is accepted "+ clientNumber);
				String value;
				DataInputStream input = new DataInputStream(new DataInputStream(socket.getInputStream()));
				while (true) {
					value = input.readUTF();
					if (value != null) {
						value = value.trim();
						if (!value.equals("")) {
							System.out.println("Value " + value);
							break;
						}
					}
				}

				Handler handler = new Handler(socket, value, input);
				handler.start();
				clientNumber++;
				System.out.println("handler start");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.gc();

			e.printStackTrace();
		}

	}

	protected void finalize() {
		try {
			super.finalize();
			server.close();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		new server();
	}

}
