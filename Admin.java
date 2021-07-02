/*
	Title	 : Admin
	Function : 비밀번호 입력 후 관리자 메뉴를 제공
	author	 : 김서현, 조은선
*/

// ＊수정사항＊
// 21-03-19 2:40 용지관리, 스낵관리 메뉴 → 재고관리 메뉴로 통합하여 수정
//          3:45 공지 User 와 연동완료

// * 수정사항*
// 21-05-02 공지 관리 변경 유무 Y/N or y/n만 가능하도록 변경
//			초기 세팅 값 누락시 관리자 메뉴로 돌아감 (시재, 재고)
// 21-05-16 상영 등록된 영화가 없다면 전원 On 불가 (admin클래스의 adminSalesOn 메소드)

import java.util.Scanner;
import java.io.IOException;

import java.util.InputMismatchException;

class AdminVO
{
	private static String notice = "\n\t환영합니다. CRG입니다!\n";
	
	public String getNotice()
	{ return notice;}

	public void setNotice(String notice)
	{ this.notice = notice; }
}


// 관리자 메뉴 선택값만을 담는 클래스 
class AdminMenu
{
  // 상수화된 변수들(메뉴)
  static final int ADMIN_CASH = 1;		// 시재관리(현금 충전) + 판매 관리 로 수정 
  static final int ADMIN_MOVIE = 2;		// 영화 등록
  static final int ADMIN_STOCKS = 3;	// 용지+스낵 재고관리 로 수정! 
  static final int ADMIN_NOTICE = 4;	// 공지 변경
  static final int ADMIN_SALES_ON = 5;	// 전원 관리(판매 시작/종료)
}

class Admin extends Record
{

	void pw() throws IOException, InterruptedException
	{
		System.out.println("\n\n");

		Scanner sc = new Scanner(System.in);
		System.out.print("\t관리자 비밀번호를 입력하세요 : ");
		String pw = sc.next();

		do
		{
			if (pw.equals("1234"))
			{
				System.out.println("\t>> 관리자 로그인되었습니다.");
				break;
			}
			System.out.println("\t>> 잘못된 비밀번호를 입력했습니다. 다시 입력해주세요.");
			System.out.print("\t관리자 비밀번호를 입력하세요 : ");
			pw = sc.next();
		}
		while (true);


		startAdmin();
	}

	void startAdmin() throws IOException, InterruptedException
	{
		do
		{
			AdminMenuDisp();		// 관리자 메뉴 출력 메소드 
			menuSelect();			// 메뉴 선택 메소드
			AdminMenuRun();			// 메뉴 실행 메소드
		}
		while (true);
	}

	// 관리자 메뉴 출력 메소드
	public static void AdminMenuDisp() throws IOException
	{
		System.out.println("\n\n\t[ 관리자 메뉴 ]");
		System.out.println("\t================================================================");
		System.out.println("\t1. 시재 관리");
		System.out.println("\t2. 영화 관리");
		System.out.println("\t3. 재고 관리");
		System.out.println("\t4. 공지 관리");
		System.out.println("\t5. 판매 시작/종료\n");
	}

	// 메뉴 선택 메소드
	public static void menuSelect()
	{
		Scanner sc = new Scanner(System.in);
		int n = 0;

		while (true)
		{
			try
			{
				do
				{
					if (n>0)
					{
						System.out.println();
						System.out.println("\t>> 잘못 입력하셨습니다. 다시 선택해주세요.");
					}

					System.out.print("\t>> 메뉴 선택(1~5) : ");
					sel = sc.nextInt();

					n++;
				}
				while (sel<1 || sel>5);

				break;
			}
			catch (InputMismatchException e)
			{
				sc = new Scanner(System.in);
				System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
			}
		}
		
	}

	public static void AdminMenuRun() throws IOException, InterruptedException
    {
     	switch (sel)
		{
			case AdminMenu.ADMIN_CASH :  AdminCash.menuCash();break;				// 시재 관리
			case AdminMenu.ADMIN_MOVIE : AdminMovie.movieMenuDisp(); break;			// 영화 관리
			case AdminMenu.ADMIN_STOCKS : Stocks.menuStocks(); break;					// 재고 관리
			case AdminMenu.ADMIN_NOTICE : editNotice(); break;						// 공지 관리
			case AdminMenu.ADMIN_SALES_ON : adminSalesON(); break;					// 전원 관리(판매 시작/종료)
		}
    }

	// 공지 편집 메소드
	public static void editNotice() throws IOException
    {
		Scanner sc = new Scanner(System.in);
		AdminVO ad = new AdminVO();
		int n=0;

		System.out.println("\n\n\t[ 공지 변경 ]");
		System.out.println("\t================================================================");
		System.out.println("\t***현재 공지***");
		System.out.print(ad.getNotice());
		
		do
		{
			if (n>0)
				System.out.println("\t>> 잘못입력하셨습니다.");

			System.out.print("\n\t변경하시겠습니까? (Y/N) : ");
			answer = sc.next();
			//System.in.skip(2);

			n++;
		}
		while (!answer.equals("y") && !answer.equals("Y") && !answer.equals("n") && !answer.equals("N"));
		
		if (answer.equals("Y") || answer.equals("y"))
		{
			System.out.print("\t>> 변경할 공지를 입력해주세요 : ");
			String edit = sc.nextLine();
			ad.setNotice(edit);
			System.out.println("\t================================================================");
	    	System.out.println("\n\n\t[ 변경된 공지 ]");
			System.out.println("\t================================================================");
			System.out.println("\n\t" + ad.getNotice()+"\n");
		}
	}

	// 판매 시작 메소드 (판매시작 / 전원 OFF)
	public static void adminSalesON() throws IOException, InterruptedException
    {
		Scanner sc = new Scanner(System.in);
		
		AdminCashVO ac = new AdminCashVO();			//-- 기본셋팅보다 적다면 다시 입력하도록
		AdminMovie am = new AdminMovie();
		int sel, n=0;  //-- 메뉴 선택을 담을 변수, do-while문을 다시 실행하면 출력구문 실행을 위한 변수 

		do
		{
			if (n>0)
			{
				System.out.println();
				System.out.println("\t>>유효하지 않은 메뉴입니다. 다시 선택해주세요.");
			}

			System.out.println("\n\n");
			System.out.println("\t전원 관리				          0. 관리자 메뉴");
			System.out.println("\t================================================================");
			System.out.println("\t1. 판매 시작");
			System.out.println("\t2. 종료");
			System.out.println();

			while (true)
			{
				try
				{
					System.out.print("\t>> 메뉴 선택(1, 2) : ");
					sel = sc.nextInt();

					break;
				}
				catch (InputMismatchException e)
				{
					sc = new Scanner(System.in);
					System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
				}		
			}
			

			n++;
		}
		while (sel != 0 && sel!=1 && sel!=2);	// 0, 1, 2가 아니라면 다시 받아라 
		
		n = 0;	// 초기값 설정 완료 되었는지 확인하기 위해

		// 전원 ON
		if (sel == 1)
		{
			if (ac.getManwon()<10 || ac.getOcheonwon()<10 || ac.getCheonwon()<50)
			{
				System.out.println("\n");
				System.out.println("\t================================================================");
				System.out.println("\t>> 시재 부족");
				System.out.println("\t>> 기본 시재 : 10000원권 10장, 5000원권 10장, 1000원권 50장");
				n++;
			}

			if (StocksVO.getCoke()<50 || StocksVO.getCida()<50 || StocksVO.getPop()<80 || StocksVO.getPaper()<300)
			{
				if (n==0)
				{
					System.out.println("\n");
					System.out.println("\t================================================================");
				}
				else
					System.out.println();
				System.out.println("\t>> 재고 부족");
				System.out.println("\t>> 스낵 기본 재고 : 팝콘 80개, 사이다 50캔, 콜라 50캔, 용지 300장");
				n++;
			}

			if (am.ht2.isEmpty())
			{
				if (n==0)
				{
					System.out.println("\n");
					System.out.println("\t================================================================");
				}
				else
					System.out.println();
				System.out.println("\t>> 상영 중인 영화가 없습니다.");
				n++;
			}

			if (n>0)				// 초기 세팅이 미완료되었다면 관리자 메뉴로 돌아가라
			{
				System.out.println("\t================================================================");
				return;
			}

			// 판매 시작(사용자 메뉴 호출)
			User.startUser();
		}
		// 전원 OFF
		else if (sel == 2)		
			System.exit(0);

		// sel == 0 일 시 추가 작없 없이 자동 전원관리 메소드 종료 확인 완료...
	}
}

