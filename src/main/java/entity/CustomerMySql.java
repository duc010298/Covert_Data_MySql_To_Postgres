package entity;

import java.util.Date;

public class CustomerMySql {
    private int id;
    private String name;
    private String nameS;
    private int yOB;
    private String addressCus;
    private String addressCusS;
    private Date dayVisit;
    private Date expectedDOB;
    private String result;
    private String note;
    private String report;

    public CustomerMySql(int id, String name, String nameS, int yOB, String addressCus, String addressCusS, Date dayVisit, Date expectedDOB, String result, String note, String report) {
        this.id = id;
        this.name = name;
        this.nameS = nameS;
        this.yOB = yOB;
        this.addressCus = addressCus;
        this.addressCusS = addressCusS;
        this.dayVisit = dayVisit;
        this.expectedDOB = expectedDOB;
        this.result = result;
        this.note = note;
        this.report = report;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameS() {
        return nameS;
    }

    public void setNameS(String nameS) {
        this.nameS = nameS;
    }

    public int getyOB() {
        return yOB;
    }

    public void setyOB(int yOB) {
        this.yOB = yOB;
    }

    public String getAddressCus() {
        return addressCus;
    }

    public void setAddressCus(String addressCus) {
        this.addressCus = addressCus;
    }

    public String getAddressCusS() {
        return addressCusS;
    }

    public void setAddressCusS(String addressCusS) {
        this.addressCusS = addressCusS;
    }

    public Date getDayVisit() {
        return dayVisit;
    }

    public void setDayVisit(Date dayVisit) {
        this.dayVisit = dayVisit;
    }

    public Date getExpectedDOB() {
        return expectedDOB;
    }

    public void setExpectedDOB(Date expectedDOB) {
        this.expectedDOB = expectedDOB;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
