package serv;

import java.io.IOException;
import java.net.InetAddress;

public class Main {
	public static void main(String[] args) throws IOException {
		System.out.println(InetAddress.getLocalHost());
		new Thread(new Server(1337)).run();
	}
}

