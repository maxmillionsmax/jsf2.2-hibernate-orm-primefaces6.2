package dao;

import java.util.List;

import javax.persistence.Query;

import model.UsuarioPessoa;

public class DaoUsuario<E> extends DaoGeneric<UsuarioPessoa> {

	private static final long serialVersionUID = 1L;

	public void removerUsario(UsuarioPessoa pessoa) throws Exception{
		
		getEntityManager().getTransaction().begin();
		
		getEntityManager().remove(pessoa);
		
		getEntityManager().getTransaction().commit();
		
		super.deletarPorId(pessoa);
	}

	@SuppressWarnings("unchecked")
	public List<UsuarioPessoa> pesquisar(String campoPesquisa) {
		
		Query query = super.getEntityManager().createQuery("from UsuarioPessoa where nome like '%"+campoPesquisa+"%' ");
		
		return query.getResultList();
	}

}
