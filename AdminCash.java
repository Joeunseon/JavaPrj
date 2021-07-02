//-------------------------[ ����/�Ǹűݾ� ���� Ŭ���� ]-------------------------

/*
	Title	 : AdminCash
	Function : ������ �޴����� �����ϰ� ����/�Ǹűݾ��� ����
	author	 : �輭��, �Ҽ���
*/

// * ��������*
// 21-05-02 ���� �޴��� ���Խ� �����ڸ޴��� �̵� �߰�
//			���� �޴����� �����޴� ��� �߰�
//			���� ���� �� ���ñݾ׺��� ���� ���°� �Ұ� ��� �߰�
//			���� �� �ݾ� ���� ����
//			���� �Ǹ� �ݾ׸� Ȯ�� �����Ͽ� ���� Ȯ�� �޼ҵ� �߰�


import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;


class AdminCashVO extends Record
{
	// ��ȭ/������ ����/ī���� �Ǹűݾ� ���� �� ���߿� �Ǹ� ������ �� �����ؼ� ���� �ֱ� ����
	private static int cardMovie, cardSnacks, cashMovie, cashSnacks;

	// ���� ����
	private static int omanwon, manwon, ocheonwon, cheonwon;
	
	// ������
	AdminCashVO() {}
	
	// �Ǹűݾ��� getter, setter ���� 
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

	// ������ getter, setter ����
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
	
	// �޴� ����
	public static void menuCash() throws InterruptedException, IOException
	{
		Scanner sc = new Scanner(System.in);
		int n = 0;

		System.out.println("\n\n\t[���� ����]				         0. ������ �޴�");
		System.out.println("\t================================================================");
		System.out.println("\t1. ���� ����");
		System.out.println("\t2. ���� Ȯ��");
		System.out.println("\t3. �Ǹ� �ݾ� ����\n");

		while (true)
		{
			try
			{
				do
				{
					if (n>0)
					{
						System.out.println();
						System.out.println("\t>> �߸��� ��ȣ�� �Է��߽��ϴ�. �ٽ� �Է����ּ���.");
					}

					System.out.print("\t>> �޴� ����(1~3) : ");
					sel = sc.nextInt();

					n++;	// do while���� �ٽ� ���ٸ� �ȳ��޽��� ����� ����
				}
				while (sel<-1 || sel>3);
				
				break;
			}
			catch (InputMismatchException e)
			{
				sc = new Scanner(System.in);
				System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
			}
		}

		switch (sel)
		{
			case 0: break;			// ������ �޴��� �̵�
			default : cashMenuSelect(sel); break;	// ���� �޼ҵ�� �̵�
		}
	}
	
	// ���� ���� ���� �޼ҵ�
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
	
	// ���� ���� �� ����
	public static void setCash() throws InterruptedException, IOException
	{
		Scanner sc = new Scanner(System.in);   
		
		int a, b, c;		// ����, ��õ��, õ������ ����� ���� ����
		int n=0;			// do while���� �ٽ� ���ٸ� �ȳ��޽��� ����� ����
		System.out.println("\n\n\t���� ����					  00. ���� �޴�");
		System.out.println("\t================================================================");
			
		while (true)
		{
			try
			{
				do
				{
					if (n>0)
						System.out.println("\t>> ���簡 �����մϴ�.");
		
					System.out.print("\t10000������ ��� : ");
					answer = sc.next();

					if (answer.equals("00"))	// ���� �޴��� ���ư���
						return;
					
					a = Integer.parseInt(answer);		// ���������� ��ȯ

					n++;		// �ȳ��޽��� ����� ����
				}
				while (a<0 && a<-ac.getManwon());
				
				break;
			}
			catch (NumberFormatException e)
			{
				sc = new Scanner(System.in);
				System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.");
			}
		}

		n = 0;	// ���� do while���� ���� ���� �ʱ�ȭ
		System.out.println();

		while (true)
		{
			try
			{
				do
				{
					if (n>0)
						System.out.println("\t>> ���簡 �����մϴ�.");

					System.out.print("\t5000������ ��� : ");
					answer = sc.next();

					if (answer.equals("00"))	// ���� �޴��� ���ư���
						return;
					
					b = Integer.parseInt(answer);		// ���������� ��ȯ

					n++;		// �ȳ��޽��� ����� ����
				}
				while (b<0 && b<-ac.getOcheonwon());

				break;
			}
			catch (NumberFormatException e)
			{
				sc = new Scanner(System.in);
				System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.");
			}
		}

		n = 0;	// ���� do while���� ���� ���� �ʱ�ȭ
		System.out.println();

		while (true)
		{
			try
			{
				do
				{
					if (n>0)
						System.out.println("\t>> ���簡 �����մϴ�.");

					System.out.print("\t1000������ ��� : ");
					answer = sc.next();

					if (answer.equals("00"))	// ���� �޴��� ���ư���
						return;

					c = Integer.parseInt(answer);		// ���������� ��ȯ

					n++;		// �ȳ��޽��� ����� ����
				}
				while (c<0 && c<-ac.getCheonwon());

				break;
			}
			catch (InputMismatchException e)
			{
				sc = new Scanner(System.in);
				System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.");
			}
		}

		// ���� ���� 
		ac.setManwon(a);
		ac.setOcheonwon(b);
		ac.setCheonwon(c);
		
		// �ѱݾ�
		int totalPay = ac.getOmanwon()*50000 + ac.getManwon()*10000 + ac.getOcheonwon()*5000 + ac.getCheonwon()*1000;
		
      // ���� ���
	  System.out.println();
	  System.out.println("\t================================================================");
      System.out.printf("\t���� 50000���� %d��, 10000���� %d��, 5000���� %d��, 1000���� %d��\n", ac.getOmanwon(), ac.getManwon(), ac.getOcheonwon(), ac.getCheonwon());
      System.out.printf("\t�� [%d]�� �ֽ��ϴ�.\n\n\n", totalPay);
	}

	public static void checkCash() throws InterruptedException, IOException
	{
		int totalPay = ac.getOmanwon()*50000 + ac.getManwon()*10000 + ac.getOcheonwon()*5000 + ac.getCheonwon()*1000;
		
		System.out.println("\n\n\t���� Ȯ��");
		System.out.println("\t================================================================");
		System.out.printf("\t���� 50000���� %d��, 10000���� %d��, 5000���� %d��, 1000���� %d��\n", ac.getOmanwon(), ac.getManwon(), ac.getOcheonwon(), ac.getCheonwon());
		System.out.printf("\t�� [%d]�� �ֽ��ϴ�.\n\n\n", totalPay);
	}

	// �Ǹ� ���� ��� ���
	public static void calCash() throws InterruptedException, IOException
	{	
		System.out.println("\n\n\t\t\t  [ �Ǹ� ���� ��� ]\n "); 
		System.out.println("\t ����������������������������������������������������������������������������������������������������");
		System.out.println("\t ��     ��     ��ȭ    ��     ����     ��     �հ�    ��");
		System.out.println("\t ����������������������������������������������������������������������������������������������������");
		System.out.printf("\t �� ���ݦ�%10d   ��%10d    ��%10d   ��\n", ac.getCashMovie(), ac.getCashSnacks(), ac.getCashMovie()+ac.getCashSnacks());
		System.out.println("\t ����������������������������������������������������������������������������������������������������"); 
		System.out.printf("\t �� ī�妢%10d   ��%10d    ��%10d   ��\n", ac.getCardMovie(), ac.getCardSnacks(), ac.getCardMovie()+ac.getCardSnacks());
		System.out.println("\t ����������������������������������������������������������������������������������������������������"); 
		System.out.printf("\t �� �հ覢%10d   ��%10d    ��%10d   ��\n", ac.getCashMovie()+ac.getCardMovie(), ac.getCashSnacks()+ac.getCardSnacks(), ac.getCashMovie()+ac.getCardMovie()+ac.getCashSnacks()+ac.getCardSnacks());
		System.out.println("\t ����������������������������������������������������������������������������������������������������");
		System.out.println();
	}
}
