import java.util.*;
class Grade12V extends Grade12
{
	ClassName[]a = new ClassName[6];
	int[] classCode = new int[9];
	Period[] period = new Period[classCode.length];
	int english;
	{
		timeTable[4][4] = "체육"; timeTable[4][5] = "체육";
		
		period[0] = new Period(0,4);
		period[1] = new Period(2,4);	period[2] = new Period(2,5);
		period[3] = new Period(3,2);	period[4] = new Period(3,3);	period[5] = new Period(3,4);	period[6] = new Period(3,5);
		period[7] = new Period(4,6);	period[8] = new Period(4,7);

		a[0] = new ClassName(2, "최관영", "국어", 2);
		a[2] = new ClassName(1, "윤승길", "영어", 3);
		a[3] = new ClassName(1, "게리옥", "영어", 0);
		a[4] = new ClassName(1, "오병문", "한국사", 1);
		a[5] = new ClassName(1, "윤승길", "게리옥", "상담", 0);
	}

	public Grade12V(Teacher[] teacher, int english){
		this.teacher = teacher;
		this.english = english;
		switch (english){
			case 0: a[1] = new ClassName(2, "웨인", "영어", 3);
						break;
			case 1: a[1] = new ClassName(2, "메나드", "영어", 3);
						break;		
		}
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
		throw new Error("can't find teacher");
	}

	public void printClassCode(){
		for (int i=0;i<classCode.length ;i++ )
			System.out.print(classCode[i]);
		System.out.println();
	}
}
