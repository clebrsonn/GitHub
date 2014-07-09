package training.framework.business;

public abstract class AbstractEntitySearchOptions {

	private Integer startPosition;
	private Integer maxResults;

	public void setStartPosition(Integer startPosition) {
		this.startPosition = startPosition;
	}

	public Integer getStartPosition() {
		return startPosition;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public Integer getMaxResults() {
		return maxResults;
	}
}