package br.bug.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.bug.dao.GenericDAO;
import br.bug.model.Versao;

@Transactional
@Component
public class VersaoService{
	
	@Autowired
	private GenericDAO dao;

	public void salvar(Versao a) {
		dao.salvar(a);
	}

	public void remover(int id) {
		Versao v = buscar(id);
		dao.remover(v);
	}

	public Versao buscar(int id) {
		return dao.buscar(id, Versao.class);
	}

	public List<Versao> buscarTodos() {
		List<Versao> lista = dao.buscarTodos(Versao.class);
		return lista;
	}

	public long totalRegistros() {
		return dao.totalRegistros(Versao.class);
	}

	@SuppressWarnings("unchecked")
	public List<Versao> pesquisar(String sSearch, int iSortColumnIndex, String sSortDirection, int iDisplayStart,
			int iDisplayLength, Integer[] iTotalDisplayRecords) {
		String hql = "select v from Versao v join v.produto p";

		String hqlCount = "select count(v.id) from Versao v join v.produto p";

		boolean usouBusca = false;
		String hqlWhere = "";

		if (sSearch != null) {
			if (!sSearch.trim().isEmpty()) {
				usouBusca = true;

				hqlWhere = " where (lower(v.descricao) like lower(:versao))"
						+ " or (lower(p.descricao) like lower(:produto))";

				if (sSearch.equalsIgnoreCase("Sim")) {
					hqlWhere = hqlWhere + " or (v.ativa=true)";
				} else if (sSearch.equalsIgnoreCase("Não")) {
					hqlWhere = hqlWhere + " or (v.ativa=false)";
				}
			}
		}

		if (usouBusca) {
			hql = hql + hqlWhere;
			hqlCount = hqlCount + hqlWhere;
		}

		// ordenação
		switch (iSortColumnIndex) {
		case 0:
			hql = hql + " order by p.descricao " + sSortDirection;
			break;
		case 1:
			hql = hql + " order by v.descricao " + sSortDirection;
			break;
		case 2:
			hql = hql + " order by v.ativa " + sSortDirection;
			break;
		}

		Session session = dao.getHt().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		Query queryCount = session.createQuery(hqlCount);

		if (usouBusca) {
			query.setString("produto", "%" + sSearch + "%");
			query.setString("versao", "%" + sSearch + "%");

			queryCount.setString("produto", "%" + sSearch + "%");
			queryCount.setString("versao", "%" + sSearch + "%");
		}

		Long qtdRegistrosPesquisa = 0L;
		qtdRegistrosPesquisa = (Long) queryCount.uniqueResult();

		iTotalDisplayRecords[0] = qtdRegistrosPesquisa.intValue();

		query.setFirstResult(iDisplayStart);
		query.setMaxResults(iDisplayLength);

		List<Versao> lista = query.list();

		return lista;
	}
}