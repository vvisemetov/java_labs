package lab5_servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class App {

	public static void main(String[] args) throws Exception {

		Server server = new Server();

		ServerConnector connector = new ServerConnector(server);
		connector.setHost("127.0.0.1");
		connector.setPort(8080);

		connector.setName("main");
		server.addConnector(connector);
		// Handler
		ServletHandler myservlet = new ServletHandler();
		myservlet.addServletWithMapping(Servlet.class, "/time"); // http://localhost:8080/time
		server.setHandler(myservlet);

		try {
			server.start();
			server.join();
		} catch (Throwable t) {
			t.printStackTrace(System.err);
		} finally {
			if (server != null) {
				server.stop();
			}
		}
	}

}