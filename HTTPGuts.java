package serv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;

public class HTTPGuts {
	private static final byte[] EOL = {(byte) '\r', (byte) '\n'};
	private static final int BUFSIZE = 2048;
	public static void processGet(String path, PrintStream out) throws IOException {
		File file = new File(new File(".").getCanonicalPath() + path);
		sendHeader(file, out);
		if (!file.exists())
			send404(out);
		else if (!file.isDirectory())
			sendFile(file, out);
		else Directory.listingToHTML(file, out);
	}
	private static void send404(PrintStream out) throws IOException {
		out.print("<html><head><title>404: Not Found</title></head><body>" +
				"<span style=\"color: red; font-size: small\">there doesn't seem to be anything here</span>" +
				"</body></html>");
	}
	private static void sendHeader(File file, PrintStream out) throws IOException {
		out.print("HTTP/1.1 ");
		if (!file.exists())
			writeLine(out, HTTPStatus.NOT_FOUND + " not found");
		else writeLine(out, HTTPStatus.OK + " OK");
		writeLine(out, "Server: fazzone");
		writeLine(out, "Date: "+new java.util.Date());
		if (file.exists())
			if (file.isDirectory())
				writeLine(out, "Content-type: text/html");
			else sendFileHeader(file, out);
		out.write(EOL);
	}
	private static void sendFileHeader(File file, PrintStream out) throws IOException {
		writeLine(out, "Content-length: "+file.length());
		writeLine(out, "Last Modified: "+(new java.util.Date(file.lastModified())));
		writeLine(out, "Content-type: "+MIMEType.getMIMEType(file.getName()));
	}
	private static void sendFile(File file, PrintStream out) throws IOException {
		byte[] buf=new byte[BUFSIZE];
		java.io.BufferedInputStream in=new java.io.BufferedInputStream(new FileInputStream(file));
		int c;
		try {
            while ((c = in.read(buf)) > 0)
                out.write(buf, 0, c);
        } finally {
            in.close();
        }
	}
	private static void writeLine(PrintStream out, String line) throws IOException {
		out.print(line);
		out.write(EOL);
	}
}
