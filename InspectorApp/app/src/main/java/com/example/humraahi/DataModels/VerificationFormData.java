package com.example.humraahi.DataModels;

import java.util.ArrayList;

public class VerificationFormData {

    private String audit;
    private String roadName;
    private String inspectionCodes;
    private String remedialCodes;
    private String activities;
    private String localName;
    private String priority;
    private String risk;
    private String description;
    private String streamCulvert;
    private ArrayList<String> postedSignage;
    private ArrayList<String> roadDesign;

    public VerificationFormData(String audit,
            String roadName,
            String inspectionCodes,
            String remedialCodes,
            String activities,
            String localName,
            String priority,
            String risk,
            String description,
            String streamCulvert,
            ArrayList<String> postedSignage,
            ArrayList<String> roadDesign){

        this.audit = audit;
        this.roadName = roadName;
        this.inspectionCodes = inspectionCodes;
        this.remedialCodes = remedialCodes;
        this.activities = activities;
        this.localName = localName;
        this.priority = priority;
        this.risk = risk;
        this.description = description;
        this.streamCulvert = streamCulvert;
        this.postedSignage = postedSignage;
        this.roadDesign = roadDesign;
    }

    public String getAudit(){
        return this.audit;
    }

    public String getRoadName(){
        return this.roadName;
    }

    public String getInspectionCodes(){
        return this.inspectionCodes;
    }

    public String getRemedialCodes(){
        return this.remedialCodes;
    }

    public String getActivities(){
        return this.activities;
    }

    public String getLocalName(){
        return this.localName;
    }

    public String getPriority(){
        return this.priority;
    }

    public String getRisk(){
        return this.risk;
    }

    public String getDescription(){
        return this.description;
    }

    public String getStreamCulvert(){
        return this.streamCulvert;
    }

    public ArrayList<String> getPostedSignage(){
        return this.postedSignage;
    }

    public ArrayList<String> getRoadDesign(){
        return this.roadDesign;
    }

}


