package cm.dto;

import cm.pojo.Medicine;

import java.io.Serializable;
import java.util.Date;

public class MedicineDto extends Medicine implements Serializable {
    private Date startDate;
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
