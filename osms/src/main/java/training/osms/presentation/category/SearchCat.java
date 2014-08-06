package training.osms.presentation.category;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.framework.business.BusinessException;
import training.osms.business.category.CatController;
import training.osms.business.category.CatSearchOptions;
import training.osms.business.category.Category;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SearchCat {

	private static final int RESULTS_PER_PAGE = 10;

	private @Autowired
	CatController controller;
	private CatSearchOptions options;
	private List<Category> result;
	private boolean catDeleted;
	private CatForm form;
	private List<Integer> pages;
	private int page;

	public SearchCat() {
		reset();
	}

	public void reset() {
		options = new CatSearchOptions();
		result = null;
		page = 0;
		pages = null;
	}

	public CatSearchOptions getOptions() {
		return options;
	}

	public void setOptions(CatSearchOptions options) {
		this.options = options;
	}

	public List<Category> getResult() {
		return result;
	}

	public void setResult(List<Category> result) {
		this.result = result;
	}

	public void setCatDeleted(boolean catDeleted) {
		this.catDeleted = catDeleted;
	}

	public boolean isCatDeleted() {
		return catDeleted;
	}

	public void setForm(CatForm form) {
		this.form = form;
	}

	public CatForm getForm() {
		return form;
	}

	public List<Integer> getPages() {
		return pages;
	}

	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void search() {
		int resultCount = controller.searchCatCount(options);

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
		result = controller.searchCats(options);

		if (result.isEmpty()) {
			FacesMessage message = new FacesMessage();
			message.setSummary("Sua pesquisa não retornou nenhum resultado");
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

	public String update(Category category) {
		Category catAux = category.clone();
		// catAux.setId(category.getId());
		// catAux.setDescription(category.getDescription());
		// catAux.setName(category.getName());
		this.form = new CatForm();
		this.form.setCategory(catAux);
		return "updateCategory";
	}

	public void confirmUpdate() {
		FacesMessage message = new FacesMessage();
		try {
			controller.updateCategory(form.getCategory());
			message.setSummary("Category was successfully update");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		} catch (BusinessException e) {
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		reset();
	}

	public String delete(Category category) {
		this.form = new CatForm();
		this.form.setCategory(category);
		this.catDeleted = false;
		return "deleteCategory";
	}

	public void confirmDeletion() {
		CatSearchOptions options = new CatSearchOptions();
		options.setId(form.getCategory().getId());
		form.setCategory(controller.searchCats(options).get(0));

		FacesMessage message = new FacesMessage();
		try {
			controller.delete(form.getCategory());
			catDeleted = true;
			reset();

			message.setSummary("Category succesfully deleted");
			message.setSeverity(FacesMessage.SEVERITY_INFO);

		} catch (BusinessException e) {
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);

	}

}
