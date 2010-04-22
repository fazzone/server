package serv;

import java.util.HashMap;

public class MIMEType {
	private static HashMap<String, String> map=new HashMap<String, String>();
	static {
		map.put("", "content/unknown");
        map.put(".uu", "application/octet-stream");
        map.put(".exe", "application/octet-stream");
        map.put(".ps", "application/postscript");
        map.put(".zip", "application/zip");
        map.put(".sh", "application/x-shar");
        map.put(".tar", "application/x-tar");
        map.put(".snd", "audio/basic");
        map.put(".au", "audio/basic");
        map.put(".wav", "audio/x-wav");
        map.put(".gif", "image/gif");
        map.put(".jpg", "image/jpeg");
        map.put(".jpeg", "image/jpeg");
        map.put(".htm", "text/html");
        map.put(".html", "text/html");
        map.put(".text", "text/plain");
        map.put(".c", "text/plain");
        map.put(".cc", "text/plain");
        map.put(".c++", "text/plain");
        map.put(".h", "text/plain");
        map.put(".pl", "text/plain");
        map.put(".txt", "text/plain");
        map.put(".java", "text/plain");
	}
	public static String getMIMEType(String fname) {
		int idex;
		String ext;
		if ((idex = fname.indexOf('.')) < 0)
			ext="";
		else ext=fname.substring(idex);
		return map.get(ext);
	}
}
