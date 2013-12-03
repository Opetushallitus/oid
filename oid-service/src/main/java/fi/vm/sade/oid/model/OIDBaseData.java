package fi.vm.sade.oid.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import fi.vm.sade.generic.model.BaseEntity;

@Entity
@Table(name = "oid_base_data",
        uniqueConstraints = @UniqueConstraint(columnNames = "key", name = "uk_oid_base_data_01"))
public class OIDBaseData extends BaseEntity {

    private static final long serialVersionUID = 5211836622983672875L;

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
