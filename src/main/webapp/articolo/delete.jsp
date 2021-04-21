<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="it.gestionearticolijspservletjpamaven.model.Articolo"%>
<!DOCTYPE html>
<html>
	<head>
	<jsp:include page="../header.jsp" />
		<title>Elimina Articolo</title>
		 <link href="./assets/css/global.css" rel="stylesheet">
	</head>
	<body>
		<jsp:include page="../navbar.jsp" />
		<main role="main" class="container">
		
		
		
			<div class='card'>
			    <div class='card-header'>
			        Sei sicuro di voler eliminare il seguente articolo?
			    </div>
	   		   	<% Articolo articoloInPagina = (Articolo)request.getAttribute("articoloDaEliminare"); %>
			    
			    <div class='card-body'>
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Codice</dt>
					  <dd class="col-sm-9"><%=articoloInPagina.getCodice() %></dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Descrizione:</dt>
					  <dd class="col-sm-9"><%=articoloInPagina.getDescrizione() %></dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Prezzo:</dt>
					  <dd class="col-sm-9"><%=articoloInPagina.getPrezzo() %></dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Data di Arrivo:</dt>
					  <dd class="col-sm-9"><%=articoloInPagina.getDataArrivo()!=null? new SimpleDateFormat("dd/MM/yyyy").format(articoloInPagina.getDataArrivo()):"N.D."  %></dd>
			    	</dl>
			    	
			    </div>
			    
			    <div class='card-footer'>
			    <form method="post" action="ExecuteModificaArticoloServlet">
			    	<a href="ListArticoliServlet" class="btn  btn-sm btn-outline-secondary" style='width:80px'>
		            <i class='fa fa-chevron-left'></i> Back
			        </a>
				    <button type="submit" name="elimina" value="elimina" id="elimina" class="btn btn-outline-danger btn-sm">Elimina</button>
				    <input type="hidden" name ="inputId" value=<%=articoloInPagina.getId() %>>
			    
			    </form>
			        
			           
			    </div>
			</div>	
		</main>
		<jsp:include page="../footer.jsp" />
	</body>
</html>