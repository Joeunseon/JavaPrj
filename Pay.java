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
	Function : Ƽ�� ���� �� ���� ����, �������� �Է� �� ���� ��� ���, �ε� �� ���� ���, ��ȭǥ/��ȯ��/������ ���  
	author	 : �輭��, ������
*/

//==================================================================================================================

// * ��������*
// 21-06-06 selMem() �޼ҵ� ����ó�� ����
// 21-06-13 Ŭ���� ��ü ���� ó�� ����
//          PayDisc Ŭ���� ���� ȸ�� ���� ��ȸ ����
//          ó��ȭ�� / ����ȭ�� ��� �߰�
//          ���� �Ϸ� �� ���� + ����� ����Ʈ ������Ʈ �����ϵ��� ����
//          ������ �¼� ���� �Ұ��� ���� �߰� 

class RsvNumbVO
{
	// ����ڰ� ���Ÿ� �Ϸ��ϰ� ������ �� �����鿡 ���� ����
	public static String rMem;			// ���Ź�ȣ	
	public static String rMovie;		// ��ȭ ����	
	public static String cSeat;			// �¼���ȣ
	public static String lastTimeTable;	// �󿵽ð�
    public static String rmNum;         // �󿵰�


   //���Ź�ȣ, ����, �󿵳�¥, �¼���ȣ
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
	  return "\n\t���Ź�ȣ : "+rMem+"\n\t��ȭ ���� : "+rMovie+"\n\t�󿵰� : "+rmNum+"1��\n\t�¼� : "+cSeat+"\n\t�󿵽ð� : "+lastTimeTable+            // �� ����
         "\n\n\t====================================";   																			
   }	
}

// ���� Ŭ���� �� 1. ���ο� ���� ���� ����, �����ϴ� Ŭ���� / 2. ���� ����� ���� �ݾ����� ���� �����ϴ� Ŭ���� 2������ �и�
// 1. PayDisc ȸ��/��ȸ�� Ȯ�� �� ���� ����, ������ ���
class PayDisc extends Record
{
	//public static ReserveTiketsVO rt = new ReserveTiketsVO();
	public static int index;

	// ���� �����ϱ� �� ȸ��/��ȸ�� Ȯ�� �޼ҵ�
	public static void selMem() throws InterruptedException,IOException
	{
		sc = new Scanner(System.in);

		int n = 0;
		
		System.out.println("\n\n\t                        00.�ʱ�ȭ�� 01.����ȭ��");
		System.out.print("\t===============================================");
		System.out.print("\n\t\t    *���� ���� �� ȸ�� Ȯ��*  ");
		System.out.print("\n\t-----------------------------------------------");
		System.out.print("\n\t              1. ȸ�� | 2. ��ȸ��");
		System.out.println("\n\t===============================================");

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
				  System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
			  }
		  }
		  
		}
		while (sel>2 || sel<1);
		
		
		recogMem();
		 
	} // end selMem ȸ��/��ȸ�� ���� �޼ҵ�


	// ȸ����ȣ(��ȭ��ȣ) ��ϵǾ��ִ� �������� �Ǻ�
	public static void recogMem() throws InterruptedException,IOException
	{
		if (sel==1) // ȸ���� ��
		{
			Scanner sc = new Scanner(System.in);

			System.out.println("\n\n\t[ȸ�� ���� Ȯ��]                         00.ó��ȭ�� 01.����ȭ��");
			System.out.println("\t================================================================");
			
			String tel = "";
			while (true)
		    {
			  try
			  {
				  

				  System.out.print("\t>> ��ȭ��ȣ�� �Է��ϼ���(xxx-xxxx-xxxx) : ");
				  tel = sc.next();

				  // 00 �� �Է��ϸ� �޼ҵ� �����Ͽ� ����ڸ��θ޴��� �̵�
				  if (tel.equals("00"))
				  {
					 moviePayTot = 0;
					 return;
				  }
				  // 01 �� �Է��ϸ� �޼ҵ� �����Ͽ� �����޴��� �̵�
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
			  catch (NumberFormatException e)  // ���ڼ��� ������ x �κ��� ���ڰ� �ƴ϶��
			  {
				  System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. �Է� ���Ŀ� ���� �Է����ּ���.\n");
			  }
			  catch (StringIndexOutOfBoundsException so)	// ���ڿ��� substring �� ���ڼ��� �����ϴٸ�
			  {
				  System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. �Է� ���Ŀ� ���� �Է����ּ���.\n");
			  }
			}

			search(tel); // ȸ������ Ȯ�� ���� �Է¹��� ��ȭ��ȣ search() �޼ҵ忡 �Ѱ��ְ�
		}
		else if(sel==2)
			noMemAge(); // ��ȸ�� �޼ҵ� ȣ��
			//System.out.println("\t��ȸ��");

	} // end recogMem ȸ�� Ȯ�� �޼ҵ�

	// ȸ������ Ȯ�ε� �� ���� �� ������ ��� �ȳ� �޼ҵ�
	public static void search(String tel) throws InterruptedException,IOException
	{
		Scanner sc = new Scanner(System.in);
		AdminMovie am = new AdminMovie();
		ReserveTikets rt = new ReserveTikets();

		//Members.oldMem(); // Members�� ��ϵǾ��ִ� ȸ�� ���� ������� Ȯ���ϱ� ���� ȣ��
						  // Members.mData�� ȸ������ ������ ����ִ� ArrayList > ������ Ȯ��(ȸ�� ����ŭ �ݺ�)
		//System.out.println("\ttest" +((MembersVO)Members.mData.get(0)).getTel());
		index = 0;
		boolean flag = false;
		String str = "";
		for (int i = 0; i < Members.mData.size() ; i++) 
        {
			// ArrayList�� ����Ǿ��ִ� ���� �� ��ȭ��ȣ �ҷ�����
			str = ((MembersVO)Members.mData.get(i)).getTel();
			// ArrayList�� Tel�� �Է��� Tel�� ������
			// �� Tel�� ���� ȸ�� ������ ��� ���
			if (tel.equals(str)) 
			{
				flag = true;
				index = i;
				break;
			}
	    }
		
		if (flag)
		{
		
			String name = ((MembersVO)Members.mData.get(index)).getName(); // �λ縻�� �ֱ� ���� �̸� get�ؿ���
			System.out.println("\n\n\t================================================================");
			System.out.printf("\t                   %s ȸ���� �ȳ��ϼ���!\n", name);
			
			// û�� ��ȭ ��� ���� Ȯ��
			if (((MovieVO)am.ht.get(rt.title)).getmAge()>=19)
			{
				//System.out.println("test");
				String ssn = ((MembersVO)Members.mData.get(index)).getId();
				
				Calendar cal = Calendar.getInstance();
				
				// ���̺��� 
				int age = 0;
				if (Integer.parseInt(ssn.substring(0, 1)) == 0)
					age = cal.get(Calendar.YEAR) - (2000 + Integer.parseInt(ssn.substring(0, 2)));
				else 
					age = cal.get(Calendar.YEAR) - (1900 + Integer.parseInt(ssn.substring(0, 2)));

				if (age < 19)
				{
					System.out.println("\t�����Ͻ� ��ȭ�� û�ҳ� ���� �Ұ� ��ȭ�Դϴ�.");
					System.out.println("\t��� & û�ҳ� ������ ���Ű� �Ұ��մϴ�.");
					System.out.println("\t================================================================");
					return;
				}
				
			}

			System.out.println("\t================================================================");

			// �ش� ȸ���� ������ �ִ� ����Ʈ(getPoint)�� 2000���� �Ѵ´ٸ� ��� �����ϴٴ� ���� ����
			if (((MembersVO)Members.mData.get(index)).getPoint()>=2000)
			{
				// ������ ����� �� �����ϰ� �ٽ� �ݿ��� �� �ִ� usePoint ����
				int usePoint = ((MembersVO)Members.mData.get(index)).getPoint(); // getPoint�� �˾ƿ� ����Ʈ�� usePoint�� ����
				System.out.println("\n\n\t[������ ��� Ȯ��]                       00.ó��ȭ�� 01.����ȭ��");
				System.out.println("\t================================================================");
				System.out.printf("\t���� ������ [%d��] �����ֽ��ϴ�.", usePoint);
				int n = 0;
				do
				{	
					if (n>0)
						System.out.println();

					System.out.print("\n\t>> ����Ͻðڽ��ϱ�? (Y/N) : ");
					answer = sc.next();

					// 00 �� �Է��ϸ� �޼ҵ� �����Ͽ� ����ڸ��θ޴��� �̵�
					if (answer.equals("00"))
					{
						moviePayTot = 0;
						return;
					}

					// 01 �� �Է��ϸ� �޼ҵ� �����Ͽ� �����޴��� �̵�
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
									System.out.print("\t>> �߸� �Է��ϼ̽��ϴ�. �ٽ� �������ּ���.");
								System.out.print("\n\t>> ����� �������� �Է����ּ���. : ");
								answer = sc.next();

								// 00 �� �Է��ϸ� �޼ҵ� �����Ͽ� ����ڸ��θ޴��� �̵�
								if (answer.equals("00"))
								{
									moviePayTot = 0;
									return;
								}
								// 01 �� �Է��ϸ� �޼ҵ� �����Ͽ� �����޴��� �̵�
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
								System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է����ּ���.\n");
							}
						}
					}
					while (su>usePoint || su<0);
					
					usePoint -= su; // �����ݿ��� ��� ������ ����
					//(Members.mData).get(i).setPoint(usePoint); // ������ ������ �ݾ� �ٽ� set ���ֱ�
					//System.out.print("������ Ȯ�� : " + usePoint);
					System.out.printf("\t>> [%d]�� ��� �Ϸ��߽��ϴ�. (���� ������ : %d)\n", su, usePoint);

					// ��ȭ�� �� ���� > ���� �߰� ���ŷ�
					Pay.selSnack();
					//System.out.println("������ ���. ��������ȣ��");
				}
				else
					Pay.selSnack();
					//System.out.println("������ ������. ��������ȣ��");
			}
			else // ������ 2000������ ������ ���Ұ��� �ٷ� ���� �߰����ŷ� �Ѿ
				Pay.selSnack(); 
				//System.out.println("������ ���Ұ�. ��������ȣ��");
		}	
		else
		{
			System.out.println("\n\n\t>> �������� �ʴ� ȸ���Դϴ�.");
			//break; // ���� ���������� �ٽ� ȸ��/��ȸ�� �����ϴ� recogMem() �޼ҵ��
			recogMem();
			return;
		}
	} // end search

	// ��ȸ�� ���� Ȯ�� �޼ҵ�
	public static void noMemAge() throws InterruptedException,IOException
	{
		Scanner sc = new Scanner(System.in);
		//Set<String> keys = AdminMovie.ht.keySet(); // key��(��ȭ ����) ��������
		//Iterator<String> it = keys.iterator();	   // �ش� ��ȭ�� ���� Ȯ���ϱ� ���� Iterator ����
		AdminMovie am = new AdminMovie();
		ReserveTikets rt = new ReserveTikets();
									   
		boolean flag = false;
		
		if (((MovieVO)am.ht.get(rt.title)).getmAge()>=19) // ����ڰ� ������ ��ȭ�� 19�� ���� �Ұ����
		{
			System.out.println("\n\n\n\t���� ����                             00. �ʱ�ȭ�� 01. ����ȭ��");
			System.out.println("\t================================================================");
			System.out.println("\t�����Ͻ� ��ȭ�� û�ҳ� ���� �Ұ� ������� ���� ������ �ʿ��մϴ�."); // �ֹι�ȣ Ȯ�� �ȳ���Ʈ�� �� ��ȭ�̸� ��������

			do // ��ȿ�� �˻縦 ���� �ݺ���
			{	
				while (true)
				{
					try
					{
						System.out.print("\t>> �ֹε�Ϲ�ȣ�� �Է��ϼ���(xxxxxx-xxxxxxx) : ");
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
					catch (NumberFormatException e)  // ���ڼ��� ������ x �κ��� ���ڰ� �ƴ϶��
					{
						System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. �Է� ���Ŀ� ���� �Է����ּ���.\n");
					}
					catch (StringIndexOutOfBoundsException so)	// ���ڿ��� substring �� ���ڼ��� �����ϴٸ�
					{
						System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. �Է� ���Ŀ� ���� �Է����ּ���.\n");
					}
				}
				
				
				// �ֹι�ȣ ��ȿ�� �˻� ����
				int[] chk = {2, 3, 4, 5, 6, 7, 0, 8, 9, 2, 3, 4, 5}; 
				int tot = 0;

				for (int i=0 ; i<13 ; i++) // i �� 0 1 2 3 4 5 6 7 8 9 10 11 12
					{
						if (i==6)
							continue;			
						tot += chk[i] * Integer.parseInt(answer.substring(i,i+1));
					}
				
				int su = 11 - tot % 11;
				su = su % 10;
				// �ֹι�ȣ ��ȿ�� �˻� ��
				int nowYear = Calendar.getInstance().get(Calendar.YEAR);

				if (su == Integer.parseInt(answer.substring(13))) // ��ȿ�� �ֹι�ȣ���
				{
					int birYear20;

					if (Integer.parseInt(answer.substring(0, 1)) == 0)
						birYear20 = Integer.parseInt("20" + answer.substring(0,2));
					else 
						birYear20 = Integer.parseInt("19" + answer.substring(0,2));

					if (nowYear-birYear20<19)
					{
						System.out.print("\n\t>> �˼��մϴ�. �ش� ��ȭ�� û�ҳ� ���� �Ұ��� ���Ÿ� ������ �� �����ϴ�.\n\t���� �ʱ�ȭ������ ���ư��ϴ�.\n");
						return; // ���� �ʱ�ȭ������
					}	
					else // ������ 19�̻� ������ ���� �����ϴϱ� ��ȭ�� �� ���� > ���� �߰� ���ŷ�
					{
						flag=false;
						System.out.println("\t>> ���� ������ �Ϸ�Ǿ����ϴ�.");
					}
				}
				else
				{	// �������� �ʴ� �ֹι�ȣ �Է�, flag�� true�� �ٽ� �ݺ��� ����
					flag=true;
					System.out.println("\n\t>> �������� �ʴ� �ֹι�ȣ�Դϴ�. �ٽ� �Է��ϼ���.\n\n");
				}
			} 
			while (flag);				
		} 
		
		Pay.selSnack();
		
	}


	
} // end PayDisc class 




// 2. ���� ����� �ݾ� + ���� �߰����� Ȯ�� �� �����ݾ� ���� Ŭ����
class Pay extends AdminCashVO
{
	// key������ ���Ź�ȣ�� ��� value������ RsvNumbVO���� ��� nRsv �ؽ����̺� ���� 
    public static Hashtable<String, RsvNumbVO> nRsv;

    static ReserveTiketsVO rt = new ReserveTiketsVO();
	static AdminCashVO ac = new AdminCashVO();

	static Date payToday = new Date();		//-- �����ð� Ȯ�ο�
	static SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
	static SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
	static String payDate = date.format(payToday); //-- ������ ��¥ ���
	static String payTime = time.format(payToday); //-- ������ �ð� ���

	static int rtn = 0; //-- ���Ź�ȣ ��������� ��ȣ

	static int payTot; //-- ��ȭ+���� �ջ� ����
	static int a, b, c, d, inputTot, chaCash; // ���� �Է¹޾� ���翡 �ݿ��ϱ� ���� a,b,c,d ����
											  // �Է��� �� ����, �Ž�����(changeCash)

	public static boolean flag = false;

   static 
   {
	   nRsv = new Hashtable<>();
   }

	// ���� �߰� ���� Ȯ�� �� ����
	static void selSnack() throws InterruptedException,IOException
	{
		BuySnacks bst = new BuySnacks(); // ���� ���� VO �ν��Ͻ� ����
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n\t�߰� ����");
		System.out.println("\t================================================================");
		do
		{
			System.out.print("\t>> ����/���� �߰� ���� �Ͻðڽ��ϱ�?(Y/N) : ");
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
			bst.snackMenuDisp(); // ���� ���� �޼ҵ� ȣ��
		}
		else
			selPay(); // ���� ���� ���� �޼ҵ� ȣ��
	}

	// ���� ���� ���� �޼ҵ�
	static void selPay() throws InterruptedException,IOException
	{
		BuySnacks bst = new BuySnacks();

		payTot = snackPayTot + moviePayTot; // ��ȭ+���� ���� ����

		if (moviePayTot>0 && snackPayTot>0) // ��ȭ, ���� ��� ���� �ݾ��� �ִٸ�
		{
			System.out.print("\n\t================================================================");
			System.out.printf("\n\t���� ���� �ݾ��� %d�Դϴ�.", moviePayTot+snackPayTot-su);
			//System.out.println("\n\t================================================================");
			
		}

		if (moviePayTot<=0 && snackPayTot>0)
		{
			System.out.print("\n\t================================================================");
			System.out.printf("\n\t���� ���� �ݾ��� %d�Դϴ�.", snackPayTot-su);
			//System.out.print("\n\t================================================================");
		}

		if (moviePayTot>0 && snackPayTot<=0)
		{
			
			System.out.print("\n\t================================================================");
			System.out.printf("\n\t���� ���� �ݾ��� %d�Դϴ�.", moviePayTot-su);
			//System.out.print("\n\t================================================================");
		}
		
		System.out.println("\n\n\n\n\t���� ��� ����                          00.�ʱ�ȭ�� 01.����ȭ��");
		System.out.println("\t================================================================");
		System.out.println("\t1. ����");
		System.out.println("\t2. ī��");
		
		int n = 0;
		do
		{
			Scanner sc = new Scanner(System.in);

			while (true)
			{
				try
				{
					if (n>0)
						System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. �ٽ� �Է��� �ּ���.\n");
					
					System.out.print("\t>> ���� ����� ���� ���ּ��� : ");
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
					System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. �ٽ� �Է��� �ּ���.\n");
				}
			}
			
		}
		while (sel<1 || sel>2);
		
		cant = true;
		if (sel==1)
		{
			if (moviePayTot>0 && snackPayTot>0) // ��ȭ, ���� ��� ���� �ݾ��� �ִٸ�
			{
				payCash();
				
				if (cant)
					printAd();
				
				if (cant)
					receipt();
				
				if (cant)
					coupon();
			}
			if (moviePayTot<=0 && snackPayTot>0) // ������ ���� �ݾ��� �ִٸ�
			{
				payCash();

				if (cant)
					coupon();
			}
			if (moviePayTot>0 && snackPayTot<=0) // ��ȭ�� ���� �ݾ��� �ִٸ�
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
			if (moviePayTot>0 && snackPayTot>0) // ��ȭ, ���� ��� ���� �ݾ��� �ִٸ�
			{
				payCard();

				if (cant)
					printAd();
				
				if (cant)
					receipt();
				
				if (cant)
					coupon();
			}
			if (moviePayTot<=0 && snackPayTot>0) // ������ ���� �ݾ��� �ִٸ�
			{
				payCard();
				
				if (cant)
					coupon();
			}
			if (moviePayTot>0 && snackPayTot<=0) // ��ȭ�� ���� �ݾ��� �ִٸ�
			{
				payCard();

				if (cant)
					printAd();
				
				if (cant)
					receipt();
			}
		}

	} // end selPay() ���� ���� ���� �޼ҵ�


	// ���� ����
	static void payCash() throws InterruptedException,IOException
	{
		payTot = snackPayTot + moviePayTot;

		Scanner sc = new Scanner(System.in);
		System.out.print("\n\n\n\t������ �ݾ��� ���󺰷� �Է����ּ���.");
		int n = 0;
		do // ���� �����ϸ� �ٽ� �Է� ��û�ϴ� �ݺ� ����
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
							System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");

						System.out.print("\n\t>> 50000���� : ");
						a = sc.nextInt();

						n++;
					}
					while (a<0);

					break;
				}
				catch (InputMismatchException e)
				{
					sc = new Scanner(System.in);
					System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
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
							System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");

						System.out.print("\t>> 10000���� : ");
						b = sc.nextInt();

						n++;
					}
					while (b<0);

					break;
				}
				catch (InputMismatchException e)
				{
					sc = new Scanner(System.in);
					System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
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
							System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");

						System.out.print("\t>> 5000����  : ");
						c = sc.nextInt();

						n++;
					}
					while (c<0);

					break;
				}
				catch (InputMismatchException e)
				{
					sc = new Scanner(System.in);
					System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
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
							System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");

						System.out.print("\t>> 1000����  : ");
						d = sc.nextInt();

						n++;
					}
					while (d<0);

					break;
				}
				catch (InputMismatchException e)
				{
					sc = new Scanner(System.in);
					System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
				}
			}
			
			inputTot = 50000*a + 10000*b + 5000*c + 1000*d;

			System.out.printf("\n\t50000���� %d��, 10000���� %d��, 5000���� %d��, 1000���� %d�� \n\t�� [%d��] �����߽��ϴ�.\n", a, b, c, d, inputTot);

			if (payTot>inputTot)
			{
				flag = true;
				System.out.printf("\n\t[�� ���� �ݾ�(%d)]���� [%d��]���� �����߽��ϴ�. �ٽ� �Է����ּ���.\n", payTot, payTot-inputTot);
			}
		}
		while (flag);

		if (payTot<inputTot) // �� �ݾ׺��� ���� �������� �� �� �Ž�����
		{
			payi = 2; 
			
			//----------------- �Ž����� ���
			chaCash = inputTot-payTot;

			int oman = chaCash / 50000;
			int man = chaCash % 50000 / 10000;
			int ocheon = chaCash % 50000 % 10000 / 5000;
			int cheon = chaCash % 50000 % 10000 % 5000 / 1000;

			if (chaCash>payHap) // �Ž������� ���� ���뿡 �ִ� �ݾ׺��� ���� ��(=�Ž����� �� ����)
			{
				payi = 1; // �Ž����� �� ���� �� 1�� �����س����� �ʱ�޴� ���� �޼ҵ忡 �ִ� if������ ���� ī����� �ȳ� ���� �߰���
				System.out.print("\n\n\n\t�˼��մϴ�. ���� ��迡 ���� �������� �ܵ� ��ȯ�� �Ұ��Ͽ� ������ ������ �� �����ϴ�.");
				System.out.print("\n\t===============================================");
				System.out.print("\n\t1.ī��� �����ϱ� | 2. ���� �������� �ʱ�");
				System.out.print("\n\t===============================================");
				System.out.print("\n\t>> �ٸ� ����� �Է����ּ��� : ");
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
					System.out.println("\t>> �߸��� ��ȣ�� �Է��߽��ϴ�. �ٽ� �Է����ּ���.\n");
	
			}
			else
			{
				payi = 2; 
				System.out.printf("\n\t���Ǳ� �ϴܿ� �Ž����� [50000���� %d��, 10000���� %d��, 5000���� %d��, 1000���� %d��]", oman, man, ocheon, cheon);
				System.out.printf("\n\t>> �� [%d��] �޾ư�����.",chaCash);
			}

			//----------------- ���翡 �Է¹��� �� �ݿ�
			ac.setOmanwon(a);
			ac.setManwon(b);
			ac.setOcheonwon(c);
			ac.setCheonwon(d);

			//----------------- ������ �Ž����� ���翡 �ݿ�(����)
			ac.setOmanwon(-(oman));
			ac.setManwon(-(man));
			ac.setOcheonwon(-(ocheon));
			ac.setCheonwon(-(cheon));
			ac.setCashMovie(moviePayTot);
			ac.setCashSnacks(snackPayTot);
		}
		else if (payTot==inputTot)
		{
			//----------------- ���翡 �Է¹��� �� �ݿ�
			ac.setOmanwon(a);
			ac.setManwon(b);
			ac.setOcheonwon(c);
			ac.setCheonwon(d);
			ac.setCashMovie(moviePayTot);
			ac.setCashSnacks(snackPayTot);

			System.out.printf("\n\t>> %d�� ���� �Ϸ�ƽ��ϴ�.\n", inputTot);
			System.out.println("\t(���� �ð� : " + payDate + " " + payTime + ")");
		}

		//snackPayTot = 0;
		//moviePayTot = 0;
		//su = 0;
		if (su != 0)
			(Members.mData).get(PayDisc.index).setPoint((Members.mData).get(PayDisc.index).getPoint()-su);

	}// end payCash() ���� ���� �޼ҵ�
	
	// ī�� ����
	static void payCard() throws InterruptedException,IOException
	{
		System.out.print("\n\n\n\n\t�ܸ��⿡ ī�带 �������ּ���.\n\n\n\t");

        for (int i = 0; i<3; i++) 
		{
            System.out.print("..");
            Thread.sleep(1000);
        }
        System.out.print("������ �������̿��� ī�带 ��������������...\n\t");

		for (int i = 0; i<2; i++) 
		{
            System.out.print(".");
            Thread.sleep(1000);
        }
		System.out.println("\n\n\t������ �Ϸ�ƽ��ϴ�!\n\tī�带 �������ּ���.\n");
		ac.setCardMovie(moviePayTot);
		ac.setCardSnacks(snackPayTot);

		System.out.println("\t(���� �ð� : " + payDate + " " + payTime + ")"); 
		
		if (su != 0)
			(Members.mData).get(PayDisc.index).setPoint((Members.mData).get(PayDisc.index).getPoint()-su);
	} // end payCard() ī�� ����

	// ���� �� ��� ���� Ȯ��
	public static void printAd() throws IOException, InterruptedException
	{
		Random rd = new Random();
		System.out.println("\n\n\n\n\t===============================================");
		System.out.println("\t����! ������ �� ��ȭ�� �����?         (AD)");
		System.out.println("\t-----------------------------------------------");

		// ��ϵ� ��ȭ �� ���� ���(����)

		Set<String> key = AdminMovie.ht.keySet(); // ���� ��ϵ� ��ȭ�� ��������
		Iterator<String> hit = key.iterator();	  // ��ȸ�ϱ� ���� iterator ����
		int rdsu = rd.nextInt(key.size());		  // ��ϵ� ��ȭ ����ŭ ������ ������ ���� �޾Ƴ���
		
		String rdMovie="";
		while (hit.hasNext())
		{
			String[] rdmTit = new String[key.size()]; // ��ϵ� ��ȭ ������ŭ �� �����
			//Object keys = hit.next();
			for(int i=0; i<key.size(); i++) // �迭�� ��ϵ� ��ȭ �� ���
				rdmTit[i] = hit.next();
			rdMovie = rdmTit[rdsu];	// �� �� �������� �ִ� ��ȭ ���� ��Ƴ���
		}
		System.out.println(AdminMovie.ht.get(rdMovie)); // ���� ��� (��ȭ ����)
		System.out.println("\t===============================================");


		System.out.print("\n\t��ȭ Ƽ�� ������Դϴ�");
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
		System.out.printf("\n\t      %13s\n", "��ȭ �����");
		System.out.printf("\t      %14s\n\n", " (������ ���)");
		System.out.printf("\t   %20s\n", "-------------------------------");
		System.out.printf("\t            %s\n", " |���� �ð�|");
		System.out.printf("\t      %-1s  %-1s\n", payDate, payTime);
		System.out.printf("\t   %25s\n", "-------------------------------");
		//System.out.printf("\t   %24s \n", "");
		System.out.printf("\t      ���Ź�ȣ : %s \n", rMem);
		System.out.printf("\t      %s(%d�� �̻�) \n", rt.title, AdminMovie.ht.get(rt.title).getmAge());
		System.out.printf("\t      �� �ð� : %s \n", rt.time);
		System.out.printf("\t   %25s\n", "-------------------------------");
		System.out.printf("\t      �� �ο�  : %d�� (",rt.inwonS + rt.inwonC);
		if (rt.inwonS != 0)
		{
			System.out.printf("���� : %d��", rt.inwonS);
			
			if (rt.inwonC != 0)
				System.out.print(", ");
		}
		if (rt.inwonC != 0)
			System.out.printf("û�ҳ� : %d��", rt.inwonC);
		System.out.println(")");
		System.out.println("\t      �󿵰�   : " + ((ScreenVO)am.ht2.get(rt.serial)).getmRoom()); 
		System.out.print("\t      �¼���ȣ : ");
		
		for (String item : rt.seatc)
		  System.out.print(item + " ");
		
		System.out.printf("\n\t   %25s\n", "-------------------------------");
		System.out.print("\t      ��ȭ Ƽ�� : ");
		int tPay=0;
		if ((rt.serial/10)%10 == 1 || (rt.serial/10)%10 == 2)
			tPay = (rt.inwonS*10000)+(rt.inwonC*8000);
	    else if ((rt.serial/10)%10 == 3)
			tPay = (rt.inwonS*15000)+(rt.inwonC*13000);
		System.out.println(tPay + "��");

		Calendar cal = Calendar.getInstance();
		w = cal.get(Calendar.DAY_OF_WEEK);

		int n=0; // ���� ����Ǵ� �ݾ� ��Ƶα� ����
		if (w==4) // ��ǥ�ϴ� ���Ϸ� ������
		{   
			System.out.printf("\t      >> ��ȭ�� �� ���� : %d \n", moviePayTot);
		}
		System.out.printf("\t      >> ������ ���� : %d \n", su); // ������ ���� �������

		//System.out.println(moviePayTot + " " + snackPayTot);
		if (moviePayTot>0 && snackPayTot>0) // ��ȭ, ���� ��� ���� �ݾ��� �ִٸ�
		{
			System.out.printf("\t     ���� ���� : %d \n", snackPayTot);	
			System.out.printf("\t   %25s\n", "-------------------------------");
			
			System.out.printf("\t      �� �����ݾ� : %d \n", (moviePayTot+snackPayTot-su));
			
			System.out.printf("\n\t   %20s\n", "===============================");
		}

		if (moviePayTot>0 && snackPayTot<=0) // ��ȭ�� ���� �ݾ��� �ִٸ�
		{
			System.out.printf("\t   %25s\n", "-------------------------------");
			
			System.out.printf("\t      �� �����ݾ� : %d \n", (moviePayTot+snackPayTot-su));
			
			System.out.printf("\n\t   %20s\n", "===============================");
		}

		
		// ��ȭ��, ������, �������Ʈ �ʱ�ȭ
		snackPayTot = 0;
		moviePayTot = 0;
		su = 0;
		
		// �¼� ������ ��� 
		String rSeat = "";
		for (String item : rt.seatc)
			rSeat += item + " ";
		
		// ���� �¼� ���� �Ұ��� ����
		char[][] uSeat = ((ScreenVO)am.ht2.get(rt.serial)).getmSeat();
		
		for (String item : rt.seatc)
		{
			char seat1 = item.substring(0, 1).charAt(0);
			int seat2 = Integer.parseInt(item.substring(1));
			uSeat[((int)seat1-65)][(seat2-1)] = '��';
		}

		((ScreenVO)am.ht2.get(rt.serial)).setmSeat(uSeat);

       // �ؽ����̺� nRsv�� value���� �������� �������� �Ű������� �޴� RsvNumbVo ����� ���ο� �ν��Ͻ� �����Ͽ� �߰�
       nRsv.put(rMem, new RsvNumbVO(rMem, rt.title, rSeat, rt.time, ((ScreenVO)am.ht2.get(rt.serial)).getmRoom()));
	   

	   //return;
	}

	public static void coupon()
	{
		System.out.printf("\n\n\n\n\n\t   %20s\n", "===============================");
		System.out.printf("\n\t           %s\n", "����/���� ��ȯ��");
		System.out.printf("\t        %s\n\n", " (������ ���/����)");
		System.out.printf("\t   %20s\n", "-------------------------------");
		System.out.printf("\t            %s\n", " |���� �ð�|");
		System.out.printf("\t      %-1s  %-1s\n", payDate, payTime);
		System.out.printf("\t   %25s\n", "-------------------------------");
		System.out.printf("\t   %24s \n", "");
		System.out.printf("\t         �� ��ȯ ��ȣ : %d �� \n\n", couponNum+1);
		System.out.print("\t   ���� ������ ��ȯ ��ȣ��");
		System.out.print("\n\t   Ȯ���� �� �ֵ���");
		System.out.println("\n\t   �� �������ּ���.");
		System.out.printf("\n\t   %20s\n", "===============================");
		couponNum += 1;
	}

} // end Pay class 