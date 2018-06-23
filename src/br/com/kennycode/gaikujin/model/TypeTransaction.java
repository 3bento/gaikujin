package br.com.kennycode.gaikujin.model;

public enum TypeTransaction {

	/**
	 * Description of type of transaction:
	 * 
	 * @SALES are the transactions in which property is transferred from buyer to
	 *        seller for money or credit. Sales transactions are recorded in the
	 *        accounting journal for the seller as a debit to cash or accounts
	 *        receivable and a credit to the sales account.
	 * @PURCHASES are the transactions that are required by a business in order to
	 *            obtain the goods or services needed to accomplish the goals of the
	 *            organization. Purchases made in cash result in a debit to the
	 *            inventory account and a credit to cash. If the purchase is made
	 *            with a credit account, the debit entry would still be to the
	 *            inventory account and the credit entry would be to the accounts
	 *            payable account.
	 * @RECEIPTS are the transactions that refer to a business getting paid for
	 *           delivering goods or services to another business. The receipt
	 *           transaction is recorded in the journal for the seller as a debit to
	 *           cash and a credit to accounts receivable.
	 * @PAYMENTS are the transactions that refer to a business receiving money for a
	 *           good or service. They are recorded in the accounting journal of the
	 *           business issuing the payment as a credit to cash and a debit to
	 *           accounts payable.
	 * 
	 */

	SALES, PURCHASES, RECEIPTS, PAYMENTS
}
