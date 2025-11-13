package ru.tecon.integrationEISUOT.ejb;

import jakarta.annotation.Resource;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import org.postgresql.util.PGobject;
import ru.tecon.integrationEISUOT.model.EisuotData;
import ru.tecon.integrationEISUOT.util.IntegrateException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Stateless bean для работы с бд
 * @author Maksim Shchelkonogov
 */
@Stateless
@LocalBean
public class IntegrationBean {

    private static final Logger logger = Logger.getLogger(IntegrationBean.class.getName());

    private static final String FUNCTION_INPUT_EISUOT_DATA = "{? = call EISUOT.INPUT(?)}";
    private static final String INSERT_EISUOT_DATA_TEST = "insert into admin.EISUOT_TEST (RESULT) values (?)";

    @Resource(name = "jdbc/DataSource")
    private DataSource ds;

    /**
     * Внесение в базу данных по интеграции с ЕИСУОТ
     * @param data данные для внесения в базу
     * @return список muid, которые не удалось внести в базу
     * @throws IntegrateException в случае ошибки внесения данных
     */
    public List<Long> setEisuotData(List<EisuotData> data) throws IntegrateException {
        List<Long> result = new ArrayList<>();
        try (Connection connect = ds.getConnection();
             PreparedStatement stm = connect.prepareStatement(INSERT_EISUOT_DATA_TEST);
             CallableStatement cStm = connect.prepareCall(FUNCTION_INPUT_EISUOT_DATA)) {
            stm.setBytes(1, data.toString().getBytes());
            stm.executeUpdate();

            for (EisuotData entry: data) {
                StringJoiner sj = new StringJoiner(", ", "(", ")")
                        .add(entry.getCtp())
                        .add(entry.getFilial())
                        .add(entry.getPredpr())
                        .add(entry.getAddress())
                        .add(entry.getBuildingType())
                        .add(entry.getBuildingMaxFloor())
                        .add(entry.getMuid().toString())
                        .add(entry.getSchemaGvs())
                        .add(entry.getAffiliation())
                        .add(entry.getAvailability())
                        .add(entry.getNumberGvsZone())
                        .add(entry.getAffiliationZone())
                        .add(entry.getDirection())
                        .add(entry.getClassificationGvs())
                        .add(entry.getEstimatedCirculation())
                        .add(entry.getBuildingT1Opt())
                        .add(entry.getBuildingT2Opt())
                        .add(entry.getBuildingDtOpt())
                        .add(entry.getBuildingEstimatedCirculationOpt())
                        .add(entry.getBuildingT1Dop())
                        .add(entry.getBuildingDtDop())
                        .add(entry.getBuildingEstimatedCirculationDop())
                        .add(entry.getCtpT7Opt())
                        .add(entry.getCtpT13Opt())
                        .add(entry.getCtpDtOpt())
                        .add(entry.getCtpCirculationGvsOpt())
                        .add(entry.getCtpT7Dop())
                        .add(entry.getCtpCirculationGvsDop())
                        .add(entry.getCtpT7$2Opt())
                        .add(entry.getCtpT13$2Opt())
                        .add(entry.getCtpDt2Opt())
                        .add(entry.getCtpCirculationGvs2Opt())
                        .add(entry.getCtpT7$2Dop())
                        .add(entry.getCtpCirculationGvs2Dop())
                        .add(entry.getZoneProblem())
                        .add(entry.gettProblem())
                        .add(entry.getMaster());

                PGobject pGobject = new PGobject();
                pGobject.setType("eisuot.t_eisuot_data");
                pGobject.setValue(sj.toString());

                cStm.registerOutParameter(1, Types.BIGINT);
                cStm.setObject(2, pGobject);

                cStm.executeUpdate();

                if (cStm.getLong(1) != 0) {
                    logger.log(Level.WARNING, "error insert EISUOT data: {0}", entry);
                    result.add(cStm.getLong(1));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error insert EISUOT data", e);
            throw new IntegrateException("Database exception", e);
        }
        return result;
    }
}
