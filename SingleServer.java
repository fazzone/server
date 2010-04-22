package serv;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SingleServer implements Runnable {
	private InputStream in;
	private PrintStream out;
	private Socket sock;
	private long time;
	public SingleServer(Socket s) throws IOException {
		in=(sock=s).getInputStream();
		out=new PrintStream(sock.getOutputStream());
	}
	public SingleServer(Socket s, long tim) throws IOException {
		this(s);
		time=tim;
	}
	public void run() {
		Scanner s=new Scanner(in);
		String k="";
			try {
				handleRequest(k=s.nextLine());
				sock.close();
				//System.out.println("Handled request in "+(System.currentTimeMillis()-time)+"ms");
			} catch (NoSuchElementException e) {
				System.out.println(e+" "+k);
			} catch (IOException e) {
				throw new Error(e);
			}
		
	}
	private void handleRequest(String line) {
		if (!line.startsWith("GET"))
			return;
		try {
			HTTPGuts.processGet(line.split(" ")[1], out);
		} catch (IOException e) {
			throw new Error(e);
		}
	}
}
