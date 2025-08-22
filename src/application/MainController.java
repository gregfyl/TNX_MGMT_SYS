package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;

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

	}

	/**
	 * Disable Loyal Customer and enable Direct Deposit.
	 * @param event ActionEvent.
	 */
	@FXML
	void OselectChecking(ActionEvent event) {

	}

	/**
	 * Disable Direct Deposit and enable Loyal Customer.
	 * @param event ActionEvent.
	 */
	@FXML
	void OselectSavings(ActionEvent event) {

	}

	/**
	 * Disable Direct Deposit and Loyal Customer.
	 * @param event ActionEvent.
	 */
	@FXML
	void OselectMoneyMarket(ActionEvent event) {

	}

	/**
	 * Clear Open section.
	 * @param event ActionEvent.
	 */
	@FXML
	void Oclear(ActionEvent event) {

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

	}

	/**
	 * Clear Close section.
	 * @param event ActionEvent.
	 */
	@FXML
	void Cclear(ActionEvent event) {

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

	}

	/**
	 * Clear Deposit section.
	 * @param event ActionEvent.
	 */
	@FXML
	void Dclear(ActionEvent event) {

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

	}

	/**
	 * Clear Withdraw section.
	 * @param event ActionEvent.
	 */
	@FXML
	void Wclear(ActionEvent event) {

	}

	/** RadioButtons in Print section. */
	@FXML
	private RadioButton PList, PDate, PName;

	/** ToggleGroup in Print section. */
	@FXML
	private ToggleGroup Print;

	/**
	 * Print accounts.
	 * @param event ActionEvent.
	 */
	@FXML
	void print(ActionEvent event) {

	}

	/**
	 * Clear Print section.
	 * @param event ActionEvent.
	 */
	@FXML
	void Pclear(ActionEvent event) {

	}

	/**
	 * Import the database from file.
	 * @param event ActionEvent.
	 */
	@FXML
	void importFile(ActionEvent event) {

	}

	/**
	 * Export the database to file.
	 * @param event ActionEvent.
	 */
	@FXML
	void exportFile(ActionEvent event) {

	}

	/**
	 * Restart the program.
	 * @param event ActionEvent.
	 */
	@FXML
	void restart(ActionEvent event) {

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
