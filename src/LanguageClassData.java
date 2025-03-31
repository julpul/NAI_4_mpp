import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class LanguageClassData {
    public enum Language{
        PL,FRE,DK
    }
    public List<Double> numberOfLetters;
    public Language language;

    public LanguageClassData(List<Double> list , Language language){
        this.numberOfLetters = list;
        this.language = language;
    }

    @Override
    public String toString() {
        return "LanguageClassData{" +
                "numberOfLetters=" + numberOfLetters +
                ", language=" + language +
                '}';
    }

    public static LanguageClassData readDataFromFile(Path path) throws IOException {
        List<Double> listOfCharacters = new ArrayList<>(Collections.nCopies(26,0.0));
        String[] file_Language_Arr = path.getFileName().toString().split("_");
        Language language = Language.valueOf(file_Language_Arr[file_Language_Arr.length-1]);

        Files.lines(path).forEach(line -> line.toLowerCase().chars().forEach(c -> {
            if (97<=c && c<123){
                double current = listOfCharacters.get(c-97);
                listOfCharacters.set(c-97,current+1);
            }
        }));
        double total = listOfCharacters.stream().mapToDouble(Double::doubleValue).sum();
        for (int i = 0; i < listOfCharacters.size(); i++) {
            listOfCharacters.set(i, listOfCharacters.get(i) / total);
        }
        return new LanguageClassData(listOfCharacters,language);
    }
}
