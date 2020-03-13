package br.bug.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public class GenericDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private HibernateTemplate ht;

	public void salvar(Object obj) 
	{
		ht.saveOrUpdate(obj);
	}

	public void remover(Object obj)
	{
		ht.delete(obj);
	}

	public <T> List<T> buscarTodos(Class<T> classe) 
	{
		return ht.loadAll(classe);
	}		

	public <T> T buscar(int id, Class<T> classe) {
		return ht.get(classe, id);
	}

	public HibernateTemplate getHt() {		
		return ht;
	}
	
	public long totalRegistros(Class<?> classe) {
		long size = 0;  
	    	    
	    Session session = getHt().getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(classe);
	    
	    c.setProjection(Projections.rowCount());
	    size = (Long)  c.uniqueResult();
	    return size;
	}
}