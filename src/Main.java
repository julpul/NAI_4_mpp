import javax.swing.plaf.synth.SynthOptionPaneUI;
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
        //LanguageClassData.readDataFromFile(Path.of("C:\\Users\\julia\\OneDrive\\Pulpit\\NAI4\\src\\Data\\callas_PL"));

        List<LanguageClassData> languageClassDataList = collect_data(Path.of("C:\\Users\\dell\\pjatk\\NAI_4_mpp\\src\\Data"));
        List<List<Double>> data = languageClassDataList.stream().map(e->e.numberOfLetters).collect(Collectors.toList());
        List<List<Integer>> decisions = languageClassDataList.stream().map(e->{
            List<Integer> list = new ArrayList<>(Collections.nCopies(3, 0));
            list.set(e.language.ordinal(), 1);
            return list;
        }).collect(Collectors.toList());

        Layer layer = new Layer();
        layer.AddPerceptron(new Perceptron(26,0.1));
        layer.AddPerceptron(new Perceptron(26,0.1));
        layer.AddPerceptron(new Perceptron(26,0.1));

        layer.train(data,decisions,50_000);


        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        LanguageClassData classData = LanguageClassData.readDataFromFile(Paths.get(line));
        List<Double> doubleList = classData.numberOfLetters;
        List<Integer> decisionslista = new ArrayList<>(Collections.nCopies(3, 0));
        decisionslista.set(classData.language.ordinal(), 1);
        System.out.println(doubleList);
        System.out.println(decisionslista);
        System.out.println(layer.layerCompute(doubleList));


    }
}