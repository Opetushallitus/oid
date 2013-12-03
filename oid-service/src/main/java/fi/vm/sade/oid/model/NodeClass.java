package fi.vm.sade.oid.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import fi.vm.sade.generic.model.BaseEntity;

@Entity
@Table(name = "nodeclass", uniqueConstraints = @UniqueConstraint(columnNames = "nodeValue", name = "uk_nodeclass_01"))
public class NodeClass extends BaseEntity {

    private static final long serialVersionUID = -3845939583690580029L;

    @Column(name = "nodevalue", nullable = false)
    private String nodeValue;

    @Column(name = "description")
    private String description;

    @Column(name = "classcode")
    private String classCode;

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

}
