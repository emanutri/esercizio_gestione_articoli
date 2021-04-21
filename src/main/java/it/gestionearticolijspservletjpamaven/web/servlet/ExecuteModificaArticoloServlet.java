package it.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import it.gestionearticolijspservletjpamaven.model.Articolo;
import it.gestionearticolijspservletjpamaven.service.MyServiceFactory;
import it.gestionearticolijspservletjpamaven.utility.UtilityArticoloForm;

@WebServlet("/ExecuteModificaArticoloServlet")
public class ExecuteModificaArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExecuteModificaArticoloServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idArticoloDaModificare = request.getParameter("inputId");
		
		String codiceInputParam = request.getParameter("codice");
		String descrizioneInputParam = request.getParameter("descrizione");
		String prezzoInputStringParam = request.getParameter("prezzo");
		String dataArrivoStringParam = request.getParameter("dataArrivo");
		
		Date dataArrivoParsed = UtilityArticoloForm.parseDateArrivoFromString(dataArrivoStringParam);
		
		Articolo articolo = new Articolo(codiceInputParam, descrizioneInputParam, Integer.parseInt(prezzoInputStringParam), dataArrivoParsed);
		articolo.setId(Long.parseLong(idArticoloDaModificare));
		
		if (!UtilityArticoloForm.validateInput(codiceInputParam, descrizioneInputParam, prezzoInputStringParam,
				dataArrivoStringParam) || dataArrivoParsed == null) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.setAttribute("articoloDaModificare", articolo);
			request.getRequestDispatcher("/articolo/edit.jsp").forward(request, response);
			return;
		}
			try {

				MyServiceFactory.getArticoloServiceInstance().aggiorna(articolo);
				
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
