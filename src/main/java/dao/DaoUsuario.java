package dao;

import model.UsuarioPessoa;

public class DaoUsuario<E> extends DaoGeneric<UsuarioPessoa> {

	private static final long serialVersionUID = 1L;

	public void removerUsario(UsuarioPessoa pessoa) throws Exception{
		
		getEntityManager().getTransaction().begin();
		
		getEntityManager().remove(pessoa);
		
		getEntityManager().getTransaction().commit();
		
		super.deletarPorId(pessoa);
	}

}
