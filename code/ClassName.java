class ClassName 
{
	public int unit;
	public String[] teacherGroup;
	public String className;
	public int classLocation;						//0이면 다산, 1이면 충무, 2이면 민교, 3이면 영교

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
