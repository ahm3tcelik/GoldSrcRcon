import com.ahmetc.goldsrcrcon.GoldSrcRcon;

public class Main {
    public static void main(String[] args) {
        try {
            GoldSrcRcon rcon = new GoldSrcRcon("213.238.173.37", 27015, "111546861");
            String result = rcon.sendConsoleCommand("stats");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
