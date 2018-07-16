package bitcamp.pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class Team implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String description;
    private int maxQty;
    private Date startDate;
    private Date endDate;

    public String getName() {
        return name;
    }

    public Team setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Team setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getMaxQty() {
        return maxQty;
    }

    public Team setMaxQty(int maxQty) {
        this.maxQty = maxQty;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Team setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Team setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }
    
    @Override
    public String toString() {
        return "Team [name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + "]";
    }
    
}