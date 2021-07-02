import java.util.Scanner;

//-------------------------[ Record 클래스 ]-------------------------

public class Record
{
	public static Integer sel;      //-- 메뉴 선택 값을 담는 변수
	static Scanner sc;				//-- Scanner 메모리 할당
	static String answer;			//-- char에서 String으로 변경

	static int snackPayTot=0; 
	static int payCash, payCard, payHap;	//-- 음료/팝콘에서의 총금액, 영화에서의 총금액
	static int moviePayTot=0; //-- 테스트용
	//static String memFlag="";
	static int payTot = moviePayTot+snackPayTot;
	static String cMem;				//-- 예매번호
	static String uSelTit;
	static int selMovieDate;
    static int payi = 2;			//-- 잔고 부족 공지 판별용
	static int iLast=1;
	static int temp;				//-- 좌석번호 임시 담기용
	static int w;					//-- Pay 클래스 문화의 날 판별변수
	static int su;					//-- Pay 클래스 포인트 차감금액
	static int cul;					//-- 문화의 날 차감액 담은 변수
	static int couponNum;		//-- 교환권 카운팅
	static String rm;				//-- ConRes
	static boolean cant;
}