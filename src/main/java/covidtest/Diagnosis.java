package covidtest;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Diagnosis_table")
public class Diagnosis {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long inspectionId;
    private Long kitId;
    private String status;
    private String result;

    public Diagnosis() {
    }

    @PostUpdate
    public void onPostUpdate(){
        Diagnosed diagnosed = new Diagnosed();
        BeanUtils.copyProperties(this, diagnosed);
        diagnosed.publishAfterCommit();


    }

    public Diagnosis(Sent sent){
        this.setInspectionId(sent.getInspectionId());
        this.setKitId(sent.getKitId());
        this.setStatus("TESTING");
        this.setResult("UNKNOWN");
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(Long inspectionId) {
        this.inspectionId = inspectionId;
    }
    public Long getKitId() {
        return kitId;
    }

    public void setKitId(Long kitId) {
        this.kitId = kitId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }




}
