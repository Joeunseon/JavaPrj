/*
	Title	 : Stocks
	Function : ��� ����
	author	 : ��ƺ�, �Ҽ���
*/

// * ��������*
// 21-05-23 ����ó�� ����
//			����ڸ�忡�� �����ڸ��� ���Խ� �����޴� ���� �Ұ��� ���Ͽ� startAdmin �޼ҵ� �߰� (������ ���� �޼ҵ� �̵�)
//			���� �޴��� ������ ���� startUser() do while�� �߰�

//---------------------------------------------����� : ���� �޴� User Ŭ����

import java.util.Scanner;
import java.io.IOException;
import java.lang.InterruptedException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.Random;

import java.util.InputMismatchException;

// ���� �޴� ���ð��� ��� Ŭ����
class UserMenuSel
{
  // ���ȭ�� ������(�޴�)
  final static int RESERVE_TICKETS = 1;   // ��ȭ ����
  final static int CONFIRM_TICKETS = 2;   // ���� Ȯ��
  final static int BUY_SNACKS = 3;        // ���� ����
  final static int MEMBERS = 4;           // ȸ������
  static final int ADMIN = 1004;  //���� ������ �޴�
}


public class User extends Record
{
   // �ֿ� ���� ���� �� �ν��Ͻ� ����
   static Scanner sc = new Scanner(System.in);

   // �ٸ� Ŭ�������� User ���θ޴� ȣ��� startUser() ���
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
  
   // �޴� ��� �޼ҵ�
   //--���� Ȯ��/��ȭ ����/����,���� ����/ȸ�� ����/�����ڸ��� �̵�(switch)
   public static void menuDisp() throws IOException, InterruptedException
   { 
      
      AdminVO at = new AdminVO();

      // �������� ���
	  System.out.println();
	  System.out.println();
	  System.out.println();
	  System.out.print("\t�������������������\n\n");
      System.out.println("\t"+at.getNotice());
	  System.out.println("\n\t�������������������");

      // ������ �����ҽ� ī����� �ȳ��޼��� ���
      if (payi==1)
         System.out.println("\n\t������ ��� ���� �������� ���� ������ ������ �� �����ϴ�. ī�� ������ �����մϴ١�\n");

      System.out.println("\n");
      System.out.println("\t============= ���� �޴�=============");
      System.out.println("\t1.  ��ȭ ����     ");
      System.out.println("\t2.  ���� Ȯ��     ");
      System.out.println("\t3.  ����/���� ���� ");
      System.out.println("\t4.  ȸ�� ��ȸ/����     ");
      System.out.println("\t===================================="); 
      // �����ڸ޴��� 1004��(����)
   }// end menuDisp()

   // �޴� ���� �޼ҵ�
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
					  System.out.println("\t>>��ȿ���� ���� �޴��Դϴ�. �ٽ� �������ּ���.");
					  System.out.println();
				  }

				  System.out.print("\t>>�޴� ����(1~4) : ");
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
			  System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
		  }
	  }
      
   }// end menuSelect


   // ���õ� �޴� ���࿡ ���� ��� ȣ�� �޼ҵ�
   public static void menuRun() throws IOException,InterruptedException
   { 
      // ��---------���⿡ �ٸ� Ŭ���� �ν��Ͻ� ���� ���� �ۼ�----------��
      Members m1 = new Members();
      BuySnacks bs = new BuySnacks();
      Admin ad = new Admin();

      switch (sel)
      {
      //��ȭ ���� �޼ҵ� 1��
	  //ScreenVO.roomNum
      //case UserMenuSel.RESERVE_TICKETS : ReserveTikets.selReserve(); ReserveTikets.selRoom(1); break;
	  case UserMenuSel.RESERVE_TICKETS : ReserveTikets.tiketsMenuDisp(); break;
      // ���� Ȯ�� �޼ҵ� 2��
      case UserMenuSel.CONFIRM_TICKETS : ConRes.conInput(); break;
      // ����, ���� ���� �޼ҵ� 3��
      case UserMenuSel.BUY_SNACKS : 
            bs.snackMenuDisp(); break;
            //bs.snackCheck();
      // ȸ������ �޼ҵ� 4��
      //case UserMenuSel.MEMBERS : m1.startMem(); break;
      case UserMenuSel.MEMBERS : m1.mMenuDisp(); break;
      // �����ڸ޴�(����޴�) 1004��
      case UserMenuSel.ADMIN :
		 ad.startAdmin();	break; 

      //default: System.out.println("\t1~4���� �������ּ���."); break;
      }

	  //startUser();
  
   }// end menuRun()

}//end User class
