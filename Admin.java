/*
	Title	 : Admin
	Function : ��й�ȣ �Է� �� ������ �޴��� ����
	author	 : �輭��, ������
*/

// ���������ף�
// 21-03-19 2:40 ��������, �������� �޴� �� ������ �޴��� �����Ͽ� ����
//          3:45 ���� User �� �����Ϸ�

// * ��������*
// 21-05-02 ���� ���� ���� ���� Y/N or y/n�� �����ϵ��� ����
//			�ʱ� ���� �� ������ ������ �޴��� ���ư� (����, ���)
// 21-05-16 �� ��ϵ� ��ȭ�� ���ٸ� ���� On �Ұ� (adminŬ������ adminSalesOn �޼ҵ�)

import java.util.Scanner;
import java.io.IOException;

import java.util.InputMismatchException;

class AdminVO
{
	private static String notice = "\n\tȯ���մϴ�. CRG�Դϴ�!\n";
	
	public String getNotice()
	{ return notice;}

	public void setNotice(String notice)
	{ this.notice = notice; }
}


// ������ �޴� ���ð����� ��� Ŭ���� 
class AdminMenu
{
  // ���ȭ�� ������(�޴�)
  static final int ADMIN_CASH = 1;		// �������(���� ����) + �Ǹ� ���� �� ���� 
  static final int ADMIN_MOVIE = 2;		// ��ȭ ���
  static final int ADMIN_STOCKS = 3;	// ����+���� ������ �� ����! 
  static final int ADMIN_NOTICE = 4;	// ���� ����
  static final int ADMIN_SALES_ON = 5;	// ���� ����(�Ǹ� ����/����)
}

class Admin extends Record
{

	void pw() throws IOException, InterruptedException
	{
		System.out.println("\n\n");

		Scanner sc = new Scanner(System.in);
		System.out.print("\t������ ��й�ȣ�� �Է��ϼ��� : ");
		String pw = sc.next();

		do
		{
			if (pw.equals("1234"))
			{
				System.out.println("\t>> ������ �α��εǾ����ϴ�.");
				break;
			}
			System.out.println("\t>> �߸��� ��й�ȣ�� �Է��߽��ϴ�. �ٽ� �Է����ּ���.");
			System.out.print("\t������ ��й�ȣ�� �Է��ϼ��� : ");
			pw = sc.next();
		}
		while (true);


		startAdmin();
	}

	void startAdmin() throws IOException, InterruptedException
	{
		do
		{
			AdminMenuDisp();		// ������ �޴� ��� �޼ҵ� 
			menuSelect();			// �޴� ���� �޼ҵ�
			AdminMenuRun();			// �޴� ���� �޼ҵ�
		}
		while (true);
	}

	// ������ �޴� ��� �޼ҵ�
	public static void AdminMenuDisp() throws IOException
	{
		System.out.println("\n\n\t[ ������ �޴� ]");
		System.out.println("\t================================================================");
		System.out.println("\t1. ���� ����");
		System.out.println("\t2. ��ȭ ����");
		System.out.println("\t3. ��� ����");
		System.out.println("\t4. ���� ����");
		System.out.println("\t5. �Ǹ� ����/����\n");
	}

	// �޴� ���� �޼ҵ�
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
						System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. �ٽ� �������ּ���.");
					}

					System.out.print("\t>> �޴� ����(1~5) : ");
					sel = sc.nextInt();

					n++;
				}
				while (sel<1 || sel>5);

				break;
			}
			catch (InputMismatchException e)
			{
				sc = new Scanner(System.in);
				System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
			}
		}
		
	}

	public static void AdminMenuRun() throws IOException, InterruptedException
    {
     	switch (sel)
		{
			case AdminMenu.ADMIN_CASH :  AdminCash.menuCash();break;				// ���� ����
			case AdminMenu.ADMIN_MOVIE : AdminMovie.movieMenuDisp(); break;			// ��ȭ ����
			case AdminMenu.ADMIN_STOCKS : Stocks.menuStocks(); break;					// ��� ����
			case AdminMenu.ADMIN_NOTICE : editNotice(); break;						// ���� ����
			case AdminMenu.ADMIN_SALES_ON : adminSalesON(); break;					// ���� ����(�Ǹ� ����/����)
		}
    }

	// ���� ���� �޼ҵ�
	public static void editNotice() throws IOException
    {
		Scanner sc = new Scanner(System.in);
		AdminVO ad = new AdminVO();
		int n=0;

		System.out.println("\n\n\t[ ���� ���� ]");
		System.out.println("\t================================================================");
		System.out.println("\t***���� ����***");
		System.out.print(ad.getNotice());
		
		do
		{
			if (n>0)
				System.out.println("\t>> �߸��Է��ϼ̽��ϴ�.");

			System.out.print("\n\t�����Ͻðڽ��ϱ�? (Y/N) : ");
			answer = sc.next();
			//System.in.skip(2);

			n++;
		}
		while (!answer.equals("y") && !answer.equals("Y") && !answer.equals("n") && !answer.equals("N"));
		
		if (answer.equals("Y") || answer.equals("y"))
		{
			System.out.print("\t>> ������ ������ �Է����ּ��� : ");
			String edit = sc.nextLine();
			ad.setNotice(edit);
			System.out.println("\t================================================================");
	    	System.out.println("\n\n\t[ ����� ���� ]");
			System.out.println("\t================================================================");
			System.out.println("\n\t" + ad.getNotice()+"\n");
		}
	}

	// �Ǹ� ���� �޼ҵ� (�ǸŽ��� / ���� OFF)
	public static void adminSalesON() throws IOException, InterruptedException
    {
		Scanner sc = new Scanner(System.in);
		
		AdminCashVO ac = new AdminCashVO();			//-- �⺻���ú��� ���ٸ� �ٽ� �Է��ϵ���
		AdminMovie am = new AdminMovie();
		int sel, n=0;  //-- �޴� ������ ���� ����, do-while���� �ٽ� �����ϸ� ��±��� ������ ���� ���� 

		do
		{
			if (n>0)
			{
				System.out.println();
				System.out.println("\t>>��ȿ���� ���� �޴��Դϴ�. �ٽ� �������ּ���.");
			}

			System.out.println("\n\n");
			System.out.println("\t���� ����				          0. ������ �޴�");
			System.out.println("\t================================================================");
			System.out.println("\t1. �Ǹ� ����");
			System.out.println("\t2. ����");
			System.out.println();

			while (true)
			{
				try
				{
					System.out.print("\t>> �޴� ����(1, 2) : ");
					sel = sc.nextInt();

					break;
				}
				catch (InputMismatchException e)
				{
					sc = new Scanner(System.in);
					System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
				}		
			}
			

			n++;
		}
		while (sel != 0 && sel!=1 && sel!=2);	// 0, 1, 2�� �ƴ϶�� �ٽ� �޾ƶ� 
		
		n = 0;	// �ʱⰪ ���� �Ϸ� �Ǿ����� Ȯ���ϱ� ����

		// ���� ON
		if (sel == 1)
		{
			if (ac.getManwon()<10 || ac.getOcheonwon()<10 || ac.getCheonwon()<50)
			{
				System.out.println("\n");
				System.out.println("\t================================================================");
				System.out.println("\t>> ���� ����");
				System.out.println("\t>> �⺻ ���� : 10000���� 10��, 5000���� 10��, 1000���� 50��");
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
				System.out.println("\t>> ��� ����");
				System.out.println("\t>> ���� �⺻ ��� : ���� 80��, ���̴� 50ĵ, �ݶ� 50ĵ, ���� 300��");
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
				System.out.println("\t>> �� ���� ��ȭ�� �����ϴ�.");
				n++;
			}

			if (n>0)				// �ʱ� ������ �̿Ϸ�Ǿ��ٸ� ������ �޴��� ���ư���
			{
				System.out.println("\t================================================================");
				return;
			}

			// �Ǹ� ����(����� �޴� ȣ��)
			User.startUser();
		}
		// ���� OFF
		else if (sel == 2)		
			System.exit(0);

		// sel == 0 �� �� �߰� �۾� ���� �ڵ� �������� �޼ҵ� ���� Ȯ�� �Ϸ�...
	}
}

