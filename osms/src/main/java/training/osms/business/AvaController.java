package training.osms.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import training.framework.business.BusinessException;
import training.osms.persistence.AvaDao;

@Component
public class AvaController {

	private @Autowired
	AvaDao dao;

	public void setDao(AvaDao dao) {
		this.dao = dao;
	}

	@Transactional
	public void saveAvaliacao(Avaliacao avaliacao) {
		dao.insertEntity(avaliacao);
	}

	public Integer searchAvaliacaoCount(AvaSearchOptions options) {
		return dao.searchEntityCount(options);
	}

	public List<Avaliacao> searchAvaliacao(AvaSearchOptions options) {
		return dao.searchEntity(options);
	}

	@Transactional
	public void updateAvaliacao(Avaliacao avaliacao) {
		Avaliacao databaseAvaliacao = dao.searchOneEntity(avaliacao.getId());
		if (databaseAvaliacao == null) {
			dao.updateEntity(avaliacao);
		} else {
			if (avaliacao.getId().equals(databaseAvaliacao.getId())) {
				dao.updateEntity(avaliacao);
			} else {
				throw new BusinessException("There is a avaliacao named "
						+ avaliacao.getId() + " already");
			}
		}
	}

	@Transactional
	public void deleteAvaliacao(Avaliacao avaliacao) {
		dao.deleteEntity(avaliacao);
	}

}
