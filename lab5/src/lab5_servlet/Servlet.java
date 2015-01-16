package lr5_servlet;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import java.text.SimpleDateFormat;

import javax.servlet.*;
import javax.servlet.http.*;

public class Servlet extends HttpServlet{
	// Метод GET
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String currentTime = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS").format(System.currentTimeMillis());
		Path path = FileSystems.getDefault().getPath("src/web/time.html");
		
		resp.setContentType("text/html"); // Тип содержимого
		PrintWriter out = resp.getWriter(); // Куда будет выведено сообщение
		
		List<String> page = Files.readAllLines(path);
		
		for(String str:page) {
			out.println(str.replaceAll("%MESSAGE%", currentTime));
		}
		
		out.close(); // Закрытие вывода
	}
	
}