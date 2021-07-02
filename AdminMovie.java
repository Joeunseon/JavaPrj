/*
	Title	 : AdminMovie
	Function : 관리자 메뉴에서 영화 관리 메뉴 호출시 영화 등록 및 상영 스케줄 등록 기능 제공
	author	 : 심혜진, 조은선
*/

// * 수정사항*
// 21-05-16 영화 정보 입력 시 년도까지 입력 되도록 (기존 MM-DD)
//			영화 정보 개봉일자, 종영일자 입력 시 년도 까지 수정 되도록 (기존 MM-DD)
//			영화 정보 수정 시 현재 설정된 값 출력되도록 수정
//			영화 상영 등록 시 상영 현황 값 출력 기능 추가 (같은 날짜, 같은 상영관 단, 정렬은 안됨..)
//			상영 시간 (시간, 분)유효성 검사 기능 추가
//			상영 시간 분 예외처리 기능 추가
// 21-06-06 상영 정보 객체에 좌석 추가

import java.util.Scanner;
import java.util.Iterator;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.LinkedList;
import java.util.InputMismatchException;

class MovieVO
{
	 // 영화 정보 담는 변수들
	 private String mTitle;			// 영화 제목
	 private String mGenre;			// 장르
	 private String mOpenDate;		// 개봉일자 
	 private String mEndDate;		// 종영일자
	 private String mStory;		    // 줄거리
	 private int mAge;				// 관람가능연령
	 private int mTime;				// 총 상영시간(러닝타임)
	 private int mStar;				// 평점
		
	// 생성자(사용자 정의 생성자 → 매개변수 없는 기본 생성자)
	MovieVO()
	{
	}
	
	// 생성자(사용자 정의 생성자)
	public MovieVO(String mTitle, String mGenre, String mOpenDate, String mEndDate, String mStory, int mAge, int mTime, int mStar)
	{
		this.mTitle = mTitle;
		this.mGenre = mGenre;
		this.mOpenDate = mOpenDate;
		this.mEndDate = mEndDate;
		this.mStory = mStory;
		this.mAge = mAge;
		this.mTime = mTime;
		this.mStar = mStar;
	}
	
	
	// get, set 메소드 
	public String getmTitle()			// 영화 제목
	{
		return mTitle;
	}

	public void setmTitle(String mTitle)
	{
		this.mTitle = mTitle;
	}

	public String getmGenre()			// 장르
	{
		return mGenre;
	}

	public void setmGenre(String mGenre)
	{
		this.mGenre = mGenre;
	}

	public String getmOpenDate()		// 상영 일자
	{
		return mOpenDate;
	}

	public void setmOpenDate(String mOpenDate)
	{
		this.mOpenDate = mOpenDate;
	}

	public String getmEndDate()			// 종영 일자
	{
		return mEndDate;
	}

	public void setmEndDate(String mEndDate)
	{
		this.mEndDate = mEndDate;
	}

	public String getmStory()			// 줄거리
	{
		return mStory;
	}

	public void setmStory(String mStory)
	{
		this.mStory = mStory;
	}

	public int getmAge()				// 관람 가능 연령
	{
		return mAge;
	}

	public void setmAge(int mAge)
	{
		this.mAge = mAge;
	}

	public int getmTime()				// 총 상영 시간
	{
		return mTime;
	}

	public void setmTime(int mTime)
	{
		this.mTime = mTime;
	}

	public int getmStar()				// 평점
	{
		return mStar;
	}

	public void setmStar(int mStar)
	{
		this.mStar = mStar;
	}
	
	// Hashtable 출력시 Hashcode가 출력됨, 값을 가져오기 위해 toString Override
	@Override
	public String toString()
	{
		return "\t"+mTitle+" ("+mAge+")\n\t평점 : "+mStar+"\n\t장르 : "+mGenre+"\n\t개봉일자 : "+mOpenDate+"\n\t종영일자 : "+mEndDate+"\n\t총 상영시간 : "+mTime+"\n\t"+mStory;
		//영화제목 장르 개봉일자 종영일자 줄거리 관람가능연령 총상영시간 평점
	}

}

class ScreenVO
{
	//변수설정 
   private String mDate;					// 상영일자
   private String mRoom;					// 상영관
   private String mTitle;					// 영화제목
   private String mSh;				    	// 상영시작시간(시간) 
   private String mSm;				    	// 상영시작시간(분)
   private String mEh;				        // 상영종료시간(시간)
   private String mEm;				    	// 상영종료시간(분)  int
   private char[][] mSeat;
   
   // 생성자(사용자 정의 생성자 → 매개변수 없는 기본 생성자)
   ScreenVO()
   {	
   }

   // 생성자(사용자 정의 생성자)
   public ScreenVO(String mDate,String mRoom, String mTitle, String mSh, String mSm, String mEh, String mEm, char[][] mSeat)
   {
	    this.mDate = mDate;
		this.mRoom = mRoom;
		this.mTitle = mTitle;
		this.mSh = mSh;
		this.mSm = mSm;
		this.mEh = mEh;
		this.mEm = mEm;
		this.mSeat = mSeat;
   }

   //get, set 메소드

   public String getmDate()			// 상영 일자
   {
	  return mDate;	
   }

   public void setmDate(String mDate)	
   {
	  this.mDate = mDate;	
   }
  
   public String getmRoom()			// 상영관
   {
      return mRoom;
   }

   public void setmRoom(String mRoom)
   {
      this.mRoom=mRoom;
   }

   public String getmTitle()		// 상영영화 제목
   {
	  return mTitle;	
   }

   public void setmTitle(String mTitle)		
   {
	  this.mTitle = mTitle;	
   }

   public String getmSh()			// 상영 시작 시간
   {
	  return mSh;
   }

   public void setmSh(String mSh)
   {
	  this.mSh = mSh;
   }

   public String getmSm()			// 상영 시작 분
   {
	  return mSm;
   }

   public void setmSm(String mSm)
   {
	  this.mSm = mSm;
   }

   public String getmEh()			// 상영 종료 시간
   {
	  return mEh;
   }

   public void setmEh(String mEh)
   {
	  this.mEh = mEh;
   }

   public String getmEm()			// 상영 종료 분
   {
	  return mEm;
   }

   public void setmEm(String mEh)
   {
	  this.mEm = mEh;
   }
   
   public char[][] getmSeat()			// 좌석
   {
	  return mSeat;
   }

   public void setmSeat(char[][] mSeat)
   {
	  this.mSeat = mSeat;
   }

   // Hashtable 출력시 Hashcode가 출력됨, 값을 가져오기 위해 toString Override
   @Override
   public String toString()
   {
	   return "\t"+mTitle+"  "+mRoom+"\n\t상영일자 : "+mDate+"\n\t상영시간 : "+mSh+":"+mSm+" ~ "+mEh+":"+mEm;
   }
}


class AdminMovie
{
	public static final Hashtable<String, MovieVO> ht;		// 영화 정보를 담을 hashtable
	public static final Hashtable<Integer, ScreenVO> ht2;	// 영화 스케줄링 담을 hashtable
	//public static  Map<String, String> ht3;					// 영화 시작/종료 시간 담을 hashtable
    // 추가 ★
    //public static  Map<String, String> rm1;                 // 상영시간/상영관 번호 담을 hashtable
	
	private static Integer s;		                        // hashtable의 등록되는 순서 세번째 serial 값
    public static Integer serial;                           // hashtable 키값을 담을 변수 
    public static String rmNum;                             // 상영시간, 상영관번호 담을 hashtable    
    public static String strSerial;                         // serial을 만들기 위한 변수       
    public static int sel;                                  // 사용자 입력값

	// static 초기화 블럭
	static 
	{
		ht = new Hashtable<>();
		ht2 = new Hashtable<>();
        //ht3 = new HashMap<>();
        // 추가 ★
        //rm1 = new HashMap<>();
		//ht333 = new HashMap<>();
		 ht.put("소울", (new MovieVO("소울", "애니메이션", "2022-06-01", "2022-07-01", "평범한 삶의 소중함과 살아갈 의미를 깨닫는 성인을 위한 힐링 애니메이션", 12, 137, 8)));
		 ht.put("미나리", (new MovieVO("미나리", "가족/감동", "2021-06-11", "2021-09-12", "1980년대에 아메리칸드림을 꿈꾸며 미국으로 이민을 떠난 한국 가족이야기", 15, 117, 9)));
	     ht.put("범죄와의 전쟁", (new MovieVO("범죄와의 전쟁", "범죄", "2021-03-20", "2021-04-20", "넘버원이 되고 싶은 나쁜 놈들의 한판 승부", 19, 136, 7)));
		s = 0;
	}

	// 영화 관리 메뉴 출력 메소드
	public static void movieMenuDisp() 
	{
		Scanner sc = new Scanner(System.in);

		int sel;

		System.out.println("\n\n");
		System.out.println("\t[영화 관리]					  0. 관리자 메뉴");
		System.out.println("\t================================================================");
		System.out.println("\t1. 영화 등록");
		System.out.println("\t2. 등록 전체 출력");
		System.out.println("\t3. 등록 영화 정보 변경");
		System.out.println("\t4. 등록 영화 삭제");
		System.out.println("\t5. 상영 리스트 등록");
		System.out.println("\t6. 상영 현황 전체 출력");
		System.out.println("\t7. 상영 리스트 삭제");
		System.out.println();
	

		while (true)
		{
			try
			{
				System.out.print("\t>> 메뉴를 선택해주세요 (1~7) : ");
				sel = sc.nextInt();
				break;
			}
			catch (InputMismatchException e)
			{
				sc = new Scanner(System.in);
				System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
			}
		}
		

		switch (sel)
		{
			case 0: break;							// 관리자 메뉴로 이동, 메소드 종료
			default : movieMenuSelect(sel); break;
		}
		
	} // end movieMenuDisp() 영화 관리 메뉴 출력 메소드

	// 영화 관리 메뉴 선택 메소드
	public static void movieMenuSelect(int sel) 
	{
		switch (sel)
		{ 
	    	case 1: registerMovie(); break;		
			case 2: printMoive(); break;		
			case 3: chaMoive(); break;			
			case 4: delMoive(); break; 
			case 5: registerScreen(); break;
			case 6: printScreen(); break;
			case 7: delScreen(); break;
		}

		movieMenuDisp();
	} // end movieMenuSelect() 영화 관리 메뉴 선택 메소드

  // 영화정보 등록 메소드 
  public static void registerMovie() 
  {
	 Scanner sc = new Scanner(System.in);

	 String title="";	// 영화 제목 변수 
	 String genre;		// 영화 장르
	 String openDate;	// 영화 개봉일자
	 String endDate;	// 영화 종영일자
	 String story;		// 영화 줄거리
	 int age;			// 영화 관람가능연령
	 int time;			// 영화 총 상영시간
	 int star;			// 영화 평점
	 String answer="";
	 int t=0;					//do while을 다시 돌았다면...
	 String yy, mm, dd;				// 개봉, 종영일자 유효성 검사를 위한 변수
	 int oyy, omm, odd, eyy, emm, edd;    // 개봉, 종영일자 유효성 검사를 위한 변수
	 boolean flag;				 // do while문을 빠져나가기 위한 
	 
	 System.out.println("\n\n");
	 System.out.println("\t영화 등록				      -1. 영화 관리 메뉴");
	 System.out.println("\t================================================================");
	
	  
	 do
	 {	
		 if (t>0)
			System.out.println("\t" + title + "은 이미 등록되어있습니다.");

		 System.out.print("\t>> 등록할 영화 제목을 입력하세요 : ");
		 title = sc.nextLine();
		 
		 // 영화 관리 메뉴로 돌아가기 위해
		 if (title.equals("-1"))
			 return; 

		 t++;
	 }
	 while (ht.containsKey(title));
		  
	 System.out.println();
	 System.out.print("\t>> 등록할 영화 장르를 입력하세요 : ");
	 genre = sc.next();
	 if (genre.equals("-1"))
			 return; 
	
	 t = 0;			// do while문을 돌기 위해 t 초기화 
	 
	 do
	{
		 //flag = false;	// do while문을 돌기 위해 flag 값 초기화

		 if (t>0)
			 System.out.println("\n\t>> 입력한 일자가 유효하지 않습니다.");
		
		while (true)		// 예외처리를 하기 위한 반복문 
		{
			 flag = false;	// do while문을 돌기 위해 flag 값 초기화
			 System.out.println();
			 System.out.print("\t>> 등록할 영화 개봉 일자를 입력하세요 (YYYY-MM-DD) : ");
			 openDate = sc.next();		
			 
			 // 영화 관리로 돌아가기 위해
			 if (openDate.equals("-1"))
				 return; 
			 
			 try
			 {
				// 입력한 개봉일자 월만 가져오기 
				 //mm = openDate.substring(0,2);		// 사용자가 입력한 일자 월만 잘라서 mm 변수에 저장
				 yy = openDate.substring(0, 4);
				 mm = openDate.substring(5, 7);
				 dd = openDate.substring(8, 10);		// 사용자가 입력한 일자 일만 잘라서 dd 변수에 저장

				 String hi = openDate.substring(4,5);

				 // 하이픈이 아니라면
				 if (!hi.equals("-"))
					flag = true;
				
				 hi = openDate.substring(7,8);
				 
				 if (!hi.equals("-"))
					 flag = true;

				// 정수형으로 변경
				 oyy = Integer.parseInt(yy);		// 년도만 잘랐던 변수 정수형으로 변환 (Open year) 
				 omm = Integer.parseInt(mm);		// 월만 잘랐던 변수 정수형으로 변환 (Open month)
				 odd = Integer.parseInt(dd);		// 일만 잘랐던 변수 정수형으로 변환 (Open day)	
				 
				 break;
			 }
			 catch (NumberFormatException e) // 문자열을 숫자로 변환시 문자열의 값이 숫자가 아니라면 
			 {
				 System.out.println("\n\t>> 잘못 입력하셨습니다. 입력 형식에 맞춰 입력해주세요.");
			 }
			 catch (StringIndexOutOfBoundsException so)	// 문자열의 substring 중 글자수가 부족하다면
			 {
				 System.out.println("\n\t>> 잘못 입력하셨습니다. 입력 형식에 맞춰 입력해주세요.");
			 }

		}
		  // 입력 받은 개봉일 유효성 검사
		 if (omm<1 || omm>12)				// 월이 1~12가 아니라면
			 flag = true;

		 if (odd>31)						// 일이 31일이 넘으면
			 flag = true;
		 else if (omm==2 && odd>28)			// 2월일시 28일이 넘으면  
			 flag = true;
		 else if ((omm==4 || omm==6 || omm==9 || omm==11) && odd>30)	// 4, 6, 9. 11월일시 30일이 넘으면 
			 flag = true;

		 t++;
	 }
	 while (flag);
	
	 t = 0;			// do while문을 돌기 위해 t 초기화 

	 do
	 {
		 // flag = false;	// do while문을 돌기 위해 flag 값 초기화

		 if (t>0)
			 System.out.println("\n\t>> 입력한 일자가 유효하지 않습니다.");

		 while (true)		// 예외처리를 하기 위한 반복문 
		 {
			 flag = false;

			 try
			 {
				 System.out.println();
				 System.out.print("\t>> 등록할 영화 종영 일자를 입력하세요 (YYYY-MM-DD) : ");
				 endDate = sc.next();

				 // 영화 관리 메뉴로 이동, 메소드 종료
				 if (endDate.equals("-1"))
					 return; 

				 // 입력한 종영일자 월만 가져오기 
				 yy = endDate.substring(0, 4);		// 사용자가 입력한 일자 년도만 잘라서 yy 변수에 저장
				 mm = endDate.substring(5,7);		// 사용자가 입력한 일자 월만 잘라서 mm 변수에 저장
				 dd = endDate.substring(8,10);		// 사용자가 입력한 일자 월만 잘라서 mm 변수에 저장
				 
				 //System.out.println("\t"+mm+"-"+dd);
				 // 정수형으로 변경
				 eyy = Integer.parseInt(yy);		// 년도만 잘랐던 변수 정수형으로 변환 (end year)
				 emm = Integer.parseInt(mm);		// 월만 잘랐던 변수 정수형으로 변환 (end month)
				 edd = Integer.parseInt(dd);		// 일만 잘랐던 변수 정수형으로 변환	(end day)

				 // 사용자가 입력한 문자열 잘라서 저장 (하이픈, 양식이 맞나 검사하기 위해) 
				 String hi = endDate.substring(4,5);

				 // 하이픈이 아니라면
				 if (!hi.equals("-"))
					flag = true;

				 hi = endDate.substring(7,8);
				 
				 if (!hi.equals("-"))
					 flag = true;

				  break;
			 }
			 catch (NumberFormatException e) // 문자열을 숫자로 변환시 문자열의 값이 숫자가 아니라면 
			 {
				 System.out.println("\n\t>> 잘못 입력하셨습니다. 입력 형식에 맞춰 입력해주세요.");
			 }
			 catch (StringIndexOutOfBoundsException so)	// 문자열의 substring 중 글자수가 부족하다면
			 {
				 System.out.println("\n\t>> 잘못 입력하셨습니다. 입력 형식에 맞춰 입력해주세요.");
			 }
		 }
		 
		 if (oyy > eyy)
			 flag = true;

		 if (emm<1 || emm>12)				// 월이 1~12가 아니라면
			 flag = true;

		 if (edd>31)						// 일이 31일이 넘는다면
			 flag = true;
		 else if (emm==2 && edd>28)			// 2월일시 28일이 넘으면
			 flag = true;
		 else if ((emm==4 || emm==6 || emm==9 || emm==11) && edd>30)	// 4, 6, 9. 11월일시 30일이 넘으면 
			 flag = true;
	
		 if (oyy==eyy)
		 {
			 if (omm>emm)						// 개봉 달이 종영 달보다 크다면
				 flag = true;

			 if (omm==emm && odd>edd)			// 개봉 달과 종영 달이 같은데 개봉일이 종영일보다 크다면
				 flag = true;
		 }
		  
		 t++;

	 }
	 while (flag);

	 sc.nextLine();
	 System.out.println();
	 System.out.print("\t>> 등록할 영화 줄거리를 입력하세요 : ");
	 story = sc.nextLine();
	 // 영화 관리 메뉴로 이동, 메소드 종료
	 if (story.equals("-1"))
		 return;
	 
	 while (true)
	 {
		 System.out.println();
		 System.out.print("\t>> 등록할 영화 관람 가능 연령을 입력하세요 : ");
		 try
		 {
		    age = sc.nextInt();

			break;
		 }
		 catch (InputMismatchException e)
		 {
			 sc = new Scanner(System.in);
			System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
		 }
	 }
	 
	 // 영화 관리 메뉴로 이동, 메소드 종료
	 if (age == -1)
		 return;
	 
	 while (true)
	 {
		 System.out.println();
		 System.out.print("\t>> 등록할 영화 총 상영시간을 입력하세요 (MM) : ");

		 try
		 {
			time = sc.nextInt();

			break;
		 }
		 catch (InputMismatchException e)
		 {
			 sc = new Scanner(System.in);
			System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
		 }
	 }
	
	 // 영화 관리 메뉴로 이동, 메소드 종료
	 if (time == -1)
		 return;
	 
	 while (true)
	 {
		 System.out.println();
		 System.out.print("\t>> 등록할 영화 평점을 입력하세요. : ");

		 try
		 {
			star = sc.nextInt();

			break;
		 }
		 catch (InputMismatchException e)
		 {
			 sc = new Scanner(System.in);
			System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
		 }
	 }
	 
	 // 영화 관리 메뉴로 이동, 메소드 종료
	 if (star == -1)
		 return;
	 
	 // 입력받은 영화 정보 hashtable에 넣기 
	 ht.put(title,(new MovieVO(title, genre, openDate, endDate, story, age, time, star)));		// Hashtable<영화제목, 영화정보>
	 //ht.put("미나리",(new MovieVO("미나리", "가족", "02-19", "04-01", "재미교포 이야기", 12, 115, 8)));
	 //ht.put("소울",(new MovieVO("소울", "애니", "02-27", "03-27", "음악 영화", 15, 100, 9)));
	
	 System.out.println();
	 System.out.println("\t>> 영화『"+title+"』이(가) 정상적으로 등록 완료되었습니다.");
	 

  } // end registerMovie() 영화 등록 메소드 

   // 등록된 영화 전체 출력 메소드 
   public static void printMoive() 
   {
	  System.out.println("\n\n");
	  System.out.println("\t		  [등록된 영화 전체 출력]");
	  System.out.println("\t================================================================");
	  
	  Set<String> key = ht.keySet();
	  Iterator<String> hit = key.iterator();
	  while (hit.hasNext())
	  {
		  String keys = (String)hit.next();
		  System.out.println(ht.get(keys));			// MovieVO에 Override한 toString 값이 넘어옴
		  System.out.println("\t================================================================");
	  }
   } // end printMovie()


   // 영화 정보 변경 메소드
   public static void chaMoive()   
   {
      Scanner sc = new Scanner(System.in);
      
      String str;	// 수정할 메뉴가 문자열일 시 입력 값을 담을 변수
      int n;		// 수정할 메뉴가 정수형일 시 입력 값을 담을 변수
      String key;	// 변경할 hashtable의 key 값을 담을 변수 
	  String yy, mm, dd;			// 개봉, 종영일자 유효성 검사를 위한 변수
	  int oyy, omm, odd, eyy, emm, edd;  // 개봉, 종영일자 유효성 검사를 위한 변수
	  boolean flag;  // do while문을 빠져나가기 위한 

      int sel=0;	// 선택 값을 담을 변수
      int t=0;		// do while문을 또 돈다면...
      
      System.out.println("\n\n");
      System.out.println("\t영화 정보 변경				      -1. 영화 관리 메뉴");
      System.out.println("\t================================================================");
      System.out.print("\t>> 변경할 영화 제목을 입력하세요 : ");
      key = sc.nextLine();
	  // 영화 관리 메뉴로 이동, 메소드 종료
	  if (key.equals("-1"))
		  return;
      
      if (ht.containsKey(key))
      {
         do
         {
            if (t>0)
              System.out.println(">> 유효하지 않은 메뉴입니다. 다시 선택해주세요.\n");
			
			System.out.println();
            System.out.println("\t1. 영화 제목");
            System.out.println("\t2. 영화 장르");
            System.out.println("\t3. 개봉 일자");
            System.out.println("\t4. 종영 일자");
            System.out.println("\t5. 줄거리");
            System.out.println("\t6. 관람 가능 연령");
            System.out.println("\t7. 총 상영 시간");
            System.out.println("\t8. 평점"); 
            System.out.println();
            System.out.println();
            System.out.print("\t>> 변경할 옵션을 선택하세요 : ");

            sel = sc.nextInt();
			// 영화 관리 메뉴로 이동, 메소드 종료
			if (sel == -1)
				 return;
 
            t++;
         }
         while (sel>8 || sel<1);         // 입력값이 1~8이 아니라면 
      }
      else 
         System.out.println("\t>> 해당 영화는 등록 되어있지않습니다.");


      //sel에 다른 수 입력시 default로 해결할 수 있도록
      
      if (sel==1)

      {
		 sc.nextLine();
         System.out.print("\t>> 수정할 제목을 입력하세요 : ");
         str = sc.nextLine();
		 // 영화 관리 메뉴로 이동, 메소드 종료
		 if (str.equals("-1"))
			return;

         ht.get(key).setmTitle(str);		// 생성된 인스턴스에 변경하고자하는 value 값 세팅
         ht.get(key).getmTitle();			// 생성된 인스턴스에 value 값을 넣어준 것
         ht.replace(key, ht.get(key));		// Hatable의 key의 값의 value를 변경한다.
		 
		 // key 값이 영화 제목으로 있음, key 값을 변경하기 위해
		 String genre = ((MovieVO)ht.get(key)).getmGenre();
		 String openDate = ((MovieVO)ht.get(key)).getmOpenDate();
		 String endDate = ((MovieVO)ht.get(key)).getmEndDate();
		 String story = ((MovieVO)ht.get(key)).getmStory();
		 int age = ((MovieVO)ht.get(key)).getmAge();
		 int time = ((MovieVO)ht.get(key)).getmTime();
		 int star = ((MovieVO)ht.get(key)).getmStar();

		 ht.remove(key);					// 기존 key의 내용 삭제
		 ht.put(str,(new MovieVO(str, genre, openDate, endDate, story, age, time, star)));		// 변경된 key값으로 내용 생성

		 System.out.print("\n\t>> 제목이 정상적으로 수정 완료되었습니다.");
      } 

      if (sel==2)
      {
		 System.out.println("\n\t== 현재 장르 : " + ((MovieVO)ht.get(key)).getmGenre() + " ==");
         System.out.print("\t>> 수정할 장르를 입력하세요 : ");
         str = sc.next();
		 // 영화 관리 메뉴로 이동, 메소드 종료
		 if (str.equals("-1"))
			return;

         ht.get(key).setmGenre(str);
         ht.get(key).getmGenre();
         ht.replace(key, ht.get(key));

		 System.out.print("\n\t>> 장르가 정상적으로 수정 완료되었습니다.");
      } 
	
	  // do while 문을 돌기 위해 초기화 
	  t = 0;

      if (sel==3)
      {
		 System.out.println("\n\t== 현재 개봉일자 : " + ((MovieVO)ht.get(key)).getmOpenDate() + " ==");
		 do
		 {
			 // do while 문을 돌기 위해 초기화 
			 //flag = false;

			 if (t>0)
				System.out.println("\n\t>> 입력한 일자가 유효하지 않습니다.");

			 while (true)
			 {
				flag = false;

				try
				{
					 System.out.print("\t>> 수정할 개봉 일자를 입력하세요 : ");
					 str = sc.next();
					 // 영화 관리 메뉴로 이동, 메소드 종료
					 if (str.equals("-1"))
						return;
					 
					 String heTime = ((MovieVO)ht.get(key)).getmEndDate();	// 해당 key의 종영일을 가져오기 (개봉일이 종영일 후에 있다면 등록되지않게 하기 위해)

					 // 입력한 개봉일자 월만 가져오기 
					 yy = heTime.substring(0, 4);
					 mm = heTime.substring(5, 7);	// 종영일 월만 잘라서 mm 변수에 넣기
					 dd = heTime.substring(8, 10);	// 종영일 일만 잘라서 dd 변수에 넣기
					 
					 eyy = Integer.parseInt(yy);
					 emm = Integer.parseInt(mm);	// 정수형으로 변환
					 edd = Integer.parseInt(dd);	// 정수형으로 변환

					 // 입력한 개봉일자 가져오기
					 yy = str.substring(0, 4);
					 mm = str.substring(5, 7);
					 dd = str.substring(8, 10);

					 String hi = str.substring(4, 5);	// 입력 양식 비교를 하기위해 
					 
					 if (!hi.equals("-"))			// 하이픈이 맞는지
						flag = true;

					 hi = str.substring(7, 8);	// 입력 양식 비교를 하기위해 
					 
					 if (!hi.equals("-"))			// 하이픈이 맞는지
						flag = true;

					 //System.out.println("\t"+mm+"-"+dd);
					 // 정수형으로 변경
					 oyy = Integer.parseInt(yy);
					 omm = Integer.parseInt(mm);	// 변경될 개봉 월 정수형 변환
					 odd = Integer.parseInt(dd);	// 변경될 개봉 일 정수형 변환

					 break;
				}
				catch (NumberFormatException e) // 문자열을 숫자로 변환시 문자열의 값이 숫자가 아니라면 
				{
					 System.out.println("\n\t>> 잘못 입력하셨습니다. 입력 형식에 맞춰 입력해주세요.\n");
				}
				catch (StringIndexOutOfBoundsException so)	// 문자열의 substring 중 글자수가 부족하다면
				{
					 System.out.println("\n\t>> 잘못 입력하셨습니다. 입력 형식에 맞춰 입력해주세요.\n");
				}
			 }
			 
			 if (oyy > eyy)
				 flag = true;

			 if (omm<1 || omm>12)
				 flag = true;
			 if (odd>31)
				 flag = true;
			 else if (omm==2 && odd>28)
				 flag = true;
			 else if ((omm==4 || omm==6 || omm==9 || omm==11) && odd>30)
				 flag = true;
			 
			 if (oyy==eyy)
			 {
				 if (omm>emm)
					flag = true;
				else if (omm==emm && odd>edd)
					flag = true;
			 }
			 

			 t++;
		 }
		 while (flag);
         
         ht.get(key).setmOpenDate(str);		// 유효성 검사에 문제가 없다면 실행됨
         ht.get(key).getmOpenDate();
         ht.replace(key, ht.get(key));

		 System.out.print("\n\t>> 개봉 일자가 정상적으로 수정 완료되었습니다.");
      }
	 
	  // do while문을 돌기 위해 초기화 
	  t = 0;

      if (sel==4)
      {
		 System.out.println("\n\t== 현재 종영일자 : " + ((MovieVO)ht.get(key)).getmEndDate() + " ==");

		 do
		 {
			 // flag = false; // do while문을 돌기 위해 초기화 

			 if (t>0)
				System.out.println("\n\t>> 입력한 일자가 유효하지 않습니다.");
			 
			 while (true)
			 {
				 flag = false;

				 try
				 {
					System.out.print("\t>> 수정할 종영 일자를 입력하세요 (YYYY-MM-DD) : ");
					 str = sc.next();
					 // 영화 관리 메뉴로 이동, 메소드 종료
					 if (str.equals("-1"))
						return;

					 // 입력한 개봉일자 월만 가져오기
					 String hoTime = ((MovieVO)ht.get(key)).getmOpenDate();		// 해당 key의 개봉일 가져오기 (개봉일이 종영일 후에 있다면 등록되지않게 하기 위해)
					 yy = hoTime.substring(0, 4);
					 mm = hoTime.substring(5, 7);
					 dd = hoTime.substring(8, 10);
					 
					 oyy = Integer.parseInt(yy);
					 omm = Integer.parseInt(mm);
					 odd = Integer.parseInt(dd);
					 
					 //System.out.println("\t"+mm+"-"+dd);
					 // 정수형으로 변경
					 yy = str.substring(0, 4);
					 mm = str.substring(5, 7);
					 dd = str.substring(8, 10);
					 
					 eyy = Integer.parseInt(yy);
					 emm = Integer.parseInt(mm);
					 edd = Integer.parseInt(dd);

					 String hi = str.substring(4,5);
					 
					 if (!hi.equals("-"))
						flag = true;

					 hi = str.substring(7, 8);	// 입력 양식 비교를 하기위해 
					 
					 if (!hi.equals("-"))			// 하이픈이 맞는지
						flag = true;

					 break;
				 }
				 catch (NumberFormatException e) // 문자열을 숫자로 변환시 문자열의 값이 숫자가 아니라면 
				 {
					 System.out.println("\n\t>> 잘못 입력하셨습니다. 입력 형식에 맞춰 입력해주세요.\n");
				 }
				 catch (StringIndexOutOfBoundsException so)	// 문자열의 substring 중 글자수가 부족하다면
				 {
					 System.out.println("\n\t>> 잘못 입력하셨습니다. 입력 형식에 맞춰 입력해주세요.\n");
				 }
			 }
			 
			 if (oyy > eyy)
				 flag = true;

			 if (emm<1 || emm>12)
				 flag = true;

			 if (edd>31)
				 flag = true;
			 else if (emm==2 && edd>28)
				 flag = true;
			 else if ((emm==4 || emm==6 || emm==9 || emm==11) && edd>30)
				 flag = true;
			 
			 if (oyy == eyy)
			 {
			 
				 if (omm>emm)	 
					 flag = true;

				 if (omm==emm && odd>edd)
					 flag = true;
			 }

			 t++;
		 }
		 while (flag);
      
         ht.get(key).setmEndDate(str);
         ht.get(key).getmEndDate();
         ht.replace(key, ht.get(key));

		 System.out.print("\n\t>> 종영 일자가 정상적으로 수정 완료되었습니다.");
      }

      if (sel==5)
      {
		 System.out.println("\n\t== 현재 줄거리 : " + ((MovieVO)ht.get(key)).getmStory() + " ==");
         System.out.print("\t>> 수정할 줄거리를 입력하세요 : ");
         sc.nextLine();
         str = sc.nextLine();
		 // 영화 관리 메뉴로 이동, 메소드 종료
		 if (str.equals("-1"))
			return;

         ht.get(key).setmStory(str);
         ht.get(key).getmStory();
         ht.replace(key, ht.get(key));

		 System.out.print("\n\t>> 줄거리가 정상적으로 수정 완료되었습니다.");
      }

      if (sel==6)
      {
		 System.out.println("\n\t== 현재 줄거리 : " + ((MovieVO)ht.get(key)).getmAge() + " ==");

		 while (true)
		 {
			 try
			 {
				System.out.print("\t>> 관람 가능 연령을 입력하세요 : ");
			    n = sc.nextInt();

				break;
			 }
			 catch (InputMismatchException e)
			 {
				 sc = new Scanner(System.in);
				System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
			 }
		 }
         
		 // 영화 관리 메뉴로 이동, 메소드 종료
		 if (n==-1)
			return;

         ht.get(key).setmAge(n);
         ht.get(key).getmAge();
         ht.replace(key, ht.get(key));

		 System.out.print("\n\t>> 관람 가능 연령 정상적으로 수정 완료되었습니다.");
      }

      if (sel==7)
      {
		 System.out.println("\n\t== 현재 총 상영시간 : " + ((MovieVO)ht.get(key)).getmTime() + "분 ==");

		 while (true)
		 {
			 try
			 {
				System.out.print("\t>> 총 상영시간을 입력하세요 : ");
			    n = sc.nextInt();

				break;
			 }
			 catch (InputMismatchException e)
			 {
				 sc = new Scanner(System.in);
				System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
			 }
		 }
        
		 // 영화 관리 메뉴로 이동, 메소드 종료
		 if (n==-1)
			return;

         ht.get(key).setmTime(n);
         ht.get(key).getmTime();
         ht.replace(key, ht.get(key));

		 System.out.print("\n\t>> 총 상영시간이 정상적으로 수정 완료되었습니다.");
      }

      if (sel==8)
      {
		 System.out.println("\n\t== 현재 평점 : " + ((MovieVO)ht.get(key)).getmStar() + " ==");

		 while (true)
		 {
			 try
			 {
				System.out.print("\t>> 평점을 입력하세요 : ");
			    n = sc.nextInt();

				break;
			 }
			 catch (InputMismatchException e)
			 {
				 sc = new Scanner(System.in);
				System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
			 }
		 }
         
		 // 영화 관리 메뉴로 이동, 메소드 종료
		 if (n==-1)
			return;

         ht.get(key).setmStar(n);
         ht.get(key).getmStar();
         ht.replace(key, ht.get(key));

		 System.out.print("\n\t>> 평점이 정상적으로 수정 완료되었습니다.");
      }
      
   } // end chaMoive() 등록된 영화 정보 변경 메소드 

   // 영화 삭제 메소드
   public static void delMoive() 
   {
		Scanner sc = new Scanner(System.in);
		
		String key;
		
		System.out.println("\n\n");
		System.out.println("\t등록 영화 삭제				      -1. 영화 관리 메뉴");
		System.out.println("\t================================================================");
		System.out.print("\t>> 삭제할 영화 제목을 입력하세요 : ");
		key = sc.nextLine();
		// 영화 관리 메뉴로 이동, 메소드 종료
		if (key.equals("-1"))
			return;

		if (ht.containsKey(key))		// Key 값이 있으면 if문 실행
	    {
			ht.remove(key);				// 영화정보를 담은 Hashtable에서 해당 내용 삭제 
			System.out.print("\n\t>> 정상적으로 삭제되었습니다.");
	    }
		else 
			System.out.println("\n\t>> 선택하신 영화는 존재하지않습니다.");


   } // end delMoive()
   
  
  // 상영정보 등록 메소드
  public static void registerScreen()
  {
	 Scanner sc = new Scanner(System.in);
	 Calendar cal = Calendar.getInstance();

	 String key="";					// 영화 제목을 입력 받을 변수
	 int sel;						// 선택 변수 
	 int t=0;						// 루프?변수
	 String date="";
	 String room="";
	 int d, sh, sm;					// 일자와 시작 시간과 분을 정수형으로 담을 변수	→ 처음 시간과 일을 정수로 받는 이유 endTime 계산때문에..
	 String ssh, ssm;				// 시작 시간과 분을 문자형으로 담을 변수 
	 String strTime;				// 입력받은 시간 정수를 문자열로 변환하여 담을 변수 
	 Integer sTime=0, eTime=0;		// 문자열로 변환한 시간 정소로 변환
	 int eh, em;					// 끝나는 시간과 분을 담을 변수
	 String seh, sem;				// 끝나는 시간과 분을 문자열로 
	 Integer serial;				// hashtable 키값을 담을 변수 
	 String strSerial;				// hashtable 키값을 꺼내올 변수
	 boolean flag;				    // 유효성 검사를 위한 flag
	 String yy, mm, dd;					// 월과 일을 담은 문자열 변수 
	 int oyy, omm, odd, pyy, pmm, pdd;	    // 개봉, 상영일자 유효성 검사를 위한 변수
	 char[][] seat= new char[0][0];
	 

	 // -상영정보 등록
	 System.out.println("\n\n");
	 System.out.println("\t상영 리스트 등록			      -1. 영화 관리 메뉴");
	 System.out.println("\t================================================================");
	 
	 // 날짜 받아오기
	 Date today = cal.getTime();					
	 cal.add(Calendar.DAY_OF_WEEK, 1);
	 Date tomorrow = cal.getTime();
	 cal.add(Calendar.DAY_OF_WEEK, 1);
	 Date thedayAfter = cal.getTime();
	 
	 SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");

	
	 // 상영 일자 선택
	 do
	 {
		 if (t>0)
			 System.out.println("\t>> 유효하지 않은 메뉴입니다. 다시 선택해주세요."); 
		 while (true)
		 {
			 try
			 {
				System.out.println("\t 1. "+(dformat.format(today)));
				System.out.println("\t 2. "+(dformat.format(tomorrow)));
				System.out.println("\t 3. "+(dformat.format(thedayAfter)));
				System.out.println();
				System.out.print("\t>> 상영일자를 선택해주세요 : ");
				sel = sc.nextInt();

				break;
			 }
			 catch (InputMismatchException e)
			 {
				sc = new Scanner(System.in);
				System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
			 }
		 }
		 
		 // 영화 관리 메뉴로 이동, 메소드 종료
		 if (sel==-1)
			return;

		 t++;
	 }
	 while (sel>3 || sel<1);
	 
	 strSerial = String.valueOf(sel);		// 첫번째자리 시리얼 생성을 위해, 문자열로 변환하여 선택값을 받음	

	 // 선택된 상영일 변수에 담기
	 switch (sel)
	 {
	    case 1: date = (dformat.format(today)); break;
		case 2: date = (dformat.format(tomorrow)); break;
		case 3: date = (dformat.format(thedayAfter)); break;
	 }
	 
	 // 개봉 일자와 상영일 유효성 검사를 위해 (상영일자)
	 yy = date.substring(0, 4);
	 mm = date.substring(5, 7);				// 입력받은 개봉 월만 mm 변수에 담기
	 dd = date.substring(8, 10);				// 입력받은 개봉 일만 dd 변수에 담기

	 // 정수형으로 변환
	 pyy = Integer.parseInt(yy);
	 pmm = Integer.parseInt(mm);			
	 pdd = Integer.parseInt(dd);
	 
	 t=0;		// 다음 do while 문을 돌기 위한 초기화 
	 System.out.println("\n");
	
	 // 상영관 선택
	 do
	 {
		 if (t>0)
		 {
			 System.out.println("\t>> 유효하지 않은 메뉴입니다. 다시 선택해주세요."); 
			 System.out.println();
		 }
		 
		 while (true)
		 {
			 try
			 {
				System.out.println("\t1. 1관(2D)");
				System.out.println("\t2. 2관(2D)");
				System.out.println("\t3. 3관(4D)");
				//System.out.println("\t4. 4관(4D)");
				System.out.println();
				System.out.print("\t>> 상영관을 선택해주세요 : ");
				sel = sc.nextInt();

				break;
			 }
			 catch (InputMismatchException e)
			 {
				sc = new Scanner(System.in);
				System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
			 }
		 }
		 
		 // 영화 관리 메뉴로 이동, 메소드 종료
		 if (sel==-1)
			return;

		 t++;
	 }
	 while (sel>4 || sel<1);

	 strSerial += String.valueOf(sel)+"0";	// 두번째,세번째 시리얼 생성을 위해, 기존 시리얼 값에 선택값과 0을 문자열로 변환하여 합침
	 serial = Integer.parseInt(strSerial);	// 일자, 상영관 세팅됨 ex) 110 or 230... 시간 등록 후 등록 순서 + 해줄거임..
	 
	 // 선택된 상영관 변수에 담기
	 switch (sel)
	 {
		case 1: room = "1관(2D)"; seat = settingSeat(1); break;
		case 2: room = "2관(2D)"; seat = settingSeat(2); break;
		case 3: room = "3관(4D)"; seat = settingSeat(3); break;
		//case 4: room = "4관(4D)"; break;
	 }
	 
	 sc.nextLine();
		
	 System.out.println();

	 int s = 0;	// 밖의 do while문을 돌기위한 루프변수?선언과 초기화 

	 Set<Integer> ks = ht2.keySet();
	 Iterator<Integer> ik = ks.iterator();

	 System.out.println();
	 System.out.println("\n\t			[ 상영 현황 ]");
	 System.out.println("\t================================================================");
	 while (ik.hasNext())
	 {
		 Integer pkey = ik.next();

		 if (date.equals(((ScreenVO)ht2.get(pkey)).getmDate()) && room.equals(((ScreenVO)ht2.get(pkey)).getmRoom()))
		 {
			 //System.out.println("\tserialNum."+pkey);
			 //System.out.println("\t==============================");
			 System.out.println(ht2.get(pkey));
			 System.out.println("\t================================================================");
			 s++;
		 }
	 }
	 if (s == 0)
		System.out.println("\n\t================================================================");
	 
	 System.out.println("\n");

	 int j = 0;	// 밖의 do while문을 돌기위한 루프변수?선언과 초기화 

	 // 스케줄링할 영화 선택 
	 do
	 {
		 flag = false;	// do while문을 돌기 위한 초기화 
		 t = 0;			// 밑 do while문을 돌기 위한 초기화
		 if (j>0)
			System.out.println("\n\t>> 영화 개봉일 전에는 상영이 불가능합니다."); 
		 do
		 {
			 if (t>0)
			 {
				 System.out.printf("\n\t>> %s 는 등록되지 않은 영화 입니다.\n", key); // 해당 do while문이 한번 이상 반복되면 해당 메세지 출력
				 System.out.println();
			 }

			 System.out.print("\t>> 상영할 영화 제목을 입력하세요 : ");
			 key = sc.nextLine();
			 // 영화 관리 메뉴로 이동, 메소드 종료
			 if (key.equals("-1"))
				return;

			 t++;
		 }
		 while (!ht.containsKey(key));	// 선택한 영화가 있는지 없는지 검사 없으면 do while 다시 실행하여 받아오기 

		String kot = ((MovieVO)ht.get(key)).getmOpenDate();		// 선택한 영화 개봉일 가져오기 
		
		yy = kot.substring(0, 4);
		mm = kot.substring(5, 7);
		dd = kot.substring(8, 10);
		
		oyy = Integer.parseInt(yy);
		omm = Integer.parseInt(mm);
		odd = Integer.parseInt(dd);

		if (pyy < oyy)
		{
			flag = true;
		}

		if (pyy == oyy)
		{
			if (omm>pmm)					// 해당 개봉월이 상영월보다 크다면 
				flag = true;
			else if (omm==pmm && odd>pdd)	// 해당 개봉월과 상영월이 같을때 개봉일이 상영일보다 크다면 
				flag = true;

		}

		j++;

	 }
	 while (flag);
	 
	 j=t=0; // 다음 do while 문을 돌기 위한 초기화 
	 
	 System.out.println();

	 // 상영 시작 시간 입력
	 do
	 {
		 flag = false;

		 if (t>0)
		 {
			 System.out.println("\t>> 해당 시간은 이미 선택되어 있습니다.");
			 System.out.println();
		 }
		 
		 while (true)
		 {
			 try
			 {
				do
				{
					if (j>0)
						System.out.println("\t>> 입력하신 시간은 유효하지 않습니다.");

					System.out.print("\t>> 상영 시작 시간을 입력해주세요 (시간만 입력) : ");
					sh = sc.nextInt(); 

					j++;
				}
				while (sh>24 || sh<-1);

				break;
			 }
			 catch (InputMismatchException e)
			 {
				sc = new Scanner(System.in);
				System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
			 }
		 }
		 
		 // 영화 관리 메뉴로 이동, 메소드 종료
		 if (sh==-1)
			return;
		 
		 j = 0;
		 while (true)
		 {
			 try
			 {
				 do
				 {
					 if (j>0)
						System.out.println("\t>> 입력하신 시간은 유효하지 않습니다.");

					System.out.print("\t>> 상영 시작 시간을 입력해주세요 (분만 입력)   : ");
					sm = sc.nextInt();

					j++;
				 }
				 while (sm>59 || sm<-1);
				
				break;
			 }
			 catch (InputMismatchException e)
			 {
				sc = new Scanner(System.in);
				System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
			 }
		 }
		 //System.out.print("\t>> 상영 시작 시간을 입력해주세요 (분만 입력)   : ");
		 //sm = sc.nextInt();
		 
		 // 영화 관리 메뉴로 이동, 메소드 종료
		 if (sm==-1)
			return;
		 
		 strTime = String.valueOf(sh);  // 입력받은 상영 시작 시간을 문자열로 변환
		 if (sm<10)						// 입력받은 상영 시작 분이 일의 자리라면
			 strTime += "0"+String.valueOf(sm);	// 문자열로 변환 후 0을 더하고 시간이 담긴 변수에 더해주기 
		 else 
			 strTime += String.valueOf(sm);	// 문자열로 변환 후 시간이 담긴 변수에 더해주기
		 
		 sTime = Integer.parseInt(strTime); // 시간 비교를 위해 정수형으로 변환 (상영 시작 시간)

		 // 해당 영화의 러닝타임 가져오기 (상영종료 시간을 구하기 위해)
		 int runtime = ((MovieVO)ht.get(key)).getmTime();		// 상영 총 시간 가져오기 
		
		 // 상영종료 시간 구하기 
		 eh = sh+(runtime/60);	// 입력 받은 상영시작 시간과 총상영시간중 시간을 구한 값을 더해 eh 변수에 넣기
		 em = sm+(runtime%60);	// 입력 받은 상영시작 분과 총상영시간중 분을 구한 값을 더해 em 변수에 넣기 
		 
		 if (em>=60)			// em이 60이 넘는다면
		 {
			 eh += (em/60);		// 시간을 구해 eh 변수에 더해주기 
			 em %= 60;			// 분을 구해 em 변수에 넣어주기 
		 }
		 
		 // hashtable에 담을 상영시작 시간
		 if (sh<10)
			ssh = "0"+String.valueOf(sh);
		 else 
			ssh = String.valueOf(sh);
		 
		 // hashtable에 담을 상영시작 분
		 if (sm<10)
			ssm = "0"+String.valueOf(sm);
		 else
			ssm = String.valueOf(sm);	
		 
		 //System.out.println("\t"+ssh+":"+ssm);
		 
		 // hashtable에 담을 상영종료 분
		 if (eh<10)
			seh = "0"+String.valueOf(eh);
		 else 
			seh = String.valueOf(eh);
		 
		 // hashtable에 담을 상영종료 분
		 if (em<10)
			sem = "0"+String.valueOf(em);
		 else
			sem = String.valueOf(em);

		 // 테스트 확인
		 //System.out.println("\t"+eh+em);

		 strTime = String.valueOf(eh);				// 문자열로 저장 (Hashtable 추가를 위해)
		 if (em<10)
			 strTime += "0"+String.valueOf(em);
		 else 
			 strTime += String.valueOf(em);

		 eTime = Integer.parseInt(strTime);			// 시간 비교를 위해 정수 형으로 저장(상영종료시간)
         
		 // 시간 중복 여부를 검사하기 위한 생성
		 Set<Integer> keySet = ht2.keySet();
		 Iterator<Integer> iterKey = keySet.iterator(); 
		 
		  while (iterKey.hasNext())
		  {
			 // keys에다 기존 serial 키 담기
			 Integer keys = iterKey.next();
			 Integer seK = (keys/10);			// ht2(상영스케줄) Hashtable key 값/10	// 선택한 일자와 관의 상영스케줄을 비교하기 위해
			 //System.out.println("\t"+keys);
			  
			 Integer Kse = (serial/10); // 등록할 영화의 serail/10

			 if (Kse == seK)
			 {	
				 // 같은 상영일자, 상영관의 시작시간, 종료시간 받아와 정수형으로 변경
				 strTime = ((ScreenVO)ht2.get(keys)).getmSh();
				 strTime += ((ScreenVO)ht2.get(keys)).getmSm();
				 Integer startTime = Integer.parseInt(strTime);

				 strTime = ((ScreenVO)ht2.get(keys)).getmEh();
				 strTime += ((ScreenVO)ht2.get(keys)).getmEm();
				 Integer endTime = Integer.parseInt(strTime);
				
				 // 시간 중복 여부 검사 
				 if ((sTime>=startTime) && (eTime<=endTime))	// 선택한 시작 시간이 기본 스케줄에 등록된 시간과 같거나 클때 상영종료시간이 스케줄에 들어있는 종료시간보다 작거나 같다면  
				 {
					flag = true;
					break;
				 }
				 else if ((sTime<startTime) && (eTime>startTime) && (eTime<=endTime || eTime>endTime))	// 선택한 시간이 기본 스케줄에 등록된 시간보다 작고, 상영종료 시간이 시작시간보다 클때
			  	 {																						// 상영종료 시간이 기본 스케줄 시간과 같거나 작다면 또는 상영종료시간이 기존 상영종료시간보다 크다면
					flag = true;
					break;
				 }
				 else if (sTime>=startTime && sTime<=endTime)		// 선택한 시작 시간이 스케줄에 등록된 시간과 같거나 크고 시작 신가이 스케줄에 등록된 상영종료시간과 같거나 작다면
				 {
					flag = true;
					break;
				 }
			 }
		 } // 시간 중복확인을 위해 while문 

		 t++;
	 }
	 while (flag);	// 상영 시간 입력 받는 do while문 
	 
	 System.out.println("\t>> 스케줄이 정상 등록 완료되었습니다.");
	 
	 s++;
	 serial += s;
	 
	 ht2.put(serial,(new ScreenVO(date, room, key, ssh, ssm, seh, sem, seat)));		// 상영스케줄 Hatshtable에 스케줄 생성
     // 추가 ★
     rmNum=Integer.toString(serial);
     rmNum=rmNum.substring(1,2);
	 //ldate = Integer.parseInt(date);//k
	 //ht3.put(date+" "+ssh+":"+ssm+"~"+seh+":"+sem, key); //key는 제목 
     // 추가 ★
     //rm1.put(date+" "+ssh+":"+ssm+"~"+seh+":"+sem, rmNum);

	 
  } // registerScreen() 상영정보 등록 메소드

  // 상영관 현황 출력 메소드
  public static void printScreen()
  {
	 Set<Integer> keySet = ht2.keySet();
	 Iterator<Integer> iterKey = keySet.iterator();

	 System.out.println();
	 System.out.println("\n\t			[상영 현황 전체 출력]");

	 while (iterKey.hasNext())
	 {
		 Integer key = iterKey.next();
		 System.out.println();
		 System.out.println("\tserialNum."+key);
		 System.out.println("\t================================================================");
		 System.out.println(ht2.get(key));
	 }
  }	// end printScreen() 상영관 현황 출력 메소드

  // 상영정보(상영 스케줄)를 삭제하는 메소드
  public static void delScreen()
  {
	 Scanner sc = new Scanner(System.in);
	 
	 Integer serial;

	 System.out.println("\n");
	 System.out.println("\t상영 리스트 삭제		              -1. 영화 관리 메뉴");
	 System.out.println("\t================================================================");
	 System.out.print("\t>> 삭제할 리스트의 시리얼넘버를 입력하세요 : ");
	 serial = sc.nextInt();
	 // 영화 관리 메뉴로 이동, 메소드 종료
	 if (serial==-1)
			return;

	 if (ht2.containsKey(serial))		// 상영스케줄 Hashtable에 해당 key가 있다면 
	 {
		 ht2.remove(serial);			// 해당 내용을 삭제해라 
		 System.out.println("\n\t>> 정상적으로 삭제되었습니다.");
	 }
	 else 
		System.out.println("\t>> 입력하신 시리얼은 유효하지않습니다.");
  } // end delScreen()
  
  // 좌석 리스트
  public static char[][] settingSeat(int sel)
  {
	char[][] seat;

	if (sel == 1 || sel == 2)
	{
		seat = new char[10][12];

		int n = 0;
		// 1, 2관 좌석 입력
		for (int i=0; i<seat.length; i++)
		{
			for (int j=0; j<seat[0].length; j++)
			{
				if (n==1 || n==4 || n==7)
				{
					seat[i][j] = '▩';
					
					n++;

					if (n==8)
						n=0;
				}
				else 
				{
					seat[i][j] = '□';
					n++;
				}	
			}
		}
	}
	else
	{
		seat = new char[4][8];

		int n = 0;
		// 3관 좌석 입력
		for (int i=0; i<seat.length; i++)
		{
			for (int j=0; j<seat[0].length; j++)
			{
				if (n==1 || n==4 || n==7)
				{
					seat[i][j] = '▩';
					
					n++;

					if (n==8)
						n=0;
				}
				else 
				{
					seat[i][j] = '□';
					n++;
				}	
			}
		}
	}

	return seat;
  }
} // end class AdminMovie