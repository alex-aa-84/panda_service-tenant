package wwf.org.tenant.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name="tn_users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotEmpty(message = "inquilino_vacio")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="tenantId", nullable = false, referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Tenant tenantId;

    @NotEmpty(message = "oid_vacio")
    @Column(unique = true, nullable = false)
    private String oid;

    private String userPrincipalName;
    private String displayName;
    private String surname;
    private String givenName;

    @NotEmpty(message = "permisos_inquilino_vacio")
    @ManyToOne(optional = false)
    @JoinColumn(name="permissionsTenantId", nullable = false, referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    PermissionTenant permissionsTenantId;

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
