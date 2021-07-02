import java.util.Scanner;
import java.io.IOException;
import java.util.Hashtable;
import java.util.InputMismatchException;

/*
	Title	 : BuySnacks
	Function : 사용자가 음료/팝콘을 구매할 수 있도록 기능 구현
	author	 : 소서현, 조은선
*/

/*
	작성자 : 조은선 
	※ 21-03-21 12:58 snackMenuDisp() 에서 초기화면으로 돌아가기위해 snackSelect() do while문 snackMenuDisp()메소드로 이동,
					  사용자에게 입력 받은 sel 값을 snackSelect()에서 가져오기 위해 매개변수 추가 
*/

// * 수정사항*
// 21-05-23 예외처리 진행
//			처음으로, 이전으로 이동 기능 추가
//			재고 업데이트 코드 주석 처리, 결제 완료 후 결제 메소드에서 업데이트 필요
// 21-05-30 추가 구매시 선택 수량 표시
//          추가 구매시 입력한 수량으로 변경되도록(기존수량 + 추가입력받은수량 였음)

//-------------------------[ 음료/팝콘 구매 클래스 ]-------------------------

class BuySnacks extends Record
{
	static int snackSu;												//-- 입력받는 구매 수량
	static final String[] snackName = {"콜라", "사이다", "팝콘"};	//-- 스낵 메뉴 이름들을 담은 배열
	static int[] snackSuArr = new int[3];							//-- 스낵별 최종 구매 수량을 담는 배열


	// <선택한 메뉴, 구매 수량> 을 담을 ht
	public static Hashtable<String, Integer> ht = new Hashtable<String, Integer>();

	
	// 스낵 메뉴판 출력 및 입력 메소드
	public static void snackMenuDisp() throws InterruptedException, IOException
	{
		sc = new Scanner(System.in);
		
		int n = 0;

		System.out.println("\n");
		System.out.println("\t메뉴판				       00. 처음으로");
		System.out.println("\t===================================================");
		System.out.println("\t1. 콜라(2,000원)");
		System.out.println("\t2. 사이다(2,000원)");
		System.out.println("\t3. 팝콘(3,000원)");
		System.out.println();
		

		while (true)
		{
			try
			{
				// 1~3 이외의 수를 입력할 시 메뉴선택문이 반복하도록 do-while문 사용
				do
				{
					if (n>0)
					{
						System.out.println("\t>> 유효하지 않은 메뉴입니다. 다시 선택해주세요.");
						System.out.println();
					}
					
					System.out.print("\t>> 구매하실 메뉴를 선택하세요(1~3) : ");
					answer = sc.next();
					
					// 00 을 입력하면 메소드 종료하여 사용자메인메뉴로 이동
					if (answer.equals("00"))
						return;

					sel = Integer.parseInt(answer);

					n++;
				}
				while (sel>3 || sel<1);

				break;
			}
			catch (NumberFormatException e)
			{
				sc = new Scanner(System.in);
				System.out.println("\t>> 잘못 입력하셨습니다. 정수만 입력해 주세요.\n");
			}
		}
		
		// 입력 완료 시 sel값을 매개변수로 받는 snackSelect 메소드로 이동
		snackSelect(sel);			//-- ★ 확인필요!!!
	}

	// 스낵 구매수량 입력 메소드
	public static void snackSelect(int sel) throws InterruptedException, IOException
	{
		// 입력받기 위한 스캐너 인스턴스 생성
		sc = new Scanner(System.in);
		
		int n = 0;
		// 스낵 재고를 담을 배열 변수
		// StocksVO 클래스의 재고 변수들 값을 가져올 수 있도록 getter 를 사용
		int su[] = {StocksVO.getCoke(), StocksVO.getCida(), StocksVO.getPop()};		//-- {팝콘 재고량, 사이다 재고량, 팝콘 재고량}
		
		// 양수의 구매수량을 입력받지 않으면 반복하도록 do-whil문 사용
		System.out.println("\n\n");
		System.out.printf("\t>> 선택 항목 : %s         00. 초기화면 01. 이전화면\n", snackName[sel-1]);	//-- sel에 입력했던 값을 매개변수로 바로 받아 스낵이름 배열에 적용
		System.out.println("\t====================================================");		
		
		// 추가 구매라면...
		if (snackSuArr[sel-1]!=0)
		{
			System.out.printf("\t>> 현재 %s의 선택 수량 : %d\n", snackName[sel-1], snackSuArr[sel-1]);
		}

		while (true)
		{
			try
			{
				do
				{
					if (n>0)
					{
						System.out.println("\t>> 수량은 0 이상의 정수만 입력 가능합니다.");
						System.out.println();
					}

					System.out.printf("\t>> 『%s』의 구매 수량을 입력해주세요 : ",snackName[sel-1]);
					answer = sc.next();
					
					// 00 을 입력하면 메소드 종료하여 사용자메인메뉴로 이동
					if (answer.equals("00"))
						return;
					
					// 01을 입력하면 스낵 메뉴판으로 이동. 확인 필요 
					if (answer.equals("01"))
					{
						snackMenuDisp();
						return;
					}

					snackSu = Integer.parseInt(answer);

					n++;
				}
				while (snackSu<0);

				break;

			}
			catch (NumberFormatException e)
			{
				System.out.println("\t>> 잘못 입력하셨습니다. 다시 입력해 주세요.\n");
			}
		}
		
		n = 0;
		// 구매 수량이 잔고 수량을 넘을 시 나오는 구문
		if (snackSu>su[sel-1])
		{
			System.out.println("\n\n");
			System.out.print("        -----------------------------------------------------");		
			System.out.printf("\n\t※ 현재 『%s』의 최대가능 구매수량은 『%d』입니다.\n\t   추가구매를 원하시면 관리자를 불러주세요.\n", snackName[sel-1], su[sel-1]);
			System.out.println("        -----------------------------------------------------");	
			
			while (true)
			{
				try
				{
					do
					{
						if (n>0)
						{
							System.out.println("\t>> 수량은 0 이상의 정수만 입력 가능합니다.");
							System.out.println();
						}

						System.out.printf("\n\t>> 『%s』의 구매 수량을 재입력해주세요 : ",snackName[sel-1]);
						answer = sc.next();
						
						// 00 을 입력하면 메소드 종료하여 사용자메인메뉴로 이동
						if (answer.equals("00"))
							return;
						
						// 01을 입력하면 스낵 메뉴판으로 이동. 확인 필요 
						if (answer.equals("01"))
						{
							snackMenuDisp();
							return;
						}

						snackSu = Integer.parseInt(answer);

						n++;
					}
					while (snackSu<0);

					break;

				}
				catch (NumberFormatException e)
				{
					System.out.println("\t>> 잘못 입력하셨습니다. 다시 입력해 주세요.\n");
				}
			}
		}
		
										
		// 첫 번째 구매수량 입력완료 이후 구매수량확인 및 추가구매문의 메소드 호출
		snackCheck();
	}
		
	// 스낵 총구매수량확인 및 추가구매문의 메소드
	public static void snackCheck() throws InterruptedException, IOException
	{	
		sc = new Scanner(System.in);
		// 스낵 수 이름에 따라 배열에 구매수량 넣기
		//snackSuArr[sel-1] += snackSu;
		snackSuArr[sel-1] = snackSu;
		// ht의 key값(선택메뉴)의 value 값에 구매수량 넣기
		ht.put(snackName[sel-1], snackSuArr[sel-1]);
		

		// 구매수량 안내 확인 문구 출력
		System.out.println("\n\n\t구매 수량 확인");
		System.out.println("\t===================================================");
		System.out.printf("\t>> 콜라 %d개, 사이다 %d개, 팝콘 %d개를 선택하셨습니다.\n", snackSuArr[0], snackSuArr[1], snackSuArr[2]);
		

		// 추가구매질문에 대한 답이 Y, y, N, n 이 아닐 때 반복하도록 do-while문 사용
		do
		{
			System.out.print("\t>> 추가 구매를 하시겠습니까?(Y/N) : ");
			// 한 문자를 입력받을 수 있도록 System.in.read() 사용 -- answer 변수 선언은 Record 클래스에 존재 
			answer = sc.next();
			//System.in.skip(2);
		}
		while (!answer.equals("y") && !answer.equals("Y") && !answer.equals("n") && !answer.equals("N"));
			
		// 추가구매 할 시 스낵메뉴판 다시 호출
		if (answer.equals("y") || answer.equals("Y"))
			snackMenuDisp();

		// 추가구매 안할 시 각각 해당 스낵에 맞게 최종 구매량을 snackSuArr 에 넣음
		if (answer.equals("n") || answer.equals("N"))
		{
			if (ht.containsKey(snackName[sel-1]))				// → key 값(해당 스낵이름)이 존재한다면
			{
				if(ht.get(snackName[sel-1]) != null)			// → 그리고 키 값을 찾았으면
				{
					int add = snackSuArr[sel-1] + snackSu;		// → add라는 변수에 기존 구매수량 + 추가 구매수량 값을 넣는다.
					ht.put(snackName[sel-1], add);				// → ht의 key 값에 맞게 add 값을 더한다.
				}
			}
			// 기존 선택 스낵의 수량 추가가 아니라 새로운 스낵을 추가로 구매했을 시 해당 스낵 이름에 맞게 그대로 snackSu 를 넣음 
			else 
				ht.put(snackName[sel-1], snackSu);


			// 스낵에서 구매하는 총금액 계산
			// 콜라와 사이다는 2000원, 팝콘은 3000원으로 설정
			snackPayTot = 2000*(snackSuArr[0]+snackSuArr[1]) + 3000*snackSuArr[2];

			
			// 스낵메뉴별 재고를 나타내는 setCoke 에 매개변수로 음수의 총구매량을 대입해서 재고 업데이트 실행
			//StocksVO.setCoke(-snackSuArr[0]);
			//StocksVO.setCida(-snackSuArr[1]);
			//StocksVO.setPop(-snackSuArr[2]);
			// 결제쪽에서 진행	
			
			// 입력한 구매수량 리셋 (다음 사용자를 위해)
			for (int i=0; i<3; i++)						
				snackSuArr[i] = 0;
			
			// 다 완료되면 Pay클래스의 결제수단선택 메소드를 호출
			Pay.selPay();
		}
	}
	
}
