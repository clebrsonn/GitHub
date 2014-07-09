package training.osms.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.osms.business.CatController;
import training.osms.business.CatSearchOptions;
import training.osms.business.Category;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ShowCategory {

	private @Autowired
	CatController controller;

	private CatForm form;
	private int catPaiId;
	private List<Category> subCat;

//	private List<Category> subAdd = new ArrayList<>();

	public ShowCategory() {
		form = new CatForm();
	}

	public CatForm getForm() {
		return form;
	}

	public void setForm(CatForm form) {
		this.form = form;
	}

	public void setCatPaiId(int catPaiId) {
		this.catPaiId = catPaiId;
	}

	public int getCatPaiId() {
		return catPaiId;
	}

	public void setSubCat(List<Category> subCat) {
		this.subCat = subCat;
	}

	public List<Category> getSubCat() {

		CatSearchOptions options = new CatSearchOptions();
		options.setCatPai(catPaiId);

		subCat = controller.searchCats(options);
		
		return subCat;
	}

//	public void setSubAdd(List<Category> subAdd) {
//		this.subAdd = subAdd;
//	}
//
//	public List<Category> getSubAdd() {
//		return subAdd;
//	}

//	public List<Category> getSub(Integer subAll) {
//		if (subAll != null) {
//			Category catAux = controller.searchOneCat(subAll);
//			if (catAux != null) {
//				subAdd.add(catAux);
//				if (catAux.getCatPai().getId() != null) {
//
//					getSub(catAux.getCatPai().getId());
//				}
//			}
//		}
//		return subAdd;
//	}
}
