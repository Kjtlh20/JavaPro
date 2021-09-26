package ua.kiev.prog;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class CounterServlet extends HttpServlet {
	int x = 0;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//int x = 0;
		resp.getWriter().println("<html><h1>" + (x++) + "</h1></html>");
	}
}
