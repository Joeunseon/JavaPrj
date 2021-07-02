public class ReserveTest
{
	public static void main(String[] args)
	{
		 // 1관 10x5 배열 생성
		char[][] mr1 = new char[10][12];
		// 2관 10x5 배열 생성
		char[][] mr2 = new char[4][8];

		int n = 0;
		// 1관 좌석 입력
		for (int i=0; i<mr1.length; i++)
		{
			for (int j=0; j<mr1[0].length; j++)
			{
				if (n==1 || n==4 || n==7)
				{
					mr1[i][j] = '▩';
					
					n++;

					if (n==8)
						n=0;
				}
				else 
				{
					mr1[i][j] = '□';
					n++;
				}	
			}
		}
		

		System.out.println("    -----------Screen-----------\n\n");

		for (int i=0; i<mr1.length; i++)
		{
			char a = (char)(65+i);
				System.out.print(a + " | ");

			for (int j=0; j<mr1[0].length; j++)
			{
				System.out.print(mr1[i][j]);
				
				if (j==2 || j==8)
					System.out.print("  ");
				if (j==(mr1[0].length-1))
					System.out.println();
			}

			System.out.println("    1 2 3   4 5 6 7 8 9  10 11 12");

			if (i==4)
				System.out.println();
		}


		n = 0;
		// 3관 좌석 입력
		for (int i=0; i<mr2.length; i++)
		{
			for (int j=0; j<mr2[0].length; j++)
			{
				if (n==1 || n==4 || n==7)
				{
					mr2[i][j] = '▩';
					n++;
					//System.out.println((int)mr2[i][j]);
					if (n==8)
						n=0;
				}
				else 
				{
					mr2[i][j] = '□';
					n++;
					//System.out.println((int)mr2[i][j]);
				}	
			}
		}

		System.out.println("    ------Screen------\n\n");

		for (int i=0; i<mr2.length; i++)
		{
			char a = (char)(65+i);
				System.out.print(a + " | ");

			for (int j=0; j<mr2[0].length; j++)
			{
				System.out.print(mr1[i][j]);
				
				if (j==3)
					System.out.print("  ");
				
			}

			System.out.println("\n    1 2 3 4   5 6 7 8 ");

			if (i==4)
				System.out.println();
		}
		
	}
}