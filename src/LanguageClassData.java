import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class LanguageClassData {
    public enum Language{
        PL,FRE,DK
    }
    public List<Integer> numberOfLetters;
    public Language language;

    public LanguageClassData(ArrayList<Integer> list ,Language language){
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
        List<Integer> listOfCharacters = new ArrayList<>(Collections.nCopies(26,0));
        String[] file_Language_Arr = path.getFileName().toString().split("_");
        Language language = Language.valueOf(file_Language_Arr[file_Language_Arr.length-1]);

        Files.lines(path).forEach(line -> line.toLowerCase().chars().forEach(c -> {
            if (97<=c && c<=123){
                int current = listOfCharacters.get(c-97);
                listOfCharacters.set(c-97,current+1);
            }
        }));

        return new LanguageClassData((ArrayList<Integer>) listOfCharacters,language);
    }
}
