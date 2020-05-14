package br.bug.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.ContextLoader;

import br.bug.bean.security.CustomUserDetailsService;
import br.bug.jsf.FacesUtil;
import br.bug.model.Papel;
import br.bug.model.Usuario;
import br.bug.service.UsuarioService;

@Component
public class UsuarioBean {
	
	@Autowired
	UsuarioService gerenciador;
	Usuario usuario;
	String senhaDigitada;
	String senhaConfirmacao;
	UsuarioBean() {
		usuario = new Usuario();
		senhaDigitada = null;
		senhaConfirmacao = null;
	}
	public String salvar() {
		try {

			if (getSenhaDigitada() != null) {
				if (getSenhaConfirmacao() != null) {
					if (getSenhaConfirmacao().equals(getSenhaDigitada())) {
						String senhaCripto = DigestUtils
								.md5DigestAsHex(getSenhaDigitada().getBytes());
						
						usuario = ((CustomUserDetailsService) ContextLoader
								  .getCurrentWebApplicationContext().getBean(
										  "customUserDetailsService")).getUsuarioLogado();						
						usuario.setSenha(senhaCripto);
						
					} else {
						FacesUtil.addError("As senhas n�o s�o iguais! Digite novamente!");
						senhaDigitada = null;
						senhaConfirmacao = null;
						return null;
					}
				} else {
					FacesUtil.addError("Digite a senha de confirma��o!");
					senhaDigitada = getSenhaDigitada();					
					return null;
				}
			}

			gerenciador.salvar(usuario);
			FacesUtil.addInfo("Dados do usu�rio "+usuario.getLogin()+" salvos com sucesso!");
			return "/view/principal.jsf";
		} catch (Exception e) {
			FacesUtil
					.addError("Ocorreu um erro ao tentar salvar o Usu�rio. Mensagem T�cnica: "
							+ e.getMessage());
			return null;
		}
	}

	/*
	 * public String remover() { try { gerenciador.remover(usuario);
	 * FacesUtil.addInfo("Usu�rio removido com sucesso!"); return null; } catch
	 * (Exception e) { FacesUtil.addError(
	 * "Ocorreu um erro ao tentar remover o Usu�rio escolhido, possivelmente por estar sendo utilizado em outro cadastro. Mensagem T�cnica: "
	 * +e.getMessage()); return null; } } public String alterar() { usuario =
	 * gerenciador.buscar(usuario.getId()); return "/view/usuario/form"; }
	 */
	public String alterarSenha() {
		usuario = ((CustomUserDetailsService) ContextLoader
				  .getCurrentWebApplicationContext().getBean(
						  "customUserDetailsService")).getUsuarioLogado(); 
		
		/*
		 * usuario = gerenciador.buscar(usuario.getId());
		 * 
		 * Usuario u = ((CustomUserDetailsService) ContextLoader
		 * .getCurrentWebApplicationContext().getBean(
		 * "customUserDetailsService")).getUsuarioLogado(); if ( u.getId() ==
		 * usuario.getId() ) {
		 */
		return FacesUtil.redirect("/view/usuario/form_muda_senha.jsf");
		/*
		 * } else { FacesUtil.addWarning("Altera��o n�o permitida!"); return
		 * null; }
		 */
	}
	
	
	public boolean eAdmin(){
		boolean admin = false;
		// Retorna se o usu�rio tem acesso � �rea Admin ( gerente ou admin )
		usuario = ((CustomUserDetailsService) ContextLoader
				  .getCurrentWebApplicationContext().getBean(
						  "customUserDetailsService")).getUsuarioLogado();
		
		Iterator<Papel> it = usuario.getPapeis().iterator();
		while (it.hasNext()) {
			Papel p = it.next();
			if ( (p.getDescricao().equals("ROLE_ADMIN")) || (p.getDescricao().equals("ROLE_GERENTE")) ) {
				admin = true;
				break;
			}
		}		
		
		return admin;
		
	}
	
	
	public List<Usuario> getListagem() {
		return gerenciador.buscarTodos();
	}

	public Usuario getUsuario() {	
		
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSenhaDigitada() {
		return senhaDigitada;
	}

	public void setSenhaDigitada(String senhaDigitada) {
		this.senhaDigitada = senhaDigitada;
	}

	public String getSenhaConfirmacao() {
		return senhaConfirmacao;
	}

	public void setSenhaConfirmacao(String senhaConfirmacao) {
		this.senhaConfirmacao = senhaConfirmacao;
	}

}
