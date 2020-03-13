package br.bug.datatables;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import br.bug.dominio.Versao;
import br.bug.negocio.GerenciadorVersao;

/**
 * Servlet implementation class VersaoGsonServlet
 * 
 * //como usar inje��o de depend�ncia com servlets:
 * //http://www.codeproject.com/
 * Tips/251636/How-to-inject-Spring-beans-into-Servlets
 * 
 * @WebServlet("/VersaoGsonServlet")
 */

@Component("VersaoGsonServlet")
public class VersaoGsonServlet extends HttpServlet implements
		HttpRequestHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VersaoGsonServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private GerenciadorVersao gerenciador;

	/**
	 * @see HttpRequestHandler#handleRequest(HttpServletRequest,
	 *      HttpServletResponse)
	 */
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {
		// TODO Auto-generated method stub

		String IdVersao = request.getParameter("id");
		
		
		GerenciadorVersao gv = getGerenciador();
		if (IdVersao != null) {
			response.setContentType("application/Json");
			try {
				gv.remover(Integer.parseInt(IdVersao));
				response.getWriter().print(
						"{ \"erro\" : false, \"mensagem\" : \""
								+ "Dado removido com sucesso!" + "\" }");

			} catch (Exception e) {
				response.getWriter()
						.print("{ \"erro\" : true, \"mensagem\" : \""
								+ "Ocorreu um erro ao tentar remover o dado escolhido, possivelmente por estar sendo utilizado em outro cadastro. \\n\\n Mensagem T�cnica:\\n "
								+ e.getMessage() + "\" }");
			}
		}
		
		else {

			JQueryDataTableParamModel param = DataTablesParamUtility
					.getParam(request);

			String sEcho = param.sEcho;


			Integer[] iTotalDisplayRecords = { 0 }; // Total de registros filtrados

			long iTotalRecords = gv.totalRegistros(); // Total de registros sem filtro;

			List<Versao> versoes = new LinkedList<Versao>();

			versoes = gv.pesquisar(param.sSearch, param.iSortColumnIndex,
					param.sSortDirection, param.iDisplayStart,
					param.iDisplayLength, iTotalDisplayRecords);

			
			try {
				JsonObject jsonResponse = new JsonObject();
				jsonResponse.addProperty("sEcho", sEcho);
				jsonResponse.addProperty("iTotalRecords", iTotalRecords);
				jsonResponse.addProperty("iTotalDisplayRecords",
						iTotalDisplayRecords[0]);

				Gson gson = new GsonBuilder().setExclusionStrategies(
						new MyOwnExclusionStrategy()).create();

				// Gson gson = new
				// GsonBuilder().serializeNulls().excludeFieldsWithoutExposeAnnotation().create();

				jsonResponse.add("aaData", gson.toJsonTree(versoes));

				response.setContentType("application/Json");
				response.getWriter().print(jsonResponse.toString());

			} catch (JsonIOException e) {
				e.printStackTrace();
				response.setContentType("text/html");
				response.getWriter().print(e.getMessage());
			}

		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	public GerenciadorVersao getGerenciador() {
		return gerenciador;
	}

	public void setGerenciador(GerenciadorVersao gerenciador) {
		this.gerenciador = gerenciador;
	}

}
