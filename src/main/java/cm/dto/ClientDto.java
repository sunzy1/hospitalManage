package cm.dto;

import cm.pojo.Client;

import java.io.Serializable;
import java.util.Date;

/**
 *
 *ClientDto
 * @author sunzy
 * @date 2020/03/29
 */
public class ClientDto extends Client implements Serializable {
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
