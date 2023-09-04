public class Candidate {

	private String id;
	private String name;
	private Double finalScoreConhecimentosBasicos;
	private Integer correctAnswersConhecimentosEspecificos;
	private Double finalScoreConhecimentosEspecificos;
	private Integer finalScore;
	private Double appScore;

	public Candidate(String id, String name, Double finalScoreConhecimentosBasicos,
			Integer correctAnswersConhecimentosEspecificos, Double finalScoreConhecimentosEspecificos,
			Integer finalScore, Double appScore) {
		super();
		this.id = id;
		this.name = name;
		this.finalScoreConhecimentosBasicos = finalScoreConhecimentosBasicos;
		this.correctAnswersConhecimentosEspecificos = correctAnswersConhecimentosEspecificos;
		this.finalScoreConhecimentosEspecificos = finalScoreConhecimentosEspecificos;
		this.finalScore = finalScore;
		this.appScore = appScore;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getFinalScoreConhecimentosBasicos() {
		return finalScoreConhecimentosBasicos;
	}

	public void setFinalScoreConhecimentosBasicos(Double finalScoreConhecimentosBasicos) {
		this.finalScoreConhecimentosBasicos = finalScoreConhecimentosBasicos;
	}

	public Integer getCorrectAnswersConhecimentosEspecificos() {
		return correctAnswersConhecimentosEspecificos;
	}

	public void setCorrectAnswersConhecimentosEspecificos(Integer correctAnswersConhecimentosEspecificos) {
		this.correctAnswersConhecimentosEspecificos = correctAnswersConhecimentosEspecificos;
	}

	public Double getFinalScoreConhecimentosEspecificos() {
		return finalScoreConhecimentosEspecificos;
	}

	public void setFinalScoreConhecimentosEspecificos(Double finalScoreConhecimentosEspecificos) {
		this.finalScoreConhecimentosEspecificos = finalScoreConhecimentosEspecificos;
	}

	public Integer getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(Integer finalScore) {
		this.finalScore = finalScore;
	}

	public Double getAppScore() {
		return appScore;
	}

	public void setAppScore(Double appScore) {
		this.appScore = appScore;
	}

	public Double getScoreExame() {
		return finalScoreConhecimentosBasicos + 2 * finalScore + appScore;
	}

	@Override
	public String toString() {
		return String.format("%s - %s - %s - %s - %s - %s", id, name,finalScoreConhecimentosBasicos, finalScoreConhecimentosEspecificos, finalScore, getScoreExame().toString());
	}

}
