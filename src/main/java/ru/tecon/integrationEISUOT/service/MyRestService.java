package ru.tecon.integrationEISUOT.service;

import com.google.gson.Gson;
import ru.tecon.integrationEISUOT.ejb.IntegrationBean;
import ru.tecon.integrationEISUOT.model.EisuotData;
import ru.tecon.integrationEISUOT.util.IntegrateException;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * REST сервис для интеграции с ЕИСУОТ
 * @author Maksim Shchelkonogov
 */
@Path("/")
public class MyRestService {

    private static Logger logger = Logger.getLogger(MyRestService.class.getName());

    @EJB
    private IntegrationBean integrationBean;

    /**
     * Получение данных для интеграции с ЕИСУОТ
     * @param eisuotData список данных для внесения в базу
     * @return если ошибка работы, то вернет 500,<br>
     *     если данные внесены, то вернет 201,<br>
     *     если данные внесены частично, то вернет 206 и список muid, которые не внесены в базу
     */
    @POST
    @Path("/EISUOT")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setEisuotData(List<EisuotData> eisuotData) {
        logger.log(Level.INFO, "integrate data: {0}", eisuotData);
        List<Long> unknownMuids;
        try {
            unknownMuids = integrationBean.setEisuotData(eisuotData);
        } catch (IntegrateException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

        if (unknownMuids.isEmpty()) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            Map<String, List<Long>> unknownMuidsMap = new HashMap<>();
            unknownMuidsMap.put("muid", unknownMuids);
            return Response.status(Response.Status.PARTIAL_CONTENT).entity(new Gson().toJson(unknownMuidsMap)).build();
        }
    }
}
