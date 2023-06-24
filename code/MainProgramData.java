//나중에 피트니스 바꾸면 - 불가능한 수업수 출력하는거 해줘야함 - 최고의 피트니스에 대해서. 
import java.io.*;
class MainProgramData
{
	public static void main(String[] args) throws IOException
	{
		String lastGenerationText = "LastGeneration.txt";
		String myName = args[0]; // 이름 이런식으로 써야함.
		String maxFitnessText = "C:\\Users\\user\\Desktop\\data모음집\\"+myName+".txt";
		//1. 상단 고정부: 선생님들의 시간
		String[] teacherGroup = {"김정환", "이병하", "지광현", "최관영", "권택일",
													"우창효", "문단일", "게리옥", "웨인", "정유경", "쑥", "윤승길", "메나드",
													"김정수", "이준석", "정기원", "하동우", "박용성", "김도영", "김중태", "전현구",
													"이두현", "김태완", "오병문","김민주"};
		Teacher[] teacher = new Teacher[teacherGroup.length];
		for (int i=0;i<teacher.length ;i++ )
			teacher[i] = new Teacher(teacherGroup[i]);
		{
			//수업반 시간이 들어가기 전에 미리 들어가게 되는 선생님들의 차는 시간. 
		}

		//2. 유전알고리즘의 시작
		Grade[][] kmla = new Grade[3][10];
		makeClass(kmla, teacher);
		int population= 10000;																		//개체수
		int geneLength=0;																				//유전자의 길이
		for (int i=0;i<3 ;i++ )
			for (int j=0;j<kmla[i].length ;j++ )
				if (kmla[i][j] != null)
					geneLength += kmla[i][j].getClassCode().length;				//유전
		int generation = 20000;																		//세대수
		int mutationppercentage=21;															//변이율 - 1이 0.1퍼센트임.
		int[][] parent = new int[population][geneLength];						//부모의 유전자 배열 
		Fitness[] fitness = new Fitness[population];													//부모의 피트니스
		for (int i=0;i<fitness.length ;i++ )
			fitness[i] = new Fitness();
		int[] maxFitness = new int[generation+1];
		maxFitness[0] = 1;
		
		System.out.print("1세대: ");
		firstRandomInitialize(kmla, teacher, parent, fitness, maxFitness);
		
		int fatherLength = childHelper(kmla,1,0).length+childHelper(kmla,1,3).length+
										childHelper(kmla,1,2).length+childHelper(kmla,1,6).length+
										childHelper(kmla,1,8).length+childHelper(kmla,2,0).length+
										childHelper(kmla,2,4).length+childHelper(kmla,2,5).length+
										childHelper(kmla,2,8).length;
		int[] fatherGene = new int[fatherLength];
		makeFatherGene(kmla, fatherGene);	
	
		for (int i=0;i<generation-1 ;i++ ){
			System.out.print(i+2 + "세대: ");
			if (5>3)
				maxFitness[maxFitness[0]] = makingChild(parent, fitness, kmla, teacher, mutationppercentage, fatherGene,2);			//마지막 int 0: 룰렛휠 / 1: 우선순위기반 / 2: 토너먼트
			maxFitness[0]++;
			if ((i+2)%500 == 0 ){
				BufferedWriter bw = new BufferedWriter(new FileWriter(new File(lastGenerationText), false));
				writeGeneration(bw, i+2, parent);
				bw.flush();
				bw.close();
				System.out.println("here is point");

				BufferedWriter bw2 = new BufferedWriter(new FileWriter(new File(maxFitnessText), false));
				for (int j=1;j<maxFitness[0] ;j++ )
					bw2.write(" "+maxFitness[j]);
				bw2.flush();
				bw2.close();
			}
		}
	}

	public static void makeFatherGene(Grade[][] kmla, int[] fatherGene){
		int fatherCount = 0;
		for (int i=0;i<childHelper(kmla,1,0).length ;i++ ){
			fatherGene[fatherCount] = childHelper(kmla,1,0)[i]; fatherCount++;
		}
		for (int i=0;i<childHelper(kmla,1,3).length ;i++ ){
			fatherGene[fatherCount] = childHelper(kmla,1,3)[i]; fatherCount++;
		}
		for (int i=0;i<childHelper(kmla,1,2).length ;i++ ){
			fatherGene[fatherCount] = childHelper(kmla,1,2)[i]; fatherCount++;
		}
		for (int i=0;i<childHelper(kmla,1,6).length ;i++ ){
			fatherGene[fatherCount] = childHelper(kmla,1,6)[i]; fatherCount++;
		}
		for (int i=0;i<childHelper(kmla,1,8).length ;i++ ){
			fatherGene[fatherCount] = childHelper(kmla,1,8)[i]; fatherCount++;
		}
		for (int i=0;i<childHelper(kmla,2,0).length ;i++ ){
			fatherGene[fatherCount] = childHelper(kmla,2,0)[i]; fatherCount++;
		}
		for (int i=0;i<childHelper(kmla,2,4).length ;i++ ){
			fatherGene[fatherCount] = childHelper(kmla,2,5)[i]; fatherCount++;
		}
		for (int i=0;i<childHelper(kmla,2,5).length ;i++ ){
			fatherGene[fatherCount] = childHelper(kmla,2,5)[i]; fatherCount++;
		}
		for (int i=0;i<childHelper(kmla,2,8).length ;i++ ){
			fatherGene[fatherCount] = childHelper(kmla,2,8)[i]; fatherCount++;
		}
	}

	public static void writeGeneration(BufferedWriter bw, int generation, int[][] parent){
		try{
			bw.write(""+generation);
			bw.newLine();
			for (int i=0;i<parent.length ;i++ ){
				for (int j=0;j<parent[i].length ;j++ )
					bw.write(parent[i][j]+"");
				bw.newLine();
			}
		}
		catch (Exception IOException){}
	}

	public static void makeClass(Grade[][] kmla, Teacher[] teacher){
		/*/
		Grade10 m110 = new Grade10();
		Grade10 m210 = new Grade10();
		Grade10 m310 = new Grade10();
		Grade10 m410 = new Grade10();
		Grade10 m510 = new Grade10();
		Grade10 m610 = new Grade10();
		Grade10 m710 = new Grade10();
		Grade10 m810 = new Grade10();
		Grade10 m910 = new Grade10();
		Grade10 m1010 = new Grade10();
		//*/

		kmla[1][0] = new Grade11M1(teacher);
		kmla[1][1] = new Grade11M1(teacher);
		kmla[1][2] = new Grade11M0(teacher,1,0,0);
		kmla[1][3] = new Grade11M0(teacher,1,0,0);
		kmla[1][4] = new Grade11M0(teacher,1,1,0);
		kmla[1][5] = new Grade11M0(teacher,1,1,1);
		kmla[1][6] = new Grade11M0(teacher,0,2,1);
		kmla[1][7] = new Grade11V(teacher,0,0);
		kmla[1][8] = new Grade11V(teacher,0,1);
		kmla[1][9] = new Grade11V(teacher,1,2);

		kmla[2][0] = new Grade12M1(teacher);
		kmla[2][1] = new Grade12M1(teacher);
		kmla[2][2] = new Grade12M0(teacher,0,0);
		kmla[2][3] = new Grade12M0(teacher,0,2);
		kmla[2][4] = new Grade12M0(teacher,0,2);
		kmla[2][5] = new Grade12M0(teacher,1,3);
		kmla[2][6] = new Grade12M0(teacher,1,1);
		kmla[2][7] = new Grade12M0(teacher,1,1);
		kmla[2][8] = new Grade12V(teacher,0);
		kmla[2][9] = new Grade12V(teacher,1);
	}

	public static void clearTeacherTimeTable(Teacher[] teacher){
		for (int k=0;k<teacher.length ;k++ ){
			teacher[k].countDouble = 0;
			for (int i=0;i<teacher[k].timeTable.length ;i++ )
				for (int j=0;j<teacher[k].timeTable[i].length ;j++ )
					teacher[k].timeTable[i][j] = 0;	
		}
	}

	public static void randomInitialize(Grade[][] kmla, Teacher[] teacher){
		for (int i=0;i<kmla.length ;i++ )
			for (int j=0;j<kmla[i].length ;j++ )
				if (kmla[i][j]!=null)
					for (int k=0;k<kmla[i][j].getClassCode().length ;k++ )
						kmla[i][j].getClassCode()[k]=0;
		clearTeacherTimeTable(teacher);

		kmla[1][0].randomInitialize();
		kmla[1][1].randomInitialize();
		kmla[1][2].randomInitialize(kmla[1][6]);
		kmla[1][3].randomInitialize();
		kmla[1][4].randomInitialize(kmla[1][5]);
		kmla[1][5].randomInitialize();
		kmla[1][6].randomInitialize();
		kmla[1][7].randomInitialize();
		kmla[1][8].randomInitialize();
		kmla[1][9].randomInitialize();

		kmla[2][0].randomInitialize();
		kmla[2][1].randomInitialize();
		kmla[2][2].randomInitialize(kmla[2][3]);
		kmla[2][3].randomInitialize();
		kmla[2][4].randomInitialize(kmla[2][5]);
		kmla[2][5].randomInitialize();
		kmla[2][6].randomInitialize(kmla[2][7]);
		kmla[2][7].randomInitialize();
		kmla[2][8].randomInitialize();
		kmla[2][9].randomInitialize();
	}

	public static void firstRandomInitialize(Grade[][] kmla, Teacher[] teacher, int[][] parent, Fitness[] fitness, int[] maxFitness){
			for (int i=0;i<parent.length ;i++ ){
				randomInitialize(kmla, teacher);
				int count=0;
				for (int j=0;j<kmla.length ;j++ )
					for (int k=0;k<kmla[j].length ;k++ )
						if (kmla[j][k]!=null){
							kmla[j][k].teacherTimeTable();
							for (int l=0;l<kmla[j][k].getClassCode().length ;l++ ){
								parent[i][count] = kmla[j][k].getClassCode()[l];
								count++;
							}
						}
				for (int j=0;j<teacher.length ;j++ )
					teacher[j].countDouble();
				fitness[i].fitness = calculateFitness(kmla, teacher);
			}
			maxFitness[maxFitness[0]] = printFitness(fitness);
			maxFitness[0]++;
	}

	public static void secondRandomInitialize(Grade[][] kmla, Teacher[] teacher, int[][] parent, Fitness[] fitness){
			for (int i=0;i<0.4*fitness.length ;i++ ){
				randomInitialize(kmla, teacher);
				int count=0;
				for (int j=0;j<kmla.length ;j++ )
					for (int k=0;k<kmla[j].length ;k++ )
						if (kmla[j][k]!=null){
							kmla[j][k].teacherTimeTable();
							for (int l=0;l<kmla[j][k].getClassCode().length ;l++ ){
								parent[i][count] = kmla[j][k].getClassCode()[l];
								count++;
							}
						}
				for (int j=0;j<teacher.length ;j++ )
					teacher[j].countDouble();
				fitness[i].fitness = calculateFitness(kmla, teacher);
			}
			printFitness(fitness);
		}

	public static int printFitness(Fitness[] fitness){
		int min=fitness[0].fitness, max = fitness[0].fitness;
			for (int i=0;i<fitness.length ;i++ ){
				if (fitness[i].fitness<min)	
					min = fitness[i].fitness;
				if (fitness[i].fitness>max)
					max = fitness[i].fitness;
			}
			System.out.println("피트니스 최소값은: "+min+" 피트니스 최대값은: "+max);
			return max;
	}

	public static int calculateFitness(Grade[][] kmla, Teacher[] teacher){
		int minusCount = 0;
		for (int i=0;i<teacher.length ;i++ )
			minusCount += teacher[i].countDouble;

		return 110 - minusCount;
	}
	
	public static int choosingParent(Fitness[] fitness){
		int fitnessSum = 0;
		for (int i=0;i<fitness.length ;i++ )
			fitnessSum += fitness[i].fitness;
		
		int selectIndex = (int)(fitnessSum*Math.random()), fitnessIndex = 0, sum = fitness[0].fitness;
		while (selectIndex >= sum){
			fitnessIndex++; sum+=fitness[fitnessIndex].fitness;
		}

		return fitnessIndex;
	}
	
	public static int calculatePercentage(int[] sortedFitness, int value){
		int a1=2, d=2;
		int minIndex=0, maxIndex=0;
		while (sortedFitness[minIndex]!=value)
			minIndex++;
		maxIndex=minIndex;
		while (maxIndex<sortedFitness.length&&sortedFitness[maxIndex]==value)
			maxIndex++;
		maxIndex--;
		return a1+(d*(minIndex+maxIndex))/2;
	}

	public static int choosingParent2(Fitness[] fitness){
		int sortedFitness[] = new int[fitness.length];
		for (int i=0;i<fitness.length ;i++ )
			sortedFitness[i]=fitness[i].fitness;
		java.util.Arrays.sort(sortedFitness);
		for (int i=0;i<fitness.length ;i++ )
			fitness[i].percentage=calculatePercentage(sortedFitness, fitness[i].fitness);

		int fitnessSum = 0;
		for (int i=0;i<fitness.length ;i++ )
			fitnessSum += fitness[i].percentage;
		int selectIndex = (int)(fitnessSum*Math.random()), fitnessIndex = 0, sum = fitness[0].percentage;
		while (selectIndex >= sum){
			fitnessIndex++; sum+=fitness[fitnessIndex].percentage;
		}

		return fitnessIndex;
	}

	public static int choosingParent3(Fitness[] fitness){
		int firstIndex = (int)(fitness.length*Math.random());
		int secondIndex = (int)(fitness.length*Math.random());
		int percentage = 90;
		int randomNumber = (int)(100*Math.random());

		if (fitness[firstIndex].fitness>fitness[secondIndex].fitness){
			if (randomNumber<percentage)
				return firstIndex;
			else
				return secondIndex;
		}
		else{
			if(randomNumber<percentage)
				return secondIndex;
			else
				return firstIndex;
		}
	}

	public static int[] childHelper(Grade[][] kmla, int i, int j){ 
		int[] returnArray=new int[kmla[i][j].getClassCode().length];
		int count=0;
		switch (i){
			case 1: for (int k=0;k<j ;k++ ) count+=kmla[1][k].getClassCode().length;
						 for (int k=0;k<returnArray.length ;k++ ){
							 returnArray[k] = count;
							 count++;
						 }
						 break;
			case 2: count+=158;
						for (int k=0;k<j ;k++ ) count+=kmla[2][k].getClassCode().length;
						 for (int k=0;k<returnArray.length ;k++ ){
							 returnArray[k] = count;
							 count++;
						 }
						 break;
			default: throw new Error("invalid in childHelper");
		}
		return returnArray;
	}

	public static int makingChild(int[][] parent, Fitness[] fitness, Grade[][] kmla, Teacher[] teacher, int mutationppercentage, int[] fatherGene, int childType){
		int[][] child = new int[parent.length][parent[0].length];
		Fitness[] childFitness = new Fitness[fitness.length];
		for (int i=0;i<childFitness.length ;i++ )
			childFitness[i] = new Fitness();

		for (int i=0;i<child.length ;i++ ){
			int fatherIndex=0, motherIndex=0;
			switch (childType){
				case 0: fatherIndex = choosingParent(fitness); motherIndex = choosingParent(fitness);
							  break;
				case 1: fatherIndex = choosingParent2(fitness); motherIndex = choosingParent2(fitness);
							  break;
				case 2: fatherIndex = choosingParent3(fitness); motherIndex = choosingParent3(fitness);
							 break;
			}
			
			int fatherCount = 0;
			for (int j=0;j<child[i].length ;j++ )
				if (j==fatherGene[fatherCount]){
					child[i][j] = parent[fatherIndex][j]; fatherCount++;
				}
				else
					child[i][j] = parent[motherIndex][j];
			int count=0;
			for (int j=0;j<kmla.length ;j++ )
				for (int k=0;k<kmla[j].length ;k++ )
					if (kmla[j][k] != null){
						for (int l=0;l<kmla[j][k].getClassCode().length ;l++ )	{
							kmla[j][k].getClassCode()[l] =child[i][count];
							count++;
						}
					}
			mutation(kmla, mutationppercentage);
			count =0;
			for (int j=0;j<kmla.length ;j++ )
				for (int k=0;k<kmla[j].length ;k++ )
					if (kmla[j][k] != null){
						for (int l=0;l<kmla[j][k].getClassCode().length ;l++ )	{
							child[i][count] = kmla[j][k].getClassCode()[l];
							count++;
						}
					}
			clearTeacherTimeTable(teacher);
			for (int j=0;j<kmla.length ;j++ )
				for (int k=0;k<kmla[j].length ;k++ )
					if (kmla[j][k] != null){
						kmla[j][k].teacherTimeTable();
					}
			for (int j=0;j<teacher.length ;j++ )
					teacher[j].countDouble();
			childFitness[i].fitness = calculateFitness(kmla, teacher);
		}
		for (int i=0;i<parent.length ;i++ )
			for (int j=0;j<parent[i].length ;j++ )
				parent[i][j] = child[i][j];
		for (int i=0;i<fitness.length ;i++ )
			fitness[i].fitness = childFitness[i].fitness;
		return printFitness(fitness);
	}

	public static void mutation(Grade[][] kmla, int percentage)
	{
		int random;
		for (int i=0;i<kmla[1].length ;i++ ){
			random = (int)(1000 * Math.random());
			if (random<percentage){
				switch (i){
					case 2:	clearClassCode(kmla[1][2].getClassCode()); clearClassCode(kmla[1][6].getClassCode());
								kmla[1][2].randomInitialize(kmla[1][6]);
								kmla[1][6].randomInitialize();
								break;
					case 4:	clearClassCode(kmla[1][4].getClassCode()); clearClassCode(kmla[1][5].getClassCode());
								kmla[1][4].randomInitialize(kmla[1][5]);
								kmla[1][5].randomInitialize();
								break;
					case 5:	break;
					case 6:	break;
					default: clearClassCode(kmla[1][i].getClassCode());
								  kmla[1][i].randomInitialize();
				}
			}
		}
		for (int i=0;i<kmla[2].length ;i++ ){
			random = (int)(1000 * Math.random());
			if (random<percentage){
				switch (i){
					case 2:	clearClassCode(kmla[2][2].getClassCode()); clearClassCode(kmla[2][3].getClassCode());
								kmla[2][2].randomInitialize(kmla[1][3]);
								kmla[2][3].randomInitialize();
								break;
					case 3:	break;
					case 4:	clearClassCode(kmla[2][4].getClassCode()); clearClassCode(kmla[2][5].getClassCode());
								kmla[2][4].randomInitialize(kmla[2][5]);
								kmla[2][5].randomInitialize();
								break;
					case 5:	break;
					case 6:	clearClassCode(kmla[2][6].getClassCode()); clearClassCode(kmla[2][7].getClassCode());
								kmla[2][6].randomInitialize(kmla[2][7]);
								kmla[2][7].randomInitialize();
								break;
					case 7: break;
					default: clearClassCode(kmla[2][i].getClassCode());
								  kmla[2][i].randomInitialize();
				}
			}
		}
	}
	
	public static void clearClassCode(int[] array){
		for (int i=0;i<array.length ;i++ )
			array[i] = 0;
	}
}
