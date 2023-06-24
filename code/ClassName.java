class ClassName 
{
	public int unit;
	public String[] teacherGroup;
	public String className;
	public int classLocation;						//0�̸� �ٻ�, 1�̸� �湫, 2�̸� �α�, 3�̸� ����

	public ClassName(int unit, String teacher, String className, int classLocation){
		this.unit = unit;
		teacherGroup = new String[1];
		teacherGroup[0] = teacher;
		this.className = className;
		this.classLocation = classLocation;
	}
	
	public ClassName(int unit, String teacher1, String teacher2, String className, int classLocation){
		this.unit = unit;
		teacherGroup = new String[2];
		teacherGroup[0] = teacher1;
		teacherGroup[1] = teacher2;
		this.className = className;
		this.classLocation = classLocation;
	}

	public ClassName(int unit, String teacher1, String teacher2, String teacher3, String className, int classLocation){
		this.unit = unit;
		teacherGroup = new String[3];
		teacherGroup[0] = teacher1;
		teacherGroup[1] = teacher2;
		teacherGroup[2] = teacher3;
		this.className = className;
		this.classLocation = classLocation;
	}
}
