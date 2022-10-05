import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Account account = new Account();
        BankThread thread = new BankThread(account);

        thread.start();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите сумму которую хотите снять");
            account.withdraw(scanner.nextInt());

            account.waiting(thread);
        }
    }
}

class Account {
    int balance = 0;

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void waiting(BankThread thread){

        thread.run();

    }

    public void deposit(int dep) {

        setBalance(getBalance() + dep);
    }

    public void withdraw(int with) {

        if (getBalance() < with) {
            System.out.println("Ошибка! На счету недостаточно средств");
        } else {
            System.out.println("Успешное снятие, текущий баланс: " + (getBalance() - with));
            setBalance(getBalance() - with);
        }
    }
}


class BankThread extends Thread{

    Account account;
    BankThread(Account account){
        this.account=account;
    }

    public void run(){

       for(int i = 0; account.getBalance() < 5000; i++){
           account.deposit(500);
           System.out.println("Счёт пополнен на 500, текущий баланс: " + account.getBalance());
       }
    }
}
