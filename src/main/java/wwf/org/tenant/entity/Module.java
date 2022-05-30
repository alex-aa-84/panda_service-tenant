package wwf.org.tenant.entity;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name="tn_modules")
@Data
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotEmpty(message = "modulo_vacio")
    @Column(unique = true, nullable = false)
    private String module;

    private String description;

    @Lob
    private byte[] imagen;

    @NotEmpty(message = "router_link_vacio")
    @Column(unique = true, nullable = false)
    private String routerLink;

    @NotEmpty(message = "servicio_vacio")
    @URL(protocol = "http")
    @Column(unique = true, nullable = false)
    private String serviceUrl;

    private String serviceLanguage;
    private String serviceLanguageVersion;

    @URL(protocol = "http")
    private String serviceGit;

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
