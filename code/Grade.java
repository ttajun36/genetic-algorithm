abstract class Grade 
{
	String[][] timeTable = new String[5][8];
	Teacher[] teacher;
	{
		for (int i=0;i<timeTable.length ;i++ )
			for (int j=0;j<timeTable[i].length ;j++ )
				timeTable[i][j] = "공강";		

		timeTable[0][0] = "애국조회";
		timeTable[0][7] = "학급회의";
		
		timeTable[1][6] = "I 모듈";
		timeTable[1][7] = "I 모듈";

		timeTable[3][6] = "K 모듈";
		timeTable[3][7] = "K 모듈";
	}

	public void printTimeTable(){
		System.out.println();
		for (int i=0;i<timeTable.length ;i++ ){
			for (int j=0;j<timeTable[i].length ;j++ )
				System.out.print(String.format("%-18s", timeTable[i][j]));
			System.out.println();
		}
		System.out.println();
	}

	abstract public void teacherTimeTable();
	abstract public Teacher findTeacher(String teacherName);
	abstract public void randomInitialize();
	abstract public int[] getClassCode();
	public void randomInitialize(Grade friendClass){}
	public void printClassCode(){}
	public void makeTimeTable(){}
}
