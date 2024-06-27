package de.itstimetoforget.backend.rest.service;

import de.itstimetoforget.backend.rest.provider.HistoryProvider;
import org.javers.core.Changes;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {

    private HistoryProvider historyProvider;


    public HistoryService(HistoryProvider historyProvider) {
        this.historyProvider = historyProvider;
    }

    public Changes getCustomerHistory(Long id) {
        return historyProvider.getCustomerHistory(id);
    }

    public Changes getCompleteHistory() {
        return historyProvider.getCompleteHistory();
    }

    public Changes getChangesByClass(Class clazz){
        return historyProvider.getHistoryByClass(clazz);
    }
}
