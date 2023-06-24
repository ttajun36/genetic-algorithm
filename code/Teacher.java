class Teacher 
{
	int[][] timeTable = new int [5][8];
	int countDouble=0;
	String name;

	public Teacher(String name){
		this.name = name;
	}

	public void countDouble(){
		for (int i=0;i<timeTable.length ;i++ )
			for (int j=0;j<timeTable[i].length ;j++ )
				if (timeTable[i][j]>1)
					countDouble += timeTable[i][j]-1;
	}
}
