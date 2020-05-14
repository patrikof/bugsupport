/**
 * 
 */
package br.bug.config;

import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author patrik
 *
 */
public class ConfiguracaoListener implements ServletContextListener {

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Locale aLocale = new Locale("pt","BR");
		
		Locale.setDefault(aLocale);
		
		TimeZone.setDefault(TimeZone.getTimeZone("America/Fortaleza"));
	}
}