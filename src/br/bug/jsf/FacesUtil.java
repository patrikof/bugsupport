package br.bug.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

public class FacesUtil {
	/**
	 * Retorna o Contexto
	 */
	public static FacesContext getFacesContext(){
		return FacesContext.getCurrentInstance();
	}	 	
	/**
	 * Realiza a navegação com redirect
	 */		
	public static String redirect(String caminho) {
		return caminho + "?faces-redirect=true";
	}
	
	/**
	 * Realiza a navegação com Forward (Patrik)
	 */		
	public static String forward(String caminho) {	
	NavigationHandler nh = getFacesContext().getApplication().getNavigationHandler();  
	nh.handleNavigation(FacesUtil.getFacesContext(), null, caminho);
	return caminho;	
}
	/**
	 * Adiciona uma mensagem ao FacesContext	
	 */
	private  static void  addMessage(FacesMessage.Severity severidade, String mensagem) {
		FacesMessage facesMessage = new FacesMessage(severidade, mensagem, null);
		getFacesContext().addMessage(null, facesMessage);
	}
	/**
	 * Adiciona uma mensagem de erro ao FacesContext	
	 */
	public  static void  addError(String mensagem) {
		addMessage(FacesMessage.SEVERITY_ERROR, mensagem);
	}
	/**
	 * Adiciona uma mensagem de warning ao FacesContext	 
	 */
	public  static void  addWarning(String mensagem) {
		addMessage(FacesMessage.SEVERITY_WARN, mensagem);
	}
	/**
	 * Adiciona uma mensagem de informação ao FacesContext	
	 */
	public  static void  addInfo(String mensagem) {
		addMessage(FacesMessage.SEVERITY_INFO, mensagem);
	}
	
}