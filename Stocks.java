/*
	Title	 : Stocks
	Function : ��� ����
	author	 : ��ƺ�, ������
*/


// �������� : menrRun() switch�� case 5 ���� 
//			: �ʱ�ȭ �� ���� (3/20)

// * ��������*
// 21-05-02 ��� �޴� ����ó�� ����
//			��� ���� �����ڸ޴��� �̵� ��� �߰�
//			��� ���� �Լ� ���� ȣ�� ���� ���� startStocks() �޼ҵ� ����
//			��� ���� ��� ������ ����� �Էµǵ��� ����

// import
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.Random;
import java.util.InputMismatchException;

//-----------------------------------------------------������ : ������ ���� Ŭ����
class StocksVO
{
   
   // ����, ���� ������
   private static int coke, cida, popcorn, paper;

   // getter, setter �޼ҵ�
   public static int getCoke()
   { return coke; }
   
   public static void setCoke(int coke)
   { StocksVO.coke += coke; }

   public static int getCida()
   { return cida; }

   public static void setCida(int cida)
   { StocksVO.cida += cida; }

   public static int getPop()
   { return popcorn; }

   public static void setPop(int popcorn)
   { StocksVO.popcorn += popcorn; }

   public static int getPaper()
   { return paper; }

   public static void setPaper(int paper)
   { StocksVO.paper += paper; }
  
}//end class StockVO

//--------------------------------------------------������ : ������ Ŭ���� <�ϼ�>

class Stocks extends Record
{
   
    // �ܡ� (�ٸ� Ŭ�������� Stocks ���θ޴� ȣ��� startStocks() ����Ͻø� �˴ϴ�!)
	/*
   public static void startStocks() throws InterruptedException, IOException
   {
         menuStocks();
         User.menuSelect();
         menuRun();
   }  
   */
   
   static StocksVO asv = new StocksVO();

   // ������ ���� �޴� ��� �޼ҵ�
   public static void menuStocks() throws InterruptedException, IOException
   {
	  sc = new Scanner(System.in);
	  int n = 0;

      System.out.println("\n\n\t[��� ����]				         0. ������ �޴�");
	  System.out.println("\t================================================================");
      System.out.println("\t1. �ݶ� ��� Ȯ��/����");
      System.out.println("\t2. ���̴� ���Ȯ��/����");
      System.out.println("\t3. ���� ��� Ȯ��/����");
      System.out.println("\t4. ���� ��� Ȯ��/����");
      
	  while (true)
	  {
		  try
		  {
			  do
			  {
				  if (n>0)
					  System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. �ٽ� �������ּ���.");

				  System.out.println();
				  System.out.print("\t>> �޴� ����(1~4) : ");
				  sel = sc.nextInt();
				  n++;
			  }
			  while (sel<0 || sel>4);

			  break;
		  }
		  catch (InputMismatchException e)
		  {
			  sc = new Scanner(System.in);
			  System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.");
		  }
	  }

	  switch (sel)
	  {
		case 0: break;
		default : menuRun(); break;
	  }
   }// end menuStocks() 
  
   // ������ �޴� ȣ�� �޼ҵ�
   public static void menuRun() throws InterruptedException, IOException
   {

      // Admin Ŭ���� �ν��Ͻ� ����(�޴� �̵�)
      // Admin ad = new Admin();

      switch (sel)
      {
		  case 1: addCoke(); break;
		  case 2: addCida(); break;
		  case 3: addPop(); break;
		  case 4: addPaper(); break;
      }

	  menuStocks();
   }
   
   // �ݶ� ���Ȯ�� �� ���� �޼ҵ�
   public static void addCoke() throws InterruptedException, IOException
   {
      sc = new Scanner(System.in);
      int n=0;		// do while �ٽ� ���� �ȳ� �޽��� ���
	  int su;		// ���� ������ ���� ����

      System.out.println();
	  System.out.println("\t�ݶ� ��� Ȯ��/����");
	  System.out.println("\t================================================================");
      System.out.printf("\t���� �ݶ�ĵ�� ���� %d���Դϴ�.\n", StocksVO.getCoke());

	  do
	  {
		  if (n>0)
			  System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�.\n");

		  System.out.print("\t��� �����Ͻðڽ��ϱ�?(Y/N): ");
		  answer = sc.next();

		  n++;
	  }
	  while (!answer.equals("y") && !answer.equals("Y") && !answer.equals("n") && !answer.equals("N"));
      

      if (answer.equals("Y") || answer.equals("y"))
      {
         while (true)
         {
			 try
			 {
				n = 0;	// ���� do while���� ���� �ʱ�ȭ

				do
				{
					if (n>0)
						System.out.println("\t>> ���� �߰��� �����մϴ�.");

					System.out.print("\t������ ������ �Է��ϼ��� : ");
					su = sc.nextInt();
					
					n++;
				}
				while (su<0);

				break;
			 }
			 catch (InputMismatchException e)
			 {
				sc = new Scanner(System.in);
				System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
			 }
         }
        
         if (su+StocksVO.getCoke()>300)
            System.out.println("\t���� ����� 300���� �ʰ��Ҽ� �����ϴ�.");
         else
         {
            StocksVO.setCoke(su);
			System.out.println();
			System.out.println("\t================================================================");
            System.out.println("\t������ �Ϸ�Ǿ����ϴ�.");
            System.out.printf("\t���� �ݶ��� ���� %d�Դϴ�.\n", StocksVO.getCoke());
			System.out.println("\t================================================================");
            System.out.println();
         }
         
      }
   }//end addCoke()

   // ���̴� ���Ȯ�� �� ���� �޼ҵ�
   public static void addCida() throws InterruptedException, IOException
   {
      sc = new Scanner(System.in);
      int n=0;		// do while �ٽ� ���� �ȳ� �޽��� ���
	  int su;		// ���� ������ ���� ����

      System.out.println();
	  System.out.println("\t���̴� ��� Ȯ��/����");
	  System.out.println("\t================================================================");
      System.out.printf("\t���� ���̴�ĵ�� ���� %d���Դϴ�.\n", StocksVO.getCida());
      
	  do
	  {
		  if (n>0)
			  System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�.\n");

		  System.out.print("\t��� �����Ͻðڽ��ϱ�?(Y/N): ");
		  answer = sc.next();

		  n++;
	  }
	  while (!answer.equals("y") && !answer.equals("Y") && !answer.equals("n") && !answer.equals("N"));
	  
      if (answer.equals("Y") || answer.equals("y"))
      {       
         while (true)
         {
			 try
			 {
				n = 0;	// ���� do while���� ���� �ʱ�ȭ

				do
				{
					if (n>0)
						System.out.println("\t>> ���� �߰��� �����մϴ�.");

					System.out.print("\t������ ������ �Է��ϼ��� : ");
					su = sc.nextInt();
					
					n++;
				}
				while (su<0);

				break;
			 }
			 catch (InputMismatchException e)
			 {
				sc = new Scanner(System.in);
				System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
			 }
         }

         if (su+StocksVO.getCida()>300)
            System.out.println("\t���� ����� 300���� �ʰ��Ҽ� �����ϴ�.");
         else
         {
            StocksVO.setCida(su);
			System.out.println();
			System.out.println("\t================================================================");
            System.out.println("\t������ �Ϸ�Ǿ����ϴ�.");
            System.out.printf("\t���� ���̴�ĵ�� ���� %d�Դϴ�.\n", StocksVO.getCida());
			System.out.println("\t================================================================");
            System.out.println();
         }   
      }

   }//end addCida()


   // ���� ���Ȯ�� �� ���� �޼ҵ�
   public static void addPop() throws InterruptedException, IOException
   {
      sc = new Scanner(System.in);
      int n=0;		// do while �ٽ� ���� �ȳ� �޽��� ���
	  int su;		// ���� ������ ���� ����

      System.out.println();
	  System.out.println("\t���� ��� Ȯ��/����");
	  System.out.println("\t================================================================");
      System.out.printf("\t���� ������ ���� %d���Դϴ�.\n", StocksVO.getPop());

      do
	  {
		  if (n>0)
			  System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�.\n");

		  System.out.print("\t��� �����Ͻðڽ��ϱ�?(Y/N): ");
		  answer = sc.next();

		  n++;
	  }
	  while (!answer.equals("y") && !answer.equals("Y") && !answer.equals("n") && !answer.equals("N"));


      if (answer.equals("Y") || answer.equals("y"))
      {
         
         while (true)
         {
			 try
			 {
				n = 0;	// ���� do while���� ���� �ʱ�ȭ

				do
				{
					if (n>0)
						System.out.println("\t>> ���� �߰��� �����մϴ�.");

					System.out.print("\t������ ������ �Է��ϼ��� : ");
					su = sc.nextInt();
					
					n++;
				}
				while (su<0);

				break;
			 }
			 catch (InputMismatchException e)
			 {
				sc = new Scanner(System.in);
				System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
			 }
         }

         if (su+StocksVO.getPop()>300)
            System.out.println("\t���� ����� 300���� �ʰ��Ҽ� �����ϴ�.");
         else
         {
            StocksVO.setPop(su);
			System.out.println();
			System.out.println("\t================================================================");
            System.out.println("\t������ �Ϸ�Ǿ����ϴ�.");
            System.out.printf("\t���� ������ ���� %d���Դϴ�.\n", StocksVO.getPop());
			System.out.println("\t================================================================");
            System.out.println();
         }
      //startStocks();
      }
   }//end addPop()

   // ���� ���� �� ���� �޼ҵ�
   public static void addPaper() throws InterruptedException, IOException
   {
      sc = new Scanner(System.in);
	  int n=0;		// do while �ٽ� ���� �ȳ� �޽��� ���
	  int su;		// ���� ������ ���� ����

	  System.out.println();
	  System.out.println("\t���� ��� Ȯ��/����");
	  System.out.println("\t================================================================");
      System.out.printf("\t���� ������ ����� %d���Դϴ�.\n", StocksVO.getPaper());
      
	  do
	  {
		  if (n>0)
			  System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�.\n");

		  System.out.print("\t��� �����Ͻðڽ��ϱ�?(Y/N): ");
		  answer = sc.next();

		  n++;
	  }
	  while (!answer.equals("y") && !answer.equals("Y") && !answer.equals("n") && !answer.equals("N"));


      if (answer.equals("Y") || answer.equals("y"))
      {
         
         while (true)
         {
			 try
			 {
				n = 0;	// ���� do while���� ���� �ʱ�ȭ

				do
				{
					if (n>0)
						System.out.println("\t>> ���� �߰��� �����մϴ�.");

					System.out.print("\t������ ������ �Է��ϼ��� : ");
					su = sc.nextInt();
					
					n++;
				}
				while (su<0);

				break;
			 }
			 catch (InputMismatchException e)
			 {
				sc = new Scanner(System.in);
				System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
			 }
         }

         if (su+StocksVO.getPaper()>1000)
            System.out.println("\t���� ����� 1000���� �ʰ��Ҽ� �����ϴ�.");
         else
         {
            StocksVO.setPaper(su);
			System.out.println();
			System.out.println("\t================================================================");
            System.out.println("\t������ �Ϸ�Ǿ����ϴ�.");
            System.out.printf("\t���� ������ ����� %d���Դϴ�.\n", StocksVO.getPaper());
			System.out.println("\t================================================================");
            System.out.println();
         }    
      }
   }//end addPaper()

}//end class Stocks


