package br.bug.bean.security;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.bug.dominio.Usuario;
import br.bug.negocio.GerenciadorUsuario;

@Component
public class CustomUserDetailsService implements UserDetailsService,
		Serializable {
	
	@Autowired
	GerenciadorUsuario gerenciador;
	Usuario usuario;
	/*String senhaDigitada;
	String senhaConfirmacao;*/

	CustomUserDetailsService() {
		usuario = new Usuario();
		/*senhaDigitada = null;
		senhaConfirmacao = null;*/
	}

	/*public String salvar() {
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
			return "/views/principal.jsf";
		} catch (Exception e) {
			FacesUtil
					.addError("Ocorreu um erro ao tentar salvar o Usu�rio. Mensagem T�cnica: "
							+ e.getMessage());
			return null;
		}
	}

	
	 * public String remover() { try { gerenciador.remover(usuario);
	 * FacesUtil.addInfo("Usu�rio removido com sucesso!"); return null; } catch
	 * (Exception e) { FacesUtil.addError(
	 * "Ocorreu um erro ao tentar remover o Usu�rio escolhido, possivelmente por estar sendo utilizado em outro cadastro. Mensagem T�cnica: "
	 * +e.getMessage()); return null; } } public String alterar() { usuario =
	 * gerenciador.buscar(usuario.getId()); return "/views/usuario/form"; }
	 
	public String alterarSenha() {
		usuario = ((CustomUserDetailsService) ContextLoader
				  .getCurrentWebApplicationContext().getBean(
						  "customUserDetailsService")).getUsuarioLogado(); 
		
		
		 * usuario = gerenciador.buscar(usuario.getId());
		 * 
		 * Usuario u = ((CustomUserDetailsService) ContextLoader
		 * .getCurrentWebApplicationContext().getBean(
		 * "customUserDetailsService")).getUsuarioLogado(); if ( u.getId() ==
		 * usuario.getId() ) {
		 
		return FacesUtil.redirect("/views/usuario/form_muda_senha.jsf");
		
		 * } else { FacesUtil.addWarning("Altera��o n�o permitida!"); return
		 * null; }
		 
	}
*/
public Usuario getUsuarioLogado() 
{
		String username;
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}

		usuario = (Usuario) loadUserByUsername(username);
		return usuario;
	}

	@Override
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException, DataAccessException {
		try {
			usuario = gerenciador.buscarPorLogin(login);
			return usuario;
		} catch (Exception e) {
			//System.out.println(e.getMessage()+ " StackTrace:" + e.getStackTrace().toString());
			e.printStackTrace();
			throw new UsernameNotFoundException("Usu�rio n�o encontrado: "	+ login);
			
		}
	}

	/*public List<Usuario> getListagem() {
		return gerenciador.buscarTodos();
	}*/

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

/*	public String getSenhaDigitada() {
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
	}*/

}
