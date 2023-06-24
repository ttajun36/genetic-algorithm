import java.util.ArrayList;
class Grade12M1 extends Grade12
{
	ClassName[] a = new ClassName[6];
	int[] classCode = new int[15];
	Period[] period = new Period[classCode.length];
	{
		timeTable[4][2] = "Ã¼À°"; timeTable[4][3] = "Ã¼À°";

		period[0] = new Period(0,4);	period[1] = new Period(0,5); period[2] = new Period(0,6);
		period[3] = new Period(1,4);	period[4] = new Period(1,5);
		period[5] = new Period(2,4);	period[6] = new Period(2,5);
		period[7] = new Period(3,2);	period[8] = new Period(3,3);	period[9] = new Period(3,4);	period[10] = new Period(3,5);
		period[11] = new Period(4,4);	period[12] = new Period(4,5);	period[13] = new Period(4,6);	period[14] = new Period(4,7);

		a[0] = new ClassName(4, "Áö±¤Çö", "±¹¾î", 2);
		a[1] = new ClassName(2, "¾¦", "¿µ¾î", 3);
		a[2] = new ClassName(2, "Á¤À¯°æ", "¿µ¾î", 3);
		a[3] = new ClassName(4, "ÇÏµ¿¿ì", "¼öÇÐ", 0);
		a[4] = new ClassName(1, "±èÅÂ¿Ï", "ÇÑ±¹»ç", 1);
		a[5] = new ClassName(1, "±è¹ÎÁÖ", "»ó´ã", 1);
	}

	public Grade12M1(Teacher[] teacher){
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
		throw new Error("can't find teacher");
	}

	public void printClassCode(){
		for (int i=0;i<classCode.length ;i++ )
			System.out.print(classCode[i]);
		System.out.println();
	}
}
