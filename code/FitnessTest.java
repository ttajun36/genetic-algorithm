import java.io.*;
class FitnessTest 
{
	public static void main(String[] args) throws IOException
	{
		String[] teacherGroup = {"김정환", "이병하", "지광현", "최관영", "권택일",
													"우창효", "문단일", "게리옥", "웨인", "정유경", "쑥", "윤승길", "메나드",
													"김정수", "이준석", "정기원", "하동우", "박용성", "김도영", "김중태", "전현구",
													"이두현", "김태완", "오병문","김민주"};
		Teacher[] teacher = new Teacher[teacherGroup.length];
		for (int i=0;i<teacher.length ;i++ )
			teacher[i] = new Teacher(teacherGroup[i]);

		Grade[][] kmla = new Grade[3][10];
		makeClass(kmla, teacher);
		
		/*/여따가 for문으로  텍스트 이용해서 채워주라 요런 느낌으로.

		for (int i=0;i<kmla.length ;i++ )
			for (int j=0;j<kmla[i].length ;j++ )
				if (kmla[i][j]!= null)
					for (int k=0;k<kmla[i][j].getClassCode().length ;k++ )
						kmla[i][j].getClassCode().length[k] = 텍스트의 다음 int값
		//*/
		
		FileReader fi = new FileReader("현클래스코드.txt");
		for (int i = 0; i < kmla.length; i++) {
			for (int j = 0; j < kmla[i].length; j++) {
				if (kmla[i][j] != null) {
					for (int k = 0; k < kmla[i][j].getClassCode().length; k++) 
						kmla[i][j].getClassCode()[k] = fi.read()-48;
				}
			}
		}

		for (int j=0;j<kmla.length ;j++ )
			for (int k=0;k<kmla[j].length ;k++ )
				if (kmla[j][k]!=null)
					kmla[j][k].teacherTimeTable();
		for (int j=0;j<teacher.length ;j++ )
			teacher[j].countDouble();
		System.out.println(calculateFitness(kmla, teacher));
		
		//49614651387237851
		

		kmla[1][2].getClassCode()[0]=4;
		kmla[1][2].getClassCode()[1]=9;
		kmla[1][2].getClassCode()[2]=6;
		kmla[1][2].getClassCode()[3]=1;
		kmla[1][2].getClassCode()[4]=4;
		kmla[1][2].getClassCode()[5]=6;
		kmla[1][2].getClassCode()[6]=5;
		kmla[1][2].getClassCode()[7]=1;
		kmla[1][2].getClassCode()[8]=3;
		kmla[1][2].getClassCode()[9]=8;
		kmla[1][2].getClassCode()[10]=7;
		kmla[1][2].getClassCode()[11]=2;
		kmla[1][2].getClassCode()[12]=3;
		kmla[1][2].getClassCode()[13]=7;
		kmla[1][2].getClassCode()[14]=8;
		kmla[1][2].getClassCode()[15]=5;
		kmla[1][2].getClassCode()[16]=1;
		kmla[1][2].makeTimeTable();
		kmla[1][2].printTimeTable();

//46815393874762151
		kmla[1][2].getClassCode()[0]=4;
		kmla[1][2].getClassCode()[1]=6;
		kmla[1][2].getClassCode()[2]=8;
		kmla[1][2].getClassCode()[3]=1;
		kmla[1][2].getClassCode()[4]=5;
		kmla[1][2].getClassCode()[5]=3;
		kmla[1][2].getClassCode()[6]=9;
		kmla[1][2].getClassCode()[7]=3;
		kmla[1][2].getClassCode()[8]=8;
		kmla[1][2].getClassCode()[9]=7;
		kmla[1][2].getClassCode()[10]=4;
		kmla[1][2].getClassCode()[11]=7;
		kmla[1][2].getClassCode()[12]=6;
		kmla[1][2].getClassCode()[13]=2;
		kmla[1][2].getClassCode()[14]=1;
		kmla[1][2].getClassCode()[15]=5;
		kmla[1][2].getClassCode()[16]=1;
		kmla[1][2].makeTimeTable();
		kmla[1][2].printTimeTable();
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

	public static int calculateFitness(Grade[][] kmla, Teacher[] teacher){
		int minusCount = 0;
		for (int i=0;i<teacher.length ;i++ ){
			minusCount += teacher[i].countDouble;
			if(teacher[i].countDouble>0)
				System.out.println(teacher[i].name+teacher[i].countDouble);
		}

		return 110 - minusCount;
	}
}
