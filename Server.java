package serv;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
	private ServerSocket ssock;
	private ExecutorService exs;
	private static final int default_poolsize=128;
	public Server(int port, int threads) throws IOException {
		ssock=new ServerSocket(port);
		exs=Executors.newFixedThreadPool(threads);
	}
	public Server(int port) throws IOException {
		this(port, default_poolsize);
	}
	public void run() {
		while (true) {
			try {
				Socket s=ssock.accept();

				exs.execute(new SingleServer(s, System.currentTimeMillis()));
			} catch (IOException e) {
				throw new Error(e);
			}
		}
	}
}
