package de.itstimetoforget.backend.rest.service;

import de.itstimetoforget.backend.rest.entity.History;
import de.itstimetoforget.backend.rest.provider.HistoryProvider;
import javassist.NotFoundException;
import org.javers.core.Changes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    private HistoryProvider historyProvider;


    public HistoryService(HistoryProvider historyProvider) {
        this.historyProvider = historyProvider;
    }

    public List<History> getCustomerHistory(Long id) {
        return historyProvider.getCustomerHistory(id);
    }

    public List<History> getCompleteHistory() {
        return historyProvider.getCompleteHistory();
    }

    public List<History> getChangesByClass(String clazz, Long id) throws NotFoundException {
        return historyProvider.getHistoryByClass(clazz, id);
    }
}
