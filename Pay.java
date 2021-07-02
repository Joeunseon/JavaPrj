import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.Random;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.lang.Thread;
import java.io.IOException;

import java.util.InputMismatchException;

//import java.util.NumberFormatException;

//==================================================================================================================

/*
	Title	 : Pay
	Function : 티켓 가격 및 할인 적용, 결제수단 입력 및 결제 결과 출력, 로딩 및 광고 출력, 영화표/교환권/영수증 출력  
	author	 : 김서현, 심혜진
*/

//==================================================================================================================

// * 수정사항*
// 21-06-06 selMem() 메소드 예외처리 진행
// 21-06-13 클래스 전체 예외 처리 진행
//          PayDisc 클래스 기존 회원 정상 조회 가능
//          처음화면 / 이전화면 기능 추가
//          결제 완료 후 정산 + 사용할 포인트 업데이트 진행하도록 변경
//          예매한 좌석 선택 불가로 변경 추가 

class RsvNumbVO
{
	// 사용자가 예매를 완료하고 선택이 된 정보들에 대한 변수
	public static String rMem;			// 예매번호	
	public static String rMovie;		// 영화 제목	
	public static String cSeat;			// 좌석번호
	public static String lastTimeTable;	// 상영시간
    public static String rmNum;         // 상영관


   //예매번호, 제목, 상영날짜, 좌석번호
   RsvNumbVO(String rMem, String rMovie, String cSeat, String lastTimeTable, String rmNum)
   {
      this.rMem = rMem;
      this.rMovie = rMovie;
      this.cSeat = cSeat;
      this.lastTimeTable = lastTimeTable;
      this.rmNum = rmNum;
   }

   @Override
   public String toString()
   {
	  return "\n\t예매번호 : "+rMem+"\n\t영화 제목 : "+rMovie+"\n\t상영관 : "+rmNum+"1관\n\t좌석 : "+cSeat+"\n\t상영시간 : "+lastTimeTable+            // ★ 변경
         "\n\n\t====================================";   																			
   }	
}

// 결제 클래스 → 1. 할인에 관한 정보 제공, 적용하는 클래스 / 2. 할인 적용된 최종 금액으로 결제 진행하는 클래스 2가지로 분리
// 1. PayDisc 회원/비회원 확인 및 할인 적용, 적립금 사용
class PayDisc extends Record
{
	//public static ReserveTiketsVO rt = new ReserveTiketsVO();
	public static int index;

	// 결제 진행하기 전 회원/비회원 확인 메소드
	public static void selMem() throws InterruptedException,IOException
	{
		sc = new Scanner(System.in);

		int n = 0;
		
		System.out.println("\n\n\t                        00.초기화면 01.이전화면");
		System.out.print("\t===============================================");
		System.out.print("\n\t\t    *결제 진행 전 회원 확인*  ");
		System.out.print("\n\t-----------------------------------------------");
		System.out.print("\n\t              1. 회원 | 2. 비회원");
		System.out.println("\n\t===============================================");

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
				answer = sc.next();
				
				if (answer.equals("00"))
				{
					moviePayTot = 0;
					return;
				}

				if (answer.equals("01"))
				{	
					ReserveTikets.printMal();
					return;
				}
				n++;

				sel = Integer.parseInt(answer);

				break;
			  }
			  catch (NumberFormatException e)
			  {
				  sc = new Scanner(System.in);
				  System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
			  }
		  }
		  
		}
		while (sel>2 || sel<1);
		
		
		recogMem();
		 
	} // end selMem 회원/비회원 선택 메소드


	// 회원번호(전화번호) 등록되어있는 정보인지 판별
	public static void recogMem() throws InterruptedException,IOException
	{
		if (sel==1) // 회원일 때
		{
			Scanner sc = new Scanner(System.in);

			System.out.println("\n\n\t[회원 정보 확인]                         00.처음화면 01.이전화면");
			System.out.println("\t================================================================");
			
			String tel = "";
			while (true)
		    {
			  try
			  {
				  

				  System.out.print("\t>> 전화번호를 입력하세요(xxx-xxxx-xxxx) : ");
				  tel = sc.next();

				  // 00 을 입력하면 메소드 종료하여 사용자메인메뉴로 이동
				  if (tel.equals("00"))
				  {
					 moviePayTot = 0;
					 return;
				  }
				  // 01 을 입력하면 메소드 종료하여 이전메뉴로 이동
				  if (tel.equals("01"))
				  {
					selMem();
					return;
				  }

				  int ck = Integer.parseInt(tel.substring(0, 3));
				  ck += Integer.parseInt(tel.substring(4, 8));
				  ck += Integer.parseInt(tel.substring(9));

				  break;
			  }
			  catch (NumberFormatException e)  // 글자수는 맞으나 x 부분이 숫자가 아니라면
			  {
				  System.out.println("\t>> 잘못 입력하셨습니다. 입력 형식에 맞춰 입력해주세요.\n");
			  }
			  catch (StringIndexOutOfBoundsException so)	// 문자열의 substring 중 글자수가 부족하다면
			  {
				  System.out.println("\t>> 잘못 입력하셨습니다. 입력 형식에 맞춰 입력해주세요.\n");
			  }
			}

			search(tel); // 회원인지 확인 위해 입력받은 전화번호 search() 메소드에 넘겨주고
		}
		else if(sel==2)
			noMemAge(); // 비회원 메소드 호출
			//System.out.println("\t비회원");

	} // end recogMem 회원 확인 메소드

	// 회원으로 확인된 후 할인 및 적립금 사용 안내 메소드
	public static void search(String tel) throws InterruptedException,IOException
	{
		Scanner sc = new Scanner(System.in);
		AdminMovie am = new AdminMovie();
		ReserveTikets rt = new ReserveTikets();

		//Members.oldMem(); // Members에 등록되어있는 회원 정보 기반으로 확인하기 위해 호출
						  // Members.mData는 회원들의 정보를 담고있는 ArrayList > 사이즈 확인(회원 수만큼 반복)
		//System.out.println("\ttest" +((MembersVO)Members.mData.get(0)).getTel());
		index = 0;
		boolean flag = false;
		String str = "";
		for (int i = 0; i < Members.mData.size() ; i++) 
        {
			// ArrayList에 저장되어있는 값들 중 전화번호 불러오기
			str = ((MembersVO)Members.mData.get(i)).getTel();
			// ArrayList의 Tel과 입력한 Tel이 같으면
			// 그 Tel을 가진 회원 정보를 모두 출력
			if (tel.equals(str)) 
			{
				flag = true;
				index = i;
				break;
			}
	    }
		
		if (flag)
		{
		
			String name = ((MembersVO)Members.mData.get(index)).getName(); // 인사말에 넣기 위한 이름 get해오기
			System.out.println("\n\n\t================================================================");
			System.out.printf("\t                   %s 회원님 안녕하세요!\n", name);
			
			// 청불 영화 라면 나이 확인
			if (((MovieVO)am.ht.get(rt.title)).getmAge()>=19)
			{
				//System.out.println("test");
				String ssn = ((MembersVO)Members.mData.get(index)).getId();
				
				Calendar cal = Calendar.getInstance();
				
				// 나이변수 
				int age = 0;
				if (Integer.parseInt(ssn.substring(0, 1)) == 0)
					age = cal.get(Calendar.YEAR) - (2000 + Integer.parseInt(ssn.substring(0, 2)));
				else 
					age = cal.get(Calendar.YEAR) - (1900 + Integer.parseInt(ssn.substring(0, 2)));

				if (age < 19)
				{
					System.out.println("\t선택하신 영화는 청소년 관람 불가 영화입니다.");
					System.out.println("\t어린이 & 청소년 고객님은 예매가 불가합니다.");
					System.out.println("\t================================================================");
					return;
				}
				
			}

			System.out.println("\t================================================================");

			// 해당 회원이 가지고 있는 포인트(getPoint)가 2000원이 넘는다면 사용 가능하다는 조건 설정
			if (((MembersVO)Members.mData.get(index)).getPoint()>=2000)
			{
				// 적립금 사용할 시 차감하고 다시 반여할 수 있는 usePoint 변수
				int usePoint = ((MembersVO)Members.mData.get(index)).getPoint(); // getPoint로 알아온 포인트를 usePoint에 저장
				System.out.println("\n\n\t[적립금 사용 확인]                       00.처음화면 01.이전화면");
				System.out.println("\t================================================================");
				System.out.printf("\t현재 적립금 [%d원] 남아있습니다.", usePoint);
				int n = 0;
				do
				{	
					if (n>0)
						System.out.println();

					System.out.print("\n\t>> 사용하시겠습니까? (Y/N) : ");
					answer = sc.next();

					// 00 을 입력하면 메소드 종료하여 사용자메인메뉴로 이동
					if (answer.equals("00"))
					{
						moviePayTot = 0;
						return;
					}

					// 01 을 입력하면 메소드 종료하여 이전메뉴로 이동
					if (answer.equals("01"))
					{
						recogMem();
						return;
					}

					n++;
				}
				while (!answer.equals("y") && !answer.equals("Y") && !answer.equals("n") || answer.equals("N"));
				
				n = 0;
				if (answer.equals("y") || answer.equals("Y"))
				{
					do
					{
						while (true)
						{
							try
							{
								if (n>0)
									System.out.print("\t>> 잘못 입력하셨습니다. 다시 선택해주세요.");
								System.out.print("\n\t>> 사용할 적립금을 입력해주세요. : ");
								answer = sc.next();

								// 00 을 입력하면 메소드 종료하여 사용자메인메뉴로 이동
								if (answer.equals("00"))
								{
									moviePayTot = 0;
									return;
								}
								// 01 을 입력하면 메소드 종료하여 이전메뉴로 이동
								if (answer.equals("01"))
								{
									recogMem();
									return;
								}

								su = Integer.parseInt(answer);

								n++;

								break;
							}
							catch (NumberFormatException e)
							{
								System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해주세요.\n");
							}
						}
					}
					while (su>usePoint || su<0);
					
					usePoint -= su; // 적립금에서 사용 적립금 빼기
					//(Members.mData).get(i).setPoint(usePoint); // 적립금 차감한 금액 다시 set 해주기
					//System.out.print("적립금 확인 : " + usePoint);
					System.out.printf("\t>> [%d]원 사용 완료했습니다. (남은 적립금 : %d)\n", su, usePoint);

					// 문화의 날 할인 > 스낵 추가 구매로
					Pay.selSnack();
					//System.out.println("적립금 사용. 스낵구매호출");
				}
				else
					Pay.selSnack();
					//System.out.println("적립금 사용안함. 스낵구매호출");
			}
			else // 적립금 2000원보다 적으면 사용불가라서 바로 스낵 추가구매로 넘어감
				Pay.selSnack(); 
				//System.out.println("적립금 사용불가. 스낵구매호출");
		}	
		else
		{
			System.out.println("\n\n\t>> 존재하지 않는 회원입니다.");
			//break; // 여길 빠져나가면 다시 회원/비회원 선택하는 recogMem() 메소드로
			recogMem();
			return;
		}
	} // end search

	// 비회원 연령 확인 메소드
	public static void noMemAge() throws InterruptedException,IOException
	{
		Scanner sc = new Scanner(System.in);
		//Set<String> keys = AdminMovie.ht.keySet(); // key값(영화 제목) 가져오기
		//Iterator<String> it = keys.iterator();	   // 해당 영화의 정보 확인하기 위해 Iterator 생성
		AdminMovie am = new AdminMovie();
		ReserveTikets rt = new ReserveTikets();
									   
		boolean flag = false;
		
		if (((MovieVO)am.ht.get(rt.title)).getmAge()>=19) // 사용자가 선택한 영화가 19세 관람 불가라면
		{
			System.out.println("\n\n\n\t성인 인증                             00. 초기화면 01. 이전화면");
			System.out.println("\t================================================================");
			System.out.println("\t선택하신 영화는 청소년 관람 불가 등급으로 성인 인증이 필요합니다."); // 주민번호 확인 안내멘트에 들어갈 영화이름 가져오기

			do // 유효성 검사를 위한 반복문
			{	
				while (true)
				{
					try
					{
						System.out.print("\t>> 주민등록번호를 입력하세요(xxxxxx-xxxxxxx) : ");
						answer = sc.next();

						if (answer.equals("00"))
						{
							moviePayTot = 0;
							return;
						}
						
						if (answer.equals("01"))
						{
							selMem();
							return;
						}

						int ck = Integer.parseInt(answer.substring(0, 6));
						ck += Integer.parseInt(answer.substring(6, 13));

						break;
					}
					catch (NumberFormatException e)  // 글자수는 맞으나 x 부분이 숫자가 아니라면
					{
						System.out.println("\t>> 잘못 입력하셨습니다. 입력 형식에 맞춰 입력해주세요.\n");
					}
					catch (StringIndexOutOfBoundsException so)	// 문자열의 substring 중 글자수가 부족하다면
					{
						System.out.println("\t>> 잘못 입력하셨습니다. 입력 형식에 맞춰 입력해주세요.\n");
					}
				}
				
				
				// 주민번호 유효성 검사 시작
				int[] chk = {2, 3, 4, 5, 6, 7, 0, 8, 9, 2, 3, 4, 5}; 
				int tot = 0;

				for (int i=0 ; i<13 ; i++) // i → 0 1 2 3 4 5 6 7 8 9 10 11 12
					{
						if (i==6)
							continue;			
						tot += chk[i] * Integer.parseInt(answer.substring(i,i+1));
					}
				
				int su = 11 - tot % 11;
				su = su % 10;
				// 주민번호 유효성 검사 끝
				int nowYear = Calendar.getInstance().get(Calendar.YEAR);

				if (su == Integer.parseInt(answer.substring(13))) // 유효한 주민번호라면
				{
					int birYear20;

					if (Integer.parseInt(answer.substring(0, 1)) == 0)
						birYear20 = Integer.parseInt("20" + answer.substring(0,2));
					else 
						birYear20 = Integer.parseInt("19" + answer.substring(0,2));

					if (nowYear-birYear20<19)
					{
						System.out.print("\n\t>> 죄송합니다. 해당 영화는 청소년 관람 불가로 예매를 진행할 수 없습니다.\n\t예매 초기화면으로 돌아갑니다.\n");
						return; // 예매 초기화면으로
					}	
					else // 연산이 19이상 나오면 관람 가능하니까 문화의 날 할인 > 스낵 추가 구매로
					{
						flag=false;
						System.out.println("\t>> 성인 인증이 완료되었습니다.");
					}
				}
				else
				{	// 존재하지 않는 주민번호 입력, flag는 true로 다시 반복문 실행
					flag=true;
					System.out.println("\n\t>> 존재하지 않는 주민번호입니다. 다시 입력하세요.\n\n");
				}
			} 
			while (flag);				
		} 
		
		Pay.selSnack();
		
	}


	
} // end PayDisc class 




// 2. 할인 적용된 금액 + 스낵 추가구매 확인 및 최종금액 결제 클래스
class Pay extends AdminCashVO
{
	// key값으로 예매번호를 담고 value값으로 RsvNumbVO형을 담는 nRsv 해시테이블 생성 
    public static Hashtable<String, RsvNumbVO> nRsv;

    static ReserveTiketsVO rt = new ReserveTiketsVO();
	static AdminCashVO ac = new AdminCashVO();

	static Date payToday = new Date();		//-- 결제시간 확인용
	static SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
	static SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
	static String payDate = date.format(payToday); //-- 결제한 날짜 담기
	static String payTime = time.format(payToday); //-- 결제한 시간 담기

	static int rtn = 0; //-- 예매번호 만들기위한 번호

	static int payTot; //-- 영화+스낵 합산 가격
	static int a, b, c, d, inputTot, chaCash; // 지폐를 입력받아 시재에 반영하기 위한 a,b,c,d 변수
											  // 입력한 값 총합, 거스름돈(changeCash)

	public static boolean flag = false;

   static 
   {
	   nRsv = new Hashtable<>();
   }

	// 스낵 추가 구매 확인 및 결제
	static void selSnack() throws InterruptedException,IOException
	{
		BuySnacks bst = new BuySnacks(); // 스낵 관련 VO 인스턴스 생성
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n\t추가 구매");
		System.out.println("\t================================================================");
		do
		{
			System.out.print("\t>> 팝콘/음료 추가 구매 하시겠습니까?(Y/N) : ");
			answer = sc.next();
			
			if (answer.equals("00"))
			{
				moviePayTot = 0;
				su = 0;
				return;
			}

			if (answer.equals("01"))
			{
				PayDisc.selMem();
				return;
			}

		}
		while (!answer.equals("y") && !answer.equals("Y") && !answer.equals("n") && !answer.equals("N"));
		

		if (answer.equals("y") || answer.equals("Y"))
		{
			bst.snackMenuDisp(); // 스낵 구매 메소드 호출
		}
		else
			selPay(); // 결제 수단 선택 메소드 호출
	}

	// 결제 수단 선택 메소드
	static void selPay() throws InterruptedException,IOException
	{
		BuySnacks bst = new BuySnacks();

		payTot = snackPayTot + moviePayTot; // 영화+스낵 구매 가격

		if (moviePayTot>0 && snackPayTot>0) // 영화, 스낵 모두 결제 금액이 있다면
		{
			System.out.print("\n\t================================================================");
			System.out.printf("\n\t최종 결제 금액은 %d입니다.", moviePayTot+snackPayTot-su);
			//System.out.println("\n\t================================================================");
			
		}

		if (moviePayTot<=0 && snackPayTot>0)
		{
			System.out.print("\n\t================================================================");
			System.out.printf("\n\t최종 결제 금액은 %d입니다.", snackPayTot-su);
			//System.out.print("\n\t================================================================");
		}

		if (moviePayTot>0 && snackPayTot<=0)
		{
			
			System.out.print("\n\t================================================================");
			System.out.printf("\n\t최종 결제 금액은 %d입니다.", moviePayTot-su);
			//System.out.print("\n\t================================================================");
		}
		
		System.out.println("\n\n\n\n\t결제 방법 선택                          00.초기화면 01.이전화면");
		System.out.println("\t================================================================");
		System.out.println("\t1. 현금");
		System.out.println("\t2. 카드");
		
		int n = 0;
		do
		{
			Scanner sc = new Scanner(System.in);

			while (true)
			{
				try
				{
					if (n>0)
						System.out.println("\t>> 잘못 입력하셨습니다. 다시 입력해 주세요.\n");
					
					System.out.print("\t>> 결제 방법을 선택 해주세요 : ");
					answer = sc.next();

					if (answer.equals("00"))
					{
						moviePayTot = 0;
						su = 0;
						snackPayTot = 0;
						return;
					}

					if (answer.equals("01"))
					{
						if (snackPayTot <= 0)
							PayDisc.selMem();
						else 
							bst.snackMenuDisp();

						return;
					}
					
					sel = Integer.parseInt(answer);
					
					n++;
					break;
				}
				catch (NumberFormatException e)
				{
					System.out.println("\t>> 잘못 입력하셨습니다. 다시 입력해 주세요.\n");
				}
			}
			
		}
		while (sel<1 || sel>2);
		
		cant = true;
		if (sel==1)
		{
			if (moviePayTot>0 && snackPayTot>0) // 영화, 스낵 모두 결제 금액이 있다면
			{
				payCash();
				
				if (cant)
					printAd();
				
				if (cant)
					receipt();
				
				if (cant)
					coupon();
			}
			if (moviePayTot<=0 && snackPayTot>0) // 스낵만 결제 금액이 있다면
			{
				payCash();

				if (cant)
					coupon();
			}
			if (moviePayTot>0 && snackPayTot<=0) // 영화만 결제 금액이 있다면
			{
				payCash();

				if (cant)
					printAd();
				
				if (cant)
					receipt();
			}
		}
		else if(sel==2)
		{
			if (moviePayTot>0 && snackPayTot>0) // 영화, 스낵 모두 결제 금액이 있다면
			{
				payCard();

				if (cant)
					printAd();
				
				if (cant)
					receipt();
				
				if (cant)
					coupon();
			}
			if (moviePayTot<=0 && snackPayTot>0) // 스낵만 결제 금액이 있다면
			{
				payCard();
				
				if (cant)
					coupon();
			}
			if (moviePayTot>0 && snackPayTot<=0) // 영화만 결제 금액이 있다면
			{
				payCard();

				if (cant)
					printAd();
				
				if (cant)
					receipt();
			}
		}

	} // end selPay() 결제 수단 선택 메소드


	// 현금 결제
	static void payCash() throws InterruptedException,IOException
	{
		payTot = snackPayTot + moviePayTot;

		Scanner sc = new Scanner(System.in);
		System.out.print("\n\n\n\t결제할 금액을 지폐별로 입력해주세요.");
		int n = 0;
		do // 적게 투입하면 다시 입력 요청하는 반복 실행
		{
			flag = false;
			
			n = 0;
			while (true)
			{
				try
				{
					do
					{
						if (n>0)
							System.out.println("\t>> 잘못 입력하셨습니다. 다시 입력해주세요.");

						System.out.print("\n\t>> 50000원권 : ");
						a = sc.nextInt();

						n++;
					}
					while (a<0);

					break;
				}
				catch (InputMismatchException e)
				{
					sc = new Scanner(System.in);
					System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
				}
			}
			
			n = 0;
			while (true)
			{
				try
				{
					do
					{
						if (n>0)
							System.out.println("\t>> 잘못 입력하셨습니다. 다시 입력해주세요.");

						System.out.print("\t>> 10000원권 : ");
						b = sc.nextInt();

						n++;
					}
					while (b<0);

					break;
				}
				catch (InputMismatchException e)
				{
					sc = new Scanner(System.in);
					System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
				}
			}

			n = 0;
			while (true)
			{
				try
				{
					do
					{
						if (n>0)
							System.out.println("\t>> 잘못 입력하셨습니다. 다시 입력해주세요.");

						System.out.print("\t>> 5000원권  : ");
						c = sc.nextInt();

						n++;
					}
					while (c<0);

					break;
				}
				catch (InputMismatchException e)
				{
					sc = new Scanner(System.in);
					System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
				}
			}
			
			n = 0;
			while (true)
			{
				try
				{
					do
					{
						if (n>0)
							System.out.println("\t>> 잘못 입력하셨습니다. 다시 입력해주세요.");

						System.out.print("\t>> 1000원권  : ");
						d = sc.nextInt();

						n++;
					}
					while (d<0);

					break;
				}
				catch (InputMismatchException e)
				{
					sc = new Scanner(System.in);
					System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
				}
			}
			
			inputTot = 50000*a + 10000*b + 5000*c + 1000*d;

			System.out.printf("\n\t50000원권 %d장, 10000원권 %d장, 5000원권 %d장, 1000원권 %d장 \n\t총 [%d원] 투입했습니다.\n", a, b, c, d, inputTot);

			if (payTot>inputTot)
			{
				flag = true;
				System.out.printf("\n\t[총 결제 금액(%d)]보다 [%d원]적게 투입했습니다. 다시 입력해주세요.\n", payTot, payTot-inputTot);
			}
		}
		while (flag);

		if (payTot<inputTot) // 총 금액보다 많이 투입했을 때 → 거스름돈
		{
			payi = 2; 
			
			//----------------- 거스름돈 계산
			chaCash = inputTot-payTot;

			int oman = chaCash / 50000;
			int man = chaCash % 50000 / 10000;
			int ocheon = chaCash % 50000 % 10000 / 5000;
			int cheon = chaCash % 50000 % 10000 % 5000 / 1000;

			if (chaCash>payHap) // 거스름돈이 현재 돈통에 있는 금액보다 많을 때(=거슬러줄 수 없음)
			{
				payi = 1; // 거슬러줄 수 없을 때 1로 변경해놓으면 초기메뉴 실행 메소드에 있는 if문으로 들어가서 카드결제 안내 공지 추가됨
				System.out.print("\n\n\n\t죄송합니다. 현재 기계에 현금 부족으로 잔돈 반환이 불가하여 결제를 진행할 수 없습니다.");
				System.out.print("\n\t===============================================");
				System.out.print("\n\t1.카드로 결제하기 | 2. 예매 진행하지 않기");
				System.out.print("\n\t===============================================");
				System.out.print("\n\t>> 다른 방법을 입력해주세요 : ");
				sel = sc.nextInt();

				if (sel==1)
					payCard();
				else if (sel==2)
				{
					cant = false;

					moviePayTot = 0;
					su = 0;
					snackPayTot = 0;
					
					return;
				}
				else
					System.out.println("\t>> 잘못된 번호를 입력했습니다. 다시 입력해주세요.\n");
	
			}
			else
			{
				payi = 2; 
				System.out.printf("\n\t자판기 하단에 거스름돈 [50000원권 %d장, 10000원권 %d장, 5000원권 %d장, 1000원권 %d장]", oman, man, ocheon, cheon);
				System.out.printf("\n\t>> 총 [%d원] 받아가세요.",chaCash);
			}

			//----------------- 시재에 입력받은 값 반영
			ac.setOmanwon(a);
			ac.setManwon(b);
			ac.setOcheonwon(c);
			ac.setCheonwon(d);

			//----------------- 돌려준 거스름돈 시재에 반영(빼기)
			ac.setOmanwon(-(oman));
			ac.setManwon(-(man));
			ac.setOcheonwon(-(ocheon));
			ac.setCheonwon(-(cheon));
			ac.setCashMovie(moviePayTot);
			ac.setCashSnacks(snackPayTot);
		}
		else if (payTot==inputTot)
		{
			//----------------- 시재에 입력받은 값 반영
			ac.setOmanwon(a);
			ac.setManwon(b);
			ac.setOcheonwon(c);
			ac.setCheonwon(d);
			ac.setCashMovie(moviePayTot);
			ac.setCashSnacks(snackPayTot);

			System.out.printf("\n\t>> %d원 결제 완료됐습니다.\n", inputTot);
			System.out.println("\t(결제 시간 : " + payDate + " " + payTime + ")");
		}

		//snackPayTot = 0;
		//moviePayTot = 0;
		//su = 0;
		if (su != 0)
			(Members.mData).get(PayDisc.index).setPoint((Members.mData).get(PayDisc.index).getPoint()-su);

	}// end payCash() 현금 결제 메소드
	
	// 카드 결제
	static void payCard() throws InterruptedException,IOException
	{
		System.out.print("\n\n\n\n\t단말기에 카드를 삽입해주세요.\n\n\n\t");

        for (int i = 0; i<3; i++) 
		{
            System.out.print("..");
            Thread.sleep(1000);
        }
        System.out.print("결제가 진행중이오니 카드를 제거하지마세요...\n\t");

		for (int i = 0; i<2; i++) 
		{
            System.out.print(".");
            Thread.sleep(1000);
        }
		System.out.println("\n\n\t결제가 완료됐습니다!\n\t카드를 제거해주세요.\n");
		ac.setCardMovie(moviePayTot);
		ac.setCardSnacks(snackPayTot);

		System.out.println("\t(결제 시간 : " + payDate + " " + payTime + ")"); 
		
		if (su != 0)
			(Members.mData).get(PayDisc.index).setPoint((Members.mData).get(PayDisc.index).getPoint()-su);
	} // end payCard() 카드 결제

	// 광고 및 출력 여부 확인
	public static void printAd() throws IOException, InterruptedException
	{
		Random rd = new Random();
		System.out.println("\n\n\n\n\t===============================================");
		System.out.println("\t고객님! 다음에 이 영화는 어떠세요?         (AD)");
		System.out.println("\t-----------------------------------------------");

		// 등록된 영화 중 랜덤 출력(광고)

		Set<String> key = AdminMovie.ht.keySet(); // 현재 등록된 영화들 가져오기
		Iterator<String> hit = key.iterator();	  // 조회하기 위해 iterator 생성
		int rdsu = rd.nextInt(key.size());		  // 등록된 영화 수만큼 랜덤을 돌리고 숫자 받아놓기
		
		String rdMovie="";
		while (hit.hasNext())
		{
			String[] rdmTit = new String[key.size()]; // 등록된 영화 개수만큼 방 만들기
			//Object keys = hit.next();
			for(int i=0; i<key.size(); i++) // 배열에 등록된 영화 다 담고
				rdmTit[i] = hit.next();
			rdMovie = rdmTit[rdsu];	// 그 중 랜덤수에 있는 영화 빼서 담아내기
		}
		System.out.println(AdminMovie.ht.get(rdMovie)); // 광고 출력 (영화 정보)
		System.out.println("\t===============================================");


		System.out.print("\n\t영화 티켓 출력중입니다");
		for (int i = 0; i<3; i++) 
		{
            System.out.print(".");
            Thread.sleep(2000);
        }
	}

	public static void receipt() throws IOException, InterruptedException
	{
		ReserveTikets rt = new ReserveTikets();	
		AdminMovie am = new AdminMovie();

		rtn++;
		String rMem = payDate.substring(0, 4) + payDate.substring(5, 7) + payDate.substring(8) + "-" + rt.serial + rtn;

		System.out.printf("\n\n\n\n\n\t   %20s\n", "===============================");
		System.out.printf("\n\t      %13s\n", "영화 입장권");
		System.out.printf("\t      %14s\n\n", " (영수증 겸용)");
		System.out.printf("\t   %20s\n", "-------------------------------");
		System.out.printf("\t            %s\n", " |결제 시간|");
		System.out.printf("\t      %-1s  %-1s\n", payDate, payTime);
		System.out.printf("\t   %25s\n", "-------------------------------");
		//System.out.printf("\t   %24s \n", "");
		System.out.printf("\t      예매번호 : %s \n", rMem);
		System.out.printf("\t      %s(%d세 이상) \n", rt.title, AdminMovie.ht.get(rt.title).getmAge());
		System.out.printf("\t      상영 시간 : %s \n", rt.time);
		System.out.printf("\t   %25s\n", "-------------------------------");
		System.out.printf("\t      총 인원  : %d명 (",rt.inwonS + rt.inwonC);
		if (rt.inwonS != 0)
		{
			System.out.printf("성인 : %d명", rt.inwonS);
			
			if (rt.inwonC != 0)
				System.out.print(", ");
		}
		if (rt.inwonC != 0)
			System.out.printf("청소년 : %d명", rt.inwonC);
		System.out.println(")");
		System.out.println("\t      상영관   : " + ((ScreenVO)am.ht2.get(rt.serial)).getmRoom()); 
		System.out.print("\t      좌석번호 : ");
		
		for (String item : rt.seatc)
		  System.out.print(item + " ");
		
		System.out.printf("\n\t   %25s\n", "-------------------------------");
		System.out.print("\t      영화 티켓 : ");
		int tPay=0;
		if ((rt.serial/10)%10 == 1 || (rt.serial/10)%10 == 2)
			tPay = (rt.inwonS*10000)+(rt.inwonC*8000);
	    else if ((rt.serial/10)%10 == 3)
			tPay = (rt.inwonS*15000)+(rt.inwonC*13000);
		System.out.println(tPay + "원");

		Calendar cal = Calendar.getInstance();
		w = cal.get(Calendar.DAY_OF_WEEK);

		int n=0; // 할인 적용되는 금액 담아두기 위해
		if (w==4) // 발표하는 요일로 맞췄음
		{   
			System.out.printf("\t      >> 문화의 날 할인 : %d \n", moviePayTot);
		}
		System.out.printf("\t      >> 적립금 차감 : %d \n", su); // 적립금 차감 담겨있음

		//System.out.println(moviePayTot + " " + snackPayTot);
		if (moviePayTot>0 && snackPayTot>0) // 영화, 스낵 모두 결제 금액이 있다면
		{
			System.out.printf("\t     스낵 구매 : %d \n", snackPayTot);	
			System.out.printf("\t   %25s\n", "-------------------------------");
			
			System.out.printf("\t      총 결제금액 : %d \n", (moviePayTot+snackPayTot-su));
			
			System.out.printf("\n\t   %20s\n", "===============================");
		}

		if (moviePayTot>0 && snackPayTot<=0) // 영화만 결제 금액이 있다면
		{
			System.out.printf("\t   %25s\n", "-------------------------------");
			
			System.out.printf("\t      총 결제금액 : %d \n", (moviePayTot+snackPayTot-su));
			
			System.out.printf("\n\t   %20s\n", "===============================");
		}

		
		// 영화값, 스낵값, 사용포인트 초기화
		snackPayTot = 0;
		moviePayTot = 0;
		su = 0;
		
		// 좌석 변수에 담기 
		String rSeat = "";
		for (String item : rt.seatc)
			rSeat += item + " ";
		
		// 선택 좌석 선택 불가로 변경
		char[][] uSeat = ((ScreenVO)am.ht2.get(rt.serial)).getmSeat();
		
		for (String item : rt.seatc)
		{
			char seat1 = item.substring(0, 1).charAt(0);
			int seat2 = Integer.parseInt(item.substring(1));
			uSeat[((int)seat1-65)][(seat2-1)] = '▩';
		}

		((ScreenVO)am.ht2.get(rt.serial)).setmSeat(uSeat);

       // 해시테이블 nRsv의 value값에 예매정보 변수들을 매개값으로 받는 RsvNumbVo 기반의 새로운 인스턴스 생성하여 추가
       nRsv.put(rMem, new RsvNumbVO(rMem, rt.title, rSeat, rt.time, ((ScreenVO)am.ht2.get(rt.serial)).getmRoom()));
	   

	   //return;
	}

	public static void coupon()
	{
		System.out.printf("\n\n\n\n\n\t   %20s\n", "===============================");
		System.out.printf("\n\t           %s\n", "음료/팝콘 교환권");
		System.out.printf("\t        %s\n\n", " (영수증 겸용/고객용)");
		System.out.printf("\t   %20s\n", "-------------------------------");
		System.out.printf("\t            %s\n", " |결제 시간|");
		System.out.printf("\t      %-1s  %-1s\n", payDate, payTime);
		System.out.printf("\t   %25s\n", "-------------------------------");
		System.out.printf("\t   %24s \n", "");
		System.out.printf("\t         ▶ 교환 번호 : %d ◀ \n\n", couponNum+1);
		System.out.print("\t   매점 직원이 교환 번호를");
		System.out.print("\n\t   확인할 수 있도록");
		System.out.println("\n\t   꼭 제시해주세요.");
		System.out.printf("\n\t   %20s\n", "===============================");
		couponNum += 1;
	}

} // end Pay class 