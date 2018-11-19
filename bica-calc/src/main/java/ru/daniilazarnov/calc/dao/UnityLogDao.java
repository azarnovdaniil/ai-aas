package ru.daniilazarnov.calc.dao;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Repository;
import ru.daniilazarnov.calc.Calc;
import ru.daniilazarnov.calc.domain.UnityLogRow;
import ru.daniilazarnov.calc.service.StorageService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Repository
public class UnityLogDao implements LogDao {

    private final StorageService storageService;

    public UnityLogDao(StorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public List<UnityLogRow> getListEvent() {

        try (Reader reader = new InputStreamReader(Calc.class.getClassLoader().getResourceAsStream("log.csv"))) {

            CsvToBean<UnityLogRow> csvToBean = new CsvToBeanBuilder<UnityLogRow>(reader)
                    .withType(UnityLogRow.class)
                    .withSeparator(',')
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();

        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

}
