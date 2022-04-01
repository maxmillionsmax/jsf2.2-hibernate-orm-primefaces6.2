package managedBean;

import java.io.Serializable;

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
}
