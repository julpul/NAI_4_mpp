import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Layer {
    List<Perceptron> perceptrons = new ArrayList<Perceptron>();

    public Layer() {}
    public void AddPerceptron(Perceptron perceptron) {
        perceptrons.add(perceptron);
    }
    public List<Perceptron> GetPerceptrons() {
        return perceptrons;
    }

    public List<Double> layerCompute(List<Double> data){
        return perceptrons.stream().map(perceptron -> perceptron.compute(data)).collect(Collectors.toCollection(ArrayList::new));
    }
    public void layerUpdate(List<Double> data,List<Integer> decision){
        for (int i = 0; i < perceptrons.size(); i++) {
            perceptrons.get(i).updateWeights(data,decision.get(i));
        }
    }
    public void train(List<List<Double>> data, List<List<Integer>> decision, int epochs){
        for (int i = 0; i < epochs ; i++) {
            for (int j = 0; j < decision.size(); j++) {
                layerUpdate(data.get(j),decision.get(j));
            }
        }
    }
}
