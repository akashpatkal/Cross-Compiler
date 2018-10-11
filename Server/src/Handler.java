import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 
 * @author akash
 *
 */
public class Handler extends Thread {
	
	DataOutputStream dataOut;
	String name;
	DataInputStream inputReader;
	String language;
	String filename, username, Textinfile;
	BufferedWriter bufferWritter;
	Runtime runtime;
	int count = 0;

	public void run() {
		int i = 0;
		while (true) {

			String input = null;
			try {
				input = inputReader.readUTF();
			} catch (IOException e) {

			}
			if (input != null) {
				if (i == 0) { 
					System.out.println(input);
					language = input;
					i++;
				} else if (i == 1) {
					{
						filename = input;
						i++;
					}
				} else if (i == 2) {

					Textinfile = input;
					System.out.println(input);
					i++;

					
					File file = null;
					String filePath= "/" + filename;
					try {
						
						file = new File(filePath);
						if (!file.exists()) {
							file.createNewFile();
						}
					} catch (IOException e) {

						e.printStackTrace();
					}
					try {
						System.out.println(file.getAbsolutePath());

						bufferWritter = new BufferedWriter(new FileWriter(file));

						bufferWritter.append(Textinfile);
						bufferWritter.close();
						if (language.equals("C")) {
							Process process = runtime.exec("gcc /" + filePath);
							BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

							System.out.println("Error");
							String message = getConsoleMessage(reader);
							dataOut.writeUTF("Error\n" + message);

							process = runtime.exec("./a.out");
							BufferedReader rBufferedReader = new BufferedReader(
									new InputStreamReader(process.getInputStream()));
							System.out.println("Output");
							message = getConsoleMessage(rBufferedReader);
							dataOut.writeUTF("Output\n" + message);

						} else if (language.equals("C++")) {
							Process process = runtime.exec("g++ /" + username + "/" + filename);
							BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
							System.out.println("Error");
							String message = getConsoleMessage(reader);
							dataOut.writeUTF("Error\n" + message);
							process = runtime.exec("./a.out");
							BufferedReader rBufferedReader = new BufferedReader(
									new InputStreamReader(process.getInputStream()));
							System.out.println("Output");
							message = getConsoleMessage(rBufferedReader);
							dataOut.writeUTF("Output\n" + message);
							
						} else if (language.equals("python")) {
							
							Process process = runtime.exec("python /" + filename);
							BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
							System.out.println("Error");
							String s = getConsoleMessage(reader);
							dataOut.writeUTF("Error\n" + s);
							BufferedReader rBufferedReader = new BufferedReader(
									new InputStreamReader(process.getInputStream()));
							s = getConsoleMessage(rBufferedReader);
							dataOut.writeUTF("Output\n" + s);
						}
					} catch (Exception e) {

						e.printStackTrace();
					}
					i = 0;

				}

			}
		}
	}

	private String getConsoleMessage(BufferedReader rBufferedReader) {
		String s;
		StringBuilder builder = new StringBuilder("");
		try {

			while ((s = rBufferedReader.readLine()) != null) {
				System.out.println(s);
				builder.append(s);
				count++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return builder.toString();

	}

	public Handler(Socket socket, String userName, DataInputStream input) {

		try {
			username = userName.trim();
			inputReader = input;
			dataOut = new DataOutputStream(socket.getOutputStream());
			runtime = Runtime.getRuntime();
			System.out.println("mkdir -p " + username);
			runtime.exec("mkdir -p " + username);
			System.out.println("cd  /" + username);
			System.out.println("connect");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
