
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.*;

//
//====================================================================================================

/*
  Title  : ReserveTikets
  Function : ��ȭ ���� ���
  author   : ������
*/

//====================================================================================================

class ReserveTikets extends Record
{ 
  public static ArrayList<String> arrLast = new ArrayList<String>();		// ��ȭ ��� ���͸��� ���� �ڷᱸ��
  public static ArrayList<Integer> arrLast2 = new ArrayList<Integer>();		// �� ��� ���͸��� ���� �ڷᱸ��
  
  static int temp;
  static int n;
  //static char a;
  
  public static String title = "";		// ������ ���� ����
  public static Integer serialM=0;		// ���õ� ��ȭ���� �ø���
  public static Integer serial=0;		// ���õ� ���� �ø���
  public static String time = "";	    // ���õ� ���� �ð�
  public static String datec = "";		// ���õ� ���� ����
  public static char[][] seat;			// ���õ� ��ȭ �¼� ����Ʈ
  public static String[] seatc;			// ���õ� �¼� ��ȣ
  public static int inwonS;				// ���õ� ���� �ο�
  public static int inwonC;				// ���õ� û�ҳ� �ο�
  
  //public static ReserveTiketsVO rtv = new ReserveTiketsVO();
  
  public static void tiketsMenuDisp() throws IOException, InterruptedException
  {
	Scanner sc = new Scanner(System.in);
	
	sel = 0;

	System.out.println("\n\n\t��ȭ ����                          00. �ʱ�ȭ��");
	System.out.println("\t===============================================");
	System.out.println("\t     *��ȭ ��� ���͸� ����� �Է����ּ���*  ");
	System.out.println("\t-----------------------------------------------");
	System.out.println("\t 1. �����ټ� | 2. ������");
	System.out.println("\t===============================================");
	
	n = 0;
	do
	{ 
	  if (n>0)
	  {
		  System.out.println("\t>>��ȿ���� ���� �޴��Դϴ�. �ٽ� �������ּ���.");
		  System.out.println();
	  }
	  while (true)
	  {
		  try
		  {
			System.out.print("\t>> �Է� : ");
			answer = sc.next();
			
			if (answer.equals("00"))
				return;

			sel = Integer.parseInt(answer);

			n++;

			break;
		  }
		  catch (NumberFormatException e)
		  {
			  sc = new Scanner(System.in);
			  System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
		  }
	  }
	  
	}
	while (sel<1 || sel>2);
	
	temp = sel;
	printMlist(temp);
  }

  //�󿵸���Ʈ ��� �޼ҵ�
  public static void printMlist(int temp) throws IOException, InterruptedException
  {
    Scanner sc = new Scanner(System.in);
	
	AdminMovie am = new AdminMovie();
	
	System.out.println("\n");
	
	arrLast.clear();

	Set<String> key = am.ht.keySet();
	Iterator<String> hit = key.iterator();
	
	while (hit.hasNext())
	{
		String keys = (String)hit.next();
		arrLast.add(((MovieVO)am.ht.get(keys)).getmTitle());
	}
	
	// �����ټ� ���ý�
	if (temp==1)
	{
		//System.out.println(arrLast);
		
		// �������� ����
		Collections.sort(arrLast);
		//System.out.println(arrLast.get(0));
		
		n = 1;

		for (String title : arrLast)
		{
			if (n==1)
			{
				System.out.println("\t��ȭ ����Ʈ                              00.�ʱ�ȭ�� 01.����ȭ��");
				System.out.println("\t================================================================");
			}
			else 
				System.out.println();

			System.out.println("\t"+n+". "+title+" ("+((MovieVO)am.ht.get(title)).getmAge()+")   "+ "|���� "+((MovieVO)am.ht.get(title)).getmStar()+"|");
			System.out.println("\t"+((MovieVO)am.ht.get(title)).getmStory());
			
			n++;
		}

		System.out.println("\t================================================================");

	}// end �����ټ� ���� if��
	// ���� ��
	else if (temp==2)
	{
		String tempStr = "";
		boolean flag;
		n = 0;
		
		/*
		for (int i=0; i<arrLast.size()-n; i++)
		{
			System.out.println("\t"+((MovieVO)am.ht.get(arrLast.get(i))).getmStar());
		}
		*/
		do
		{
			n++;
			
			flag = false;

			for (int i=0; i<arrLast.size()-n; i++)
			{
				if (((MovieVO)am.ht.get(arrLast.get(i))).getmStar()<((MovieVO)am.ht.get(arrLast.get(i+1))).getmStar())
				{
					tempStr = arrLast.get(i);
					arrLast.set(i, arrLast.get(i+1));
					arrLast.set((i+1), tempStr);

					flag = true;
				}
				
			}
		}
		while (flag);
		
		n = 1;
		for (String title : arrLast)
		{
			if (n==1)
			{
				System.out.println("\t��ȭ ����Ʈ                              00.�ʱ�ȭ�� 01.����ȭ��");
				System.out.println("\t================================================================");
			}
			else 
				System.out.println();

			System.out.println("\t"+n+". "+title+" ("+((MovieVO)am.ht.get(title)).getmAge()+")   "+ "|���� "+((MovieVO)am.ht.get(title)).getmStar()+"|");
			System.out.println("\t"+((MovieVO)am.ht.get(title)).getmStory());
			
			n++;
		}
		System.out.println("\t================================================================");
	}// end ���� �� if�� 
	
	n = 0;
	do
	{ 
	  if (n>0)
	  {
		  System.out.println("\t>>��ȿ���� ���� �޴��Դϴ�. �ٽ� �������ּ���.");
		  System.out.println();
	  }
	  while (true)
	  {
		  try
		  {
			System.out.print("\t>> ��ȭ ���� : ");
			answer = sc.next();
			
			if (answer.equals("00"))
				return;

			if (answer.equals("01"))
			{
				tiketsMenuDisp();
				return;
			}
			
			sel = Integer.parseInt(answer);

			n++;

			break;
		  }
		  catch (NumberFormatException e)
		  {
			  sc = new Scanner(System.in);
			  System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
		  }
	  }
	  
	}
	while (sel<1 || sel>arrLast.size());
	
	title = arrLast.get(sel-1);
	
	// 19�� �̻� ��ȭ���
	if (((MovieVO)am.ht.get(title)).getmAge()==19)
	{
		checkAge();
	}
	
	pinrtDate();
  }//end printMlist()
  
  // û�� ��ȭ ���ý� �ȳ� ��Ʈ
  public static void checkAge() throws IOException, InterruptedException
  {
	 sc = new Scanner(System.in);
	 
	 System.out.println("\n");
     System.out.println("\t================================================================");
	 System.out.println("\t       �����Ͻ� ��ȭ�� 'û�ҳ� ���� �Ұ�' �������");
	 System.out.println("\t       ���� ���� �� ���������� ����˴ϴ�.");
	 System.out.println("\t       �������� �Ұ��� ���Ű� ���� �� �� �ֽ��ϴ�."); 
	 System.out.println("\t================================================================");
	 System.out.println("\t              1. ���� ����   2. ���� ȭ��");
	 
     n = 0;
	do
	{ 
	  if (n>0)
	  {
		  System.out.println("\t>>��ȿ���� ���� �޴��Դϴ�. �ٽ� �������ּ���.");
		  System.out.println();
	  }
	  while (true)
	  {
		  try
		  {
			System.out.print("\t>> ����(1, 2) : ");
			sel = sc.nextInt();
			
			if (sel==2)
			{
				printMlist(temp);
				return;
			}
			n++;

			break;
		  }
		  catch (InputMismatchException e)
		  {
			  sc = new Scanner(System.in);
			  System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
		  }
	  }
	  
	}
	while (sel!=1);
  }

  // ������ ����
  public static void pinrtDate() throws IOException, InterruptedException
  {
	 Scanner sc = new Scanner(System.in);
	 Calendar cal = Calendar.getInstance();
	 
	 // ��¥ �޾ƿ���
	 Date today = cal.getTime();					
	 cal.add(Calendar.DAY_OF_WEEK, 1);
	 Date tomorrow = cal.getTime();
	 cal.add(Calendar.DAY_OF_WEEK, 1);
	 Date thedayAfter = cal.getTime();
	 
	 SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
	 
	 System.out.println("\n\n");
	 System.out.println("\t�� ����                                00.�ʱ�ȭ�� 01.����ȭ��");
	 System.out.println("\t================================================================");
	 System.out.println("\t���õ� ��ȭ : " + title );
	 System.out.println();
	 // �� ���� ����
	 n = 0;
	 do
	 {
		 if (n>0)
			 System.out.println("\t>> ��ȿ���� ���� �޴��Դϴ�. �ٽ� �������ּ���."); 

		 while (true)
		 {
			 try
			 {
				System.out.println("\t 1. "+(dformat.format(today)));
				System.out.println("\t 2. "+(dformat.format(tomorrow)));
				System.out.println("\t 3. "+(dformat.format(thedayAfter)));
				System.out.print("\t>> ������ ���� : ");
				answer = sc.next();
			
				if (answer.equals("00"))
					return;

				if (answer.equals("01"))
				{	
					printMlist(temp);
					return;
				}

				sel = Integer.parseInt(answer);

				break;
			 }
			 catch (NumberFormatException e)
			 {
				sc = new Scanner(System.in);
				System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
			 }
		 }
		 
		 n++;
	 }
	 while (sel>3 || sel<1);

	 serialM = sel;
     
     switch (serialM)
     {
     case 1: datec = dformat.format(today); break;
	 case 2: datec = dformat.format(tomorrow); break;
	 case 3: datec = dformat.format(thedayAfter); break;
     }

	 printMtime();
  }
    //�󿵽ð� ��� �޼ҵ� 
    public static void printMtime() throws IOException, InterruptedException
    { 
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n\n\n");
		System.out.println("\t��ȭ �� ����Ʈ                         00.�ʱ�ȭ�� 01.����ȭ��");
		System.out.println("\t================================================================");
		//System.out.println("\t                       " + "[" + title + "]");
		System.out.println("\t���õ� ��ȭ : " + title + " ("+datec+")" );
		//System.out.println(title);
		AdminMovie am = new AdminMovie();
		
		arrLast2.clear();

		Set<Integer> keySet = am.ht2.keySet();
		Iterator<Integer> iterKey = keySet.iterator(); 
		
		
		while (iterKey.hasNext())
		{
			Integer keys = iterKey.next();
			Integer seK = (keys/100);
			//System.out.println(((ScreenVO)am.ht2.get(keys)).getmTitle());
			
			// ���� ��ȭ ���� �󿵸���Ʈ �ֱ�
			if (title.equals(((ScreenVO)am.ht2.get(keys)).getmTitle()))
			{
				if (serialM == seK)
				{
					arrLast2.add(keys);
				}
			}
		}
		
		Collections.sort(arrLast2);
		//System.out.println(arrLast2);
		
		Integer dr = 0;
		boolean flag;
		n=0;
		do
		{
			n++;
			
			flag = false;

			for (int i=0; i<arrLast2.size()-n; i++)
			{
				// ���� ��¥, ���� �󿵰��̸� 
				if ( (arrLast2.get(i)/10)==(arrLast2.get(i+1)/10) ) 
				{
					if(Integer.parseInt(((ScreenVO)am.ht2.get(arrLast2.get(i))).getmSh())>Integer.parseInt(((ScreenVO)am.ht2.get(arrLast2.get(i+1))).getmSh()))
					{
						dr = arrLast2.get(i);
						arrLast2.set(i, arrLast2.get(i+1));
						arrLast2.set((i+1), dr);

						flag = true;
					}
				}
			}
		}
		while (flag);
		
		//System.out.println(arrLast2);
		n=1;
		int s1 = 0;
		int s2 = 0;
		int s3 = 0;
		int z;		// ���ŵ� �¼�
		int t;		// �� �¼�
		for (int i=0; i<arrLast2.size(); i++)
		{
			z = 0;
			t = 0;
			if (((arrLast2.get(i)/10)%10)==1)
			{
				if (s1==0)
					System.out.println("\n\t"+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmRoom());	
				
				s1++;
				
				// �󿵽ð� ���
				System.out.print("\t"+n+". "+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmSh()+":"+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmSm()
					+" ~ "+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmEh()+":"+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmEm());
				
				// �˼� ���� ���
				char[][] seat = ((ScreenVO)am.ht2.get(arrLast2.get(i))).getmSeat();

				for (int a=0; a<seat.length; a++)
				{
					for (int b=0; b<seat[0].length; b++)
					{
						if ((int)seat[a][b] == 9633)	// 9641 '��' // 9633 '��'
						{
							z++;
						}
						
						t++;
					}
				}
				System.out.println(" ("+z+"/"+t+")");
			}
			else if (((arrLast2.get(i)/10)%10)==2)
			{
				
				if (s2==0)
					System.out.println("\n\t"+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmRoom());
				
				s2++;
				
				// �󿵽ð� ���
				System.out.print("\t"+n+". "+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmSh()+":"+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmSm()
					+" ~ "+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmEh()+":"+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmEm());
				
				// �˼� ���� ���
				char[][] seat = ((ScreenVO)am.ht2.get(arrLast2.get(i))).getmSeat();

				for (int a=0; a<seat.length; a++)
				{
					for (int b=0; b<seat[0].length; b++)
					{
						if ((int)seat[a][b] == 9641)	// 9641 '��' // 9633 '��'
						{
							z++;
						}
						
						t++;
					}
				}
				System.out.println(" ("+z+"/"+t+")");
			}
			else if (((arrLast2.get(i)/10)%10)==3)
			{
				if (s3==0)
					System.out.println("\n\t"+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmRoom());
				
				s3++;
				
				// �󿵽ð� ���
				System.out.print("\t"+n+". "+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmSh()+":"+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmSm()
					+" ~ "+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmEh()+":"+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmEm());
				
				// �˼� ���� ���
				char[][] seat = ((ScreenVO)am.ht2.get(arrLast2.get(i))).getmSeat();

				for (int a=0; a<seat.length; a++)
				{
					for (int b=0; b<seat[0].length; b++)
					{
						if ((int)seat[a][b] == 9633)	// 9641 '��' // 9633 '��'
						{
							z++;
						}
						
						t++;
					}
				}
				System.out.println(" ("+z+"/"+t+")");
			}

			n++;
		}

		if (n==1)
		{
			System.out.println("\t>> �� ����Ʈ�� �����ϴ�.");
			System.out.println("\t================================================================");
			pinrtDate();
			return;
		}
		System.out.println("\t================================================================");
		
		n = 0;
		do
		{ 
		  if (n>0)
		  {
			  System.out.println("\t>> ��ȿ���� ���� �޴��Դϴ�. �ٽ� �������ּ���.");
			  System.out.println();
		  }
		  while (true)
		  {
			  try
			  {
				System.out.print("\t>> �󿵽ð� ���� : ");
				answer = sc.next();
				
				if (answer.equals("00"))
					return;

				if (answer.equals("01"))
				{
					pinrtDate();
					return;
				}
				
				sel = Integer.parseInt(answer);

				n++;

				break;
			  }
			  catch (NumberFormatException e)
			  {
				  sc = new Scanner(System.in);
				  System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
			  }
		  }
		  
		}
		while (sel<1 || sel>arrLast2.size()); 

		serial = arrLast2.get(sel-1);

		printMal();
		
    }//end printMtime()


  /////////////////////////////////////////////////////////////////////////////////////////////////////////////[���¼�����]


    // �Ÿ��α� �ȳ����� ��� �޼ҵ�
    public static void printMal() throws IOException, InterruptedException
    {
      System.out.println("\n");
      System.out.println("\t================================================================");
	  System.out.println("\tCRG�� ��ȸ�� �Ÿ��α⸦ �ؼ��մϴ�.");
      System.out.println("\t��ȸ�� �Ÿ��α� �¼�(��)�� ����ֽñ� �ٶ��ϴ�.");
	  System.out.println("\t================================================================");

	  printSeat();
    }//end printMal()

   // ������ �󿵰� �¼� ���
   public static void printSeat() throws IOException, InterruptedException
   {
	   sc = new Scanner(System.in);
	   AdminMovie am = new AdminMovie();

	   seat = ((ScreenVO)am.ht2.get(serial)).getmSeat();
	   
	   time = ((ScreenVO)am.ht2.get(serial)).getmSh()+":"+((ScreenVO)am.ht2.get(serial)).getmSm()
		   						+" ~ "+ ((ScreenVO)am.ht2.get(serial)).getmEh()+":"+((ScreenVO)am.ht2.get(serial)).getmEm();
	   //datec = ((ScreenVO)am.ht2.get(serial)).getmDate();
	   System.out.println("\n\n");
	   System.out.println("\t�¼� ����                                00.�ʱ�ȭ�� 01.����ȭ��"); 
	   System.out.println("\t================================================================");
	   /*System.out.println("\t"+((ScreenVO)am.ht2.get(serial)).getmTitle()+" ("+((ScreenVO)am.ht2.get(serial)).getmDate()
								+" "+((ScreenVO)am.ht2.get(serial)).getmSh()+":"+((ScreenVO)am.ht2.get(serial)).getmSm()
		   						+" ~ "+ ((ScreenVO)am.ht2.get(serial)).getmEh()+":"+((ScreenVO)am.ht2.get(serial)).getmEm()+")\n");*/
	   System.out.println("\t"+title+" ("+datec+" "+time+")\n");
	   if ( (serial/10)%10 == 1 || (serial/10)%10 == 2)
	   {
		   System.out.println("\t\t    -----------Screen-----------\n\n");

			for (int i=0; i<seat.length; i++)
			{
				char a = (char)(65+i);
					System.out.print("\t\t"+a + " | ");

				for (int j=0; j<seat[0].length; j++)
				{
					System.out.print(seat[i][j]);
					
					if (j==2 || j==8)
						System.out.print("  ");
					if (j==(seat[0].length-1))
						System.out.println();
				}

				System.out.println("\t\t    1 2 3   4 5 6 7 8 9  10 11 12");

				if (i==4)
					System.out.println();
			}
	   }
	   else if ((serial/10)%10 == 3)
	   {
		   System.out.println("\t    ------Screen------\n\n");

			for (int i=0; i<seat.length; i++)
			{
				char a = (char)(65+i);
					System.out.print("\t"+a + " | ");

				for (int j=0; j<seat[0].length; j++)
				{
					System.out.print(seat[i][j]);
					
					if (j==3)
						System.out.print("  ");
					
				}

				System.out.println("\n\t    1 2 3 4   5 6 7 8 ");

				if (i==4)
					System.out.println();
			}
	   }
	   
	   inwonsu();
   }

   public static void inwonsu() throws IOException, InterruptedException
   {
	   sc = new Scanner(System.in);
	   AdminMovie am = new AdminMovie();

	   do
	   {
		   inwonC = 0;
		   inwonS = 0;
		   n = 0;
		   System.out.println();
		   do
		   { 
			  if (n>0)
			  {
				  System.out.println("\t>> 5�� �̻� ���� ������ ���Ͽ� 4�� �̻� ���Ű� �Ұ����մϴ�.");
				  System.out.println();
			  }
			  while (true)
			  {
				  try
				  {
					System.out.print("\t>> ���� �ο� ����(0~4) : ");
					answer = sc.next();
					
					if (answer.equals("00"))
						return;

					if (answer.equals("01"))
					{
						printMtime();
						return;
					}
					
					inwonS = Integer.parseInt(answer);
					//inwon = inwonS;
					n++;

					break;
				  }
				  catch (NumberFormatException e)
				  {
					  sc = new Scanner(System.in);
					  System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
				  }
			  }
			  
			}
			while (inwonS<0 || inwonS>4);
			
			n = 0;
			if (inwonS<4 && ((MovieVO)am.ht.get(title)).getmAge()!=19)
			{
				do
				{
					if (n>0)
					{
						System.out.println("\t>> 5�� �̻� ���� ������ ���Ͽ� 4�� �̻� ���Ű� �Ұ����մϴ�.");
						System.out.println();
					}

					while (true)
					{
						try
						{
							System.out.print("\t>> û�ҳ� �ο� ����(0~"+(4-inwonS)+") :");
							answer = sc.next();
							
							if (answer.equals("00"))
								return;

							if (answer.equals("01"))
							{
								printMtime();
								return;
							}

							inwonC = Integer.parseInt(answer);

							n++;

							break;
						}
						catch (NumberFormatException e)
						{
							sc = new Scanner(System.in);
							System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
						}
					}
				}
				while (inwonC<0 || inwonC>(4-inwonS));
			}
			
			if ((inwonC+inwonS)>0)
			{
				System.out.println();
				if (((MovieVO)am.ht.get(title)).getmAge()==19)
					System.out.println("\t>> �����Ͻ� �����ο��� "+inwonS+"�� �Դϴ�.");
				else
					System.out.println("\t>> �����Ͻ� �����ο��� "+inwonS+"��, "+"û�ҳ��ο��� "+inwonC+"�� �Դϴ�.");
				
				do
				{
					System.out.print("\t>> �ش� �ο����� �����Ͻðڽ��ϱ�? (Y/N) : ");
					answer = sc.next();

					if (answer.equals("00"))
						return;

					if (answer.equals("01"))
					{
						printMtime();
						return;
					}
				}
				while (!answer.equals("y") && !answer.equals("Y") && !answer.equals("n") && !answer.equals("N"));
			}
			else if ((inwonC+inwonS)==0)
			{
				System.out.println("\t>> ���� �����Ͻ� �� �ο��� 0���Դϴ�.");
			}
		
		}
	    while (!answer.equals("y") && !answer.equals("Y") || (inwonC+inwonS)==0);


		seatc = new String[(inwonS+inwonC)];
		
		selSeat();
   }
   
   public static void selSeat() throws IOException, InterruptedException
   {
	    sc = new Scanner(System.in);
		
		n = 0;
		boolean flag;
		char seat1 = 'A';
		int seat2 = 0;

	   System.out.println();
	   System.out.println("\t>> ������ �¼��� ���ڸ��� ������ �ּ���.");
	   System.out.println("\t>> ������ �ڵ� ���� �˴ϴ�.");
	   for (int i=0; i<seatc.length; i++)
	   { 
		  do
	      {
			  flag = false;
			  while (true)
			  {
				  try
				  {
					if ((n+1)%2==0 && i!=0 && seat2>1 && seat[((int)seat1-65)][(seat2-2)] == 9633) // 9641 '��' // 9633 '��'
					{
						System.out.println("\t>> " + (n+1)+"��° �¼� (���� �ڵ�����) : " + seat1 + (seat2-1));
						seat2--;
						break;
					}
					else if ((n+1)%2==0 && i!=0 && seat2<seat[0].length && seat[((int)seat1-65)][seat2] == 9633)
					{
						System.out.println("\t>> " + (n+1)+"��° �¼� (���� �ڵ�����) : " + seat1 + (seat2+1));
						seat2++;
						break;
					}
					else
					{
						System.out.print("\t>> "+(n+1)+"��° �¼� ���� (ex. A1) : ");
						answer = sc.next();
					}
					
					if (answer.equals("00"))
						return;

					if (answer.equals("01"))
					{
						inwonsu();
						return;
					}
					
					// �¼� �� �޾ƿ���
					seat1 = answer.substring(0, 1).charAt(0);
					// �¼� ��ȣ �޾ƿ���
					seat2 = Integer.parseInt(answer.substring(1));
					
					//System.out.println(seat2);
					
					if (((int)seat1-65)>seat.length || ((int)seat1-65)<0)
					{	
						flag = true;
						System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ��Ŀ� �°� �Է��Ͽ��ּ���.\n");
						break;
					}

					if (seat2>seat[0].length || seat2<1)
					{
						flag = true;
						System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ��Ŀ� �°� �Է��Ͽ��ּ���.\n");
						break;
					}
					
					if (seat[((int)seat1-65)][(seat2-1)] == 9641)
					{
						flag = true;
						System.out.println("\t>> ������ �� ���� �¼��Դϴ�.");
						break;
					}
					

					for (int j=0; j<seatc.length; j++)
					{
						if (seatc[j]!=null && seatc[j].equals(answer))
						{
							flag = true;
							System.out.println("\t>> �¼��� �ߺ� ������ �� �����ϴ�.");
							break;
						}
					}
				
					break;
				  }
				  catch (NumberFormatException e)
				  {
					  sc = new Scanner(System.in);
					  System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ��Ŀ� �°� �Է��Ͽ��ּ���.\n");
				  }

			  }

		  }
		  while (flag);

		  seatc[i] = "" + seat1 + seat2;
					
		  n++;
		  
		}// end �ο���for��

		checkSel();

   }// end selSeat()
   

   // ���� ���� Ȯ�� �޼ҵ� 
   public static void checkSel() throws IOException, InterruptedException
   {
	  sc = new Scanner(System.in);
	  AdminMovie am = new AdminMovie();
      
	  Arrays.sort(seatc);
	  
	  Calendar cal = Calendar.getInstance();
	  int day = cal.get(Calendar.DAY_OF_WEEK);
	  
	  if (day==4)
	  {		
		  System.out.println("\n\n\n\n\n\t          �ߡ�[ ��ȭ�� �� �ȳ� ]�ߡ�");
		  System.out.println("\t===============================================");
		  System.out.println("\t              ������ ��ȭ�� ��!");
		  System.out.println("\t           ��ȭ�� �ݰ��� ��⼼��!");
		  System.out.println("\t===============================================");
	  }

	  System.out.print("\n\n\t===============================================");
      System.out.print("\n\t                *���� ���� Ȯ��*  ");
      System.out.print("\n\t-----------------------------------------------");
      System.out.print("\n\t��ȭ ���� : " + title);
      System.out.print("\n\t�� �ð� : " + datec+" "+time);
      System.out.print("\n\t�󿵰�    : "+ ((ScreenVO)am.ht2.get(serial)).getmRoom());
      System.out.print("\n\t���õ� �¼� : ");
	  for (String item : seatc)
		  System.out.print(item + " ");
	  if ((serial/10)%10 == 1 || (serial/10)%10 == 2)
		  moviePayTot = (inwonS*10000)+(inwonC*8000);
	  else if ((serial/10)%10 == 3)
		  moviePayTot = (inwonS*15000)+(inwonC*13000);
	  
	  // ��ȭ�� �� �ݰ�
	  if (day == 4)
		  moviePayTot /= 2;

	  System.out.print("\n\t���� Ƽ�� ���� �ݾ� : " + moviePayTot + "��");
      System.out.print("\n\t===============================================");

	  do
	  {
		  System.out.print("\n\t>> ��� �������� ������ �����Ͻðڽ��ϱ�? (Y/N) : ");
		  answer = sc.next();

		  if (answer.equals("00"))
			return;

	      if (answer.equals("01"))
		  {
			printMal();
			return;
		  }
	  }
	  while (!answer.equals("y") && !answer.equals("Y") && !answer.equals("n") && !answer.equals("N"));
      
	  if (answer.equals("n") || answer.equals("N"))
	  {
		  printMal();
		  return;
	  }
      //moviePayTot = rtv.inwon * 10000;
      PayDisc.selMem();
   }
	

}

//===============================================================================================================