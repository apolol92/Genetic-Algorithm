package genetic_algorithm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by apolol92 on 06.02.2016.
 * This class represents a genetic algorithm.
 */
public class GeneticAlgorithm {

    /**
     * Set the gen size of a chromosome
     * @param n size of gen
     */
    public static void setGenSize(int n) {
        Chromosome.genSize = n;
    }
    /**
     * @param nodes just nodes of a graph
     * @return roundtrip graph
     */
    public static ArrayList<Node> calculate(ArrayList<Node> nodes, int iterations, int populationSize) {
        ArrayList<Chromosome> chromosomes = new ArrayList<>();
        //Populate first time
        for(int i = 0; i < populationSize; i++) {
            chromosomes.add(new Chromosome());
        }
        for(int i = 0; i < iterations; i++){
            System.out.println("Geneartion "+ i +"+++++++++++++");
            chromosomes = getTop2(nodes,chromosomes);
            System.out.println("Best Chromosome of last generation:"+chromosomes.get(0).toString());
            System.out.println("Chromosomes amount after top2:"+chromosomes.size());
            chromosomes = repopulate(chromosomes, populationSize);
            System.out.println("Chromosomes amount after repopulation:"+chromosomes.size());
            System.out.println("Fitness: " + chromosomes.get(0).calculateTotalDistance(nodes));
            System.out.println("");
        }
        chromosomes = getTop2(nodes,chromosomes);
        return createRoundtrip(nodes,chromosomes.get(0));
    }

    /**
     * Get the best 2 chromosomes of the current population
     * @param nodes reference on the graph
     * @param chromosomes, current population
     * @return best 2 chromosomes
     */
    public static ArrayList<Chromosome> getTop2(ArrayList<Node> nodes, ArrayList<Chromosome> chromosomes) {
        ArrayList<Chromosome> copy = new ArrayList<>();
        for(int i = 0; i < chromosomes.size();i++) {
            copy.add(chromosomes.get(i).copy());
        }
        ArrayList<Chromosome> top2 = new ArrayList<Chromosome>();
        for(int i = 0; i < 2; i++) {
            Chromosome best = copy.get(0);
            int bestIndex = 0;
            for(int d = 0; d < copy.size(); d++) {
                if(copy.get(d).calculateTotalDistance(nodes)<best.calculateTotalDistance(nodes)){
                    best = copy.get(d).copy();
                    bestIndex = d;
                }
            }
            top2.add(best);
            copy.remove(bestIndex);
        }
        return top2;
    }

    /**
     * Repopulate the new generation
     * @param chromosomes
     * @param populationSize
     * @return repopulated generation
     */
    private static ArrayList<Chromosome> repopulate(ArrayList<Chromosome> chromosomes, int populationSize) {
        ArrayList<Chromosome> nPopulation = new ArrayList<>();
        nPopulation.add(chromosomes.get(0));
        nPopulation.add(chromosomes.get(1));

        for(int i = 0; i < populationSize-2; i++) {
            Chromosome child = crossover(chromosomes.get(0),chromosomes.get(1));
            child = mutate(child);
            nPopulation.add(child);
        }
        return nPopulation;

    }

    /**
     * Create a crossover with 2 chromosomes..
     * TODO: DO A REAL CROSSOVER.. CURRENTLY JUST USE CHROMOSOME0
     * @param chromosome0
     * @param chromosome1
     * @return
     */
    private static Chromosome crossover(Chromosome chromosome0, Chromosome chromosome1) {
        Chromosome child = new Chromosome();
        /**Random rnd = new Random();
        for (int i = 0; i < child.genSequence.length; i++)
        {

        }*/
        child = chromosome0.copy();
        return child.copy();
    }

    /**
     * Do a chromosome mutation
     * @param child
     * @return maybe a mutated chromosome
     */
    private static Chromosome mutate(Chromosome child) {
        Random rnd = new Random();
        for(int i = 0; i < child.genSequence.length; i++) {
            if (rnd.nextInt(10) <= 3)
            {
                //Mutation
                int rndPosition;
                int rndPosition2;
                do {
                    rndPosition = rnd.nextInt(child.genSequence.length);
                    rndPosition2 = rnd.nextInt(child.genSequence.length);
                }while(rndPosition==rndPosition2);
                int tmp = child.genSequence[rndPosition];
                child.genSequence[rndPosition] = child.genSequence[rndPosition2];
                child.genSequence[rndPosition2] = tmp;
            }
        }
        return child.copy();
    }
    //23.22713040353781
    //20.316108002332076
    //14.549776476484551
    //13.721349351738361

    /**
     * Create a roundtrip
     * @param nodes reference on graph
     * @param bestChromosome use the best calculated chromosome
     * @return list of nodes in the "roundtrip" order..
     */
    private static ArrayList<Node> createRoundtrip(ArrayList<Node> nodes, Chromosome bestChromosome){
        int lastNode = -1;
        ArrayList<Node> roundtrip = new ArrayList<>();
        for (int i = 0; i < Chromosome.genSize; i++)
        {
            for (int d = 0; d < Chromosome.genSize; d++)
            {
                if (bestChromosome.genSequence[d] == i)
                {
                    roundtrip.add(nodes.get(d));
                }
            }
        }
        return roundtrip;
    }
}
