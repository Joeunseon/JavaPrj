//-------------------------[ 시재/판매금액 관리 클래스 ]-------------------------

/*
	Title	 : AdminCash
	Function : 관리자 메뉴에서 실행하고 시재/판매금액을 관리
	author	 : 김서현, 소서현
*/

// * 수정사항*
// 21-05-02 시재 메뉴로 진입시 관리자메뉴로 이동 추가
//			시재 메뉴에서 이전메뉴 기능 추가
//			시재 세팅 시 세팅금액보다 많이 빼는거 불가 기능 추가
//			시재 총 금액 수식 변경
//			기존 판매 금액만 확인 가능하여 시재 확인 메소드 추가


import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;


class AdminCashVO extends Record
{
	// 영화/스낵별 현금/카드의 판매금액 변수 → 나중에 판매 정산할 때 구분해서 돈을 넣기 위해
	private static int cardMovie, cardSnacks, cashMovie, cashSnacks;

	// 현금 변수
	private static int omanwon, manwon, ocheonwon, cheonwon;
	
	// 생성자
	AdminCashVO() {}
	
	// 판매금액의 getter, setter 생성 
	public int getCardMovie()
	{ return cardMovie;}

	public void setCardMovie(int cardMovie)  
	{ this.cardMovie += cardMovie; }

	public int getCardSnacks()
	{ return cardSnacks;}

	public void setCardSnacks(int cardSnacks)  
	{ this.cardSnacks += cardSnacks; }

	public int getCashMovie()
	{ return cashMovie;}

	public void setCashMovie(int cashMovie)  
	{ this.cashMovie += cashMovie; }

	public int getCashSnacks()
	{ return cashSnacks;}

	public void setCashSnacks(int cashSnacks)  
	{ this.cashSnacks += cashSnacks; }

	// 현금의 getter, setter 생성
	public int getOmanwon()
	{ return omanwon;}

	public void setOmanwon(int omanwon)  
	{ this.omanwon += omanwon; }

	public int getManwon()
	{ return manwon;}

	public void setManwon(int manwon)  
	{ this.manwon += manwon; }

	public int getOcheonwon()
	{ return ocheonwon;}

	public void setOcheonwon(int ocheonwon)  
	{ this.ocheonwon += ocheonwon; }

	public int getCheonwon()
	{ return cheonwon;}

	public void setCheonwon(int cheonwon)  
	{ this.cheonwon += cheonwon; }
}

class AdminCash extends Record
{
	static AdminCashVO ac = new AdminCashVO();
	
	// 메뉴 선택
	public static void menuCash() throws InterruptedException, IOException
	{
		Scanner sc = new Scanner(System.in);
		int n = 0;

		System.out.println("\n\n\t[시재 관리]				         0. 관리자 메뉴");
		System.out.println("\t================================================================");
		System.out.println("\t1. 시재 세팅");
		System.out.println("\t2. 시재 확인");
		System.out.println("\t3. 판매 금액 정산\n");

		while (true)
		{
			try
			{
				do
				{
					if (n>0)
					{
						System.out.println();
						System.out.println("\t>> 잘못된 번호를 입력했습니다. 다시 입력해주세요.");
					}

					System.out.print("\t>> 메뉴 선택(1~3) : ");
					sel = sc.nextInt();

					n++;	// do while문을 다시 돈다면 안내메시지 출력을 위해
				}
				while (sel<-1 || sel>3);
				
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
			case 0: break;			// 관리자 메뉴로 이동
			default : cashMenuSelect(sel); break;	// 선택 메소드로 이동
		}
	}
	
	// 시재 관리 선택 메소드
	public static void cashMenuSelect(int sel) throws InterruptedException, IOException
	{
		switch (sel)
		{
			case 1: setCash(); break;
			case 2: checkCash(); break;
			case 3: calCash(); break;
		}

		menuCash();
	}
	
	// 시재 세팅 및 충전
	public static void setCash() throws InterruptedException, IOException
	{
		Scanner sc = new Scanner(System.in);   
		
		int a, b, c;		// 만원, 오천원, 천원권의 장수를 담을 변수
		int n=0;			// do while문을 다시 돈다면 안내메시지 출력을 위해
		System.out.println("\n\n\t시재 세팅					  00. 이전 메뉴");
		System.out.println("\t================================================================");
			
		while (true)
		{
			try
			{
				do
				{
					if (n>0)
						System.out.println("\t>> 시재가 부족합니다.");
		
					System.out.print("\t10000원권의 장수 : ");
					answer = sc.next();

					if (answer.equals("00"))	// 이전 메뉴로 돌아가기
						return;
					
					a = Integer.parseInt(answer);		// 정수형으로 변환

					n++;		// 안내메시지 출력을 위해
				}
				while (a<0 && a<-ac.getManwon());
				
				break;
			}
			catch (NumberFormatException e)
			{
				sc = new Scanner(System.in);
				System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.");
			}
		}

		n = 0;	// 다음 do while문을 돌기 위해 초기화
		System.out.println();

		while (true)
		{
			try
			{
				do
				{
					if (n>0)
						System.out.println("\t>> 시재가 부족합니다.");

					System.out.print("\t5000원권의 장수 : ");
					answer = sc.next();

					if (answer.equals("00"))	// 이전 메뉴로 돌아가기
						return;
					
					b = Integer.parseInt(answer);		// 정수형으로 변환

					n++;		// 안내메시지 출력을 위해
				}
				while (b<0 && b<-ac.getOcheonwon());

				break;
			}
			catch (NumberFormatException e)
			{
				sc = new Scanner(System.in);
				System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.");
			}
		}

		n = 0;	// 다음 do while문을 돌기 위해 초기화
		System.out.println();

		while (true)
		{
			try
			{
				do
				{
					if (n>0)
						System.out.println("\t>> 시재가 부족합니다.");

					System.out.print("\t1000원권의 장수 : ");
					answer = sc.next();

					if (answer.equals("00"))	// 이전 메뉴로 돌아가기
						return;

					c = Integer.parseInt(answer);		// 정수형으로 변환

					n++;		// 안내메시지 출력을 위해
				}
				while (c<0 && c<-ac.getCheonwon());

				break;
			}
			catch (InputMismatchException e)
			{
				sc = new Scanner(System.in);
				System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.");
			}
		}

		// 시재 세팅 
		ac.setManwon(a);
		ac.setOcheonwon(b);
		ac.setCheonwon(c);
		
		// 총금액
		int totalPay = ac.getOmanwon()*50000 + ac.getManwon()*10000 + ac.getOcheonwon()*5000 + ac.getCheonwon()*1000;
		
      // 시재 출력
	  System.out.println();
	  System.out.println("\t================================================================");
      System.out.printf("\t현재 50000원권 %d장, 10000원권 %d장, 5000원권 %d장, 1000원권 %d장\n", ac.getOmanwon(), ac.getManwon(), ac.getOcheonwon(), ac.getCheonwon());
      System.out.printf("\t총 [%d]원 있습니다.\n\n\n", totalPay);
	}

	public static void checkCash() throws InterruptedException, IOException
	{
		int totalPay = ac.getOmanwon()*50000 + ac.getManwon()*10000 + ac.getOcheonwon()*5000 + ac.getCheonwon()*1000;
		
		System.out.println("\n\n\t시재 확인");
		System.out.println("\t================================================================");
		System.out.printf("\t현재 50000원권 %d장, 10000원권 %d장, 5000원권 %d장, 1000원권 %d장\n", ac.getOmanwon(), ac.getManwon(), ac.getOcheonwon(), ac.getCheonwon());
		System.out.printf("\t총 [%d]원 있습니다.\n\n\n", totalPay);
	}

	// 판매 정산 결과 출력
	public static void calCash() throws InterruptedException, IOException
	{	
		System.out.println("\n\n\t\t\t  [ 판매 정산 결과 ]\n "); 
		System.out.println("\t ┌────────────────────────────────────────────────┐");
		System.out.println("\t │     │     영화    │     간식     │     합계    │");
		System.out.println("\t │────────────────────────────────────────────────│");
		System.out.printf("\t │ 현금│%10d   │%10d    │%10d   │\n", ac.getCashMovie(), ac.getCashSnacks(), ac.getCashMovie()+ac.getCashSnacks());
		System.out.println("\t │────────────────────────────────────────────────│"); 
		System.out.printf("\t │ 카드│%10d   │%10d    │%10d   │\n", ac.getCardMovie(), ac.getCardSnacks(), ac.getCardMovie()+ac.getCardSnacks());
		System.out.println("\t │────────────────────────────────────────────────│"); 
		System.out.printf("\t │ 합계│%10d   │%10d    │%10d   │\n", ac.getCashMovie()+ac.getCardMovie(), ac.getCashSnacks()+ac.getCardSnacks(), ac.getCashMovie()+ac.getCardMovie()+ac.getCashSnacks()+ac.getCardSnacks());
		System.out.println("\t └────────────────────────────────────────────────┘");
		System.out.println();
	}
}
