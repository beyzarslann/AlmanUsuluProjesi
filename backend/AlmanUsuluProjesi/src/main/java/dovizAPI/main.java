package dovizAPI;

public class main {
    public static void main(String[] args) {

        CurrencyFactory factory = new CurrencyFactory(Moneys.US_DOLLAR);
        Currency cur = factory.getCurrency();     // get selected money unit current infos

        String date = cur.getDate();     // 12/14/2018


        String name = cur.getName();    // US DOLLAR


        float buying = cur.getBuyingPrice();   // 5.3672


        float selling = cur.getSellingPrice();   // 5.3887


        boolean isForex = cur.isForex();   // false dollar not forex

        System.out.println(buying);
    }
}
