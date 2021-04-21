package it.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.gestionearticolijspservletjpamaven.model.Articolo;
import it.gestionearticolijspservletjpamaven.service.MyServiceFactory;

@WebServlet("/ExecuteDeleteArticoloServlet")
public class ExecuteDeleteArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteDeleteArticoloServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String IdArticoloDaEliminare = request.getParameter("inputId");

		if (!NumberUtils.isCreatable(IdArticoloDaEliminare)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		try {

			Articolo articoloDaEliminare = MyServiceFactory.getArticoloServiceInstance()
					.caricaSingoloElemento(Long.parseLong(IdArticoloDaEliminare));
			MyServiceFactory.getArticoloServiceInstance().rimuovi(articoloDaEliminare);
			request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().listAll());

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/articolo/results.jsp").forward(request, response);
	}

}
