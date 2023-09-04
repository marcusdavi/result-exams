
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class ResultApp {

	private static final String PATH = System.getProperty("user.dir") + "\\src\\";

	public static void main(String[] args) {
		try {
			String fileAmpla = PATH + "ampla.txt";
			String filePCD = PATH + "pcd.txt";
			String fileCotas = PATH + "cotas.txt";

			String fileAmplaResult = PATH + "ampla-result.txt";
			String filePCDResult = PATH + "pcd-result.txt";
			String fileCotasResult = PATH + "cotas-result.txt";

			List<Candidate> candidatesAmpla = buildCandidatesResult(fileAmpla);
			List<Candidate> candidatesPCD = buildCandidatesResult(filePCD);
			List<Candidate> candidatesCota = buildCandidatesResult(fileCotas);

			processResult(candidatesAmpla, candidatesPCD, candidatesCota);

			createfileResults(candidatesAmpla, fileAmplaResult);
			createfileResults(candidatesPCD, filePCDResult);
			createfileResults(candidatesCota, fileCotasResult);
			
			System.out.println("Arquivos gerados com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error Processing File.");
		}
	}

	private static void createfileResults(List<Candidate> candidates, String filePath) throws IOException {

		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
			int position = 1;
			for (Candidate candidate : candidates) {
				writeLine(bufferedWriter, candidate.toString(), position);
				position++;
			}
		}
	}

	private static void processResult(List<Candidate> candidatesAmpla, List<Candidate> candidatesPCD,
			List<Candidate> candidatesCota) {

		Comparator<Candidate> comparator = Comparator.comparing(Candidate::getAppScore, Comparator.reverseOrder())
				.thenComparing(Candidate::getScoreExame, Comparator.reverseOrder())
				.thenComparing(Candidate::getFinalScoreConhecimentosEspecificos, Comparator.reverseOrder())
				.thenComparing(Candidate::getCorrectAnswersConhecimentosEspecificos, Comparator.reverseOrder())
				.thenComparing(Candidate::getFinalScoreConhecimentosBasicos, Comparator.reverseOrder());

		Collections.sort(candidatesAmpla, comparator);
		Collections.sort(candidatesPCD, comparator);
		Collections.sort(candidatesCota, comparator);
	}

	private static void writeLine(BufferedWriter bufferedWriter, String content, int position) {

		try {
			bufferedWriter.write(position + "º - " + content);
			bufferedWriter.newLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private static List<Candidate> buildCandidatesResult(String filePath) throws FileNotFoundException, IOException {

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
			List<Candidate> candidates = new ArrayList<>();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String[] partsLine = line.split(",");
				candidates.add(buildCandidate(partsLine));

			}
			return candidates;
		}

	}

	private static Candidate buildCandidate(String[] partsLine) {
		String id = partsLine[0];
		String name = partsLine[1].trim();
		Double finalScoreConhecimentosBasicos = getDoubleValue(partsLine[12], name);
		Double finalScoreConhecimentosEspecificos = getDoubleValue(partsLine[14], name);
		Integer correctAnswersConhecimentosEspecificos = getIntegerValue(partsLine[15], name);
		Integer finalScore = getIntegerValue(partsLine[16], name);
		Double appScore = 0.00;

		return new Candidate(id, name, finalScoreConhecimentosBasicos, correctAnswersConhecimentosEspecificos,
				finalScoreConhecimentosEspecificos, finalScore, appScore);
	}

	public static Double getDoubleValue(String valor, String nome) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
		DecimalFormat decimalFormat = new DecimalFormat("0.00", symbols);

		try {
			return decimalFormat.parse(valor.replace(" ", "")).doubleValue();
		} catch (ParseException e) {
			System.out.println(String.format("Erro ao converter %d do candidato: %s", valor, nome));
			return null;
		}
	}

	public static Integer getIntegerValue(String valor, String nome) {
		try {
			return Integer.parseInt(valor.replace(" ", "").replace(".", ""));
		} catch (NumberFormatException e) {
			System.out.println(String.format("Erro ao converter %d do candidato: %s", valor, nome));
			return null;
		}
	}

}
