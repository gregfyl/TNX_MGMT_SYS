package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * TransactionManager Class
 * This is the controller class.
 * It controls user interface from window, handles the transactions, and displays the results to the window.
 */

public class MainController {
	/**
	 * Add output to text area
	 * @param content Output content.
	 */
	void addOutput(String content) {
		if (output.getText().isEmpty()) {
			output.setText(content);
		} else {
			String historical_logs = "\n======================Historical Logs======================\n";
			output.setText(content + historical_logs + output.getText().replace(historical_logs, "\n"));
		}
	}

	/** To avoid magic number. */
	private static final int EMPTY = 0;

	/** Account database. */
	private AccountDatabase accounts = new AccountDatabase();

	/** TextArea to display output. */
	@FXML
	private TextArea output;

	/** TextFields in Open section. */
	@FXML
	private TextField Ofname, Olname, Obalance, month, day, year;

	/** RadioButtons in Open section. */
	@FXML
	private RadioButton OChecking, OSavings, OMoneyMarket;

	/** ToggleGroup in Open section. */
	@FXML
	private ToggleGroup OAccount;

	/** CheckBoxs in Open section. */
	@FXML
	private CheckBox OdirectDeposit, OisLoyal;

	/**
	 * Add an account to the database.
	 * @param event ActionEvent.
	 */
	@FXML
	void add(ActionEvent event) {
		String fname = Ofname.getText();
		String lname = Olname.getText();
		if (fname.equals("") || lname.equals("")) {
			addOutput("Please fill out the name.\n");
			return;
		}

		if (month.getText().equals("") || day.getText().equals("") || year.getText().equals("")) {
			addOutput("Please fill out the date.\n");
			return;
		}
		String dateStr = month.getText() + "/" + day.getText() + "/" + year.getText();
		Date date = Date.toDate(dateStr);
		if (date == null) {
			addOutput(dateStr + " is not a valid date.\n");
			month.clear(); day.clear(); year.clear();
			return;
		}

		if (Obalance.getText().equals("")) {
			addOutput("Please fill out the balance.\n");
			return;
		}
		double balance = 0;
		try {
			balance = Double.valueOf(Obalance.getText());
		} catch (NumberFormatException e) {
			addOutput(Obalance.getText() + " is not a valid balance.\n");
			Obalance.clear();
			return;
		}
		if (balance <= 0) {
			addOutput("Amount cannot be " + Obalance.getText() + ".\n");
			Obalance.clear();
			return;
		}

		if (!OChecking.isSelected() && !OSavings.isSelected() && !OMoneyMarket.isSelected()) {
			addOutput("Please select account type.\n");
			return;
		}

		Account newAccount = null;
		if (OChecking.isSelected()) {
			boolean directDeposit = Boolean.valueOf(OdirectDeposit.isSelected());
			newAccount = new Checking(new Profile(fname, lname), balance, date, directDeposit);
		} else if (OSavings.isSelected()) {
			boolean isLoyal = Boolean.valueOf(OisLoyal.isSelected());
			newAccount = new Savings(new Profile(fname, lname), balance, date, isLoyal);
		} else {
			newAccount = new MoneyMarket(new Profile(fname, lname), balance, date, EMPTY);
		}

		if (accounts.add(newAccount)) {
			addOutput("Account opened and added to the database.\n");
		} else {
			addOutput("Account is already in the database.\n");
		}
	}

	/**
	 * Disable Loyal Customer and enable Direct Deposit.
	 * @param event ActionEvent.
	 */
	@FXML
	void OselectChecking(ActionEvent event) {
		OdirectDeposit.setDisable(false);
		OisLoyal.setSelected(false);
		OisLoyal.setDisable(true);
	}

	/**
	 * Disable Direct Deposit and enable Loyal Customer.
	 * @param event ActionEvent.
	 */
	@FXML
	void OselectSavings(ActionEvent event) {
		OdirectDeposit.setSelected(false);
		OdirectDeposit.setDisable(true);
		OisLoyal.setDisable(false);
	}

	/**
	 * Disable Direct Deposit and Loyal Customer.
	 * @param event ActionEvent.
	 */
	@FXML
	void OselectMoneyMarket(ActionEvent event) {
		OdirectDeposit.setSelected(false);
		OdirectDeposit.setDisable(true);
		OisLoyal.setSelected(false);
		OisLoyal.setDisable(true);
	}

	/**
	 * Clear Open section.
	 * @param event ActionEvent.
	 */
	@FXML
	void Oclear(ActionEvent event) {
		Ofname.clear();
		Olname.clear();
		Obalance.clear();
		month.clear();
		day.clear();
		year.clear();
		OChecking.setSelected(false);
		OSavings.setSelected(false);
		OMoneyMarket.setSelected(false);
		OdirectDeposit.setDisable(false);
		OisLoyal.setDisable(false);
		OdirectDeposit.setSelected(false);
		OisLoyal.setSelected(false);
	}

	/** TextFields in Close section. */
	@FXML
	private TextField Sfname, Slname;

	/** RadioButtons in Close section. */
	@FXML
	private RadioButton SChecking, SSavings, SMoneyMarket;

	/** ToggleGroup in Close section. */
	@FXML
	private ToggleGroup SAccount;

	public void search(ActionEvent actionEvent) {
		String fname = Sfname.getText();
		String lname = Slname.getText();
		if (fname.equals("") || lname.equals("")) {
			addOutput("Please fill out the name.\n");
			return;
		}

		if (!SChecking.isSelected() && !SSavings.isSelected() && !SMoneyMarket.isSelected()) {
			addOutput("Please select account type.\n");
			return;
		}

		String targetAccountType = null;
		if (SChecking.isSelected()) {
			targetAccountType = "Checking";
		} else if (SSavings.isSelected()) {
			targetAccountType = "Savings";
		} else {
			targetAccountType = "MoneyMarket";
		}

		ArrayList<Integer> target_accounts = accounts.findByName(targetAccountType, fname, lname);
		StringBuilder output = new StringBuilder("Search Result:\n");
		for (int targetAccount : target_accounts) {
			output.append(accounts.getAccount(targetAccount)).append("\n");
		}
		addOutput(output.toString());
	}

	/**
	 * Clear Close section.
	 * @param event ActionEvent.
	 */
	@FXML
	void Sclear(ActionEvent event) {
		Sfname.clear();
		Slname.clear();
		SChecking.setSelected(false);
		SSavings.setSelected(false);
		SMoneyMarket.setSelected(false);
	}

	/** TextFields in Close section. */
	@FXML
	private TextField Cfname, Clname;

	/** RadioButtons in Close section. */
	@FXML
	private RadioButton CChecking, CSavings, CMoneyMarket;

	/** ToggleGroup in Close section. */
	@FXML
	private ToggleGroup CAccount;

	/**
	 * Remove an account from the database.
	 * @param event ActionEvent.
	 */
	@FXML
	void close(ActionEvent event) {
		String fname = Cfname.getText();
		String lname = Clname.getText();
		if (fname.equals("") || lname.equals("")) {
			addOutput("Please fill out the name.\n");
			return;
		}

		if (!CChecking.isSelected() && !CSavings.isSelected() && !CMoneyMarket.isSelected()) {
			addOutput("Please select account type.\n");
			return;
		}

		Account targetAccount = null;
		if (CChecking.isSelected()) {
			targetAccount = new Checking(new Profile(fname, lname), EMPTY, null, false);
		} else if (CSavings.isSelected()) {
			targetAccount = new Savings(new Profile(fname, lname), EMPTY, null, false);
		} else {
			targetAccount = new MoneyMarket(new Profile(fname, lname), EMPTY, null, EMPTY);
		}

		if (accounts.remove(targetAccount)) {
			addOutput("Account closed and removed from the database.\n");
		} else {
			addOutput("Account does not exist.\n");
		}
	}

	/**
	 * Clear Close section.
	 * @param event ActionEvent.
	 */
	@FXML
	void Cclear(ActionEvent event) {
		Cfname.clear();
		Clname.clear();
		CChecking.setSelected(false);
		CSavings.setSelected(false);
		CMoneyMarket.setSelected(false);
	}

	/** TextFields in Deposit section. */
	@FXML
	private TextField Dfname, Dlname, Damount;

	/** RadioButtons in Deposit section. */
	@FXML
	private RadioButton DChecking, DSavings, DMoneyMarket;

	/** ToggleGroup in Deposit section. */
	@FXML
	private ToggleGroup DAccount;

	/**
	 * Deposit money to an account.
	 * @param event ActionEvent.
	 */
	@FXML
	void deposit(ActionEvent event) {
		String fname = Dfname.getText();
		String lname = Dlname.getText();
		if (fname.equals("") || lname.equals("")) {
			addOutput("Please fill out the name.\n");
			return;
		}

		if (Damount.getText().equals("")) {
			addOutput("Please fill out the amount.\n");
			return;
		}
		double amount = 0;
		try {
			amount = Double.valueOf(Damount.getText());
		} catch (NumberFormatException e) {
			addOutput(Damount.getText() + " is not a valid amount.\n");
			Damount.clear();
			return;
		}
		if (amount <= 0) {
			addOutput("Amount cannot be " + Damount.getText() + ".\n");
			Damount.clear();
			return;
		}

		if (!DChecking.isSelected() && !DSavings.isSelected() && !DMoneyMarket.isSelected()) {
			addOutput("Please select account type.\n");
			return;
		}

		Account targetAccount = null;
		if (DChecking.isSelected()) {
			targetAccount = new Checking(new Profile(fname, lname), EMPTY, null, false);
		} else if (DSavings.isSelected()) {
			targetAccount = new Savings(new Profile(fname, lname), EMPTY, null, false);
		} else {
			targetAccount = new MoneyMarket(new Profile(fname, lname), EMPTY, null, EMPTY);
		}

		if (accounts.deposit(targetAccount, amount)) {
			addOutput(String.format("%,.2f", amount) + " deposited to account.\n");
		} else {
			addOutput("Account does not exist.\n");
		}
	}

	/**
	 * Clear Deposit section.
	 * @param event ActionEvent.
	 */
	@FXML
	void Dclear(ActionEvent event) {
		Dfname.clear();
		Dlname.clear();
		Damount.clear();
		DChecking.setSelected(false);
		DSavings.setSelected(false);
		DMoneyMarket.setSelected(false);
	}

	/** TextFields in Withdraw section. */
	@FXML
	private TextField Wfname, Wlname, Wamount;

	/** RadioButtons in Withdraw section. */
	@FXML
	private RadioButton WChecking, WSavings, WMoneyMarket;

	/** ToggleGroup in Withdraw section. */
	@FXML
	private ToggleGroup WAccount;

	/**
	 * Withdraw money from an account.
	 * @param event ActionEvent.
	 */
	@FXML
	void withdraw(ActionEvent event) {
		String fname = Wfname.getText();
		String lname = Wlname.getText();
		if (fname.equals("") || lname.equals("")) {
			addOutput("Please fill out the name.\n");
			return;
		}

		if (Wamount.getText().equals("")) {
			addOutput("Please fill out the amount.\n");
			return;
		}
		double amount = 0;
		try {
			amount = Double.valueOf(Wamount.getText());
		} catch (NumberFormatException e) {
			addOutput(Wamount.getText() + " is not a valid amount.\n");
			Wamount.clear();
			return;
		}
		if (amount <= 0) {
			addOutput("Amount cannot be " + Wamount.getText() + ".\n");
			Wamount.clear();
			return;
		}

		if (!WChecking.isSelected() && !WSavings.isSelected() && !WMoneyMarket.isSelected()) {
			addOutput("Please select account type.\n");
			return;
		}

		Account targetAccount = null;
		if (WChecking.isSelected()) {
			targetAccount = new Checking(new Profile(fname, lname), EMPTY, null, false);
		} else if (WSavings.isSelected()) {
			targetAccount = new Savings(new Profile(fname, lname), EMPTY, null, false);
		} else {
			targetAccount = new MoneyMarket(new Profile(fname, lname), EMPTY, null, EMPTY);
		}

		int code = accounts.withdrawal(targetAccount, amount);
		if (code == 0) {
			addOutput(String.format("%,.2f", amount) + " withdrawn from account.\n");
		} else if (code == 1) {
			addOutput("Insufficient funds.\n");
		} else {
			addOutput("Account does not exist.\n");
		}
	}

	/**
	 * Clear Withdraw section.
	 * @param event ActionEvent.
	 */
	@FXML
	void Wclear(ActionEvent event) {
		Wfname.clear();
		Wlname.clear();
		Wamount.clear();
		WChecking.setSelected(false);
		WSavings.setSelected(false);
		WMoneyMarket.setSelected(false);
	}

	/** RadioButtons in Print section. */
	@FXML
	private RadioButton PDate, PFname, PLname;

	/** ToggleGroup in Print section. */
	@FXML
	private ToggleGroup Print;

	/**
	 * Print accounts.
	 * @param event ActionEvent.
	 */
	@FXML
	void print(ActionEvent event) {
		if (!PFname.isSelected() && !PLname.isSelected() && !PDate.isSelected()) {
			addOutput("Please select print method.\n");
			return;
		}
		if (PFname.isSelected()) {
			addOutput(accounts.printAccount("first name"));
		} else if (PLname.isSelected()) {
			addOutput(accounts.printAccount("last name"));
		} else {
			addOutput(accounts.printAccount("date opened"));
		}
	}

	/**
	 * Clear Print section.
	 * @param event ActionEvent.
	 */
	@FXML
	void Pclear(ActionEvent event) {
		PDate.setSelected(false);
		PFname.setSelected(false);
		PLname.setSelected(false);
	}

	/**
	 * Import the database from file.
	 * @param event ActionEvent.
	 */
	@FXML
	void settle(ActionEvent event) {
		accounts.settle();
		addOutput("All accounts have been settled.\n");
	}

	/**
	 * Import the database from file.
	 * @param event ActionEvent.
	 */
	@FXML
	void importFile(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.setTitle("Select File");
		File file = fc.showOpenDialog(null);
		if (file == null) {
			return;
		}

		try {
			addOutput(accounts.importFile(file));
		} catch (FileNotFoundException e) {
			addOutput("File not found.\n");
		}
	}

	/**
	 * Export the database to file.
	 * @param event ActionEvent.
	 */
	@FXML
	void exportFile(ActionEvent event) {
		addOutput(accounts.exportFile());
	}

	/**
	 * Restart the program.
	 * @param event ActionEvent.
	 */
	@FXML
	void restart(ActionEvent event) {
		Oclear(event);
		Cclear(event);
		Dclear(event);
		Wclear(event);
		Pclear(event);
		output.clear();
		accounts = new AccountDatabase();
	}

	/**
	 * End the program.
	 * @param event ActionEvent.
	 */
	@FXML
	void end(ActionEvent event) {
		Platform.exit();
	}
}