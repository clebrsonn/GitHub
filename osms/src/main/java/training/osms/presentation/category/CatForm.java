package training.osms.presentation.category;

import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import training.osms.business.category.CatController;
import training.osms.business.category.CatSearchOptions;
import training.osms.business.category.Category;

public class CatForm {

	private Category category;
	private List<Category> categories;


	public CatForm() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ApplicationContext applicationContext = FacesContextUtils
				.getWebApplicationContext(facesContext);

		CatController controller = applicationContext
				.getBean(CatController.class);
		categories = controller.searchCats(new CatSearchOptions());


		category = new Category();
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void setCatId(Integer catId) {
		if (catId == null) {
			category.setCatPai(null);
		} else {
			for (Category catPai : categories) {
				if (catPai.getId().equals(catId)) {
					category.setCatPai(catPai);
					break;
				}
			}
		}
	}

	public Integer getCatId() {
		Category catPai = category.getCatPai();
		if (catPai == null) {
			return null;
		} else {
			return catPai.getId();
		}
	}

	

//	public void setCatPai(Integer catPai) {
//		for (Category cats : categories) {
//			if (!cats.getCatPai().getId().equals(null)
//					&& !cats.getCatPai().getId().equals(catPai)) {
//				this.catPai.remove(cats);
//
//			}
//
//		}
//
//	}


}
