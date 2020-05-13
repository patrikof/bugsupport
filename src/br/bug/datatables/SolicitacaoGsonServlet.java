package br.bug.datatables;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.context.ContextLoader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import br.bug.bean.BuscaSolicitacaoBean;
import br.bug.bean.SolicitacaoBean;
import br.bug.bean.security.CustomUserDetailsService;
import br.bug.dominio.Papel;
import br.bug.dominio.SistemaOperacional;
import br.bug.dominio.Solicitacao;
import br.bug.dominio.Usuario;
import br.bug.negocio.GerenciadorSolicitacao;

@Component("SolicitacaoGsonServlet")
public class SolicitacaoGsonServlet extends HttpServlet implements
		HttpRequestHandler {

	/**
	 * 
	 */
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SolicitacaoGsonServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private GerenciadorSolicitacao gerenciador;

	/**
	 * @see HttpRequestHandler#handleRequest(HttpServletRequest,
	 *      HttpServletResponse)
	 */
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {
		// TODO Auto-generated method stub

		String IdSolicitacaoRm = request.getParameter("id_rm");
		String IdSolicitacaoResp = request.getParameter("id_resp");
		String IdSolicitacaoEdt = request.getParameter("id_edt");

		GerenciadorSolicitacao gs = getGerenciador();

		if (IdSolicitacaoRm != null) {
			
			int atendente = 0, responsavel = 0, idUsuarioLogado = 0;
			
			Solicitacao s;
			s = gs.buscar(Integer.parseInt(IdSolicitacaoRm));
			

			atendente = s.getAtendente().getId();

			if (s.getResponsavel() != null)
			{			
				responsavel = s.getResponsavel().getId();
			}
					
			
			Papel papelAdmin = new Papel();
			papelAdmin.setId(1);
			papelAdmin.setDescricao("ROLE_ADMIN");
			
			
			Usuario usuarioLogado = ((CustomUserDetailsService) ContextLoader
					.getCurrentWebApplicationContext().getBean(
							"customUserDetailsService")).getUsuarioLogado();

			idUsuarioLogado = usuarioLogado.getId();
			
				

			if ((atendente == idUsuarioLogado) || (responsavel == idUsuarioLogado)|| (responsavel == 0) || 
					( usuarioLogado.getPapeis().contains(papelAdmin) ))
			{
			
			
			
			
			response.setContentType("application/Json");
			try {
				gs.remover(Integer.parseInt(IdSolicitacaoRm));
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
			else
			{
				/*RequestDispatcher dispatcher = request
						.getRequestDispatcher("/acessonegado.jsf?error="+"A Solicita��o s� pode ser removida pelo Atendente que a cadastrou ou pelo Respons�vel dela ou por um Administrador!");
				dispatcher.forward(request, response);	*/
		
				//response.sendRedirect("/acessonegado.jsf?error="+"A Solicita��o s� pode ser removida pelo Atendente que a cadastrou ou pelo Respons�vel dela ou por um Administrador!");
				//FacesUtil.addError("A Solicita��o s� pode ser removida pelo Atendente que a cadastrou ou pelo Respons�vel dela ou por um Administrador!");
				
				response.getWriter().print(
						"{ \"erro\" : true, \"mensagem\" : \""
								+ "A Solicita��o s� pode ser removida pelo Atendente que a cadastrou ou pelo Respons�vel dela ou por um Administrador!" + "\" }");
				
				
			}
		} else if (IdSolicitacaoResp != null) {

			Solicitacao s;
			s = gs.buscar(Integer.parseInt(IdSolicitacaoResp));

			if (s.getSistemaOperacional() == null) {

				s.setSistemaOperacional(new SistemaOperacional());
			}
			
			
			s.setResponsavel(new Usuario());

			SolicitacaoBean solicitacaoBean = ((SolicitacaoBean) ContextLoader
					.getCurrentWebApplicationContext().getBean(
							"solicitacaoBean"));

			solicitacaoBean.setSolicitacao(s);

			
			
			/*RequestDispatcher dispatcher = request
					.getRequestDispatcher("/admin/alteraResponsavel.jsf");
			dispatcher.forward(request, response);
					*/
			
			
			response.sendRedirect(request.getContextPath()+"/admin/alteraResponsavel.jsf");	
			
			
			

		} else if (IdSolicitacaoEdt != null) {
			
			Solicitacao s;
			s = gs.buscar(Integer.parseInt(IdSolicitacaoEdt));

			int atendente = 0, responsavel = 0, idUsuarioLogado = 0;

			atendente = s.getAtendente().getId();

			if (s.getResponsavel() != null)
			{			
				responsavel = s.getResponsavel().getId();
			}
					
			
			Papel papelAdmin = new Papel();
			papelAdmin.setId(1);
			papelAdmin.setDescricao("ROLE_ADMIN");
			
			
			Usuario usuarioLogado = ((CustomUserDetailsService) ContextLoader
					.getCurrentWebApplicationContext().getBean(
							"customUserDetailsService")).getUsuarioLogado();

			idUsuarioLogado = usuarioLogado.getId();
			
				

			if ((atendente == idUsuarioLogado) || (responsavel == idUsuarioLogado)|| (responsavel == 0) || 
					( usuarioLogado.getPapeis().contains(papelAdmin) ))
			{

				SolicitacaoBean solicitacaoBean = ((SolicitacaoBean) ContextLoader
						.getCurrentWebApplicationContext().getBean(
								"solicitacaoBean"));

				solicitacaoBean.setSolicitacao(s);
				solicitacaoBean.carregar();

				/*RequestDispatcher dispatcher = request
						.getRequestDispatcher("/views/solicitacao/form.jsf");
				dispatcher.forward(request, response);*/
				
				
				response.sendRedirect(request.getContextPath()+"/views/solicitacao/form.jsf");
				
				
			} else {
				
				
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/acessonegado.jsf?error="+"A Solicita��o s� pode ser alterada pelo Atendente que a cadastrou ou pelo Respons�vel dela ou por um Administrador!");
				dispatcher.forward(request, response);
				
				
			
				
			}

		}

		else {

			JQueryDataTableParamModel param = DataTablesParamUtility
					.getParam(request);

			String sEcho = param.sEcho;

			// Filtros personalizados para as solicita��es

			// ############ pegar os par�metros de request!!!!!!!!!!!!!!

			BuscaSolicitacaoBean busca = ((BuscaSolicitacaoBean) ContextLoader
					.getCurrentWebApplicationContext().getBean(
							"buscaSolicitacaoBean"));

			Date dataCadastroInicial = busca.getDataCadastroInicial();
			Date dataCadastroFinal = busca.getDataCadastroFinal();
			Date dataAtualizacaoInicial = busca.getDataAtualizacaoInicial();
			Date dataAtualizacaoFinal = busca.getDataAtualizacaoFinal();
			String pessoaContato = busca.getPessoaContato();
			String assunto = busca.getAssunto();
			int id = busca.getId();
			int cliente = busca.getCliente();
			int prioridade = busca.getPrioridade();
			int status = busca.getStatus();
			int tipoSolicitacao = busca.getTipoSolicitacao();
			int sistemaOperacional = busca.getSistemaOperacional();
			int produto = busca.getProduto();
			int versao = busca.getVersao();
			int atendente = busca.getAtendente();
			int responsavel = busca.getResponsavel();

			Integer[] iTotalDisplayRecords = { 0 }; // Total de registros
													// filtrados

			long iTotalRecords = gs.totalRegistros(); // Total de registros sem
														// filtro;

			List<Solicitacao> solicitacoes = new LinkedList<Solicitacao>();

			solicitacoes = gs.pesquisar(dataCadastroInicial, dataCadastroFinal,
					dataAtualizacaoInicial, dataAtualizacaoFinal,
					pessoaContato, assunto, id, cliente, prioridade, status,
					tipoSolicitacao, sistemaOperacional, produto, versao,
					atendente, responsavel, param.iSortColumnIndex,
					param.sSortDirection, param.iDisplayStart,
					param.iDisplayLength, iTotalDisplayRecords);

			try {
				JsonObject jsonResponse = new JsonObject();
				jsonResponse.addProperty("sEcho", sEcho);
				jsonResponse.addProperty("iTotalRecords", iTotalRecords);
				jsonResponse.addProperty("iTotalDisplayRecords",
						iTotalDisplayRecords[0]);

				Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy")
						.setExclusionStrategies(new MyOwnExclusionStrategy())
						.create();

				jsonResponse.add("aaData", gson.toJsonTree(solicitacoes));

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

	public GerenciadorSolicitacao getGerenciador() {
		return gerenciador;
	}

	public void setGerenciador(GerenciadorSolicitacao gerenciador) {
		this.gerenciador = gerenciador;
	}

}
