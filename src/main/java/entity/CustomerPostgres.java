package entity;

import java.sql.Date;

public class CustomerPostgres {
    private int id;
    private String customerName;
    private String nameSearch;
    private Integer yob;
    private String address;
    private String addressSearch;
    private Date dayVisit;
    private Date expectedDob;
    private String result;
    private String note;
    private String report;

    public CustomerPostgres(int id, String customerName, String nameSearch, Integer yob, String address, String addressSearch, Date dayVisit, Date expectedDob, String result, String note, String report) {
        this.id = id;
        this.customerName = customerName;
        this.nameSearch = nameSearch;
        this.yob = yob;
        this.address = address;
        this.addressSearch = addressSearch;
        this.dayVisit = dayVisit;
        this.expectedDob = expectedDob;
        this.result = result;
        this.note = note;
        this.report = report;
    }

    public CustomerPostgres() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getNameSearch() {
        return nameSearch;
    }

    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    public Integer getYob() {
        return yob;
    }

    public void setYob(Integer yob) {
        this.yob = yob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressSearch() {
        return addressSearch;
    }

    public void setAddressSearch(String addressSearch) {
        this.addressSearch = addressSearch;
    }

    public Date getDayVisit() {
        return dayVisit;
    }

    public void setDayVisit(Date dayVisit) {
        this.dayVisit = dayVisit;
    }

    public Date getExpectedDob() {
        return expectedDob;
    }

    public void setExpectedDob(Date expectedDob) {
        this.expectedDob = expectedDob;
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
