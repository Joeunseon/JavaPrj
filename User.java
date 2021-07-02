/*
	Title	 : Stocks
	Function : 재고 관리
	author	 : 김아별, 소서현
*/

// * 수정사항*
// 21-05-23 예외처리 진행
//			사용자모드에서 관리자모드로 진입시 이전메뉴 실행 불가로 인하여 startAdmin 메소드 추가 (관리자 실행 메소드 이동)
//			이전 메뉴로 구성을 위해 startUser() do while문 추가

//---------------------------------------------사용자 : 메인 메뉴 User 클래스

import java.util.Scanner;
import java.io.IOException;
import java.lang.InterruptedException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.Random;

import java.util.InputMismatchException;

// 유저 메뉴 선택값만 담는 클래스
class UserMenuSel
{
  // 상수화된 변수들(메뉴)
  final static int RESERVE_TICKETS = 1;   // 영화 예매
  final static int CONFIRM_TICKETS = 2;   // 예매 확인
  final static int BUY_SNACKS = 3;        // 간식 구입
  final static int MEMBERS = 4;           // 회원관리
  static final int ADMIN = 1004;  //히든 관리자 메뉴
}


public class User extends Record
{
   // 주요 변수 선언 및 인스턴스 생성
   static Scanner sc = new Scanner(System.in);

   // 다른 클래스에서 User 메인메뉴 호출시 startUser() 사용
   public static void startUser() throws IOException, InterruptedException
   {
	  do
	  {
		  menuDisp();
		  menuSelect();
		  menuRun();
	  }
	  while (true);
   }
  
   // 메뉴 출력 메소드
   //--예매 확인/영화 예매/음료,팝콘 구매/회원 가입/관리자모드로 이동(switch)
   public static void menuDisp() throws IOException, InterruptedException
   { 
      
      AdminVO at = new AdminVO();

      // 공지내용 출력
	  System.out.println();
	  System.out.println();
	  System.out.println();
	  System.out.print("\t■■■■■■■■■■■■■■■■■■\n\n");
      System.out.println("\t"+at.getNotice());
	  System.out.println("\n\t■■■■■■■■■■■■■■■■■■");

      // 현금이 부족할시 카드결제 안내메세지 출력
      if (payi==1)
         System.out.println("\n\t※현재 기계 현금 부족으로 현금 결제를 진행할 수 없습니다. 카드 결제만 가능합니다※\n");

      System.out.println("\n");
      System.out.println("\t============= 메인 메뉴=============");
      System.out.println("\t1.  영화 예매     ");
      System.out.println("\t2.  예매 확인     ");
      System.out.println("\t3.  음료/팝콘 구매 ");
      System.out.println("\t4.  회원 조회/가입     ");
      System.out.println("\t===================================="); 
      // 관리자메뉴는 1004번(히든)
   }// end menuDisp()

   // 메뉴 선택 메소드
   public static void menuSelect() throws IOException, InterruptedException
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
					  System.out.println("\t>>유효하지 않은 메뉴입니다. 다시 선택해주세요.");
					  System.out.println();
				  }

				  System.out.print("\t>>메뉴 선택(1~4) : ");
				  sel = sc.nextInt();

				  n++;

				  if (sel == 1004)
					  break;
				  
			  }
			  while (sel<0 || sel>4);
			  
			  break;
		  }
		  catch (InputMismatchException e)
		  {
			  sc = new Scanner(System.in);
			  System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
		  }
	  }
      
   }// end menuSelect


   // 선택된 메뉴 실행에 따른 기능 호출 메소드
   public static void menuRun() throws IOException,InterruptedException
   { 
      // ◆---------여기에 다른 클래스 인스턴스 생성 구문 작성----------◆
      Members m1 = new Members();
      BuySnacks bs = new BuySnacks();
      Admin ad = new Admin();

      switch (sel)
      {
      //영화 예매 메소드 1번
	  //ScreenVO.roomNum
      //case UserMenuSel.RESERVE_TICKETS : ReserveTikets.selReserve(); ReserveTikets.selRoom(1); break;
	  case UserMenuSel.RESERVE_TICKETS : ReserveTikets.tiketsMenuDisp(); break;
      // 예매 확인 메소드 2번
      case UserMenuSel.CONFIRM_TICKETS : ConRes.conInput(); break;
      // 음료, 팝콘 구입 메소드 3번
      case UserMenuSel.BUY_SNACKS : 
            bs.snackMenuDisp(); break;
            //bs.snackCheck();
      // 회원관리 메소드 4번
      //case UserMenuSel.MEMBERS : m1.startMem(); break;
      case UserMenuSel.MEMBERS : m1.mMenuDisp(); break;
      // 관리자메뉴(히든메뉴) 1004번
      case UserMenuSel.ADMIN :
		 ad.startAdmin();	break; 

      //default: System.out.println("\t1~4번을 선택해주세요."); break;
      }

	  //startUser();
  
   }// end menuRun()

}//end User class

