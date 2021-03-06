package managedBean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import clases.AuditoriaClass;
import model.Usuario;
import service.UsuarioService;

@ManagedBean
@ViewScoped
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private String originalURL;
	private ExternalContext externalContext;

	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private AuditoriaClass auditoriaClass;

	public Login() {
	}

	@PostConstruct
	public void init() {
		externalContext = FacesContext.getCurrentInstance().getExternalContext();
		originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);

		if (originalURL == null) {
			originalURL = externalContext.getRequestContextPath() + "/protected/main.xhtml";
		} else {
			String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);

			if (originalQuery != null) {
				originalURL += "?" + originalQuery;
			}
		}
	}

	public void login() throws IOException {
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		if ((userName.equals(null)) || userName.equals("")) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falta Completar Campo Usuario", null));
		} else {
			if ((password.equals(null)) || password.equals("")) {
				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falta Completar Campo Contraseña", null));
			} else {
				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Campos completados", null));
				System.out.println("Login: " + userName);
				Usuario user = usuarioService.getUsuario(userName);
				if (user == null) {
					FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Usuario no existe en la base de datos", null));
				} else {
					if (!user.getUsuaActivo()) {
						FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Usuario inactivo. No sé permitirá login de usuario", null));
					} else {
						try {
							if (request.getUserPrincipal() != null) {
								request.logout();
							}
							
							System.out.println("USERNAME: " + userName + " -> " + password);
							request.login(userName, password);
							externalContext.redirect(originalURL);
							FacesContext.getCurrentInstance().addMessage(null,
									new FacesMessage("Acceso Concedido", userName));
							auditoriaClass.agregarAuditoria("Ingreso al Sistema", "Usuario", userName);
						} catch (ServletException e) {
							e.printStackTrace();
							FacesContext.getCurrentInstance().addMessage("", new FacesMessage(
									FacesMessage.SEVERITY_ERROR, "Nombre de usuario o contraseña incorrecta.", null));
						}
					}
				}
			}
		}
	}

	public void mensajedeinicio() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Ingreso Exitoso", userName));
	}

	public String logout(String userName) {
		auditoriaClass.agregarAuditoria("Salida del Sistema", "Usuario", userName);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index.xhtml?faces-redirect=true";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		System.out.println("Username: " + userName);
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
