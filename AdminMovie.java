/*
	Title	 : AdminMovie
	Function : ������ �޴����� ��ȭ ���� �޴� ȣ��� ��ȭ ��� �� �� ������ ��� ��� ����
	author	 : ������, ������
*/

// * ��������*
// 21-05-16 ��ȭ ���� �Է� �� �⵵���� �Է� �ǵ��� (���� MM-DD)
//			��ȭ ���� ��������, �������� �Է� �� �⵵ ���� ���� �ǵ��� (���� MM-DD)
//			��ȭ ���� ���� �� ���� ������ �� ��µǵ��� ����
//			��ȭ �� ��� �� �� ��Ȳ �� ��� ��� �߰� (���� ��¥, ���� �󿵰� ��, ������ �ȵ�..)
//			�� �ð� (�ð�, ��)��ȿ�� �˻� ��� �߰�
//			�� �ð� �� ����ó�� ��� �߰�
// 21-06-06 �� ���� ��ü�� �¼� �߰�

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
	 // ��ȭ ���� ��� ������
	 private String mTitle;			// ��ȭ ����
	 private String mGenre;			// �帣
	 private String mOpenDate;		// �������� 
	 private String mEndDate;		// ��������
	 private String mStory;		    // �ٰŸ�
	 private int mAge;				// �������ɿ���
	 private int mTime;				// �� �󿵽ð�(����Ÿ��)
	 private int mStar;				// ����
		
	// ������(����� ���� ������ �� �Ű����� ���� �⺻ ������)
	MovieVO()
	{
	}
	
	// ������(����� ���� ������)
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
	
	
	// get, set �޼ҵ� 
	public String getmTitle()			// ��ȭ ����
	{
		return mTitle;
	}

	public void setmTitle(String mTitle)
	{
		this.mTitle = mTitle;
	}

	public String getmGenre()			// �帣
	{
		return mGenre;
	}

	public void setmGenre(String mGenre)
	{
		this.mGenre = mGenre;
	}

	public String getmOpenDate()		// �� ����
	{
		return mOpenDate;
	}

	public void setmOpenDate(String mOpenDate)
	{
		this.mOpenDate = mOpenDate;
	}

	public String getmEndDate()			// ���� ����
	{
		return mEndDate;
	}

	public void setmEndDate(String mEndDate)
	{
		this.mEndDate = mEndDate;
	}

	public String getmStory()			// �ٰŸ�
	{
		return mStory;
	}

	public void setmStory(String mStory)
	{
		this.mStory = mStory;
	}

	public int getmAge()				// ���� ���� ����
	{
		return mAge;
	}

	public void setmAge(int mAge)
	{
		this.mAge = mAge;
	}

	public int getmTime()				// �� �� �ð�
	{
		return mTime;
	}

	public void setmTime(int mTime)
	{
		this.mTime = mTime;
	}

	public int getmStar()				// ����
	{
		return mStar;
	}

	public void setmStar(int mStar)
	{
		this.mStar = mStar;
	}
	
	// Hashtable ��½� Hashcode�� ��µ�, ���� �������� ���� toString Override
	@Override
	public String toString()
	{
		return "\t"+mTitle+" ("+mAge+")\n\t���� : "+mStar+"\n\t�帣 : "+mGenre+"\n\t�������� : "+mOpenDate+"\n\t�������� : "+mEndDate+"\n\t�� �󿵽ð� : "+mTime+"\n\t"+mStory;
		//��ȭ���� �帣 �������� �������� �ٰŸ� �������ɿ��� �ѻ󿵽ð� ����
	}

}

class ScreenVO
{
	//�������� 
   private String mDate;					// ������
   private String mRoom;					// �󿵰�
   private String mTitle;					// ��ȭ����
   private String mSh;				    	// �󿵽��۽ð�(�ð�) 
   private String mSm;				    	// �󿵽��۽ð�(��)
   private String mEh;				        // ������ð�(�ð�)
   private String mEm;				    	// ������ð�(��)  int
   private char[][] mSeat;
   
   // ������(����� ���� ������ �� �Ű����� ���� �⺻ ������)
   ScreenVO()
   {	
   }

   // ������(����� ���� ������)
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

   //get, set �޼ҵ�

   public String getmDate()			// �� ����
   {
	  return mDate;	
   }

   public void setmDate(String mDate)	
   {
	  this.mDate = mDate;	
   }
  
   public String getmRoom()			// �󿵰�
   {
      return mRoom;
   }

   public void setmRoom(String mRoom)
   {
      this.mRoom=mRoom;
   }

   public String getmTitle()		// �󿵿�ȭ ����
   {
	  return mTitle;	
   }

   public void setmTitle(String mTitle)		
   {
	  this.mTitle = mTitle;	
   }

   public String getmSh()			// �� ���� �ð�
   {
	  return mSh;
   }

   public void setmSh(String mSh)
   {
	  this.mSh = mSh;
   }

   public String getmSm()			// �� ���� ��
   {
	  return mSm;
   }

   public void setmSm(String mSm)
   {
	  this.mSm = mSm;
   }

   public String getmEh()			// �� ���� �ð�
   {
	  return mEh;
   }

   public void setmEh(String mEh)
   {
	  this.mEh = mEh;
   }

   public String getmEm()			// �� ���� ��
   {
	  return mEm;
   }

   public void setmEm(String mEh)
   {
	  this.mEm = mEh;
   }
   
   public char[][] getmSeat()			// �¼�
   {
	  return mSeat;
   }

   public void setmSeat(char[][] mSeat)
   {
	  this.mSeat = mSeat;
   }

   // Hashtable ��½� Hashcode�� ��µ�, ���� �������� ���� toString Override
   @Override
   public String toString()
   {
	   return "\t"+mTitle+"  "+mRoom+"\n\t������ : "+mDate+"\n\t�󿵽ð� : "+mSh+":"+mSm+" ~ "+mEh+":"+mEm;
   }
}


class AdminMovie
{
	public static final Hashtable<String, MovieVO> ht;		// ��ȭ ������ ���� hashtable
	public static final Hashtable<Integer, ScreenVO> ht2;	// ��ȭ �����ٸ� ���� hashtable
	//public static  Map<String, String> ht3;					// ��ȭ ����/���� �ð� ���� hashtable
    // �߰� ��
    //public static  Map<String, String> rm1;                 // �󿵽ð�/�󿵰� ��ȣ ���� hashtable
	
	private static Integer s;		                        // hashtable�� ��ϵǴ� ���� ����° serial ��
    public static Integer serial;                           // hashtable Ű���� ���� ���� 
    public static String rmNum;                             // �󿵽ð�, �󿵰���ȣ ���� hashtable    
    public static String strSerial;                         // serial�� ����� ���� ����       
    public static int sel;                                  // ����� �Է°�

	// static �ʱ�ȭ ����
	static 
	{
		ht = new Hashtable<>();
		ht2 = new Hashtable<>();
        //ht3 = new HashMap<>();
        // �߰� ��
        //rm1 = new HashMap<>();
		//ht333 = new HashMap<>();
		 ht.put("�ҿ�", (new MovieVO("�ҿ�", "�ִϸ��̼�", "2022-06-01", "2022-07-01", "����� ���� �����԰� ��ư� �ǹ̸� ���ݴ� ������ ���� ���� �ִϸ��̼�", 12, 137, 8)));
		 ht.put("�̳���", (new MovieVO("�̳���", "����/����", "2021-06-11", "2021-09-12", "1980��뿡 �Ƹ޸�ĭ�帲�� �޲ٸ� �̱����� �̹��� ���� �ѱ� �����̾߱�", 15, 117, 9)));
	     ht.put("���˿��� ����", (new MovieVO("���˿��� ����", "����", "2021-03-20", "2021-04-20", "�ѹ����� �ǰ� ���� ���� ����� ���� �º�", 19, 136, 7)));
		s = 0;
	}

	// ��ȭ ���� �޴� ��� �޼ҵ�
	public static void movieMenuDisp() 
	{
		Scanner sc = new Scanner(System.in);

		int sel;

		System.out.println("\n\n");
		System.out.println("\t[��ȭ ����]					  0. ������ �޴�");
		System.out.println("\t================================================================");
		System.out.println("\t1. ��ȭ ���");
		System.out.println("\t2. ��� ��ü ���");
		System.out.println("\t3. ��� ��ȭ ���� ����");
		System.out.println("\t4. ��� ��ȭ ����");
		System.out.println("\t5. �� ����Ʈ ���");
		System.out.println("\t6. �� ��Ȳ ��ü ���");
		System.out.println("\t7. �� ����Ʈ ����");
		System.out.println();
	

		while (true)
		{
			try
			{
				System.out.print("\t>> �޴��� �������ּ��� (1~7) : ");
				sel = sc.nextInt();
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
			case 0: break;							// ������ �޴��� �̵�, �޼ҵ� ����
			default : movieMenuSelect(sel); break;
		}
		
	} // end movieMenuDisp() ��ȭ ���� �޴� ��� �޼ҵ�

	// ��ȭ ���� �޴� ���� �޼ҵ�
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
	} // end movieMenuSelect() ��ȭ ���� �޴� ���� �޼ҵ�

  // ��ȭ���� ��� �޼ҵ� 
  public static void registerMovie() 
  {
	 Scanner sc = new Scanner(System.in);

	 String title="";	// ��ȭ ���� ���� 
	 String genre;		// ��ȭ �帣
	 String openDate;	// ��ȭ ��������
	 String endDate;	// ��ȭ ��������
	 String story;		// ��ȭ �ٰŸ�
	 int age;			// ��ȭ �������ɿ���
	 int time;			// ��ȭ �� �󿵽ð�
	 int star;			// ��ȭ ����
	 String answer="";
	 int t=0;					//do while�� �ٽ� ���Ҵٸ�...
	 String yy, mm, dd;				// ����, �������� ��ȿ�� �˻縦 ���� ����
	 int oyy, omm, odd, eyy, emm, edd;    // ����, �������� ��ȿ�� �˻縦 ���� ����
	 boolean flag;				 // do while���� ���������� ���� 
	 
	 System.out.println("\n\n");
	 System.out.println("\t��ȭ ���				      -1. ��ȭ ���� �޴�");
	 System.out.println("\t================================================================");
	
	  
	 do
	 {	
		 if (t>0)
			System.out.println("\t" + title + "�� �̹� ��ϵǾ��ֽ��ϴ�.");

		 System.out.print("\t>> ����� ��ȭ ������ �Է��ϼ��� : ");
		 title = sc.nextLine();
		 
		 // ��ȭ ���� �޴��� ���ư��� ����
		 if (title.equals("-1"))
			 return; 

		 t++;
	 }
	 while (ht.containsKey(title));
		  
	 System.out.println();
	 System.out.print("\t>> ����� ��ȭ �帣�� �Է��ϼ��� : ");
	 genre = sc.next();
	 if (genre.equals("-1"))
			 return; 
	
	 t = 0;			// do while���� ���� ���� t �ʱ�ȭ 
	 
	 do
	{
		 //flag = false;	// do while���� ���� ���� flag �� �ʱ�ȭ

		 if (t>0)
			 System.out.println("\n\t>> �Է��� ���ڰ� ��ȿ���� �ʽ��ϴ�.");
		
		while (true)		// ����ó���� �ϱ� ���� �ݺ��� 
		{
			 flag = false;	// do while���� ���� ���� flag �� �ʱ�ȭ
			 System.out.println();
			 System.out.print("\t>> ����� ��ȭ ���� ���ڸ� �Է��ϼ��� (YYYY-MM-DD) : ");
			 openDate = sc.next();		
			 
			 // ��ȭ ������ ���ư��� ����
			 if (openDate.equals("-1"))
				 return; 
			 
			 try
			 {
				// �Է��� �������� ���� �������� 
				 //mm = openDate.substring(0,2);		// ����ڰ� �Է��� ���� ���� �߶� mm ������ ����
				 yy = openDate.substring(0, 4);
				 mm = openDate.substring(5, 7);
				 dd = openDate.substring(8, 10);		// ����ڰ� �Է��� ���� �ϸ� �߶� dd ������ ����

				 String hi = openDate.substring(4,5);

				 // �������� �ƴ϶��
				 if (!hi.equals("-"))
					flag = true;
				
				 hi = openDate.substring(7,8);
				 
				 if (!hi.equals("-"))
					 flag = true;

				// ���������� ����
				 oyy = Integer.parseInt(yy);		// �⵵�� �߶��� ���� ���������� ��ȯ (Open year) 
				 omm = Integer.parseInt(mm);		// ���� �߶��� ���� ���������� ��ȯ (Open month)
				 odd = Integer.parseInt(dd);		// �ϸ� �߶��� ���� ���������� ��ȯ (Open day)	
				 
				 break;
			 }
			 catch (NumberFormatException e) // ���ڿ��� ���ڷ� ��ȯ�� ���ڿ��� ���� ���ڰ� �ƴ϶�� 
			 {
				 System.out.println("\n\t>> �߸� �Է��ϼ̽��ϴ�. �Է� ���Ŀ� ���� �Է����ּ���.");
			 }
			 catch (StringIndexOutOfBoundsException so)	// ���ڿ��� substring �� ���ڼ��� �����ϴٸ�
			 {
				 System.out.println("\n\t>> �߸� �Է��ϼ̽��ϴ�. �Է� ���Ŀ� ���� �Է����ּ���.");
			 }

		}
		  // �Է� ���� ������ ��ȿ�� �˻�
		 if (omm<1 || omm>12)				// ���� 1~12�� �ƴ϶��
			 flag = true;

		 if (odd>31)						// ���� 31���� ������
			 flag = true;
		 else if (omm==2 && odd>28)			// 2���Ͻ� 28���� ������  
			 flag = true;
		 else if ((omm==4 || omm==6 || omm==9 || omm==11) && odd>30)	// 4, 6, 9. 11���Ͻ� 30���� ������ 
			 flag = true;

		 t++;
	 }
	 while (flag);
	
	 t = 0;			// do while���� ���� ���� t �ʱ�ȭ 

	 do
	 {
		 // flag = false;	// do while���� ���� ���� flag �� �ʱ�ȭ

		 if (t>0)
			 System.out.println("\n\t>> �Է��� ���ڰ� ��ȿ���� �ʽ��ϴ�.");

		 while (true)		// ����ó���� �ϱ� ���� �ݺ��� 
		 {
			 flag = false;

			 try
			 {
				 System.out.println();
				 System.out.print("\t>> ����� ��ȭ ���� ���ڸ� �Է��ϼ��� (YYYY-MM-DD) : ");
				 endDate = sc.next();

				 // ��ȭ ���� �޴��� �̵�, �޼ҵ� ����
				 if (endDate.equals("-1"))
					 return; 

				 // �Է��� �������� ���� �������� 
				 yy = endDate.substring(0, 4);		// ����ڰ� �Է��� ���� �⵵�� �߶� yy ������ ����
				 mm = endDate.substring(5,7);		// ����ڰ� �Է��� ���� ���� �߶� mm ������ ����
				 dd = endDate.substring(8,10);		// ����ڰ� �Է��� ���� ���� �߶� mm ������ ����
				 
				 //System.out.println("\t"+mm+"-"+dd);
				 // ���������� ����
				 eyy = Integer.parseInt(yy);		// �⵵�� �߶��� ���� ���������� ��ȯ (end year)
				 emm = Integer.parseInt(mm);		// ���� �߶��� ���� ���������� ��ȯ (end month)
				 edd = Integer.parseInt(dd);		// �ϸ� �߶��� ���� ���������� ��ȯ	(end day)

				 // ����ڰ� �Է��� ���ڿ� �߶� ���� (������, ����� �³� �˻��ϱ� ����) 
				 String hi = endDate.substring(4,5);

				 // �������� �ƴ϶��
				 if (!hi.equals("-"))
					flag = true;

				 hi = endDate.substring(7,8);
				 
				 if (!hi.equals("-"))
					 flag = true;

				  break;
			 }
			 catch (NumberFormatException e) // ���ڿ��� ���ڷ� ��ȯ�� ���ڿ��� ���� ���ڰ� �ƴ϶�� 
			 {
				 System.out.println("\n\t>> �߸� �Է��ϼ̽��ϴ�. �Է� ���Ŀ� ���� �Է����ּ���.");
			 }
			 catch (StringIndexOutOfBoundsException so)	// ���ڿ��� substring �� ���ڼ��� �����ϴٸ�
			 {
				 System.out.println("\n\t>> �߸� �Է��ϼ̽��ϴ�. �Է� ���Ŀ� ���� �Է����ּ���.");
			 }
		 }
		 
		 if (oyy > eyy)
			 flag = true;

		 if (emm<1 || emm>12)				// ���� 1~12�� �ƴ϶��
			 flag = true;

		 if (edd>31)						// ���� 31���� �Ѵ´ٸ�
			 flag = true;
		 else if (emm==2 && edd>28)			// 2���Ͻ� 28���� ������
			 flag = true;
		 else if ((emm==4 || emm==6 || emm==9 || emm==11) && edd>30)	// 4, 6, 9. 11���Ͻ� 30���� ������ 
			 flag = true;
	
		 if (oyy==eyy)
		 {
			 if (omm>emm)						// ���� ���� ���� �޺��� ũ�ٸ�
				 flag = true;

			 if (omm==emm && odd>edd)			// ���� �ް� ���� ���� ������ �������� �����Ϻ��� ũ�ٸ�
				 flag = true;
		 }
		  
		 t++;

	 }
	 while (flag);

	 sc.nextLine();
	 System.out.println();
	 System.out.print("\t>> ����� ��ȭ �ٰŸ��� �Է��ϼ��� : ");
	 story = sc.nextLine();
	 // ��ȭ ���� �޴��� �̵�, �޼ҵ� ����
	 if (story.equals("-1"))
		 return;
	 
	 while (true)
	 {
		 System.out.println();
		 System.out.print("\t>> ����� ��ȭ ���� ���� ������ �Է��ϼ��� : ");
		 try
		 {
		    age = sc.nextInt();

			break;
		 }
		 catch (InputMismatchException e)
		 {
			 sc = new Scanner(System.in);
			System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
		 }
	 }
	 
	 // ��ȭ ���� �޴��� �̵�, �޼ҵ� ����
	 if (age == -1)
		 return;
	 
	 while (true)
	 {
		 System.out.println();
		 System.out.print("\t>> ����� ��ȭ �� �󿵽ð��� �Է��ϼ��� (MM) : ");

		 try
		 {
			time = sc.nextInt();

			break;
		 }
		 catch (InputMismatchException e)
		 {
			 sc = new Scanner(System.in);
			System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
		 }
	 }
	
	 // ��ȭ ���� �޴��� �̵�, �޼ҵ� ����
	 if (time == -1)
		 return;
	 
	 while (true)
	 {
		 System.out.println();
		 System.out.print("\t>> ����� ��ȭ ������ �Է��ϼ���. : ");

		 try
		 {
			star = sc.nextInt();

			break;
		 }
		 catch (InputMismatchException e)
		 {
			 sc = new Scanner(System.in);
			System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
		 }
	 }
	 
	 // ��ȭ ���� �޴��� �̵�, �޼ҵ� ����
	 if (star == -1)
		 return;
	 
	 // �Է¹��� ��ȭ ���� hashtable�� �ֱ� 
	 ht.put(title,(new MovieVO(title, genre, openDate, endDate, story, age, time, star)));		// Hashtable<��ȭ����, ��ȭ����>
	 //ht.put("�̳���",(new MovieVO("�̳���", "����", "02-19", "04-01", "��̱��� �̾߱�", 12, 115, 8)));
	 //ht.put("�ҿ�",(new MovieVO("�ҿ�", "�ִ�", "02-27", "03-27", "���� ��ȭ", 15, 100, 9)));
	
	 System.out.println();
	 System.out.println("\t>> ��ȭ��"+title+"����(��) ���������� ��� �Ϸ�Ǿ����ϴ�.");
	 

  } // end registerMovie() ��ȭ ��� �޼ҵ� 

   // ��ϵ� ��ȭ ��ü ��� �޼ҵ� 
   public static void printMoive() 
   {
	  System.out.println("\n\n");
	  System.out.println("\t		  [��ϵ� ��ȭ ��ü ���]");
	  System.out.println("\t================================================================");
	  
	  Set<String> key = ht.keySet();
	  Iterator<String> hit = key.iterator();
	  while (hit.hasNext())
	  {
		  String keys = (String)hit.next();
		  System.out.println(ht.get(keys));			// MovieVO�� Override�� toString ���� �Ѿ��
		  System.out.println("\t================================================================");
	  }
   } // end printMovie()


   // ��ȭ ���� ���� �޼ҵ�
   public static void chaMoive()   
   {
      Scanner sc = new Scanner(System.in);
      
      String str;	// ������ �޴��� ���ڿ��� �� �Է� ���� ���� ����
      int n;		// ������ �޴��� �������� �� �Է� ���� ���� ����
      String key;	// ������ hashtable�� key ���� ���� ���� 
	  String yy, mm, dd;			// ����, �������� ��ȿ�� �˻縦 ���� ����
	  int oyy, omm, odd, eyy, emm, edd;  // ����, �������� ��ȿ�� �˻縦 ���� ����
	  boolean flag;  // do while���� ���������� ���� 

      int sel=0;	// ���� ���� ���� ����
      int t=0;		// do while���� �� ���ٸ�...
      
      System.out.println("\n\n");
      System.out.println("\t��ȭ ���� ����				      -1. ��ȭ ���� �޴�");
      System.out.println("\t================================================================");
      System.out.print("\t>> ������ ��ȭ ������ �Է��ϼ��� : ");
      key = sc.nextLine();
	  // ��ȭ ���� �޴��� �̵�, �޼ҵ� ����
	  if (key.equals("-1"))
		  return;
      
      if (ht.containsKey(key))
      {
         do
         {
            if (t>0)
              System.out.println(">> ��ȿ���� ���� �޴��Դϴ�. �ٽ� �������ּ���.\n");
			
			System.out.println();
            System.out.println("\t1. ��ȭ ����");
            System.out.println("\t2. ��ȭ �帣");
            System.out.println("\t3. ���� ����");
            System.out.println("\t4. ���� ����");
            System.out.println("\t5. �ٰŸ�");
            System.out.println("\t6. ���� ���� ����");
            System.out.println("\t7. �� �� �ð�");
            System.out.println("\t8. ����"); 
            System.out.println();
            System.out.println();
            System.out.print("\t>> ������ �ɼ��� �����ϼ��� : ");

            sel = sc.nextInt();
			// ��ȭ ���� �޴��� �̵�, �޼ҵ� ����
			if (sel == -1)
				 return;
 
            t++;
         }
         while (sel>8 || sel<1);         // �Է°��� 1~8�� �ƴ϶�� 
      }
      else 
         System.out.println("\t>> �ش� ��ȭ�� ��� �Ǿ������ʽ��ϴ�.");


      //sel�� �ٸ� �� �Է½� default�� �ذ��� �� �ֵ���
      
      if (sel==1)

      {
		 sc.nextLine();
         System.out.print("\t>> ������ ������ �Է��ϼ��� : ");
         str = sc.nextLine();
		 // ��ȭ ���� �޴��� �̵�, �޼ҵ� ����
		 if (str.equals("-1"))
			return;

         ht.get(key).setmTitle(str);		// ������ �ν��Ͻ��� �����ϰ����ϴ� value �� ����
         ht.get(key).getmTitle();			// ������ �ν��Ͻ��� value ���� �־��� ��
         ht.replace(key, ht.get(key));		// Hatable�� key�� ���� value�� �����Ѵ�.
		 
		 // key ���� ��ȭ �������� ����, key ���� �����ϱ� ����
		 String genre = ((MovieVO)ht.get(key)).getmGenre();
		 String openDate = ((MovieVO)ht.get(key)).getmOpenDate();
		 String endDate = ((MovieVO)ht.get(key)).getmEndDate();
		 String story = ((MovieVO)ht.get(key)).getmStory();
		 int age = ((MovieVO)ht.get(key)).getmAge();
		 int time = ((MovieVO)ht.get(key)).getmTime();
		 int star = ((MovieVO)ht.get(key)).getmStar();

		 ht.remove(key);					// ���� key�� ���� ����
		 ht.put(str,(new MovieVO(str, genre, openDate, endDate, story, age, time, star)));		// ����� key������ ���� ����

		 System.out.print("\n\t>> ������ ���������� ���� �Ϸ�Ǿ����ϴ�.");
      } 

      if (sel==2)
      {
		 System.out.println("\n\t== ���� �帣 : " + ((MovieVO)ht.get(key)).getmGenre() + " ==");
         System.out.print("\t>> ������ �帣�� �Է��ϼ��� : ");
         str = sc.next();
		 // ��ȭ ���� �޴��� �̵�, �޼ҵ� ����
		 if (str.equals("-1"))
			return;

         ht.get(key).setmGenre(str);
         ht.get(key).getmGenre();
         ht.replace(key, ht.get(key));

		 System.out.print("\n\t>> �帣�� ���������� ���� �Ϸ�Ǿ����ϴ�.");
      } 
	
	  // do while ���� ���� ���� �ʱ�ȭ 
	  t = 0;

      if (sel==3)
      {
		 System.out.println("\n\t== ���� �������� : " + ((MovieVO)ht.get(key)).getmOpenDate() + " ==");
		 do
		 {
			 // do while ���� ���� ���� �ʱ�ȭ 
			 //flag = false;

			 if (t>0)
				System.out.println("\n\t>> �Է��� ���ڰ� ��ȿ���� �ʽ��ϴ�.");

			 while (true)
			 {
				flag = false;

				try
				{
					 System.out.print("\t>> ������ ���� ���ڸ� �Է��ϼ��� : ");
					 str = sc.next();
					 // ��ȭ ���� �޴��� �̵�, �޼ҵ� ����
					 if (str.equals("-1"))
						return;
					 
					 String heTime = ((MovieVO)ht.get(key)).getmEndDate();	// �ش� key�� �������� �������� (�������� ������ �Ŀ� �ִٸ� ��ϵ����ʰ� �ϱ� ����)

					 // �Է��� �������� ���� �������� 
					 yy = heTime.substring(0, 4);
					 mm = heTime.substring(5, 7);	// ������ ���� �߶� mm ������ �ֱ�
					 dd = heTime.substring(8, 10);	// ������ �ϸ� �߶� dd ������ �ֱ�
					 
					 eyy = Integer.parseInt(yy);
					 emm = Integer.parseInt(mm);	// ���������� ��ȯ
					 edd = Integer.parseInt(dd);	// ���������� ��ȯ

					 // �Է��� �������� ��������
					 yy = str.substring(0, 4);
					 mm = str.substring(5, 7);
					 dd = str.substring(8, 10);

					 String hi = str.substring(4, 5);	// �Է� ��� �񱳸� �ϱ����� 
					 
					 if (!hi.equals("-"))			// �������� �´���
						flag = true;

					 hi = str.substring(7, 8);	// �Է� ��� �񱳸� �ϱ����� 
					 
					 if (!hi.equals("-"))			// �������� �´���
						flag = true;

					 //System.out.println("\t"+mm+"-"+dd);
					 // ���������� ����
					 oyy = Integer.parseInt(yy);
					 omm = Integer.parseInt(mm);	// ����� ���� �� ������ ��ȯ
					 odd = Integer.parseInt(dd);	// ����� ���� �� ������ ��ȯ

					 break;
				}
				catch (NumberFormatException e) // ���ڿ��� ���ڷ� ��ȯ�� ���ڿ��� ���� ���ڰ� �ƴ϶�� 
				{
					 System.out.println("\n\t>> �߸� �Է��ϼ̽��ϴ�. �Է� ���Ŀ� ���� �Է����ּ���.\n");
				}
				catch (StringIndexOutOfBoundsException so)	// ���ڿ��� substring �� ���ڼ��� �����ϴٸ�
				{
					 System.out.println("\n\t>> �߸� �Է��ϼ̽��ϴ�. �Է� ���Ŀ� ���� �Է����ּ���.\n");
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
         
         ht.get(key).setmOpenDate(str);		// ��ȿ�� �˻翡 ������ ���ٸ� �����
         ht.get(key).getmOpenDate();
         ht.replace(key, ht.get(key));

		 System.out.print("\n\t>> ���� ���ڰ� ���������� ���� �Ϸ�Ǿ����ϴ�.");
      }
	 
	  // do while���� ���� ���� �ʱ�ȭ 
	  t = 0;

      if (sel==4)
      {
		 System.out.println("\n\t== ���� �������� : " + ((MovieVO)ht.get(key)).getmEndDate() + " ==");

		 do
		 {
			 // flag = false; // do while���� ���� ���� �ʱ�ȭ 

			 if (t>0)
				System.out.println("\n\t>> �Է��� ���ڰ� ��ȿ���� �ʽ��ϴ�.");
			 
			 while (true)
			 {
				 flag = false;

				 try
				 {
					System.out.print("\t>> ������ ���� ���ڸ� �Է��ϼ��� (YYYY-MM-DD) : ");
					 str = sc.next();
					 // ��ȭ ���� �޴��� �̵�, �޼ҵ� ����
					 if (str.equals("-1"))
						return;

					 // �Է��� �������� ���� ��������
					 String hoTime = ((MovieVO)ht.get(key)).getmOpenDate();		// �ش� key�� ������ �������� (�������� ������ �Ŀ� �ִٸ� ��ϵ����ʰ� �ϱ� ����)
					 yy = hoTime.substring(0, 4);
					 mm = hoTime.substring(5, 7);
					 dd = hoTime.substring(8, 10);
					 
					 oyy = Integer.parseInt(yy);
					 omm = Integer.parseInt(mm);
					 odd = Integer.parseInt(dd);
					 
					 //System.out.println("\t"+mm+"-"+dd);
					 // ���������� ����
					 yy = str.substring(0, 4);
					 mm = str.substring(5, 7);
					 dd = str.substring(8, 10);
					 
					 eyy = Integer.parseInt(yy);
					 emm = Integer.parseInt(mm);
					 edd = Integer.parseInt(dd);

					 String hi = str.substring(4,5);
					 
					 if (!hi.equals("-"))
						flag = true;

					 hi = str.substring(7, 8);	// �Է� ��� �񱳸� �ϱ����� 
					 
					 if (!hi.equals("-"))			// �������� �´���
						flag = true;

					 break;
				 }
				 catch (NumberFormatException e) // ���ڿ��� ���ڷ� ��ȯ�� ���ڿ��� ���� ���ڰ� �ƴ϶�� 
				 {
					 System.out.println("\n\t>> �߸� �Է��ϼ̽��ϴ�. �Է� ���Ŀ� ���� �Է����ּ���.\n");
				 }
				 catch (StringIndexOutOfBoundsException so)	// ���ڿ��� substring �� ���ڼ��� �����ϴٸ�
				 {
					 System.out.println("\n\t>> �߸� �Է��ϼ̽��ϴ�. �Է� ���Ŀ� ���� �Է����ּ���.\n");
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

		 System.out.print("\n\t>> ���� ���ڰ� ���������� ���� �Ϸ�Ǿ����ϴ�.");
      }

      if (sel==5)
      {
		 System.out.println("\n\t== ���� �ٰŸ� : " + ((MovieVO)ht.get(key)).getmStory() + " ==");
         System.out.print("\t>> ������ �ٰŸ��� �Է��ϼ��� : ");
         sc.nextLine();
         str = sc.nextLine();
		 // ��ȭ ���� �޴��� �̵�, �޼ҵ� ����
		 if (str.equals("-1"))
			return;

         ht.get(key).setmStory(str);
         ht.get(key).getmStory();
         ht.replace(key, ht.get(key));

		 System.out.print("\n\t>> �ٰŸ��� ���������� ���� �Ϸ�Ǿ����ϴ�.");
      }

      if (sel==6)
      {
		 System.out.println("\n\t== ���� �ٰŸ� : " + ((MovieVO)ht.get(key)).getmAge() + " ==");

		 while (true)
		 {
			 try
			 {
				System.out.print("\t>> ���� ���� ������ �Է��ϼ��� : ");
			    n = sc.nextInt();

				break;
			 }
			 catch (InputMismatchException e)
			 {
				 sc = new Scanner(System.in);
				System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
			 }
		 }
         
		 // ��ȭ ���� �޴��� �̵�, �޼ҵ� ����
		 if (n==-1)
			return;

         ht.get(key).setmAge(n);
         ht.get(key).getmAge();
         ht.replace(key, ht.get(key));

		 System.out.print("\n\t>> ���� ���� ���� ���������� ���� �Ϸ�Ǿ����ϴ�.");
      }

      if (sel==7)
      {
		 System.out.println("\n\t== ���� �� �󿵽ð� : " + ((MovieVO)ht.get(key)).getmTime() + "�� ==");

		 while (true)
		 {
			 try
			 {
				System.out.print("\t>> �� �󿵽ð��� �Է��ϼ��� : ");
			    n = sc.nextInt();

				break;
			 }
			 catch (InputMismatchException e)
			 {
				 sc = new Scanner(System.in);
				System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
			 }
		 }
        
		 // ��ȭ ���� �޴��� �̵�, �޼ҵ� ����
		 if (n==-1)
			return;

         ht.get(key).setmTime(n);
         ht.get(key).getmTime();
         ht.replace(key, ht.get(key));

		 System.out.print("\n\t>> �� �󿵽ð��� ���������� ���� �Ϸ�Ǿ����ϴ�.");
      }

      if (sel==8)
      {
		 System.out.println("\n\t== ���� ���� : " + ((MovieVO)ht.get(key)).getmStar() + " ==");

		 while (true)
		 {
			 try
			 {
				System.out.print("\t>> ������ �Է��ϼ��� : ");
			    n = sc.nextInt();

				break;
			 }
			 catch (InputMismatchException e)
			 {
				 sc = new Scanner(System.in);
				System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
			 }
		 }
         
		 // ��ȭ ���� �޴��� �̵�, �޼ҵ� ����
		 if (n==-1)
			return;

         ht.get(key).setmStar(n);
         ht.get(key).getmStar();
         ht.replace(key, ht.get(key));

		 System.out.print("\n\t>> ������ ���������� ���� �Ϸ�Ǿ����ϴ�.");
      }
      
   } // end chaMoive() ��ϵ� ��ȭ ���� ���� �޼ҵ� 

   // ��ȭ ���� �޼ҵ�
   public static void delMoive() 
   {
		Scanner sc = new Scanner(System.in);
		
		String key;
		
		System.out.println("\n\n");
		System.out.println("\t��� ��ȭ ����				      -1. ��ȭ ���� �޴�");
		System.out.println("\t================================================================");
		System.out.print("\t>> ������ ��ȭ ������ �Է��ϼ��� : ");
		key = sc.nextLine();
		// ��ȭ ���� �޴��� �̵�, �޼ҵ� ����
		if (key.equals("-1"))
			return;

		if (ht.containsKey(key))		// Key ���� ������ if�� ����
	    {
			ht.remove(key);				// ��ȭ������ ���� Hashtable���� �ش� ���� ���� 
			System.out.print("\n\t>> ���������� �����Ǿ����ϴ�.");
	    }
		else 
			System.out.println("\n\t>> �����Ͻ� ��ȭ�� ���������ʽ��ϴ�.");


   } // end delMoive()
   
  
  // ������ ��� �޼ҵ�
  public static void registerScreen()
  {
	 Scanner sc = new Scanner(System.in);
	 Calendar cal = Calendar.getInstance();

	 String key="";					// ��ȭ ������ �Է� ���� ����
	 int sel;						// ���� ���� 
	 int t=0;						// ����?����
	 String date="";
	 String room="";
	 int d, sh, sm;					// ���ڿ� ���� �ð��� ���� ���������� ���� ����	�� ó�� �ð��� ���� ������ �޴� ���� endTime ��궧����..
	 String ssh, ssm;				// ���� �ð��� ���� ���������� ���� ���� 
	 String strTime;				// �Է¹��� �ð� ������ ���ڿ��� ��ȯ�Ͽ� ���� ���� 
	 Integer sTime=0, eTime=0;		// ���ڿ��� ��ȯ�� �ð� ���ҷ� ��ȯ
	 int eh, em;					// ������ �ð��� ���� ���� ����
	 String seh, sem;				// ������ �ð��� ���� ���ڿ��� 
	 Integer serial;				// hashtable Ű���� ���� ���� 
	 String strSerial;				// hashtable Ű���� ������ ����
	 boolean flag;				    // ��ȿ�� �˻縦 ���� flag
	 String yy, mm, dd;					// ���� ���� ���� ���ڿ� ���� 
	 int oyy, omm, odd, pyy, pmm, pdd;	    // ����, ������ ��ȿ�� �˻縦 ���� ����
	 char[][] seat= new char[0][0];
	 

	 // -������ ���
	 System.out.println("\n\n");
	 System.out.println("\t�� ����Ʈ ���			      -1. ��ȭ ���� �޴�");
	 System.out.println("\t================================================================");
	 
	 // ��¥ �޾ƿ���
	 Date today = cal.getTime();					
	 cal.add(Calendar.DAY_OF_WEEK, 1);
	 Date tomorrow = cal.getTime();
	 cal.add(Calendar.DAY_OF_WEEK, 1);
	 Date thedayAfter = cal.getTime();
	 
	 SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");

	
	 // �� ���� ����
	 do
	 {
		 if (t>0)
			 System.out.println("\t>> ��ȿ���� ���� �޴��Դϴ�. �ٽ� �������ּ���."); 
		 while (true)
		 {
			 try
			 {
				System.out.println("\t 1. "+(dformat.format(today)));
				System.out.println("\t 2. "+(dformat.format(tomorrow)));
				System.out.println("\t 3. "+(dformat.format(thedayAfter)));
				System.out.println();
				System.out.print("\t>> �����ڸ� �������ּ��� : ");
				sel = sc.nextInt();

				break;
			 }
			 catch (InputMismatchException e)
			 {
				sc = new Scanner(System.in);
				System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
			 }
		 }
		 
		 // ��ȭ ���� �޴��� �̵�, �޼ҵ� ����
		 if (sel==-1)
			return;

		 t++;
	 }
	 while (sel>3 || sel<1);
	 
	 strSerial = String.valueOf(sel);		// ù��°�ڸ� �ø��� ������ ����, ���ڿ��� ��ȯ�Ͽ� ���ð��� ����	

	 // ���õ� ���� ������ ���
	 switch (sel)
	 {
	    case 1: date = (dformat.format(today)); break;
		case 2: date = (dformat.format(tomorrow)); break;
		case 3: date = (dformat.format(thedayAfter)); break;
	 }
	 
	 // ���� ���ڿ� ���� ��ȿ�� �˻縦 ���� (������)
	 yy = date.substring(0, 4);
	 mm = date.substring(5, 7);				// �Է¹��� ���� ���� mm ������ ���
	 dd = date.substring(8, 10);				// �Է¹��� ���� �ϸ� dd ������ ���

	 // ���������� ��ȯ
	 pyy = Integer.parseInt(yy);
	 pmm = Integer.parseInt(mm);			
	 pdd = Integer.parseInt(dd);
	 
	 t=0;		// ���� do while ���� ���� ���� �ʱ�ȭ 
	 System.out.println("\n");
	
	 // �󿵰� ����
	 do
	 {
		 if (t>0)
		 {
			 System.out.println("\t>> ��ȿ���� ���� �޴��Դϴ�. �ٽ� �������ּ���."); 
			 System.out.println();
		 }
		 
		 while (true)
		 {
			 try
			 {
				System.out.println("\t1. 1��(2D)");
				System.out.println("\t2. 2��(2D)");
				System.out.println("\t3. 3��(4D)");
				//System.out.println("\t4. 4��(4D)");
				System.out.println();
				System.out.print("\t>> �󿵰��� �������ּ��� : ");
				sel = sc.nextInt();

				break;
			 }
			 catch (InputMismatchException e)
			 {
				sc = new Scanner(System.in);
				System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
			 }
		 }
		 
		 // ��ȭ ���� �޴��� �̵�, �޼ҵ� ����
		 if (sel==-1)
			return;

		 t++;
	 }
	 while (sel>4 || sel<1);

	 strSerial += String.valueOf(sel)+"0";	// �ι�°,����° �ø��� ������ ����, ���� �ø��� ���� ���ð��� 0�� ���ڿ��� ��ȯ�Ͽ� ��ħ
	 serial = Integer.parseInt(strSerial);	// ����, �󿵰� ���õ� ex) 110 or 230... �ð� ��� �� ��� ���� + ���ٰ���..
	 
	 // ���õ� �󿵰� ������ ���
	 switch (sel)
	 {
		case 1: room = "1��(2D)"; seat = settingSeat(1); break;
		case 2: room = "2��(2D)"; seat = settingSeat(2); break;
		case 3: room = "3��(4D)"; seat = settingSeat(3); break;
		//case 4: room = "4��(4D)"; break;
	 }
	 
	 sc.nextLine();
		
	 System.out.println();

	 int s = 0;	// ���� do while���� �������� ��������?����� �ʱ�ȭ 

	 Set<Integer> ks = ht2.keySet();
	 Iterator<Integer> ik = ks.iterator();

	 System.out.println();
	 System.out.println("\n\t			[ �� ��Ȳ ]");
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

	 int j = 0;	// ���� do while���� �������� ��������?����� �ʱ�ȭ 

	 // �����ٸ��� ��ȭ ���� 
	 do
	 {
		 flag = false;	// do while���� ���� ���� �ʱ�ȭ 
		 t = 0;			// �� do while���� ���� ���� �ʱ�ȭ
		 if (j>0)
			System.out.println("\n\t>> ��ȭ ������ ������ ���� �Ұ����մϴ�."); 
		 do
		 {
			 if (t>0)
			 {
				 System.out.printf("\n\t>> %s �� ��ϵ��� ���� ��ȭ �Դϴ�.\n", key); // �ش� do while���� �ѹ� �̻� �ݺ��Ǹ� �ش� �޼��� ���
				 System.out.println();
			 }

			 System.out.print("\t>> ���� ��ȭ ������ �Է��ϼ��� : ");
			 key = sc.nextLine();
			 // ��ȭ ���� �޴��� �̵�, �޼ҵ� ����
			 if (key.equals("-1"))
				return;

			 t++;
		 }
		 while (!ht.containsKey(key));	// ������ ��ȭ�� �ִ��� ������ �˻� ������ do while �ٽ� �����Ͽ� �޾ƿ��� 

		String kot = ((MovieVO)ht.get(key)).getmOpenDate();		// ������ ��ȭ ������ �������� 
		
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
			if (omm>pmm)					// �ش� �������� �󿵿����� ũ�ٸ� 
				flag = true;
			else if (omm==pmm && odd>pdd)	// �ش� �������� �󿵿��� ������ �������� ���Ϻ��� ũ�ٸ� 
				flag = true;

		}

		j++;

	 }
	 while (flag);
	 
	 j=t=0; // ���� do while ���� ���� ���� �ʱ�ȭ 
	 
	 System.out.println();

	 // �� ���� �ð� �Է�
	 do
	 {
		 flag = false;

		 if (t>0)
		 {
			 System.out.println("\t>> �ش� �ð��� �̹� ���õǾ� �ֽ��ϴ�.");
			 System.out.println();
		 }
		 
		 while (true)
		 {
			 try
			 {
				do
				{
					if (j>0)
						System.out.println("\t>> �Է��Ͻ� �ð��� ��ȿ���� �ʽ��ϴ�.");

					System.out.print("\t>> �� ���� �ð��� �Է����ּ��� (�ð��� �Է�) : ");
					sh = sc.nextInt(); 

					j++;
				}
				while (sh>24 || sh<-1);

				break;
			 }
			 catch (InputMismatchException e)
			 {
				sc = new Scanner(System.in);
				System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
			 }
		 }
		 
		 // ��ȭ ���� �޴��� �̵�, �޼ҵ� ����
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
						System.out.println("\t>> �Է��Ͻ� �ð��� ��ȿ���� �ʽ��ϴ�.");

					System.out.print("\t>> �� ���� �ð��� �Է����ּ��� (�и� �Է�)   : ");
					sm = sc.nextInt();

					j++;
				 }
				 while (sm>59 || sm<-1);
				
				break;
			 }
			 catch (InputMismatchException e)
			 {
				sc = new Scanner(System.in);
				System.out.println("\t>> �߸� �Է��ϼ̽��ϴ�. ������ �Է��� �ּ���.\n");
			 }
		 }
		 //System.out.print("\t>> �� ���� �ð��� �Է����ּ��� (�и� �Է�)   : ");
		 //sm = sc.nextInt();
		 
		 // ��ȭ ���� �޴��� �̵�, �޼ҵ� ����
		 if (sm==-1)
			return;
		 
		 strTime = String.valueOf(sh);  // �Է¹��� �� ���� �ð��� ���ڿ��� ��ȯ
		 if (sm<10)						// �Է¹��� �� ���� ���� ���� �ڸ����
			 strTime += "0"+String.valueOf(sm);	// ���ڿ��� ��ȯ �� 0�� ���ϰ� �ð��� ��� ������ �����ֱ� 
		 else 
			 strTime += String.valueOf(sm);	// ���ڿ��� ��ȯ �� �ð��� ��� ������ �����ֱ�
		 
		 sTime = Integer.parseInt(strTime); // �ð� �񱳸� ���� ���������� ��ȯ (�� ���� �ð�)

		 // �ش� ��ȭ�� ����Ÿ�� �������� (������ �ð��� ���ϱ� ����)
		 int runtime = ((MovieVO)ht.get(key)).getmTime();		// �� �� �ð� �������� 
		
		 // ������ �ð� ���ϱ� 
		 eh = sh+(runtime/60);	// �Է� ���� �󿵽��� �ð��� �ѻ󿵽ð��� �ð��� ���� ���� ���� eh ������ �ֱ�
		 em = sm+(runtime%60);	// �Է� ���� �󿵽��� �а� �ѻ󿵽ð��� ���� ���� ���� ���� em ������ �ֱ� 
		 
		 if (em>=60)			// em�� 60�� �Ѵ´ٸ�
		 {
			 eh += (em/60);		// �ð��� ���� eh ������ �����ֱ� 
			 em %= 60;			// ���� ���� em ������ �־��ֱ� 
		 }
		 
		 // hashtable�� ���� �󿵽��� �ð�
		 if (sh<10)
			ssh = "0"+String.valueOf(sh);
		 else 
			ssh = String.valueOf(sh);
		 
		 // hashtable�� ���� �󿵽��� ��
		 if (sm<10)
			ssm = "0"+String.valueOf(sm);
		 else
			ssm = String.valueOf(sm);	
		 
		 //System.out.println("\t"+ssh+":"+ssm);
		 
		 // hashtable�� ���� ������ ��
		 if (eh<10)
			seh = "0"+String.valueOf(eh);
		 else 
			seh = String.valueOf(eh);
		 
		 // hashtable�� ���� ������ ��
		 if (em<10)
			sem = "0"+String.valueOf(em);
		 else
			sem = String.valueOf(em);

		 // �׽�Ʈ Ȯ��
		 //System.out.println("\t"+eh+em);

		 strTime = String.valueOf(eh);				// ���ڿ��� ���� (Hashtable �߰��� ����)
		 if (em<10)
			 strTime += "0"+String.valueOf(em);
		 else 
			 strTime += String.valueOf(em);

		 eTime = Integer.parseInt(strTime);			// �ð� �񱳸� ���� ���� ������ ����(������ð�)
         
		 // �ð� �ߺ� ���θ� �˻��ϱ� ���� ����
		 Set<Integer> keySet = ht2.keySet();
		 Iterator<Integer> iterKey = keySet.iterator(); 
		 
		  while (iterKey.hasNext())
		  {
			 // keys���� ���� serial Ű ���
			 Integer keys = iterKey.next();
			 Integer seK = (keys/10);			// ht2(�󿵽�����) Hashtable key ��/10	// ������ ���ڿ� ���� �󿵽������� ���ϱ� ����
			 //System.out.println("\t"+keys);
			  
			 Integer Kse = (serial/10); // ����� ��ȭ�� serail/10

			 if (Kse == seK)
			 {	
				 // ���� ������, �󿵰��� ���۽ð�, ����ð� �޾ƿ� ���������� ����
				 strTime = ((ScreenVO)ht2.get(keys)).getmSh();
				 strTime += ((ScreenVO)ht2.get(keys)).getmSm();
				 Integer startTime = Integer.parseInt(strTime);

				 strTime = ((ScreenVO)ht2.get(keys)).getmEh();
				 strTime += ((ScreenVO)ht2.get(keys)).getmEm();
				 Integer endTime = Integer.parseInt(strTime);
				
				 // �ð� �ߺ� ���� �˻� 
				 if ((sTime>=startTime) && (eTime<=endTime))	// ������ ���� �ð��� �⺻ �����ٿ� ��ϵ� �ð��� ���ų� Ŭ�� ������ð��� �����ٿ� ����ִ� ����ð����� �۰ų� ���ٸ�  
				 {
					flag = true;
					break;
				 }
				 else if ((sTime<startTime) && (eTime>startTime) && (eTime<=endTime || eTime>endTime))	// ������ �ð��� �⺻ �����ٿ� ��ϵ� �ð����� �۰�, ������ �ð��� ���۽ð����� Ŭ��
			  	 {																						// ������ �ð��� �⺻ ������ �ð��� ���ų� �۴ٸ� �Ǵ� ������ð��� ���� ������ð����� ũ�ٸ�
					flag = true;
					break;
				 }
				 else if (sTime>=startTime && sTime<=endTime)		// ������ ���� �ð��� �����ٿ� ��ϵ� �ð��� ���ų� ũ�� ���� �Ű��� �����ٿ� ��ϵ� ������ð��� ���ų� �۴ٸ�
				 {
					flag = true;
					break;
				 }
			 }
		 } // �ð� �ߺ�Ȯ���� ���� while�� 

		 t++;
	 }
	 while (flag);	// �� �ð� �Է� �޴� do while�� 
	 
	 System.out.println("\t>> �������� ���� ��� �Ϸ�Ǿ����ϴ�.");
	 
	 s++;
	 serial += s;
	 
	 ht2.put(serial,(new ScreenVO(date, room, key, ssh, ssm, seh, sem, seat)));		// �󿵽����� Hatshtable�� ������ ����
     // �߰� ��
     rmNum=Integer.toString(serial);
     rmNum=rmNum.substring(1,2);
	 //ldate = Integer.parseInt(date);//k
	 //ht3.put(date+" "+ssh+":"+ssm+"~"+seh+":"+sem, key); //key�� ���� 
     // �߰� ��
     //rm1.put(date+" "+ssh+":"+ssm+"~"+seh+":"+sem, rmNum);

	 
  } // registerScreen() ������ ��� �޼ҵ�

  // �󿵰� ��Ȳ ��� �޼ҵ�
  public static void printScreen()
  {
	 Set<Integer> keySet = ht2.keySet();
	 Iterator<Integer> iterKey = keySet.iterator();

	 System.out.println();
	 System.out.println("\n\t			[�� ��Ȳ ��ü ���]");

	 while (iterKey.hasNext())
	 {
		 Integer key = iterKey.next();
		 System.out.println();
		 System.out.println("\tserialNum."+key);
		 System.out.println("\t================================================================");
		 System.out.println(ht2.get(key));
	 }
  }	// end printScreen() �󿵰� ��Ȳ ��� �޼ҵ�

  // ������(�� ������)�� �����ϴ� �޼ҵ�
  public static void delScreen()
  {
	 Scanner sc = new Scanner(System.in);
	 
	 Integer serial;

	 System.out.println("\n");
	 System.out.println("\t�� ����Ʈ ����		              -1. ��ȭ ���� �޴�");
	 System.out.println("\t================================================================");
	 System.out.print("\t>> ������ ����Ʈ�� �ø���ѹ��� �Է��ϼ��� : ");
	 serial = sc.nextInt();
	 // ��ȭ ���� �޴��� �̵�, �޼ҵ� ����
	 if (serial==-1)
			return;

	 if (ht2.containsKey(serial))		// �󿵽����� Hashtable�� �ش� key�� �ִٸ� 
	 {
		 ht2.remove(serial);			// �ش� ������ �����ض� 
		 System.out.println("\n\t>> ���������� �����Ǿ����ϴ�.");
	 }
	 else 
		System.out.println("\t>> �Է��Ͻ� �ø����� ��ȿ�����ʽ��ϴ�.");
  } // end delScreen()
  
  // �¼� ����Ʈ
  public static char[][] settingSeat(int sel)
  {
	char[][] seat;

	if (sel == 1 || sel == 2)
	{
		seat = new char[10][12];

		int n = 0;
		// 1, 2�� �¼� �Է�
		for (int i=0; i<seat.length; i++)
		{
			for (int j=0; j<seat[0].length; j++)
			{
				if (n==1 || n==4 || n==7)
				{
					seat[i][j] = '��';
					
					n++;

					if (n==8)
						n=0;
				}
				else 
				{
					seat[i][j] = '��';
					n++;
				}	
			}
		}
	}
	else
	{
		seat = new char[4][8];

		int n = 0;
		// 3�� �¼� �Է�
		for (int i=0; i<seat.length; i++)
		{
			for (int j=0; j<seat[0].length; j++)
			{
				if (n==1 || n==4 || n==7)
				{
					seat[i][j] = '��';
					
					n++;

					if (n==8)
						n=0;
				}
				else 
				{
					seat[i][j] = '��';
					n++;
				}	
			}
		}
	}

	return seat;
  }
} // end class AdminMovie