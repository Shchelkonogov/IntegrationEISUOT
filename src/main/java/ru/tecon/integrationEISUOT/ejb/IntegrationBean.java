package ru.tecon.integrationEISUOT.ejb;

import oracle.jdbc.OracleConnection;
import ru.tecon.integrationEISUOT.model.EisuotData;
import ru.tecon.integrationEISUOT.util.IntegrateException;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Stateless bean для работы с бд
 * @author Maksim Shchelkonogov
 */
@Stateless
@LocalBean
public class IntegrationBean {

    private static Logger logger = Logger.getLogger(IntegrationBean.class.getName());

    private static final String FUNCTION_INPUT_EISUOT_DATA = "{? = call EISUOT.INPUT(?)}";
    private static final String INSERT_EISUOT_DATA_TEST = "insert into EISUOT_TEST (RESULT) values (?)";

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
        try (OracleConnection connect = (OracleConnection) ds.getConnection();
             PreparedStatement stm = connect.prepareStatement(INSERT_EISUOT_DATA_TEST);
             CallableStatement cStm = connect.prepareCall(FUNCTION_INPUT_EISUOT_DATA)) {
            for (EisuotData entry: data) {
                stm.setBytes(1, entry.toString().getBytes());
                stm.executeUpdate();

                Object[] row = {entry.getCtp(), entry.getFilial(), entry.getPredpr(), entry.getAddress(),
                        entry.getBuildingType(), entry.getBuildingMaxFloor(), entry.getMuid(), entry.getSchemaGvs(),
                        entry.getAffiliation(), entry.getAvailability(), entry.getNumberGvsZone(), entry.getAffiliationZone(),
                        entry.getDirection(), entry.getClassificationGvs(), entry.getEstimatedCirculation(),
                        entry.getBuildingT1Opt(), entry.getBuildingT2Opt(), entry.getBuildingDtOpt(),
                        entry.getBuildingEstimatedCirculationOpt(), entry.getBuildingT1Dop(), entry.getBuildingDtDop(),
                        entry.getBuildingEstimatedCirculationDop(), entry.getCtpT7Opt(), entry.getCtpT13Opt(),
                        entry.getCtpDtOpt(), entry.getCtpCirculationGvsOpt(), entry.getCtpT7Dop(),
                        entry.getCtpCirculationGvsDop(), entry.getCtpT7$2Opt(), entry.getCtpT13$2Opt(), entry.getCtpDt2Opt(),
                        entry.getCtpCirculationGvs2Opt(), entry.getCtpT7$2Dop(), entry.getCtpCirculationGvs2Dop(),
                        entry.getZoneProblem(), entry.gettProblem()};
                Struct entry_rec = connect.createStruct("T_EISUOT_DATA_REC", row);

                cStm.registerOutParameter(1, Types.INTEGER);
                cStm.setObject(2, entry_rec);

                cStm.executeUpdate();

                if (cStm.getInt(1) != 0) {
                    logger.log(Level.WARNING, "error insert EISUOT data: {0}", entry);
                    result.add((long) cStm.getInt(1));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error insert EISUOT data", e);
            throw new IntegrateException("Database exception", e);
        }
        return result;
    }
}
