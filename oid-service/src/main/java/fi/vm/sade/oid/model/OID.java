package fi.vm.sade.oid.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oid")
public class OID implements Serializable {

    private static final long serialVersionUID = -1588219820397777572L;

    @Id
    @Column(name = "oid_value")
    public Long oidValue;

    @Column(name = "node", nullable = false)
    private String node;

    @Column(name = "checkDigit", nullable = false)
    private Integer checkDigit;

    public Long getOidValue() {
        return oidValue;
    }

    public void setOidValue(Long oidValue) {
        this.oidValue = oidValue;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public Integer getCheckDigit() {
        return checkDigit;
    }

    public void setCheckDigit(Integer checkDigit) {
        this.checkDigit = checkDigit;
    }

}
