package genetic_algorithm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by apolol92 on 06.02.2016.
 * A chromosome represents a individium in a population.
 * Each chromosome has got his own genSequence. A gens equence impacts the fitness of a chromosome.
 */
public class Chromosome {
    /**
     * Size of the gensequence. The size is equal the amount of nodes in a graph
     */
    public static int genSize;
    /**
     * Represents the gen sequence. A gen sequence describes a roundtrip.
     */
    public int[] genSequence;

    /**
     * Creates a chromosome
     */
    public Chromosome() {
        this.genSequence = new int[genSize];
        for(int i = 0; i < this.genSequence.length; i++) {
            this.genSequence[i] = i;
        }
    }

    /**
     * Creates a children
     * @return a children
     */
    public Chromosome crossover() {
        //TODO: MAYBE ADD ANOTHER CROSSOVER METHOD
        Chromosome child = this.copy();
        return child;
    }

    /**
     * Calculates the total distance of the roundtrip.
     * @param nodes of the graph is needed
     * @return total distance
     */
    public double calculateTotalDistance(ArrayList<Node> nodes) {
        int lastNode = 0;
        double distance = 0;

        for (int i = 0; i < genSize; i++)
        {
            for (int d = 0; d < genSize; d++)
            {
                if(i==0 && genSequence[d]==i) {
                    lastNode = d;
                }
                if (genSequence[d] == i)
                {
                    distance += nodes.get(lastNode).distanceTo(nodes.get(d));
                    lastNode = d;
                }
            }
        }
        return distance;
    }

    /**
     * Copy this chromosome
     * @return copy of chromosome
     */
    public Chromosome copy()
    {
        Chromosome copy = new Chromosome();
        for (int i = 0; i < this.genSequence.length; i++)
        {
            copy.genSequence[i] = this.genSequence[i];
        }
        return copy;
    }

    /**
     * gen sequence to string
     * @return gen sequence as string
     */
    @Override
    public String toString() {
        String str = "";
        for(int i = 0; i < this.genSequence.length; i++) {
            str+=this.genSequence[i];
        }
        return str;
    }
}
