package com.hcjt.troubleshoot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * You have been contracted to work on a banking application. The company that
 * owns the application recently tried to add some new features to the
 * application. There are some bugs in the implementations of the new features,
 * and you have been asked to fix them. To make your job easier, the intended
 * functionality of the application and its features have been documented and a
 * test suite is provided to verify the implementation.
 * 
 * You can refer to the comments in the code to know which parts of the code can
 * be considered reliable and which parts might contain a bug.
 **/

public class BankingApplication {
	/**
	 * Represents a bank. The bank contains accounts which are stored in a map of
	 * unique id to account.
	 */
	public static class Bank {
		private HashMap<Integer, Account> customerAccounts;

// ------------------------------------------------------
// New features (potential bugs here)
// ------------------------------------------------------

		/**
		 * Pays interest to all accounts. Accounts earn 5% interest unless they have
		 * type 'Savings', then they earn 10%
		 *
		 * @return total Amount of interest paid across all accounts
		 */
		public double generateInterest() {
			double totalInterestPaid = 0;
			double interestRate = 0.05;
			for (Account account : this.customerAccounts.values()) {
				if (account.type.equals("Savings")) {
					interestRate = 0.10;
				}
				double accountInterest = account.getBalance() * interestRate;
				totalInterestPaid += accountInterest;
				account.setBalance(account.getBalance() + accountInterest);
			}

			return totalInterestPaid;
		}

		/**
		 * Transfer money between two accounts. If a transfer request is invalid for any
		 * reason, it should do nothing.
		 *
		 * @param sourceAccount    Account to transfer money from
		 * @param destAccount      Account to transfer money to
		 * @param amountToTransfer Amount to transfer between accounts
		 */
		public void transfer(Account sourceAccount, Account destAccount, double amountToTransfer) {
			if (sourceAccount == null || destAccount == null || amountToTransfer < 0) {
				return;
			}
			sourceAccount.setBalance(sourceAccount.getBalance() - amountToTransfer);
			destAccount.setBalance(destAccount.getBalance() + amountToTransfer);
		}

		/**
		 * Transfers money between two users. If a transfer request is invalid for any
		 * reason, it should do nothing. Money can be pulled from any of the source
		 * user's accounts and will deposited in one of the dest user's accounts.
		 *
		 * @param sourceUserId     UserId to transfer money from
		 * @param destUserId       UserId to transfer money to
		 * @param amountToTransfer Amount to transfer between accounts
		 */
		public void makePaymentBetweenUsers(String sourceUserId, String destUserId, double amountToTransfer) {
			double availableBalance = 0;
			Account destAccount = null;
			ArrayList<Account> sourceUserAccounts = new ArrayList<Account>();

			for (Account acct : this.customerAccounts.values()) {
				if (acct.userId.equals(sourceUserId)) {
					sourceUserAccounts.add(acct);
					availableBalance += acct.getBalance();
				}
				if (destAccount == null && acct.userId.equals(destUserId)) {
					destAccount = acct;
				}
			}

			if (availableBalance < amountToTransfer) {
				return;
			}

			for (Account acct : sourceUserAccounts) {
				if (amountToTransfer <= 0) {
					return;
				}
				if (acct.getBalance() >= amountToTransfer) {
					this.transfer(acct, destAccount, amountToTransfer);
					return;
				} else {
					this.transfer(acct, destAccount, acct.getBalance());
					amountToTransfer -= acct.getBalance();
				}
			}
		}

		/**
		 * Generates an id for a new account and inserts into customerAccounts.
		 *
		 * @param account account to add to the bank
		 * @return id of newly generated account
		 */
		public int addAccount(Account account) {
			int maxCurrentAccountId = this.customerAccounts.size();
			int newAccountId = maxCurrentAccountId + 1;
			this.customerAccounts.put(newAccountId, account);
			return newAccountId;
		}

// --------------------------------------------------------------------
// Existing functionality (no bugs past this point)
// You should read this code to understand it, but you won't need to
// modify anything here.
// --------------------------------------------------------------------

		public Bank(Map<Integer, Account> accounts) {
			this.customerAccounts = new HashMap<Integer, Account>();
			for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {
				Account a = entry.getValue();
				this.customerAccounts.put(entry.getKey(), new Account(a.userId, a.type, a.balance));
			}
		}

		/**
		 * Deletes account by Id.
		 *
		 * @param accountId
		 */
		public void deleteAccount(int accountId) {
			this.customerAccounts.remove(accountId);
		}

		/**
		 * Gets account by Id.
		 *
		 * @param accountId
		 */
		public Account getAccount(int accountId) {
			return this.customerAccounts.get(accountId);
		}

		/**
		 * Gets total balance from all accounts belonging to a user.
		 *
		 * @param userId
		 */
		public double getTotalUserBalance(String userId) {
			double total = 0;
			for (Account acct : this.customerAccounts.values()) {
				if (acct.userId.equals(userId))
					total += acct.getBalance();
			}
			return total;
		}

		/**
		 * Gets total balance of all customer accounts.
		 *
		 * @returns {number}
		 */
		public double getTotalBankBalance() {
			double total = 0;
			for (Account acct : this.customerAccounts.values()) {
				total += acct.getBalance();
			}

			return total;
		}

		public String toString() {
			String out = "";
			for (Map.Entry<Integer, Account> entry : this.customerAccounts.entrySet()) {
				out += entry.getKey() + ": " + entry.getValue() + "\n";

			}
			return out;
		}
	}

	/**
	 * Represents a bank account with user, type, and balance. An account cannot
	 * have a negative balance.
	 */
	public static class Account {
		public String userId;
		public String type;
		public double balance;

		public Account(String userId, String type, double balance) {
			this.userId = userId;
			this.type = type;
			this.balance = balance;
		}

		public String toString() {
			return "userId: " + this.userId + ", type: " + this.type + ", balance: " + this.balance;
		}

		/**
		 * Sets balance of an account. Accounts cannot have a negative balance.
		 *
		 * @param {account} account
		 * @param {number}  balance
		 */
		public void setBalance(double balance) {
			if (balance >= 0) {
				this.balance = balance;
			}
		}

		/**
		 * Gets balance of an account.
		 *
		 * @param {account} account
		 * @param {number}  balance
		 */
		public double getBalance() {
			return this.balance;
		}
	}

	/**
	 * Class for testing Bank functionality.
	 */
	public static class BankTester {
		private Map<Integer, Account> accounts;

		public BankTester() {
			this.accounts = new HashMap<Integer, Account>();
			this.accounts.put(1, new Account("Ben123", "Checking", 110));
			this.accounts.put(2, new Account("Ben123", "Savings", 20));
			this.accounts.put(3, new Account("Ben123", "Checking", 200));
			this.accounts.put(4, new Account("Amy456", "Savings", 1000));
		}

		// Bank should correctly calculate the amount of interest to pay to accounts
		public void testInterestPayments() {
			Bank b = new Bank(this.accounts);
			double expectedInterest = (0.05 * 110) + (0.1 * 20) + (0.05 * 200) + (0.1 * 1000);
			double actualInterest = b.generateInterest();
			if (actualInterest != expectedInterest) {
				System.out.println("Interest Payment Test Failed");
			} else {
				System.out.println("Interest Payment Test Passed!");
			}
		}

		// After making transfers between accounts, the total amount of
		// money in the bank should remain the same
		public void testAccountTransfers() {
			Bank b = new Bank(this.accounts);
			double totalCustomerAccountsBalanceBeforeTransfers = b.getTotalBankBalance();
			Account acct1 = b.getAccount(1);
			Account acct2 = b.getAccount(2);
			b.transfer(null, null, 1000);
			b.transfer(acct1, acct2, 50);
			b.transfer(acct2, acct1, 200);
			b.transfer(acct1, acct2, 120);
			double totalCustomerAccountsBalanceAfterTransfers = b.getTotalBankBalance();
			if (totalCustomerAccountsBalanceBeforeTransfers != totalCustomerAccountsBalanceAfterTransfers) {
				System.out.println("Account Transfer Test Failed");
			} else {
				System.out.println("Account Transfer Test Passed!");
			}
		}

		// After making transfers between users, the total balance held by each user
		// should have changed appropriately
		public void testUserTransfers() {
			Bank b = new Bank(this.accounts);
			double originalBenTotal = b.getTotalUserBalance("Ben123");
			double originalAmyTotal = b.getTotalUserBalance("Amy456");
			b.makePaymentBetweenUsers("Ben123", "Amy456", 10);
			b.makePaymentBetweenUsers("Ben123", "Amy456", 50);
			b.makePaymentBetweenUsers("Ben123", "Amy456", 230);
			double expectedBenTotalAfterTransfers = originalBenTotal - 10 - 50 - 230;
			double expectedAmyTotalAfterTransfers = originalAmyTotal + 10 + 50 + 230;
			double actualBenTotalAfterTransfers = b.getTotalUserBalance("Ben123");
			double actualAmyTotalAfterTransfers = b.getTotalUserBalance("Amy456");

			if (expectedBenTotalAfterTransfers != actualBenTotalAfterTransfers
					|| expectedAmyTotalAfterTransfers != actualAmyTotalAfterTransfers) {
				System.out.println("User Transfer Test Failed");
			} else {
				System.out.println("User Transfer Test Passed!");
			}
		}

		// After creating and deleting an equal number of accounts,
		// the total number of accounts should not have changed
		public void testAccountManagement() {
			Bank b = new Bank(this.accounts);
			int originalNumberOfAccounts = b.customerAccounts.size();
			b.addAccount(new Account("Cat789", "Checking", 100));
			b.deleteAccount(1);
			b.deleteAccount(2);
			b.addAccount(new Account("Dave012", "Savings", 2300));
			int finalNumberOfAccounts = b.customerAccounts.size();
			if (originalNumberOfAccounts != finalNumberOfAccounts) {
				System.out.println("Account Management Test Failed");
			} else {
				System.out.println("Account Management Test Passed!");
			}
		}
	}

	// Run Test Cases
	public static void main(String[] args) {
		BankTester tester = new BankTester();
		tester.testInterestPayments();
		tester.testAccountTransfers();
		tester.testUserTransfers();
		tester.testAccountManagement();
	}
}
