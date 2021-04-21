package it.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.gestionearticolijspservletjpamaven.service.MyServiceFactory;

@WebServlet("/PrepareDeleteArticoloServlet")
public class PrepareDeleteArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareDeleteArticoloServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String parametroIdArticoloCheVoglioEliminare = request.getParameter("idArticolo");

		if (!NumberUtils.isCreatable(parametroIdArticoloCheVoglioEliminare)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/articolo/results.jsp").forward(request, response);
			return;
		}
		try {

			request.setAttribute("articoloDaEliminare", MyServiceFactory.getArticoloServiceInstance()
					.caricaSingoloElemento(Long.parseLong(parametroIdArticoloCheVoglioEliminare)));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/articolo/results.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/articolo/delete.jsp").forward(request, response);
	}

}
