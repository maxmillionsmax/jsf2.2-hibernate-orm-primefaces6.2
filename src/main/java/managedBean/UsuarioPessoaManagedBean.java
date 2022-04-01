package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.DaoGeneric;
import model.UsuarioPessoa;

@ManagedBean(name = "usuarioPessoaManagedBean")
@ViewScoped
public class UsuarioPessoaManagedBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private UsuarioPessoa usuarioPessoa = new UsuarioPessoa();

	private DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<>();

	private List<UsuarioPessoa> list = new ArrayList<UsuarioPessoa>();

	public void setUsuarioPessoa(UsuarioPessoa usuarioPessoa) {
		this.usuarioPessoa = usuarioPessoa;
	}

	public UsuarioPessoa getUsuarioPessoa() {
		return usuarioPessoa;
	}

	public String salvar() {
		
		daoGeneric.salvar(usuarioPessoa);

		return "";
	}
	
	
	public String novo() {
		usuarioPessoa =  new UsuarioPessoa();
		return "";
	}
	
	public List<UsuarioPessoa> getList() {
		list = daoGeneric.listar(UsuarioPessoa.class);
		return list;
	}
}
