import java.util.Arrays;
import java.util.List;

public class Perceptron {
    double[] weights;
    double eta;
    double theta = 1;

    public Perceptron(int weightsSize, double eta) {
        this.weights = new double[weightsSize];
        this.eta = eta;
        generateWeights();
    }
    private void generateWeights() {
        for (int i = 0; i < weights.length; i++) {
            weights[i] = 2*Math.random()-1;
        }
    }
    public double compute_net(List<Double> input_data){
        double sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i]*input_data.get(i);
        }
        return sum-this.theta;
    }
    public double compute(List<Double> input_data){
        double net = compute_net(input_data);
        return 1/(1+Math.pow(Math.E, -net));
    }
    public double y_derivative(double y){
        return y -(1-y);
    }

    public void updateWeights(List<Double> input_data,int decision){
        double y = compute(input_data);
        for (int i = 0; i < weights.length; i++) {
            weights[i] = weights[i]+(decision-y)*y_derivative(y)*input_data.get(i)*eta;
        }
        updateTheta(y,decision);
    }
    public void updateTheta(double y,int decision){
        double y_derivative = y_derivative(y);
        this.theta = theta+(decision-y)*y_derivative*eta;
    }


    public void train(List<List<Double>> data, List<Integer> decisions,int iterations){
        for (int i = 0; i < iterations; i++) {
            for (int j = 0; j < decisions.size(); j++) {
                updateWeights(data.get(j),decisions.get(j));
            }
        }
    }


    @Override
    public String toString() {
        return "Perceptron{" +
                "weights=" + Arrays.toString(weights) +
                ", eta=" + eta +
                ", theta=" + theta +
                '}';
    }
}
