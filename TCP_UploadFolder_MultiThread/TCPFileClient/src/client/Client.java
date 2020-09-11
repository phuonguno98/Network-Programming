package client;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	public static final int BUFFER_LENGTH = 8192;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 1234);
		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.print("[CLIENT]: Enter file/folder to upload:_");
			
			String input = scanner.nextLine();
			
			if (input.equalsIgnoreCase("exit")) {
				dos.writeUTF("exit");
				dos.close();
				socket.close();
				scanner.close();
				return;
			}
			
			if (input.equalsIgnoreCase("shutdown")) {
				dos.writeUTF("shutdown");
				dos.close();
				socket.close();
				scanner.close();
				return;
			}
			
			System.out.print("[CLIENT]: Enter server path to store:_");
			String path = scanner.nextLine();
			
			File inputFile = new File(input);
			if (! inputFile.exists()) {
				System.out.println("[CLIENT]: You enter not exist file/folder");
				continue;
			}
			
			boolean isDirectory = inputFile.isDirectory();
			if (isDirectory) {
				uploadFolder(inputFile, path, dos);
			} else {
				uploadFile(inputFile, path, dos);
			}
		}
	}
	
	public static void uploadFolder(File folder, String path, DataOutputStream dos) throws IOException {
		createFolder(folder.getName(), path, dos);
		
		String [] listFiles = folder.list();
		for (String fileName : listFiles) {
			File file = new File(folder.getAbsolutePath() + File.separator + fileName);
			String newPath = path + File.separator + folder.getName();
			if (file.isDirectory()) {
				uploadFolder(file, newPath, dos);
			} else {
				uploadFile(file, newPath, dos);
			}
		}
	}
	
	public static void createFolder(String name, String path, DataOutputStream dos) throws IOException {
		dos.writeUTF("folder:" + name);
		dos.writeUTF(path);
		System.out.println("[CLIENT]: Request create new folder:" + path + "/" + name);
	}
	
	public static void uploadFile(File file, String path, DataOutputStream dos) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		byte[] buf = new byte[BUFFER_LENGTH];
		int readByte = 0;
		
		// writeUTF: write file name
		dos.writeUTF("file:" + file.getName());
		System.out.println("[CLIENT] Send file name:" + file.getName());
		
		// write path
		dos.writeUTF(path);
		
		// writeInt: write file length
		long length = file.length();
		dos.writeLong(length);
		System.out.println("[CLIENT] Send file length:" + length);
		
		// write byte[]: write file data
		
		while((readByte = fis.read(buf)) != -1) {
			dos.write(buf, 0, readByte);
			System.out.println("[CLIENT] Send buf = " + readByte);
		}
		fis.close();
	}

}
