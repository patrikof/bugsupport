package br.bug.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import br.bug.bean.security.CustomUserDetailsService;
import br.bug.dominio.Cliente;
import br.bug.dominio.Produto;
import br.bug.dominio.SistemaOperacional;
import br.bug.dominio.Solicitacao;
import br.bug.dominio.Usuario;
import br.bug.dominio.Versao;
import br.bug.jsf.FacesUtil;
import br.bug.negocio.GerenciadorCliente;
import br.bug.negocio.GerenciadorProduto;
import br.bug.negocio.GerenciadorSolicitacao;

@Component
@Scope("session")
public class SolicitacaoBean{

	
	@Autowired
	private GerenciadorSolicitacao gerenciador;
	@Autowired
	private GerenciadorCliente gerenciadorCliente;
	@Autowired
	private GerenciadorProduto gerenciadorProduto;
	private Solicitacao solicitacao;
	private List<Produto> produtosCliente;
	private List<Versao> versoesProduto;

	public SolicitacaoBean() {
		limparDados();
	}

	public void limparDados() {
		solicitacao = new Solicitacao();
		produtosCliente = new ArrayList<Produto>();
		versoesProduto = new ArrayList<Versao>();
	}

	public void carregar() {
		if (solicitacao.getId() > 0) 
		{
			solicitacao = gerenciador.buscar(solicitacao.getId());

			if (solicitacao.getSistemaOperacional() == null) {

				solicitacao.setSistemaOperacional(new SistemaOperacional());
			}
			
			if (solicitacao.getResponsavel() == null) {

				solicitacao.setResponsavel(new Usuario());
			}
			
			
			produtosCliente = gerenciador.buscarProdutosCliente(solicitacao.getCliente().getId());
			
			versoesProduto = gerenciador.buscarVersoesProduto(solicitacao.getProduto().getId(),	false);
			
			
		}

		/*
		 * solicitacao.setProblema(solicitacao.getProblema().replaceAll("\n",
		 * "<br />"));
		 * solicitacao.setSolucao(solicitacao.getSolucao().replaceAll("\n",
		 * "<br />"));
		 */
	}

	public void clienteMudou(ValueChangeEvent event) {

		// solicitacao = gerenciador.buscar(solicitacao.getId());
		int newValue = (Integer) event.getNewValue();
		Cliente cli = gerenciadorCliente.buscar(newValue);

		if (cli != null) {

			solicitacao.setCliente(cli);
			solicitacao.setProduto(new Produto());
			solicitacao.setVersao(new Versao());

			produtosCliente = gerenciador.buscarProdutosCliente(newValue);
			versoesProduto = new ArrayList<Versao>();
		}
	}

	public void produtoMudou(ValueChangeEvent event) {
		// solicitacao = gerenciador.buscar(solicitacao.getId());
		int newValue = (Integer) event.getNewValue();

		Produto prod = gerenciadorProduto.buscar(newValue);
		if (prod != null) {
			solicitacao.setProduto(prod);
			solicitacao.setVersao(new Versao());
			versoesProduto = gerenciador.buscarVersoesProduto(newValue,
					(solicitacao.getId() == 0));

		}

	}

	public String cadastrar() {
		limparDados();
		return FacesUtil.redirect("/views/solicitacao/form.jsf");
	}

	public String salvar() {
		try {

			boolean cadastroNovo;

			if (solicitacao.getDataCadastro() == null) {
				cadastroNovo = true;
			} else {
				cadastroNovo = false;
			}
			gerenciador.salvar(solicitacao);		

			if (cadastroNovo) {
				limparDados();
				FacesUtil.addInfo("Dados salvos com sucesso!");
				return null;
			} else {
				limparDados();		
				//return FacesUtil.redirect("/views/solicitacao/lista.jsf");
				
				return FacesUtil.forward("/views/solicitacao/lista.jsf");
				/*NavigationHandler nh = FacesUtil.getFacesContext().getApplication().getNavigationHandler();  
				nh.handleNavigation(FacesUtil.getFacesContext(), null, "/views/solicitacao/lista.jsf");
				return null;*/
				
			}

		} catch (Exception e) {
			FacesUtil
					.addError("Ocorreu um erro ao tentar salvar o cadastro. Mensagem T�cnica: "
							+ e.getMessage());
			return null;
		}
	}

	/*
	 * public String remover(int id) { try { solicitacao =
	 * gerenciador.buscar(id);
	 * 
	 * int atendente, usuarioLogado; atendente =
	 * solicitacao.getAtendente().getId(); usuarioLogado =
	 * ((CustomUserDetailsService) ContextLoader
	 * .getCurrentWebApplicationContext().getBean(
	 * "customUserDetailsService")).getUsuarioLogado() .getId();
	 * 
	 * if (atendente == usuarioLogado) { gerenciador.remover(solicitacao);
	 * FacesUtil.addInfo("Dado removido com sucesso!"); return
	 * ((BuscaSolicitacaoBean) ContextLoader
	 * .getCurrentWebApplicationContext().getBean(
	 * "buscaSolicitacaoBean")).pesquisar(); } else { FacesUtil .addWarning(
	 * "A Solicita��o s� pode ser removida pelo Atendente que a cadastrou!");
	 * return null; }
	 * 
	 * 
	 * } catch (Exception e) { FacesUtil .addError(
	 * "Ocorreu um erro ao tentar remover o dado escolhido, possivelmente por estar sendo utilizado em outro cadastro. Mensagem T�cnica: "
	 * + e.getMessage()); return null; } }
	 */

	public String alterar(int id) {

		solicitacao = gerenciador.buscar(id);
		// solicitacao = gerenciador.buscar(solicitacao.getId());

		int atendente = 0, responsavel = 0, usuarioLogado = 0;

		atendente = solicitacao.getAtendente().getId();

		responsavel = solicitacao.getResponsavel().getId();

		usuarioLogado = ((CustomUserDetailsService) ContextLoader
				.getCurrentWebApplicationContext().getBean(
						"customUserDetailsService")).getUsuarioLogado().getId();

		if ((atendente == usuarioLogado) || (responsavel == usuarioLogado)) {

			if (solicitacao.getSistemaOperacional() == null) {

				solicitacao.setSistemaOperacional(new SistemaOperacional());
			}

			produtosCliente = gerenciador.buscarProdutosCliente(solicitacao
					.getCliente().getId());
			versoesProduto = gerenciador.buscarVersoesProduto(solicitacao
					.getProduto().getId(), false);

			return FacesUtil.redirect("/views/solicitacao/form");
		} else {
			FacesUtil
					.addWarning("A Solicita��o s� pode ser alterada pelo Atendente que a cadastrou ou pelo Respons�vel dela!");
			return null;
		}
	}

	public String alterarResponsavel(int id) {

		solicitacao = gerenciador.buscar(id);

		// solicitacao = gerenciador.buscar(solicitacao.getId());
		if (solicitacao.getSistemaOperacional() == null) {

			solicitacao.setSistemaOperacional(new SistemaOperacional());
		}
		solicitacao.setResponsavel(new Usuario());
		return FacesUtil.redirect("/BugSupport/admin/alteraResponsavel");
	}

	public List<Solicitacao> getListagem() {
		return gerenciador.buscarTodos();
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	public List<Produto> getProdutosCliente() {
		return produtosCliente;
	}

	public void setProdutosCliente(List<Produto> produtosCliente) {
		this.produtosCliente = produtosCliente;
	}

	public List<Versao> getVersoesProduto() {
		return versoesProduto;
	}

	public void setVersoesProduto(List<Versao> versoesProduto) {
		this.versoesProduto = versoesProduto;
	}

}
