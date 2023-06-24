import java.io.*;
class Alphabet 
{
	public static void main(String[] args) throws IOException
	{
		String geneText = "Alphabet_all_genes.txt";
		String bestFitnessText = "Alphaebet_Generation_BestFitness.txt";
		BufferedWriter bwGene = new BufferedWriter(new FileWriter(new File(geneText),false));
		BufferedWriter bwBestFitness = new BufferedWriter(new FileWriter(new File(bestFitnessText), false));
	
		String a = "abcdefghijklmnopqrstuvwxyz";
		int [] answer = new int[26];
		for (int i=0;i<26 ;i++ )	answer[i] = 97+i;									//int로 내가 가까워져야 하는 답을 만들었다. 

		int population = 5000;																	//개체수는 5000개
		int geneLength = 26;																		//유전자의 길이는 26(a~ z)
		int generation = 1000;
		int mutationPPercentage = 7;															// x / 1000 이다. 10으로 잡으면 1퍼센트
		int [][] parent = new int [population][geneLength];
		int [] fitness = new int [population];
		
		random_Initialization(bwGene, bwBestFitness, answer, parent, fitness);
		for (int i=0;i<generation-1 ;i++ )
			make_Child(bwGene, bwBestFitness, parent, fitness, answer, mutationPPercentage);

		bwGene.close(); bwBestFitness.close();
	}

	public static void random_Initialization(BufferedWriter bw,  BufferedWriter bw2, int[] answer, int[][] parent, int[] fitness)	throws IOException
	{
		for (int i=0;i<parent.length ;i++ )
		{
			for (int j=0;j<parent[0].length ;j++ )
				parent[i][j] = (int)(26*Math.random()) + 97;
 
			fitness[i] = calculate_Fitness(answer, parent, i);
		}
		
		//write_Generation(bw, parent, fitness);
		write_Best_Fitness(bw2, parent, fitness);
	}

	public static int calculate_Fitness(int[] answer, int[][] parent, int i)
	{
		int fitness = parent[i].length*2 + 1;	
		for (int j=0;j<parent[i].length ;j++ )
		{
			if (parent[i][j] == answer[j])
				fitness += 3;
			else
				fitness -= 1;
		}
		return fitness;
	}
	
	public static void write_Generation(BufferedWriter bw, int[][] parent, int[] fitness) throws IOException
	{
		for (int i=0 ;i<parent.length  ;i++ )
		{
			for (int j=0;j<parent[0].length ;j++ )
			{
				bw.write((char)parent[i][j]);
			}
			bw.write(" " + fitness[i]);
			bw.newLine();
		}
	}

	public static void write_Best_Fitness(BufferedWriter bw2, int[][] parent, int[] fitness) throws IOException
	{
		int max = fitness[0];
		int maxIndex = 0;
		for (int i=0;i<fitness.length ;i++ )
		{
			if (fitness[i] > max)
			{
				max = fitness[i];
				maxIndex = i;
			}
		}

		for (int j=0;j<parent[0].length ;j++ )
		{
			bw2.write((char) parent[maxIndex][j]);
		}
		bw2.write(" " + fitness[maxIndex]);
		bw2.newLine();
	}

	public static int choose_Parent(int[] fitness)
	{
		int fitnessSum = 0;
		for (int i=0;i<fitness.length ;i++ )
			fitnessSum += fitness[i];
		
		int selectIndex = (int)(fitnessSum*Math.random());
		int fitnessIndex = 0;		int sum = fitness[0];
		while (selectIndex >= sum)
		{
			fitnessIndex++; sum+=fitness[fitnessIndex];
		}

		return fitnessIndex;
	}

	public static void make_Child(BufferedWriter bw, BufferedWriter bw2, int [][] parent, int[] fitness, int[] answer, int mutationPercentage) throws IOException
	{
		int[][] child = new int [parent.length][parent[0].length];
		int[] childFitness = new int [fitness.length];
		
		for (int i=0;i<child.length ;i++ )
		{
			int fatherIndex = choose_Parent(fitness); int motherIndex = choose_Parent(fitness);

			for (int j=0;j<child[0].length ;j++ )
			{
				if (j%2 == 0)
					child[i][j] = parent[fatherIndex][j];
				else
					child[i][j] = parent[motherIndex][j];
				mutation(mutationPercentage, child, i, j);
			}

			childFitness[i] = calculate_Fitness(answer, child, i);
		}
		
		for (int i=0;i<parent.length ;i++ )
			for (int j=0;j<parent[i].length ;j++ )
				parent[i][j] = child[i][j];
		for (int i=0;i<fitness.length ;i++ )
			fitness[i] = childFitness[i];

		//write_Generation(bw, parent, fitness);
		write_Best_Fitness(bw2, parent, fitness);
	}

	public static void mutation(int percentage, int[][] child, int i, int j)
	{
		int random = (int)(1000 * Math.random());
		if (random<percentage)
			child[i][j] = (int)(26*Math.random()) + 97;
	}
}
