/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bankjava;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Polyphemus
 */
interface Account {
 void deposit(double amount);
 double getBalance();
}
class BaseAccount implements Account {
    private double balance;
    private double overdraft;
    private double loanAmount;
    ArrayList<String> holders = new ArrayList<>();
    ArrayList<Transaction> transactions = new ArrayList<>();
    ArrayList<LoanHistory> loanHistory = new ArrayList<>();
    private double interestRate;
    private int overdraftLimit;
    private int accountNumber;
    private int withdrawLimit;
    private String accountType;
    private int id;
 //Set up a new account
 public BaseAccount(String accOwner, String accType, int _id, int wdLimit, double intRate, int odLimit, int _accountNumber) {
 //changed to include withdraw limit, interest rate and overdraft limit
    holders.add(accOwner);
    accountNumber = _accountNumber;
    accountType = accType;
    id = _id;
    withdrawLimit = wdLimit; //id, withdraw limit, interest rate and overdraft limit added to thebase account
    interestRate = intRate;
    overdraftLimit = odLimit;
 }
 //Add account holder
 public void AddAccHolder(String accOwner) {
    holders.add(accOwner);
    
 }
 public int getAccountNum()
 {
     return accountNumber;
 }
 public ArrayList<String> getHolderName() { //changed to arraylist to show all account holders
    return holders; //returns all holder names not just the first one
 }
 public int getID() {
    return id;
 }
 
 public int getWithdrawLimit() {
    return withdrawLimit;
 }
 public double getOverdraft() {
    return overdraft;
 }
 public int getOverdraftLimit() {
    return overdraftLimit;
 }
 public void changeOverdraft(double limit) {
    overdraft = limit;
 }
 public double getLoanAmount() {
    return loanAmount;
 }
 public void setLoanAmount(int loan) {
    loanAmount = loan;
 }
 public void withdraw(double amount) {
    balance -= amount;
 }
 @Override
 public void deposit(double amount) {
    balance += amount;
 }
 @Override
 public double getBalance() {
    return balance;
 }
 public String getAccType() {
 return accountType;
 }
 public double payInterest() {
    double charge;
    if (balance >= 0) {
        charge = interestRate * balance; 
    } else {
        charge = 0.05 * balance; 
    }
    balance += charge;
    return charge;
 }
 public double getInterestRate() {
    return interestRate;
 }
 public void addTransaction(Date d, String transType, double amount) {
    transactions.add(new Transaction(d, transType, amount));
 }
 public ArrayList<Transaction> getTransactions() {
    return transactions;
 }
 public void addLoanTransaction(Date d, String loanPaymentType, double amount) {
    loanHistory.add(new LoanHistory(d, loanPaymentType, amount));
 }
 public ArrayList<LoanHistory> getLoanPayments() {
    return loanHistory;
 }
}
class Transaction {
    protected Date time;
    String transactionType;
    protected double amount;
    public Transaction(Date _time, String _transactionType, double _amount) {
        time = _time;
        transactionType = _transactionType;
        amount = _amount;
 }
    public Date getDate() {
        return time;
    }
    public String getType() {
        return transactionType;
    }
    public double getAmount() {
        return amount;
    }
}
class LoanHistory {
    protected Date time;
    String loanPaymentType;
    protected double loanAmount;
    public LoanHistory(Date _time, String _loanPaymentType, double _amount) {
        time = _time;
        loanPaymentType = _loanPaymentType;
        loanAmount = _amount;
    }
    public Date getLoanDate() {
        return time;
    }
    public String getLoanType() {
        return loanPaymentType;
    }
    public double getLoanAmount() {
        return loanAmount;
    }
}
class Loan extends BaseAccount {
//loan acts as an account
    public Loan(String owner, int _id, int accNum) {
        super(owner, "Loan", _id, 0, 0, 0, accNum);
 }
}
class BusinessAccount extends BaseAccount {
    public BusinessAccount(String businessName,  int _id, int accNum) {
        super(businessName,  "Business", _id, 2000, 0.02, 5000, accNum);
    }
}
class CurrentAccount extends BaseAccount {
    public CurrentAccount(String owner,  int _id, int accNum) {
        super(owner,  "Current", _id, 500, 0.01, 1000, accNum);
    }
}
class IRAccount extends BaseAccount {
    public IRAccount(String owner,  int _id, int accNum) {
        super(owner,  "IR", _id, 200, 0.02, 500, accNum);
    }
}
class StudentAccount extends BaseAccount {
    public StudentAccount(String owner,  int _id, int accNum) {
        super(owner,  "Student", _id, 300, 0.01, 2000, accNum);
 }
}
class SMBAccount extends BaseAccount {
    public SMBAccount(String owner,  int _id, int accNum) {
        super(owner,  "SMB", _id, 500, 0.02, 500, accNum);
    }
}
class SavingsAccount extends BaseAccount {
    public SavingsAccount(String owner,  int _id, int accNum) {
        super(owner,  "Savings", _id, 500, 0.05, 0, accNum);
 }
}
/***6. New account types ***/
class HighInterestAccount extends BaseAccount {
    public HighInterestAccount(String owner,  int _id, int accNum) {
        super(owner,  "High Interest", _id, 1000, 0.08, 500, accNum);
 }
}
class IslamicAccount extends BaseAccount {
    public IslamicAccount(String owner,  int _id, int accNum) {
        super(owner,  "Islamic", _id, 500, 0, 0, accNum);
 }
}
class PrivateAccount extends BaseAccount {
    public PrivateAccount(String owner,  int _id, int accNum) {
        super(owner,  "Private", _id, 1000, 0.4, 3000, accNum);
 }
}
class LCRAccount extends BaseAccount {
//low credit rating account
    public LCRAccount(String owner,  int _id, int accNum) {
        super(owner,  "LCR", _id, 200, 0, 0, accNum);
 }
}
class ChildAccount extends BaseAccount {
//low credit rating account
    public ChildAccount(String owner,  int _id, int accNum) {
        super(owner,  "LCR", _id, 200, 0, 0, accNum);
 }
}
class InternationalAccount extends BaseAccount {
//low credit rating account
    public InternationalAccount(String owner,  int _id, int accNum) {
        super(owner,  "LCR", _id, 200, 0, 0, accNum);
 }
}
class DisabilityAccount extends BaseAccount {
//low credit rating account
    public DisabilityAccount(String owner,  int _id, int accNum) {
        super(owner,  "LCR", _id, 200, 0, 0, accNum);
 }
}
class FeesInterestAccount extends BaseAccount {

    public FeesInterestAccount(String owner,  int _id, int accNum) {
        super(owner,  "FeesInterest", _id, 0, 0, 0, accNum);
 }
}

 class Control {
    private final Timer timer = new Timer();
    
    private final HashMap< Integer, BaseAccount> accounts = new HashMap<Integer, BaseAccount>();
    private int accNumGenerator;
    public Control() {
        //accounts.add(new FeesInterestAccount("Fees and Interest", accNumGenerator, 0));//initialiseaccount for interest and penalties
        /***4. Interest payments***/
         /*timer.schedule(new TimerTask() {
       
        public void run() {
           for (int i = 0; i < accounts.size(); i++) {
                double charge = accounts.get(i).payInterest();
                accounts.get(0).withdraw(charge);
                accounts.get(i).addTransaction(new Date(), "Interest", (charge));
            }
            System.out.println("Interest has been paid.");
        }
        }, 30000, 30000);
        */
        
    }
    public void createAccount(int option2, String name, int id)
    {
    
            accNumGenerator++;
            switch (option2) {
            case 1:
                accounts.put(accNumGenerator, new CurrentAccount(name, id, accNumGenerator));
            break;
            case 2:
                accounts.put(accNumGenerator, new SavingsAccount(name, id, accNumGenerator));
            break;
            case 3:
                accounts.put(accNumGenerator,new StudentAccount(name,  id, accNumGenerator));
            break;
            case 4:
                accounts.put(accNumGenerator,new BusinessAccount(name,  id, accNumGenerator));
            break;
            case 5:
                accounts.put(accNumGenerator,new SMBAccount(name,  id, accNumGenerator));
            break;
            case 6:
                accounts.put(accNumGenerator,new IRAccount(name,  id, accNumGenerator));
            break;
            case 7:
                accounts.put(accNumGenerator,new HighInterestAccount(name,  id, accNumGenerator));
            break;
            case 8:
                accounts.put(accNumGenerator,new IslamicAccount(name,  id, accNumGenerator));
            break;
            case 9:
                accounts.put(accNumGenerator,new PrivateAccount(name,  id, accNumGenerator));
            break;
            case 10:
                accounts.put(accNumGenerator,new InternationalAccount(name,  id, accNumGenerator));
                case 11:
                accounts.put(accNumGenerator,new DisabilityAccount(name,  id, accNumGenerator));
            break;
            case 12:
                accounts.put(accNumGenerator,new PrivateAccount(name,  id, accNumGenerator));
            break;
            case 13:
                accounts.put(accNumGenerator,new LCRAccount(name,  id, accNumGenerator));
            
        
        System.out.println("Your account number is " + accNumGenerator);
        //This line tells you the auto generated account number, which is different to the customerID
         }
    }
        public void deposit(int accNum, double amount)
        {
                accounts.get(accNum).deposit(amount);
                
                if ("Loan".equals(accounts.get(accNum).getAccType())) {
                    accounts.get(accNum).addLoanTransaction(new Date(), "Loan Payment", amount);
                    System.out.println("You have �" + -(accounts.get(accNum).getBalance()) + " of your loan still to pay off.");
                 }       
                else {
                    accounts.get(accNum).addTransaction(new Date(), "Deposit", amount);
                 }
       }
        public double viewBalance(int accNum)
        {
                    accounts.get(accNum).addTransaction(new Date(), "View Balance", accounts.get(accNum).getBalance());
                    return accounts.get(accNum).getBalance();
         //changed from amount: used to show last amount used not balance
        }
       public String withdraw(int accNum, double amount)
       {
                if ((accounts.get(accNum).getBalance() + accounts.get(accNum).getOverdraft()) < amount) 
                {
                    return "Insufficient funds. This transaction has been cancelled.";
                } 
                else if (accounts.get(accNum).getWithdrawLimit() >= amount) 
                {
                    accounts.get(accNum).withdraw(amount);
                    accounts.get(accNum).addTransaction(new Date(), "Withdraw", amount);
                    return amount + " withdrawn";
                } 
                else 
                {
                    return "The maximum withdrawal for a " + accounts.get(accNum).getAccType() + "account is �" + accounts.get(accNum).getWithdrawLimit() + ". This transaction has been cancelled.";
               }
         }
           
            
       
      public String transfer(int accNum, int accNum2, double amount)
      {
                if ((accounts.get(accNum).getBalance() + accounts.get(accNum).getOverdraft()) >= amount) 
                {
                    accounts.get(accNum).withdraw(amount);
                    accounts.get(accNum2).deposit(amount);
                    if ("Loan".equals(accounts.get(accNum).getAccType())) 
                    {
                        accounts.get(accNum).addLoanTransaction(new Date(), "Loan Payment", amount);
                        return "You have " + -(accounts.get(accNum).getBalance()) + " of yourloan still to pay off.";
                    } 
                    else 
                    {
                        accounts.get(accNum2).addTransaction(new Date(), "Transfer in", amount); //changedto transfer in to show receipt of funds
                    }
                accounts.get(accNum).addTransaction(new Date(), "Transfer out", -(amount));
          
            return "Payment has been successfully transferred.";
            }
          
             else {
                return "There are insufficient funds to make this transfer.";
              
            }
        }
           
       public String addAccountHolder(String fName, String lName)
       {
                    //accounts.get(accNum).AddAccHolder(lName + " " + lName);
                   return "Success";
      }
           
        public JTable ViewAllAccounts(int id, JTable table)
        {
            Iterator iter = accounts.values().iterator();
            int col = 0;
            int row = 0;
            while(iter.hasNext())
            {
                BaseAccount acc = (BaseAccount) iter.next();
                if(acc.getID() == id)
                {
                table.setValueAt(acc.getAccType(), row, col);
                table.setValueAt(acc.getHolderName(), row, col);
                table.setValueAt(acc.getBalance(), row, col);
                table.setValueAt(acc.getBalance() + acc.getOverdraft(), row, col);
                //table.setValueAt(acc.getAccountNum(), row, col);
                table.setValueAt(acc.getWithdrawLimit(), row, col);
                table.setValueAt(acc.getOverdraft(), row, col);
                table.setValueAt(acc.getInterestRate(), row, col);
                col++;
                if(col == 8)
                {
                    col=0;
                    row++;
                }
                }
            }
            return table;
            }
        public JTable viewTransactions(JTable table, int accNum)
        {
           BaseAccount acc = accounts.get(accNum);
          Iterator iter = acc.getTransactions().iterator();
             int col =0;
             int row = 0;
                    while (iter.hasNext()) 
                    {
                        Transaction trans = (Transaction)iter.next();
                        table.setValueAt((trans).getType(), row, 0); 
                        col++;
                        table.setValueAt((trans).getDate().toString(), row, 1); 
                        col++;
                        table.setValueAt((trans).getAmount(), row, 2);
                        col++;
                        if(col == 3 && iter.hasNext())
                        {
                            col = 0;
                            row++;
                        }
                    }
            return table;
        }
         /***3. Overdraft facilities ***/
        public void overdraft(int accNum, double limit)
        {
                    accounts.get(accNum).changeOverdraft(limit);
                    accounts.get(accNum).addTransaction(new Date(), "New Overdraft Limit Set", limit);
        } 
                
       public void Loan (int accNum, double loan)
       {
                    if ("Loan".equals(accounts.get(accNum).getAccType())) {
                        System.out.println("Funds must be paid into a bank account.");
                    
                    } 
                    else {
                        
                        if (loan > 0 && loan <= 100000) 
                        {
                        }
                        else {
                            System.out.println("Please choose a value above zero up to �100,000");
                        }
                       
                    }
         }
        public void loanPayment(int accNum)
        {
                    
                        
        }
       
    }

public class BankJava {
    static Home home;
 public static void main(String args[]) {
     home = new Home();
     home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 1st change :D it was >> do_nothing _on_close

 }
}











