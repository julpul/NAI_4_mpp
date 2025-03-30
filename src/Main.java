import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class Main{
    public static List<LanguageClassData> collect_data(Path path) throws IOException {
        List<LanguageClassData> languageDataList = new ArrayList<>();


        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                languageDataList.add(LanguageClassData.readDataFromFile(file));
                return FileVisitResult.CONTINUE;
            }

        });
        return languageDataList;
    }

    public static void main(String[] args) throws IOException {
        LanguageClassData.readDataFromFile(Path.of("C:\\Users\\julia\\OneDrive\\Pulpit\\NAI4\\src\\Data\\callas_PL"));

        List<LanguageClassData> languageClassDataList = collect_data(Path.of("C:\\Users\\julia\\OneDrive\\Pulpit\\NAI4\\src\\Data"));
        List<List<Integer>> data = languageClassDataList.stream().map(e->e.numberOfLetters).collect(Collectors.toList());
        List<List<Integer>> decisions = (List<List<Integer>>) languageClassDataList.stream().map(e->{
            List<Integer> list = new ArrayList<>(Collections.nCopies(3, 0));
            list.set(e.language.ordinal(), 1);
            return list;
        }).collect(Collectors.toList());

        Layer layer = new Layer();
        layer.AddPerceptron(new Perceptron(26,1));
        layer.AddPerceptron(new Perceptron(26,1));
        layer.AddPerceptron(new Perceptron(26,1));









        /*System.out.println(languageClassDataList);
        System.out.println(data);
        System.out.println(decisions);*/
    }
}