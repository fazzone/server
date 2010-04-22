package serv;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Date;

public class Directory {
	private static String folderIcon="<img src=\"http://distro.ibiblio.org/icons/folder.gif\">";
	private static String otherIcon ="<img src=\"http://distro.ibiblio.org/icons/unknown.gif\">";
	private static String tableHeader="<tr><td> </td><td>Name</td><td>Last Modified</td><td>Size</td></tr>";
	public static void listingToHTML(File dir, PrintStream out) throws IOException {
		out.println("<html><body>");
		out.println("<h1>Listing of "+dir.getCanonicalPath()+"</h1>");
		out.println("<table border=\"0\" cellpadding=\"6\">");
		out.println(tableHeader);
		String[] list=dir.list();
		Arrays.sort(list);
		for (String k  : list)
			if (!k.startsWith(".") && new File(dir, k).isDirectory())
				listLine(dir, k, out);
		for (String k  : list)
			if (!k.startsWith(".") && !(new File(dir, k).isDirectory()))
				listLine(dir, k, out);
		out.println("</table>");
		out.println("</html></body>");
	}
	private static void listLine(File dir, String fname, PrintStream out) {
		File f=new File(dir, fname);
		out.println("<tr>\n"+"\t<td>");
		if (f.isDirectory())
			out.print("\t\t"+folderIcon);
		else out.print("\t\t"+otherIcon);
		out.print("\t</td>\n\t<td>");
		if (f.isDirectory())
			out.println("\t\t<a href=\""+fname+"/\">"+fname+"</a>");
		else out.println("\t\t<a href=\""+fname+"\">"+fname+"</a>");
		out.print("\t</td>\n\t<td>");
		out.print("\t\t"+new Date(f.lastModified()).toLocaleString());
		out.print("\t</td>\n\t<td>");
		if (!f.isDirectory())
			out.print("\t\t"+f.length());
		else out.print("\t\t-");
		out.println("\t</td>");
		out.println("</tr>");
	}
}
