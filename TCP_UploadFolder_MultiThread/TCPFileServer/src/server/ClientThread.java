package server;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class ClientThread extends Thread {

	public static final int BUFFER_LENGTH = 8192;
	public static final String ROOT_DIR = "SERVER";

	private Socket socket;

	public ClientThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			while (true) {
				DataInputStream dis = new DataInputStream(
						socket.getInputStream());
				// readUTF: read file name
				String text = dis.readUTF();

				if (text.equalsIgnoreCase("exit")) {
					dis.close();
					socket.close();
					System.out.println("[SERVER]: BYE!");
					return;
				}

				StringTokenizer token = new StringTokenizer(text, ":");
				String cmd = token.nextToken();
				String fileName = token.nextToken();
				String path = "";
				if (cmd.equalsIgnoreCase("folder")) {
					// read server path
					path = dis.readUTF();
					String folderURL = ROOT_DIR + File.separator + path
							+ File.separator + fileName;
					File aFolder = new File(folderURL);
					aFolder.mkdirs();
					System.out.println("[SERVER] Created folder:" + folderURL);
				} else if (cmd.equalsIgnoreCase("file")) {
					path = dis.readUTF();
					String fileURL = ROOT_DIR + File.separator + path
							+ File.separator + fileName;
					System.out.println("[SERVER] Received file:" + fileURL);

					// readInt: read file length
					long length = dis.readLong();
					System.out
							.println("[SERVER] Receive file length:" + length);

					// read bytes: file data
					File file = new File(fileURL);
					FileOutputStream fos = new FileOutputStream(file);
					long totalReadByte = 0;
					byte[] buf = new byte[BUFFER_LENGTH];
					while (totalReadByte < length) {
						int readByte = dis.read(
								buf,
								0,
								(int) Math.min(buf.length, length
										- totalReadByte));
						System.out.println("[SERVER] Received buf.length = "
								+ readByte);
						fos.write(buf, 0, readByte);
						totalReadByte += readByte;
					}
					fos.close();
					System.out.println("[SERVER] Done upload file. Length = "
							+ totalReadByte);
				} else {
					System.out.println("[SERVER] Unknown command!!!");
				}
				System.out.println("[SERVER] Receive file name:" + fileName);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
