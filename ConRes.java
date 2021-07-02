import java.util.Scanner;

/*
	Title	 : ConRes
	Function : 영화 예매 완료 후 부여받은 예매번호를 통해 예매정보 확인을 제공한다.
	author	 : 소서현, 심혜진
*/

//-------------------------[ 예매 확인 클래스 ]-------------------------

// 설명 순서 : 
// Pay클래스의 34번 : RsvNumbVO 클래스 생성 및 toString 정의
// ReserveTikets클래스의 463번 : 상영시간표의 값에서 날짜만 짤라옴
// ReserveTikets클래스의 466~7번 : rMem 의 값에 03-21 부분을 자르고 뒤에 0부터 시작하는 숫자를 합쳐서 예매번호 완성
// Pay클래스의 259번 : nRsv 해시테이블 생성
// Pay클래스의 549번 : nRsv에 put
// 그다음 ConRes 시작

class ConRes extends Record
{ 
	// 예매번호 입력 받는 메소드
	public static void conInput() 
	{
		// 사용자 입력을 위한 스캐너 인스턴스 생성
		Scanner sc = new Scanner(System.in);

		System.out.print("\n\t>> 예매번호를 입력해주세요 : ");									
		rm = sc.next();												//-- rm 은 Record 클래스에 존재
		System.out.println();										//-- 개행						
		
		// 입력 완료 후 예매번호 확인 메소드 호출
		conRes();
	}
	
	// 예매번호 확인하는 메소드
	public static void conRes()
	{
		// 사용자가 입력한 rm 값이 Pay클래스에 있는 nRsv 해시테이블의 key 값에 존재한다면 예매정보 출력
		if (Pay.nRsv.containsKey(rm))
		{
			System.out.println("\t>> 고객님의 예매내역입니다.");				
			System.out.println("\t====================================");		
			// key값(예매번호 rm)에 맞는 value값(예매정보객체의 hashcode)를 출력
			System.out.print(Pay.nRsv.get(rm));									
		}

		// 존재하지 않다면 안내 문구 출력
	   else 
		    System.out.println("\t>> 고객님의 예매 내역이 존재하지 않습니다.");
	}
}