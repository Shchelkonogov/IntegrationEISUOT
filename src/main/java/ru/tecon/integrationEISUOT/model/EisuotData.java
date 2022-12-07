package ru.tecon.integrationEISUOT.model;

import ru.tecon.integrationEISUOT.util.gson.JsonRequired;

import java.util.StringJoiner;

/**
 * Модель для получения данных на интеграцию по json
 * @author Maksim Shchelkonogov
 */
@JsonRequired
public class EisuotData {

    private String ctp;
    private String filial;
    private String predpr;
    private String address;
    private String buildingType;
    private String buildingMaxFloor;
    private Long muid;
    private String schemaGvs;
    private String affiliation;
    private String availability;
    private String numberGvsZone;
    private String affiliationZone;
    private String direction;
    private String classificationGvs;
    private String estimatedCirculation;
    private String buildingT1Opt;
    private String buildingT2Opt;
    private String buildingDtOpt;
    private String buildingEstimatedCirculationOpt;
    private String buildingT1Dop;
    private String buildingDtDop;
    private String buildingEstimatedCirculationDop;
    private String ctpT7Opt;
    private String ctpT13Opt;
    private String ctpDtOpt;
    private String ctpCirculationGvsOpt;
    private String ctpT7Dop;
    private String ctpCirculationGvsDop;
    private String ctpT7$2Opt;
    private String ctpT13$2Opt;
    private String ctpDt2Opt;
    private String ctpCirculationGvs2Opt;
    private String ctpT7$2Dop;
    private String ctpCirculationGvs2Dop;
    private String zoneProblem;
    private String tProblem;
    private String master;

    public String getCtp() {
        return ctp;
    }

    public String getFilial() {
        return filial;
    }

    public String getPredpr() {
        return predpr;
    }

    public String getAddress() {
        return address;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public String getBuildingMaxFloor() {
        return buildingMaxFloor;
    }

    public Long getMuid() {
        return muid;
    }

    public String getSchemaGvs() {
        return schemaGvs;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getAvailability() {
        return availability;
    }

    public String getNumberGvsZone() {
        return numberGvsZone;
    }

    public String getAffiliationZone() {
        return affiliationZone;
    }

    public String getDirection() {
        return direction;
    }

    public String getClassificationGvs() {
        return classificationGvs;
    }

    public String getEstimatedCirculation() {
        return estimatedCirculation;
    }

    public String getBuildingT1Opt() {
        return buildingT1Opt;
    }

    public String getBuildingT2Opt() {
        return buildingT2Opt;
    }

    public String getBuildingDtOpt() {
        return buildingDtOpt;
    }

    public String getBuildingEstimatedCirculationOpt() {
        return buildingEstimatedCirculationOpt;
    }

    public String getBuildingT1Dop() {
        return buildingT1Dop;
    }

    public String getBuildingDtDop() {
        return buildingDtDop;
    }

    public String getBuildingEstimatedCirculationDop() {
        return buildingEstimatedCirculationDop;
    }

    public String getCtpT7Opt() {
        return ctpT7Opt;
    }

    public String getCtpT13Opt() {
        return ctpT13Opt;
    }

    public String getCtpDtOpt() {
        return ctpDtOpt;
    }

    public String getCtpCirculationGvsOpt() {
        return ctpCirculationGvsOpt;
    }

    public String getCtpT7Dop() {
        return ctpT7Dop;
    }

    public String getCtpCirculationGvsDop() {
        return ctpCirculationGvsDop;
    }

    public String getCtpT7$2Opt() {
        return ctpT7$2Opt;
    }

    public String getCtpT13$2Opt() {
        return ctpT13$2Opt;
    }

    public String getCtpDt2Opt() {
        return ctpDt2Opt;
    }

    public String getCtpCirculationGvs2Opt() {
        return ctpCirculationGvs2Opt;
    }

    public String getCtpT7$2Dop() {
        return ctpT7$2Dop;
    }

    public String getCtpCirculationGvs2Dop() {
        return ctpCirculationGvs2Dop;
    }

    public String getZoneProblem() {
        return zoneProblem;
    }

    public String gettProblem() {
        return tProblem;
    }

    public String getMaster() {
        return master;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EisuotData.class.getSimpleName() + "[", "]")
                .add("ctp='" + ctp + "'")
                .add("filial='" + filial + "'")
                .add("predpr='" + predpr + "'")
                .add("address='" + address + "'")
                .add("buildingType='" + buildingType + "'")
                .add("buildingMaxFloor='" + buildingMaxFloor + "'")
                .add("muid=" + muid)
                .add("schemaGvs='" + schemaGvs + "'")
                .add("affiliation='" + affiliation + "'")
                .add("availability='" + availability + "'")
                .add("numberGvsZone='" + numberGvsZone + "'")
                .add("affiliationZone='" + affiliationZone + "'")
                .add("direction='" + direction + "'")
                .add("classificationGvs='" + classificationGvs + "'")
                .add("estimatedCirculation='" + estimatedCirculation + "'")
                .add("buildingT1Opt='" + buildingT1Opt + "'")
                .add("buildingT2Opt='" + buildingT2Opt + "'")
                .add("buildingDtOpt='" + buildingDtOpt + "'")
                .add("buildingEstimatedCirculationOpt='" + buildingEstimatedCirculationOpt + "'")
                .add("buildingT1Dop='" + buildingT1Dop + "'")
                .add("buildingDtDop='" + buildingDtDop + "'")
                .add("buildingEstimatedCirculationDop='" + buildingEstimatedCirculationDop + "'")
                .add("ctpT7Opt='" + ctpT7Opt + "'")
                .add("ctpT13Opt='" + ctpT13Opt + "'")
                .add("ctpDtOpt='" + ctpDtOpt + "'")
                .add("ctpCirculationGvsOpt='" + ctpCirculationGvsOpt + "'")
                .add("ctpT7Dop='" + ctpT7Dop + "'")
                .add("ctpCirculationGvsDop='" + ctpCirculationGvsDop + "'")
                .add("ctpT7$2Opt='" + ctpT7$2Opt + "'")
                .add("ctpT13$2Opt='" + ctpT13$2Opt + "'")
                .add("ctpDt2Opt='" + ctpDt2Opt + "'")
                .add("ctpCirculationGvs2Opt='" + ctpCirculationGvs2Opt + "'")
                .add("ctpT7$2Dop='" + ctpT7$2Dop + "'")
                .add("ctpCirculationGvs2Dop='" + ctpCirculationGvs2Dop + "'")
                .add("zoneProblem='" + zoneProblem + "'")
                .add("tProblem='" + tProblem + "'")
                .add("master='" + master + "'")
                .toString();
    }
}
