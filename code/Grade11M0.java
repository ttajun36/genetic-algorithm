import java.util.ArrayList;
class Grade11M0 extends Grade11 
{
	ClassName[] a = new ClassName[9];
	int[] classCode = new int[17];
	Period[] period = new Period[classCode.length];
	int pe, english, history;
	{
		timeTable[2][2] = "����/�̼�"; timeTable[2][3] = "����/�̼�";

		period[0] = new Period(0,4);	period[1] = new Period(0,5); period[2] = new Period(0,6);
		period[3] = new Period(1,2);	period[4] = new Period(1,3);	period[5] = new Period(1,4);	period[6] = new Period(1,5);
		period[7] = new Period(2,4);	period[8] = new Period(2,5);
		period[11] = new Period(4,2); period[12] = new Period(4,3); period[13] = new Period(4,4);	 period[14] = new Period(4,5); period[15] = new Period(4,6); period[16] = new Period(4,7);
		
		a[0] = new ClassName(3, "������", "����", 2);
		a[1] = new ClassName(1, "�̺���", "����", 2);
		a[2] = new ClassName(2, "������", "����", 3);

		a[4] = new ClassName(2, "�ڿ뼺", "���Ͽ� ����", 0);
		a[5] = new ClassName(2, "�赵��", "������", 0);
		a[6] = new ClassName(2, "������", "������", 0);

		a[8] = new ClassName(1, "�����", "�ϵ���", "���", 0);
	}

	public Grade11M0(Teacher[] teacher, int pe, int english, int history){
		this.teacher = teacher;
		this.pe = pe;
		switch (pe){
			case 0: timeTable[3][2] = "ü��"; timeTable[3][3] = "ü��";
						period[9] = new Period(3,4);	period[10] = new Period(3,5);
						break;
			case 1: timeTable[3][4] = "ü��"; timeTable[3][5] = "ü��";
						period[9] = new Period(3,2);	period[10] = new Period(3,3);
						break;
			default: throw new Error("Invalid pe number");
		}

		this.english = english;
		switch (english){
			case 0: a[3] = new ClassName(2, "����", "����", 3); 
						break;
			case 1:	a[3] = new ClassName(2, "�Ը���", "����", 3);
						break;
			case 2: a[3] = new ClassName(2, "������", "����", 3);
						break;
			default: throw new Error("Invalid english number");
		}

		this.history = history;
		switch (history){
			case 0: a[7] = new ClassName(2, "���¿�", "�ѱ���", 1); 
						break;
			case 1:	a[7] = new ClassName(2, "������", "�ѱ���", 1);
						break;
			default: throw new Error("Invalid history number");
		}
	}

	public void randomInitialize(){
		ArrayList<Integer> classCodeIndex = new ArrayList<Integer>();
		for (int i=0;i<classCode.length ;i++ )
			if (classCode[i]==0)
				classCodeIndex.add(i);

		if (classCodeIndex.size() == classCode.length)
			for (int i=0;i<a.length ;i++ )
				for (int j=0;j<a[i].unit ;j++ ){
					int randomIndex = (int)(Math.random()*classCodeIndex.size());
					classCode[classCodeIndex.get(randomIndex)] = i+1;
					classCodeIndex.remove(randomIndex);
				}
		else
			for (int i=0;i<a.length-1 ;i++ )
				for (int j=0;j<a[i].unit ;j++ ){
					int randomIndex = (int)(Math.random()*classCodeIndex.size());
					classCode[classCodeIndex.get(randomIndex)] = i+1;
					classCodeIndex.remove(randomIndex);
				}
	}

	public void randomInitialize(Grade friendClass){
		ArrayList<Integer> classCodeIndex = new ArrayList<Integer>();
		for (int i=0;i<classCode.length ;i++ )
			classCodeIndex.add(i);
		
		for (int i=0;i<a.length ;i++ )
			for (int j=0;j<a[i].unit ;j++ ){
				int randomIndex = (int)(Math.random()*classCodeIndex.size());
				classCode[classCodeIndex.get(randomIndex)] = i+1;
				if (i==8)
					friendClass.getClassCode()[classCodeIndex.get(randomIndex)] = i+1;
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
	
	public void makeTimeTable(){
		for (int i=0;i<classCode.length ;i++ )
		{
			timeTable[period[i].dow][period[i].period] = a[classCode[i] - 1].className + "(" + a[classCode[i] - 1].teacherGroup[0];
			for (int j=0;j<a[classCode[i] - 1].teacherGroup.length - 1 ;j++ )
				timeTable[period[i].dow][period[i].period] += ", " + a[classCode[i] - 1].teacherGroup[j+1];
			timeTable[period[i].dow][period[i].period] += ")";
		}
	}
}
