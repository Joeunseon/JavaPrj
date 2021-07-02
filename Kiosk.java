import java.io.IOException;

public class Kiosk
{
	public static void main(String[] args) throws IOException, InterruptedException
	{
		Admin ad = new Admin();
		Members mb = new Members();
		
		// 회원 추가
		Members.oldMem();

		do
		{
			ad.pw();
		}
		while (true);

		//User.startUser();
	}
}