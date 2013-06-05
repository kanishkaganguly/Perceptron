/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetworks;

import java.util.Scanner;

/**
 *
 * @author Nightstalker
 */
class Perceptron {

    double weights[];
    double c = 0.01; //Learning Constant

    static double rand(int Min, int Max) {
        return Min + (int) (Math.random() * ((Max - Min) + 1));
    }

    Perceptron(int n) {
        weights = new double[n];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = rand(-1, 1);
        }
    }

    int feedforward(double[] inputs) {
        double sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += inputs[i] * weights[i];
        }
        return activate(sum);
    }

    int activate(double sum) {
        if (sum > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    void train(double[] inputs, int desired) {
        int guess = feedforward(inputs);
        double error = desired - guess;
        for (int i = 0; i < weights.length; i++) {
            weights[i] += error * inputs[i] * c;
        }
    }
}

class Trainer {

    double inputs[];
    int answer;

    Trainer(double x, double y, int a) {
        inputs = new double[3];
        inputs[0] = x;
        inputs[1] = y;
        answer = a;
    }
}

public class neuralnetworks {

    static double f(double x) {
        return 2 * x + 1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("AIM :  CHECK WHETHER CO-ORDINATE (x,y) LIES ABOVE OR BELOW LINE y = 2x+1");
        System.out.println("ENTER NUMBER OF TRAINING POINTS (>1000, for a good training)");
        int training_points = in.nextInt();
        double x = 0, y = 0;
        Perceptron ptron;
        Trainer[] training = new Trainer[training_points];
        int count = 0;
        ptron = new Perceptron(3);
        for (int k = 0; k < training_points; k++) {
            for (int i = 0; i < training.length; i++) {
                x = Perceptron.rand(0, 20);
                y = Perceptron.rand(0, 20);
                int answer = 1;
                if (y < f(x)) {
                    answer = -1;
                }
                training[i] = new Trainer(x, y, answer);
            }

            ptron.train(training[count].inputs, training[count].answer);
            count = (count + 1) % training.length;

            int guess = ptron.feedforward(training[k].inputs);
            if (guess > 0) {
                System.out.println(x + "," + y + " IS " + "BELOW LINE");
            } else {
                System.out.println(x + "," + y + " IS " + "ABOVE LINE");
            }
        }
        System.out.println("TRAINING DONE");
        System.out.println("ENTER (x,y) CO-ORDINATE TO TEST LEARNING");
        double new_x = in.nextDouble();
        double new_y = in.nextDouble();
        double[] new_input = {x, y, 1};
        int guess = ptron.feedforward(new_input);
        if (guess > 0) {
            System.out.println(new_x + "," + new_y + " IS " + "BELOW LINE");
        } else {
            System.out.println(new_x + "," + new_y + " IS " + "ABOVE LINE");
        }
    }
}
