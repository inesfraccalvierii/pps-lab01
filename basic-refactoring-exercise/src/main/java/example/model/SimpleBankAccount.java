package example.model;

/**
 * This class represent a particular instance of a BankAccount.
 * In particular, a Simple Bank Account allows always the deposit
 * while the withdrawal is allowed only if the balance greater or equal the withdrawal amount
 */
public class SimpleBankAccount implements BankAccount {

    private double balance;
    private final AccountHolder holder;
    private final static double WITHDRAWAL_FEE = 1;


    public SimpleBankAccount(final AccountHolder holder, final double balance) {
        this.holder = holder;
        this.balance = balance;
    }
    @Override
    public AccountHolder getHolder(){
        return this.holder;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(final int userID, final double amount) throws Exception {
        if (checkUser(userID)) {
            this.balance += amount;
        }
    }

    @Override
    public void withdraw(final int userID, final double amount) throws Exception {
        //double finalAmount = amount + WITHDRAWAL_FEE;
        if(checkUser(userID)){
            if (isWithdrawAllowed(amount)) {
                this.balance -= amount;
            }
            else {
                throw new Exception("Insufficient funds.");
            }
        }
    }

    private boolean isWithdrawAllowed(final double amount){
        return this.balance >= amount;
    }

    private boolean checkUser(final int id) throws Exception {
        if(this.holder.getId() == id){
            return true;
        } else {
            throw new Exception("Wrong Id.");
        }
    }
}
