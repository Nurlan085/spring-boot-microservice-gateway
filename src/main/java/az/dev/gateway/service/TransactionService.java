package az.dev.gateway.service;

import com.google.gson.JsonElement;

import java.util.List;

public interface TransactionService {

    JsonElement saveTransaction(JsonElement transaction);

    void deleteTransaction(Long transactionId);

    List<JsonElement> getAllTransactionsOfUser(Long userId);
}
