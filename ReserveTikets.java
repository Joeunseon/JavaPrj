
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.*;

//
//====================================================================================================

/*
  Title  : ReserveTikets
  Function : 영화 예매 기능
  author   : 조은선
*/

//====================================================================================================

class ReserveTikets extends Record
{ 
  public static ArrayList<String> arrLast = new ArrayList<String>();		// 영화 목록 필터링을 위한 자료구조
  public static ArrayList<Integer> arrLast2 = new ArrayList<Integer>();		// 상영 목록 필터링을 위한 자료구조
  
  static int temp;
  static int n;
  //static char a;
  
  public static String title = "";		// 제목을 담을 변수
  public static Integer serialM=0;		// 선택된 영화제목 시리얼값
  public static Integer serial=0;		// 선택된 관람 시리얼값
  public static String time = "";	    // 선택된 관람 시간
  public static String datec = "";		// 선택된 관람 일자
  public static char[][] seat;			// 선택된 영화 좌석 리스트
  public static String[] seatc;			// 선택된 좌석 번호
  public static int inwonS;				// 선택된 성인 인원
  public static int inwonC;				// 선택된 청소년 인원
  
  //public static ReserveTiketsVO rtv = new ReserveTiketsVO();
  
  public static void tiketsMenuDisp() throws IOException, InterruptedException
  {
	Scanner sc = new Scanner(System.in);
	
	sel = 0;

	System.out.println("\n\n\t영화 예매                          00. 초기화면");
	System.out.println("\t===============================================");
	System.out.println("\t     *영화 목록 필터링 방법을 입력해주세요*  ");
	System.out.println("\t-----------------------------------------------");
	System.out.println("\t 1. 가나다순 | 2. 평점순");
	System.out.println("\t===============================================");
	
	n = 0;
	do
	{ 
	  if (n>0)
	  {
		  System.out.println("\t>>유효하지 않은 메뉴입니다. 다시 선택해주세요.");
		  System.out.println();
	  }
	  while (true)
	  {
		  try
		  {
			System.out.print("\t>> 입력 : ");
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
			  System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
		  }
	  }
	  
	}
	while (sel<1 || sel>2);
	
	temp = sel;
	printMlist(temp);
  }

  //상영리스트 출력 메소드
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
	
	// 가나다순 선택시
	if (temp==1)
	{
		//System.out.println(arrLast);
		
		// 오름차순 정렬
		Collections.sort(arrLast);
		//System.out.println(arrLast.get(0));
		
		n = 1;

		for (String title : arrLast)
		{
			if (n==1)
			{
				System.out.println("\t영화 리스트                              00.초기화면 01.이전화면");
				System.out.println("\t================================================================");
			}
			else 
				System.out.println();

			System.out.println("\t"+n+". "+title+" ("+((MovieVO)am.ht.get(title)).getmAge()+")   "+ "|평점 "+((MovieVO)am.ht.get(title)).getmStar()+"|");
			System.out.println("\t"+((MovieVO)am.ht.get(title)).getmStory());
			
			n++;
		}

		System.out.println("\t================================================================");

	}// end 가나다순 선택 if문
	// 별점 순
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
				System.out.println("\t영화 리스트                              00.초기화면 01.이전화면");
				System.out.println("\t================================================================");
			}
			else 
				System.out.println();

			System.out.println("\t"+n+". "+title+" ("+((MovieVO)am.ht.get(title)).getmAge()+")   "+ "|평점 "+((MovieVO)am.ht.get(title)).getmStar()+"|");
			System.out.println("\t"+((MovieVO)am.ht.get(title)).getmStory());
			
			n++;
		}
		System.out.println("\t================================================================");
	}// end 별점 순 if문 
	
	n = 0;
	do
	{ 
	  if (n>0)
	  {
		  System.out.println("\t>>유효하지 않은 메뉴입니다. 다시 선택해주세요.");
		  System.out.println();
	  }
	  while (true)
	  {
		  try
		  {
			System.out.print("\t>> 영화 선택 : ");
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
			  System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
		  }
	  }
	  
	}
	while (sel<1 || sel>arrLast.size());
	
	title = arrLast.get(sel-1);
	
	// 19세 이상 영화라면
	if (((MovieVO)am.ht.get(title)).getmAge()==19)
	{
		checkAge();
	}
	
	pinrtDate();
  }//end printMlist()
  
  // 청불 영화 선택시 안내 멘트
  public static void checkAge() throws IOException, InterruptedException
  {
	 sc = new Scanner(System.in);
	 
	 System.out.println("\n");
     System.out.println("\t================================================================");
	 System.out.println("\t       선택하신 영화는 '청소년 관람 불가' 등급으로");
	 System.out.println("\t       결제 진행 시 성인인증이 진행됩니다.");
	 System.out.println("\t       성인인증 불가시 예매가 제한 될 수 있습니다."); 
	 System.out.println("\t================================================================");
	 System.out.println("\t              1. 예매 진행   2. 이전 화면");
	 
     n = 0;
	do
	{ 
	  if (n>0)
	  {
		  System.out.println("\t>>유효하지 않은 메뉴입니다. 다시 선택해주세요.");
		  System.out.println();
	  }
	  while (true)
	  {
		  try
		  {
			System.out.print("\t>> 선택(1, 2) : ");
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
			  System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
		  }
	  }
	  
	}
	while (sel!=1);
  }

  // 상영일자 선택
  public static void pinrtDate() throws IOException, InterruptedException
  {
	 Scanner sc = new Scanner(System.in);
	 Calendar cal = Calendar.getInstance();
	 
	 // 날짜 받아오기
	 Date today = cal.getTime();					
	 cal.add(Calendar.DAY_OF_WEEK, 1);
	 Date tomorrow = cal.getTime();
	 cal.add(Calendar.DAY_OF_WEEK, 1);
	 Date thedayAfter = cal.getTime();
	 
	 SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
	 
	 System.out.println("\n\n");
	 System.out.println("\t상영 일자                                00.초기화면 01.이전화면");
	 System.out.println("\t================================================================");
	 System.out.println("\t선택된 영화 : " + title );
	 System.out.println();
	 // 상영 일자 선택
	 n = 0;
	 do
	 {
		 if (n>0)
			 System.out.println("\t>> 유효하지 않은 메뉴입니다. 다시 선택해주세요."); 

		 while (true)
		 {
			 try
			 {
				System.out.println("\t 1. "+(dformat.format(today)));
				System.out.println("\t 2. "+(dformat.format(tomorrow)));
				System.out.println("\t 3. "+(dformat.format(thedayAfter)));
				System.out.print("\t>> 상영일자 선택 : ");
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
				System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
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
    //상영시간 출력 메소드 
    public static void printMtime() throws IOException, InterruptedException
    { 
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n\n\n");
		System.out.println("\t영화 상영 리스트                         00.초기화면 01.이전화면");
		System.out.println("\t================================================================");
		//System.out.println("\t                       " + "[" + title + "]");
		System.out.println("\t선택된 영화 : " + title + " ("+datec+")" );
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
			
			// 같은 영화 제목 상영리스트 넣기
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
				// 같은 날짜, 같은 상영관이면 
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
		int z;		// 예매된 좌석
		int t;		// 총 좌석
		for (int i=0; i<arrLast2.size(); i++)
		{
			z = 0;
			t = 0;
			if (((arrLast2.get(i)/10)%10)==1)
			{
				if (s1==0)
					System.out.println("\n\t"+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmRoom());	
				
				s1++;
				
				// 상영시간 출력
				System.out.print("\t"+n+". "+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmSh()+":"+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmSm()
					+" ~ "+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmEh()+":"+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmEm());
				
				// 죄석 수량 출력
				char[][] seat = ((ScreenVO)am.ht2.get(arrLast2.get(i))).getmSeat();

				for (int a=0; a<seat.length; a++)
				{
					for (int b=0; b<seat[0].length; b++)
					{
						if ((int)seat[a][b] == 9633)	// 9641 '▩' // 9633 '□'
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
				
				// 상영시간 출력
				System.out.print("\t"+n+". "+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmSh()+":"+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmSm()
					+" ~ "+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmEh()+":"+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmEm());
				
				// 죄석 수량 출력
				char[][] seat = ((ScreenVO)am.ht2.get(arrLast2.get(i))).getmSeat();

				for (int a=0; a<seat.length; a++)
				{
					for (int b=0; b<seat[0].length; b++)
					{
						if ((int)seat[a][b] == 9641)	// 9641 '▩' // 9633 '□'
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
				
				// 상영시간 출력
				System.out.print("\t"+n+". "+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmSh()+":"+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmSm()
					+" ~ "+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmEh()+":"+((ScreenVO)am.ht2.get(arrLast2.get(i))).getmEm());
				
				// 죄석 수량 출력
				char[][] seat = ((ScreenVO)am.ht2.get(arrLast2.get(i))).getmSeat();

				for (int a=0; a<seat.length; a++)
				{
					for (int b=0; b<seat[0].length; b++)
					{
						if ((int)seat[a][b] == 9633)	// 9641 '▩' // 9633 '□'
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
			System.out.println("\t>> 상영 리스트가 없습니다.");
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
			  System.out.println("\t>> 유효하지 않은 메뉴입니다. 다시 선택해주세요.");
			  System.out.println();
		  }
		  while (true)
		  {
			  try
			  {
				System.out.print("\t>> 상영시간 선택 : ");
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
				  System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
			  }
		  }
		  
		}
		while (sel<1 || sel>arrLast2.size()); 

		serial = arrLast2.get(sel-1);

		printMal();
		
    }//end printMtime()


  /////////////////////////////////////////////////////////////////////////////////////////////////////////////[★좌석선택]


    // 거리두기 안내사항 출력 메소드
    public static void printMal() throws IOException, InterruptedException
    {
      System.out.println("\n");
      System.out.println("\t================================================================");
	  System.out.println("\tCRG는 사회적 거리두기를 준수합니다.");
      System.out.println("\t사회적 거리두기 좌석(▩)은 비워주시길 바랍니다.");
	  System.out.println("\t================================================================");

	  printSeat();
    }//end printMal()

   // 선택한 상영관 좌석 출력
   public static void printSeat() throws IOException, InterruptedException
   {
	   sc = new Scanner(System.in);
	   AdminMovie am = new AdminMovie();

	   seat = ((ScreenVO)am.ht2.get(serial)).getmSeat();
	   
	   time = ((ScreenVO)am.ht2.get(serial)).getmSh()+":"+((ScreenVO)am.ht2.get(serial)).getmSm()
		   						+" ~ "+ ((ScreenVO)am.ht2.get(serial)).getmEh()+":"+((ScreenVO)am.ht2.get(serial)).getmEm();
	   //datec = ((ScreenVO)am.ht2.get(serial)).getmDate();
	   System.out.println("\n\n");
	   System.out.println("\t좌석 선택                                00.초기화면 01.이전화면"); 
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
				  System.out.println("\t>> 5인 이상 집합 금지로 인하여 4매 이상 예매가 불가능합니다.");
				  System.out.println();
			  }
			  while (true)
			  {
				  try
				  {
					System.out.print("\t>> 성인 인원 선택(0~4) : ");
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
					  System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
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
						System.out.println("\t>> 5인 이상 집합 금지로 인하여 4매 이상 예매가 불가능합니다.");
						System.out.println();
					}

					while (true)
					{
						try
						{
							System.out.print("\t>> 청소년 인원 선택(0~"+(4-inwonS)+") :");
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
							System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
						}
					}
				}
				while (inwonC<0 || inwonC>(4-inwonS));
			}
			
			if ((inwonC+inwonS)>0)
			{
				System.out.println();
				if (((MovieVO)am.ht.get(title)).getmAge()==19)
					System.out.println("\t>> 선택하신 성인인원은 "+inwonS+"명 입니다.");
				else
					System.out.println("\t>> 선택하신 성인인원은 "+inwonS+"명, "+"청소년인원은 "+inwonC+"명 입니다.");
				
				do
				{
					System.out.print("\t>> 해당 인원으로 예매하시겠습니까? (Y/N) : ");
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
				System.out.println("\t>> 현재 선택하신 총 인원은 0명입니다.");
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
	   System.out.println("\t>> 예매할 좌석을 한자리씩 선택해 주세요.");
	   System.out.println("\t>> 연석은 자동 선택 됩니다.");
	   for (int i=0; i<seatc.length; i++)
	   { 
		  do
	      {
			  flag = false;
			  while (true)
			  {
				  try
				  {
					if ((n+1)%2==0 && i!=0 && seat2>1 && seat[((int)seat1-65)][(seat2-2)] == 9633) // 9641 '▩' // 9633 '□'
					{
						System.out.println("\t>> " + (n+1)+"번째 좌석 (연석 자동선택) : " + seat1 + (seat2-1));
						seat2--;
						break;
					}
					else if ((n+1)%2==0 && i!=0 && seat2<seat[0].length && seat[((int)seat1-65)][seat2] == 9633)
					{
						System.out.println("\t>> " + (n+1)+"번째 좌석 (연석 자동선택) : " + seat1 + (seat2+1));
						seat2++;
						break;
					}
					else
					{
						System.out.print("\t>> "+(n+1)+"번째 좌석 선택 (ex. A1) : ");
						answer = sc.next();
					}
					
					if (answer.equals("00"))
						return;

					if (answer.equals("01"))
					{
						inwonsu();
						return;
					}
					
					// 좌석 열 받아오기
					seat1 = answer.substring(0, 1).charAt(0);
					// 좌석 번호 받아오기
					seat2 = Integer.parseInt(answer.substring(1));
					
					//System.out.println(seat2);
					
					if (((int)seat1-65)>seat.length || ((int)seat1-65)<0)
					{	
						flag = true;
						System.out.println("\t>> 잘못 입력하셨습니다. 양식에 맞게 입력하여주세요.\n");
						break;
					}

					if (seat2>seat[0].length || seat2<1)
					{
						flag = true;
						System.out.println("\t>> 잘못 입력하셨습니다. 양식에 맞게 입력하여주세요.\n");
						break;
					}
					
					if (seat[((int)seat1-65)][(seat2-1)] == 9641)
					{
						flag = true;
						System.out.println("\t>> 선택할 수 없는 좌석입니다.");
						break;
					}
					

					for (int j=0; j<seatc.length; j++)
					{
						if (seatc[j]!=null && seatc[j].equals(answer))
						{
							flag = true;
							System.out.println("\t>> 좌석은 중복 선택할 수 없습니다.");
							break;
						}
					}
				
					break;
				  }
				  catch (NumberFormatException e)
				  {
					  sc = new Scanner(System.in);
					  System.out.println("\t>> 잘못 입력하셨습니다. 양식에 맞게 입력하여주세요.\n");
				  }

			  }

		  }
		  while (flag);

		  seatc[i] = "" + seat1 + seat2;
					
		  n++;
		  
		}// end 인원수for문

		checkSel();

   }// end selSeat()
   

   // 예매 정보 확인 메소드 
   public static void checkSel() throws IOException, InterruptedException
   {
	  sc = new Scanner(System.in);
	  AdminMovie am = new AdminMovie();
      
	  Arrays.sort(seatc);
	  
	  Calendar cal = Calendar.getInstance();
	  int day = cal.get(Calendar.DAY_OF_WEEK);
	  
	  if (day==4)
	  {		
		  System.out.println("\n\n\n\n\n\t          ◆◆[ 문화의 날 안내 ]◆◆");
		  System.out.println("\t===============================================");
		  System.out.println("\t              오늘은 문화의 날!");
		  System.out.println("\t           영화를 반값에 즐기세요!");
		  System.out.println("\t===============================================");
	  }

	  System.out.print("\n\n\t===============================================");
      System.out.print("\n\t                *예매 정보 확인*  ");
      System.out.print("\n\t-----------------------------------------------");
      System.out.print("\n\t영화 제목 : " + title);
      System.out.print("\n\t상영 시간 : " + datec+" "+time);
      System.out.print("\n\t상영관    : "+ ((ScreenVO)am.ht2.get(serial)).getmRoom());
      System.out.print("\n\t선택된 좌석 : ");
	  for (String item : seatc)
		  System.out.print(item + " ");
	  if ((serial/10)%10 == 1 || (serial/10)%10 == 2)
		  moviePayTot = (inwonS*10000)+(inwonC*8000);
	  else if ((serial/10)%10 == 3)
		  moviePayTot = (inwonS*15000)+(inwonC*13000);
	  
	  // 문화의 날 반값
	  if (day == 4)
		  moviePayTot /= 2;

	  System.out.print("\n\t예상 티켓 결제 금액 : " + moviePayTot + "원");
      System.out.print("\n\t===============================================");

	  do
	  {
		  System.out.print("\n\t>> 상단 내용으로 결제를 진행하시겠습니까? (Y/N) : ");
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