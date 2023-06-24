import java.util.ArrayList;
class	 Grade11M1 extends Grade11 
{
	ClassName[] a = new ClassName[6];
	int[] classCode = new int[17];
	Period[] period = new Period[classCode.length];
	{
		timeTable[4][2] = "체육"; timeTable[4][3] = "체육";

		period[0] = new Period(0,4);	period[1] = new Period(0,5); period[2] = new Period(0,6);
		period[3] = new Period(1,2);	period[4] = new Period(1,3);	period[5] = new Period(1,4);	period[6] = new Period(1,5);
		period[7] = new Period(2,2);	period[8] = new Period(2,3);
		period[9] = new Period(3,4);	period[10] = new Period(3,5);
		period[11] = new Period(4,2); period[12] = new Period(4,3); period[13] = new Period(4,4);	 period[14] = new Period(4,5); period[15] = new Period(4,6); period[16] = new Period(4,7);
	
		a[0] = new ClassName(4, "지광현", "국어", 2);
		a[1] = new ClassName(2, "우창효", "영어", 3);
		a[2] = new ClassName(2, "메나드", "영어", 3);
		a[3] = new ClassName(4, "김중태", "수학", 0);
		a[4] = new ClassName(2, "김태완", "한국사", 1);
		a[5] = new ClassName(1, "김민주", "상담", 1);
	}

	public Grade11M1(Teacher[] teacher){
		this.teacher = teacher;
	}

	public void randomInitialize(){
		ArrayList<Integer> classCodeIndex = new ArrayList<Integer>();
		for (int i=0;i<classCode.length ;i++ )
			classCodeIndex.add(i);
		
		for (int i=0;i<a.length ;i++ )
			for (int j=0;j<a[i].unit ;j++ ){
				int randomIndex = (int)(Math.random()*classCodeIndex.size());
				classCode[classCodeIndex.get(randomIndex)] = i+1;
				classCodeIndex.remove(randomIndex);
			}
	}

	public int[] getClassCode(){
		return classCode;
	}
	
	public void teacherTimeTable(){
		for (int i=0;i<classCode.length ;i++ )
			if (classCode[i]!=0)
				for (int j=0;j<a[classCode[i]-1].teacherGroup.length ;j++ )
					findTeacher(a[classCode[i]-1].teacherGroup[j]).timeTable[period[i].dow][period[i].period]++;
	}

	public Teacher findTeacher(String teacherName){
		for (int i=0;i<teacher.length ;i++ )
				if (teacher[i].name.equals(teacherName))
					return teacher[i];
		throw new Error("can't find teacher"+teacherName);
	}

	public void printClassCode(){
		for (int i=0;i<classCode.length ;i++ )
			System.out.print(classCode[i]);
		System.out.println();
	}
}
