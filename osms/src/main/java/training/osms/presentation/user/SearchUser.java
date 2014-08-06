package training.osms.presentation.user;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.framework.business.BusinessException;
import training.osms.business.user.User;
import training.osms.business.user.UserSearchOptions;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SearchUser {

	private static final int RESULTS_PER_PAGE = 10;

	private @Autowired
	training.osms.business.user.UserController controller;
	private UserSearchOptions options;
	private UserForm form;
	private List<User> result;
	private boolean deleted;
	private List<Integer> pages;
	private int page;

	public SearchUser() {
		reset();
	}

	public void reset() {
		options = new UserSearchOptions();
		result = null;
		page = 0;
		pages = null;
	}

	public UserSearchOptions getOptions() {
		return options;
	}

	public void setOptions(UserSearchOptions options) {
		this.options = options;
	}

	public UserForm getForm() {
		return form;
	}

	public void setForm(UserForm form) {
		this.form = form;
	}

	public List<User> getResult() {
		return result;
	}

	public void setResult(List<User> result) {
		this.result = result;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPage() {
		return page;
	}

	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}

	public List<Integer> getPages() {
		return pages;
	}

	public void search() {
		int resultCount = controller.searchUserCount(options);
		int pageCount = resultCount / RESULTS_PER_PAGE;

		if (resultCount % RESULTS_PER_PAGE > 0) {
			++pageCount;
		}
		pages = new ArrayList<Integer>();
		for (int page = 1; page <= pageCount; ++page) {
			pages.add(page);
		}
		goToPage(1);

	}

	public void goToPage(int page) {
		this.page = page;

		int startPosition = (page - 1) * RESULTS_PER_PAGE;
		options.setStartPosition(startPosition);
		options.setMaxResults(RESULTS_PER_PAGE);
		result = controller.searchUser(options);
		if (result.isEmpty()) {
			FacesMessage message = new FacesMessage();
			message.setSummary("A sua pesquisa não retornou nenhum resultado");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, message);
		}
	}

	public String getPageClass(int page) {
		if (page == this.page) {
			return "active";
		} else {
			return "";
		}
	}

	public String viewUpdateUser(User user) {
		User prodAux = user.clone();
		this.form = new UserForm();
		this.form.setUser(prodAux);
		return "updateUser";
	}

	public void confirmUpdateUser() {
		FacesMessage message = new FacesMessage();
		try {

			controller.updateUser(form.getUser());
			message.setSummary("User was updated");
			message.setSeverity(FacesMessage.SEVERITY_INFO);

		} catch (BusinessException e) {
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);

		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		reset();

	}

	public String viewDeletedUser(User user) {
		this.form = new UserForm();
		this.form.setUser(user);
		this.deleted = false;
		return "deleteUser";
	}

	public void deleteUser() {
		FacesMessage message = new FacesMessage();
		UserSearchOptions options = new UserSearchOptions();
		options.setUseId(form.getUser().getId());
		form.setUser(controller.searchUser(options).get(0));

		try {
			controller.delete(form.getUser());
			this.deleted = true;

			message.setSummary("User was successfully deleted");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		} catch (BusinessException e) {
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);

		}

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		reset();
	}

}
