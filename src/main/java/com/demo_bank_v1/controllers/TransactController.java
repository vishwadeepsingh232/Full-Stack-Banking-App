package com.demo_bank_v1.controllers;

import com.demo_bank_v1.models.User;
import com.demo_bank_v1.repository.AccountRepository;
import com.demo_bank_v1.repository.PaymentRepository;
import com.demo_bank_v1.repository.TransactRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/transact")
public class TransactController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TransactRepository transactRepository;

    private LocalDateTime currentDateTime = LocalDateTime.now();

    @PostMapping("/transfer")
    public String transfer(@RequestParam("transfer_from") String transfer_from,
                           @RequestParam("transfer_to") String transfer_to,
                           @RequestParam("transfer_amount") String transfer_amount,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {

        // Check for empty fields
        if (transfer_from.isEmpty() || transfer_to.isEmpty() || transfer_amount.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Transfer details cannot be empty.");
            return "redirect:/app/dashboard";
        }

        // Convert parameters to appropriate types
        int transferFromId;
        int transferToId;
        double transferAmount;
        try {
            transferFromId = Integer.parseInt(transfer_from);
            transferToId = Integer.parseInt(transfer_to);
            transferAmount = Double.parseDouble(transfer_amount);
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("error", "Invalid transfer details. Please enter valid numbers.");
            return "redirect:/app/dashboard";
        }

        // Ensure transfer is not into the same account
        if (transferFromId == transferToId) {
            redirectAttributes.addFlashAttribute("error", "Cannot transfer into the same account.");
            return "redirect:/app/dashboard";
        }

        // Check for zero transfer amount
        if (transferAmount <= 0) {
            redirectAttributes.addFlashAttribute("error", "Transfer amount must be greater than zero.");
            return "redirect:/app/dashboard";
        }

        // Get logged-in user
        User user = (User) session.getAttribute("user");

        // Get current balance of transferring account
        double currentBalanceOfAccountTransferringFrom = accountRepository.getAccountBalance(user.getUser_id(), transferFromId);

        // Check if sufficient funds are available
        if (currentBalanceOfAccountTransferringFrom < transferAmount) {
            redirectAttributes.addFlashAttribute("error", "Insufficient funds to perform transfer.");
            // Log failed transaction
            transactRepository.logTransaction(transferFromId, "transfer", transferAmount, "online", "failed", "Insufficient Funds", currentDateTime);
            return "redirect:/app/dashboard";
        }

        // Calculate new balances
        double currentBalanceOfAccountTransferringTo = accountRepository.getAccountBalance(user.getUser_id(), transferToId);
        double newBalanceOfAccountTransferringFrom = currentBalanceOfAccountTransferringFrom - transferAmount;
        double newBalanceOfAccountTransferringTo = currentBalanceOfAccountTransferringTo + transferAmount;

        // Update balances
        accountRepository.changeAccountBalanceById(newBalanceOfAccountTransferringFrom, transferFromId);
        accountRepository.changeAccountBalanceById(newBalanceOfAccountTransferringTo, transferToId);

        // Log successful transaction
        transactRepository.logTransaction(transferFromId, "Transfer", transferAmount, "online", "success", "Transfer Transaction Successful", currentDateTime);

        redirectAttributes.addFlashAttribute("success", "Amount Transferred Successfully.");
        return "redirect:/app/dashboard";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam("deposite_amount") String depositAmount,
                          @RequestParam("account_id") String accountID,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {

        // Check for empty fields
        if (depositAmount.isEmpty() || accountID.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Deposit amount or account ID cannot be empty.");
            return "redirect:/app/dashboard";
        }

        // Get logged-in user
        User user = (User) session.getAttribute("user");

        // Convert parameters to appropriate types
        int acc_id;
        double depositAmountValue;
        try {
            acc_id = Integer.parseInt(accountID);
            depositAmountValue = Double.parseDouble(depositAmount);
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("error", "Invalid deposit details. Please enter valid numbers.");
            return "redirect:/app/dashboard";
        }

        // Check if deposit amount is zero
        if (depositAmountValue <= 0) {
            redirectAttributes.addFlashAttribute("error", "Deposit amount must be greater than zero.");
            return "redirect:/app/dashboard";
        }

        // Get current balance
        double currentBalance = accountRepository.getAccountBalance(user.getUser_id(), acc_id);

        // Update balance
        double newBalance = currentBalance + depositAmountValue;
        accountRepository.changeAccountBalanceById(newBalance, acc_id);

        // Log successful transaction
        transactRepository.logTransaction(acc_id, "deposit", depositAmountValue, "online", "success", "Deposit Transaction Successful", currentDateTime);

        redirectAttributes.addFlashAttribute("success", "Amount Deposited Successfully.");
        return "redirect:/app/dashboard";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam("withdraw_amount") String withdrawalAmount,
                           @RequestParam("account_id") String accountID,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {

        // Check for empty fields
        if (withdrawalAmount.isEmpty() || accountID.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Withdrawal amount or account ID cannot be empty.");
            return "redirect:/app/dashboard";
        }

        // Get logged-in user
        User user = (User) session.getAttribute("user");

        // Convert parameters to appropriate types
        double withdrawal_amount;
        int account_id;
        try {
            withdrawal_amount = Double.parseDouble(withdrawalAmount);
            account_id = Integer.parseInt(accountID);
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("error", "Invalid withdrawal details. Please enter valid numbers.");
            return "redirect:/app/dashboard";
        }

        // Check if withdrawal amount is zero
        if (withdrawal_amount <= 0) {
            redirectAttributes.addFlashAttribute("error", "Withdrawal amount must be greater than zero.");
            return "redirect:/app/dashboard";
        }

        // Get current balance
        double currentBalance = accountRepository.getAccountBalance(user.getUser_id(), account_id);

        // Check if sufficient funds are available
        if (currentBalance < withdrawal_amount) {
            redirectAttributes.addFlashAttribute("error", "Insufficient funds to perform withdrawal.");
            // Log failed transaction
            transactRepository.logTransaction(account_id, "withdrawal", withdrawal_amount, "online", "failed", "Insufficient Funds", currentDateTime);
            return "redirect:/app/dashboard";
        }

        // Calculate new balance
        double newBalance = currentBalance - withdrawal_amount;

        // Update balance
        accountRepository.changeAccountBalanceById(newBalance, account_id);

        // Log successful transaction
        transactRepository.logTransaction(account_id, "Withdraw", withdrawal_amount, "online", "success", "Withdraw Transaction Successful", currentDateTime);

        redirectAttributes.addFlashAttribute("success", "Amount Withdrawn Successfully.");
        return "redirect:/app/dashboard";
    }

    @PostMapping("/payment")
    public String payment(@RequestParam("beneficiary") String beneficiary,
                          @RequestParam("account_number") String account_number,
                          @RequestParam("account_id") String accountID,
                          @RequestParam("reference") String reference,
                          @RequestParam("payment_amount") String payment_amount,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {

        // Check for empty fields
        if (beneficiary.isEmpty() || account_number.isEmpty() || accountID.isEmpty() || payment_amount.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Payment details cannot be empty.");
            return "redirect:/app/dashboard";
        }

        // Get logged-in user
        User user = (User) session.getAttribute("user");

        // Convert parameters to appropriate types
        int account_id;
        double paymentAmount;
        try {
            account_id = Integer.parseInt(accountID);
            paymentAmount = Double.parseDouble(payment_amount);
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("error", "Invalid payment details. Please enter valid numbers.");
            return "redirect:/app/dashboard";
        }

        // Check if payment amount is zero
        if (paymentAmount <= 0) {
            redirectAttributes.addFlashAttribute("error", "Payment amount must be greater than zero.");
            return "redirect:/app/dashboard";
        }

        // Get current balance
        double currentBalance = accountRepository.getAccountBalance(user.getUser_id(), account_id);

        // Check if sufficient funds are available
        if (currentBalance < paymentAmount) {
            redirectAttributes.addFlashAttribute("error", "Insufficient funds to perform payment.");
            String reasonCode = "Could not Processed Payment due to insufficient funds!";
            paymentRepository.makePayment(account_id, beneficiary, account_number, paymentAmount, reference, "failed", reasonCode, currentDateTime);
            // Log failed transaction
            transactRepository.logTransaction(account_id, "payment", paymentAmount, "online", "failed", "Insufficient Funds", currentDateTime);
            return "redirect:/app/dashboard";
        }

        // Calculate new balance
        double newBalance = currentBalance - paymentAmount;

        // Make payment
        String reasonCode = "Payment Processed Successfully";
        paymentRepository.makePayment(account_id, beneficiary, account_number, paymentAmount, reference, "success", reasonCode, currentDateTime);

        // Update balance
        accountRepository.changeAccountBalanceById(newBalance, account_id);

        // Log successful transaction
        transactRepository.logTransaction(account_id, "Payment", paymentAmount, "online", "success", "Payment Transaction Successful", currentDateTime);

        redirectAttributes.addFlashAttribute("success", "Payment Made Successfully.");
        return "redirect:/app/dashboard";
    }
}

