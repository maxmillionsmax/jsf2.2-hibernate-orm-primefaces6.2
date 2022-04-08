package posjavamavenhibernate;

import java.util.List;

import org.junit.Test;

import dao.DaoGeneric;
import model.TelefoneUser;
import model.UsuarioPessoa;

public class TesteHibernate {

	@Test
	public void testeHibernteUtil() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setIdade(3);
		pessoa.setNome("c");
		pessoa.setSobrenome("c");
		pessoa.setLogin("c");
		pessoa.setSenha("c");

		daoGeneric.salvar(pessoa);
		System.out.println(pessoa);
	}

	@Test
	public void testeBuscar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(3L);

		pessoa = daoGeneric.pesquisar(pessoa);

		System.out.println(pessoa);
	}

	@Test
	public void testeBuscar2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(3L, UsuarioPessoa.class);

		System.out.println(pessoa);
	}

	@Test
	public void testeUpdate() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = daoGeneric.pesquisar(2L, UsuarioPessoa.class);
		pessoa.setIdade(94);
		pessoa.setNome("name");
		pessoa.setSenha("96");

		pessoa = daoGeneric.updateMerge(pessoa);

		System.out.println(pessoa);
	}

	@Test
	public void testeDelete() throws Exception {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(4L, UsuarioPessoa.class);

		daoGeneric.deletarPorId(pessoa);

	}

	@Test
	public void testeConsultar() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("----------------------------------");
		}
	}

	@Test
	public void testeQuerylist() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa where nome = 'name'")
				.getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("----------------------------------");
		}

	}

	@Test
	public void testeQuerylistMaxResult() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa order by id")
				.setMaxResults(7).getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("----------------------------------");
		}
	}
	
	@Test
	public void testeQuerylistParameter() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa where  nome =:nome or sobrenome = :sobrenome")
				.setParameter("nome", "a").setParameter("sobrenome", "m").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	
	@Test
	public void testeQuerySomaIdade() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		
		Long somaIdade = (Long) daoGeneric.getEntityManager().createQuery("select sum(u.idade) from UsuarioPessoa u ").getSingleResult();
		Double mediaIdade = (Double) daoGeneric.getEntityManager().createQuery("select avg(u.idade) from UsuarioPessoa u ").getSingleResult();
		
		System.out.println("soma de todas as idades é --> "+somaIdade);
		System.out.println("--------------------------------------------");
		System.out.println("soma de todas as idades é --> "+mediaIdade);
		
	}
	
	@Test
	public void testeNamedQuery1() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.todos").getResultList();
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	
	@Test
	public void testeNamedQuery2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().
				createNamedQuery("UsuarioPessoa.buscarPorNome").
				setParameter("nome", "a").
				getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	
	@Test
	public void testeGravaTelefone() {
		
		DaoGeneric daoGeneric = new DaoGeneric();
		UsuarioPessoa pessoa =  (UsuarioPessoa) daoGeneric.pesquisar(8L, UsuarioPessoa.class);		
		
		TelefoneUser telefoneUser = new TelefoneUser();
		
		telefoneUser.setTipo("comercial");
		telefoneUser.setNumero("4654654654");
		telefoneUser.setUsuarioPessoa(pessoa);

		daoGeneric.salvar(telefoneUser);
		
	}
	
	@Test
	public void testeConsultaTelefone() {
		
		DaoGeneric daoGeneric = new DaoGeneric();
	
		UsuarioPessoa pessoa =  (UsuarioPessoa) daoGeneric.pesquisar(8L, UsuarioPessoa.class);		
	
		TelefoneUser telefoneUser = new TelefoneUser();
		
		for (TelefoneUser fone : pessoa.getTelefoneUsers()) {
			System.out.println("Nome: "+fone.getUsuarioPessoa().getNome());
			System.out.println("Telefone: " +fone.getNumero());
			System.out.println("Tipo: "+fone.getTipo());
			System.out.println("-----------------------------------------");
		}
				
	}
}
