package wwf.org.tenant.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name="tn_tenants")
@Data
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotEmpty(message = "inquilino_vacio")
    @Column(unique = true, nullable = false)
    private String tenant;

    @NotEmpty(message = "domicilio_vacio")
    @Column(unique = true, nullable = false)
    private String domain;

    private String organization;

    @NotEmpty(message = "departamento_wwf_vacio")
    @Column(unique = true, nullable = false)
    private String departmentWwf;

    @NotEmpty(message = "unidad_administrativa_vacia")
    @OneToOne(optional = false)
    @JoinColumn(name="administrativeUnitId", nullable = false, referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    AdministrativeUnit administrativeUnitId;

    @OneToOne(optional = false)
    @JoinColumn(name="countryId", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    Country countryId;

    private Integer attribute1;
    private Integer attribute2;
    private Integer attribute3;
    private Integer attribute4;
    private String attribute5;
    private String attribute6;
    private String attribute7;
    private String attribute8;

    @Temporal(TemporalType.DATE)
    private Date attribute9 ;

    @Temporal(TemporalType.TIMESTAMP)
    private Date attribute10;

    private String status;
    private Long create_by;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creation_date;

    private Long last_update_by;

    @Temporal(TemporalType.TIMESTAMP)
    private Date last_update_date;

}
