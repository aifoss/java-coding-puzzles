import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sofia on 4/23/18.
 */

/**
 * Code written during a timed online coding test.
 */

/**
 private static final String TEXT =  "I am a {0} account with {1,number,#} units of {2} currency";

 public static void main(String args[] ) throws Exception {

 List<BankAccount> accounts = new ArrayList<BankAccount>();
 accounts.add(new SavingsAccount("USD",3));//Savings
 accounts.add(new SavingsAccount("EUR",2));//Savings
 accounts.add(new CheckingAccount("HUF",100));//Checking
 accounts.add(new CheckingAccount("COP",10000));//Checking
 accounts.add(new BrokerageAccount("GBP",2));//Brokerage
 accounts.add(new BrokerageAccount("INR",600));//Brokerage

 accounts.stream().forEach(
     account -> System.out.println(
         MessageFormat.format(TEXT,
             new Object[]{
             account.getAccountType().getName(),//make this work
             account.getUnits(),//make this work
             account.getCurrency()//make this work
     })));
 }

 Please create your classes below.
 DO NOT USE Access Modifiers for your classes
 */
public class BankAccounts {

    private static final String TEXT =  "I am a {0} account with {1,number,#} units of {2} currency";

    enum AccountType {
        SAVINGS("Savings"),
        CHECKING("Checking"),
        BROKERAGE("Brokerage");

        private String name;

        AccountType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class BankAccount {
        protected AccountType accountType;
        protected int units;
        protected String currency;

        public BankAccount(String currency, int units) {
            this.currency = currency;
            this.units = units;
        }

        public AccountType getAccountType() {
            return accountType;
        }

        public String getCurrency() {
            return currency;
        }

        public int getUnits() {
            return units;
        }
    }

    static class SavingsAccount extends BankAccount {
        public SavingsAccount(String currency, int units) {
            super(currency, units);
            this.accountType = AccountType.SAVINGS;
        }
    }

    static class CheckingAccount extends BankAccount {
        public CheckingAccount(String currency, int units) {
            super(currency, units);
            this.accountType = AccountType.CHECKING;
        }
    }

    static class BrokerageAccount extends BankAccount {
        public BrokerageAccount(String currency, int units) {
            super(currency, units);
            this.accountType = AccountType.BROKERAGE;
        }
    }





    public static void main(String[] args) {
        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(new SavingsAccount("USD",3)); //Savings
        accounts.add(new SavingsAccount("EUR",2)); //Savings
        accounts.add(new CheckingAccount("HUF",100)); //Checking
        accounts.add(new CheckingAccount("COP",10000)); //Checking
        accounts.add(new BrokerageAccount("GBP",2)); //Brokerage
        accounts.add(new BrokerageAccount("INR",600)); //Brokerage

        accounts.stream().forEach(
                account -> System.out.println(
                        MessageFormat.format(TEXT,
                                new Object[]{
                                        account.getAccountType().getName(), //make this work
                                        account.getUnits(), //make this work
                                        account.getCurrency() //make this work
                                })));
    }

}
