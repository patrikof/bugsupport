package br.bug.negocio;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import br.bug.bean.security.CustomUserDetailsService;
import br.bug.dao.GenericDAO;
import br.bug.dominio.Papel;
import br.bug.dominio.Produto;
import br.bug.dominio.Solicitacao;
import br.bug.dominio.Usuario;
import br.bug.dominio.Versao;
import br.bug.jsf.FacesUtil;
import br.bug.relatorio.RelatorioJasper;
import net.sf.jasperreports.engine.JRException;

@Transactional
@Component
public class GerenciadorSolicitacao {

	@Autowired
	private GenericDAO dao;

	public void salvar(Solicitacao a) {

		if (a.getSistemaOperacional().getId() == 0) {

			a.setSistemaOperacional(null);
		}

		if (a.getResponsavel() != null) {

			if (a.getResponsavel().getId() == 0) {
				a.setResponsavel(null);
			} else {
				/*
				 * Usuario resp = new Usuario(); resp = dao.buscar(a.getResponsavel().getId(),
				 * Usuario.class); a.setResponsavel(resp);
				 */

			}
		}

		if (a.getId() == 0) {
			a.setDataCadastro(new Date());
			a.setHoraCadastro(new Date());

			Usuario usuario = ((CustomUserDetailsService) ContextLoader.getCurrentWebApplicationContext()
					.getBean("customUserDetailsService")).getUsuarioLogado();
			a.setAtendente(usuario);

			boolean aplicaRegraProgramador = false;

			if (a.getResponsavel() == null) {

				aplicaRegraProgramador = true;
			} else {

				aplicaRegraProgramador = (a.getResponsavel().getId() == 0);
			}

			if (aplicaRegraProgramador) {
				Iterator<Papel> it = usuario.getPapeis().iterator();
				boolean eProgramador = false;

				while (it.hasNext()) {
					Papel p = it.next();
					if (p.getDescricao().equals("ROLE_PROGRAMADOR")) {
						eProgramador = true;
						break;
					}
				}
				a.setResponsavel(new Usuario());
				if (eProgramador) {
					a.setResponsavel(usuario);
				} else {
					a.setResponsavel(null);
				}
			}
		}
		a.setDataAtualizacao(new Date());
		a.setHoraAtualizacao(new Date());
		dao.salvar(a);
	}

	public void remover(int id) {
		Solicitacao s = buscar(id);
		dao.remover(s);
	}

	public Solicitacao buscar(int id) {
		return dao.buscar(id, Solicitacao.class);
	}

	public List<Solicitacao> buscarTodos() {
		return dao.buscarTodos(Solicitacao.class);
	}

	@SuppressWarnings("unchecked")
	public List<Produto> buscarProdutosCliente(int idCliente) {
		String QueryAux = "select p from Produto p join p.clientes c where c.id =?";
		QueryAux = QueryAux + " order by p.descricao, p.id";
		List<Produto> lista = dao.getHt().find(QueryAux, idCliente);
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Versao> buscarVersoesProduto(int idProduto, boolean somenteAtivas) {

		String QueryAux = "select v from Versao v where v.produto.id =?";

		if (somenteAtivas) {

			QueryAux = QueryAux + " and v.ativa=true";
		}

		QueryAux = QueryAux + " order by v.descricao, v.id";

		List<Versao> lista = dao.getHt().find(QueryAux, idProduto);
		return lista;

	}

	@SuppressWarnings("unchecked")
	public List<Solicitacao> pesquisar(Date dataCadastroInicial, Date dataCadastroFinal, Date dataAtualizacaoInicial,
			Date dataAtualizacaoFinal, String pessoaContato, String assunto, int id, int cliente, int prioridade,
			int status, int tipoSolicitacao, int sistemaOperacional, int produto, int versao, int atendente,
			int responsavel, int iSortColumnIndex, String sSortDirection, int iDisplayStart, int iDisplayLength,
			Integer[] iTotalDisplayRecords) {

		String hqlFrom = " from Solicitacao s left join s.produto pro left join s.cliente cli"
				+ " left join s.prioridade pri left join s.status stat left join s.tipoSolicitacao tipsol"
				+ " left join s.sistemaOperacional so left join s.versao ver left join s.atendente atend"
				+ " left join s.responsavel resp";

		String hql = "select s" + hqlFrom;

		String hqlCount = "select count(s.id)" + hqlFrom;

		boolean BuscouId = false;
		boolean BuscouDataCadastroInicial = false;
		boolean BuscouDataCadastroFinal = false;
		boolean BuscouDataAtualizacaoInicial = false;
		boolean BuscouDataAtualizacaoFinal = false;
		boolean BuscouPessoaContato = false;
		boolean BuscouAssunto = false;
		boolean BuscouCliente = false;
		boolean BuscouPrioridade = false;
		boolean BuscouStatus = false;
		boolean BuscouTipoSolicitacao = false;
		boolean BuscouSistemaOperacional = false;
		boolean BuscouProduto = false;
		boolean BuscouVersao = false;
		boolean BuscouAtendente = false;
		boolean BuscouResponsavel = false;

		String hqlWhere = " where (1=1)";

		if (id > 0) {
			BuscouId = true;
			hqlWhere = hqlWhere + " and (s.id = :id)";

		} else {

			if (dataCadastroInicial != null) {
				BuscouDataCadastroInicial = true;
				// c.add(Restrictions.ge("dataCadastro", dataCadastroInicial));
				hqlWhere = hqlWhere + " and (s.dataCadastro >= :dataCadastroInicial)";
			}
			if (dataCadastroFinal != null) {
				BuscouDataCadastroFinal = true;
				// c.add(Restrictions.le("dataCadastro", dataCadastroFinal));
				hqlWhere = hqlWhere + " and (s.dataCadastro <= :dataCadastroFinal)";
			}
			if (dataAtualizacaoInicial != null) {
				BuscouDataAtualizacaoInicial = true;
				// c.add(Restrictions.ge("dataAtualizacao",
				// dataAtualizacaoInicial));
				hqlWhere = hqlWhere + " and (s.dataAtualizacao >= :dataAtualizacaoInicial)";
			}
			if (dataAtualizacaoFinal != null) {
				BuscouDataAtualizacaoFinal = true;
				// c.add(Restrictions.le("dataAtualizacao",
				// dataAtualizacaoFinal));
				hqlWhere = hqlWhere + " and (s.dataAtualizacao <= :dataAtualizacaoFinal)";
			}

			if (pessoaContato != null) {
				if (!pessoaContato.trim().isEmpty()) {
					// c.add(Restrictions.like("pessoaContato","%" +
					// pessoaContato + "%").ignoreCase());
					BuscouPessoaContato = true;
					hqlWhere = hqlWhere + " and (lower(s.pessoaContato) like lower(:pessoaContato))";

				}
			}
			if (assunto != null) {
				if (!assunto.trim().isEmpty()) {
					BuscouAssunto = true;
					// c.add(Restrictions.like("assunto", "%" + assunto +
					// "%").ignoreCase());
					hqlWhere = hqlWhere + " and (lower(s.assunto) like lower(:assunto))";
				}
			}
			if (cliente > 0) {
				BuscouCliente = true;
				// c.add(Expression.eq("cliente.id", cliente));
				hqlWhere = hqlWhere + " and (cli.id = :cliente)";
			}
			if (prioridade > 0) {
				BuscouPrioridade = true;
				// c.add(Expression.eq("prioridade.id", prioridade));
				hqlWhere = hqlWhere + " and (pri.id = :prioridade)";
			}
			if (status > 0) {
				// c.add(Expression.eq("status.id", status));
				BuscouStatus = true;
				hqlWhere = hqlWhere + " and (stat.id = :status)";
			}
			if (tipoSolicitacao > 0) {
				// c.add(Expression.eq("tipoSolicitacao.id", tipoSolicitacao));
				BuscouTipoSolicitacao = true;
				hqlWhere = hqlWhere + " and (tipsol.id = :tipoSolicitacao)";

			}
			if (sistemaOperacional > 0) {
				BuscouSistemaOperacional = true;
				// c.add(Expression.eq("sistemaOperacional.id",
				// sistemaOperacional));
				hqlWhere = hqlWhere + " and (so.id = :sistemaOperacional)";
			}
			if (produto > 0) {
				BuscouProduto = true;
				// c.add(Expression.eq("produto.id", produto));
				hqlWhere = hqlWhere + " and (pro.id = :produto)";
			}
			if (versao > 0) {
				BuscouVersao = true;
				// c.add(Expression.eq("versao.id", versao));
				hqlWhere = hqlWhere + " and (ver.id = :versao)";
			}
			if (atendente > 0) {
				BuscouAtendente = true;
				// c.add(Expression.eq("atendente.id", atendente));
				hqlWhere = hqlWhere + " and (atend.id = :atendente)";
			}
			if (responsavel > 0) {
				BuscouResponsavel = true;
				// c.add(Expression.eq("responsavel.id", responsavel));
				hqlWhere = hqlWhere + " and (resp.id = :responsavel)";
			} else if (responsavel == -1) {
				// c.add(Restrictions.isNull("responsavel.id"));
				hqlWhere = hqlWhere + " and (resp.id is null)";
			}

		}

		hql = hql + hqlWhere;
		hqlCount = hqlCount + hqlWhere;

		// ordena��o ( feito inicialmente s� para 1 coluna!!!!!)

		/*
		 * <th>C�digo</th> <th>Cliente</th> <th>Produto</th> <th>Assunto</th>
		 * <th>Respons�vel</th> <th>Cadastro</th>
		 */
		switch (iSortColumnIndex) {
		case 0:
			hql = hql + " order by s.id " + sSortDirection;
			break;
		case 1:
			hql = hql + " order by cli.nomeFantasia " + sSortDirection;
			break;
		case 2:
			hql = hql + " order by tipsol.descricao " + sSortDirection;
			break;
		case 3:
			hql = hql + " order by pro.descricao " + sSortDirection;
			break;
		case 4:
			hql = hql + " order by s.assunto " + sSortDirection;
			break;
		case 5:
			hql = hql + " order by resp.login " + sSortDirection;
			break;
		case 6:
			hql = hql + " order by pessoaContato " + sSortDirection;
			break;
		case 7:
			hql = hql + " order by s.dataCadastro " + sSortDirection;
			break;
		}

		Session session = dao.getHt().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		Query queryCount = session.createQuery(hqlCount);

		// Setando os par�metros da busca
		if (BuscouId) {
			query.setInteger("id", id);
			queryCount.setInteger("id", id);
		}
		if (BuscouDataCadastroInicial) {
			query.setDate("dataCadastroInicial", dataCadastroInicial);
			queryCount.setDate("dataCadastroInicial", dataCadastroInicial);
		}
		if (BuscouDataCadastroFinal) {
			query.setDate("dataCadastroFinal", dataCadastroFinal);
			queryCount.setDate("dataCadastroFinal", dataCadastroFinal);
		}
		if (BuscouDataAtualizacaoInicial) {
			query.setDate("dataAtualizacaoInicial", dataAtualizacaoInicial);
			queryCount.setDate("dataAtualizacaoInicial", dataAtualizacaoInicial);
		}
		if (BuscouDataAtualizacaoFinal) {
			query.setDate("dataAtualizacaoFinal", dataAtualizacaoFinal);
			queryCount.setDate("dataAtualizacaoFinal", dataAtualizacaoFinal);
		}
		if (BuscouPessoaContato) {
			query.setString("pessoaContato", "%" + pessoaContato + "%");
			queryCount.setString("pessoaContato", "%" + pessoaContato + "%");
		}
		if (BuscouAssunto) {
			query.setString("assunto", "%" + assunto + "%");
			queryCount.setString("assunto", "%" + assunto + "%");
		}
		if (BuscouCliente) {
			query.setInteger("cliente", cliente);
			queryCount.setInteger("cliente", cliente);
		}
		if (BuscouPrioridade) {
			query.setInteger("prioridade", prioridade);
			queryCount.setInteger("prioridade", prioridade);
		}
		if (BuscouStatus) {
			query.setInteger("status", status);
			queryCount.setInteger("status", status);
		}
		if (BuscouTipoSolicitacao) {
			query.setInteger("tipoSolicitacao", tipoSolicitacao);
			queryCount.setInteger("tipoSolicitacao", tipoSolicitacao);
		}
		if (BuscouSistemaOperacional) {
			query.setInteger("sistemaOperacional", sistemaOperacional);
			queryCount.setInteger("sistemaOperacional", sistemaOperacional);
		}
		if (BuscouProduto) {
			query.setInteger("produto", produto);
			queryCount.setInteger("produto", produto);
		}
		if (BuscouVersao) {
			query.setInteger("versao", versao);
			queryCount.setInteger("versao", versao);
		}
		if (BuscouAtendente) {
			query.setInteger("atendente", atendente);
			queryCount.setInteger("atendente", atendente);
		}
		if (BuscouResponsavel) {
			query.setInteger("responsavel", responsavel);
			queryCount.setInteger("responsavel", responsavel);
		}

		Long qtdRegistrosPesquisa = 0L;
		qtdRegistrosPesquisa = (Long) queryCount.uniqueResult();

		iTotalDisplayRecords[0] = qtdRegistrosPesquisa.intValue();

		query.setFirstResult(iDisplayStart);
		query.setMaxResults(iDisplayLength);

		List<Solicitacao> lista = query.list();

		return lista;
	}

	public void gerarPdf(List<Solicitacao> solicitacoes) throws JRException, IOException {
		if (solicitacoes != null)
			RelatorioJasper.imprimir(solicitacoes, "RelatorioSolicitacoes", null);
		else
			FacesUtil.addInfo("N�o h� resultado para gerar um PDF.");

	}

	public long totalRegistros() {
		return dao.totalRegistros(Solicitacao.class);
	}
}
