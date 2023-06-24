//���߿� ��Ʈ�Ͻ� �ٲٸ� - �Ұ����� ������ ����ϴ°� ������� - �ְ��� ��Ʈ�Ͻ��� ���ؼ�. 
import java.io.*;
class MainProgram2//crossover����� �ٲ�
{
	public static void main(String[] args) throws IOException
	{
		String lastGenerationText = "LastGeneration.txt";								//���� �̸�
		//1. ��� ������: �����Ե��� �ð�
		String[] teacherGroup = {"����ȯ", "�̺���", "������", "�ְ���", "������",
													"��âȿ", "������", "�Ը���", "����", "������", "��", "���±�", "�޳���",
													"������", "���ؼ�", "�����", "�ϵ���", "�ڿ뼺", "�赵��", "������", "������",
													"�̵���", "���¿�", "������","�����"};
		Teacher[] teacher = new Teacher[teacherGroup.length];
		for (int i=0;i<teacher.length ;i++ )
			teacher[i] = new Teacher(teacherGroup[i]);
		{
			//������ �ð��� ���� ���� �̸� ���� �Ǵ� �����Ե��� ���� �ð�. 
		}

		//2. �����˰����� ����
		Grade[][] kmla = new Grade[3][10];
		makeClass(kmla, teacher);
		int population= 1000;																		//��ü��
		int geneLength=0;																			//�������� ����
		for (int i=0;i<3 ;i++ )
			for (int j=0;j<kmla[i].length ;j++ )
				if (kmla[i][j] != null)
					geneLength += kmla[i][j].getClassCode().length;				//����
		int generation = 10000;																		//�����
		int mutationppercentage=15;															//������ - 1�� 0.1�ۼ�Ʈ��.
		int[][] parent = new int[population][geneLength];						//�θ��� ������ �迭
		int[] fitness = new int[population];												//�θ��� ��Ʈ�Ͻ�

		System.out.print("1����: ");
		firstRandomInitialize(kmla, teacher, parent, fitness);
		
		int fatherLength = childHelper(kmla,1,0).length+childHelper(kmla,1,3).length+
										childHelper(kmla,1,2).length+childHelper(kmla,1,6).length+
										childHelper(kmla,1,8).length+childHelper(kmla,2,0).length+
										childHelper(kmla,2,4).length+childHelper(kmla,2,5).length+
										childHelper(kmla,2,8).length;
		int[] fatherGene = new int[fatherLength];
		makeFatherGene(kmla, fatherGene);	
		//*/
		for (int i=0;i<generation-1 ;i++ ){
			System.out.print(i+2 + "����: ");
			makingChild(parent, fitness, kmla, teacher, mutationppercentage, fatherGene);
			if ((i+2)%700 == 0){
				BufferedWriter bw = new BufferedWriter(new FileWriter(new File(lastGenerationText), false));
				writeGeneration(bw, i+2, parent);
				bw.flush();
				bw.close();
				System.out.println("here is point");
				secondRandomInitialize(kmla, teacher, parent, fitness);
			}
		}
		//*/
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

	public static void firstRandomInitialize(Grade[][] kmla, Teacher[] teacher, int[][] parent, int[] fitness){
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
				fitness[i] = calculateFitness(kmla, teacher);
			}
			printFitness(fitness);
	}

	public static void secondRandomInitialize(Grade[][] kmla, Teacher[] teacher, int[][] parent, int[] fitness){
			for (int i=0;i<1000 ;i++ ){
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
				fitness[i] = calculateFitness(kmla, teacher);
			}
			printFitness(fitness);
		}

	public static int printFitness(int[] fitness){
		int min=fitness[0], max = fitness[0];
			for (int i=0;i<fitness.length ;i++ ){
				if (fitness[i]<min)	
					min = fitness[i];
				if (fitness[i]>max)
					max = fitness[i];
			}
			System.out.println("��Ʈ�Ͻ� �ּҰ���: "+min+" ��Ʈ�Ͻ� �ִ밪��: "+max);
			return max;
	}

	public static int calculateFitness(Grade[][] kmla, Teacher[] teacher){		//�ϴ��� �׳� 440���� ��ġ�� �� *4 �ؼ� �����. 
		//������ ������ ��ġ�� ����ŭ
		int minusCount = 0;
		for (int i=0;i<teacher.length ;i++ )
			minusCount += teacher[i].countDouble;

		return 110 - minusCount;
	}

	public static int choosingParent(int[] fitness){
		int fitnessSum = 0;
		for (int i=0;i<fitness.length ;i++ )
			fitnessSum += fitness[i];
		
		int selectIndex = (int)(fitnessSum*Math.random()), fitnessIndex = 0, sum = fitness[0];
		while (selectIndex >= sum){
			fitnessIndex++; sum+=fitness[fitnessIndex];
		}

		return fitnessIndex;
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

	public static int makingChild(int[][] parent, int[] fitness, Grade[][] kmla, Teacher[] teacher, int mutationppercentage, int[] fatherGene){		//�ϴ��� 11, 12�г⸸
		int[][] child = new int[parent.length][parent[0].length];
		int[] childFitness = new int[fitness.length];
		
		for (int i=0;i<child.length ;i++ ){
			int fatherIndex = choosingParent(fitness), motherIndex = choosingParent(fitness);
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
			childFitness[i] = calculateFitness(kmla, teacher);
		}
		for (int i=0;i<parent.length ;i++ )
			for (int j=0;j<parent[i].length ;j++ )
				parent[i][j] = child[i][j];
		for (int i=0;i<fitness.length ;i++ )
			fitness[i] = childFitness[i];
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

	public static void printNotAllowed(Teacher[] teacher){
		int count = 0;
		for (int i=0;i<teacher.length ;i++ ) 
			count += teacher[i].countDouble;
		
		System.out.print("�Ұ����� ���� ����: "+count+ " ");
		/*/
		for (int i=0;i<teacher[1].timeTable.length ;i++ )
		{
			for (int j=0;j<teacher[1].timeTable[i].length ;j++ )
			{
				System.out.print(teacher[1].timeTable[i][j]+" ");
			}
			System.out.println();
		}
		//*/
	}
}
