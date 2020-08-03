package com.example.humraahi.DataModels;

public class ScheduleDetails {

    private String location;
    private String assignedDate;
    private String deadline;
    private String tag;
    private Integer projectId;
    private String type;


    public ScheduleDetails(String location, String assignedDate, String type, String deadline, String tag, Integer pid){
        this.location = location;
        this.assignedDate = assignedDate;
        this.deadline = deadline;
        this.tag = tag;
        this.type = type;
        this.projectId = pid;
    }

    public String getLocation(){
        return this.location;
    }
    public String getAssignedDate(){
        return this.assignedDate;
    }
    public String getDeadline(){
        return this.deadline;
    }
    public String getTag(){
        return this.tag;
    }
    public String getType() { return  type;}
    public Integer getProjectId(){
        return this.projectId;
    }

}
