package it.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.gestionearticolijspservletjpamaven.service.MyServiceFactory;

@WebServlet("/PrepareModificaArticoloServlet")
public class PrepareModificaArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareModificaArticoloServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parametroIdArticoloCheVoglioModificare = request.getParameter("idArticolo");

		if (!NumberUtils.isCreatable(parametroIdArticoloCheVoglioModificare)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		try {

			request.setAttribute("articoloDaModificare", MyServiceFactory.getArticoloServiceInstance()
					.caricaSingoloElemento(Long.parseLong(parametroIdArticoloCheVoglioModificare)));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/articolo/edit.jsp").forward(request, response);

	}
}
